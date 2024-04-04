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
<button><a href="join">가입</a></button>
<jsp:include page="/WEB-INF/views/footer.jsp" />
</body>
</html>
