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
import java.io.IOException
import java.nio.charset.StandardCharsets
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse


/**
 * {"username":"hello", "age":20}
 * content-type: application/json
 */
@Controller
class RequestBodyJsonController {

    companion object: KLogging()

    private val objectMapper = ObjectMapper()

    @PostMapping("/request-body-json-v1")
    fun requestBodyJsonV1(
        request: HttpServletRequest,
        response: HttpServletResponse
    ) {
        val inputStream = request.inputStream
        val messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8)

        logger.info("messageBody={}", messageBody)
        val data = objectMapper.readValue(messageBody, HelloData::class.java)
        logger.info("username={}, age={}", data.username, data.age)

        response.writer.write("ok")
    }

    /**
     * @RequestBody
     *  HttpMessageConverter 사용 -> StringHttpMessageConverter 적용
     * @ResponseBody
     *  - 모든 메서드에 @ResponseBody 적용
     *  - 메시지 바디 정보 직접 반환(view 조회X)
     *  - HttpMessageConverter 사용 -> StringHttpMessageConverter 적용
     */
    @ResponseBody
    @PostMapping("/request-body-json-v2")
    @Throws(IOException::class)
    fun requestBodyJsonV2(@RequestBody messageBody: String?): String? {
        val (username, age) = objectMapper.readValue(messageBody, HelloData::class.java)
        logger.info("username={}, age={}", username, age)
        return "ok"
    }

    /**
     * @RequestBody 생략 불가능(@ModelAttribute 가 적용되어 버림)
     * HttpMessageConverter 사용 -> MappingJackson2HttpMessageConverter (content-type: application/json)
     */
    @ResponseBody
    @PostMapping("/request-body-json-v3")
    fun requestBodyJsonV3(@RequestBody data: HelloData): String? {
        logger.info("username={}, age={}", data.username, data.age)
        return "ok"
    }

    @ResponseBody
    @PostMapping("/request-body-json-v4")
    fun requestBodyJsonV4(@RequestBody httpEntity: HttpEntity<HelloData>): String? {
        val data = httpEntity.body
        logger.info("username={}, age={}", data.username, data.age)
        return "ok"
    }

    /**
     * @RequestBody 생략 불가능(@ModelAttribute 가 적용되어 버림)
     * HttpMessageConverter 사용 -> MappingJackson2HttpMessageConverter (content-type: application/json)
     *
     * @ResponseBody 적용
     *  - 메시지 바디 정보 직접 반환(view 조회X)
     *  - HttpMessageConverter 사용 -> MappingJackson2HttpMessageConverter 적용 (Accept: application/json)
     */
    @ResponseBody
    @PostMapping("/request-body-json-v5")
    fun requestBodyJsonV5(@RequestBody data: HelloData): HelloData {
        logger.info("username={}, age={}", data.username, data.age)
        return data
    }
}