현대 웹서비스에서는 토큰을 사용하여 사용자들의 인증 작업을 처리하는 것이 가장 좋은 방법이다. 이번에는 토큰 기반의 인증 시스템에서 주로 사용하는 JWT(Json Web Token)에 대해 SpringBoot와 Spring Security 기반으로 직접 제작해보도록 하겠다.

1. Spring Security 처리 과정

Spring Security 아키텍쳐는 위와 같으며 각각의 처리 과정에 대해서 자세히 알아보도록 하자.(아래에서 설명하는 내용은 Json Web Token을 활용한 Spring Security의 구현 방식으로, Session과 Token 기반의 차이점에 대해서는 여기를 참고하시고, Form을 활용한 Session기반의 구현 방식이 궁금하시다면 여기를 참고해주세요!)

 

 

 

[ 0. 사전 세팅 ]
먼저 프로젝트에서 사용할 Dependency들을 build.gradle에 추가해준다.

dependencies {

    implementation 'io.jsonwebtoken:jjwt:0.9.1'
    
    implementation 'org.mariadb.jdbc:mariadb-java-client'
    implementation 'org.springframework.boot:spring-boot-starter-data-jpa'
    
    implementation 'org.springframework.boot:spring-boot-starter-security'
    
    implementation 'org.springframework.boot:spring-boot-starter-thymeleaf'
    implementation 'org.springframework.boot:spring-boot-starter-web'
    
    compileOnly 'org.projectlombok:lombok'
    annotationProcessor 'org.projectlombok:lombok'
    
    testImplementation('org.springframework.boot:spring-boot-starter-test') {
        exclude group: 'org.junit.vintage', module: 'junit-vintage-engine'
    }
    testImplementation 'org.springframework.security:spring-security-test'
}
 

그리고 정적 자원을 제공하는 클래스를 생성하여 아래와 같이 설정한다.

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    private static final String[] CLASSPATH_RESOURCE_LOCATIONS = { "classpath:/static/", "classpath:/public/", "classpath:/",
            "classpath:/resources/", "classpath:/META-INF/resources/", "classpath:/META-INF/resources/webjars/" };

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        // /에 해당하는 url mapping을 /common/test로 forward한다.
        registry.addViewController( "/" ).setViewName( "forward:/index" );
        // 우선순위를 가장 높게 잡는다.
        registry.setOrder(Ordered.HIGHEST_PRECEDENCE);
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**").addResourceLocations(CLASSPATH_RESOURCE_LOCATIONS);
    }

}
 

그리고 SpringSecurity에 대한 기본적인 설정들을 추가한다. SpringSecurity에 대한 설정 클래스에서는

