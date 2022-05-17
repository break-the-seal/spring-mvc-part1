package io.brick.springmvc.basic.response

import io.brick.springmvc.basic.HelloData
import mu.KLogging
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.ResponseStatus
import javax.servlet.http.HttpServletResponse

@Controller
//  @ResponseBody -> 클래스 단위로 설정을 하면, 하위 메서드들은 view를 사용하지 않도록 적용
//      => 이를 한방에 해결해준게 @RestController = @Controller + @ResponseBody
class ResponseBodyController {

    companion object: KLogging()

    @GetMapping("/response-body-string-v1")
    fun responseBodyV1(response: HttpServletResponse) {
        response.writer.write("ok")
    }

    /**
     * HttpEntity, ResponseEntity(Http Status 추가)
     * @return
     */
    @GetMapping("/response-body-string-v2")
    fun responseBodyV2(): ResponseEntity<String> {
        return ResponseEntity("ok", HttpStatus.OK)
    }

    @ResponseBody
    @GetMapping("/response-body-string-v3")
    fun responseBodyV3(): String {
        return "ok"
    }

    // === json ===
    @GetMapping("/response-body-json-v1")
    fun responseBodyJsonV1(): ResponseEntity<HelloData> {
        val helloData = HelloData("userA", 20)
        return ResponseEntity(helloData, HttpStatus.OK)
    }

    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    @GetMapping("/response-body-json-v2")
    fun responseBodyJsonV2(): HelloData {
        return HelloData("userA", 20)
    }
}