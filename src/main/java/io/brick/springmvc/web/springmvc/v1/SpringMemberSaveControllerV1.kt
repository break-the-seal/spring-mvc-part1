package io.brick.springmvc.web.springmvc.v1

import io.brick.springmvc.domain.member.Member
import io.brick.springmvc.domain.member.MemberRepository
import mu.KLogging
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.servlet.ModelAndView
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Controller
class SpringMemberSaveControllerV1 {

    companion object : KLogging()

    private val memberRepository: MemberRepository = MemberRepository.getInstance()

    @RequestMapping("/springmvc/v1/members/save")
    fun process(request: HttpServletRequest, response: HttpServletResponse): ModelAndView {
        logger.info { "SpringMemberSaveControllerV1.process" }
        val username = request.getParameter("username")
        val age = request.getParameter("age")?.toInt()

        val member = Member(username = username!!, age = age!!)
        memberRepository.save(member)

        val mv = ModelAndView("save-result")
        mv.addObject("member", member)
        return mv
    }
}