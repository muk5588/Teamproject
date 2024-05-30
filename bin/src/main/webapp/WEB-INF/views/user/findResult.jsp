<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: c
  Date: 24. 4. 16.
  Time: 오후 2:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="icon" href="resources/img/favicon.ico">
</head>
<body>
<c:if test="${value =='id'}">
    <div class="result-box">
        <c:choose>
            <c:when test="${empty finduserid}">
                <p class="inquiry">조회결과가 없습니다.</p>
            </c:when>
            <c:otherwise>
                <p class="title">회원님의 아이디 입니다</p>
                <p>${finduserid.userid}</p>

            </c:otherwise>
        </c:choose>
    </div>
</c:if>
<c:if test="${value =='pw'}">
<p class="title">회원님의 임시 비밀번호 입니다</p>
<div class="result-box">
    <p>${newPwd}</p>
</c:if>
<br>
<button><a class="btn-fill" href="<%=request.getContextPath()%>/login">로그인</a></button>
<button><a class="btn-fill" href="<%=request.getContextPath()%>/">홈으로</a></button>
</body>
</html>
