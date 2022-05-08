package io.brick.springmvc.web.frontcontroller.v3.controller

import io.brick.springmvc.domain.member.MemberRepository
import io.brick.springmvc.web.frontcontroller.ModelView
import io.brick.springmvc.web.frontcontroller.v3.ControllerV3

class MemberListControllerV3 : ControllerV3 {

    private val memberRepository = MemberRepository.getInstance()

    override fun process(paramMap: MutableMap<String, String>): ModelView {
        val members = memberRepository.findAll()
        val mv = ModelView("members")
        mv.model["members"] = members

        return mv
    }
}