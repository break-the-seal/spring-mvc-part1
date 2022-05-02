package io.brick.springmvc.basic.request

import javax.servlet.annotation.WebServlet
import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

/**
 * 1. 파라미터 전송 기능
 * http://localhost:8080?username=hello&age=20
 *
 */
@WebServlet(name = "requestParamServlet", urlPatterns = ["/request-param"])
class RequestParamServlet : HttpServlet() {

    override fun service(request: HttpServletRequest?, response: HttpServletResponse?) {
        println("[전체 파라미터 조회] - start")
        request?.parameterNames?.asIterator()
            ?.forEachRemaining { paramName -> println("$paramName = ${request?.getParameter(paramName)}") }
        println("[전체 파라미터 조회] - end")
        println()

        println("[단일 파라미터 조회] - start")
        val username = request?.getParameter("username")
        val age = request?.getParameter("age")
        println("username = ${username}")
        println("age = ${age}")
        println("[단일 파라미터 조회] - end")
        println()

        println("[이름이 같은 복수 파라미터 조회] - start")
        val usernames = request?.getParameterValues("username")
        if (usernames != null) {
            for (name in usernames) {
                println("name = $name")
            }
        }
        println("[이름이 같은 복수 파라미터 조회] - end")
        println()

        response?.writer?.write("ok")
    }
}