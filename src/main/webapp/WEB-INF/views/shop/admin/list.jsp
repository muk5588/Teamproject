<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:import url="/WEB-INF/views/layout/header.jsp"/>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet"
      integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz"
        crossorigin="anonymous"></script>
<style type="text/css">

    .wrap {
        width: 1100px;
    }

    table, th {
        text-align: center;
    }

    /* <!-- body { --> */
    /* <!-- 	width: 1500px; --> */
    /* <!-- 	margin: 0 auto; --> */
    /* <!-- } --> */

    /* <!-- h1 { --> */
    /* <!-- 	text-align: center; --> */
    /* <!-- } --> */

    /* <!-- table { --> */
    /* <!-- 	border: 1px solid black; --> */
    /* <!-- 	margin: 0 auto; --> */
    /* <!-- } --> */

    /* <!-- tr, th, td { --> */
    /* <!-- 	border: 1px solid black; --> */
    /* <!-- } --> */

    /* <!-- th { --> */
    /* <!-- 	background-color: #ccc; --> */
    /* <!-- } --> */

    /* <!-- td.no, .title, .id, .nick, .hit, .date { --> */
    /* <!-- 	text-align: center; --> */
    /* <!-- } --> */

    /* <!-- td.title { --> */
    /* <!-- 	width: 200px; --> */
    /* <!-- } --> */

    /* <!-- td.content { --> */
    /* <!-- 	width: 500px; --> */
    /* <!-- } --> */

    /* <!-- td.id, .nick { --> */
    /* <!-- 	width: 150px; --> */
    /* <!-- } --> */

    /* <!-- td.hit { --> */
    /* <!-- 	width: 50px; --> */
    /* <!-- } --> */

    /* <!-- td.date { --> */
    /* <!-- 	width: 200px; --> */
    /* <!-- } --> */
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

<!-- wrap 때문에 container가 반응형 X로 바뀜 -->
<div class="wrap mx-auto">


    <div class="container">

        <h1>상품 관리</h1>
        <a href="/adminPage">
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
<div id="itemwarp">
    <ul>
    <c:forEach var="item" items="${items }">
    <div class="oneItem">
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
    </c:forEach>
    </div>
    </ul>
</div>
       

    </div>
    <!-- .container End -->

    <c:import url="/WEB-INF/views/layout/shopPaging.jsp"/>


<c:import url="/WEB-INF/views/layout/footer.jsp"/>