<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<link rel="stylesheet" type="text/css" href="/resources/css/common/paging.css">
<link rel="stylesheet" type="text/css" href="/resources/css/shop/admin/list.css">
<style type="text/css">
	.container {
		text-align: center; /* 내용을 가운데 정렬 */
		margin-top: 20px; /* 상단 여백 */
	}

	.container h1 {
		font-size: 24px; /* 제목 크기 */
		margin-bottom: 10px; /* 아래 여백 */
	}

	.container a {
		text-decoration: none; /* 링크 밑줄 제거 */
	}

	.container button {
		padding: 8px 20px; /* 버튼 내용과 상하 좌우 패딩 */
		margin: 0 5px; /* 버튼 사이 여백 */
		border: none; /* 테두리 없음 */
		background-color: #007bff; /* 배경색 */
		color: #fff; /* 글자색 */
		border-radius: 4px; /* 테두리 둥글게 */
		cursor: pointer; /* 커서 모양 변경 */
	}

	.container button:hover {
		background-color: #0056b3; /* 마우스 호버 시 배경색 변경 */
	}

	.container form {
		display: flex; /* 폼 요소들을 가로로 나란히 배치 */
		justify-content: center; /* 가운데 정렬 */
		margin-top: 20px; /* 상단 여백 */
	}

	.container input[type="text"] {
		padding: 8px; /* 입력 상자 패딩 */
		border-radius: 4px; /* 입력 상자 테두리 둥글게 */
		border: 1px solid #ccc; /* 입력 상자 테두리 스타일 */
	}

	.container button#searchBtn {
		padding: 8px 20px; /* 버튼 내용과 상하 좌우 패딩 */
		margin-left: 10px; /* 왼쪽 여백 */
		border: none; /* 테두리 없음 */
		background-color: #007bff; /* 배경색 */
		color: #fff; /* 글자색 */
		border-radius: 4px; /* 테두리 둥글게 */
		cursor: pointer; /* 커서 모양 변경 */
	}

	.management-container #searchBtn:hover {
		background-color: #0056b3; /* 마우스 호버 시 배경색 변경 */
	}
</style>
<script type="text/javascript">
    $(function () {

        var blank_pattern = /^\s+|\s+$/g;

        $("#searchForm").submit(function (e) {
            console.log("#searchForm submit")

            var searchTerm = $("#search").val().trim(); // 입력값의 앞뒤 공백 제거

            if (!searchTerm) {
                alert("검색어를 입력해주세요")
                return false;
            }

            if (searchTerm.length === 0) {
                alert(' 공백만 입력되었습니다 ');
                return false;
            }

            $("#search").val(searchTerm)

        })




    })
</script>
</head>
<body>
<c:import url="/WEB-INF/views/layout/header.jsp"/>

<!-- wrap 때문에 container가 반응형 X로 바뀜 -->
<div class="wrap mx-auto">


    <div class="container">

        <h1>상품 관리</h1>
        <a href="/user/adminPage">
            <button>관리자 메인페이지로</button>
        </a>
        <a href="./create">
            <button>상품 추가하기</button>
        </a>
        <div>
            <form action="" method="get" id="searchForm">
                <input type="text" name="search" id="search" placeholder="검색하실 상품명을 작성해 주세요">
                <input hidden="hidden" name="curPage" value="${curPage}">
                <button id="serchBtn">검색</button>
            </form>
        </div>
        <hr>
        
    	<c:set var="imgFiles" property="${itemFiles }"/>

    <div class="oneAll">
    <c:forEach var="item" items="${items }">
    <ul class="oneItem">
    	<li style="list-style: none; border: 1px solid #ccc;">
    	<div class="item Img">
    	<a href="./detail?itemNo=${item.itemNo }">
    	<c:choose>
	    	<c:when test="${not empty imgFiles}">
		    	<c:forEach items="${files}" var="files">
		    	<c:if test="${not empty files.itemNo and item.itemNo eq  files.itemNo}">
		    		<img alt="ItemImg" src="/resources/img/shop/upload/${files.storedName }">
		    	</c:if>
		    	<c:if test="${empty files.itemNo}">
		    		<img src="/resources/img/shop/nullimg.jpg" alt="notready">
		    	</c:if>
		   		</c:forEach>
	   		</c:when>
	   		<c:when test="${empty imgFiles }">
	    		<img src="/resources/img/shop/nullimg.jpg" alt="notready">
	   		</c:when>
   		</c:choose>
    	</a>
    	</div>
    	<div class="item Info">
    	<a href="./detail?itemNo=${item.itemNo }">
   		${item.itemName}<br>
   		<fmt:setLocale value="ko_KR"/><fmt:formatNumber type="currency" value="${item.price}" />
   		</div>
    	</li>
    </ul>
    </c:forEach>
    </div>
</div>
       


    <!-- .container End -->

    <c:import url="/WEB-INF/views/layout/shopPaging.jsp"/>


<c:import url="/WEB-INF/views/layout/footer.jsp"/>