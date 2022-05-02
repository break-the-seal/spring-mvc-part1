package io.brick.springmvc

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.boot.web.servlet.ServletComponentScan

@ServletComponentScan   // servlet 자동 등록
@SpringBootApplication
class SpringMvcPart1Application

fun main(args: Array<String>) {
    runApplication<SpringMvcPart1Application>(*args)
}