package io.brick.springmvc.web.frontcontroller.v5.adapter

import io.brick.springmvc.web.frontcontroller.ModelView
import io.brick.springmvc.web.frontcontroller.v4.ControllerV4
import io.brick.springmvc.web.frontcontroller.v5.MyHandlerAdapter
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class ControllerV4HandlerAdapter: MyHandlerAdapter {
    override fun supports(handler: Any): Boolean {
        return handler is ControllerV4
    }

    override fun handle(request: HttpServletRequest, response: HttpServletResponse, handler: Any): ModelView {
        if (handler !is ControllerV4)
            throw RuntimeException("${this::class.simpleName} is not ControllerV4 implementation")

        val paramMap = createParamMap(request)
        val model: MutableMap<String, Any> = mutableMapOf()

        val viewName = handler.process(paramMap, model)

        // 여기서 adapter의 역할을 수행
        return ModelView(viewName).apply {
            this.model = model
        }
    }

    private fun createParamMap(request: HttpServletRequest): Map<String, String> {
        val paramMap: MutableMap<String, String> = mutableMapOf()
        request.parameterNames.asIterator()
            .forEachRemaining { paramMap[it] = request.getParameter(it) }

        return paramMap
    }
}