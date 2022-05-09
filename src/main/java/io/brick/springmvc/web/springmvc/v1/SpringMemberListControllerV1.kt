package io.brick.springmvc.web.springmvc.v1

import io.brick.springmvc.domain.member.MemberRepository
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.servlet.ModelAndView

@Controller
class SpringMemberListControllerV1 {
    private val memberRepository = MemberRepository.getInstance()

    @RequestMapping("/springmvc/v1/members")
    fun process(): ModelAndView {
        val members = memberRepository.findAll()

        val mv = ModelAndView("members")
        mv.addObject("members", members)

        return mv
    }
}