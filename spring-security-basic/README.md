# Spring Security Setup

* To disable spring security exclude `SecurityAutoConfiguration.class`
```java
@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
```

* By default, form based authentication is provided
