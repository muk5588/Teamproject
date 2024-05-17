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

        <h1>주문 목록 확인</h1>
        <a href="/">
            <button>메인페이지로</button>
        </a>
        <a href="./create">
            <button>상품 추가하기</button>
        </a>
        <div>
            <form action="" method="get" id="searchForm">
                <input type="text" name="search" id="search" placeholder="검색하실 상품명을 작성해 주세요" value="${paging.search }">
                <input hidden="hidden" name="curPage" value="${curPage}">
                <button id="serchBtn">검색</button>
            </form>
        </div>
        <hr>
        
<div id="orderwrap">
<c:forEach var="userOrder" items="${orders }">
<c:set var="i" value="0"/>
<div class="oneOrder" style="border: 1px solid #ccc;">
<table>
<c:forEach items="${orderitems}" var="orderItem">
<c:forEach items="${items}" var="item">
<c:if test="${userOrder.orderNo eq orderItem.orderNo}">
<c:if test="${orderItem.itemNo eq item.itemNo }">
<c:if test="${i eq 0}">
<tr><th><h3>주문 번호 : ${userOrder.orderNo }</h3></th></tr>
<tr>
	<th>주문자 : ${userOrder.userName }</th>
</tr>
<tr>
	<th>배송지 : ${userOrder.address }</th>
</tr>
<tr>
	<th>배송지 세부 주소 : ${userOrder.detailAddress }</th>
</tr>
<tr>
	<th>배송지 세부 주소 추가 : ${userOrder.extraAddress }</th>
</tr>
<tr>
	<th>결제 가격 : ${userOrder.totalPrice }</th>
</tr>
<c:set var="i" value="${i + 1 }"/>
</c:if>
	<c:set var="sum" value="0"/>
	<tr>
	<c:if test="${not empty orderItem }">
   			<td>상품명 : ${orderItem.itemName }</td>
   			<td>구매수량 : ${orderItem.orderQuantity }</td>
   			<td>
   			<c:choose>
	    	<c:when test="${ item.titleImg ne 0}">
	    		<img alt="ItemImg" src="/resources/img/shop/upload/${item.storedName }">
	   		</c:when>
	   		<c:when test="${item.titleImg eq 0}">
	    		<img src="/resources/img/shop/nullimg.jpg" alt="notready">
	   		</c:when>
   			</c:choose>
   			</td>
   			<td><fmt:setLocale value="ko_KR"/><fmt:formatNumber type="currency" value="${orderItem.price }"/>
   			</td>
			<c:set var="i" value="${orderItem.price}"/>
			<c:set var="sum" value="${sum + i}"/>
    </c:if>
	</tr>
    <c:if test="${empty orderItem }">
   		<tr>
   			<h5>구매한 상품이 없습니다</h5>
   		</tr>
   	</c:if>
    </c:if>
    </c:if>
    </c:forEach>
    </c:forEach>
</table>
</div>
</c:forEach>
</div>
</div>
</div>
    <!-- .container End -->

    <c:import url="/WEB-INF/views/layout/orderPaging.jsp"/>


<c:import url="/WEB-INF/views/layout/footer.jsp"/>