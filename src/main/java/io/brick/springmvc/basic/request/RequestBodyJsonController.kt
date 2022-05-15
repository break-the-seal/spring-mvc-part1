package io.brick.springmvc.basic.request

import com.fasterxml.jackson.databind.ObjectMapper
import io.brick.springmvc.basic.HelloData
import mu.KLogging
import org.springframework.http.HttpEntity
import org.springframework.stereotype.Controller
import org.springframework.util.StreamUtils
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.ResponseBody
import java.nio.charset.StandardCharsets
import javax.servlet.ServletInputStream
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

/**
 * {"username":"beanie", "age":30}
 * content-type: application/json
 */
@Controller
class RequestBodyJsonController {
    companion object: KLogging()

    private val objectMapper = ObjectMapper()

    @PostMapping("/request-body-json-v1")
    fun requestBodyJsonV1(request: HttpServletRequest, response: HttpServletResponse) {
        val inputStream: ServletInputStream = request.inputStream
        val messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8)
        logger.info { "messageBody=${messageBody}" }

        val helloData = objectMapper.readValue(messageBody, HelloData::class.java)
        logger.info { "username=${helloData.username}, age=${helloData.age}" }

        response.writer.write("ok")
    }

    @ResponseBody
    @PostMapping("/request-body-json-v2")
    fun requestBodyJsonV2(@RequestBody messageBody: String): String {
        logger.info { "messageBody=${messageBody}" }

        val helloData = objectMapper.readValue(messageBody, HelloData::class.java)
        logger.info { "username=${helloData.username}, age=${helloData.age}" }

        return "ok"
    }

    /**
     * @RequestBody 생략 불가능
     * HttpMessageConverter -> MappingJackson2HttpMessageConverter 적용
     * (content-type: application/json)
     */
    @ResponseBody
    @PostMapping("/request-body-json-v3")
    fun requestBodyJsonV3(@RequestBody helloData: HelloData): String {
        logger.info { "username=${helloData.username}, age=${helloData.age}" }
         return "ok"
    }

    @ResponseBody
    @PostMapping("/request-body-json-v4")
    fun requestBodyJsonV4(httpEntity: HttpEntity<HelloData>): String {
        val helloData = httpEntity.body
        logger.info { "username=${helloData?.username}, age=${helloData?.age}" }
        return "ok"
    }

    /**
     * @ResponseBody 적용
     * - 메시지 바디 정보 직접 반환(view 조회 X)
     * - HttpMessageConverter 사용 -> MappingJackson2HttpMessageConverter 적용
     * (accept: application/json)
     */
    @ResponseBody
    @PostMapping("/request-body-json-v5")
    fun requestBodyJsonV5(@RequestBody helloData: HelloData): HelloData {
        logger.info { "username=${helloData.username}, age=${helloData.age}" }
        return helloData
    }
}