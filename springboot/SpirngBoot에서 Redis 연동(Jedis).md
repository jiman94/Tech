SpirngBoot에서 Redis 연동(Jedis)

Gradle
compile('org.springframework.boot:spring-boot-starter-data-redis')
compile group: 'redis.clients', name: 'jedis', version: '2.9.0'

application.properties 설정
spring.cache.type=redis
spring.redis.host = 127.0.0.1 
spring.redis.password= 
spring.redis.port=6379

RedisConfig 클래스 생성


package com.example.service.redis;
 
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
 
import redis.clients.jedis.JedisPoolConfig;
 
@Configuration
@ComponentScan("com.spring.redis")
public class RedisConfig {
 
    private @Value("${spring.redis.host}") String redisHost;
    private @Value("${spring.redis.port}") int redisPort;
    private @Value("${spring.redis.password}") String password;
 
    @Bean
    public JedisPoolConfig jedisPoolConfig() {
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxIdle(30);
        jedisPoolConfig.setMinIdle(10);
        jedisPoolConfig.setTestOnBorrow(true);
        jedisPoolConfig.setTestOnReturn(true);
        return jedisPoolConfig;
    }
 
    @SuppressWarnings("deprecation")
    @Bean
    public JedisConnectionFactory redisConnectionFactory() {
        JedisConnectionFactory jedisConnectionFactory = new JedisConnectionFactory(jedisPoolConfig());
        jedisConnectionFactory.setHostName(redisHost);
        jedisConnectionFactory.setPort(redisPort);
        jedisConnectionFactory.setPassword(password);
        jedisConnectionFactory.setUsePool(true);
        return jedisConnectionFactory;
    }
 
    @Bean
    public RedisTemplate<String, Object> redisTemplate() {
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(redisConnectionFactory());
        template.setKeySerializer(new StringRedisSerializer());
        template.setValueSerializer(new StringRedisSerializer());
        template.setHashKeySerializer(new StringRedisSerializer());
        template.setHashValueSerializer(new StringRedisSerializer());
        template.setEnableDefaultSerializer(false);
        template.setEnableTransactionSupport(true);
        return template;
    }
}

Redis Client에서 가장 많이 사용되는 오픈소스는 Jedis/lettuce/Redisson  중 Jedis이다.
많이 쓴다는 것은 곧 그만큼 사용하기 쉽고 레퍼런스가 많다는 의미이다.
실제로 Jedis를 통해 손쉽게 사용할 수 있었다.
Spring에서 RedisTemplate을 통해 데이터에 엑세스할 뿐 아니라 ConnectionPool의 자원 관리 또한 알아서 해준다.

=> 실제론 JedisConnectionFactory를 통해서 수행

RedisTemplate을 통해 다음과 같은 데이터 타입을 다룰 수 있다.
String

@Resource(name="redisTemplate")
private ValueOperations<String, String> valueOperations;
Set

@Resource(name="redisTemplate")
private SetOperations<String, String> setOperations;
Sorted Set

@Resource(name="redisTemplate")
private ZSetOperations<String, String> zSetOperations;
Hashes

@Resource(name="redisTemplate")
private HashOperations<String, String, String> hashOperations;
List

@Resource(name="redisTemplate")
private ListOperations<String, String> listOperations;