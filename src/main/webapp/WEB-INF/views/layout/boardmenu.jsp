<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>네이버 쪽지함 스타일 메뉴</title>
    <link rel="stylesheet" type="text/css" href="/resources/css/mainPage/boardMenu.css">
</head>
<body>
<div class="Header-Search">
    <input type="text" placeholder="검색어를 입력하세요">
    <img src="/resources/img/mainPage/search.png" alt="검색" id="search-icon">
</div>


<ul class="nav">
    <li class="menu">
        <a href="/">여행지정보</a>
        <ul>
            <li><a href="/board/list?categoryNo=11&curPage=${curPage}">여행 이야기 공유</a></li>
            <li><a href="/board/list?categoryNo=12&curPage=${curPage}">여행 팁 및 권고사항</a></li>
            <li><a href="/board/list?categoryNo=13&curPage=${curPage}">다양한 여행 목적지 추천</a></li>
        </ul>
    </li>
    <li><a href="/board/list?categoryNo=21&curPage=${curPage}">사진 갤러리 - 여행지 사진</a></li>
    <li class="menu">
        <a href="/board/list?categoryNo=31&curPage=${curPage}">지역별 여행</a>
        <ul>
            <li><a href="/board/list?categoryNo=31&curPage=${curPage}">지역 리스트</a></li>
            <li><a href="/board/list?categoryNo=32&curPage=${curPage}">여행지 선택 페이지</a></li>
        </ul>
    </li>
    <li class="menu">
        <a href="/">여행 Q&A</a>
        <ul>
            <li><a href="/board/list?categoryNo=41&curPage=${curPage}">꿀팁게시판</a></li>
            <li><a href="/board/list?categoryNo=42&curPage=${curPage}">자유게시판</a></li>
            <li><a href="/board/list?categoryNo=43&curPage=${curPage}">공지사항</a></li>
        </ul>
    </li>
    <li class="menu">
        <a href="/">이벤트</a>
        <ul>
            <li><a href="/board/list?categoryNo=51&curPage=${curPage}">이벤트 공지사항</a></li>
            <li><a href="/board/list?categoryNo=52&curPage=${curPage}">이벤트</a></li>
        </ul>
    </li>
    <li><a href="/shop/">여행관련 상품</a></li>
</ul>

<div style="clear: both; overflow: hidden;"></div>
<hr class="line">

</body>
</html>
