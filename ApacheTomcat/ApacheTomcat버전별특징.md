# 톰캣 버전 

|서블릿 스펙	|JSP 사양|	EL Spec|	WebSocket 사양|	재 스픽 사양|	Apache Tomcat 버전|	최신 출시 버전|	지원되는 Java 버전|
| :--------:| :--------:| :--------:| :--------:| :--------:| :--------:| :--------:| :--------:| 
|4.0|	2.3|	3.0|	1.1|	1.1|	9.0.x|	9.0.14|	8 이상|
|3.1|	2.3|	3.0|	1.1|	1.1|	8.5.x|	8.5.37|	7 이상|
|3.1|	2.3|	3.0|	1.1|	N / A|	8.0.x (대체 됨)|	8.0.53 (대체 됨)	|7 이상|
|3.0|	2.2|	2.2|	1.1|	N / A|	7.0.x|	7.0.92|	6 이상 (WebSocket의 경우 7 이상)|
|2.5|	2.1|	2.1|	N / A|	N / A|	6.0.x (보관 처리됨)|	6.0.53 (보관 처리됨)|	5 이상|
|2.4|	2.0|	N / A|	N / A|	N / A|	5.5.x (보관 처리됨)|	5.5.36 (보관 처리됨)|	1.4 이상|
|2.3|	1.2|	N / A|	N / A|	N / A|	4.1.x (보관 처리됨)|	4.1.40 (보관 됨)|	1.3 이상|
|2.2|	1.1|	N / A|	N / A|	N / A|	3.3.x (보관 처리됨)|	3.3.2 (보관)	|1.1 이상|

#### Apache Tomcat 9.x
* Apache Tomcat 9.x 는 가장 최신형 버전이며 아직 안정적인 단계는 아닙니다. Tomcat 8.0.x 및 8.5.x를 기반으로하며 Servlet 4.0 , JSP 2.3 , EL 3.0 , WebSocket 1.1 및 JASPIC 1.1 사양 (Java EE 8 플랫폼에 필요한 버전)을 구현합니다. 여기에는 다음과 같은 중요한 개선 사항이 포함됩니다.

* HTTP / 2에 대한 지원 추가 (Java 9 (Apache Tomcat 9.0.0.M18 이후) 또는 Tomcat 네이티브 라이브러리가 설치되어 있어야 함)
* JSSE 커넥터 (NIO 및 NIO2)와 함께 TLS 지원을위한 OpenSSL 사용에 대한 지원을 추가합니다.
* SNI (TLS 가상 호스팅)에 대한 지원을 추가합니다.

#### Apache Tomcat 8.x
* Apache Tomcat 8.0.x 는 Tomcat 7.0.x를 기반으로하며 Servlet 3.1 , JSP 2.3 , EL 3.0 및 WebSocket 1.1 사양을 구현합니다. 여기에는 다음과 같은 중요한 개선 사항이 포함됩니다.

* 이전 버전에서 제공된 여러 리소스 확장 기능을 대체하기위한 단일 공통 리소스 구현입니다.
Apache Tomcat 8.5.x 는 Apache Tomcat 8.0.x와 동일한 Servlet, JSP, EL 및 WebSocket 사양 버전을 지원합니다. 이 외에도 JASPIC 1.1 스펙을 구현한다 .

* 그것은 Tomcat 9.0.0.M4 (알파) 마일스톤 릴리스에서 포크로 2016 년 3 월에 만들어졌습니다. Tomcat 9.x 코드베이스의 HTTP / 2 지원 및 기타 기능을 제공하며 Tomcat 8.0 런타임 및 사양 요구 사항과 호환됩니다. (Tomcat 9가 타겟으로하는 Java EE 사양이 겨우 몇 년 후 완성 되었기 때문에, Tomcat 9.0의 안정적인 릴리스는 그 당시 생성 될 수 없었습니다).

* Tomcat 8.5는 Tomcat 8.0을 대신하는 것으로 생각됩니다. Tomcat 8.5로 마이그레이션하는 방법에 대한 지침은 마이그레이션 가이드 를 참조하십시오 .

* Apache Tomcat 8.5.x의 주요 개선 내용은 다음과 같습니다.

> HTTP / 2에 대한 지원을 추가합니다 ( Tomcat 네이티브 라이브러리 필요 ).

> JSSE 커넥터 (NIO 및 NIO2)와 함께 TLS 지원을위한 OpenSSL 사용에 대한 지원을 추가합니다.

> SNI (TLS 가상 호스팅)에 대한 지원을 추가합니다.

다음 기술은 Apache Tomcat 8.5.x에서 제거되었습니다.

> HTTP 및 AJP 커넥터의 BIO 구현

> Comet API 지원

후드 아래 많은 부분에서 중요한 변화가있어 성능, 안정성 및 총 소유 비용이 향상됩니다. 자세한 내용은 Apache Tomcat 8.5 Changelog를 참조하십시오.

Tomcat 8.0의 사용자는 Tomcat 8.0 의 수명이 다한 것을 알고 있어야합니다 . Tomcat 8.0.x 사용자는 Tomcat 8.5.x 이상으로 업그레이드해야합니다.

#### Apache Tomcat 7.x
* Apache Tomcat 7.x 는 Tomcat 6.0.x의 향상된 기능을 기반으로하며 Servlet 3.0 , JSP 2.2 , EL 2.2 및 WebSocket 1.1 사양을 구현합니다. 그 외에도 다음과 같은 개선 사항이 포함됩니다.

> 웹 응용 프로그램 메모리 누수 감지 및 예방

> Manager 및 Host Manager 응용 프로그램의 향상된 보안

> 일반적인 CSRF 보호

> 웹 애플리케이션에 직접 외부 컨텐츠를 포함 할 수 있도록 지원

> 리팩토링 (커넥터, 라이프 사이클) 및 많은 내부 코드 정리


# web.xml 파일

#### tomcat9 기본값
```xml 
<?xml version="1.0" encoding="UTF-8"?>
<web-app xmlns="http://xmlns.jcp.org/xml/ns/javaee"
  xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
  xsi:schemaLocation="http://xmlns.jcp.org/xml/ns/javaee
                      http://xmlns.jcp.org/xml/ns/javaee/web-app_4_0.xsd"
  version="4.0"
  metadata-complete="true">

</web-app>

```

#### tomcat8 기본값
```xml 
<?xml version="1.0" encoding="UTF-8"?>
<web-app xmlns="http://xmlns.jcp.org/xml/ns/javaee"
  xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
  xsi:schemaLocation="http://xmlns.jcp.org/xml/ns/javaee
                      http://xmlns.jcp.org/xml/ns/javaee/web-app_3_1.xsd"
  version="3.1"
  metadata-complete="true">

</web-app>

```

#### tomcat7 기본값
```xml 
<?xml version="1.0" encoding="ISO-8859-1"?>
<web-app xmlns="http://java.sun.com/xml/ns/javaee"
  xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
  xsi:schemaLocation="http://java.sun.com/xml/ns/javaee
                      http://java.sun.com/xml/ns/javaee/web-app_3_0.xsd"
  version="3.0"
  metadata-complete="true">

</web-app>

```

#### tomcat6 기본값
```xml 
<?xml version="1.0" encoding="ISO-8859-1"?>
<web-app xmlns="http://java.sun.com/xml/ns/javaee"
   xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
   xsi:schemaLocation="http://java.sun.com/xml/ns/javaee http://java.sun.com/xml/ns/javaee/web-app_2_5.xsd"
   version="2.5">

</web-app>
```

