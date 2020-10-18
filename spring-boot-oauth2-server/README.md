# Getting Started

## Default urls provided by spring security

* [http//localhost:8080/oauth/token](http//localhost:8080/oauth/token)

### Request ```grant_type=client_credentials```
```jshelllanguage
curl --location --request POST 'http://localhost:8080/oauth/token' \
--header 'Authorization: Basic Y2xpZW50MjpzZWNyZXQ=' \
--header 'Content-Type: application/x-www-form-urlencoded' \
--data-urlencode 'grant_type=client_credentials'
```
**Response**
```json
{
    "access_token": "bf082f93-33de-439e-8f91-91be35d6749d",
    "token_type": "bearer",
    "expires_in": 109,
    "scope": "user_info"
}
```


### Request ```grant_type=password```
```jshelllanguage
curl --location --request POST 'http://localhost:8080/oauth/token' \
--header 'Authorization: Basic Y2xpZW50MjpzZWNyZXQ=' \
--header 'Content-Type: application/x-www-form-urlencoded' \
--data-urlencode 'grant_type=password' \
--data-urlencode 'username=admin' \
--data-urlencode 'password=password'
```
**Response**
```json
{
    "access_token": "80e98bef-c872-4850-b822-f67c63452a3e",
    "token_type": "bearer",
    "refresh_token": "20d38355-c34e-4e4e-84b9-290f69f0b4c8",
    "expires_in": 119,
    "scope": "user_info"
}
```

### Check Token
* [http//localhost:8080/oauth/check_token](http//localhost:8080/oauth/check_token)

**Request**
```jshelllanguage
curl --location --request GET 'http://localhost:8080/oauth/check_token?token=80e98bef-c872-4850-b822-f67c63452a3e' \
--header 'Authorization: Basic Y2xpZW50MjpzZWNyZXQ=' \
--header 'Cookie: JSESSIONID=B0218FA4F0F3B3CC8F82CECB1B8815F8'
```

**Response**
```json
{
    "active": true,
    "exp": 1599394428,
    "user_name": "admin",
    "authorities": [
        "ROLE_USER"
    ],
    "client_id": "client2",
    "scope": [
        "user_info"
    ]
}
```

* [http//localhost:8080/oauth/authorize](http//localhost:8080/oauth/authorize)

## Added jwt support

```
keytool -genkey -alias mykeystore -storetype jks -keyalg RSA -keysize 2048 -keystore mykeystore.jks
```

OR

```java
@Configuration
public class JwtSetKeyGeneration {
    @Bean
    public KeyPair keyPairBean() throws NoSuchAlgorithmException {
        KeyPairGenerator gen = KeyPairGenerator.getInstance("RSA");
        gen.initialize(2048);
        return gen.generateKeyPair();
    }
}
```


### Refresh token
**Request**
```jshelllanguage
http://localhost:8080/oauth/token?grant_type=refresh_token&client_id=myclient&client_secret=secret&refresh_token=<token>
```

**Response**
```json
{
    "access_token": "eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJleHAiOjE1OTk1NzUxNjIsInVzZXJfbmFtZSI6ImFkbWluIiwiYXV0aG9yaXRpZXMiOlsiUk9MRV9VU0VSIl0sImp0aSI6ImEwYTA5NmNkLTM0NjgtNGFjYy1iYTg1LTI4OGJiYzdkNjEyMSIsImNsaWVudF9pZCI6Im15Y2xpZW50Iiwic2NvcGUiOlsicmVhZCJdfQ.Ak1pS28umwi3HBnCN2CunzM9TdbN_p2V7zQizg1qEqyVUvPY0swmFvdnvHAdtm4TzI_LyE0OGuv5rk3WYOhuomlKdMzEivSOKJjS0ZfPhxMRAMbW69gQbvAHJr1OWG8aMFo65armty2uNNNTR8Qo-ZjnlFtL5W0u-PrPITRNMbuhSoQmx1wbeJkcp7iC7d7Il05qpwJIBeM3KgO0txBYUFLf0meEktD58-biUU6BjesCBJdxnjOoAZVNRA-SuoFuNXlcUAUkEwax03Kbr3Lh3SvmHTfuJZcPieEiUt6scOBM4bY4n8Gb6_Pfmhu3ELu2rcGQW4yE70YHcHcd9T3ESQ",
    "token_type": "bearer",
    "refresh_token": "eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyX25hbWUiOiJhZG1pbiIsInNjb3BlIjpbInJlYWQiXSwiYXRpIjoiYTBhMDk2Y2QtMzQ2OC00YWNjLWJhODUtMjg4YmJjN2Q2MTIxIiwiZXhwIjoxNTk5NTc1MTQ5LCJhdXRob3JpdGllcyI6WyJST0xFX1VTRVIiXSwianRpIjoiMDgwNjY4MWEtZTViYy00ZTdlLTllNjYtY2YyMzIzZTEwMzE1IiwiY2xpZW50X2lkIjoibXljbGllbnQifQ.YGXXbi0ubJhL6MkZy3Wb2wlMqUxbCx0EhGvQQLeGEMpkX24u56D03EcoHc1khkvOVcX814azHH-Dz9ZIJ6MMBTpWmAt4bh_x7cURxl6L6ROCtlJ6mjItCSgue9op4NfqnNnnydrlxzbmsMBTpxkTsJwM6xCF0-88cz68AQmWsshxN9U4EcVP2SAr0UvUQe-2YIV1x3LuHOc_YkduGz01KnKn8kbvYlHNmZLYeke3mlzdEZ2MKALltPBIyAX-DI_Hxm5xTFX5gv2V4Li_9Q_NiPiHxY0ZVbDG2OZpeEP7KGGnR79p2YdIzDCilCdOcVE_q9cS0ejT9BR6o8eSdAwt2Q",
    "expires_in": 29,
    "scope": "read",
    "jti": "a0a096cd-3468-4acc-ba85-288bbc7d6121"
}
```
