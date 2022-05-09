package io.brick.springmvc.web.springmvc.v3

import io.brick.springmvc.domain.member.Member
import io.brick.springmvc.domain.member.MemberRepository
import mu.KLogging
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam

@Controller
@RequestMapping("/springmvc/v3/members")
class SpringMemberControllerV3 {
    companion object : KLogging()

    private val memberRepository = MemberRepository.getInstance()

    @GetMapping("/new-form")
    fun newForm(): String {
        return "new-form"
    }

    @PostMapping("/save")
    fun save(
        @RequestParam("username") username: String,
        @RequestParam("age") age: Int,
        model: Model
    ): String {
        logger.info { "SpringMemberControllerV3.save" }

        val member = Member(username = username, age = age)
        memberRepository.save(member)

        model.addAttribute("member", member)

        return "save-result"
    }

    @GetMapping
    fun members(model: Model): String {
        val members = memberRepository.findAll()
        model.addAttribute("members", members)

        return "members"
    }
}