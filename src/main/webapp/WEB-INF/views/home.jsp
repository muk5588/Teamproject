<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="core" uri="http://java.sun.com/jsp/jstl/core" %>

<%--<%@ page session="false" %>--%>
<html>
<head>
	<title>Home</title>
	
	<script src="/resources/js/mainPage/mainSlide.js" defer></script>
	<link  rel="stylesheet" type="text/css" href="/resources/css/mainPage/mainSlide.css">
</head>
<body>

<jsp:include page="/WEB-INF/views/layout/header.jsp" />
<hr>


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

<h1>
	Hello world!  
</h1>
<div>
    <jsp:include page="layout/weather.jsp"></jsp:include>
</div>
<P>  The time on the server is ${serverTime}. </P>

<%--<button><a href="login">로그인</a></button>--%>
<%--<button><a href="/user/insertUser">가입</a></button>--%>
<button><a href="/user/adminPage">가입</a></button>
<button onclick="location.href = '/grade/gradeList'">권한조회</button>
<button onclick="location.href = '/menu/menuList'">권한부여상황</button>
<button onclick="location.href = '/user/userList'">유저조회</button>
<button onclick="location.href = '/board/list'">게시물조회</button>
<button onclick="location.href = '/user/searchUser?value=id'">아이디찾기</button>
<button onclick="location.href = '/user/searchUser?value=pw'">비밀번호찾기</button>
<button onclick="location.href = '/message/'">메세지 테스트</button>
<button onclick="location.href = '/weather'">시발날씨</button>


<%--<c:if test="${empty isLogin}">--%>
<%--	<a href="login"><button>로그인</button></a>--%>
<%--	<a href="insertUser"><button>회원가입</button></a>--%>
<%--</c:if>--%>

<c:if test="${isLogin > 0}">
	<a href ="/user/userDetail"><button>마이페이지</button></a>
<%--	<a href="login/logout"><button>로그아웃</button></a>--%>
</c:if>
<jsp:include page="/WEB-INF/views/layout/footer.jsp" />
</body>
</html>
