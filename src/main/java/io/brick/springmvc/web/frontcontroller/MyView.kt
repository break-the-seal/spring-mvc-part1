package io.brick.springmvc.web.frontcontroller

import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class MyView(
    val viewPath: String
) {

    fun render(request: HttpServletRequest, response: HttpServletResponse) {
        val dispatcher = request.getRequestDispatcher(viewPath)
        dispatcher.forward(request, response)
    }
}