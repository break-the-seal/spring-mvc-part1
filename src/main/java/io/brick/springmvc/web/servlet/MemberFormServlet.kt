package io.brick.springmvc.web.servlet

import io.brick.springmvc.domain.member.MemberRepository
import javax.servlet.annotation.WebServlet
import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@WebServlet(name = "memberFormServlet", urlPatterns = ["/servlet/members/new-form"])
class MemberFormServlet: HttpServlet() {
    private val memberRepository = MemberRepository.getInstance()

    override fun service(request: HttpServletRequest, response: HttpServletResponse) {
        response.contentType = "text/html"
        response.characterEncoding = "utf-8"

        val writer = response.writer

        writer.write("""
            |<!DOCTYPE html>
            |<html>
            |<head>
            |   <meta charset="UTF-8">
            |   <title>Title</title>
            |</head>
            |<body>
            |   <form action="/servlet/members/save" method="post">
            |       username: <input type="text" name="username" />
            |       age:      <input type="text" name="age" />
            |       <button type="submit">전송</button>
            |   </form>
            |</body>
            |</html>
        """.trimMargin()
        )
    }
}