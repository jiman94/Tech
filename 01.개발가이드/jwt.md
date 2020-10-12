
### build.gradle

```xml 
	// jwt
	compile 'io.jsonwebtoken:jjwt:0.9.1'
```


#### Spring Security
1. JWT
2. 주요 구조
3. AuthenticationManager, AuthenticationProvider 동작 흐름 분석
4. Authentication, SecurityContextHolder의 이해
5. Spring Security Filter 적용
6. 인가(Authorization)


```java
@Service
public class TokenProvider {
    @Autowired
    private JwtProperties jwtProperties;
    
    public String createToken(Account account) {
        SecretKey key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtProperties.getSecretKey()));

        Date exp = new Date((new Date()).getTime() + jwtProperties.getExpirationSec());
        String jwt = Jwts.builder()
                .setSubject(String.valueOf(account.getId()))
                .setAudience(account.getEmail())
                .setExpiration(exp)
                .signWith(key)
                .compact();

        return jwt;
    }

    public Long getAccountIdFromToken(String token) {
        SecretKey key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtProperties.getSecretKey()));

        Jws<Claims> jws = Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token);

        return Long.parseLong(jws.getBody().getSubject());
    }

    public boolean verifyToken(String token) throws RuntimeException {
        SecretKey key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtProperties.getSecretKey()));
        Jwts.parserBuilder().setSigningKey(key).build().parse(token);
        return true;
    }
}
```


```java
package com.chicor.api.member.common.util;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.chicor.api.member.common.CubeOneUtil;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;


@Component
@RequiredArgsConstructor
public class JwtTokenUtil {

	private final CubeOneUtil cubeOneUtil;

	/** 로그인 토큰 만료 일자 */
	@Value("${login.token.expire.day}")
	private String tokenExpireDay;

	private String secretKey = "CHICOR.jwt.secret.key";
	private byte[] apiKeySecretByte = DatatypeConverter.parseBase64Binary(secretKey);
	private SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
	private Key signatureKey = new SecretKeySpec(apiKeySecretByte, signatureAlgorithm.getJcaName());;
	private long tokenExpireTime = 1000L * 60 * 60 * 24; // 30일 유효


	public String createToken(String custId, String custPw) {
		Date now = new Date();

		Map<String, Object> header = new HashMap<String, Object>() {
			{
				put("typ","JWT");
				put("alg","HS256");
			}
		};

		String ci = this.cubeOneUtil.encrypt(custId + "_" + custPw, "ADDRESS");

		Map<String, Object> claim = new HashMap<String, Object>() {
			{
				put("ci", ci);
			}
		};

		JwtBuilder builder = Jwts.builder()
				.setHeader(header)
				.setClaims(claim)
				.setExpiration(new Date(now.getTime() + this.tokenExpireTime * Integer.parseInt(tokenExpireDay)))
				.signWith(this.signatureAlgorithm, this.signatureKey);

		return builder.compact();
	}

	public boolean expiredToken(String jwtToken) {
		try {
			Jws<Claims> claims = Jwts.parser().setSigningKey(this.signatureKey).parseClaimsJws(jwtToken);
			return claims.getBody().getExpiration().after(new Date());
		} catch (ExpiredJwtException e) {
			return false;
		}
	}

	public Map<String, String> getCI(String jwtToken) {
		Jws<Claims> claims = Jwts.parser().setSigningKey(this.signatureKey).parseClaimsJws(jwtToken);

		String ci = this.cubeOneUtil.decrypt(String.valueOf(claims.getBody().get("ci")), "ADDRESS");
		String[] cis = (ci == null) ? new String[] {} : ci.split("_");

		return new HashMap<String, String>() {
			{
				if (cis.length == 2) {
					put("custId", ci.split("_")[0]);
					put("custPw", ci.split("_")[1]);
				}
			}
		};
	}

}
```


#### 권한체크
```java
Authentication user = SecurityContextHolder.getContext().getAuthentication();

```



#### 브라우저 쿠키 저장 
```js
function loginCustom_ajax(){
    var datas = objToJson($("form[name=loginCustom]").serializeArray());

        $.ajax({
            type : 'POST',
            url : 'http://localhost:8082/loginCustom',
            data : JSON.stringify(datas),
            dataType : 'text',  //위와 같이 dataType:json 일 경우 json 형태로 맞춰서 데이터를 받지 못해 오류 발생
            contentType : "application/json",
            error: function(xhr, status, error){
                alert(error);
            },
            success : function(token){
                console.log(token);
                var expireDay = 24 * 60 * 60 * 1000; //1일
                document.cookie = "X-AUTH-TOKEN=" + token + expireDay +"; path=/";

            },
        });
    }
```