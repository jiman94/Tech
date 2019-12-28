### 가이드 
>  Front-End > 캐쉬 설정 

----------------------------------------------------

## [캐쉬] 

```java
<%
//DateUtils.getDbDatetime().substring(0,8);
String jsid = TopasContext.getProperty("topas.deploy.date"); 

String theme = (String)subSession.getAttribute("theme"); // TopasContext.getProperty("ibe.theme");
String headerFlag = (String)subSession.getAttribute("headerFlag");
String menuFlag = (String)subSession.getAttribute("menuFlag");
String footerFlag = (String)subSession.getAttribute("footerFlag");
	
jsMiddlePath = jsPath + "/AIR/" + arrPath[2] + "/" + arrPath[3].substring(0,6) + ".js";
pageJsMiddlePath = jsPath + "/AIR/" + arrPath[2] + "/" + arrPath[3].substring(0,9) + ".js";
jsMiddlePathCore = jsPathCore + "/AIR/" + arrPath[2] + "/" + arrPath[3].substring(0,6) + ".js";
pageJsMiddlePathCore = jsPathCore + "/AIR/" + arrPath[2] + "/" + arrPath[3].substring(0,9) + ".js";
%>
```

```javascript
<script type="text/javascript" src="<%=jsMiddlePathCore +"?"+ jsid %>" defer="defer"></script>
<script type="text/javascript" src="<%=pageJsMiddlePathCore +"?"+ jsid %>" defer="defer"></script>
<script type="text/javascript" src="<%=jsMiddlePath +"?"+ jsid %>" defer="defer"></script>
<script type="text/javascript" src="<%=pageJsMiddlePath +"?"+ jsid %>" defer="defer"></script>
```
