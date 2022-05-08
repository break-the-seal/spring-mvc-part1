package io.brick.springmvc.web.frontcontroller.v2.controller

import io.brick.springmvc.web.frontcontroller.MyView
import io.brick.springmvc.web.frontcontroller.v2.ControllerV2
import io.brick.springmvc.web.frontcontroller.v3.ControllerV3
import io.brick.springmvc.web.frontcontroller.v3.controller.MemberFormControllerV3
import io.brick.springmvc.web.frontcontroller.v3.controller.MemberListControllerV3
import io.brick.springmvc.web.frontcontroller.v3.controller.MemberSaveControllerV3
import javax.servlet.annotation.WebServlet
import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@WebServlet(name = "frontControllerServletV", urlPatterns = ["/front-controller/v3/*"])
class FrontControllerServletV3 : HttpServlet() {

    private var controllerMap = mutableMapOf<String, ControllerV3>()

    init {
        controllerMap["/front-controller/v2/members/new-form"] = MemberFormControllerV3()
        controllerMap["/front-controller/v2/members/save"] = MemberSaveControllerV3()
        controllerMap["/front-controller/v2/members"] = MemberListControllerV3()
    }

    override fun service(request: HttpServletRequest, response: HttpServletResponse) {
        val requestURI = request.requestURI
        val controller = controllerMap[requestURI]
        if (controller == null) {
            response.status = HttpServletResponse.SC_NOT_FOUND
            return
        }

        // paramMap 생성 및 ModelView 반환
        val paramMap = createParamMap(request)
        val mv = controller.process(paramMap)

        // view-resolver를 통해 MyView 생성 (/WEB-INF 경로 추가)
        val view = viewResolver(mv.viewName)

        // model까지 해서 render 과정
        view.render(mv.model, request, response)
    }

    private fun viewResolver(viewName: String): MyView {
        return MyView("/WEB-INF/views/${viewName}.jsp")
    }

    private fun createParamMap(request: HttpServletRequest): MutableMap<String, String> {
        val paramMap: MutableMap<String, String> = mutableMapOf()
        request.parameterNames.asIterator()
            .forEachRemaining { paramMap[it] = request.getParameter(it) }

        return paramMap
    }
}