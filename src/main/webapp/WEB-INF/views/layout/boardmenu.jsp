<%--
  Created by IntelliJ IDEA.
  User: 몰랑에른
  Date: 24. 5. 9.
  Time: 오후 4:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<div class="Header-Search">
    <input type="text" placeholder="검색어를 입력하세요">
    <img src="/resources/img/mainPage/search.png" alt="검색" id="search-icon">
</div>
<div class="Header-Button">
    <ul class="Header-Button">
        		<li><a href="/board/list?categoryNo=11&curPage=${curPage}">여행지 정보 - 여행 이야기 공유</a></li>
				<li><a href="/board/list?categoryNo=12&curPage=${curPage}">여행지 정보 - 여행 팁 및 권고사항</a></li>
				<li><a href="/board/list?categoryNo=13&curPage=${curPage}">여행지 정보 - 다양한 여행 목적지 추천</a></li>
				<li><a href="/board/list?categoryNo=21&curPage=${curPage}">사진 갤러리 - 여행지 사진</a></li>
				<li><a href="/board/list?categoryNo=31&curPage=${curPage}">지역별 여행 - 지역 리스트</a></li>
				<li><a href="/board/list?categoryNo=32&curPage=${curPage}">지역별 여행 - 여행지 선택 페이지</a></li>
				<li><a href="/board/list?categoryNo=41&curPage=${curPage}">여행 Q&A - 꿀팁게시판</a></li>
				<li><a href="/board/list?categoryNo=42&curPage=${curPage}">여행 Q&A - 자유게시판</a></li>
				<li><a href="/board/list?categoryNo=43&curPage=${curPage}">여행 Q&A - 공지사항</a></li>
				<li><a href="/board/list?categoryNo=51&curPage=${curPage}">이벤트 - 이벤트 공지사항</a></li>
				<li><a href="/board/list?categoryNo=52&curPage=${curPage}">이벤트 - 이벤트</a></li>
				<li><a href="/board/list?categoryNo=61&curPage=${curPage}">여행관련 상품 - 여행관련 상품</a></li>
    </ul>
</div>
</body>
</html>