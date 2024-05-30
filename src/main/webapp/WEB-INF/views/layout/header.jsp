<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- 스프링은 따로 라이브러리를 설치하지 않아도 다운 받을 라이브러리를 설정할 수 있다. -->
<!-- 그 중에 jstl 라이브러리도 있기 때문에 사용할 수 있는것 -->
<!-- 라이브러리 목록은 iot/pom.xml, c:\사용자\.m2에서 확인가능 -->
<link href="/resources/css/common/common.css" rel="stylesheet" type="text/css">
<link href="/resources/css/common/header.css" rel="stylesheet" type="text/css">



<script type="text/javascript" src="/resources/js/mainPage/header.js"></script>
<script type="text/javascript" src="/resources/js/mainPage/mainToggle.js"></script>


<!-- 어느 페이지에 가도 인클루드 되어있는 헤더에 jQuery 선언문을 넣는다. -->
<script type="text/javascript" src="https://code.jquery.com/jquery-3.7.1.min.js"></script>

<style>
    header ul, header ul li {
        margin: 0;
        padding: 0;
        display: inline;
    }

    Header-Button {
        display: inline-block;
        text-align: center;

    }
    .hidden {
        display: none;
    }

    #menu {
        position: absolute;
        top: 80px; /* 위치를 조정하세요 */
        right: 10px; /* 위치를 조정하세요 */
        background-color: white;
        border: 1px solid #ccc;
        box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
        padding: 10px;
        display: none; /* 기본적으로 메뉴를 숨깁니다 */
    }

    #menu.show {
        display: block;
    }

    #menu a {
        display: block;
        padding: 10px;
        text-decoration: none;
        color: black;
    }

    #menu a:hover {
        background-color: #f0f0f0;
    }
    header ul li input {
        display: block;
    }
</style>
<head>
    <title>헤더</title>
    <link rel="icon" href="<%=request.getContextPath()%>/resources/img/20191208094528217881320965.png">
</head>
<div class="Head">
    <header class="Main-Header">
        <div class="Menu-List" id="menuList">
            <img id="list-icon" src="/resources/img/mainPage/list.png" alt="우측 상단 목록" onclick="toggleLoginMenu(event);"><br>
        </div>
<div id="menu" class="hidden">
            <c:choose>
                <c:when test="${empty isLogin}">
                    <a href="#" onclick="redirectToLogin(event);">쪽지</a>
                    <a href="#" onclick="redirectToLogin(event);">문의하기</a>
                    <a href="#" onclick="redirectToLogin(event);">구매기록</a>
                    <a href="#" onclick="redirectToLogin(event);">장바구니</a>
                </c:when>
                <c:otherwise>
                    <c:choose>
                        <c:when test="${dto1.gradeno == 0 || dto1.gradeno == 5000}">
                            <a href='<%=request.getContextPath()%>/message/'>쪽지</a>
                            <a href='<%=request.getContextPath()%>/inquiry/adminList'>문의보기</a>
                            <a href='<%=request.getContextPath()%>/order/history'>주문내역</a>
                            <a href='<%=request.getContextPath()%>/basket/userbasket'>장바구니</a>
                        </c:when>
                        <c:otherwise>
                            <a href='<%=request.getContextPath()%>/message/'>쪽지</a>
                            <a href='<%=request.getContextPath()%>/inquiry/list'>문의하기</a>
                            <a href='<%=request.getContextPath()%>/order/history'>주문내역</a>
                            <a href='<%=request.getContextPath()%>/basket/userbasket'>장바구니</a>
                        </c:otherwise>
                    </c:choose>
                </c:otherwise>
            </c:choose>
        </div>
        <div class="Header-Name">
            <a href="/" class="Site-Name"><img src="/resources/img/title/18px.png" class="site-Img" ></a>
        </div>

        <div style="position: absolute; right: 0; top: 55px; margin-right: 100px;">
            <!-- 로그인한 경우 -->
            <c:if test="${not empty isLogin}">
                <ul>
                    <li>${dto1.name } <a href="/user/userDetail">[${dto1.nickname } ]</a></li>
                    <li><a href="<%=request.getContextPath()%>/login/logout">로그아웃</a></li>
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


