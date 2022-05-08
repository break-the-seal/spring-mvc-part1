package io.brick.springmvc.web.frontcontroller.v4.controller

import io.brick.springmvc.domain.member.Member
import io.brick.springmvc.domain.member.MemberRepository
import io.brick.springmvc.web.frontcontroller.v4.ControllerV4
import mu.KLogging

class MemberSaveControllerV4: ControllerV4 {
    companion object : KLogging()

    private val memberRepository: MemberRepository = MemberRepository.getInstance()

    override fun process(paramMap: Map<String, String>, model: MutableMap<String, Any>): String {
        logger.info { "MemberSaveControllerV4.process" }

        val username = paramMap["username"]
        val age = paramMap["age"]?.toInt()

        val member = Member(username = username!!, age = age!!)
        memberRepository.save(member)

        model["member"] = member
        return "save-result"
    }
}