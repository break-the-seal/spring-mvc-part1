package io.brick.springmvc.web.frontcontroller.v5

import io.brick.springmvc.web.frontcontroller.ModelView
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

interface MyHandlerAdapter {

    fun support(handler: Any): Boolean

    fun handle(request: HttpServletRequest, response: HttpServletResponse, handler: Any): ModelView
}