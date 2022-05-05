package io.brick.springmvc.web.frontcontroller.v1.controller

import io.brick.springmvc.domain.member.Member
import io.brick.springmvc.domain.member.MemberRepository
import io.brick.springmvc.web.frontcontroller.v1.ControllerV1
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class MemberSaveControllerV1 : ControllerV1 {

    private val memberRepository = MemberRepository.getInstance()

    override fun process(request: HttpServletRequest, response: HttpServletResponse) {
        val username = request.getParameter("username")
        val age = request.getParameter("age").toInt()

        val member = Member(username = username, age = age)
        memberRepository.save(member)

        // model에 데이터 보관
        request.setAttribute("member", member)

        val viewPath = "/WEB-INF/views/save-result.jsp"
        val dispatcher = request.getRequestDispatcher(viewPath)
        dispatcher.forward(request, response)
    }
}