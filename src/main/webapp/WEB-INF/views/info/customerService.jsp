<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
<meta charset="UTF-8">
<title>고객센터</title>
	<link rel="icon" href="<%=request.getContextPath()%>/resources/img/20191208094528217881320965.png">
<c:import url="/WEB-INF/views/layout/header.jsp"/>
<link href="/resources/css/info/customerService.css" rel="stylesheet" type="text/css">

</head>
<body>

<h1>TravelSquare 고객센터</h1>
<hr>

<div class="customerServiceSection">
	<p class="bold-text">메일로 문의 부탁드립니다.</p>
	<p class="bold-text">TravelSquare1@gmail.com</p>
</div>


<hr>
<c:import url="/WEB-INF/views/layout/footer.jsp"/>
</body>
</html>