

 o.s.s.c.bcrypt.BCryptPasswordEncoder     : Encoded password does not look like BCrypt

```
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {

	@Autowired
	PasswordEncoder encoder;
	
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
    	 
    	String password = encoder.encode("foo");
    	
        return new User("foo", password,
                new ArrayList<>());
    }
}
```


```java

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

@RestController
@RequestMapping(value = "/rest/auth")
@Slf4j 
public class JwtTokenController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private TokenProvider jwtTokenUtil;
	
	@Autowired
	private UserService userService;

	@PostMapping("/login")
	public ResponseEntity<AuthDetail> register(@RequestBody UserVo userVo, HttpServletRequest req)
			throws AuthenticationException {
		
		try {
			UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userVo.getUserid(), userVo.getUserpw());

			authToken.setDetails(new WebAuthenticationDetails(req));
			Authentication authentication = authenticationManager.authenticate(authToken);
			
			ResponseEntity<AuthDetail> loginResult = null;
			
			JwtUser jwtUser = new JwtUser();
			
			jwtUser.setUserid(userVo.getUserid());
			
			JwtUser jwtUserData = jwtService.getUserInfo(jwtUser);
			jwtUserData.setLoginOk(true);
			
			Date expireDate = DateUtils.getAddHours(new Date(), loginDuration);
			Date expireTimestamp = new Timestamp(expireDate.getTime());
			jwtUser.setExpireDateTime(DateUtils.getTimsStampString(expireTimestamp));

			SecurityContextHolder.getContext().setAuthentication(authentication);

			long iat = System.currentTimeMillis();

			AuthDetail authDetail = jwtTokenUtil.generateToken(authentication, jwtUserData);

			authDetail.setServerAccessTime(String.valueOf(iat));
			loginResult = new ResponseEntity<>(authDetail, HttpStatus.OK);
			
			return loginResult == null ? new ResponseEntity<>(HttpStatus.NOT_FOUND) : loginResult;

		} catch (BadCredentialsException e) {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		} catch (Exception e) {
			return null;
		}

	}
```

#### SecurityUser
```java
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class SecurityUser {

    private String username;
    private String password;
    private Set<Role> roles;
}
```


#### UserDetailsService

```java
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service(value = "jwtUserService")
public class JwtUserService implements UserDetailsService {

	@Autowired
	private JwtMapper jwtMapper;

	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		SecurityUser securityUser = new SecurityUser();
		securityUser.setUsername(username);
		SecurityUser user = jwtMapper.getUser(securityUser);
		user.setRoles(jwtMapper.getRole(username));
		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), getAuthority(user));
	}

    public List<UrlRole> GetUrlRoles(List<String> roles) {
        return jwtMapper.getUrlRoles(roles);
    }

	private Set<SimpleGrantedAuthority> getAuthority(SecurityUser securityUser) {
		Set<SimpleGrantedAuthority> authorities = new HashSet<>();
		securityUser.getRoles().forEach(role -> {
			// data에 role을 붙여서 관리중이라 role + 를 빼먹음
			authorities.add(new SimpleGrantedAuthority(role.getId()));
		});
		return authorities;
	}

}
```

#### WebSecurityConfig

```java
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	DataSource dataSource;

	@Resource(name = "jwtUserService")
	private UserDetailsService userDetailsService;

	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	UsernamePasswordAuthenticationToken authentication = jwtTokenUtil.getAuthentication(authToken,
								SecurityContextHolder.getContext().getAuthentication(), username);
						
    authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(req));		

    SecurityContextHolder.getContext().setAuthentication(authentication);



	UsernamePasswordAuthenticationToken getAuthentication(final String token, final Authentication existingAuth,
			final String username) {

		final JwtParser jwtParser = Jwts.parser().setSigningKey(jwtSecret);

		final Jws<Claims> claimsJws = jwtParser.parseClaimsJws(token);

		final Claims claims = claimsJws.getBody();

		final Collection<? extends GrantedAuthority> authorities = Arrays
				.stream(claims.get(jwtAuthKey).toString().split(",")).map(SimpleGrantedAuthority::new)
				.collect(Collectors.toList());

		return new UsernamePasswordAuthenticationToken(username, "", authorities);
	}
```