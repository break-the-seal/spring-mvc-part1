package io.brick.springmvc.basic.request

import java.util.*
import javax.servlet.annotation.WebServlet
import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@WebServlet(name = "requestHeaderServlet", urlPatterns = ["/request-header"])
class RequestHeaderServlet : HttpServlet() {

    override fun service(request: HttpServletRequest?, response: HttpServletResponse?) {
        request?.let { printStartLine(it) }
        request?.let { printHeaders(it) }
        request?.let { printHeaderUtils(it) }
    }

    //start line 정보
    private fun printStartLine(request: HttpServletRequest) {
        println("--- REQUEST-LINE - start ---")

        println("request.getMethod() = " + request.method) //GET
        println("request.getProtocol() = " + request.protocol) // HTTP/1.1
        println("request.getScheme() = " + request.scheme) //http
        println("request.getRequestURL() = " + request.requestURL) // http://localhost:8080/request-header
        println("request.getRequestURI() = " + request.requestURI) // /request-test
        println("request.getQueryString() = " + request.queryString) //username=hi
        println("request.isSecure() = " + request.isSecure) //https 사용 유무
        println("--- REQUEST-LINE - end ---")
        println()
    }

    //Header 모든 정보
    private fun printHeaders(request: HttpServletRequest) {
        println("--- Headers - start ---")
        request.headerNames.asIterator()
            .forEachRemaining { headerName -> println(headerName + ":" + request.getHeader(headerName)) }
        println("--- Headers - end ---")
        println()
    }

    //Header 편리한 조회
    private fun printHeaderUtils(request: HttpServletRequest) {
        println("--- Header 편의 조회 start ---")
        println("[Host 편의 조회]")
        println("request.getServerName() = " + request.serverName) //Host 헤더
        println("request.getServerPort() = " + request.serverPort) //Host 헤더
        println()

        println("[Accept-Language 편의 조회]")
        request.locales.asIterator()
            .forEachRemaining { locale: Locale -> println("locale = $locale") }
        println("request.getLocale() = " + request.locale)
        println()

        println("[cookie 편의 조회]")
        if (request.cookies != null) {
            for (cookie in request.cookies) {
                println(cookie.name + ": " + cookie.value)
            }
        }
        println()

        println("[Content 편의 조회]")
        println("request.getContentType() = " + request.contentType)
        println("request.getContentLength() = " + request.contentLength)
        println("request.getCharacterEncoding() = " + request.characterEncoding)
        println("--- Header 편의 조회 end ---")
        println()
    }

    //기타 정보

    //기타 정보
    private fun printEtc(request: HttpServletRequest) {
        println("--- 기타 조회 start ---")
        println("[Remote 정보]")
        println("request.getRemoteHost() = " + request.remoteHost) //
        println("request.getRemoteAddr() = " + request.remoteAddr) //
        println("request.getRemotePort() = " + request.remotePort) //
        println()

        println("[Local 정보]")
        println("request.getLocalName() = " + request.localName) //
        println("request.getLocalAddr() = " + request.localAddr) //
        println("request.getLocalPort() = " + request.localPort) //
        println("--- 기타 조회 end ---")
        println()
    }
}