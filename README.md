# Spring MVC (Part 1)

- Spring Boot `v2.6.7`
- kotlin `v1.6.21`

## Spring MVC 기본기능

### :pushpin: HTTP 요청 파라미터 @RequestParam
- Map
  - 하나의 key에 하나의 value
  - `key=value`
- MultiValueMap: 
  - 하나의 key에 여러 value가 들어갈 수 있다.
  - `userIds=a&userIds=b`

### :pushpin: HTTP 요청 메시지
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

### :pushpin: 응답
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

### :pushpin: HTTP message converter
- `@ResponseBody` 동작 원리
  - **HTTP body**에 문자내용 직접 반환
  - `viewResolver` 대신 `HttpMessageConverter` 동작
  - 기본 문자처리: `StringHttpMessageConverter`
  - 기본 객체처리: `MappingJackson2HttpMessageConverter`
  - byte 처리 등의 여러 `HttpMessageConverter`가 기본으로 등록되어 있음
- HTTP request, response 둘다 사용
- `canRead()`, `canWrite()` - `read()`, `write()`
- 클래스타입(`byte[]`, `String`, `Object`, `HashMap`)과 미디어타입(`*/*`, `application/json`)을 고려해서 매핑한다.

### :pushpin: 요청 매핑 핸들러 어댑터 구조
- `RequestMappingHandlerAdapter` 여기서 `HttpMessageConverter`가 적용
- `HandlerMethodArgumentResolver`: 컨트롤러 파라미터를 유연하게 처리할 수 있도록 해준다.
  - Adapter에서 핸들러 파라미터 내용을 호출
  - Controller가 필요로하는 다양한 파라미터 값(객체)을 생성
  - handler 호출시 전달
  - `supportsParameter()` -> `resolveArgument()`
- `HandlerMethodReturnValueHandler`: 컨트롤러의 리턴값을 변환
- `HttpMessageConverter` - `ArgumentResolver`, `ReturnValueHandler`에서 사용

#### 흐름 정리
- request 호출
- `RequestMappingHandlerAdapter` 여기서 handler parameter 내용 호출  
- `HandlerMethodArgumentResolver` 컨트롤러 파라미터를 유연하게 처리  
  - ex) `@RequestBody`, `HttpEntity` parameter를 처리해주는 ArgumentResolver 존재
  - ArgumentResolver에서 파라미터를 처리할 때 `HttpMessageConverter`가 동작
    - `canRead`를 통해 적절한 처리 Converter 선택 후 `read`
- Controller 처리
- `HandlerMethodReturnValueHandler` Controller 반환값을 처리해준다.
  - 여기서도 적절한 `HttpMessageConverter` 선택 후 동작(`canWrite` -> `write`)
- `RequestMappingHandlerAdapter` 반환값을 adapter에 다시 전달, 처리해준다.

#### 확장
- `WebMvcConfigurer`
  - Spring MVC 구조에 있는 인터페이스 내용을 커스터마이징해서 확장하고 싶을 때 사용

### :pushpin: 결론(핵심)
- 스프링 MVC는 FrontController 패턴을 사용 (`DispatcherServlet` 중요한 역할)
- 핸들러 매핑, 핸들러 어댑터 목록 조회, 핸들러 어댑터(handler 호출), viewResolver, View 까지의 프로세스 이해
- `HandlerAdapter`가 중요함
  - 매핑된 핸들러를 호출해주고 반환값을 view나 message body에 넣도록 적용해준다.
  - request시 `ArgumentResolver` 작동 (원하는 파라미터를 가져와준다.)
    - `ArgumentResolver`에서 부가적으로 message body 처리가 필요할 때 `HttpMessageConverter` 사용
  - response시 `ReturnValueHandler` 작동해서 반환값을 처리해준다.
    - 여기서도 부가적으로 `HttpMessageConverter` 필요할 수 있음 (message body 반환시)