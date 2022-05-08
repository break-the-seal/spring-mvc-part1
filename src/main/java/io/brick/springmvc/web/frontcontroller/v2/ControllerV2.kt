package io.brick.springmvc.web.frontcontroller.v2

import io.brick.springmvc.web.frontcontroller.MyView
import java.io.IOException
import javax.servlet.ServletException
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

interface ControllerV2 {

    @Throws(ServletException::class, IOException::class)
    fun process(request: HttpServletRequest, response: HttpServletResponse): MyView

}