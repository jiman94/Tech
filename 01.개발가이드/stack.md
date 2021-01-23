
### Spring WebClient 사용법
* Spring 5.0 버전부터는 RestTemplate 은 유지 모드로 변경되고 향후 deprecated 될 예정.


### 튜닝 작업
#### 배경
* Spring Boot 2.1.6 기준 Spring의 @Cacheable에서 Reactor Cache 미지원
### 작업
* Reactor Cache 개발
### 요구사항
* Spring에서 Reactive Cache를 지원할 경우를 고려해서 추가 및 제거가 용이해야 한다.
* Spring @Cacheable 인터페이스와 구현이 변경되도 영향을 받지 않게 개발해야 한다.
### 요구사항 구체화
* AOP & Annotation 기반으로 개발
* Spring @Cacheable 관련 코드를 재활용 하지 않는다.
### Github
* Reactor Cache


```java
@Autowired
WebClient.Builder builder;

WebClient webClient = builder.build();
```

역시나 동시성이 적을 때는 Spring Web MVC + RDBC가 좋은 선택이였고,
Spring Webflux + R2DBC는 동시성이 높아져도 안정적인 메모리 사용량을 보여주었다.

 