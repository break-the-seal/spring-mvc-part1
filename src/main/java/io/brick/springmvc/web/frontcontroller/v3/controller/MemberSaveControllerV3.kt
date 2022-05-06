package io.brick.springmvc.web.frontcontroller.v3.controller

import io.brick.springmvc.domain.member.Member
import io.brick.springmvc.domain.member.MemberRepository
import io.brick.springmvc.web.frontcontroller.ModelView
import io.brick.springmvc.web.frontcontroller.v3.ControllerV3
import mu.KLogging

class MemberSaveControllerV3: ControllerV3 {
    companion object : KLogging()

    private val memberRepository: MemberRepository = MemberRepository.getInstance()

    override fun process(paramMap: Map<String, String>): ModelView {
        logger.info { "MemberSaveControllerV3.process" }
        val username = paramMap["username"]
        val age = paramMap["age"]?.toInt()

        val member = Member(username = username!!, age = age!!)
        memberRepository.save(member)

        val mv = ModelView("save-result")
        mv.model["member"] = member
        return mv
    }
}