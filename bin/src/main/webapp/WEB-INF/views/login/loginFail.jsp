<%--
  Created by IntelliJ IDEA.
  User: c
  Date: 2024-03-25
  Time: 오전 9:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
    <link rel="icon" href="resources/img/favicon.ico">
</head>
<body>
<h1 style="color: red">로그인 실패</h1>
<hr>

<c:if test="${not empty loginid and not empty loginpw}">

<h3>ID와 PASSWORD를 잘못 입력하였습니다</h3>
<button><a href="<%=request.getContextPath()%>/"/>로그인창</button>
</c:if>

<c:if test="${empty loginid or empty loginpw}">
    <h3>ID와 PASSWORD를 입력해 주세요</h3>
    <button><a href="<%=request.getContextPath()%>/login"/>로그인창</button>
</c:if>

<a href = "<c:url value="/"/>"><button>메인페이지</button></a>
</body>
</html>
