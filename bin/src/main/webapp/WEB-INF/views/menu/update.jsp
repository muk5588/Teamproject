<%--
  Created by IntelliJ IDEA.
  User: c
  Date: 2024-04-09
  Time: 오전 11:19
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
<table class='w-pct60'>
    <tr>
        <th class='w-px60'>회원번호</th>
        <th class='w-px200'>회원이름</th>
        <th>등급번호</th>
        <th>수정</th>
    </tr>
    <!-- for(꺼낸 배열 변수를 담을 새로운 변수 (String x) : 배열 변수(list)) -->
    <!-- items : 배열 변수 -->
    <!-- var : 꺼낸 배열 변수를 담을 새로운 변수 -->
    <form action="/menu/menuUpdate?userno=${dto.userno}" method="post">
        <tr>
            <td>${dto.userno }</td>
            <td>${dto.userid }</td>
            <td><input type="text" value="${dto.gradeno }" style="width: 50px"></td>
            <td>
                <button onclick="location.href='/menu/menuUpdate?userno=${dto.userno}'">수정</button>
            </td>
            </a>
        </tr>
    </form>
</table>
<div>
    <table>
        <tr>
            <th>등급명</th>
            <th>등급번호</th>
            <th>추가사항</th>
        </tr>
        <c:forEach var="Menu" items="${list}">
            <tr>
                <td>${Menu.gradename}</td>
                <td>${Menu.gradeno}</td>
                <td>${Menu.comm}</td>
            </tr>
        </c:forEach>
    </table>
</div>

</body>
</html>
