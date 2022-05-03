package io.brick.springmvc.web.servletmvc

import javax.servlet.annotation.WebServlet
import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@WebServlet(name = "mvcMemberServlet", urlPatterns = ["/servlet-mvc/members/new-form"])
class MvcMemberServlet: HttpServlet() {
    override fun service(request: HttpServletRequest, response: HttpServletResponse) {
        val viewPath = "/WEB-INF/views/new-form.jsp"
        val dispatcher = request.getRequestDispatcher(viewPath) // controller -> view 이동

        // redirect 방식과 다름, client -> server -> 내부에서 jsp 호출 -> jsp 파일 response 보냄
        dispatcher.forward(request, response)
    }
}