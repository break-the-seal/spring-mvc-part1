# Spring MVC (Part 1)

- Spring Boot `v2.6.7`
- kotlin `v1.6.21`

## 웹 애플리케이션의 이해

### 웹서버, 웹 애플리케이션 서버
- 웹서버: 정적 리소스(파일) 제공
- WAS: 동적 리소스 제공, 프로그램 코드 실행을 통해 애플리케이션 로직 수행 가능
- 웹서버 - WAS - DB (각각 역할을 분담에 과부하 부담을 줄임)

### 서블릿
- HTTP 통신을 위한 처리 과정(소켓 연결, HTTP 메시지 파싱, 응답 메시지 생성, 연결 종료 등)을 대신 수행
- 비즈니스 로직에만 집중할 수 있음
- 요청당 Request, Response 객체 생성(WAS) / Servlet 객체를 싱글톤으로 관리 (Servlet Container에서 관리)
- **멀티 쓰레드 처리도 지원 가능**

### 멀티 쓰레드
- Thread
  - **자바 애플리케이션 코드 한 줄씩 순차 실행해주는 주체**
  - 요청마다 쓰레드를 생성해서 각 요청에 대한 로직을 실행
- 요청당 쓰레드 생성
  - 동시 처리 가능
  - but, 생성 비용 문제, Context Switching 비용, 서버의 임계점 over 문제 존재
- **Thread Pool**
  - 미리 생성한 쓰레드들을 풀에 보관하고 관리
  - 톰캣은 기본 200개 설정 (개인 설정 가능)
  - 사용과 반납으로 이루어짐 (모두 사용 중인 경우 추가 요청 거절, 대기 설정 가능)
  - max thread 튜닝이 필요함(nGrinder 사용해서 성능 테스트 수행 가능)

### HTML, HTTP API, CSR, SSR
- HTML: 정적, 동적으로 HTML 파일 생성해서 전달
- HTTP API: 데이터 전달(JSON 형식)
- SSR(Server Side Rendering): 서버에서 최종 HTML 생성해서 전달(Thymeleaf, JSP)
- CSR(Client Side Rendering): JS 사용해서 웹브라우저에서 동적으로 생성(React, Vue)

### 자바 백엔드 웹 기술 역사
- Servlet
- JSP
- Servlet/JSP 조합 MVC 패턴
- 애노테이션 기반 스프링 MVC 등장 -> 스프링 부트
- Spring MVC(Web Servlet) / Spring WebFlux(Web Reactive)
- Java View Template
  - JSP, Freemarker, Velocity, **Thymeleaf**

<br>

## Servlet

### HttpServletRequest
`basic.request.RequestHeaderServlet.kt` 참고
- Servlet은 HTTP 요청 메시지를 파싱해서 편리하게 사용할 수 있게 해준다. (`HttpServletRequest`)
- 임시 저장소 기능: `request.setAttribute(name, value)` / `request.getAttribute(name)`
- 세션 기능: `request.getSession(create: true)`

### HTTP 요청 데이터
3가지 유형을 기억하자
- `GET` Query Parameter: Query String 이용, `/url?username=hello&age=20`
- `POST` HTML Form
  - content-type: `application/x-www-form-urlencoded`
  - `username=hello&age=20` 형태로 message body에 전달
- `POST` message body data 직접 전달: **JSON**, XML, TEXT(POST, PUT, PATCH 에서 사용)

## MVC 프레임워크 만들기

### 프론트 컨트롤러 패턴
- 여러 Controller Servlet에서 공통으로 수행하던 작업들을 FrontController 하나에서 적용
- 모든 요청에 대해 FrontController가 받아들이고 요청 각각에 해당하는 Controller로 분기처리
- Spring MVC의 `DispatcherServlet`

### 도입
- v1: URL 매핑 정보에서 컨트롤러 조회 및 호출
- v2: View 객체 적용, `render()`에서 forward 하는 작업 수행
- v3
  - Servlet 중복 제거: `HttpServletRequest`, `HttpServletResponse` 제거 및 `ModelView` 도입
  - View 이름 중복 제거: `viewResolver` 도입 -> `/WEB-INF/views/new-form.jsp` -> `new-form`
- v4: 실용성 있게 변경
  - `ModelView` 반환 제거: Controller 단에서 기존 ModelView 반환하는 방식에서 `viewName`(String)만 반환하는 것으로 수정
  - model 전달: FrontController에서 Model을 직접 생성하고 Controller에 전달해서 사용
- v5: FrontController가 다양한 방식의 컨트롤러를 처리할 수 있도록 변경(Adapter 패턴)
  - v3, v4 controller 방식 지원
  - adapter 패턴 활용(`supports` -> `handle`)