package io.brick.springmvc.basic.request

import mu.KLogging
import org.springframework.util.StreamUtils
import java.nio.charset.StandardCharsets
import javax.servlet.annotation.WebServlet
import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@WebServlet(name = "requestBodyStringServlet", urlPatterns = ["/request-body-string"])
class RequestBodyStringServlet : HttpServlet() {
    companion object: KLogging()

    override fun service(request: HttpServletRequest, response: HttpServletResponse) {
        // InputStream으로 String(text/plain) body 데이터를 읽을 수 있다.
        val inputStream = request.inputStream
        val messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8)

        logger.info { "messageBody = ${messageBody}" }

        response.writer.write("ok")
    }
}