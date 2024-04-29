<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- 스프링은 따로 라이브러리를 설치하지 않아도 다운 받을 라이브러리를 설정할 수 있다. -->
<!-- 그 중에 jstl 라이브러리도 있기 때문에 사용할 수 있는것 -->
<!-- 라이브러리 목록은 iot/pom.xml, c:\사용자\.m2에서 확인가능 -->

<link href="/resources/css/header.css" rel="stylesheet" type="text/css">

<script type="text/javascript" src="/resources/js/header.js"></script>


<!-- 어느 페이지에 가도 인클루드 되어있는 헤더에 jQuery 선언문을 넣는다. -->
<script type="text/javascript" src="https://code.jquery.com/jquery-3.7.1.min.js"></script>

<style>
    header ul, header ul li {
        margin: 0;
        padding: 0;
        display: inline;
    }

    header .category {
        font-size: 18px;
    }

    header .category ul li:not(:first-child) { /* 첫번째 li만 빼고 지정 */
        padding-left: 30px;
    }

    header .category ul li a:hover, header .category ul li a.active {
        font-weight: bold;
        color: #0000cd;
    }

    header #userid, header #userpw {
        width: 100px;
        height: 18px;
        font-size: 14px;
    }

    Header-Button {
        display: inline-block;
        text-align: center;

    }

    header ul li input {
        display: block;
    }


</style>

<div class="container">
    <header class="Main-Header">
        <div class="Menu-List" id="menuList">
            <img id="list-icon" src="/resources/img/mainPage/list.png" alt="우측 상단 목록" onclick="toggleLoginMenu();"><br>
        </div>
        <div class="Header-Name">
            <a href="/" class="Site-Name">사이트 이름</a>
        </div>

        <div class="Header-Search">
            <input type="text" placeholder="검색어를 입력하세요">
            <img src="/resources/img/mainPage/search.png" alt="검색" id="search-icon">
        </div>
        <div class="Header-Button">
            <ul class="Header-Button">
                <li><a href="#">공지사항</a></li>
                <li><a href="#">여행지 정보</a></li>
                <li><a href="#">사진 갤러리</a></li>
                <li><a href="#">여행 Q&A</a></li>
                <li><a href="#">지역별 여행</a></li>
                <li><a href="#">여행 상품</a></li>
                <li><a href="#">이벤트</a></li>
                <li><a href="#">고객지원</a></li>
            </ul>
        </div>
        <!-- <div id="login-menu" class="login-menu">
            <div class="login-button-container">
            <a href="#" id="login-button">로그인</a>
            </div>
        </div> -->
        <div style="position: absolute; right: 0; top: 35px; margin-right: 100px;">
            <!-- 로그인한 경우 -->
            <c:if test="${not empty isLogin}">
                <ul>
                    <li>${dto1.name } [${dto1.nickname } ]</li>
                    <li><a class="btn-fill" href="<%=request.getContextPath()%>/login/logout">로그아웃</a></li>
                    <c:choose>
                        <c:when test="${dto1.gradeno == 0 || dto1.gradeno == 5000}">
                            <br>
                            <a href="/user/adminPage" style="margin-left: 90px">관리자페이지</a>
                        </c:when>
                        <c:otherwise>
                            <br>
                            <a href="/user/userDetail">마이페이지</a>
                        </c:otherwise>
                    </c:choose>
                </ul>
            </c:if>

            <!-- 로그인하지 않은 경우 -->
            <c:if test="${empty isLogin }">
                <ul>
                    <li><a class="btn-fill" href="<%=request.getContextPath()%>/login">로그인</a></li>
                    <li><a class="btn-fill" href="/user/insertUser">회원가입</a></li>
                </ul>
            </c:if>
        </div>

    </header>
</div>


<script>
    function go_login() {
        if ($('#userid').val() == '') {
            alert('아이디를 입력하세요!');
            $('#userid').focus();
            return;
        } else if ($('#userpw').val() == '') {
            alert('비밀번호를 입력하세요!');
            $('#userpw').focus();
            return;
        }

        $.ajax({
            type: 'post',
            url: 'login',
            data: {id: $('#userid').val(), pw: $('#userpw').val()},
            success: function (data) {
                if (data == 'true') {
                    location.reload();
                } else {
                    alert('아이디나 비밀번호가 일치하지 않습니다!');
                    $("#userid").focus();
                }
            },
            error: function (req, text) {
                alert(text + ': ' + req.status);
            }
        });
    }

    function go_logout() {
        $.ajax({
            type: "post",
            url: "/",
            success: function () {
                location.reload();
            },
            error: function (req, text) {
                alert(text + ': ' + req.status);
            }
        });
    }
</script>
