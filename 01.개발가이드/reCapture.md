
  
Carousel 03PreviousNext
구글 리캡챠(RECAPTCHA) 사용법
촌놈 - 취미로하는개발/기타, 팁 2018. 10. 30. 01:17

 
안녕하세요 촌놈입니다.



웹 사이트를 운영하다보면 보안과 관련된 이슈는 언제나 골치 거리입니다. 머리를 싸매고, 싸매고 보안을 적용 하여도 뚫고 들어와서는 난감하게도 광고를 남기고 가는 그런 공격성향의 매체가 많습니다.



저의 경험으로도 과거 captcha를 적용 해 놓아도 어떻게 뚫고 들어오는지.... 세션을 넣어놔도 들어오고 말이죠....



물론 지금 소개할 구글에서 지원 하는 reCAPTCHA도 보안상에 이슈가 있긴 하지만, 구글이라는 네임 벨류가 너무나도 크기에 충분히 적용을 할만한 가치가 있다고 판단되어 소개를 하게 되었습니다.



운용사양

 - 거의 모든 언어에서 응용이 가능

   (콘솔 프로그램에서도 가능 하다고 판단 됩니다)



▣ 운용절차



1. 구글회원 가입

   (하나의 발급키로도 여러사이트를 운용 하능하지만 운용처의 회원 정보를 취득하여야 함)



2. 사이트키(토큰호출 시사용), 보안키 발급(토큰 확인용 사용)



- 아래 주소로 이동

  https://developers.google.com/recaptcha/



- Get Started 선택

- Choose a Type 선택을 통하여 제공하고자 하는 스타일을 먼저 검토 하여야 합니다.

- reCAPTCHA v2(행위기반), reCAPTCHA v3(화면 로딩시 확인), Invisible reCAPTCHA(반응형)의 스타일이 있으며 보통

  행위기반으로 적용을 많이 합니다. (안드로이드 버전도 있네요 ^^)



각각의 버전별로 영문으로 어떻게 적용 하는지 안내 하고 있습니다, 처음에는 이게뭐야 이겠지만 한번 적용 해보시게 되면 모든게 이해가 됩니다.



https://developers.google.com/recaptcha/docs/v3  - reCAPTCHA v3

https://developers.google.com/recaptcha/docs/display  - reCAPTCHA v2

https://developers.google.com/recaptcha/docs/invisible  - Invisible reCAPTCHA



각각의 페이지에 가시면 HTML 코드가 있으며 선처리, 후처리로 나누게 되면 후처리는 어떻게 파라미터를 던지는지 받는 데이터가 무엇인지에 대한 것입니다.



- 사이트 키 발급

  https://www.google.com/recaptcha/admin#list - 주소로 이동(각각의 설명 페이지에도 here 등의 문구로 링크 존재)





[위와 같이 필수 값을 넣고 등록을 진행]





저의 경우 현재까지 reCAPTCHA v2 기반만을 실제 적용 하였기에 이를 기반으로 하는 JSP 예제를 아래와 같이 진행 하시면 됩니다.

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"
%><%@ page import="java.net.*, java.io.*"
%><%

    // 생성된 토큰 받음
    String g_recaptcha_response = request.getParameter("g-recaptcha-response");
    System.out.println(g_recaptcha_response);
    
    // 토큰과 보안키를 가지고 성공 여부를 확인 함
    HttpURLConnection conn = (HttpURLConnection) new URL("https://www.google.com/recaptcha/api/siteverify").openConnection();
    String params = "secret=보안키입력" + "&response=" + g_recaptcha_response;
    conn.setRequestMethod("POST");
    conn.setDoOutput(true);
    DataOutputStream wr = new DataOutputStream(conn.getOutputStream());
    wr.writeBytes(params);
    wr.flush();
    wr.close();
    
    // 결과코드 확인(200 : 성공)
    int responseCode = conn.getResponseCode();
    StringBuffer responseBody = new StringBuffer();
    if (responseCode == 200) {
        
        // 데이터 추출
        BufferedInputStream bis = new BufferedInputStream(conn.getInputStream());
        BufferedReader reader = new BufferedReader(new InputStreamReader(bis));
        String line;
        while ((line = reader.readLine()) != null) {
            responseBody.append(line);
        }
        bis.close();
        
        // JSON으로 변환 하여야 하지만 기본 모듈에서 처리하기위하여 아래와 같이 진행 합니다
        if(responseBody.toString().indexOf("\"success\": true") > -1)
            out.println("인증 되었습니다...");
    }
    
%>
<html>
<head>
<script src="https://www.google.com/recaptcha/api.js?onload=onloadCallback&render=explicit" async defer></script>
<title>google, reCAPTCHA - reCAPTCHA2 Type</title>
<script type="text/javascript">
    var correctCaptcha = function(response) {
        console.log();
    };
    var onloadCallback = function() {
        grecaptcha.render('html_element', {
            'sitekey' : '사이트키입력',
            'callback' : correctCaptcha
        });
    };
</script>
</head>
<body>
    <form action="" method="get">
        <div id="html_element"></div>
        <input type="submit" value="Submit">
    </form>
    <!-- JSON String 찍어보기 -->
    <%=responseBody != null ? responseBody.toString() : ""%>
</body>
</html>


1. 구글회원 가입
   (하나의 발급키로도 여러사이트를 운용 하능하지만 운용처의 회원 정보를 취득하여야 함)
2. 사이트키(토큰호출 시사용), 보안키 발급(토큰 확인용 사용)
- 아래 주소로 이동
  https://developers.google.com/recaptcha/
- Get Started 선택
- Choose a Type 선택을 통하여 제공하고자 하는 스타일을 먼저 검토 하여야 합니다.
- reCAPTCHA v2(행위기반), reCAPTCHA v3(화면 로딩시 확인), Invisible reCAPTCHA(반응형)의 스타일이 있으며 보통
  행위기반으로 적용을 많이 합니다. (안드로이드 버전도 있네요 ^^)
각각의 버전별로 영문으로 어떻게 적용 하는지 안내 하고 있습니다, 처음에는 이게뭐야 이겠지만 한번 적용 해보시게 되면 모든게 이해가 됩니다.
https://developers.google.com/recaptcha/docs/v3  - reCAPTCHA v3
https://developers.google.com/recaptcha/docs/display  - reCAPTCHA v2
https://developers.google.com/recaptcha/docs/invisible  - Invisible reCAPTCHA
각각의 페이지에 가시면 HTML 코드가 있으며 선처리, 후처리로 나누게 되면 후처리는 어떻게 파라미터를 던지는지 받는 데이터가 무엇인지에 대한 것입니다.
- 사이트 키 발급
  https://www.google.com/recaptcha/admin#list - 주소로 이동(각각의 설명 페이지에도 here 등의 문구로 링크 존재