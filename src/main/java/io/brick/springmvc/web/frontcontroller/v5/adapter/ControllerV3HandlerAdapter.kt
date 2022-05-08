package io.brick.springmvc.web.frontcontroller.v5.adapter

import io.brick.springmvc.web.frontcontroller.ModelView
import io.brick.springmvc.web.frontcontroller.v3.ControllerV3
import io.brick.springmvc.web.frontcontroller.v5.MyHandlerAdapter
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class ControllerV3HandlerAdapter: MyHandlerAdapter {
    override fun supports(handler: Any): Boolean {
        return handler is ControllerV3
    }

    override fun handle(request: HttpServletRequest, response: HttpServletResponse, handler: Any): ModelView {
        if (handler !is ControllerV3)
            throw RuntimeException("${this::class.simpleName} is not ControllerV3 implementation")

        val paramMap = createParamMap(request)

        return handler.process(paramMap)
    }

    private fun createParamMap(request: HttpServletRequest): Map<String, String> {
        val paramMap: MutableMap<String, String> = mutableMapOf()
        request.parameterNames.asIterator()
            .forEachRemaining { paramMap[it] = request.getParameter(it) }

        return paramMap
    }
}