package io.brick.springmvc.web.frontcontroller.v2

import io.brick.springmvc.web.frontcontroller.v2.controller.MemberFormControllerV2
import io.brick.springmvc.web.frontcontroller.v2.controller.MemberListControllerV2
import io.brick.springmvc.web.frontcontroller.v2.controller.MemberSaveControllerV2
import mu.KLogging
import javax.servlet.annotation.WebServlet
import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@WebServlet(name = "frontControllerServletV2", urlPatterns = ["/front-controller/v2/*"])
class FrontControllerServletV2 : HttpServlet() {
    companion object: KLogging()

    private val controllerMap: MutableMap<String, ControllerV2> = mutableMapOf()

    init {
        controllerMap["/front-controller/v2/members/new-form"] = MemberFormControllerV2()
        controllerMap["/front-controller/v2/members/save"] = MemberSaveControllerV2()
        controllerMap["/front-controller/v2/members"] = MemberListControllerV2()
    }

    override fun service(request: HttpServletRequest, response: HttpServletResponse) {
        logger.info { "FrontControllerServletV2.service" }

        val requestURI = request.requestURI

            val controller = controllerMap[requestURI]

        if (controller == null) {
            response.status = HttpServletResponse.SC_NOT_FOUND
            return
        }

        val view = controller.process(request, response)
        view.render(request, response)
    }
}