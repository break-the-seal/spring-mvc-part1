package io.brick.springmvc.basic.response

import com.fasterxml.jackson.databind.ObjectMapper
import io.brick.springmvc.basic.HelloData
import java.nio.charset.StandardCharsets
import javax.servlet.annotation.WebServlet
import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@WebServlet(name = "responseJsonServlet", urlPatterns = ["/response-json"])
class ResponseJsonServlet : HttpServlet() {

    private val objectMapper = ObjectMapper()

    override fun service(request: HttpServletRequest?, response: HttpServletResponse?) {
        // Content-Type: application/json; charset=utf-8
        response?.contentType = "application/json"
        response?.characterEncoding = StandardCharsets.UTF_8.toString()

        val helloData = HelloData("yoo", 20)
        val result = objectMapper.writeValueAsString(helloData)
        response?.writer?.write(result)
    }
}