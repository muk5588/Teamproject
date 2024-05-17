<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: c
  Date: 24. 5. 17.
  Time: 오후 12:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h3>신고 내역</h3>
<table>
    <h3>게시물 신고</h3>
    <tr>
        <th>신고번호</th>
        <th>신고내용</th>
        <th>신고일자</th>
        <th>신고종류</th>
        <th>작성된글</th>
        <th>닉네임</th>
        <th></th>
    </tr>
<c:forEach items="${boardlist}" var="list">
<tr>
    <td>${list.boardReportNo}</td>
    <td>${list.reportContent}</td>
    <td><fmt:formatDate value="${list.reportDate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
    <td>${list.reportType}</td>
    <td>${list.content}</td>
    <td>${list.nickname}</td>
    <td><a class='btn-fill' href="/report/deleteReport?reportno=${list.boardReportNo}"><button>삭제</button></a></td>
</tr>
</c:forEach>
</table>
<h3>댓글 신고</h3>
<table>
<tr>
    <th>신고번호</th>
    <th>신고내용</th>
    <th>신고일자</th>
    <th>신고종류</th>
    <th>작성된글</th>
    <th>닉네임</th>
    <th></th>
</tr>
<c:forEach items="${commlist}" var="list">
    <tr>
        <td>${list.commReportNo}</td>
        <td>${list.content}</td>
        <td><fmt:formatDate value="${list.commReportDate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
        <td>${list.commReport}</td>
        <td>${list.commContent}</td>
        <td>${list.nickname}</td>
        <td><a class='btn-fill' href="/report/deleteReport?reportno=${list.boardReportNo}"><button>삭제</button></a></td>
    </tr>
</c:forEach>
</table>

</body>
</html>
