package io.brick.springmvc.basic.request

import mu.KLogging
import javax.servlet.annotation.WebServlet
import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

/**
 * getParameter: query parameter 방식, x-www-form-urlencoded 방식 두 가지 모두 지원
 */
@WebServlet(name = "requestParamServlet", urlPatterns = ["/request-param"])
class RequestParamServlet : HttpServlet() {
    companion object: KLogging()

    override fun service(request: HttpServletRequest, response: HttpServletResponse) {
        logger.info { "[전체 파라미터 조회] - start" }
        request.parameterNames

        request.parameterNames.asIterator()
            .forEachRemaining { logger.info { "${it} = ${request.getParameter(it)}" } }

        logger.info { "[전체 파라미터 조회] - end\n" }

        logger.info { "[단일 파라미터 조회] - start" }

        val username = request.getParameter("username")
        val age = request.getParameter("age")
        logger.info { "username = $username, age = $age" }

        logger.info { "[단일 파라미터 조회] - end\n" }

        logger.info { "[이름이 같은 복수 파라미터 조회]" }
        val usernames = request.getParameterValues("username")
        usernames.forEach { logger.info { "username = $it" } }

        response.writer.write("ok")
    }
}