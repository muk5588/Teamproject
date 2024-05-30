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

<style type="text/css">
	body {
		font-family: Arial, sans-serif;
		background-color: #f8f9fa;
		margin: 0;
		padding: 0;
	}

	.container {
		margin-top: 30px;
	}

	h1 {
		text-align: center;
		margin-bottom: 20px;
		color: #343a40;
	}

	button {
		background-color: #007bff;
		border: none;
		color: white;
		padding: 10px 20px;
		text-align: center;
		text-decoration: none;
		display: inline-block;
		font-size: 16px;
		margin: 4px 2px;
		cursor: pointer;
		border-radius: 4px;
	}

	button:hover {
		background-color: #0056b3;
	}

	#orderwrap {
		background-color: #ffffff;
		padding: 20px;
		border-radius: 8px;
		box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
	}

	table {
		width: 100%;
		border-collapse: collapse;
		margin-top: 20px;
	}

	th, td {
		border: 1px solid #dee2e6;
		padding: 10px;
		text-align: left;
	}

	th {
		background-color: #f8f9fa;
		font-weight: bold;
	}



	@media (max-width: 768px) {
		h1 {
			font-size: 24px;
		}

		button {
			font-size: 14px;
			padding: 8px 16px;
		}

		th, td {
			font-size: 14px;
			padding: 8px;
		}


	}
</style>
</head>
<body>

   <div class="container">

        <h1>구매 상품 확인</h1>
        <a href="/">
            <button>메인 페이지로</button>
        </a>
        <a href="/order/history">
        	<button>전체 구매목록 조회</button>
        </a>
<div id="orderwrap">
<table>
<tr>
	<th colspan="4">주문자 : ${userOrder.userName }</th>
</tr>
<tr>
	<th colspan="4">배송지 : ${userOrder.address }</th>
</tr>
<tr>
	<th colspan="4">배송지 세부 주소 : ${userOrder.detailAddress }</th>
</tr>
<tr>
	<th colspan="4">배송지 세부 주소 추가 : ${userOrder.extraAddress }</th>
</tr>
	<c:set var="sum" value="0"/>
	<c:choose>
	<c:when test="${not empty orderItems }">
    <c:forEach items="${orderItems}" var="orderItem">
   		<tr>
   			<td>상품명 : ${orderItem.itemName }</td>
   			<td>구매수량 : ${orderItem.orderQuantity }</td>
   			<td>
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
   			</td>
   			<td>결제금액 : <fmt:setLocale value="ko_KR"/><fmt:formatNumber type="currency" value="${orderItem.price }"/>
   			</td>
			<c:set var="i" value="${orderItem.price}"/>
			<c:set var="sum" value="${sum + i}"/>
   		</tr>
    </c:forEach>
    </c:when>
    <c:when test="${not empty orderItem }">
   		<tr>
   			<td>상품명 : ${orderItem.itemName }</td>
   			<td>구매수량 : ${orderItem.orderQuantity }</td>
   			<td>
   			<c:choose>
	    	<c:when test="${not empty imgFiles}">
		    	<c:if test="${not empty files.itemNo and item.itemNo eq  files.itemNo}">
		    		<img alt="ItemImg" src="/resources/img/shop/upload/${files.storedName }">
		    	</c:if>
		    	<c:if test="${empty files.itemNo}">
		    		<img src="/resources/img/shop/nullimg.jpg" alt="notready">
		    	</c:if>
	   		</c:when>
	   		<c:when test="${empty imgFiles }">
	    		<img src="/resources/img/shop/nullimg.jpg" alt="notready">
	   		</c:when>
   			</c:choose>
   			</td>
   			<td>결제금액 :<fmt:setLocale value="ko_KR"/><fmt:formatNumber type="currency" value="${orderItem.price }"/>
   			</td>
			<c:set var="i" value="${orderItem.price}"/>
			<c:set var="sum" value="${sum + i}"/>
   		</tr>
    </c:when>
    </c:choose>
</table>
       

    </div>
</div>
    <!-- .container End -->


<c:import url="/WEB-INF/views/layout/footer.jsp"/>

