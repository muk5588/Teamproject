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
   			<td><fmt:setLocale value="ko_KR"/><fmt:formatNumber type="currency" value="${orderItem.price }"/>
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
   			<td><fmt:setLocale value="ko_KR"/><fmt:formatNumber type="currency" value="${orderItem.price }"/>
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

