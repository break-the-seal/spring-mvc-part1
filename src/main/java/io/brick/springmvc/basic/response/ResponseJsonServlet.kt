package io.brick.springmvc.basic.response

import com.fasterxml.jackson.databind.ObjectMapper
import io.brick.springmvc.basic.HelloData
import javax.servlet.annotation.WebServlet
import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse


@WebServlet(name = "responseJsonServlet", urlPatterns = ["/response-json"])
class ResponseJsonServlet: HttpServlet() {
    private val objectMapper = ObjectMapper()

    override fun service(request: HttpServletRequest, response: HttpServletResponse) {
        response.contentType = "application/json"
        response.characterEncoding = "utf-8"

        val helloData = HelloData().apply {
            username = "beanie"
            age = 20
        }

        val result = objectMapper.writeValueAsString(helloData)
        response.writer.write(result)
    }

}