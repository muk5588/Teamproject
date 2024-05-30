<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<title>상품</title>
<link rel="icon" href="<%=request.getContextPath()%>/resources/img/20191208094528217881320965.png">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<script type="text/javascript" src="https://code.jquery.com/jquery-3.7.1.js"></script>


<link rel="stylesheet" type="text/css" href="/resources/css/common/paging.css">
<link rel="stylesheet" type="text/css" href="/resources/css/common/header.css">
<link rel="stylesheet" type="text/css" href="/resources/css/shop/admin/shoplist.css">
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
<jsp:include page="/WEB-INF/views/layout/boardmenu.jsp"/>
<!-- wrap 때문에 container가 반응형 X로 바뀜 -->
<div class="wrap mx-auto">


    <div class="container">
        <h1 style="font-size: 50px;">상품</h1>
        <div class="title">
        <a href="/">
            <button>메인 페이지로</button>
        </a>
            <form action="" method="get" id="searchForm">
                <input type="text" name="search" id="search" placeholder="검색하실 상품명을 작성해 주세요">
                <input hidden="hidden" name="curPage" value="${curPage}">
                <button id="searchBtn">검색</button>
            </form>
        </div>
        <hr>

        <c:set var="imgFiles" property="${files }"/>

            <div class="itemAll">
                <c:forEach var="item" items="${item }">
                    <ul class="oneItem">
                        <li style="list-style: none; border: 1px solid #ccc;">
                            <div class="item-img">
                                <a href="./detail?itemNo=${item.itemNo }">
                                    <c:choose>
                                        <c:when test="${not empty imgFiles}">
                                            <c:forEach items="${files}" var="files">
                                                <c:if test="${not empty files.itemNo and item.itemNo eq  files.itemNo}">
                                                    <img alt="ItemImg"
                                                         src="/resources/img/shop/upload/${files.storedName }">
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
                            <div class="item-info">
                                <a href="./detail?itemNo=${item.itemNo }">
                                        ${item.itemName}<br>
                                        <fmt:setLocale value="ko_KR"/>
                                        <fmt:formatNumber type="currency" value="${item.price}"/>
                            </div>
                        </li>
                    </ul>
                </c:forEach>
            </div>


    </div>
    <!-- .container End -->
</div>
    <c:import url="/WEB-INF/views/layout/shopPaging.jsp"/>


<c:import url="/WEB-INF/views/layout/footer.jsp"/>