package io.brick.springmvc.web.servletmvc

import javax.servlet.annotation.WebServlet
import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@WebServlet(name = "mvcMemberFormServlet", urlPatterns = ["/servlet-mvc/members/new-form"])
class MvcMemberFormServlet : HttpServlet() {

    override fun service(request: HttpServletRequest, response: HttpServletResponse) {
        // controller에서 해당 경로의 jsp로 전달 (서버 내부에서의 호출)
        val viewPath = "/WEB-INF/views/new-form.jsp"
        val dispatcher = request.getRequestDispatcher(viewPath)
        dispatcher.forward(request, response)
    }
}