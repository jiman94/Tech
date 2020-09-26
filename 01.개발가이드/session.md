DEVICE_INFO

```java
	/*****************************************************
	 * 세션 정보 저장
	 * @param serveClsCd
	 * @return
	 *****************************************************/
	public static void setLoginSession(Map loginInfo) {
		ServletRequestAttributes	attr	= (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
		HttpSession					session = attr.getRequest().getSession();

		session.setAttribute(sessionKey + getServerClsCd(), loginInfo);
	}
```


	RequestContextUtils.setLoginSession(sessionMap);

# member api 

```java
	/*****************************************************
	 * 통합회원로그인
	 * @param paramLgin
	 * @return
	 * @throws Exception
	 *****************************************************/
	@PostMapping(value = "/intgMbsh/intgMbshLgin")
	@ApiOperation(value = "통합회원로그인", response = IntgMbsh.class)
	public ResponseEntity<ResponseDto> intgMbshLgin(@RequestBody MbshLgin paramLgin, HttpServletRequest req, HttpServletResponse res) {
		InfcLog infcLog = new InfcLog();
		infcLog.setExecDt(DateUtil.getCurrentDate("", DATE_FORM[0]));	//실행일시

		// JwtToken 조회
		// 자동로그인용 토큰이 생성되어있는지 확인 후
		IntgMbsh intgMbsh = new IntgMbsh();	// 로그인 정보

		String chicorToken = req.getHeader(Const.Agent.MOBILE_CHICOR_TOKEN);

		String mbrLginId = StringUtil.nvl(paramLgin.getIntgLginId());

		String mbrLginPwd = paramLgin.getIntgMbshPwd();
		if (mbrLginPwd != null) mbrLginPwd = mbrLginPwd.replaceAll(" ", "");

		String autoLginYn = StringUtil.nvl(paramLgin.getAutoLginYn());
		String captchaAnswer = StringUtil.nvl(paramLgin.getCaptchaAnswer());
		String captchaResponse = StringUtil.nvl(paramLgin.getCaptchaResponse());

		log.error("========================================================");
		log.error("========================================================");
		log.error("mbrLginId ====> "+mbrLginId+", "+mbrLginPwd+", "+autoLginYn+", "+captchaAnswer+", "+captchaResponse);
		log.error("chicorToken ====> "+(chicorToken != null && "Y".contentEquals(autoLginYn)));
		log.error("========================================================");
		log.error("========================================================");
		// 자동로그인 토큰값이 있고 자동로그인 여부가 Y인 경우 로그인 아이디 추출
		if(StringUtils.isNotEmpty(chicorToken) && !"null".equals(chicorToken) && "Y".contentEquals(autoLginYn)) {
			// 토큰 유효기간 체크
			if(!jwtTokenUtil.expiredToken(chicorToken)) {
				return new ResponseEntity<>(
						ResponseDto.builder()
								.data(null)
								.resultMessage(resultMessage.getArgumentsMessage(Const.Message.LOGIN_MESSAGE_EXPIRED_TOKEN, null))
								.build(),
								HttpStatus.OK);
			}

			mbrLginId = StringUtil.nvl(jwtTokenUtil.getCI(chicorToken).get("custId"));
			mbrLginPwd = StringUtil.nvl(jwtTokenUtil.getCI(chicorToken).get("custPw"));

			// 체크 할 문자배열
			String[] arrSrcCode = {"&lt;", "&gt;", "&#34;", "&#39;", "null;", "&#37;", "&#59;", "&#45;"};
			// 체크 할 Tag 문자배열
			String[] arrTgtCode = {"<", ">", "\"", "\'", "%00", "%", ";", "-"};

			for (int nLoop=0; nLoop < arrSrcCode.length; nLoop++) {
				mbrLginPwd = mbrLginPwd.replaceAll(arrSrcCode[nLoop], arrTgtCode[nLoop]);
			}
		}

		// 로그인 API 서비스 호출
		LoginRequestDto loginRequestDto = new LoginRequestDto();

		if(!"prod".contentEquals(System.getProperty("spring.profiles.active"))) {
			// 운영이 아닌경우 dev로 접근하기 위해 생성(임시)
			loginApiService = new LoginApiService();
			memberApiService = new MemberApiService();
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

	/**************************************************************
	 * JWT Token 생성
	 *
	 * @param custId 아이디
	 * @param custPw 비밀번호
	 * @return String
	 **************************************************************/
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

	/**************************************************************
	 * token 유효기간 체크
	 *
	 * @param jwtToken jwt token 문자
	 * @return boolean
	 **************************************************************/
	public boolean expiredToken(String jwtToken) {
		try {
			Jws<Claims> claims = Jwts.parser().setSigningKey(this.signatureKey).parseClaimsJws(jwtToken);
			return claims.getBody().getExpiration().after(new Date());
		} catch (ExpiredJwtException e) {
			return false;
		}
	}

	/**************************************************************
	 * jwt token 에서 ci(id & pw) 얻기
	 *
	 * @param jwtToken jwt token 문자
	 * @return Map<String, String>
	 **************************************************************/
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

```java
package com.chicor.frontend.mobile.common;
RestTemplateUtil


   public HttpHeaders httpHeader() {
        HttpHeaders headers = new HttpHeaders();

        // member 서버 client ip 유실로 인한 예외처리
        String clientIp = "";
        if (ContextUtil.getRequest().getHeader("x-forwarded-for") != null) {
            clientIp = ContextUtil.getRequest().getHeader("x-forwarded-for").split(",")[0];
        }

        headers.add("x-forwarded-host", ContextUtil.getRequest().getHeader(HttpHeaders.HOST));
        headers.add("CLIENT-IP", clientIp);
        headers.add("user-agent", ContextUtil.getRequest().getHeader(HttpHeaders.USER_AGENT));
        headers.add("chicor_token", String.valueOf(ContextUtil.getRequest().getAttribute("chicor_token")));
        headers.add("Cookie", "SESSION=" + ContextUtil.getCookie("SESSION"));
        headers.add("CHICOR_DEVICE_INFO", (String)ContextUtil.getSession().getAttribute("DEVICE-INFO"));
        ContextUtil.getSession().removeAttribute("DEVICE-INFO");

        return headers;
    }