configure 메소드를 통해 정적 자원들에 대해서는 Security를 적용하지 않음을 추가한다.
configure 메소드를 통해 어떤 요청에 대해서는 로그인을 요구하고, 어떤 요청에 대해서 로그인을 요구하지 않을지 설정한다.
form 기반의 로그인을 비활성화 한다.
package com.mang.example.security.config.security;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    // 정적 자원에 대해서는 Security 설정을 적용하지 않음.
    @Override
    public void configure(WebSecurity web) {
        web.ignoring().requestMatchers(PathRequest.toStaticResources().atCommonLocations());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().authorizeRequests()
                // 토큰을 활용하는 경우 모든 요청에 대해 접근이 가능하도록 함
                .anyRequest().permitAll()
                .and()
                // 토큰을 활용하면 세션이 필요 없으므로 STATELESS로 설정하여 Session을 사용하지 않는다.
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                // form 기반의 로그인에 대해 비활성화 한다.
            .formLogin()
                .disable();
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
 

 이번 예제에서는 토큰을 활용하고, 세션을 활용하지 않도록 설정해준다.

 

 

 

[ 1. 로그인 요청 ]
사용자는 로그인 하기 위해 아이디와 비밀번호를 입력해서 로그인 요청을 하게 된다. 이번에 작성하는 예제에서는 로그인 API를 호출하고, Json으로 사용자의 아이디와 비밀번호를 보내는 상황이다.

 

 

[ 2.  UserPasswordAuthenticationToken 발급 ]
전송이 오면 AuthenticationFilter로 요청이 먼저 오게 되고, 아이디와 비밀번호를 기반으로 UserPasswordAuthenticationToken을 발급해주어야 한다. 프론트 단에서 유효성 검사를 하겠지만, 안전을 위해서 다시 한번 아이디와 패스워드의 유효성 검사를 해주는 것이 좋지만 아래의 예제에서는 생략하도록 하겠다.(아이디나 비밀번호의 null 여부 등) 해당 Filter를 직접 구현하면 아래와 같다.

 

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mang.example.security.app.user.model.UserVO;
import com.mang.example.security.exception.InputNotFoundException;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Log4j2
public class CustomAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    public CustomAuthenticationFilter(AuthenticationManager authenticationManager) {
        super.setAuthenticationManager(authenticationManager);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException{
        UsernamePasswordAuthenticationToken authRequest;
        try{
            UserVO userVO = new ObjectMapper().readValue(request.getInputStream(), UserVO.class);
            authRequest = new UsernamePasswordAuthenticationToken(userVO.getUserEmail(), userVO.getUserPw());
        } catch (IOException exception){
            throw new InputNotFoundException();
        }
        setDetails(request, authRequest);
        return this.getAuthenticationManager().authenticate(authRequest);
    }
}
 

 

만약 아이디와 비밀번호가 제대로 전달되지 않았을 경우에는 예외 처리를 해주어야 하므로 InputNotFoundException 클래스를 생성하여 처리한다.

public class InputNotFoundException extends RuntimeException {

    public InputNotFoundException(){
        super();
    }

}
 

 

 

 

이렇게 직접 제작한 Filter를 이제 적용시켜야 하므로 UsernamePasswordAuthenticationFilter 필터 이전에 적용시켜야 한다. 그리고 해당 CustomAuthenticationFilter가 수행된 후에 처리될 Handler 역시 Bean으로 등록하고 CustomAuthenticationFilter의 핸들러로 추가해주어야 하는데, 해당 코드들은 WebSecurityConfig에 아래와 같이 추가해줄 수 있다.

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import com.mang.example.security.app.user.model.UserDetailsVO;
import com.mang.example.security.app.user.model.UserVO;
import com.mang.example.security.utils.TokenUtils;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Log4j2
public class CustomLoginSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    @Autowired
    private TokenUtils tokenUtils;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) {
        UserVO userVO = ((UserDetailsVO)authentication.getPrincipal()).getUserVO();
        String token = tokenUtils.generateJwtToken(userVO);
        response.addHeader("Authorization", "Bearer " + token);
    }

}
CustomLoginSuccessHandler는 AuthenticationProvider를 통해 인증이 성공될 경우 처리되는데, TokenUtils에 대해서는 아래에서 작성하도록 하겠다. 로그인이 성공하면 TokenUtils를 통해 토큰을 생성하고, response에 이를 추가하여 반환한다.

 

 

package com.mang.example.security.config.security;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    // 정적 자원에 대해서는 Security 설정을 적용하지 않음.
    @Override
    public void configure(WebSecurity web) {
        web.ignoring().requestMatchers(PathRequest.toStaticResources().atCommonLocations());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().authorizeRequests()
                // 토큰을 활용하는 경우 모든 요청에 대해 접근이 가능하도록 함
                .anyRequest().permitAll()
                .and()
                // 토큰을 활용하면 세션이 필요 없으므로 STATELESS로 설정하여 Session을 사용하지 않는다.
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                // form 기반의 로그인에 대해 비활성화 한다.
            .formLogin()
                .disable()
            .addFilterBefore(customAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
	}
    
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
    @Bean
    public CustomAuthenticationFilter customAuthenticationFilter() throws Exception {
        CustomAuthenticationFilter customAuthenticationFilter = new CustomAuthenticationFilter(authenticationManager());
        customAuthenticationFilter.setFilterProcessesUrl("/user/login");
        customAuthenticationFilter.setAuthenticationSuccessHandler(customLoginSuccessHandler());
        customAuthenticationFilter.afterPropertiesSet();
        return customAuthenticationFilter;
    }

    @Bean
    public CustomLoginSuccessHandler customLoginSuccessHandler() {
        return new CustomLoginSuccessHandler();
    }
    
}
 CustomAuthenticationFilter를 빈으로 등록하는 과정에서 UserName파라미터와 UserPassword파라미터를 설정할 수 있다. 이러한 과정을 거치면 UsernamePasswordToken이 발급되게 된다.

 

 

[ 3. UsernamePasswordToken을 Authentication Manager에게 전달 ]
AuthenticationFilter는 생성한 UsernamePasswordToken을 AuthenticationManager에게 전달한다. AuthenticationManager은 실제로 인증을 처리할 여러 개의 AuthenticationProvider를 가지고 있다.

 

