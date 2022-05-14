package io.brick.springmvc.basic

import mu.KLogging
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class LogTestController {

    companion object: KLogging()

    @RequestMapping("/log-test")
    fun logTest(): String {
        var name = "spring"

        // log level
        logger.trace("trace log = {}", name)
        logger.debug("debug log = {}", name)
        logger.info("info log = {}", name)
        logger.warn("warn log = {}", name)
        logger.error("error log = {}", name)
        return "ok"
    }
}