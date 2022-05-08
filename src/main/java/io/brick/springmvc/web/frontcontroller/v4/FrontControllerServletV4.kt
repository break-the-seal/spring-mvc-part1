package io.brick.springmvc.web.frontcontroller.v4

import io.brick.springmvc.web.frontcontroller.MyView
import io.brick.springmvc.web.frontcontroller.v4.controller.MemberFormControllerV4
import io.brick.springmvc.web.frontcontroller.v4.controller.MemberListControllerV4
import io.brick.springmvc.web.frontcontroller.v4.controller.MemberSaveControllerV4
import mu.KLogging
import javax.servlet.annotation.WebServlet
import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@WebServlet(name = "frontControllerServletV4", urlPatterns = ["/front-controller/v4/*"])
class FrontControllerServletV4 : HttpServlet() {
    companion object : KLogging()

    private val controllerMap: MutableMap<String, ControllerV4> = mutableMapOf()

    init {
        controllerMap["/front-controller/v4/members/new-form"] = MemberFormControllerV4()
        controllerMap["/front-controller/v4/members/save"] = MemberSaveControllerV4()
        controllerMap["/front-controller/v4/members"] = MemberListControllerV4()
    }

    override fun service(request: HttpServletRequest, response: HttpServletResponse) {
        logger.info { "FrontControllerServletV4.service" }

        val requestURI = request.requestURI

        // url mapping
        val controller = controllerMap[requestURI]
        if (controller == null) {
            response.status = HttpServletResponse.SC_NOT_FOUND
            return
        }

        // query param map 생성
        val paramMap = createParamMap(request)
        val model: MutableMap<String, Any> = mutableMapOf()

        // 단순 view name 반환
        val viewName = controller.process(paramMap, model)

        // viewResolver를 통해 MyView 생성 (/WEB-INF 경로 추가)
        val view = viewResolver(viewName)

        // frontController 에서 model 직접 제공
        view.render(model, request, response)
    }

    private fun viewResolver(viewName: String): MyView {
        return MyView("/WEB-INF/views/${viewName}.jsp")
    }

    private fun createParamMap(request: HttpServletRequest): Map<String, String> {
        val paramMap: MutableMap<String, String> = mutableMapOf()
        request.parameterNames.asIterator()
            .forEachRemaining { paramMap[it] = request.getParameter(it) }

        return paramMap
    }
}