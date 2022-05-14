package io.brick.springmvc.basic.request

import mu.KLogging
import org.springframework.http.HttpMethod
import org.springframework.util.MultiValueMap
import org.springframework.web.bind.annotation.CookieValue
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.Locale
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@RestController
class RequestHeaderController {

    companion object: KLogging()

    @RequestMapping("/headers")
    fun headers(
        request: HttpServletRequest,
        response: HttpServletResponse,
        httpMethod: HttpMethod,
        locale: Locale,
        @RequestHeader headerMap: MultiValueMap<String, String>,    // 하나의 key에 여러값을 받기위해 사 용
        @RequestHeader("host") host: String,
        @CookieValue(value = "myCookie", required = true) cookie: String
    ): String {

        logger.info("request={}", request);
        logger.info("response={}", response);
        logger.info("httpMethod={}", httpMethod);
        logger.info("locale={}", locale);
        logger.info("headerMap={}", headerMap);
        logger.info("header host={}", host);
        logger.info("myCookie={}", cookie);

        return "ok";
    }
}