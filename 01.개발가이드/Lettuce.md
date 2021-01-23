LettuceClientConfiguration config 
= LettuceClientConfiguration
.builder()
.readFrom(SLAVE_PREFERRED)
.commandTimeOut(Duration.ofMinutes(1))
.shutdownTimeout(Duration.ZERO)
.build();


@Bean
public LettuceConnectionFactory lockLettuceConnectionFactory(
        RedisStandaloneConfiguration lockRedisConfig,GenericObjectPoolConfig lockPoolConfig) {
    LettuceClientConfiguration clientConfig =
            LettucePoolingClientConfiguration.builder().commandTimeout(Duration.ofMillis(5000))
                    .poolConfig(lockPoolConfig).build();
    return new LettuceConnectionFactory(lockRedisConfig, clientConfig);
}
 