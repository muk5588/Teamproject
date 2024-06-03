<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<link rel="stylesheet" type="text/css" href="/resources/css/common/paging.css">
<link rel="stylesheet" type="text/css" href="/resources/css/shop/admin/shoplist.css">
<script type="text/javascript" src="https://code.jquery.com/jquery-3.7.1.js"></script>

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

<!-- wrap 때문에 container가 반응형 X로 바뀜 -->
<div class="wrap mx-auto">


    <div class="container">

        <h1>상품 관리</h1>
        <div class="title">
            <div>
        <a href="/user/adminPage">
            <button>관리자 메인페이지로</button>
        </a>
        <a href="./create">
            <button>상품 추가하기</button>
        </a>
            </div>

            <form action="" method="get" id="searchForm">
                <input type="text" name="search" id="search" placeholder="검색하실 상품명을 작성해 주세요">
                <input hidden="hidden" name="curPage" value="${curPage}">
                <button id="searchBtn">검색</button>
            </form>
        </div>
        <hr>

        <c:set var="imgFiles" property="${itemFiles }"/>

        <div class="itemAll">
            <c:forEach var="item" items="${items }">
                <ul class="oneItem">
                    <li style="list-style: none; border: 1px solid #ccc;">
                        <div class="item Img" style="width: 300px; height: 250px;">
                            <a href="./detail?itemNo=${item.itemNo }">
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
                            </a>
                        </div>
                        <div class="item Info">
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

    <c:import url="/WEB-INF/views/layout/shopPaging.jsp"/>


<c:import url="/WEB-INF/views/layout/footer.jsp"/>