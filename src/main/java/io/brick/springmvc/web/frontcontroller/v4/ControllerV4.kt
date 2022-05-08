package io.brick.springmvc.web.frontcontroller.v4

import io.brick.springmvc.web.frontcontroller.ModelView

interface ControllerV4 {
    /**
     * @param paramMap
     * @param model
     * @return viewName
     */
    fun process(paramMap: Map<String, String>, model: MutableMap<String, Any>): String
}