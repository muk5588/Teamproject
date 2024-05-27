<%@page import="java.util.UUID"%>
<%@page import="dto.Item"%>
<%@page import="dto.OrderItem"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<c:import url="/WEB-INF/views/layout/header.jsp"/>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet"
      integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz"
        crossorigin="anonymous"></script>
    <script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
    <script>
        function DaumPostcode() {
            new daum.Postcode({
                oncomplete: function (data) {
                    // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

                    // 각 주소의 노출 규칙에 따라 주소를 조합한다.
                    // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
                    var addr = ''; // 주소 변수
                    var extraAddr = ''; // 참고항목 변수

                    //사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
                    if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
                        addr = data.roadAddress;
                    } else { // 사용자가 지번 주소를 선택했을 경우(J)
                        addr = data.jibunAddress;
                    }

                    // 사용자가 선택한 주소가 도로명 타입일때 참고항목을 조합한다.
                    if (data.userSelectedType === 'R') {
                        // 법정동명이 있을 경우 추가한다. (법정리는 제외)
                        // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
                        if (data.bname !== '' && /[동|로|가]$/g.test(data.bname)) {
                            extraAddr += data.bname;
                        }
                        // 건물명이 있고, 공동주택일 경우 추가한다.
                        if (data.buildingName !== '' && data.apartment === 'Y') {
                            extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
                        }
                        // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
                        if (extraAddr !== '') {
                            extraAddr = ' (' + extraAddr + ')';
                        }
                        // 조합된 참고항목을 해당 필드에 넣는다.
                        document.getElementById("extraAddress").value = extraAddr;

                    } else {
                        document.getElementById("extraAddress").value = '';
                    }

                    // 우편번호와 주소 정보를 해당 필드에 넣는다.
                    document.getElementById('postCode').value = data.zonecode;
                    document.getElementById("address").value = addr;
                    // 커서를 상세주소 필드로 이동한다.
                    document.getElementById("detailAddress").focus();
                }
            }).open();
        }
</script>        
<% 
	List<Item> items = (List<Item>)request.getAttribute("items");
	String itemNames = "";
	int i =0;
	for(Item o: items){
		itemNames += o.getItemName();
		if( i != 0){
			itemNames += " , ";
		}
		i++;
	}
	request.setAttribute("itemNames", itemNames);
	String uuid = UUID.randomUUID().toString().split("-")[4];
	uuid += UUID.randomUUID().toString().split("-")[4];
	request.setAttribute("uuid", uuid);
%>
<script src="https://cdn.iamport.kr/v1/iamport.js"></script>
<script type="text/javascript">

//window.IMP
//가맹점 식별코드 초기화
IMP.init('imp21765258')

       var today = new Date();   
        var hours = today.getHours(); // 시
        var minutes = today.getMinutes();  // 분
        var seconds = today.getSeconds();  // 초
        var milliseconds = today.getMilliseconds();
        var makeMerchantUid = '' +hours +  minutes + seconds + milliseconds;
        
function requestPay() {
	

IMP.request_pay({
	
	pg: "html5_inicis",						//pg 모듈 설정
	
	pay_method: "card",						//결제 수단 (필수)
// 	merchant_uid: ${userOrder.orderNo}+makeMerchantUid,	// 고유 주문번호(필수) (unique)
	merchant_uid: '${uuid}' + makeMerchantUid,	// 고유 주문번호(필수) (unique)
// 	amount: document.getElementById("totalPrice").value,		// 결제 금액 (필수)
	amount: 1,		// 결제 금액 (필수)
	
	name: '${itemNames}',				//주문 상품 이름
	
	buyer_email: '${dto1.email}',		// 주문자 정보들
	buyer_name: document.getElementById("userName").value,					// 주문자 정보들
	buyer_tel: document.getElementById("phone").value,				// 주문자 정보들
	buyer_addr: document.getElementById("address").value,	// 주문자 정보들
	buyer_postcode: document.getElementById("postCode").value					// 주문자 정보들
	
}, function (rsp) { // callback
   //rsp.imp_uid 값으로 결제 단건조회 API를 호출하여 결제결과를 판단합니다.
	
    console.log( rsp )
    console.log( rsp.imp_uid )
    //rsp.imp_uid -> 결제 실패시 null 반환
    var imp_uid = rsp.imp_uid
    if( rsp.success && imp_uid ){
    var form = $("#orderForm");

    $("<input>")
    .append(
    	$("<input>")
    	.attr({
    		type:"text"
    		, name:"impUid"
    		, value: rsp.imp_uid
    	}) )
    .appendTo(form)
    
    $("<input>")
    .append(
    	$("<input>")
    	.attr({
    		type:"text"
    		, name:"method"
    		, value: 'card'
    	}) )
    .appendTo(form)
    
    $("<input>")
    .append(
    	$("<input>")
    	.attr({
    		type:"text"
    		, name:"pay"
    		, value: "Y"
    	}) )
    .appendTo(form)
    
    $("<input>")
    .append(
    	$("<input>")
    	.attr({
    		type:"text"
    		, name:"userNo"
    		, value: ${dto1.userno}
    	}) )
    .appendTo(form)
    
    $("<input>")
    .append(
    	$("<input>")
    	.attr({
    		type:"text"
    		, name:"merchantUid"
    		, value: rsp.merchant_uid
    	}) )
    .appendTo(form)
    
    $("<input>")
    .append(
    	$("<input>")
    	.attr({
    		type:"text"
    		, name:"paraMount"
    		, value: rsp.paid_amount
    	}) )
    .appendTo(form)
    
    
   	alert('결제를 완료 했습니다')
   	
    form.submit();
    }else{
    	alert('결제 오류 입니다 [원인] : ' + rsp.error_msg)
    }
    
   
});
 
}

