package io.brick.springmvc.basic.request

import com.fasterxml.jackson.databind.ObjectMapper
import io.brick.springmvc.basic.HelloData
import mu.KLogging
import org.springframework.util.StreamUtils
import java.nio.charset.StandardCharsets
import javax.servlet.annotation.WebServlet
import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@WebServlet(name = "requestBodyJsonServlet", urlPatterns = ["/request-body-json"])
class RequestBodyJsonServlet : HttpServlet() {

    private val objectMapper = ObjectMapper()

    companion object : KLogging()

    override fun service(request: HttpServletRequest, response: HttpServletResponse) {
        val inputStream = request.inputStream
        val messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8)

        logger.info { "\n messageBody = ${messageBody}" }

        val helloData = objectMapper.readValue(messageBody, HelloData::class.java)
        logger.info { "helloData.username = ${helloData.username}" }
        logger.info { "helloData.age = ${helloData.age}" }

        response.writer.write("ok")
    }
}