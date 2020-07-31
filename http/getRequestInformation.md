
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
