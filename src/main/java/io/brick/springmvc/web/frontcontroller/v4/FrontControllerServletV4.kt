package io.brick.springmvc.web.frontcontroller.v4

import io.brick.springmvc.web.frontcontroller.MyView
import io.brick.springmvc.web.frontcontroller.v4.controller.MemberFormControllerV4
import io.brick.springmvc.web.frontcontroller.v4.controller.MemberListControllerV4
import io.brick.springmvc.web.frontcontroller.v4.controller.MemberSaveControllerV4
import javax.servlet.annotation.WebServlet
import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@WebServlet(name = "frontControllerServletV4", urlPatterns = ["/front-controller/v4/*"])
class FrontControllerServletV4 : HttpServlet() {

    private var controllerMap = mutableMapOf<String, ControllerV4>()

    init {
        controllerMap["/front-controller/v4/members/new-form"] = MemberFormControllerV4()
        controllerMap["/front-controller/v4/members/save"] = MemberSaveControllerV4()
        controllerMap["/front-controller/v4/members"] = MemberListControllerV4()
    }

    override fun service(request: HttpServletRequest, response: HttpServletResponse) {
        val requestURI = request.requestURI
        val controller = controllerMap[requestURI]
        if (controller == null) {
            response.status = HttpServletResponse.SC_NOT_FOUND
            return
        }

        // paramMap, model 생성 및 view-name 반환
        val paramMap = createParamMap(request)
        val model = mutableMapOf<String, Any>()
        val viewName = controller.process(paramMap, model)

        // view-resolver를 통해 MyView 생성 (/WEB-INF 경로 추가)
        val view = viewResolver(viewName)

        // model까지 해서 render 과정
        view.render(model, request, response)
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