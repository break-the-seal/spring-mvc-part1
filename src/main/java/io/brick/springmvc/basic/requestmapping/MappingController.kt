package io.brick.springmvc.basic.requestmapping

import mu.KLogging
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*

@RestController
class MappingController {
    companion object : KLogging()

    @RequestMapping(value = ["/hello-basic", "/hello-basic2"])
    fun helloBasic(): String {
        logger.info { "helloBasic" }
        return "ok"
    }

    @RequestMapping(value = ["/mapping-get-v1"], method = [RequestMethod.GET])
    fun mappingGetV1(): String {
        logger.info { "mappingGetV1" }
        return "ok"
    }

    @GetMapping("/mapping-get-v2")
    fun mappingGetV2(): String {
        logger.info { "mappingGetV2" }
        return "ok"
    }

    @GetMapping("/mapping/users/{userId}/orders/{orderId}")
    fun mappingPath(@PathVariable("userId") userId: String, @PathVariable("orderId") orderId: Long): String {
        logger.info { "mappingPath userId=${userId}, orderId=${userId}" }
        return "ok"
    }

    /**
     * 파라미터로 추가 매핑(java 표현) -> @RequestParam으로 대체
     * params="mode"
     * params="!mode"
     * params="mode=debug"
     * params="mode!=debug"
     * params={"mode=debug","data=good"}
     */
    @GetMapping(value = ["/mapping-param"], params = ["mode"])
    fun mappingParam(): String {
        logger.info { "mappingParam" }
        return "ok"
    }

    // 위 파라미터 추가 매핑 형식과 같다.
    @GetMapping(value = ["/mapping-header"], headers = ["mode=debug"])
    fun mappingHeader(): String {
        logger.info { "mappingHeader" }
        return "ok"
    }

    /**
     * Content-Type 헤더 기반 추가 매핑 Media Type
     * consumes="application/json"
     * consumes="!application/json"
     * consumes="application/ *"
     * consumes="*\/ *"
     * MediaType.APPLICATION_JSON_VALUE
     */
    @PostMapping(value = ["/mapping-consume"], consumes = [MediaType.APPLICATION_JSON_VALUE])
    fun mappingConsumes(): String {
        logger.info { "mappingConsumes" }
        return "ok"
    }

    /**
     * Accept 헤더 기반 Media Type
     * produces = "text/html"
     * produces = "!text/html"
     * produces = "text/ *"
     * produces = "*\/ *"
     */
    @PostMapping(value = ["/mapping-produce"], produces = [MediaType.TEXT_HTML_VALUE])
    fun mappingProduces(): String {
        logger.info { "mappingProduces" }
        return "ok"
    }
}