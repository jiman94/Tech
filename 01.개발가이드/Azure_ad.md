# azure-spring-boot-sample-active-directory-b2c-oidc

```yaml 
azure:
  activedirectory:
    b2c:
      tenant: ${your-tenant-name}
      oidc-enabled: true
      client-id: ${your-client-id}
      client-secret: ${your-client-secret}
      reply-url: ${your-reply-url-from-aad} # should be absolute url.
      logout-success-url: ${you-logout-success-url}
      user-flows:
        sign-up-or-sign-in: ${your-sign-up-or-in-user-flow}
        profile-edit: ${your-profile-edit-user-flow}     # optional
        password-reset: ${your-password-reset-user-flow} # optional
```        

```java
package com.azure.aad.b2c.controller;

import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebController {

    private void initializeModel(Model model, OAuth2AuthenticationToken token) {
        if (token != null) {
            final OAuth2User user = token.getPrincipal();

            model.addAttribute("grant_type", user.getAuthorities());
            model.addAllAttributes(user.getAttributes());
        }
    }

    @GetMapping(value = "/")
    public String index(Model model, OAuth2AuthenticationToken token) {
        initializeModel(model, token);

        return "home";
    }

    @GetMapping(value = "/greeting")
    public String greeting(Model model, OAuth2AuthenticationToken token) {
        initializeModel(model, token);

        return "greeting";
    }

    @GetMapping(value = "/home")
    public String home(Model model, OAuth2AuthenticationToken token) {
        initializeModel(model, token);

        return "home";
    }
}
```

```java
package com.azure.aad.b2c.security;

import com.microsoft.azure.spring.autoconfigure.b2c.AADB2COidcLoginConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

    private final AADB2COidcLoginConfigurer configurer;

    public WebSecurityConfiguration(AADB2COidcLoginConfigurer configurer) {
        this.configurer = configurer;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
            .anyRequest()
            .authenticated()
            .and()
            .apply(configurer);
    }
}
```