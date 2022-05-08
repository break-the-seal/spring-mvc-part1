package io.brick.springmvc.web.frontcontroller.v5.adapter

import io.brick.springmvc.web.frontcontroller.ModelView
import io.brick.springmvc.web.frontcontroller.v4.ControllerV4
import io.brick.springmvc.web.frontcontroller.v5.MyHandlerAdapter
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class ControllerV4HandlerAdapter : MyHandlerAdapter {

    override fun support(handler: Any): Boolean {
        return handler is ControllerV4
    }

    override fun handle(request: HttpServletRequest, response: HttpServletResponse, handler: Any): ModelView {
        val controller = handler as ControllerV4

        val paramMap = createParamMap(request)
        val model = mutableMapOf<String, Any>()

        val viewName = controller.process(paramMap, model)
        val mv = ModelView(viewName)
        mv.model = model

        return mv
    }

    private fun createParamMap(request: HttpServletRequest): MutableMap<String, String> {
        val paramMap: MutableMap<String, String> = mutableMapOf()
        request.parameterNames.asIterator()
            .forEachRemaining { paramMap[it] = request.getParameter(it) }

        return paramMap
    }
}