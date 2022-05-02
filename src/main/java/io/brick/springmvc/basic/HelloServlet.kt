package io.brick.springmvc.basic

import javax.servlet.annotation.WebServlet
import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@WebServlet(name = "helloServlet", urlPatterns = ["/hello"])
class HelloServlet : HttpServlet() {

    override fun service(request: HttpServletRequest?, response: HttpServletResponse?) {
        // super.service(req, resp)
        println("HelloServlet.service")
        println("request = ${request}")
        println("response = ${response}")

        // request
        val username = request?.getParameter("username")
        println("username = $username")

        // response
        response?.contentType = "text/plain"
        response?.characterEncoding = "utf-8"
        response?.writer?.write("hello $username")

    }
}