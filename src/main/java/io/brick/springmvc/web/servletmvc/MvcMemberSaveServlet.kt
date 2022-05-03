package io.brick.springmvc.web.servletmvc

import io.brick.springmvc.domain.member.Member
import io.brick.springmvc.domain.member.MemberRepository
import mu.KLogging
import javax.servlet.annotation.WebServlet
import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@WebServlet(name = "mvcMemberSaveServlet", urlPatterns = ["/servlet-mvc/members/save"])
class MvcMemberSaveServlet: HttpServlet() {
    companion object: KLogging()

    private val memberRepository = MemberRepository.getInstance()

    override fun service(request: HttpServletRequest, response: HttpServletResponse) {
        logger.info { "MvcMemberSaveServlet.service" }
        val username = request.getParameter("username")
        val age = request.getParameter("age").toInt()

        val member = Member(username = username, age = age)
        memberRepository.save(member)

        // Model에 저장
        request.setAttribute("member", member)

        // 맨 앞에 '/' 붙여야 한다.
        val viewPath = "/WEB-INF/views/save-result.jsp"
        val dispatcher = request.getRequestDispatcher(viewPath)
        dispatcher.forward(request, response)
    }
}