```
    
```java

package com.chicor.frontend.mobile.common;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static com.chicor.frontend.mobile.common.CommonUtil.loginCheck;

@Component
@RequiredArgsConstructor
@Slf4j
public class PreCheckInterceptor implements HandlerInterceptor {

    @Value("${server.servlet.session.timeout}")
    private int timeout;
    @Value("${spring.profiles.active}")
    private String profiles;

    /**************************************************************
     *  전처리 handle override
     *
     * @param request 요청정보
     * @param response 응답정보
     * @param handler handler
     * @return boolean 체크여부
     * @throws IOException exception
     **************************************************************/
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        HttpSession session = ContextUtil.getSession();

        // 세션 timeout 설정
        session.setMaxInactiveInterval(this.timeout);

        try {
            if (session.isNew() && request.getRequestURI().equals("/login/auto")) {
                String token = request.getQueryString();

                // IOS 는 header 에 token 을 전달
                if (token == null || "".equals(token)) {
                    token = "token=" + ContextUtil.getRequest().getHeader("token");
                }

                response.sendRedirect("/login/auto?" + token);
                return false;
            }

            // 비회원 주문조회 허용
            if ("Y".equals(session.getAttribute("frontGMbshLogin")) &&
                    (request.getRequestURI().startsWith("/mypage/order") ||
                            request.getRequestURI().startsWith("/mypage/claim") ||
                            	request.getRequestURI().startsWith("/mypage/delivery/change")) &&
                    !request.getRequestURI().equals("/mypage/order/guest")) {
                return true;
            }

            // 로그인 체크
            if (loginCheckUri(request.getRequestURI()) && !loginCheck()) {
                response.setHeader("CHICOR_DEVICE_INFO", ContextUtil.getRequest().getHeader("CHICOR_DEVICE_INFO"));
                response.sendRedirect(request.getContextPath() + "/login");
                return false;
            }
        } catch (IOException e) {
            log.info("===== Interceptor Login Check Exception");
        }

