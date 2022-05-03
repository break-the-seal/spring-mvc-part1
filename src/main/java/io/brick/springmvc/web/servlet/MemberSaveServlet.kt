package io.brick.springmvc.web.servlet

import io.brick.springmvc.domain.member.Member
import io.brick.springmvc.domain.member.MemberRepository
import mu.KLogging
import javax.servlet.annotation.WebServlet
import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@WebServlet(name = "memberSaveServlet", urlPatterns = ["/servlet/members/save"])
class MemberSaveServlet: HttpServlet() {
    companion object: KLogging()

    private val memberRepository = MemberRepository.getInstance()

    override fun service(request: HttpServletRequest, response: HttpServletResponse) {
        logger.info { "MemberSaveServlet.service" }
        val username = request.getParameter("username")
        val age = request.getParameter("age").toInt()

        val member = Member(username = username, age = age)
        memberRepository.save(member)
        
        response.contentType = "text/html"
        response.characterEncoding = "utf-8"

        val writer = response.writer
        writer.write(
            """
                |<html>
                |<head>
                |    <meta charset="UTF-8">
                |</head>
                |<body>
                |성공
                |<ul>
                |    <li>id=${member.id}</li>
                |    <li>username=${member.username}</li>
                |    <li>age=${member.age}</li>
                |</ul>
                |<a href="/index.html">메인</a>
                |</body>
                |</html>
            """.trimMargin()
        )
    }
}