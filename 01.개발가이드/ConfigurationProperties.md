# ConfigurationProperties



#### BasicConfiguration

```java
@Component
@ConfigurationProperties("aad")
class BasicConfiguration {
    String clientId;
    String authority;
    String redirectUri;
    String secretKey;
    String oboApi;
```

#### application.properties 

```yaml 
aad.authority=https://login.microsoftonline.com/common/
aad.clientId=Enter_the_Application_Id_Here
aad.secretKey=Enter_the_Client_Secret_Here
aad.redirectUri=http://localhost:8443/msal4jsample/secure/aad
aad.oboApi=api://Enter_the_Obo_Api_Application_Id_Here/access_as_user
aad.webapp.defaultScope=api://Enter_the_Obo_Api_Application_Id_Here/.default
```


```java
@Getter
@Setter
@Component
@ConfigurationProperties("b2c")
class BasicConfiguration {
    String clientId;
    String secret;
    String redirectUri;

    String api;
    String apiScope;

    String signUpSignInAuthority;
    String editProfileAuthority;
    String resetPasswordAuthority;
}
```