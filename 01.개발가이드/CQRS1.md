# CQRS-MSA
* Redis 

* Member
* Product 
* Order 

```java
//REDIS KEY
public static final String EVENTROW_Z_MEMBER = "EVENTROW_Z_MEMBER";
public static final String EVENTROW_Z_ORDER = "EVENTROW_Z_MEMBER";
public static final String EVENTROW_Z_PRODUCT = "EVENTROW_Z_MEMBER";
public static final String USER_H_KEY = "USER_H_KEY";
```


redisCmd.hput(InfraConstants.USER_H_KEY, id, id);

