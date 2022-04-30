package io.brick.springmvc.basic.request

import mu.KLogging
import java.util.*
import javax.servlet.annotation.WebServlet
import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@WebServlet(name = "requestHeaderServlet", urlPatterns = ["/request-header"])
class RequestHeaderServlet : HttpServlet() {
    companion object : KLogging() {
        const val NEW_LINE = "\n"
    }

    override fun service(request: HttpServletRequest, response: HttpServletResponse) {
        printStartLine(request)
        printHeaders(request)
        printHeaderUtils(request)
        printEtc(request)
    }

    private fun printStartLine(request: HttpServletRequest) {
        /**
         * request.getMethod(): GET
         * request.getProtocol(): HTTP/1.1
         * request.getScheme(): http
         * request.getRequestURL(): http://localhost:8080/request-header
         * request.getRequestURI(): /request-header
         * request.getQueryString(): username=hi
         * request.isSecure(): https 사용 유무
         */
        logger.info {
            """
                |
                |--- REQUEST-LINE - start ---
                |request.getMethod() = ${request.method}
                |request.getProtocol() = ${request.protocol}
                |request.getScheme() = ${request.scheme}
                |request.getRequestURL() = ${request.requestURL}
                |request.getRequestURI() = ${request.requestURI}
                |request.getQueryString() = ${request.queryString}
                |request.isSecure() = ${request.isSecure}
                |--- REQUEST-LINE - end ---
                |
            """.trimMargin()
        }
    }

    //Header 모든 정보
    private fun printHeaders(request: HttpServletRequest) {
        val headerNameList: MutableList<String> = mutableListOf()

//        val headerNames = request.headerNames
//        while (headerNames.hasMoreElements()) {
//            val headerName = headerNames.nextElement()
//            logger.info { "headerName = ${headerName}" }
//        }

        // request.getHeader("headerName") 으로 header를 뽑아 쓸 수 있다.

        request.headerNames.asIterator()
            .forEachRemaining { headerName: String -> headerNameList.add("headerName: ${headerName}") }

        logger.info {
            """
                |
                |--- Headers - start ---
                |${headerNameList.joinToString(NEW_LINE)}
                |--- Headers - end ---
                |
        """.trimMargin()
        }
    }

    private fun printHeaderUtils(request: HttpServletRequest) {
        val localeList: MutableList<String> = mutableListOf()
        request.locales.asIterator().forEachRemaining { locale: Locale -> localeList.add("locale = ${locale}") }

        val cookieList: MutableList<String> = mutableListOf()
        request.cookies?.let { cookies ->
            cookies.forEach {
                cookieList.add("${it.name}: ${it.value}")
            }
        }

        /**
         * request.getServerName(): Host 헤더
         * request.getServerPort(): Host 헤더
         */
        logger.info {
            """
            |
            |--- Header 편의 조회 start ---
            |[Host 편의 조회]
            |request.getServerName() = ${request.serverName}
            |request.getServerPort() = ${request.serverPort}
            |
            |[Accept-Language 편의 조회]
            |${localeList.joinToString(NEW_LINE)}
            |request.getLocale() = ${request.locale}
            |
            |[cookie 편의 조회]
            |${cookieList.joinToString(NEW_LINE)}
            |
            |[Content 편의 조회]
            |request.getContentType() = ${request.contentType}
            |request.getContentLength() = ${request.contentLength}
            |request.getCharacterEncoding() = ${request.characterEncoding}
            |--- Header 편의 조회 end ---
            |
        """.trimMargin()
        }
    }

    //기타 정보
    private fun printEtc(request: HttpServletRequest) {
        /**
         * remoteHost: 요청을 보낸 쪽의 정보
         * local: 현재 서버에 대한 정보
         */
        logger.info {
            """
            |
            |--- 기타 조회 start ---
            |[Remote 정보]
            |request.getRemoteHost() = ${request.remoteHost}
            |request.getRemoteAddr() = ${request.remoteAddr}
            |request.getRemotePort() = ${request.remotePort}
            |
            |[Local 정보]
            |request.getLocalName() = ${request.localName}
            |request.getLocalAddr() = ${request.localAddr}
            |request.getLocalPort() = ${request.localPort}
            |--- 기타 조회 end ---
            |
        """.trimMargin()
        }
    }
}