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

* Http 요청 데이터
  * Get 메서드의 쿼리 파라미터와 Post 메서드의 html form 데이터 형식은 동일하기 때문에 request.getParameter로 처리 가능

### 서블릿, JSP, MVC 패턴
* 서블릿: Java 비즈니스 로직 내에서 html 출력
* JSP: Html 내에서 Java 비즈니스 로직 활용
> ﹒비즈니스 로직과 UI는 둘 사이의  변경 사이클이 다름 -> 하나의 코드로 관리하면 유지보수에 좋지 않음 \
> ﹒비즈니스 로직과 화면(view)의 역할에 대한 분리가 필요!!

💡 MVC 패턴 
* 컨트롤러: 비즈니스 로직 실행
* 모델: 뷰에 전달할 데이터를 담음
* 뷰: 응답으로 사용 ex) html, xml ..
---
* 컨트롤러를 통해 view를 보여주고 싶다면 WEB-INF 경로 사용
* dispatch.forward
  * 서버 내부에서의 호출 (servlet, jsp)