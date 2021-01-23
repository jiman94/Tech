

```java

public class HttpRequestHelper {

    private static final String[] HEADERS= {"X-Forwarded-For", "Proxy-Client-IP", "WL-Proxy-Client-IP", "HTTP_CLIENT_IP", "HTTP_X_FORWARDED_FOR"};

    public static String getClientIpAddr(HttpServletRequest request) {
        for (String header : HEADERS) {
            String ip = request.getHeader(header);
            if (ip != null && ip.length() > 0) {                    
                return StringUtils.commaDelimitedListToStringArray(ip)[0];
            }
        }
        return request.getRemoteAddr();
    }    
}
```


```java
Enumeration params = request.getParameterNames();
while (params.hasMoreElements()) {
    String name = (String) params.nextElement();
    String value = request.getParameter(name);
    logger.debug(name + "=" + value);
}

Cookie cookies[] = request.getCookies();
for(int i = 0; i < cookies.length; i++) {
    String name = cookies[i].getName();
    String value = cookies[i].getValue();
    logger.debug(name + "=" + value);
}


Enumeration<String> attrs = request.getAttributeNames();
while (attrs.hasMoreElements()) {
    String name = (String)attrs.nextElement();
    String value = (String)request.getAttribute(name);
    logger.debug(name + " : " + value);
}


Enumeration<String> headers = request.getHeaderNames();
while (headers.hasMoreElements()) {
    String name = (String) headers.nextElement();
    String value = request.getHeader(name);
    logger.debug(name + "=" + value);
}


Enumeration<String> headerNames = request.getHeaderNames();
while (headerNames.hasMoreElements()) {
    String header = headerNames.nextElement();
    System.out.println("Header  " + header);
    System.out.println("Value  " + request.getHeader(header)));
}

```


```java
    @Get("/status")
    public HttpResponse status() {
        return HttpResponse.ok();
    }
```



```java
private Map<String, String> getRequestInformation(HttpServletRequest request) {
    Map<String, String> map = new HashMap<String, String>();
    Enumeration headerNames = request.getHeaderNames();
    while (headerNames.hasMoreElements()) {
        String key = (String) headerNames.nextElement();
        String value = request.getHeader(key);
        map.put("header: " + key, value);
    }
    Enumeration parameterNames = request.getParameterNames();
    while (parameterNames.hasMoreElements()) {
        String key = (String) parameterNames.nextElement();
        String value = request.getParameter(key);
        map.put("parameter: " + key, value);
    }
    Cookie[] cookies = request.getCookies();
    for (int i = 0; i < cookies.length; i++) {
        Cookie cookie = cookies[i];
        map.put("cookie: " + cookie.getName(), cookie.getValue());

    }
    while (parameterNames.hasMoreElements()) {
        String key = (String) parameterNames.nextElement();
        String value = request.getParameter(key);
        map.put("parameter: " + key, value);
    }
    map.put("getRequestIPAdrress", getRequestIPAdrress(request));
    map.put("getRemoteUser", request.getRemoteUser());
    map.put("getMethod", request.getMethod());
    map.put("getQueryString", request.getQueryString());
    map.put("getAuthType", request.getAuthType());
    map.put("getContextPath", request.getContextPath());
    map.put("getPathInfo", request.getPathInfo());
    map.put("getPathTranslated", request.getPathTranslated());
    map.put("getRequestedSessionId", request.getRequestedSessionId());
    map.put("getRequestURI", request.getRequestURI());
    map.put("getRequestURL", request.getRequestURL().toString());
    map.put("getMethod", request.getMethod());
    map.put("getServletPath", request.getServletPath());
    map.put("getContentType", request.getContentType());
    map.put("getLocalName", request.getLocalName());
    map.put("getProtocol", request.getProtocol());
    map.put("getRemoteAddr", request.getRemoteAddr());
    map.put("getServerName", request.getServerName());
    return map;
}
```


```java

@RequestMapping(method = RequestMethod.POST, value = "/test/{testId}")
public void doSomething(@PathVariable Long testId,
        @RequestParam(value = "someOtherParam", required = false) String someOtherParam,
        HttpServletRequest req)
{
    printRequestInfo(req);
    // ...        
}


private Map<String, String> getRequestInformation(HttpServletRequest request) {
    Map<String, String> map = new HashMap<String, String>();
    Enumeration headerNames = request.getHeaderNames();
    while (headerNames.hasMoreElements()) {
        String key = (String) headerNames.nextElement();
        String value = request.getHeader(key);
        map.put("header: " + key, value);
    }
    Enumeration parameterNames = request.getParameterNames();
    while (parameterNames.hasMoreElements()) {
        String key = (String) parameterNames.nextElement();
        String value = request.getParameter(key);
        map.put("parameter: " + key, value);
    }
    Cookie[] cookies = request.getCookies();
    for (int i = 0; i < cookies.length; i++) {
        Cookie cookie = cookies[i];
        map.put("cookie: " + cookie.getName(), cookie.getValue());

    }
    while (parameterNames.hasMoreElements()) {
        String key = (String) parameterNames.nextElement();
        String value = request.getParameter(key);
        map.put("parameter: " + key, value);
    }
    map.put("getRequestIPAdrress", getRequestIPAdrress(request));
    map.put("getRemoteUser", request.getRemoteUser());
    map.put("getMethod", request.getMethod());
    map.put("getQueryString", request.getQueryString());
    map.put("getAuthType", request.getAuthType());
    map.put("getContextPath", request.getContextPath());
    map.put("getPathInfo", request.getPathInfo());
    map.put("getPathTranslated", request.getPathTranslated());
    map.put("getRequestedSessionId", request.getRequestedSessionId());
    map.put("getRequestURI", request.getRequestURI());
    map.put("getRequestURL", request.getRequestURL().toString());
    map.put("getMethod", request.getMethod());
    map.put("getServletPath", request.getServletPath());
    map.put("getContentType", request.getContentType());
    map.put("getLocalName", request.getLocalName());
    map.put("getProtocol", request.getProtocol());
    map.put("getRemoteAddr", request.getRemoteAddr());
    map.put("getServerName", request.getServerName());
    return map;
}

```
