package io.brick.springmvc.web.frontcontroller.v1

import io.brick.springmvc.web.frontcontroller.v1.controller.MemberFormControllerV1
import io.brick.springmvc.web.frontcontroller.v1.controller.MemberListControllerV1
import io.brick.springmvc.web.frontcontroller.v1.controller.MemberSaveControllerV1
import mu.KLogging
import javax.servlet.annotation.WebServlet
import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@WebServlet(name = "frontControllerServletV1", urlPatterns = ["/front-controller/v1/*"])
class FrontControllerServletV1 : HttpServlet() {
    companion object: KLogging()

    private val controllerMap: MutableMap<String, ControllerV1> = mutableMapOf()

    init {
        controllerMap["/front-controller/v1/members/new-form"] = MemberFormControllerV1()
        controllerMap["/front-controller/v1/members/save"] = MemberSaveControllerV1()
        controllerMap["/front-controller/v1/members"] = MemberListControllerV1()
    }

    override fun service(request: HttpServletRequest, response: HttpServletResponse) {
        logger.info { "FrontControllerServletV1.service" }

        val requestURI = request.requestURI

            val controller = controllerMap[requestURI]

        if (controller == null) {
            response.status = HttpServletResponse.SC_NOT_FOUND
            return
        }

        controller.process(request, response)
    }
}