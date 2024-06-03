<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<link rel="stylesheet" href="/resources/css/shop/detail.css">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet"
      integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz"
        crossorigin="anonymous"></script>
<script type="text/javascript" src="https://code.jquery.com/jquery-3.7.1.js"></script>

<script>

    $(function () {
        $("#updateBtn").click(function () {
            var itemNo = "${item.itemNo}";

            // URL 생성
            var url = "./update?";
            url += "itemNo=" + encodeURIComponent(itemNo);

            // 페이지 이동
            window.location.href = url;
        });

        $("#deleteBtn").click(function () {
            var itemNo = "${item.itemNo}";

            // URL 생성
            var url = "./delete?";
            url += "itemNo=" + encodeURIComponent(itemNo);

            // 페이지 이동
            window.location.href = url;
        });

    })
</script>
</script>
</head>
<body>
<c:import url="/WEB-INF/views/layout/header.jsp"/>
<div class="container">
    <div class="tit">
        <h1>상품 관리 상세 정보</h1>
        <div>
            <a href="/user/adminPage">
                <button>관리자 메인페이지로</button>
            </a>
            <button type="button" id="updateBtn">수정하기</button>
            <button type="button" id="deleteBtn">삭제하기</button>
        </div>
    </div>

    <div id="itemwarp" style="border: 1px solid #ccc; padding: 20px; margin-top: 20px;">
        <table>
            <tr>
                <td>
                    <c:choose>
	                    <c:when test="${not empty itemFiles}">
	                        <c:set var="found" value="false"/>
	                        <c:forEach items="${itemFiles}" var="files">
	                            <c:if test="${item.itemNo eq files.itemNo and item.titleImg eq files.fileNo}">
	                                <img alt="ItemImg" src="/resources/itemUpload/${files.storedName}" style="width: 300px; height: 200px;">
	                                <c:set var="found" value="true"/>
	                            </c:if>
	                        </c:forEach>
	                        <c:if test="${not found}">
	                            <img src="/resources/img/shop/nullimg.jpg" alt="notready" style="width: 300px; height: 200px;">
	                        </c:if>
	                    </c:when>
	                    <c:otherwise>
	                        <img src="/resources/img/shop/nullimg.jpg" alt="notready" style="width: 300px; height: 200px;">
	                    </c:otherwise>
	                </c:choose>
                </td>
            </tr>
            <tr>
                <td>${item.itemName}</td>
            </tr>
            <tr>
                <td><fmt:setLocale value="ko_KR"/><fmt:formatNumber type="currency" value="${item.price}"/></td>
            </tr>
            <tr>
                <td>재고 : ${item.remain}</td>
            </tr>
            <tr>
                <td>${item.itemComm}</td>
            </tr>

        </table>
    </div>
    <form id="" action="" hidden="hidden" method="post"></form>


</div>
<!-- .container End -->


<c:import url="/WEB-INF/views/layout/footer.jsp"/>