</script>
<script type="text/javascript">
$(function(){
	$("#payBtn").click(function(){
		console.log("결제버튼 클릭")
		requestPay();
	})
	
})
</script>
<link rel="stylesheet" href="/resources/css/order/ordersheet.css">
</head>
<body>

   <div class="container">

        <h1>결제하기</h1>
        <a href="/">
            <button>메인 페이지로</button>
        </a>
        
<div id="orderwrap">
<form method="post" action="./completed" id="orderForm">
<table>
	<tr><td>주문자명<input type="text" name="userName" id="userName" value="${userOrder.userName }"></td></tr>
	<tr><td>핸드폰번호<input type="text" name="phone" id="phone" value="${userOrder.phone }"></td></tr>
	<tr><td>배송지 우편주소<input type="text" name="postCode" id="postCode" value="${userOrder.postCode }" readonly="readonly">
		<input type="button" onclick="DaumPostcode()" value="우편번호 찾기">
	</td></tr>
	<tr><td>주소<input type="text" name="address" id="address" value="${userOrder.address }" readonly="readonly"></td></tr>
	<tr><td><input type="text" name="detailAddress" id="detailAddress" value="${userOrder.detailAddress }">	
		<input type="text" name="extraAddress" id="extraAddress"  value="${userOrder.extraAddress}" readonly="readonly">
	</td></tr>
</table>
<table>
	<tr>
	    <th>상품명</th>
	    <th>수량</th>
	    <th>이미지</th>
	    <th>가격</th>
	</tr>
	<c:choose>
	<c:when test="${not empty baskets }">
	<c:set var="sum" value="0"/>
    <c:forEach items="${items }" var="item">
    <c:forEach items="${baskets}" var="basket">
    <c:if test="${item.itemNo eq basket.itemNo }">
   		<tr>
   			<td>${item.itemName }</td>
   			<td>${basket.quantity }</td>
   			<td>
   			<c:choose>
	    	<c:when test="${not empty imgFiles}">
		    	<c:forEach items="${imgFiles}" var="files">
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
   			</td>
   			<td>${item.price * basket.quantity}
   				<c:set var="i" value="${item.price * basket.quantity }"/>
   			</td>
			<c:set var="sum" value="${sum + i}"/>
   		</tr>
   	</c:if>
   	</c:forEach>
    </c:forEach>
		<%-- <input type="hidden" id="basketNos" name="basketNos" value='${basketNos}'/> --%>
		<c:forEach items="${orderDatas}" var="orderData">
		    <input type="hidden" name="orderDatas" value="${orderData}" />
		</c:forEach>
    </c:when>
    <c:when test="${empty baskets }">
   	<c:set var="sum" value="0"/>
    <c:forEach items="${items }" var="item">
   		<tr>
   			<td>${item.itemName }</td>
   			<td>${quantity }</td>
   			<td>
   			<c:choose>
	    	<c:when test="${not empty imgFiles}">
		    	<c:forEach items="${imgFiles}" var="files">
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
   			</td>
   			<td>${item.price * quantity}
   				<c:set var="i" value="${item.price * quantity }"/>
   			</td>
			<c:set var="sum" value="${sum + i}"/>
   		</tr>
    	<input name="itemNo" value="${item.itemNo }" hidden="hidden">
    	<input name="quantity" value="${quantity }" hidden="hidden">
    </c:forEach>
    </c:when>
    </c:choose>
    <tr>
    	<td>총계 : 
    	  <td><fmt:setLocale value="ko_KR"/><fmt:formatNumber type="currency" value="${sum }"/>
    	  <input hidden="hidden" name="totalPrice" id="totalPrice" value="${sum }">
    	</td>
    </tr>
</table>
<button onclick="requestPay();" type="button">결제하기</button>
</form>
</div>
       

    </div>
    <!-- .container End -->


<c:import url="/WEB-INF/views/layout/footer.jsp"/>

