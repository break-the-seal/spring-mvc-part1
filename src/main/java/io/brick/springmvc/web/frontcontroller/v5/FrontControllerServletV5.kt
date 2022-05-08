package io.brick.springmvc.web.frontcontroller.v5

import io.brick.springmvc.web.frontcontroller.MyView
import io.brick.springmvc.web.frontcontroller.v3.controller.MemberFormControllerV3
import io.brick.springmvc.web.frontcontroller.v3.controller.MemberListControllerV3
import io.brick.springmvc.web.frontcontroller.v3.controller.MemberSaveControllerV3
import io.brick.springmvc.web.frontcontroller.v4.controller.MemberFormControllerV4
import io.brick.springmvc.web.frontcontroller.v4.controller.MemberListControllerV4
import io.brick.springmvc.web.frontcontroller.v4.controller.MemberSaveControllerV4
import io.brick.springmvc.web.frontcontroller.v5.adapter.ControllerV3HandlerAdapter
import io.brick.springmvc.web.frontcontroller.v5.adapter.ControllerV4HandlerAdapter
import mu.KLogging
import javax.servlet.annotation.WebServlet
import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@WebServlet(name = "frontControllerServletV5", urlPatterns = ["/front-controller/v5/*"])
class FrontControllerServletV5: HttpServlet() {
    companion object : KLogging()

    private val handlerMappingMap: MutableMap<String, Any> = mutableMapOf()
    private val handlerAdapters: MutableList<MyHandlerAdapter> = mutableListOf()

    init {
        initHandlerMappingMap()
        initHandlerAdapters()
    }

    private fun initHandlerMappingMap() {
        handlerMappingMap["/front-controller/v5/v3/members/new-form"] = MemberFormControllerV3()
        handlerMappingMap["/front-controller/v5/v3/members/save"] = MemberSaveControllerV3()
        handlerMappingMap["/front-controller/v5/v3/members"] = MemberListControllerV3()

        handlerMappingMap["/front-controller/v5/v4/members/new-form"] = MemberFormControllerV4()
        handlerMappingMap["/front-controller/v5/v4/members/save"] = MemberSaveControllerV4()
        handlerMappingMap["/front-controller/v5/v4/members"] = MemberListControllerV4()
    }

    private fun initHandlerAdapters() {
        handlerAdapters.add(ControllerV3HandlerAdapter())
        handlerAdapters.add(ControllerV4HandlerAdapter())
    }

    override fun service(request: HttpServletRequest, response: HttpServletResponse) {
        // url mapping (find handler)
        val handler = getHandler(request)
        if (handler == null) {
            response.status = HttpServletResponse.SC_NOT_FOUND
            return
        }

        // find handler adapter
        val adapter = getHandlerAdapter(handler)
            ?: throw RuntimeException("Not Found handler adapter: (handler = ${handler})")

        val mv = adapter.handle(request, response, handler)

        // viewResolver를 통해 MyView 생성 (/WEB-INF 경로 추가)
        val view = viewResolver(mv.viewName)

        // model까지 해서 render 과정
        view.render(mv.model, request, response)
    }

    private fun getHandler(request: HttpServletRequest): Any? {
        val requestURI = request.requestURI
        return handlerMappingMap[requestURI]
    }

    private fun getHandlerAdapter(handler: Any): MyHandlerAdapter? {
        return handlerAdapters.firstOrNull { it.supports(handler) }
    }

    private fun viewResolver(viewName: String): MyView {
        return MyView("/WEB-INF/views/${viewName}.jsp")
    }
}