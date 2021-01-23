
```yaml 
  naver:
    client:
      id: '6DW_6caICGRmz_G8ljGQ'
      secret: 'JVACHGmdTL'
    auth:
      url: 'https://nid.naver.com/oauth2.0/authorize?response_type=code'
    token:
      protocol: 'https'
      server: 'nid.naver.com'
      uri: '/oauth2.0/token'
    api:
      protocol: 'https'
      server: 'openapi.naver.com'
      uri: '/v1/nid/me'
    callback:
      m: 'http://localhost:8083/login/callback/naver'
      pc: 'http://localhost:8080/login/callback/naver'
  kakao:
    appkey: 'bd51085c2546e6c7c7c22a0dd5c3096f'
    uri: '/v2/user/me'
  apple:
    clientid: 'kr.co.chicor'
    callback:
      m: 'http://localhost:8083/login/callback/apple'
      pc: 'http://localhost:8080/login/callback/apple'
  nice:
    url: 'https://nice.checkplus.co.kr/CheckPlusSafeModel/checkplus.cb'
    site:
      code: 'BQ565'
      password: 'E9XwwwNB44nK'
    callback:
      success:
        m: 'http://localhost:8083/nice/success'
        pc: 'http://localhost:8080/nice/success'
      fail: 
        m: 'http://localhost:8083/nice/fail'
        pc: 'http://localhost:8080/nice/fail'       
account:
  google:
    map:
      apiKey: 'AIzaSyBtQBnIwVph1nwnB90ptl3jeccXKYa9dtw'
      googleMapUrl: 'https://maps.googleapis.com/maps/api/staticmap'
      googleMapMpCrdtUrl: 'https://maps.googleapis.com/maps/api/geocode/json'

```      

#### thymeleaf 

```html 
    <th:block layout:fragment="script">
        <script th:inline="javascript">
        /*<![CDATA[*/
            var _NAVER_AUTH_URL = /*[[ ${@environment.getProperty('external-server.naver.auth.url')} ]]*/
            var _NAVER_CLIENT_ID = /*[[ ${@environment.getProperty('external-server.naver.client.id')} ]]*/
            var _NAVER_CLIENT_SECRET = /*[[ ${@environment.getProperty('external-server.naver.client.secret')} ]]*/
            var _NAVER_CALLBACK = /*[[ ${@environment.getProperty('external-server.naver.callback.m')} ]]*/
            var _KAKAO_APPKEY = /*[[ ${@environment.getProperty('external-server.kakao.appkey')} ]]*/
            var _KAKAO_URI = /*[[ ${@environment.getProperty('external-server.kakao.uri')} ]]*/
            var _CAPTCHA_KEY = /*[[ ${captchaKey} ]]*/;		// 캡차 Key값
        /*]]*/
        </script>
        <script src="https://appleid.cdn-apple.com/appleauth/static/jsapi/appleid/1/en_US/appleid.auth.js"></script>
        <script src="/static/js/member/lgin.js"></script>
    </th:block>
```



#### application.properties 값 조회


<span th:text="${@environment.getProperty('app.title')}"></span>

<div th : if = $ { @environment .acceptsProfiles ( 'production' )}> 
This is the production profile 
</ div> 

또는 

<div th : if = "$ {# arrays.contains (@ environment.getActiveProfiles () 'production')} " > 
This is the production profile 
</ div>


# 시스템 환경변수 조회
${@systemProperties['property.key']}

# 메세지 조회
<span th:text="#{msg.example.title}"></span>

# 세션정보 조회
<span th:text="${session['userId']}"></span> 

<span th:text="${session.userId}"></span> 

# 파라미터 조회
<span th:text="${param.authType}"></span> 

<span th:text="${#httpServletRequest.getParameter('authType')}"></span> 

# PathVariable 조회
<span th:text="__${userId}__"></span>

