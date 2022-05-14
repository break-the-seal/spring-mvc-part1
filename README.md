# Spring MVC (Part 1)

- Spring Boot `v2.6.7`
- kotlin `v1.6.21`

---

### logging
* log-level
  * trace
  * debug: 개발 서버
  * info: 운영 서버
  * warn
  * error

---
### 요청 매핑
@Controller: 반환 값이 String이면, view를 찾고 랜더링 \
@RestController: 반환 값이 String이면, Http 메세지 body에 바로 입력


