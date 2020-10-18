# Getting Started

### Dependencies needed
```xml
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-oauth2</artifactId>
</dependency>
```

** Consume resource **
Get token from resource server
```jshelllanguage
curl -X POST \
  http://localhost:8080/oauth/token \
  -H 'Postman-Token: 08ae5c8a-7a0a-49ad-8a7e-568ce136e73b' \
  -H 'cache-control: no-cache' \
  -H 'content-type: multipart/form-data; boundary=----WebKitFormBoundary7MA4YWxkTrZu0gW' \
  -F grant_type=password \
  -F username=admin \
  -F password=password
```
**Response**

```json
{
    "access_token": "eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJleHAiOjE1OTk1NTEyMTcsInVzZXJfbmFtZSI6ImFkbWluIiwiYXV0aG9yaXRpZXMiOlsiUk9MRV9VU0VSIl0sImp0aSI6IkpUNVBPNU1aVTZoWUdEeXVDR0lzMDBkMkFYTSIsImNsaWVudF9pZCI6Im15Y2xpZW50Iiwic2NvcGUiOlsicmVhZCJdfQ.WWPRcv1fhKLEiVk0CuaISCg1vSwQOfc5eCnNXaUox5sxGNf1PB5rMYaKhd1QiSRrTuCzY1dBvJtREKTi3QB7dm1uwSYzKucfog45g3B8Zo8LcCtt7IfrfmzKYSewfYgAFcahjUqk8LwYjOQy29i1NeEj_3-s5k2NsYhF2sNFXMb7c_OduHP_h6OiZeff3CZ-uQXSOQF73pc7f0yLuuu0j7zGviRMzZkJc2tXv7oy-f0sJM4m9SQ3LYoioHl0pQ8e5mcvv1ZB6OIufLHkviarfA659wRNzA7bPre1UW9KCuLe-4B4rRRUh4CJkcIuUEGYC0PckQHAxiZaZ9yAPX2OEg",
    "token_type": "bearer",
    "refresh_token": "eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyX25hbWUiOiJhZG1pbiIsInNjb3BlIjpbInJlYWQiXSwiYXRpIjoiSlQ1UE81TVpVNmhZR0R5dUNHSXMwMGQyQVhNIiwiZXhwIjoxNTk5NTUxMjQ3LCJhdXRob3JpdGllcyI6WyJST0xFX1VTRVIiXSwianRpIjoiYjhoVkVUY3AyZVZnQVBsRkQ3U1FhY25COUhZIiwiY2xpZW50X2lkIjoibXljbGllbnQifQ.AB6I_XQ9TnzrqxuSn4miysMQMNCtKKSTYHesW0S6l9J0pcvbVDD7viCj6ATPQW_wmhD-GlMAtkR0gO66g6Xy7TJMOEWXBIrqAei-IOrpfSXt_E2BoOn1_mlFQRcCrrkStZB8R1m-H_g7m5N4lt4EtkHuB__V-1Ao4z1xkmwel2bnr47aVEwKrG6-V5iRP-RvHito-dTmcOeoPaHekn2neIQQnCtUUQPG9-p9yyR9dXOtCSuHEsOLPYOZuaZbaJsegdJADUhe7wSlrpcoG9-1x-cOZA-nwZg8bXdTDlut8iTyidQIXc9dwcmeNbmnhDDSH5yXTjjrspZey2EWNWgZSg",
    "expires_in": 29,
    "scope": "read",
    "jti": "JT5PO5MZU6hYGDyuCGIs00d2AXM"
}
```


```jshelllanguage
curl -X GET \
  'http://localhost:8082/welcome?access_token=eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJleHAiOjE1OTk1NTEyMTcsInVzZXJfbmFtZSI6ImFkbWluIiwiYXV0aG9yaXRpZXMiOlsiUk9MRV9VU0VSIl0sImp0aSI6IkpUNVBPNU1aVTZoWUdEeXVDR0lzMDBkMkFYTSIsImNsaWVudF9pZCI6Im15Y2xpZW50Iiwic2NvcGUiOlsicmVhZCJdfQ.WWPRcv1fhKLEiVk0CuaISCg1vSwQOfc5eCnNXaUox5sxGNf1PB5rMYaKhd1QiSRrTuCzY1dBvJtREKTi3QB7dm1uwSYzKucfog45g3B8Zo8LcCtt7IfrfmzKYSewfYgAFcahjUqk8LwYjOQy29i1NeEj_3-s5k2NsYhF2sNFXMb7c_OduHP_h6OiZeff3CZ-uQXSOQF73pc7f0yLuuu0j7zGviRMzZkJc2tXv7oy-f0sJM4m9SQ3LYoioHl0pQ8e5mcvv1ZB6OIufLHkviarfA659wRNzA7bPre1UW9KCuLe-4B4rRRUh4CJkcIuUEGYC0PckQHAxiZaZ9yAPX2OEg' \
  -H 'Postman-Token: d25fc22b-d9ba-4182-b5ae-732d84c8dbd8' \
  -H 'cache-control: no-cache'
```

OR
```jshelllanguage
http://localhost:8082/welcome?access_token=<token>
```