    <%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <!DOCTYPE html>
    <html>
    <head>
        <meta charset="UTF-8">
        <title>네이버 쪽지함 스타일 메뉴</title>
        <link rel="icon" href="<%=request.getContextPath()%>/resources/img/20191208094528217881320965.png">
        <link rel="stylesheet" type="text/css" href="/resources/css/mainPage/boardMenu.css">

        <script>
        // 검색을 수행하는 함수
        function performSearch() {
            var searchText = document.getElementById('search-input').value;
            var searchKind = "title";

            // 검색어와 검색 조건을 사용하여 전체 게시물 페이지 URL을 생성합니다.
            var searchUrl = "/board/list?searchKind=" + searchKind + "&search=" + encodeURIComponent(searchText);

            // 생성된 URL로 이동합니다.
            window.location.href = searchUrl;
        }

        // 엔터키를 눌렀을 때 검색을 수행하는 함수
        function handleKeyPress(event) {
            // 엔터키의 keyCode는 13입니다.
            if (event.keyCode === 13) {
                performSearch(); // 검색을 수행합니다.
            }
        }

        // 페이지 로드 시 실행될 초기화 함수
        document.addEventListener('DOMContentLoaded', function () {
            var searchInput = document.getElementById('search-input');

            // 검색 입력 필드에 이벤트 리스너를 추가합니다.
            searchInput.addEventListener('keypress', handleKeyPress);
        });
        </script>

    </head>
    <body>
    <div class="Header-Search">
        <input type="text" placeholder="검색어를 입력하세요" id="search-input">
        <img src="/resources/img/mainPage/search.png" alt="검색" id="search-icon" onclick="performSearch()">
    </div>

    <ul class="nav">
        <li class="menu">
            <a href="/board/list?categoryNo=11&curPage=${curPage}">
                <img src="/resources/img/mainPage/information.png" class="menu-icon"/>
                여행지정보
            </a>
            <ul>
                <li><a href="/board/list?categoryNo=11&curPage=${curPage}">여행 이야기 공유</a></li>
                <li><a href="/board/list?categoryNo=12&curPage=${curPage}">여행 팁 및 권고사항</a></li>
                <li><a href="/board/list?categoryNo=13&curPage=${curPage}">다양한 여행 목적지 추천</a></li>
            </ul>
        </li>
        <li>
            <a href="/board/list?categoryNo=21&curPage=${curPage}">
                <img src="/resources/img/mainPage/gallery.png" class="menu-icon"/>
                사진 갤러리 - 여행지 사진
            </a>
        </li>
        <li class="menu">
            <a href="/board/list?categoryNo=31&curPage=${curPage}">
                <img src="/resources/img/mainPage/airplane.png" class="menu-icon"/>
                지역별 여행
            </a>
            <ul>
                <li><a href="/board/list?categoryNo=31&curPage=${curPage}">지역 리스트</a></li>
                <li><a href="/board/list?categoryNo=32&curPage=${curPage}">여행지 선택 페이지</a></li>
            </ul>
        </li>
        <li class="menu">
            <a href="/board/list?categoryNo=43&curPage=${curPage}">
                <img src="/resources/img/mainPage/qa.png" class="menu-icon"/>
                여행 Q&A
            </a>
            <ul>
                <li><a href="/board/list?categoryNo=43&curPage=${curPage}">Q&A 공지사항</a></li>
                <li><a href="/board/list?categoryNo=42&curPage=${curPage}">자유게시판</a></li>
                <li><a href="/board/list?categoryNo=41&curPage=${curPage}">꿀팁게시판</a></li>
            </ul>
        </li>
        <li class="menu">
            <a href="/board/list?categoryNo=51&curPage=${curPage}">
                <img src="/resources/img/mainPage/party.png" class="menu-icon"/>
                이벤트
            </a>
            <ul>
                <li><a href="/board/list?categoryNo=51&curPage=${curPage}">이벤트 공지사항</a></li>
                <li><a href="/board/list?categoryNo=52&curPage=${curPage}">이벤트</a></li>
            </ul>
        </li>
        <li>
            <a href="/shop/">
                <img src="/resources/img/mainPage/box.png" class="menu-icon"/>
                여행관련 상품
            </a>
        </li>
    </ul>

    <div style="clear: both; overflow: hidden;"></div>
    <hr class="line">

    </body>
    </html>
