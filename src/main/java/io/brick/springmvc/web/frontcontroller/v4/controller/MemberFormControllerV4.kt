package io.brick.springmvc.web.frontcontroller.v4.controller

import io.brick.springmvc.web.frontcontroller.v4.ControllerV4

class MemberFormControllerV4: ControllerV4 {
    override fun process(paramMap: Map<String, String>, model: MutableMap<String, Any>): String {
        return "new-form"
    }
}