package io.brick.springmvc.basic.request

import mu.KLogging
import org.springframework.http.HttpEntity
import org.springframework.stereotype.Controller
import org.springframework.util.StreamUtils
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.ResponseBody
import java.io.InputStream
import java.io.Writer
import java.nio.charset.StandardCharsets
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Controller
class RequestBodyStringController {

    companion object: KLogging()

    @PostMapping("/request-body-string-v1")
    fun requestBodyStringV1(
        request: HttpServletRequest,
        response: HttpServletResponse
    ) {
        val inputStream = request.inputStream
        val messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8)

        logger.info("messageBody={}", messageBody)

        response.writer.write("ok")
    }

    /**
     * InputStream(Reader): HTTP 요청 메시지 바디의 내용을 직접 조회
     * OutputStream(Writer): HTTP 응답 메시지의 바디에 직접 결과 출력
     */
    @PostMapping("/request-body-string-v2")
    fun requestBodyStringV2(
        inputStream: InputStream,
        responseWriter: Writer
    ) {
        val messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8)
        logger.info("messageBody={}", messageBody)

        responseWriter.write("ok")
    }

    /**
     * HttpEntity: HTTP header, body 정보를 편라하게 조회
     *  - 메시지 바디 정보를 직접 조회(@RequestParam X, @ModelAttribute X)
     *  - HttpMessageConverter 사용 -> StringHttpMessageConverter 적용
     * 응답에서도 HttpEntity 사용 가능 (view 조회 x)
     */
    @PostMapping("/request-body-string-v3")
    fun requestBodyStringV3(httpEntity: HttpEntity<String>): HttpEntity<String> {
        val messageBody = httpEntity.body
        logger.info("messageBody={}", messageBody)

        return HttpEntity("ok")
    }

    /**
     * @RequestBody
     *  - 메시지 바디 정보를 직접 조회(@RequestParam X, @ModelAttribute X)
     *  - HttpMessageConverter 사용 -> StringHttpMessageConverter 적용
     * @ResponseBody
     *  - 메시지 바디 정보 직접 반환(view 조회X)
     *  - HttpMessageConverter 사용 -> StringHttpMessageConverter 적용
     */
    @ResponseBody
    @PostMapping("/request-body-string-v4")
    fun requestBodyStringV4(@RequestBody messageBody: String): String {
        logger.info("messageBody={}", messageBody)
        return "ok"
    }
}