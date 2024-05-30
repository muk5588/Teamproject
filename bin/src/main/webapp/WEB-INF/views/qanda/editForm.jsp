<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edit Q&A</title>
    <link rel="icon" href="resources/img/favicon.ico">
</head>
<body>
<h1>글 수정</h1>
<form action="${pageContext.request.contextPath}/qanda/edit" method="post">
    <input type="hidden" name="id" value="${qanda.id}"/>
    <label>제목: <input type="text" name="title" value="${qanda.title}"/></label><br>
    <label>글쓴이: <input type="text" name="author" value="${qanda.author}"/></label><br>
    <label>내용: <textarea name="content" rows="5" cols="30">${qanda.content}</textarea></label><br>
    <input type="submit" value="저장하기"/>
</form>
<a href="${pageContext.request.contextPath}/qanda/${qanda.id}">취소</a>
</body>
</html>