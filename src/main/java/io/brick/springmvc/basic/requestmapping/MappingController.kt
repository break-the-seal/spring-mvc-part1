package io.brick.springmvc.basic.requestmapping

import mu.KLogging
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*


@RestController
class MappingController {

    companion object: KLogging()

    @RequestMapping("/hello-basic")
    fun helloBasic(): String {
        logger.info("hello basic")
        return "ok"
    }

    @RequestMapping("/mapping-get-v1", method = [RequestMethod.GET])
    fun mappingGetV1(): String {
        logger.info("mapping-get-v1")
        return "ok"
    }

    /**
     * 편리한 축약 Annotation
     * @GetMapping
     * @PostMapping
     * @PutMapping
     * @DeleteMapping
     * @PatchMapping
     */
    @GetMapping("/mapping-get-v2")
    fun mappingGetV2(): String {
        logger.info("mapping-get-v2")
        return "ok"
    }

    /**
     * PathVariable(경로변수) 사용
     * 변수명이 같으면 생략 가능
     */
    @GetMapping("/mapping/{userId}")
    fun mappingPath(@PathVariable("userId") userId: String): String {
        logger.info("mappingPath userId = {}", userId)
        return "ok"
    }

    /**
     * PathVariable 사용 다중
     */
    @GetMapping("/mapping/users/{userId}/orders/{orderId}")
    fun mappingPath(@PathVariable userId: String?, @PathVariable orderId: Long?): String? {
        logger.info("mappingPath userId={}, orderId={}", userId, orderId)
        return "ok"
    }

    /**
     * 파라미터로 추가 매핑
     *  - params="mode",
     *  - params="!mode"
     *  - params="mode=debug"
     *  - params="mode!=debug" (! = )
     *  - params = {"mode=debug","data=good"}
     */
    @GetMapping(value = ["/mapping-param"], params = ["mode=debug"])
    fun mappingParam(): String? {
        logger.info("mappingParam")
        return "ok"
    }

    /**
     * 특정 헤더로 추가 매핑
     *  - headers="mode",
     *  - headers="!mode"
     *  - headers="mode=debug"
     *  - headers="mode!=debug" (! = )
     */
    @GetMapping(value = ["/mapping-header"], headers = ["mode=debug"])
    fun mappingHeader(): String? {
        logger.info("mappingHeader")
        return "ok"
    }

    /**
     * Content-Type 헤더 기반 추가 매핑 Media Type
     *  - consumes="application/json"
     *  - consumes="!application/json"
     *  - consumes="application/ *"
     *  - consumes="*\/ *"
     * MediaType.APPLICATION_JSON_VALUE
     */
    @PostMapping(value = ["/mapping-consume"], consumes = [MediaType.APPLICATION_JSON_VALUE])
    fun mappingConsumes(): String? {
        logger.info("mappingConsumes")
        return "ok"
    }

    /**
     *
     * Accept 헤더 기반 Media Type
     *  - produces = "text/html"
     *  - produces = "!text/html"
     *  - produces = "text/ *"
     *  - produces = "*\/ *"
     */
    @PostMapping(value = ["/mapping-produce"], produces = [MediaType.TEXT_HTML_VALUE])
    fun mappingProduces(): String? {
        logger.info("mappingProduces")
        return "ok"
    }
}