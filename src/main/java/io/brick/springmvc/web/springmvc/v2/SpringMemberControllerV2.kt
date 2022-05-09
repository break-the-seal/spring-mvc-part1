package io.brick.springmvc.web.springmvc.v2

import io.brick.springmvc.domain.member.Member
import io.brick.springmvc.domain.member.MemberRepository
import mu.KLogging
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.servlet.ModelAndView
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Controller
@RequestMapping("/springmvc/v2/members")
class SpringMemberControllerV2 {
    companion object : KLogging()

    private val memberRepository = MemberRepository.getInstance()

    @RequestMapping("/new-form")
    fun newForm(): ModelAndView {
        return ModelAndView("new-form")
    }

    @RequestMapping("/save")
    fun save(request: HttpServletRequest, response: HttpServletResponse): ModelAndView {
        logger.info { "SpringMemberControllerV2.save" }
        val username = request.getParameter("username")
        val age = request.getParameter("age")?.toInt()

        val member = Member(username = username!!, age = age!!)
        memberRepository.save(member)

        val mv = ModelAndView("save-result")
        mv.addObject("member", member)
        return mv
    }

    @RequestMapping
    fun members(): ModelAndView {
        val members = memberRepository.findAll()

        val mv = ModelAndView("members")
        mv.addObject("members", members)

        return mv
    }
}