[ 4. UsernamePasswordToken을 Authentication Provider에게 전달 ]
AuthenticationManager는 전달받은 UsernamePasswordToken을 순차적으로 AuthenticaionProvider들에게 전달하여 실제 인증의 과정을 수행해야 하며, 실제 인증에 대한 부분은 authenticate 함수에 작성을 해주어야 한다. SpringSecurity에서는 Username으로 DB에서 데이터를 조회한 다음에, 비밀번호의 일치 여부를 검사하는 방식으로 작동을 한다. 그렇기 때문에 먼저 UsernamePasswordToken 토큰으로부터 아이디를 조회해야 하고 그 코드는 아래와 같다.

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.annotation.Resource;

@RequiredArgsConstructor
@Log4j2
public class CustomAuthenticationProvider implements AuthenticationProvider {

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        UsernamePasswordAuthenticationToken token = (UsernamePasswordAuthenticationToken) authentication;
        // AuthenticaionFilter에서 생성된 토큰으로부터 아이디와 비밀번호를 조회함
        String userEmail = token.getName();
        
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }

}
 

 

[ 5. UserDetailsService로 조회할 아이디를 전달 ]
AuthenticationProvider에서 아이디를 조회하였으면, UserDetailsService로부터 아이디를 기반으로 데이터를 조회해야 한다. UserDetailsService는 인터페이스이기 때문에 이를 implements한 클래스를 작성해주어야 한다. 실제 반환값을 작성하는 부분은 7번에서 다룬다.

import com.mang.example.security.app.user.model.UserDetailsVO;
import com.mang.example.security.app.user.repository.UserRepository;
import com.mang.example.security.exception.UserNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.Collections;

@AllArgsConstructor
@Service("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {

    private UserRepository userRepository;

    @Override
    public UserDetailsVO loadUserByUsername(String userEmail) {
    
    }
    
}
 

 

[ 6. 아이디를 기반으로 DB에서 데이터 조회 ]
전달받은 아이디를 기반으로 DB에서 조회하는 구현체는 우리가 직접 개발한 UserVO일 것이고, UserDetailsService의 반환값은 UserDetails 인터페이스이기 때문에 이를 implements하여 구현한 UserDetailsVO를 아래와 같이 작성할 수 있다.

@AllArgsConstructor
@Getter
public class UserDetailsVO implements UserDetails {

    @Delegate
    private UserVO userVO;
    private Collection<? extends GrantedAuthority> authorities;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return userVO.getUserPw();
    }

    @Override
    public String getUsername() {
        return userVO.getUserEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return userVO.getIsEnable();
    }

    @Override
    public boolean isAccountNonLocked() {
        return userVO.getIsEnable();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return userVO.getIsEnable();
    }

    @Override
    public boolean isEnabled() {
        return userVO.getIsEnable();
    }
}
 

 

[ 7. 아이디를 기반으로 조회한 결과를 반환 ]
아이디를 기반으로 조회한 결과를 반환하기 위해서는 위에서 작성하던 UserDetailsServiceImpl을 마무리해주어야 하는데, 그 전에 사용자의 아이디를 기반으로 데이터가 조회하지 않았을 경우 처리해주기 위한 Exception 클래스를 추가해주어야 한다.

public class UserNotFoundException extends RuntimeException{

    public UserNotFoundException(String userEmail){
        super(userEmail + " NotFoundException");
    }

}
 

그리고 조회한 결과를 CustomAuthenticaionProvider로 반환하는 UserDetailsServceImpl을 마무리해주면 아래와 같다.

@AllArgsConstructor
@Service("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {

    private UserRepository userRepository;

    @Override
    public UserDetailsVO loadUserByUsername(String userEmail) {
        return userRepository.findByUserEmail(userEmail).map(u -> new UserDetailsVO(u, Collections.singleton(new SimpleGrantedAuthority(u.getRole().getValue())))).orElseThrow(() -> new UserNotFoundException(userEmail));
    }
    
}
위의 예제에서는 UserRepository로부터 조회한 결과를 Optional로 반환하고 있기 때문에 map 함수를 이용해서 새로운 UserDetailsVO 객체로 생성하여 반환하고 있다. (만약 Optional에 대해 잘 모르신다면 여기를 참고해주세요!)

 

 

