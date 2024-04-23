<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Q&A Detail</title>
</head>
<body>
<h1>Q&A 상세 정보</h1>
<div>
    <h2>${qanda.title}</h2>
    <p>글 번호: ${qanda.id}</p>
    <p>글쓴이: ${qanda.author}</p>
    <p>조회수: ${qanda.viewCount}</p>
    <p>작성 날짜: ${qanda.date}</p>
    <p>내용: ${qanda.content}</p> <!-- content 필드가 DTO에 추가되어 있어야 합니다. -->
</div>
<a href="${pageContext.request.contextPath}/qanda/edit/${qanda.id}">수정하기</a>
<a href="${pageContext.request.contextPath}/qanda/delete/${qanda.id}">삭제하기</a>
<a href="${pageContext.request.contextPath}/qanda">목록으로</a>
</body>
</html>
