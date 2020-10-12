# Spring WebFlux와 Armeria를 이용해 고성능 Microservice 구현 하기 
* spring webflux와 armeria 살펴보기 (Mono, Flux, gRPC, Thrift)

1. speing webflux 란?
* 적은 수의 스레드로 동시성을 처리하기 위함
* 서블릿 스펙 3.1에서 논블로킹IO API를 지원하긴 하는데, 자꾸 동기(Filter, Servlet)나 블로킹(getParameter, getPart)에서 걸린다
* spring 5.0 (boot 2.0) 부터 사용 가능
* 기본 컨테이너가 tomcat -> netty (서블릿 스펙 3.1이상인 tomcat을 쓸 수는 있음)
* Reactive stream의 구현체
* 비동기 / 논블로킹 스트림 처리를 위한 스펙명세
 
1-2. Mono와 Flux
* mono : 0 or 1 
* flux : 0 or 1 or many ( 순서보장이 안됨  )

2. Armeria (아르메리아) 란 ? 
* Java 8 및 Netty, gRPC, Thrift에 기반한 오픈소스 비동기 HTTP/2 RPC/REST 클라이언트/서버라이브러리입니다. 
* 고성능 비동기 마이크로서비스를 손쉽게 제작할 수 있음
* 단일 어플리케이션에서 단일 포트로 HTTP, gRPC, Thrift를 동시에 지원

2-1. 동작원리
* Armeria가 Spring WebFlux의 서버구현체를 대체 : ArmeriaReactiveWebServerFactory.java
* ArmeriaWebServer 에 요청이 들어오면 분배
* HTTP요청으로 들어오면 Spring WebFlux 로
* gRPC요청이 들어오면 gRPC서비스로
* Thrift요청이 들어오면 Thrift서비스로
* Armeria가 네트워크 레벨을 핸들링하고 Spring은 Bean Container, DB Transaction 확보 용도로 공존
* Spring에서 지원하지 않는 gRPC, Thrift등의 서빙을 Armeria가 관리

2-2. gRPC 란?
* RPC는 원격 프로시저 요청 (Remote Procedure Call)의 약자로 gRPC는 Google에서 개발
* 통신 프로토콜 : HTTP/2
* 표현언어 : 프로토콜 버퍼
* xml 이나 json은 사람이 읽을 수 있게 직렬화 하지만, 프로토콜 버퍼는 이진바이너리로 직렬화 => 매우 빨라짐

2-2-1. 특징
* 로드밸런싱, 인증, 양방향 스트리밍 및 흐름 제어, 바인딩 차단 또는 차단 해제 및 취소 및 시간 초과 기능
* 클라이언트에서 서버의 API호출을 메소드처럼 직접 할 수 있음 (MSA에서 강점인 듯?)
* gRPC 클라이언트는 서버의 메소드의 stub을 작성
* gRPC 서버는 인터페이스 제공

2-3. Thrift 란?
* 다양한 언어를 지원하는 RPC 프레임워크, facebook 에서 개발
* 데이타 타입 및 서비스 인터페이스를 간단한 정의 파일안에 제공하며, 컴파일러는 다른 언어간에 통신할 수 있는 RPC 클라이언트/서버의 코드를 생성

2-3-1. 특징
* Sync, Async Server API 제공
* 서블릿 제공(org.apache.thrift.server.TServlet)
* 멀티쓰레드 지원 (org.apache.thrift.server.ThreadPoolServer : worker thread 지정)