[ 8. 인증 처리 후 인증된 토큰을 AuthenticationManager에게 반환 ]
이제 CustomAuthenticationProvider에서 UserDetailsService를 통해 조회한 정보와 입력받은 비밀번호가 일치하는지 확인하여, 일치한다면 인증된 토큰을 생성하여 반환해주어야 한다. DB에 저장된 사용자 비밀번호는 암호화가 되어있기 때문에, 입력으로부터 들어온 비밀번호를 PasswordEncoder를 통해 암호화하여 DB에서 조회한 사용자의 비밀번호화 매칭되는지 확인해주어야 한다. 만약 비밀번호가 매칭되지 않는 경우에는 BadCredentialsException을 발생시켜 처리해준다.

@RequiredArgsConstructor
@Log4j2
public class CustomAuthenticationProvider implements AuthenticationProvider {

    @Resource(name = "userDetailsService")
    private UserDetailsService userDetailsService;
    @NonNull
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        UsernamePasswordAuthenticationToken token = (UsernamePasswordAuthenticationToken) authentication;
        // AuthenticaionFilter에서 생성된 토큰으로부터 아이디와 비밀번호를 조회함
        String userEmail = token.getName();
        String userPw = (String) token.getCredentials();
        // UserDetailsService를 통해 DB에서 아이디로 사용자 조회
        UserDetailsVO userDetailsVO = (UserDetailsVO) userDetailsService.loadUserByUsername(userEmail);

        if (!passwordEncoder.matches(userPw, userDetailsVO.getPassword())) {
            throw new BadCredentialsException(userDetailsVO.getUsername() + "Invalid password");
        }

        return new UsernamePasswordAuthenticationToken(userDetailsVO, userPw, userDetailsVO.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }

}
 

위와 같이 완성된 CustomAuthenticaionProvider를 이제 Bean으로 등록해주어야 하는데, 이것을 WebSecurityConfig에 작성하면 아래와 같다.

import com.mang.example.security.handler.CustomLoginSuccessHandler;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    // 정적 자원에 대해서는 Security 설정을 적용하지 않음.
    @Override
    public void configure(WebSecurity web) {
        web.ignoring().requestMatchers(PathRequest.toStaticResources().atCommonLocations());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().authorizeRequests()
                // 토큰을 활용하는 경우 모든 요청에 대해 접근이 가능하도록 함
                .anyRequest().permitAll()
                .and()
                // 토큰을 활용하면 세션이 필요 없으므로 STATELESS로 설정하여 Session을 사용하지 않는다.
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                // form 기반의 로그인에 대해 비활성화 한다.
            .formLogin()
                .disable()
            .addFilterBefore(customAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);    
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public CustomAuthenticationFilter customAuthenticationFilter() throws Exception {
        CustomAuthenticationFilter customAuthenticationFilter = new CustomAuthenticationFilter(authenticationManager());
        customAuthenticationFilter.setFilterProcessesUrl("/user/login");
        customAuthenticationFilter.setAuthenticationSuccessHandler(customLoginSuccessHandler());
        customAuthenticationFilter.afterPropertiesSet();
        return customAuthenticationFilter;
    }

    @Bean
    public CustomLoginSuccessHandler customLoginSuccessHandler() {
        return new CustomLoginSuccessHandler();
    }

    @Bean
    public CustomAuthenticationProvider customAuthenticationProvider() {
        return new CustomAuthenticationProvider(bCryptPasswordEncoder());
    }

    @Override
    public void configure(AuthenticationManagerBuilder authenticationManagerBuilder) {
        authenticationManagerBuilder.authenticationProvider(customAuthenticationProvider());
    }

}
 

 

[ 9. 인증된 토큰을 AuthenticationFilter에게 전달 ]
AuthenticaitonProvider에서 인증이 완료된 UsernamePasswordAuthenticationToken을 AuthenticationFilter로 반환하고, AuthenticationFilter에서는 LoginSuccessHandler로 전달한다.

 

[ 10. 인증된 토큰을 기반으로 JWT 발급 ]
LoginSuccessHandler로 넘어온 요청은 /user/loginSuccess로 redirect된다. 전달받은 Authentication 정보를 활용해 Json Web Token을 생성해주어야 하는데, 토큰과 관련된 요청을 처리하는 TokenUtils를 아래와 같이 만들어줄 수 있다.

import com.mang.example.security.app.user.model.UserVO;
import com.mang.example.security.app.user.service.UserDetailsServiceImpl;
import io.jsonwebtoken.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
@RequiredArgsConstructor
@Log4j2
public class TokenUtils {

    private static final String secretKey = "ThisIsA_SecretKeyForJwtExample";

    @Resource(name = "userDetailsService")
    private UserDetailsService userDetailsService;

