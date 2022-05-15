# Spring MVC (Part 1)

- Spring Boot `v2.6.7`
- kotlin `v1.6.21`

## Spring MVC 기본기능

### HTTP 요청 파라미터 @RequestParam
- Map
  - 하나의 key에 하나의 value
  - `key=value`
- MultiValueMap: 
  - 하나의 key에 여러 value가 들어갈 수 있다.
  - `userIds=a&userIds=b`

### HTTP 요청 메시지
- 요청 파라미터를 조회하는 기능: `@RequestParam`, `@ModelAttribute`
  - `x-www-form-urlencoded`도 포함(form 데이터)
- HTTP 메시지 바디를 직접 조회하는 기능: `@RequestBody`
- `@ResponseBody`: 메시지바디에 응답결과를 직접 담아서 전달 가능

#### 단순텍스트, JSON 요청 받기
- `request.inputStream` (stream -> string / json)
- `InputStream`, `Writer` 인자로 받기
- `HttpEntity`를 통한 메시지 바디에 직접 접근(`HttpEntity<Type>`)
- `@RequestBody`를 통한 직접 binding
- 여기서 `HttpMessageConverter`가 사용
  - JSON은 `MappingJackson2HttpMessageConverter` 적용

### 응답
- 정적 리소스
  - `/static`, `/public`, `/resources`, `/META-INF/resources`
  - `/src/main/resources`: 리소스 보관 장소, classpath 시작 경로
  - (`/src/main/resources/static`)
- 뷰 템플릿
  - `/src/main/resources/templates`
  - 타임리프: ThymeleafViewResolver
```properties
# thymeleaf plugin 등록시 자동등록
spring.thymeleaf.prefix=classpath:/templates/
spring.thymeleaf.suffix=.html
```
