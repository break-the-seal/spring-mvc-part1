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
    companion object : KLogging()

    @PostMapping("/request-body-string-v1")
    fun requestBodyString(request: HttpServletRequest, response: HttpServletResponse) {
        val inputStream = request.inputStream
        val messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8)

        logger.info { "messageBody=${messageBody}" }

        response.writer.write("ok")
    }

    /**
     * InputStream, Writer에 대해서도 Spring MVC가 제공해준다.
     */
    @PostMapping("/request-body-string-v2")
    fun requestBodyStringV2(inputStream: InputStream, responseWriter: Writer) {
        val messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8)
        logger.info { "messageBody=${messageBody}" }
        responseWriter.write("ok")
    }

    // spring mvc에서 알아서 HttpMessageConverter 의해 body 내용을 converting 시켜준다.
    @PostMapping("/request-body-string-v3")
    fun requestBodyStringV3(httpEntity: HttpEntity<String>): HttpEntity<String> {
        // val messageBody = StreamUtvils.copyToString(inputStream, StandardCharsets.UTF_8)
        val messageBody = httpEntity.body
        logger.info { "messageBody=${messageBody}" }

        return HttpEntity("ok")
    }

    @ResponseBody
    @PostMapping("/request-body-string-v4")
    fun requestBodyStringV4(@RequestBody messageBody: String): String {
        logger.info { "messageBody=${messageBody}" }
        return "ok"
    }
}