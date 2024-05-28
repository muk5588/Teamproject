<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<link rel="stylesheet" type="text/css" href="/resources/css/order/history.css">
<script type="text/javascript" src="https://code.jquery.com/jquery-3.7.1.js"></script>
<link rel="stylesheet" type="text/css" href="/resources/css/common/paging.css">
<style type="text/css">

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
<c:import url="/WEB-INF/views/layout/boardmenu.jsp" />

<!-- wrap 때문에 container가 반응형 X로 바뀜 -->
<div class="wrap mx-auto">


    <div class="container">

        <h1>주문 내역 확인</h1>
        <div style="position: absolute">
        <a href="/">
            <button>메인페이지로</button>
        </a>
        </div>
        <div>
            <form action="" method="get" id="searchForm">
                <input type="text" name="search" id="search" placeholder="검색하실 상품명을 작성해 주세요" value="${paging.search }">
                <input hidden="hidden" name="curPage" value="${curPage}">
                <button id="serchBtn">검색</button>
            </form>
        </div>
        <hr style="clear: both;">

        <div id="orderwrap">
            <c:if test="${paging.totalCount != 0}">
            <c:forEach var="userOrder" items="${orders }">
                <c:set var="i" value="0"/>
                <div class="oneOrder" style="border: 1px solid #ccc;">
                    <table>
                        <c:forEach items="${orderitems}" var="orderItem">
                            <c:forEach items="${items}" var="item">
                                <c:if test="${userOrder.orderNo eq orderItem.orderNo}">
                                    <c:if test="${orderItem.itemNo eq item.itemNo }">
                                        <c:if test="${i eq 0}">
                                            <tr>
                                                <th><h3>주문 번호 : ${userOrder.orderNo }</h3></th>
                                                <th></th>
                                            </tr>
                                            <tr>
                                                <th>주문자 : ${userOrder.userName }</th>
                                                <th rowspan="7" class="img">
                                                <c:choose>
											    	<c:when test="${not empty imgFiles}">
												    	<c:forEach items="${imgFiles}" var="files">
												    	<c:if test="${not empty files.itemNo and item.itemNo eq  files.itemNo}">
												    		<img alt="" src="/resources/img/shop/upload/${files.storedName }">
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
                                                </th>
                                            </tr>
                                            <tr>
                                                <th><fmt:formatDate value="${userOrder.orderDate}"
                                                                    pattern="MM/dd HH:mm"/></th>
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
                                                <th>결제 가격 : <fmt:setLocale value="ko_KR"/><fmt:formatNumber type="currency" value="${orderItem.price }"/></th>
                                            </tr>
                                            <tr>
                                                <th>결제 정보 :
                                                    <c:if test="${userOrder.pay eq 'N' or empty userOrder.pay }">결제 대기</c:if>
                                                    <c:if test="${userOrder.pay eq 'Y' }">결제 완료</c:if>
                                                    <c:if test="${userOrder.pay eq 'C' }">환불 처리</c:if>
                                                </th>
                                            </tr>
                                            <c:set var="i" value="${i + 1 }"/>
                                        </c:if>
                                        <c:set var="sum" value="0"/>
                                        <tr>
                                            <c:if test="${not empty orderItem }">
                                                <th>상품명 : <c:if test="${not empty userOrder.orderNo}">
                                                	<a href="http://localhost:8088/shop/detail?orderNo=${orderItem.orderNo}&itemNo=${orderItem.itemNo}">${orderItem.itemName }</a>
                                                	</c:if>
                                                </th>
                                                <th>구매수량 : ${orderItem.orderQuantity }</th>
                                                <c:set var="i" value="${orderItem.price}"/>
                                                <c:set var="sum" value="${sum + i}"/>
                                            </c:if>
                                        </tr>

                                    </c:if>
                                </c:if>
                            </c:forEach>
                        </c:forEach>
                    </table>
                </div>
            </c:forEach>
            </c:if>
            <c:if test="${paging.totalCount == 0 }">
                <table>
                <tr>
                    <h3>구매한 상품이 없습니다</h3>
                </tr>
                </table>
            </c:if>
        </div>
    </div>
</div>
<!-- .container End -->

<c:if test="${paging.totalCount != 0}">
<c:import url="/WEB-INF/views/layout/orderPaging.jsp"/>
</c:if>

<c:import url="/WEB-INF/views/layout/footer.jsp"/>