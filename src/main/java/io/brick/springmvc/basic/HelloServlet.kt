package io.brick.springmvc.basic

import mu.KLogging
import javax.servlet.annotation.WebServlet
import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@WebServlet(name = "helloServlet", urlPatterns = ["/hello"])
class HelloServlet : HttpServlet() {
    companion object : KLogging()

    override fun service(request: HttpServletRequest, response: HttpServletResponse) {
        logger.info { "HelloServlet.service" }
        logger.info { "req = [${request}], res = [${response}]" }

        val username = request.getParameter("username")
        logger.info { "username = ${username}" }

        response.contentType = "text/plain"
        response.characterEncoding = "utf-8"
        response.writer.write("hello ${username}")
    }
}