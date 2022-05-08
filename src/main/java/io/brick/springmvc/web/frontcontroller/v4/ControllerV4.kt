package io.brick.springmvc.web.frontcontroller.v4

interface ControllerV4 {

    /**
     * @param paramMap
     * @param model
     * @return
     */
    fun process(paramMap: MutableMap<String, String>, model: MutableMap<String, Any>): String
}