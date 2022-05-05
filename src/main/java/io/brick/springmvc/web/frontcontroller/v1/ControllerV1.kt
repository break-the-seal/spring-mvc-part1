package io.brick.springmvc.web.frontcontroller.v1

import java.io.IOException
import javax.servlet.ServletException
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

interface ControllerV1 {

    @Throws(ServletException::class, IOException::class)
    fun process(request: HttpServletRequest, response: HttpServletResponse)
}