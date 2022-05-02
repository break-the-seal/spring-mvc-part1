package io.brick.springmvc.basic.request

import com.fasterxml.jackson.databind.ObjectMapper
import io.brick.springmvc.basic.HelloData
import org.springframework.util.StreamUtils
import java.nio.charset.StandardCharsets
import javax.servlet.annotation.WebServlet
import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@WebServlet(name = "requestBodyJsonServlet", urlPatterns = ["/request-body-json"])
class RequestBodyJsonServlet : HttpServlet() {

    private val objectMapper = ObjectMapper()

    override fun service(request: HttpServletRequest?, response: HttpServletResponse?) {
        val inputStream = request?.inputStream
        val messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8)
        println("messageBody = $messageBody")

        val helloData = objectMapper.readValue(messageBody, HelloData::class.java)
        println("helloData = $helloData")
    }
}