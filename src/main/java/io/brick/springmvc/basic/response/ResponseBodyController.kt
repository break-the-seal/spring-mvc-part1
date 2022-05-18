package io.brick.springmvc.basic.response

import io.brick.springmvc.basic.HelloData
import mu.KLogging
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import javax.servlet.http.HttpServletResponse

//@Controller
@RestController
class ResponseBodyController {
    companion object: KLogging()

    @GetMapping("/response-body-string-v1")
    fun responseBodyV1(response: HttpServletResponse) {
        response.writer.write("ok")
    }

    @GetMapping("/response-body-string-v2")
    fun responseBodyV2(): ResponseEntity<String> {
        return ResponseEntity("ok", HttpStatus.OK)
    }

    @GetMapping("/response-body-string-v3")
    fun responseBodyV3(): String {
        return "ok"
    }

    @GetMapping("/response-body-json-v1")
    fun responseBodyJsonV1(): ResponseEntity<HelloData> {
        val helloData = HelloData("userA", 20)

        return ResponseEntity(helloData, HttpStatus.OK)
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/response-body-json-v2")
    fun responseBodyJsonV2(): HelloData {
        return HelloData("userA", 20)
    }
}