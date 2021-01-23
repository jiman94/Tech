
```yaml 
server.port=8081

security.oauth2.client.authority=https://login.microsoftonline.com/common/
security.oauth2.client.client-id=
security.oauth2.client.client-secret=

security.oauth2.resource.id=
security.oauth2.scope.access-as-user=access_as_user
security.oauth2.resource.jwt.key-uri=https://login.microsoftonline.com/common/discovery/v2.0/keys
security.oauth2.issuer=https://login.microsoftonline.com/<TenantId>/v2.0
```


```java

// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.microsoft.azure.msalobosample;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.DefaultAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.IssuerClaimVerifier;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtClaimsSetVerifier;
import org.springframework.security.oauth2.provider.token.store.jwk.JwkTokenStore;

import java.net.MalformedURLException;
import java.net.URL;

@Configuration
public class SecurityResourceServerConfig extends ResourceServerConfigurerAdapter {

    @Value("${security.oauth2.resource.jwt.key-uri}")
    private String keySetUri;

    @Value("${security.oauth2.resource.id}")
    private String resourceId;

    @Value("${security.oauth2.issuer}")
    private String issuer;

    @Value("${security.oauth2.scope.access-as-user}")
    private String accessAsUserScope;

    private final String AAD_SCOPE_CLAIM = "scp";

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers("/*")
                .access("#oauth2.hasScope('" + accessAsUserScope + "')"); // required scope to access /api URL
    }

    @Bean
    public TokenStore tokenStore() {
        JwkTokenStore jwkTokenStore = new JwkTokenStore(keySetUri, accessTokenConverter(), issuerClaimVerifier());
        return jwkTokenStore;
    }

    @Bean
    public JwtAccessTokenConverter accessTokenConverter() {
        JwtAccessTokenConverter jwtConverter = new JwtAccessTokenConverter();

        DefaultAccessTokenConverter accessTokenConverter = new DefaultAccessTokenConverter();
        accessTokenConverter.setScopeAttribute(AAD_SCOPE_CLAIM);

        jwtConverter.setAccessTokenConverter(accessTokenConverter);

        return jwtConverter;
    }

    @Override
    public void configure(ResourceServerSecurityConfigurer resources){
        resources.resourceId(resourceId);
    }

    @Bean
    public JwtClaimsSetVerifier issuerClaimVerifier() {
        try {
            return new IssuerClaimVerifier(new URL(issuer));
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }
}
```



```java
@SpringBootApplication
@EnableResourceServer
@EnableCaching
public class MsalOboSampleApplication {

    public static void main(String[] args) {
        SpringApplication.run(MsalOboSampleApplication.class, args);
    }
}
```

```java

@Component
class MsalAuthHelper {

    @Value("${security.oauth2.client.authority}")
    private String authority;

    @Value("${security.oauth2.client.client-id}")
    private String clientId;

    @Value("${security.oauth2.client.client-secret}")
    private String secret;

    @Autowired
    CacheManager cacheManager;

    private String getAuthToken(){
        String res = null;
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if(authentication != null){
            res = ((OAuth2AuthenticationDetails) authentication.getDetails()).getTokenValue();
        }
        return res;
    }

    String getOboToken(String scope) throws MalformedURLException {
        String authToken = getAuthToken();

        ConfidentialClientApplication application =
                ConfidentialClientApplication.builder(clientId, ClientCredentialFactory.createFromSecret(secret))
                        .authority(authority).build();

        String cacheKey = Hashing.sha256()
                .hashString(authToken, StandardCharsets.UTF_8).toString();

        String cachedTokens = cacheManager.getCache("tokens").get(cacheKey, String.class);
        if(cachedTokens != null){
            application.tokenCache().deserialize(cachedTokens);
        }

        IAuthenticationResult auth;
        SilentParameters silentParameters =
                SilentParameters.builder(Collections.singleton(scope))
                        .build();
        auth = application.acquireTokenSilently(silentParameters).join();

        if (auth == null){
            OnBehalfOfParameters parameters =
                    OnBehalfOfParameters.builder(Collections.singleton(scope),
                            new UserAssertion(authToken))
                            .build();

            auth = application.acquireToken(parameters).join();
        }

        cacheManager.getCache("tokens").put(cacheKey, application.tokenCache().serialize());

        return auth.accessToken();
    }
}

```

```java

@Configuration
@EnableCaching
public class CachingConfig {
    @Bean
    public CacheManager cacheManager() {
        return new ConcurrentMapCacheManager("tokens"){
            @Override
            protected Cache createConcurrentMapCache(final String name) {
                return new ConcurrentMapCache(name,
                        CacheBuilder
                                .newBuilder()
                                .expireAfterWrite(1, TimeUnit.HOURS)// AAD access tokens expires in 1 hour
                                .maximumSize(1000)
                                .build()
                                .asMap(), false);
            }
        };
    }
}
```



```java

@RestController
public class ApiController {

    @Autowired
    MsalAuthHelper msalAuthHelper;

    @RequestMapping("/graphMeApi")
    public String graphMeApi() throws MalformedURLException {

        String oboAccessToken = msalAuthHelper.getOboToken("https://graph.microsoft.com/.default");

        return callMicrosoftGraphMeEndpoint(oboAccessToken);
    }

    private String callMicrosoftGraphMeEndpoint(String accessToken){
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        headers.set("Authorization", "Bearer " + accessToken);

        HttpEntity<String> entity = new HttpEntity<>(null, headers);

        String result = restTemplate.exchange("https://graph.microsoft.com/v1.0/me", HttpMethod.GET,
                entity, String.class).getBody();

        return result;
    }
}

```








