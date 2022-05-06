package io.brick.springmvc.web.frontcontroller.v3.controller

import io.brick.springmvc.web.frontcontroller.ModelView
import io.brick.springmvc.web.frontcontroller.v3.ControllerV3

class MemberFormControllerV3: ControllerV3 {
    override fun process(paramMap: Map<String, String>): ModelView {
        return ModelView("new-form")
    }
}