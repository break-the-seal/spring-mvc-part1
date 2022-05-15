package io.brick.springmvc.basic

import mu.KLogging
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class LogTestController {
    companion object: KLogging()

    @RequestMapping("/log-test")
    fun logTest(): String {
        val name = "String"

        println("name = ${name}")

        // 등급별로 오름차순 (점점 심각도가 높아짐)
        logger.trace { "trace log = ${name}" }
        logger.debug { "debug log = ${name}" }
        logger.info { "info log = ${name}" }
        logger.warn { "warn log = ${name}" }
        logger.error { "error log = ${name}" }
        // logger 사용시 string에 + 연산 사용하면 안됨 -> 불필요한 연산 발생

        return "ok"
    }
}