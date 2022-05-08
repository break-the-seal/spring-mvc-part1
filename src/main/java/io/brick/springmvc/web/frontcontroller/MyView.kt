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

    fun render(model: MutableMap<String, Any>, request: HttpServletRequest, response: HttpServletResponse) {
        modelToAttribute(model, request)
        val dispatcher = request.getRequestDispatcher(viewPath)
        dispatcher.forward(request, response)
    }

    private fun modelToAttribute(
        model: MutableMap<String, Any>,
        request: HttpServletRequest
    ) {
        model.forEach { (k, v) -> request.setAttribute(k, v) }
    }
}