<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<h1>메시지함</h1>
<hr>
<div id="content">
<table>
<tr>
	<th>보낸이</th>
	<th>내용</th>
	<th>전송일</th>
	<th>읽음 여부</th>
	<th>저장 여부</th>
</tr>
<c:forEach items="${list}" var="list">
<tr>
	<td class="sender">${list.sender}</td>
	<td class="content">${list.content}</td>
	<td class="date">
		<fmt:formatDate value="${list.sendDate }" pattern="yyyy-MM-dd HH:mm:ss"/>
	</td>
	<td class="read">${list.read}</td>
	<td class="save">${list.save}</td>
</tr>
</c:forEach>
</table>

</div>
<a href="/"><button>홈으로</button></a><br>
</body>
</html>