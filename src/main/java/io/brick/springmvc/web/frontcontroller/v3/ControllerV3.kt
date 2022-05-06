package io.brick.springmvc.web.frontcontroller.v3

import io.brick.springmvc.web.frontcontroller.ModelView

interface ControllerV3 {
    fun process(paramMap: Map<String, String>): ModelView
}