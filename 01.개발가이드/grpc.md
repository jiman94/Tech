8.3. Event Souring 구조

모든 액션을 이벤트로 정의하고, 이벤트 스트림을 별도 Database에 저장한다. 따라서 여기서는 Insert 작업만 발생한다. 왜냐면 변경이나 삭제도 별도 이벤트이기 때문이다.

트랜잭션 처리의 경합이 없고 모든 이벤트가 저장되어 있기 때문에 rollback이 가능하다. 또 이벤트 드리븐 아키텍처에 적합하다.

다만 상태 불일치 발생 가능성이 있고 일관성 있는 데이터 뷰가 필요한 시스템, 실시간성 시스템에 적합하지 않다.



11. gRPC

gRPC가 무엇인지는 설명은 생략한다.

조금만 알려준다면 구글 내부에서 마이크로 서비스간 연계 시 사용하던 프레임워크를 오픈한 것이다.
ProtoBuf의 IDL로 정의하면 고성능을 보장하는 서비스와 메시지에 대한 소스 코드가 생성된다.
압축률이 좋아 네트워크 트래픽을 절약할 수 있다.
gRPC가 MSA에 적합한 이유는 다양한 언어와 플랫폼을 지원하는 Poloyglot이 가능하기 때문이다.

protoBuf가 지원하는 IDL를 활용한 메시지 정의는 다양한 기술 스택의 공존에 의한 중복 발생의 단점을 보완하고 많은 서비스간의 API 호출에 의한 성능 저하를 개선한다.

다만 REST API 위주의 시스템에는 적합치 않다. gRPC는 RPC이기 때문이다.




gRPC가 HRJ(HTTP REST JSON)보다 나은점
binary protocol(Protocol Buffer 3)
text보다 더 적은 데이터 공간으로 처리 가능 -> 네트워크, 메모리 효율성 좋음
text보다 marshal/unmarshal 부하가 적으므로 CPU 더 적게 사용 -> CPU 효율성 좋음
HTTP/2
http://http2.golang.org/gophertiles
connection multiplexing
여러 자원 요청을 하나의 커넥션으로 처리 가능 -> 네트워크 효율성 좋음
client/server streaming 가능
websocket과의 비교
네트워크, 메모리, CPU 효율성 좋음
https://www.youtube.com/watch?v=BOW7jd136Ok
처리량: 7:25
처리량CPU: 7:35
클라우드 환경에서의 서버 간 대규모 통신에 적합
자원이 빈약한 모바일(또는 IoT까지?)에 적합
바이트 수, 호출 수, CPU 수 등으로 과금되는 클라우드 환경에서 비용 절감에 도움


# grpc-spring-boot-starter
https://github.com/LogNet/grpc-spring-boot-starter

# 비교 
gRPC vs JSON RPC


https://developers.google.com/protocol-buffers/



https://github.com/mincloud1501/spring-cloud-workshop


https://spring.io/blog/2015/03/22/using-google-protocol-buffers-with-spring-mvc-based-rest-services

```java

        @Bean
        RestTemplate restTemplate(ProtobufHttpMessageConverter hmc) {
            return new RestTemplate(Arrays.asList(hmc));
        }

        @Bean
        ProtobufHttpMessageConverter protobufHttpMessageConverter() {
            return new ProtobufHttpMessageConverter();
        }
```       