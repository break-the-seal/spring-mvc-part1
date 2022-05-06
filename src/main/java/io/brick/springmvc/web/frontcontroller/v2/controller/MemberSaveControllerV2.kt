package io.brick.springmvc.web.frontcontroller.v2.controller

import io.brick.springmvc.domain.member.Member
import io.brick.springmvc.domain.member.MemberRepository
import io.brick.springmvc.web.frontcontroller.MyView
import io.brick.springmvc.web.frontcontroller.v2.ControllerV2
import mu.KLogging
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class MemberSaveControllerV2 : ControllerV2 {
    companion object : KLogging()

    private val memberRepository: MemberRepository = MemberRepository.getInstance()

    override fun process(request: HttpServletRequest, response: HttpServletResponse): MyView {
        logger.info { "MemberSaveControllerV2.process" }
        val username = request.getParameter("username")
        val age = request.getParameter("age").toInt()

        val member = Member(username = username, age = age)
        memberRepository.save(member)

        request.setAttribute("member", member)

        return MyView("/WEB-INF/views/save-result.jsp")
    }
}