        return true;
    }

    /**************************************************************
     * 요청 URI 에 대한 로그인 체크 여부
     *
     * @param uri 요청 uri
     * @return boolean
     **************************************************************/
    private boolean loginCheckUri(String uri) {
        return (uri.startsWith("/mypage") && !uri.contains("guest")) ||
                uri.startsWith("/cs/onetoone") ||
                uri.startsWith("/withdrawal");
    }

    /**************************************************************
     * 후처리 handle override
     *
     * @param request 요청정보
     * @param response 응답정보
     * @param handler handler
     * @param modelAndView model
     **************************************************************/
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                            @Nullable ModelAndView modelAndView) {
        String sessionInfo = "; Path=/; HttpOnly; SameSite=None";
        if ("prod".equals(this.profiles)) {
            sessionInfo = "; Path=/; HttpOnly; Secure; SameSite=None";
        }

        if (ContextUtil.getSession().getAttribute("PRE_SESSION") != null &&
                !String.valueOf(ContextUtil.getSession().getAttribute("PRE_SESSION")).isEmpty()) {
            ContextUtil.getResponse().setHeader(HttpHeaders.SET_COOKIE,
                    "SESSION=" + ContextUtil.getSession().getAttribute("PRE_SESSION") + sessionInfo);
            ContextUtil.getSession().removeAttribute("PRE_SESSION");
        } else if (ContextUtil.getCookie("SESSION") != null && !ContextUtil.getCookie("SESSION").isEmpty()) {
            ContextUtil.getResponse().setHeader(HttpHeaders.SET_COOKIE,
                    "SESSION=" + ContextUtil.getCookie("SESSION") + sessionInfo);
        }
    }

}



	/**************************************************************
	 * 앱 자동로그인
	 *
	 * @param token 자동로그인 토큰
	 **************************************************************/
	@GetMapping("/login/auto")
	public ResponseEntity<Map<String, String>> autoLogin(
			@RequestParam(required = false, defaultValue = "") String token) {
		// IOS 는 token 를 header 로 전달
		if ("".equals(token)) {
			token = ContextUtil.getRequest().getHeader("token");
		}
		ContextUtil.getRequest().setAttribute("chicor_token", token);

		// 앱 디바이스정보
		ContextUtil.getSession().setAttribute("DEVICE-INFO",
				ContextUtil.getRequest().getHeader("CHICOR_DEVICE_INFO"));

		MbshLginReqDto mbshLginReqDto = new MbshLginReqDto();
		mbshLginReqDto.setAloginYn("Y");
		mbshLginReqDto.setAutoLginYn("Y");

		MbshLginResDto mbshLginResDto = this.lginService.intgLgin(mbshLginReqDto);

		this.lginService.setMbshInfoSession(mbshLginResDto);

		Map<String, String> loginInfo = new HashMap<String, String>() {
			{
				put("token", ContextUtil.getResponse().getHeader("chicor_token"));
				put("userName", mbshLginResDto.getMbshFulnm());
				put("userType", (mbshLginResDto.getEmpNo() == null) ? "N" : "Y");
			}
		};

		return ResponseEntity.ok(loginInfo);
	}


```
    
```java


response.setHeader("CHICOR_DEVICE_INFO", ContextUtil.getRequest().getHeader("CHICOR_DEVICE_INFO"));



ContextUtil.getResponse().setHeader("chicor_token", String.valueOf(headers.get("chicor_token")));

```


```java
        String agent = ContextUtil.getRequest().getHeader("user-agent");
        if (agent.contains("iPhone") || agent.contains("Android")) {
            model.addAttribute("IS_APP", "Y");
        } else {
            model.addAttribute("IS_APP", "N");
        }
```

```java
compile group: 'eu.bitwalker', name: 'UserAgentUtils', version: '1.21'
UserAgent userAgent = UserAgent.parseUserAgentString(request.getHeader("User-Agent"));
System.out.println(request.getHeader("User-Agent").toString());
System.out.println(userAgent.getBrowser().toString());
System.out.println(userAgent.getOperatingSystem().toString());

compile group: 'org.springframework.mobile', name: 'spring-mobile-device', version: '1.1.5.RELEASE'
```


```java

@Controller
public class HomeController {
    @RequestMapping(value = { "/" }, method = { org.springframework.web.bind.annotation.RequestMethod.GET })
    public String dashboard(Locale locale, Model model, HttpServletRequest req, Device device) throws IOException {
       
        if (device.isMobile()) {
            logger.info("Hello mobile user!");
        } else if (device.isTablet()) {
            logger.info("Hello tablet user!");
        } else {
            logger.info("Hello desktop user!");         
        }
        
        return "home";
    }
}
``


