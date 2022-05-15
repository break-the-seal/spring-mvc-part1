package io.brick.springmvc.basic.request

import io.brick.springmvc.basic.HelloData
import mu.KLogging
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseBody
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Controller
class RequestParamController {
    companion object: KLogging()

    @RequestMapping("/request-param-v1")
    fun requestParamV1(request: HttpServletRequest, response: HttpServletResponse) {
        val username = request.getParameter("username")
        val strAge = request.getParameter("age")
        val age = if(strAge.isNotEmpty()) strAge.toInt() else 0
        logger.info { "username=${username}, age=${age}" }

        response.writer.write("ok")
    }

    @ResponseBody
    @RequestMapping("/request-param-v2")
    fun requestParamV2(
        @RequestParam("username") username: String,
        @RequestParam("age") age: Int
    ): String {
        logger.info { "username=${username}, age=${age}" }

        return "ok"
    }

    @ResponseBody
    @RequestMapping("/request-param-v3")
    fun requestParamV3(
        @RequestParam username: String,
        @RequestParam age: Int
    ): String {
        logger.info { "username=${username}, age=${age}" }

        return "ok"
    }

    @ResponseBody
    @RequestMapping("/request-param-v4")
    fun requestParamV4(
        username: String,
        age: Int
    ): String {
        logger.info { "username=${username}, age=${age}" }

        return "ok"
    }

    @ResponseBody
    @RequestMapping("/request-param-required")
    fun requestParamRequired(
        @RequestParam username: String,
        @RequestParam(required = false) age: Int?
    ): String {
        // 빈문자열도 그대로 들어올 수 있음
        logger.info { "username=${username}, age=${age}" }

        return "ok"
    }

    @ResponseBody
    @RequestMapping("/request-param-default")
    fun requestParamDefault(
        @RequestParam(defaultValue = "guest") username: String,
        @RequestParam(defaultValue = "-1") age: Int
    ): String {
        // 빈 문자열까지 default로 처리해준다.
        logger.info { "username=${username}, age=${age}" }

        return "ok"
    }

    @ResponseBody
    @RequestMapping("/request-param-map")
    fun requestParamMap(
        @RequestParam paramMap: Map<String, Any>
    ): String {
        logger.info { "username=${paramMap["username"]}, age=${paramMap["age"]}" }

        return "ok"
    }

    @ResponseBody
    @RequestMapping("/model-attribute-v1")
    fun modelAttributeV1(
        @ModelAttribute helloData: HelloData
    ): String {
        logger.info { "username = ${helloData.username}, age = ${helloData.age}" }
        logger.info { "helloData = ${helloData}" }

        return "ok"
    }

    @ResponseBody
    @RequestMapping("/model-attribute-v2")
    fun modelAttributeV2(
        helloData: HelloData
    ): String {
        logger.info { "username = ${helloData.username}, age = ${helloData.age}" }
        logger.info { "helloData = ${helloData}" }

        return "ok"
    }
}