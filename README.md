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

❕비즈니스 로직과 UI는 둘 사이의  변경 사이클이 다름 -> 하나의 코드로 관리하면 유지보수에 좋지 않음 \
❕비즈니스 로직과 화면(view)의 역할에 대한 분리가 필요!!

💡 MVC 패턴
> ﹒컨트롤러: 비즈니스 로직 실행 \
> ﹒모델: 뷰에 전달할 데이터를 담음 \
> ﹒뷰: 응답으로 사용 ex) html, xml ..
* dispatch.forward
  * 서버 내부에서의 호출 (servlet, jsp)


MVC 패턴의 한계
> ﹒포워드 중복 \
> ﹒viewPath 중복 \
> ﹒사용하지 않는 코드 전달 (response) \
> ﹒공통 처리가 많아짐 

-> 컨트롤러 호출 전에 먼저 공통 기능을 처리 (***프론트 컨트롤러 패턴***)  
-> 입구를 하나로

---
### MVC 프레임워크 만들기
Front-Controller 패턴
* 서블릿 형태로 클라이언트의 요청을 받음
* 요청에 맞는 컨트롤러를 찾아서 호출
* 입구를 하나 -> 공통되는 부분 처리
* 프론트 컨트롤러를 제외한 나머지 컨트롤러는 서블릿을 사용하지 않아도 됨
* 스프링 MVC의 *Dispatcher Servlet*이 Front-Controller 패턴으로 구현되어있음

예제 내용
* v1: front-controller 도입
* v2: view 분리
* v3: model 추가
* v4: 컨트롤러에 model도 함께 전달
* v5: 어댑터 패턴 적용
