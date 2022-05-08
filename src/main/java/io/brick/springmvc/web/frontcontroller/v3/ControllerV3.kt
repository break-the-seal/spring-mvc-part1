package io.brick.springmvc.web.frontcontroller.v3

import io.brick.springmvc.web.frontcontroller.ModelView

interface ControllerV3 {

    fun process(paramMap: MutableMap<String, String>) : ModelView
}