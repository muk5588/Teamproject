<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: c
  Date: 2024-04-04
  Time: 오후 12:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="icon" href="resources/img/favicon.ico">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>

</head>
<body>
<jsp:include page="/WEB-INF/views/layout/header.jsp"></jsp:include>
<div class="warpper">
    <div class="warp">
        <div id="content">
            <h3>회원 목록</h3>
            <table class="table table-striped table-hover table-sm">
                <tr>
                    <th class='w-px60'>회원번호</th>
                    <th>아이디</th>
                    <th class='w-px200'>이름</th>
                    <th>닉네임</th>
                    <th>성별</th>
                </tr>
                <!-- for(꺼낸 배열 변수를 담을 새로운 변수 (String x) : 배열 변수(list)) -->
                <!-- items : 배열 변수 -->
                <!-- var : 꺼낸 배열 변수를 담을 새로운 변수 -->
                <c:forEach var="UserDTO" items="${list}">
                    <tr>
                        <td>${UserDTO.userno }</td>
                        <td>${UserDTO.userid }</td>
                        <td><a href='/user/detailUser?userno=${UserDTO.userno}'>${UserDTO.name }</a></td>
                        <td>${UserDTO.nickname}</td>
                        <td>${UserDTO.gender }</td>
                    </a>
                    </tr>
                </c:forEach>
            </table>
        </div>
    </div>
</div>
<div>

    <ul class="pagination pagination-sm justify-content-center">
        <%-- 첫 페이지로 이동 --%>
        <c:if test="${paging.curPage ne 1 }">
            <li class="page-item">
                <a class="page-link" href="/user/userList">&larr; 처음</a>
            </li>
        </c:if>



        <%-- 이전 페이징 리스트 이동 --%>
        <c:choose>
            <c:when test="${paging.startPage ne 1 }">
                <li class="page-item">
                    <a class="page-link" href="/user/userList?curPage=${paging.startPage - paging.pageCount }">&laquo;</a>
                </li>
            </c:when>

            <c:when test="${paging.startPage eq 1 }">
                <li class="page-item">
                    <a class="page-link disabled">&laquo;</a>
                </li>
            </c:when>
        </c:choose>



        <%-- 이전 페이지로 이동 --%>
        <c:if test="${paging.curPage > 1 }">
            <li class="page-item">
                <a class="page-link" href="/user/userList?curPage=${paging.curPage - 1 }">&lt;</a>
            </li>
        </c:if>



        <%-- 페이징 번호 목록 --%>
        <c:forEach var="i" begin="${paging.startPage }" end="${paging.endPage }">
            <c:if test="${paging.curPage eq i }">
                <li class="page-item">
                    <a class="page-link active" href="/user/userList?curPage=${i }">${i }</a>
                </li>
            </c:if>

            <c:if test="${paging.curPage ne i }">
                <li class="page-item">
                    <a class="page-link" href="/user/userList?curPage=${i }">${i }</a>
                </li>
            </c:if>
        </c:forEach>

        <%-- 	<c:forEach var="i" begin="${paging.startPage }" end="${paging.endPage }"> --%>
        <!-- 	<li class="page-item"> -->
        <%-- 		<a class="page-link ${(paging.curPage eq i) ?'active' :'' }" href="/board/list?curPage=${i }">${i }</a> --%>
        <!-- 	</li>		 -->
        <%-- 	</c:forEach> --%>



        <%-- 다음 페이지로 이동 --%>
        <c:if test="${paging.curPage < paging.totalPage }">
            <li class="page-item">
                <a class="page-link" href="/user/userList?curPage=${paging.curPage + 1 }">&gt;</a>
            </li>
        </c:if>



        <%-- 다음 페이징 리스트 이동 --%>
        <c:choose>
            <c:when test="${paging.startPage ne paging.totalPage }">
                <li class="page-item">
                    <a class="page-link" href="/user/userList?curPage=${paging.startPage + paging.pageCount }">&raquo;</a>
                </li>
            </c:when>

            <c:when test="${paging.startPage eq paging.totalPage }">
                <li class="page-item">
                    <a class="page-link disabled">&raquo;</a>
                </li>
            </c:when>
        </c:choose>



        <%-- 마지막 페이지로 이동 --%>
        <c:if test="${paging.curPage ne paging.totalPage }">
            <li class="page-item">
                <a class="page-link" href="/user/userList?curPage=${paging.totalPage }">끝 &rarr;</a>
            </li>
        </c:if>
    </ul>

</div>
<jsp:include page="/WEB-INF/views/layout/footer.jsp"></jsp:include>
</body>
</html>
