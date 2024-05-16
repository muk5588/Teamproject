<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="core" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%--<%@ page session="false" %>--%>
<html>
<head>
    <title>Home</title>
    <script src="/resources/js/mainPage/mainSlide.js" defer></script>
    <script type="text/javascript" src="https://code.jquery.com/jquery-3.7.1.js"></script>
    <script src='https://cdn.jsdelivr.net/npm/fullcalendar@6.1.11/index.global.min.js'></script>
    <link rel="stylesheet" type="text/css" href="/resources/css/mainPage/mainSlide.css">
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <script type="javascript">
        function adjustSlideshowPosition() {
            var menu = document.querySelector('.nav'); // 메뉴 요소 선택
            var slideshowContainer = document.querySelector('.slideshow-container'); // 슬라이드쇼 컨테이너 요소 선택

            var menuHeight = menu.offsetHeight; // 메뉴의 높이
            slideshowContainer.style.marginTop = menuHeight + 'px'; // 슬라이드쇼 컨테이너의 marginTop 설정
        }
        // 페이지 로드 시 조정 함수 호출
        window.onload = adjustSlideshowPosition;

    </script>
    <script type="text/javascript">
        document.addEventListener('DOMContentLoaded', function() {
            var calendarEl = document.getElementById('calendar_home');
            var calendar = new FullCalendar.Calendar(calendarEl, {
                initialView: 'dayGridMonth',
                titleFormat : {
                    year : 'numeric', month : 'long'
                },
                headerToolbar: {
                    start : 'title',
                    center:'',
                    end : ''
                }
            });
            calendar.render();
        });
        document.addEventListener('DOMContentLoaded', function() {
            var calendarLink = document.querySelector('.calendar_link');

            calendarLink.addEventListener('click', function(event) {
                // 세션에서 로그인 여부 확인
                var isLogin = <%= session.getAttribute("isLogin") %>;

                // 로그인 여부에 따라 페이지 이동 제어
                if (!isLogin) {
                    event.preventDefault(); // 기본 동작 중지 (페이지 이동 취소)
                    alert('로그인이 필요합니다.'); // 사용자에게 알림 표시
                    return false;
                } else {
                    // 로그인된 경우에만 페이지 이동
                    location.href = '/calendar';
                }
            });
        });

    </script>
    <style type="text/css">
        @import url('https://webfontworld.github.io/NexonMaplestory/NexonMaplestory.css');
        *{
            font-family: 'NexonMaplestory';
            font-weight: 300;
            font-style: normal;
        }
        a{
            color: black;
            text-decoration-line: none;
        }
        .logout{
            border-radius: 15px;
            background-color: white;
            border: 1px solid #ccc;
            padding-top: 4px;
        }
        .logout img{
            vertical-align: middle;
        }
        .usernick{
            font-size: 24px;
            margin-right: 1rem;
        }

        #calendar_home{
            clear: both;
            float:right;
            width: 300px;
            height: 500px;
        }

        .email{
            margin-top: 1em;
        }

    </style>
</head>
<body>

<jsp:include page="/WEB-INF/views/layout/header.jsp"/>
<jsp:include page="/WEB-INF/views/layout/boardmenu.jsp"/>


<div class="slideshow-container">

    <div class="mySlides fade">
        <div class="numbertext">1 / 3</div>
        <img src="/resources/img/mainPage/japan_01.jpg" style="width:100%">
    </div>

    <div class="mySlides fade">
        <div class="numbertext">2 / 3</div>
        <img src="/resources/img/mainPage/japan_02.jpg" style="width:100%">
    </div>

    <div class="mySlides fade">
        <div class="numbertext">3 / 3</div>
        <img src="/resources/img/mainPage/japan_03.jpg" style="width:100%">
    </div>

    <!-- 다음 & 이전 버튼 -->
    <a class="prev" onclick="plusSlides(-1)">&#10094;</a>
    <a class="next" onclick="plusSlides(1)">&#10095;</a>
</div>
<br>

<!-- 이미지 밑 점들 -->
<div style="text-align:center">
    <span class="dot" onclick="currentSlide(1)"></span>
    <span class="dot" onclick="currentSlide(2)"></span>
    <span class="dot" onclick="currentSlide(3)"></span>
</div>

<div id="login" class="login">
    <c:if test="${empty isLogin }">
        <a href="<%=request.getContextPath()%>/login">
            <button class="login-button">로그인</button>
        </a><br>
        <a class="btn-fill" href="/user/insertUser">회원가입</a>
        /
        <a class="btn-fill" href="/user/searchUser?value=id">아이디찾기</a>
        /
        <a class="btn-fill" href="/user/searchUser?value=pw">비밀번호찾기</a>
    </c:if>
    <c:if test="${not empty isLogin}">
        <span class="usernick">${dto1.nickname }</span>
        <a class="btn-fill" href="<%=request.getContextPath()%>/login/logout">
            <button class="logout">로그아웃<img src="/resources/img/mainPage/로그아웃.png" width="30px" height="30px"></button>
        </a><br>
        <span class="email">${dto1.email }</span><br>

        <c:choose>
            <c:when test="${dto1.gradeno == 0 || dto1.gradeno == 5000}">
                <a href = '/message/'>쪽지</a>
                /
                <a href="/user/userDetail">마이페이지</a>
                /
                <a href="/user/adminPage">관리자페이지</a>
            </c:when>
            <c:otherwise>
                <a href="/user/userDetail">마이페이지</a>
                /
                <a href = '/message/'>쪽지</a>
            </c:otherwise>
        </c:choose>
        </ul>
    </c:if>
</div>
<div>
    <jsp:include page="layout/weather.jsp"></jsp:include>
</div>

<a class="calendar_link">
<div id = "calendar_home"></div>
</a>
<P> The time on the server is ${serverTime}. </P>
<div class="board-pre">

</div>

<%--<button><a href="login">로그인</a></button>--%>
<%--<button><a href="/user/insertUser">가입</a></button>--%>
<button><a href="/user/adminPage">가입</a></button>
<button onclick="location.href = '/grade/gradeList'">권한조회</button>
<button onclick="location.href = '/menu/menuList'">권한부여상황</button>
<button onclick="location.href = '/user/userList'">유저조회</button>
<button onclick="location.href = '/board/list'">게시물조회</button>
<button onclick="location.href = '/message/'">메세지 테스트</button>
<button onclick="location.href = '/basket/userbasket'">장바구니</button>
<button onclick="location.href = '/calendar'">캘린더</button>


<%--<c:if test="${empty isLogin}">--%>
<%--	<a href="login"><button>로그인</button></a>--%>
<%--	<a href="insertUser">회원가입</a>--%>
<%--</c:if>--%>

<jsp:include page="/WEB-INF/views/layout/boardPreview.jsp"/>
<jsp:include page="/WEB-INF/views/layout/footer.jsp"/>
</body>
</html>
