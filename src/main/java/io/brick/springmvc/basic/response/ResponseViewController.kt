package io.brick.springmvc.basic.response

import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.servlet.ModelAndView

@Controller
class ResponseViewController {

    @RequestMapping("/response-view-v1")
    fun responseViewV1(): ModelAndView {
        return ModelAndView("response/hello")
            .addObject("data", "hello!")
    }

    @RequestMapping("/response-view-v2")
    fun responseViewV2(model: Model): String {
        model.addAttribute("data", "hello!")
        return "response/hello"
    }

    /**
     * 컨트롤러의 경로와 view의 경로가 동일하면 리턴 생략 가능 (권장 x)
     */
    @RequestMapping("/response/hello")
    fun responseViewV3(model: Model): Unit {
        model.addAttribute("data", "hello!")
    }
}