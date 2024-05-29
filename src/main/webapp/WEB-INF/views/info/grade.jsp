<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
<meta charset="UTF-8">
<title>Travel Square 등급안내</title>
<c:import url="/WEB-INF/views/layout/header.jsp"/>
<link href="/resources/css/info/grade.css" rel="stylesheet" type="text/css">


</head>
<body>

<h1>등급안내</h1>
<hr>

<div class="gradeSection">
<p style='word-spacing: 9em; line-height:2.5;'>	게시판이름						등급이름		추가사항</p>
<p style='word-spacing: 7em;' >	여행지정보-여행이야기			신입회원		신입회원</p>
<p>	여행지 정보 - 여행 팁 및 권고사항	신입 회원		신입 회원</p>
<p>	여행지 정보 - 다양한 여행 목적지 추천	신입 회원		신입 회원</p>
<p>	사진 갤러리 - 여행지 사진			신입 회원		신입 회원</p>
<p>	지역별 여행 - 지역 리스트			일반 회원		일반 회원, 게시글 3회 이상 작성</p>
<p>	지역별 여행 - 여행지 선택 페이지		일반 회원		일반 회원, 게시글 3회 이상 작성</p>
<p>	여행 Q&A - 꿀팁게시판			고급 회원		고급 회원, 게시글 10회 이상 작성 + 접속일 14일 이상</p>	
<p>	여행 Q&A - 자유게시판			신입 회원		신입 회원</p>
<p>	여행 Q&A - Q&A 공지사항			신입 회원		신입 회원</p>
<p>	이벤트 - 이벤트 공지사항			일반 회원		일반 회원, 게시글 3회 이상 작성</p>
<p>	이벤트 - 이벤트					고급 회원		고급 회원, 게시글 10회 이상 작성 + 접속일 14일 이상</p>
<p>	여행관련 상품 - 여행관련 상품		신입회원		신입회원</p>
</div>

<hr>
<c:import url="/WEB-INF/views/layout/footer.jsp"/>
</body>
</html>