<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
  <link rel="icon" href="resources/img/favicon.ico">
<script src="/resources/js/mainPage/mainSlide.js" defer></script>
<link  rel="stylesheet" type="text/css" href="/resources/css/mainPage/mainSlide.css">
</head>
<body>

<jsp:include page="/WEB-INF/views/layout/header.jsp" />
<hr>

<div class="slideshow-container">

  <!-- Full-width images with number and caption text -->
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



</body>

<jsp:include page="/WEB-INF/views/layout/footer.jsp" />


</html>