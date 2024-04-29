<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:import url="/WEB-INF/views/layout/header.jsp"/>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
<style type="text/css">
</style>
</head>
<body>

<div class="wrap mx-auto">

<header class="header text-center">
	<h1>Spring Board</h1>
</header>

	<div class="container">
	
	<h1>수정하기</h1>
	
	
	<form action="./update" method="post">
	
	<hr>
	
	<table class="table table-striped table-hover table-sm">
		<tr>
			<th>글 번호</th>
			<th>제목</th>
			<th>본문</th>
			<th>작성자 아이디</th>
			<th>작정자 닉네임</th>
			<th>조회수</th>
			<th>최초작성일</th>
		</tr>
		
		<tr>
				<td class="no"> ${board.boardNo } <input type="text" hidden="hidden" value="${board.boardNo }" name="boardNo"> </td>
				<td class="title"><input type="text" value="${board.title }" name="title"></td>
				<td class="content"><input type="text" value="${board.content }" name="content"></td>
				<td class="nick">${board.nickName }</td>
				<td class="hit">${board.boardView }</td>
				<td class="date">
					<fmt:formatDate value="${board.createDate }" pattern="yyyy-MM-dd"/>
				</td>
		</tr>
	</table>
	
	<div id="button">
	<button id="btnUpdate">수정 적용</button>
	<a href="./list?curPage=${curPage }"><button>목록으로</button></a>
	</div>
	
	</form>
	</div> <!-- .container End -->
	
	
	
	
	
	
	
	
	
	
<c:import url="/WEB-INF/views/layout/footer.jsp"/>
