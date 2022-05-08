package io.brick.springmvc.web.frontcontroller.v2.controller

import io.brick.springmvc.domain.member.MemberRepository
import io.brick.springmvc.web.frontcontroller.MyView
import io.brick.springmvc.web.frontcontroller.v2.ControllerV2
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class MemberListControllerV2 : ControllerV2 {

    private val memberRepository = MemberRepository.getInstance()

    override fun process(request: HttpServletRequest, response: HttpServletResponse): MyView {
        val members = memberRepository.findAll()
        request.setAttribute("members", members)

        return MyView("/WEB-INF/views/members.jsp")
    }
}