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

    /**
     * 반환 타입이 없으면서 이렇게 응답에 값을 직접 집어넣으면, view 조회X
     */
    @RequestMapping("/request-param-v1")
    fun requestParamV1(
        request: HttpServletRequest,
        response: HttpServletResponse
    ) {
        val username = request.getParameter("username")
        val age = request.getParameter("age").toInt()
        logger.info("username={}, age={}", username, age)

        response.writer.write("ok")
    }

    /**
     * @RequestParam 사용
     *  - 파라미터 이름으로 바인딩
     * @ResponseBody 추가
     *  - View 조회를 무시하고, HTTP message body에 직접 해당 내용 입력 */
    @ResponseBody
    @RequestMapping("/request-param-v2")
    fun requestParamV2(
        @RequestParam("username") username: String,
        @RequestParam("age") age: Int
    ): String {
        logger.info("username={}, age={}", username, age)
        return "ok"
    }

    @ResponseBody
    @RequestMapping("/request-param-v3")
    fun requestParamV3(
        @RequestParam username: String,
        @RequestParam age: Int
    ): String {
        logger.info("username={}, age={}", username, age)
        return "ok"
    }

    @ResponseBody
    @RequestMapping("/request-param-v4")
    fun requestParamV4(
        username: String,
        age: Int
    ): String {
        logger.info("username={}, age={}", username, age)
        return "ok"
    }

    /**
     * @RequestParam.required
     * /request-param -> username이 없으므로 예외 *
     *
     * 주의!
     * /request-param?username= -> 빈문자로 통과 *
     *
     * 주의!
     * /request-param
     * int age -> null을 int에 입력하는 것은 불가능, 따라서 Integer 변경해야 함(또는 다음에 나오는 defaultValue 사용)
     */
    @ResponseBody
    @RequestMapping("/request-param-required")
    fun requestParamRequired(
        @RequestParam(required = true) username: String,
        @RequestParam(required = false) age: Int
    ): String {
        logger.info("username={}, age={}", username, age)
        return "ok"
    }

    /**
     * @RequestParam
     * - defaultValue 사용 *
     * 참고: defaultValue는 빈 문자의 경우에도 적용 * /request-param?username=
     */
    @ResponseBody
    @RequestMapping("/request-param-default")
    fun requestParamDefault(
        @RequestParam(required = true, defaultValue = "guest") username: String?,
        @RequestParam(required = false, defaultValue = "-1") age: Int
    ): String? {
        logger.info("username={}, age={}", username, age)
        return "ok"
    }

    /**
     * @RequestParam Map, MultiValueMap
     *  - Map(key=value)
     *  - MultiValueMap(key=[value1, value2, ...] ex) (key=userIds, value=[id1, id2])
     */
    @ResponseBody
    @RequestMapping("/request-param-map")
    fun requestParamMap(@RequestParam paramMap: Map<String?, Any?>): String? {
        logger.info("username={}, age={}", paramMap["username"], paramMap["age"])
        return "ok"
    }

    /**
     * @ModelAttribute 사용
     * 참고: model.addAttribute(helloData) 코드도 함께 자동 적용됨, 뒤에 model을 설명할 때 자세히 설명
     */
    @ResponseBody
    @RequestMapping("/model-attribute-v1")
    fun modelAttributeV1(@ModelAttribute helloData: HelloData): String {
        logger.info("username={}, age={}", helloData.username, helloData.age)
        return "ok"
    }

    @ResponseBody
    @RequestMapping("/model-attribute-v2")
    fun modelAttributeV2(helloData: HelloData): String {
        logger.info("username={}, age={}", helloData.username, helloData.age)
        return "ok"
    }
}