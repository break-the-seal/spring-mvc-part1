package io.brick.springmvc.web.frontcontroller.v5

import io.brick.springmvc.web.frontcontroller.ModelView
import java.io.IOException
import javax.servlet.ServletException
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

interface MyHandlerAdapter {
    fun supports(handler: Any): Boolean

    @Throws(ServletException::class, IOException::class)
    fun handle(request: HttpServletRequest, response: HttpServletResponse, handler: Any): ModelView
}