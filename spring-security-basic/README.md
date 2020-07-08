# Spring Security Setup

* To disable spring security exclude `SecurityAutoConfiguration.class`
```java
@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
```

* By default, form based authentication is provided

## HttpBasic
* When using this no login form and logout form available.
```java
@Override
protected void configure(HttpSecurity http) throws Exception {
    http.
            authorizeRequests()
            .anyRequest()
            .authenticated()
            .and()
            .httpBasic();
}
```
