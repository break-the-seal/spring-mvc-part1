<%@ page import="io.brick.springmvc.domain.member.MemberRepository" %>
<%@ page import="io.brick.springmvc.domain.member.Member" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    // JSP는 서블릿으로 변환되기 때문에 -> request, response는 자동으로 사용 가능
    MemberRepository memberRepository = MemberRepository.Companion.getInstance();
    String username = request.getParameter("username");
    int age = Integer.parseInt(request.getParameter("age"));

    Member member = new Member(0, username, age);
    memberRepository.save(member);
%>
<html>
<head>
    <title>Title</title>
</head>
<body>
<ul>
    <li>id=<%=member.getId()%></li>
    <li>username=<%=member.getUsername()%></li>
    <li>age=<%=member.getAge()%></li>
</ul>
<a href="/index.html">메인</a>
</body>
</html>
