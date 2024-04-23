<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Q&A 게시판</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
</head>
<body>
<jsp:include page="/WEB-INF/views/layout/header.jsp" />

<div class="container mt-3">
    <h1>Q&A 게시판</h1>
    <table class="table table-striped table-hover">
        <thead>
            <tr>
                <th scope="col">글 번호</th>
                <th scope="col">제목</th>
                <th scope="col">글쓴이</th>
                <th scope="col">조회수</th>
                <th scope="col">작성 날짜</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="qanda" items="${qandas}">
                <tr>
                    <td>${qanda.id}</td>
                    <td><a href="/qanda/detail/${qanda.id}">${qanda.title}</a></td>
                    <td>${qanda.userno}</td>
                    <td>${qanda.view}</td>
                    <td><fmt:formatDate value="${qanda.createDate}" pattern="yyyy-MM-dd HH:mm"/></td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</div>

<jsp:include page="/WEB-INF/views/layout/footer.jsp" />
</body>
</html>
