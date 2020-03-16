대규모 서비스 개발시에 가장 기본적으로 하는 튜닝은 바로 데이터베이스에서 Write와 Read DB를 Replication(리플리케이션)하고 쓰기 작업은 Master(Write)로 보내고 읽기 작업은 Slave(Read)로 보내어 부하를 분산 시키는 것이다.
특히 대부분의 서비스는 읽기가 압도적으로 많기 때문에 Slave는 여러 대를 두어 읽기 부하를 분산 시킨다.
Replication은 비록 짧더라도 시차를 두고 이루어 지는 것이다.
정합성이 굉장히 중요한 데이터는 비록 읽기 작업이라 하더라도 Slave에서 읽지 않고 Master에서 읽어야만 하는 경우도 있다.

- Java의 JDBC 커넥션 객체에는 Connection.setReadOnly(true|false) 라는 메소드가 존재한다.
- Spring Framework을 사용하여 트랜잭션을 관리하면 @Transactional(readOnly=true|false)를 통해 현재 트랜잭션의 readOnly 상태를 설정할 수 있으며, 이 Spring의 트랜잭션 설정은 연쇄적으로 커넥션 객체의 setReadOnly메소드를 호출하기도 한다.

1안) Spring LazyConnectionDataSourceProxy + AbstractRoutingDataSource

TransactionManager 선별 -> DataSource에서 Connection 획득 -> Transaction 동기화(Synchronization)

public class ReplicationRoutingDataSource extends AbstractRoutingDataSource {

    @Override
    protected Object determineCurrentLookupKey() {
        String dataSourceType =
            TransactionSynchronizationManager.isCurrentTransactionReadOnly() ? "read" : "write";
        return dataSourceType;
    }
}

@Bean public DataSource writeDataSource() { return 쓰기 DataSource; }

@Bean public DataSource readDataSource() { return 읽기 DataSource; }

@Bean
public DataSource routingDataSource(DataSource writeDataSource, DataSource readDataSource) {
    ReplicationRoutingDataSource routingDataSource = new ReplicationRoutingDataSource();

    Map<Object, Object> dataSourceMap = new HashMap<Object, Object>();
    dataSourceMap.put("write", writeDataSource);
    dataSourceMap.put("read", readDataSource);
    routingDataSource.setTargetDataSources(dataSourceMap);
    routingDataSource.setDefaultTargetDataSource(writeDataSource);

    return routingDataSource;
}

@Bean
public DataSource dataSource(DataSource routingDataSource) {
    return new LazyConnectionDataSourceProxy(routingDataSource);
}

2안) 

가장 쉽게 생각나는 방법은 커넥션 풀(Connection Pool, DataSource)을 master와 slave용으로 따로 만들고 쿼리를 만들 때 비록 같은 쿼리라도 서로 다른 DS를 바라보는 두 벌의 쿼리를 만들어주는 방식이다.
즉, 프로그래머가 계속해서 두 개의 데이터 소스를 인지해가며 코드를 작성하는 방식이다.

Connection의 readOnly는 기본 설정값이 false이다.

@Transactional(readOnly = true)
public User findByIdRead(Integer id) {
    return userRepository.findById(id);
}

@Transactional(readOnly = false)
public User findByIdWrite(Integer id) {
    return userRepository.findById(id);
}


3안) MySQL Replication JDBC Driver 사용하기

Configuring Master/Slave Replication with Connector/J
Configuring Load Balancing with Connector/J

jdbc:mysql:replication://[master host][:port],[slave host 1][:port][,[slave host 2][:port]]...[/[database]] »
[?propertyName1=propertyValue1[&propertyName2=propertyValue2]...]

jdbc:mysql:loadbalance://[host1][:port],[host2][:port][,[host3][:port]]...[/[database]] »
[?propertyName1=propertyValue1[&propertyName2=propertyValue2]...]


MySQL에는 Replication JDBC 드라이버가 존재한다. 이를 이용하면 Connection.setReadOnly(true|false) 호출만으로 Master/Slave 분기 처리가 된다.


4안 LazyReplicationConnectionDataSourceProxy - Spring을 사용하지 않을 경우 

Connection.setReadOnly(true|false)를 호출하여 사용하면 된다.
Connection connection = dataSource.getConnection();
connection.setReadOnly(false);
// 쓰기 DB관련 작업
connection.close();

// 절대 앞서 획득한 커넥션을 재사용하지 말 것

Connection connection = dataSource.getConnection();
connection.setReadOnly(true);
// 읽기 DB관련 작업
connection.close();
