<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="io.brick.springmvc.domain.member.MemberRepository" %>
<%@ page import="io.brick.springmvc.domain.member.Member" %>
<%
    // request, response 사용 가능
    MemberRepository memberRepository = MemberRepository.Companion.getInstance();

    System.out.println("service");
    String username = request.getParameter("username");
    int age = Integer.parseInt(request.getParameter("age"));

    Member member = new Member(0L, username, age);
    memberRepository.save(member);
%>
<html>
<head>
    <title>Title</title>
</head>
<body>
성공
<ul>
    <li>id = <%=member.getId()%></li>
    <li>username = <%=member.getUsername()%></li>
    <li>age = <%=member.getAge()%></li>
</ul>
<a href="/index.html">메인</a>
</body>
</html>
