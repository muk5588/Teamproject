<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="core" uri="http://java.sun.com/jsp/jstl/core" %>

<%--<%@ page session="false" %>--%>
<html>
<head>
	<title>Home</title>
	<link rel="icon" href="resources/img/favicon.ico">
</head>
<body>
<jsp:include page="/WEB-INF/views/layout/header.jsp" />
<h1>
	Hello world!  
</h1>

<P>  The time on the server is ${serverTime}. </P>

<%--<button><a href="login">로그인</a></button>--%>
<%--<button><a href="/user/insertUser">가입</a></button>--%>
<button><a href="/user/adminPage">가입</a></button>
<button onclick="location.href = '/grade/gradeList'">권한조회</button>
<button onclick="location.href = '/menu/menuList'">권한부여상황</button>
<button onclick="location.href = '/user/userList'">유저조회</button>
<button onclick="location.href = '/user/searchUser?value=id'">아이디찾기</button>
<button onclick="location.href = '/user/searchUser?value=pw'">비밀번호찾기</button>


<%--<c:if test="${empty isLogin}">--%>
<%--	<a href="login"><button>로그인</button></a>--%>
<%--	<a href="insertUser"><button>회원가입</button></a>--%>
<%--</c:if>--%>

<c:if test="${not empty isLogin and isLogin}">
	<a href ="/user/userDetail"><button>마이페이지</button></a>
<%--	<a href="login/logout"><button>로그아웃</button></a>--%>
</c:if>
<jsp:include page="/WEB-INF/views/layout/footer.jsp" />
</body>
</html>
