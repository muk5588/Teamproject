<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: c
  Date: 24. 5. 1.
  Time: 오후 12:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="icon" href="<%=request.getContextPath()%>/resources/img/20191208094528217881320965.png">
    <link rel="stylesheet" href="/resources/css/menu/update.css">

</head>
<body>
<c:import url="/WEB-INF/views/layout/header.jsp"/>
<div class="container">
    <button onclick="location.href='../menu/menuList'">목록으로</button>

    <table class='w-pct60'>
    <tr>
        <th>게시판종류번호</th>
        <th>게시판명</th>
        <th>등급번호</th>
        <th>수정</th>
    </tr>
    <!-- for(꺼낸 배열 변수를 담을 새로운 변수 (String x) : 배열 변수(list)) -->
    <!-- items : 배열 변수 -->
    <!-- var : 꺼낸 배열 변수를 담을 새로운 변수 -->
    <form action="/menu/categoryUpdate" method="post">
    	<input hidden="hidden" name="categoryNo" value="${category.categoryNo }">
        <tr>
            <td>${category.categoryNo }</td>
            <td>${category.categoryName }</td>
            <td>
                <select class="c" name="gradeNo" id="gradeNo" placeholder="등급을 선택해 주세요" class="form-option" >
                    <option value="${category.gradeNo}">=== 선택 ===</option>
                    <c:forEach var="grade" items="${list }">
                        <option value="${grade.gradeno}">${grade.gradename}</option>
                    </c:forEach>
                </select>
            </td>
            <td>
                <button onclick="location.href='/menu/categoryUpdate'">수정</button>
            </td>
        </tr>
    </form>
</table>
</div>
</body>
</html>
