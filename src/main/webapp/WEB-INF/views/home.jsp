<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>

<%@ page session="false" %>
<html>
<head>
	<title>Home</title>
</head>
<body>
<h1>
	Hello world!  
</h1>

<P>  The time on the server is ${serverTime}. </P>
<jsp:include page="/WEB-INF/views/header.jsp" />
<button><a href="login">로그인</a></button>
<button><a href="/user/insertUser">가입</a></button>
<button><a href="gradeInsert">가입</a></button>
<button onclick="location.href = 'gradeList'">권한조회</button>
<button onclick="location.href = '/menu/menuList'">권한부여상황</button>
<button onclick="location.href = '/user/userList'">유저조회</button>
<c:if test="${empty dto}">
	<button><a href="login">로그인</a></button>
	<button><a href="insertUser">회원가입</a></button>
</c:if>
<c:if test="${dto}">
	<a href = 'userDetail?userid=${dto.userid}'>마이페이지</a>
	<a href="login/logout">로그아웃</a>
</c:if>
<jsp:include page="/WEB-INF/views/footer.jsp" />
</body>
</html>
