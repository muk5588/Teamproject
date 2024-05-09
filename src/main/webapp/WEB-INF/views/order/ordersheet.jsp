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
<script src="https://cdn.iamport.kr/v1/iamport.js"></script>
<script type="text/javascript">

//window.IMP
//가맹점 식별코드 초기화
IMP.init('imp21765258')

function requestPay() {
	

IMP.request_pay({
	
	pg: "html5_inicis",						//pg 모듈 설정
	
	pay_method: "card",						//결제 수단 (필수)
	merchant_uid: "ORD20180131-0000012",	// 고유 주문번호(필수) (unique)
	amount: 6900,							// 결제 금액 (필수)
	
	name: "회전 의자",				//주문 상품 이름
		
	buyer_email: "gildong@gmail.com",		// 주문자 정보들
	buyer_name: "홍길동",					// 주문자 정보들
	buyer_tel: "010-4242-4242",				// 주문자 정보들
	buyer_addr: "서울특별시 강남구 신사동",	// 주문자 정보들
	buyer_postcode: "01181"					// 주문자 정보들
	
}, function (rsp) { // callback
   //rsp.imp_uid 값으로 결제 단건조회 API를 호출하여 결제결과를 판단합니다.
   
   console.log( rsp )
   
   $("<form>")
   .attr("action","")
   .attr("method","post")
   .append(
   	$("<input>")
   	.attr({
   		type:"text"
   		, name:"imp_uid"
   		, value: rsp.imp_uid
   	}) )
   .append(
   	$("<input>")
   	.attr({
   		type:"text"
   		, name:"merchant_uid"
   		, value: rsp.merchant_uid
   	}) )
   .appendTo( $(document.body) )
   .submit()
   
   
});
   
}

</script>
<script type="text/javascript">
$(function(){
	$("#payBtn").click(function(){
		console.log("결제버튼 클릭")
	})
	
	
})
</script>
</head>
<body>

   <div class="container">

        <h1>결제하기</h1>
        <a href="../main">
            <button>메인 페이지로</button>
        </a>
        
<div id="basketwrap">
<table>
	<tr>
	    <th>상품명</th>
	    <th>수량</th>
	    <th>이미지</th>
	    <th>가격</th>
	</tr>
    <c:forEach items="${orderItems }" var="orderItem">
   		<tr>
   			<td>${orderItem.itemName }</td>
   			<td>${orderItem.orderQuantity }</td>
   			<td>이미지</td>
   			<td>${orderItem.price }</td>
   		</tr>
    </c:forEach>	
</table>
<!-- <button onclick="requestPay();">결제</button> -->
<button id="payBtn">결제</button>

</div>
       

    </div>
    <!-- .container End -->

    <c:import url="/WEB-INF/views/layout/shopPaging.jsp"/>

<c:import url="/WEB-INF/views/layout/footer.jsp"/>

