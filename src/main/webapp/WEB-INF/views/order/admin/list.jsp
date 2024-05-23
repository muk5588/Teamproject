<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<link rel="stylesheet" type="text/css" href="/resources/css/common/paging.css">
<link rel="stylesheet" type="text/css" href="/resources/css/order/admin/orderlist.css">
<script type="text/javascript" src="https://code.jquery.com/jquery-3.7.1.js"></script>


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
    });

    // $(function(){
    // 	$(".refundBtn").click(function() {
    // 			var data = $(this).val()
    // 			console.log("환불버튼" , data)
    // 			console.log("환불버튼")
    //         	var userName = $(this).data("username");
    // 	        var impUid = $(this).data("imp-uid");
    // 	        var merchantUid = $(this).data("merchant-uid");
    // 	        var totalPrice = $(this).data("totalprice");
    // 	        var paraMount = $(this).data("para-mount");
    // 			console.log("환불버튼username" , userName)
    // 			console.log("환불버튼impUid" , impUid)
    // 			console.log("환불버튼merchantUid" , merchantUid)
    // 			console.log("환불버튼totalprice" , totalPrice)
    // 			console.log("환불버튼paraMount" , paraMount)
    // 		    jQuery.ajax({
    // 		    	async: true,
    // 		    	  crossDomain: true,
    // 		    	  url: 'https://api.iamport.kr/payments/cancel',
    // 		    	  method: 'post',
    // 		    	  headers: {
    // 		    	    'Content-Type': 'application/json'
    // 		    	  },
    // 		    	  processData: false,
    // 		    	  data: '{"merchant_uid": merchantUid,
    // 				        "imp_uid":impUid,
    // 				        "cancel_request_amount": paraMount,
    // 				        "reason": "테스트 결제 환불" }'
    // 		    	}).done(function(result) { // 환불 성공시 로직
    // 		        alert("환불 성공");
    // 		      }).fail(function(error) { // 환불 실패시 로직
    // 				console.log(error)
    // 		        alert("환불 실패" ,error.message);
    // 		      });
    // 		    })
    // 	});


    $(function () {
        $(".refundBtn").click(function () {
            var data = $(this).val();
            var userName = $(this).data("username");
            var impUid = $(this).data("imp-uid");
            var merchantUid = $(this).data("merchant-uid");
            var totalPrice = $(this).data("totalprice");
            var paraMount = $(this).data("para-mount");

            $.ajax({
                type: "get",
                url: "./ordercancle",
                data: {
                    userName: userName,
                    impUid: impUid,
                    merchantUid: merchantUid,
                    totalPrice: totalPrice,
                    paraMount: paraMount,
                    orderNo: data
                },
                dataType: "json",
                success: function (res) {
                    console.log(res);
                    location.reload();

                },
                error: function () {
                    console.log("AJAX 실패");
                    location.reload();
                }
            });

        });
    });

</script>
</head>
<body>
<jsp:include page="/WEB-INF/views/layout/header.jsp"/>

<!-- wrap 때문에 container가 반응형 X로 바뀜 -->
<div class="wrap mx-auto">


    <div class="container">

        <h1>주문 목록 확인</h1>
        <div style="position: absolute">
            <a href="/user/adminPage">
                <button>관리자 메인페이지</button>
            </a>
        </div>

        <form action="" method="get" id="searchForm">
            <input type="text" name="search" id="search" placeholder="검색하실 상품명을 작성해 주세요" value="${paging.search }">
            <input hidden="hidden" name="curPage" value="${curPage}">
            <button id="serchBtn">검색</button>
        </form>
        <br>
        <hr>

        <div id="orderwrap">
            <c:forEach var="userOrder" items="${userOrders }">
                <c:set var="i" value="0"/>
                <c:if test="${not empty userOrder.orderNo }">
                    <div class="oneOrder" style="border: 1px solid #ccc;">
                        <table>
                            <c:forEach items="${orderitems}" var="orderItem">
                                <c:forEach items="${items}" var="item">
                                    <c:if test="${userOrder.orderNo eq orderItem.orderNo}">
                                        <c:if test="${orderItem.itemNo eq item.itemNo }">
                                            <c:if test="${i eq 0}">
                                                <tr>
                                                    <th><h3>주문 번호 : ${userOrder.orderNo }</h3></th>
                                                    <th><c:if
                                                            test="${not empty userOrder.pay and userOrder.pay eq 'Y'}">
                                                        <button class="refundBtn" value="${userOrder.orderNo}"
                                                                data-username="${userOrder.userName}"
                                                                data-imp-uid="${userOrder.impUid}"
                                                                data-merchant-uid="${userOrder.merchantUid}"
                                                                data-totalprice="${userOrder.totalPrice}"
                                                                data-para-mount="${userOrder.paraMount}">환불
                                                        </button>
                                                    </c:if></th>
                                                </tr>
                                                <tr>
                                                    <th>주문자 : ${userOrder.userName }</th>
                                                    <th rowspan="6" class="img">
                                                        <c:choose>
                                                            <c:when test="${ item.titleImg ne 0}">
                                                                <img alt="ItemImg"
                                                                     src="/resources/img/shop/upload/${item.storedName }">
                                                            </c:when>
                                                            <c:when test="${item.titleImg eq 0}">
                                                                <img src="/resources/img/shop/nullimg.jpg"
                                                                     alt="notready">
                                                            </c:when>
                                                        </c:choose>
                                                        </td>
                                                    </th>
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
                                                    <th>결제 가격 : <fmt:setLocale value="ko_KR"/><fmt:formatNumber
                                                            type="currency" value="${orderItem.price }"/></th>
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
                                                    <td>상품명 : ${orderItem.itemName }</td>
                                                    <td>구매수량 : ${orderItem.orderQuantity }</td>

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
                </c:if>
            </c:forEach>
        </div>
    </div>
</div>
<!-- .container End -->

<c:import url="/WEB-INF/views/layout/orderPaging.jsp"/>


<c:import url="/WEB-INF/views/layout/footer.jsp"/>