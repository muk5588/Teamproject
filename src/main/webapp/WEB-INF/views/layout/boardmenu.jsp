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
    <style type="text/css">
        /* 메인메뉴 */
        ul.nav {
            z-index: 1;
            /*    ul태그의 기본 리스트 스타일(disc) 제거 */
            list-style-type: none;

            /*    기본 여백 제거 */
            padding: 0;
            margin: 0;

            /*    외부 정렬  */
            /*    flex box를 이용한 외부 정렬 (중앙) */
            display: flex;
            justify-content: center;

        }

        /* 메인 메뉴의 항목 */
        ul.nav > li {
            /*    수평으로 일렬로 배치하기 */
            float: left;
            border-radius: 45px;
            /*    배경색 */
            background-color: #666;

            /*    항목 크기 변경 (배경 포함) */
            /*    padding: 10px;    */

            /*    항목 크기 변경 (배경 미포함) */
            /*    margin: 10px;    */

            /*    줄간격을 이용한 높이 조절 (배경 조절) */
            line-height: 2em;

            /*    테두리를 이용한 항목 구분 */
            /*    border-right: 1px solid red; */
            border-right: 1px solid #fff;

            /*    서브 메뉴의 기준점으로 설정하기 */
            position: relative;
        }

        /* 메인 메뉴의 항목 텍스트 */
        ul.nav > li > a {
            color: #fff;
            border-radius: 45px;
            /*    글자 꾸밈선 제거 (underline) */
            text-decoration: none;

            font-size: 100%;
            font-weight: bold;
            font-family: 굴림, gulim, 돋움, dotum;

            /*    내부 여백 (링크 클릭 가능) */
            padding: 16px 24px;

            /*    외부 여백 (링크 클릭 불가능) */
            margin: 0 5px;

            /*    부모 <li>태그를 <a>태그로 가득 채우기 */
            display: block;

        }

        /* ul.nav >li:hover > a { */
        ul.nav > li > a:hover {
            color: tomato;

            transition: color 200ms;

        }

        /* ---------------------------------------------------------------------------------- */

        /* 서브 메뉴 */
        ul.nav > li > ul {
            /*    ul태그의 기본 리스트 스타일(circle) 제거 */
            list-style-type: none;

            /*    ul태그 기본 여백 제거 */
            padding: 0;
            margin: 0;

            /*    서브 메뉴의 너비 */
            width: 200px;

            /*    HTML 레이아웃 구조에서 빼내기 */
            /*    다른 요소를 밀어내지 않도록 만들기 */
            position: absolute;
            z-index: 1;
        }

        /* 서브 메뉴의 항목 */
        ul.nav > li > ul > li {
            background-color: #999;
            border-radius: 45px;
            display: none;
            margin-top: 10px;
        }


        ul.nav > li:hover > ul > li {
            display: list-item;
            text-align: center;
        }

        /* 서브 메뉴의 항목 텍스트 */
        ul.nav > li > ul > li > a {
            background-color: #ccc;
            border-radius: 45px;
            /*    부모<li>태그영역을 <a>태그로 가득 채우기 */
            display: block;

            /*    padding: 10px; */

            color: #333;
            text-decoration: none;

        }


        ul.nav > li > ul > li > a:hover {
            background-color: #00c;
            color: #c00;

            transition: background 2s, color 1s;

        }


    </style>
</head>
<body>
<div class="Header-Search">
    <input type="text" placeholder="검색어를 입력하세요">
    <img src="/resources/img/mainPage/search.png" alt="검색" id="search-icon">
</div>
<%--<div class="Header-Button">--%>
<%--    <ul class="Header-Button">--%>
<%--        		<li><a href="/board/list?categoryNo=11&curPage=${curPage}">여행지 정보 - 여행 이야기 공유</a></li>--%>
<%--				<li><a href="/board/list?categoryNo=12&curPage=${curPage}">여행지 정보 - 여행 팁 및 권고사항</a></li>--%>
<%--				<li><a href="/board/list?categoryNo=13&curPage=${curPage}">여행지 정보 - 다양한 여행 목적지 추천</a></li>--%>
<%--				<li><a href="/board/list?categoryNo=21&curPage=${curPage}">사진 갤러리 - 여행지 사진</a></li>--%>
<%--				<li><a href="/board/list?categoryNo=31&curPage=${curPage}">지역별 여행 - 지역 리스트</a></li>--%>
<%--				<li><a href="/board/list?categoryNo=32&curPage=${curPage}">지역별 여행 - 여행지 선택 페이지</a></li>--%>
<%--				<li><a href="/board/list?categoryNo=41&curPage=${curPage}">여행 Q&A - 꿀팁게시판</a></li>--%>
<%--				<li><a href="/board/list?categoryNo=42&curPage=${curPage}">여행 Q&A - 자유게시판</a></li>--%>
<%--				<li><a href="/board/list?categoryNo=43&curPage=${curPage}">여행 Q&A - 공지사항</a></li>--%>
<%--				<li><a href="/board/list?categoryNo=51&curPage=${curPage}">이벤트 - 이벤트 공지사항</a></li>--%>
<%--				<li><a href="/board/list?categoryNo=52&curPage=${curPage}">이벤트 - 이벤트</a></li>--%>
<%--				<li><a href="/board/list?categoryNo=61&curPage=${curPage}">여행관련 상품 - 여행관련 상품</a></li>--%>
<%--    </ul>--%>

<ul class="nav">
    <li class="menu">
        <a href="/">여행지정보</a>
        <ul>
            <li><a href="/board/list?categoryNo=11&curPage=${curPage}"> 여행 이야기 공유</a></li>
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
    <li><a href="/board/list?categoryNo=61&curPage=${curPage}">여행관련 상품 - 여행관련 상품</a></li>
</ul>
<%--</div>--%>
<div style="clear: both; overflow: hidden;"></div>
<hr class="line">

</body>
</html>