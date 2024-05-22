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
    <style type="text/css">
        /* styles.css */
        body {
            background-color: #f8f9fa;
            margin: 0;
            padding: 0;
            display: flex;
            flex-direction: column;
            align-items: center;
        }

        h1 {
            color: red;
            margin-top: 20px;
        }

        h3 {
            color: #333;
        }

        button {
            padding: 10px 20px;
            background-color: #007bff;
            color: white;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            font-size: 16px;
        }

        button a {
            color: white;
            text-decoration: none;
        }

        button:hover {
            background-color: #0056b3;
        }
        .button{
            display: flex;
            justify-content: space-between;
            align-items: center;
            width: 275px;
        }
    </style>
</head>
<body>
<c:import url="/WEB-INF/views/layout/smallheader.jsp"/>
<jsp:include page="/WEB-INF/views/layout/boardmenu.jsp"/>
<h1 style="color: red">로그인 실패</h1>
<c:choose>
    <c:when test="${not empty loginid and not empty loginpw}">
        <h3>ID와 PASSWORD를 잘못 입력하였습니다</h3>
    </c:when>
    <c:when test="${empty loginid or empty loginpw}">
        <h3>ID와 PASSWORD를 입력해 주세요</h3>
    </c:when>
</c:choose>
<div class="button">
    <button style="margin-right: 50px"><a href="<%=request.getContextPath()%>/login"/>로그인창</button>
    <button><a href="<%=request.getContextPath()%>/"/>메인페이지</button>
</div>
</body>
</html>
