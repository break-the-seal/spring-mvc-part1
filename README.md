# Spring MVC (Part 1)

- Spring Boot `v2.6.7`
- kotlin `v1.6.21`

---

### 웹 애플리케이션 이해 

* 서블릿을 통해 Http Request와 Response에 대해 고민할 필요 없게 해줌
* 기본적으로 멀티 쓰레드를 제공함으로써 동시 요청에 대한 개발자의 고민도 없애줌
* SSR: 서버단에서 html 랜더링
* CSR: 웹 브라우저에서 동적으로 생성

### 서블릿(Servlet)
* http 요청 메세지를 기반으로 request, response 객체 생성 후 서블릿 컨테이너로 전달
* HttpServletRequest
  * *"임시 저장소 기능"* : http 요청 시작부터 끝날때까지 유지 ex) setAttribute, getAttribute
  * *"세션 관리 기능"*

