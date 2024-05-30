<%--
  Created by IntelliJ IDEA.
  User: c
  Date: 2024-03-25
  Time: 오전 9:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="icon" href="<%=request.getContextPath()%>/resources/img/20191208094528217881320965.png">
</head>
<body>
<h1>로그인 성공</h1>
<hr>
<h3>로그인 상태 : ${login }</h3>
<h3>로그인 아이디 : ${loginid }</h3>
<button><a href="<%=request.getContextPath()%>/"/>로그인창</button>
</body>
</html>
