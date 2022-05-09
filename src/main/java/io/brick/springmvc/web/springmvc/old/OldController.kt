package io.brick.springmvc.web.springmvc.old

import org.springframework.stereotype.Component
import org.springframework.web.servlet.ModelAndView
import org.springframework.web.servlet.mvc.Controller
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Component("/springmvc/old-controller")
class OldController: Controller {
    override fun handleRequest(request: HttpServletRequest, response: HttpServletResponse): ModelAndView? {
        println("OldController.handleRequest")
        return ModelAndView("new-form")
    }
}