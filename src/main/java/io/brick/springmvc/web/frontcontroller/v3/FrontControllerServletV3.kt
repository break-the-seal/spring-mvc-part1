package io.brick.springmvc.web.frontcontroller.v3

import io.brick.springmvc.web.frontcontroller.MyView
import io.brick.springmvc.web.frontcontroller.v3.controller.MemberFormControllerV3
import io.brick.springmvc.web.frontcontroller.v3.controller.MemberListControllerV3
import io.brick.springmvc.web.frontcontroller.v3.controller.MemberSaveControllerV3
import mu.KLogging
import javax.servlet.annotation.WebServlet
import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@WebServlet(name = "frontControllerServletV3", urlPatterns = ["/front-controller/v3/*"])
class FrontControllerServletV3 : HttpServlet() {
    companion object : KLogging()

    private val controllerMap: MutableMap<String, ControllerV3> = mutableMapOf()

    init {
        controllerMap["/front-controller/v3/members/new-form"] = MemberFormControllerV3()
        controllerMap["/front-controller/v3/members/save"] = MemberSaveControllerV3()
        controllerMap["/front-controller/v3/members"] = MemberListControllerV3()
    }

    override fun service(request: HttpServletRequest, response: HttpServletResponse) {
        logger.info { "FrontControllerServletV3.service" }

        val requestURI = request.requestURI

        // url mapping
        val controller = controllerMap[requestURI]
        if (controller == null) {
            response.status = HttpServletResponse.SC_NOT_FOUND
            return
        }

        // query param map 생성
        val paramMap = createParamMap(request)

        // ModelView 반환
        val mv = controller.process(paramMap)

        // viewResolver를 통해 MyView 생성 (/WEB-INF 경로 추가)
        val view = viewResolver(mv.viewName)

        // model까지 해서 render 과정
        view.render(mv.model, request, response)
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