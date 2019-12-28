# yaml 사용 가이드 @Value

#### application.yml 

```java
spring:
  profiles:
    active: local    
logging: 
  config: classpath:logback/logback-${spring.profiles.active}.xml
---
spring:
  mail:
    default-encoding: UTF-8
    username: ryu.jiman@gmail.com 
    password: 비밀번호 
    host: smtp.gmail.com
    port: 587
    protocol: smtp
    properties:
      mail.smtp.starttls.enable: true
      mail.smtp.auth: true
```

```java
import lombok.extern.slf4j.Slf4j;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@Slf4j
public class UserServiceTest {

	@Autowired
	UserService userService;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Value("${spring.mail.username}")
	String username;
		
	@Value("${spring.mail.password}")
	String rawPassword;
	
		    
	@Test
	public void register() {
		
		log.debug("username="+username);
		log.debug("rawPassword="+rawPassword);
		
	    User user = userService.register(username, rawPassword);
		assertThat(passwordEncoder.matches(rawPassword, user.getPassword())).isTrue();
	}

}
```