    public String generateJwtToken(UserVO userVO) {
        JwtBuilder builder = Jwts.builder()
                .setSubject(userVO.getUserEmail())
                .setHeader(createHeader())
                .setClaims(createClaims(userVO))
                .setExpiration(createExpireDateForOneYear())
                .signWith(SignatureAlgorithm.HS256, createSigningKey());

        return builder.compact();
    }

    public boolean isValidToken(String token) {
        try {
            Claims claims = getClaimsFormToken(token);

            log.info("expireTime :" + claims.getExpiration());
            log.info("name :" + claims.get("name"));
            log.info("Id :" + claims.get("id"));
            return true;

        } catch (ExpiredJwtException exception) {
            log.error("Token Expired");
            return false;
        } catch (JwtException exception) {
            log.error("Token Tampered");
            return false;
        } catch (NullPointerException exception) {
            log.error("Token is null");
            return false;
        }
    }

    public Authentication createAuthenticationFromToken(String token) {
        UserDetails userDetails = ((UserDetailsServiceImpl) userDetailsService).loadUserByUsername(getUserIdFromToken(token));
        // it is rather safe to return Authentication with NULL credentials if you do not require to use user credentials after successful authentication.
        return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
    }

    public String getTokenFromHeader(String header) {
        return header.split(" ")[1];
    }

    private Date createExpireDateForOneYear() {
        // 토큰 만료시간은 30일으로 설정
        Calendar c= Calendar.getInstance();
        c.add(Calendar.DATE, 30);
        return c.getTime();
    }

    private Map<String, Object> createHeader() {
        Map<String, Object> header = new HashMap<>();

        header.put("typ", "JWT");
        header.put("alg", "HS256");
        header.put("regDate", System.currentTimeMillis());

        return header;
    }

    private Map<String, Object> createClaims(UserVO userVO) {
        // 비공개 클레임으로 사용자의 이름과 이메일을 설정, 세션 처럼 정보를 넣고 빼서 쓸 수 있다.
        Map<String, Object> claims = new HashMap<>();

        claims.put("id", userVO.getId());
        claims.put("name", userVO.getUserEmail());

        return claims;
    }

    private Key createSigningKey() {
        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(secretKey);
        return new SecretKeySpec(apiKeySecretBytes, SignatureAlgorithm.HS256.getJcaName());
    }

    private Claims getClaimsFormToken(String token) {
        return Jwts.parser().setSigningKey(DatatypeConverter.parseBase64Binary(secretKey))
                .parseClaimsJws(token).getBody();
    }

    private String getUserIdFromToken(String token) {
        Claims claims = getClaimsFormToken(token);
        return (String) claims.get("id");
    }

}
 

인증이 성공되고 나면 CustomLoginSuccessHandler에서 Token이 생성되게 되고, 생성된 토큰을 반환하게 된다.

 

 

 

2. Spring Security 처리 과정 요약
[ Spring Security 처리 과정 요약 ]

사용자가 아이디 비밀번호로 로그인을 요청함
AuthenticationFilter에서 UsernamePasswordAuthenticationToken을 생성하여 AuthenticaionManager에게 전달
AuthenticaionManager는 등록된 AuthenticaionProvider(들)을 조회하여 인증을 요구함
AuthenticaionProvider는 UserDetailsService를 통해 입력받은 아이디에 대한 사용자 정보를 DB에서 조회함
입력받은 비밀번호를 암호화하여 DB의 비밀번호화 매칭되는 경우 인증이 성공된 UsernameAuthenticationToken을 생성하여 AuthenticaionManager로 반환함
AuthenticaionManager는 UsernameAuthenticaionToken을 AuthenticaionFilter로 전달함
AuthenticationFilter는 전달받은 UsernameAuthenticationToken을 LoginSuccessHandler로 전송하고, 토큰을 response의 헤더에 추가하여 반환함
 

 

3. Spring Security 샘플 코드 및 실행
[ Spring Security 예제 실행 방법 ]
https://github.com/MangKyu/SpringSecurity-Example으로부터 소스를 클론받는다.
CREATE DATABASE security DEFAULT CHARSET UTF8; 으로 데이터베이스를 생성한다.
application.properties에서 DB username과 password를 개인에 맞게 변경해준다.
서버를 실행시킨다.
http://localhost:8081/user/init 로 접속해서 사용자 데이터를 초기화 한다.
https://reqbin.com/ 과 같은 POST 요청 사이트에서 아이디/비밀번호를 JSON 형태로 POST 방식으로 전송한다
http://localhost:8081/user/login으로 접속 요청을 하면 토큰이 생성된다.
