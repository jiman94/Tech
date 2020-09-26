# 동일 출처 정책 (SOP, Same-Origin Policy)

### 1. 동일 출처 정책 (SOP, Same-Origin Policy)

```javascript 
Uncaught DOMException: Blocked a frame with origin 
"https://air-dev.naeiltour.co.kr" from accessing a cross-origin frame.
```
### 2. 서브도메인만 다른 경우
* 사이트간 정보 교환은 처리 방법 

> document.domain
### 3.크롬 확장 프로그램 추가 
* disable-web-security


## 조치 가이드 

### 1. apache httpd.conf 
```xml 
<IfModule mod_headers.c>
Header set Access-Control-Allow-Origin "*"
</IfModule>
```
### 2. ibeAIRDynamic 수정 
```xml 
<filter>
    <filter-name>cors</filter-name>
    <filter-class>topas.security.cors.SimpleCORSFilter</filter-class>   
</filter>

<filter-mapping>
    <filter-name>cors</filter-name>
    <url-pattern>/*</url-pattern> 
</filter-mapping>

```

### 3. 도메인 설정 ( 내일투어 )
```javascript 
<script type="text/javascript">
	document.domain="naeiltour.co.kr"; // 개발, 운영
</script>
```




