### Redis
- Redis는 In-memory 기반의 NoSQL DBMS로서 Key-Value의 구조를 가지고있다. 
- 또한 속도가 빠르고 사용이 간편하다. 캐싱,세션관리,pub/sub 메시징 처리등에 사용된다.

#### Spring에서 Redis를 사용하기위한 라이브러리는 2가지가있다.

- jedis
- lettuce

* jedis는 thread-safe하지 않기 때문에 jedis-pool을 사용해야한다. 
* 그러나 비용이 증가하기 때문에 lettuce를 많이 사용한다.

```yaml 
 <dependency>
     <groupId>org.springframework.boot</groupId>
     <artifactId>spring-boot-starter-data-redis</artifactId>
 </dependency>
```

```java

@Configuration
public class RedisConfiguration {
    
    @Bean
    public RedisConnectionFactory redisConnectionFactory() {
        LettuceConnectionFactory lettuceConnectionFactory = new LettuceConnectionFactory();
        return lettuceConnectionFactory;
    }
    @Bean
    public RedisTemplate<String, Object> redisTemplate() {
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(redisConnectionFactory());
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        return redisTemplate;
    }
 
}
```

```yaml 
 spring.redis.lettuce.pool.max-active=10
 spring.redis.lettuce.pool.max-idle=10
 spring.redis.lettuce.pool.min-idle=2
 spring.redis.port=6379
 spring.redis.host=127.0.0.1
 ```

## Redis 사용 예제
### GetSetService.java

```java
@Service
public class GetSetService {
    
    @Autowired
    RedisTemplate<String, Object> redisTemplate;
    
    public void test() {
        //hashmap같은 key value 구조
        ValueOperations<String, Object> vop = redisTemplate.opsForValue();
        vop.set("jdkSerial", "jdk");
        String result = (String) vop.get("jdkSerial");
        System.out.println(result);//jdk
    }
}
```

```java
@Configuration
public class RedisConfiguration {

    @Bean
    public RedisConnectionFactory redisConnectionFactory() {
        LettuceConnectionFactory lettuceConnectionFactory = new LettuceConnectionFactory();
        return lettuceConnectionFactory;
    }

    @Bean
    public RedisTemplate<String, Object> redisTemplate() {
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(redisConnectionFactory());
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(new Jackson2JsonRedisSerializer<>(MyData.class));
        return redisTemplate;
    }

}
```

### Mydata.java

```java

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class MyData implements Serializable {
    private static final long serialVersionUID = -7353484588260422449L;
    private String studentId;
    private String name;
}
```

### DataService.java

```java
@Service
public class DataService {
    @Autowired
    RedisTemplate<String, Object> redisTemplate;

    public void test() {
        ValueOperations<String, Object> valueOperations = redisTemplate.opsForValue();
        MyData data = new MyData();
        data.setStudentId("1234566");
        data.setName("HongGilDong");
        valueOperations.set("key", data);

        MyData data2 = (MyData) valueOperations.get("key");
        System.out.println(data2);
    }
}
```


## Redis Publish/Subscribe
### RedisConfiguration.java

```java

@Configuration
public class RedisConfiguration {

    @Bean
    public RedisConnectionFactory redisConnectionFactory() {
        LettuceConnectionFactory lettuceConnectionFactory = new LettuceConnectionFactory();
        return lettuceConnectionFactory;
    }

    @Bean
    public RedisTemplate<String, Object> redisTemplate() {
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(redisConnectionFactory());
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(new Jackson2JsonRedisSerializer<>(MyData.class));
        return redisTemplate;
    }

    @Bean
    MessageListenerAdapter messageListenerAdapter() {
        return new MessageListenerAdapter(new RedisService());
    }

    @Bean
    RedisMessageListenerContainer redisContainer() {
        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        container.setConnectionFactory(redisConnectionFactory());
        container.addMessageListener(messageListenerAdapter(), topic());
        return container;
    }

    @Bean
    ChannelTopic topic() {
        return new ChannelTopic("Event");
    }
}
```
# RedisService.java
```java
@Service
public class RedisService implements MessageListener {
    public static List<String> messageList = new ArrayList<String>();

    @Override
    public void onMessage(Message message, byte[] pattern) {
        messageList.add(message.toString());
        System.out.println("Message received: " + message.toString());
    }
}
```

