package io.brick.springmvc.web.frontcontroller.v3.controller

import io.brick.springmvc.domain.member.Member
import io.brick.springmvc.domain.member.MemberRepository
import io.brick.springmvc.web.frontcontroller.ModelView
import io.brick.springmvc.web.frontcontroller.v3.ControllerV3

class MemberSaveControllerV3 : ControllerV3 {

    private val memberRepository = MemberRepository.getInstance()

    override fun process(paramMap: MutableMap<String, String>): ModelView {
        val username = paramMap["username"]
        val age = paramMap["age"]?.toInt()

        val member = Member(username = username!!, age = age!!)
        memberRepository.save(member)

        val mv = ModelView("save-result")
        mv.model["member"] = member
        return mv
    }
}