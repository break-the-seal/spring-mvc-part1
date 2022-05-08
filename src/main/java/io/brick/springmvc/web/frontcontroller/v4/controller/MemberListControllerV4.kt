package io.brick.springmvc.web.frontcontroller.v4.controller

import io.brick.springmvc.domain.member.MemberRepository
import io.brick.springmvc.web.frontcontroller.ModelView
import io.brick.springmvc.web.frontcontroller.v3.ControllerV3
import io.brick.springmvc.web.frontcontroller.v4.ControllerV4

class MemberListControllerV4 : ControllerV4 {

    private val memberRepository = MemberRepository.getInstance()

    override fun process(paramMap: MutableMap<String, String>, model: MutableMap<String, Any>): String {
        val members = memberRepository.findAll()
        model["members"] = members

        return "members"
    }
}