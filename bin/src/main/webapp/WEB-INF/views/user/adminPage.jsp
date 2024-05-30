<%--
  Created by IntelliJ IDEA.
  User: c
  Date: 2024-04-09
  Time: 오후 2:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
    <link rel="icon" href="resources/img/favicon.ico">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz"
            crossorigin="anonymous"></script>
    <style type="text/css">
        /* 전체 페이지 스타일 */
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            background-color: #f4f4f4;
        }

        .container {
            max-width: 1200px;
            margin: 20px auto;
            padding: 20px;
        }

        /* 페이지 제목 스타일 */
        h3 {
            color: #333;
        }

        /* 버튼 스타일 */
        #button {
            margin-bottom: 20px;
            text-align: center;
        }

        button {
            padding: 10px 20px;
            background-color: #007bff;
            color: #fff;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            margin-right: 10px;
        }

        button a {
            color: #fff;
            text-decoration: none;
        }

        button:hover {
            background-color: #0056b3;
        }

        /* 테이블 스타일 */
        table {
            width: 100%;
            border-collapse: collapse;
            margin-bottom: 20px;
        }

        th, td {
            padding: 8px;
            text-align: left;
            border-bottom: 1px solid #ddd;
        }

        th {
            background-color: #f2f2f2;
        }

        /* 페이지네이션 스타일 */
        #bar {
            text-align: center;
        }

        .pagination {
            display: inline-block;
        }

        .page-item {
            display: inline-block;
        }

        .page-link {
            color: #007bff;
            padding: 5px 10px;
            text-decoration: none;
        }

        .page-link:hover {
            background-color: #f2f2f2;
        }

        .active .page-link {
            background-color: #007bff;
            color: #fff;
        }

        .container {
            max-width: 1200px;
            margin: 20px auto;
            padding: 20px;
            display: flex;
            justify-content: space-between;
        }

        .table-container {
            display: flex;
            flex-grow: 1;
        }

        .table-wrapper, #content2 {
            margin-right: 20px;
            flex-grow: 1;
        }

        .table-wrapper:last-child {
            margin-right: 0;
        }

        #content1, #content2, #content3 {
            flex-grow: 1;
        }
    </style>
</head>
<body>
<h3>관리자 페이지</h3>
<hr>
<div id="button">
    <button><a href="/user/insertUser">회원추가</a></button>
    <button><a href="/grade/gradeInsert">권한추가</a></button>
    <button onclick="location.href = '/grade/gradeList'">권한조회</button>
    <button onclick="location.href = '/menu/menuList'">권한부여상황</button>
    <button onclick="location.href = '/user/userList'">유저조회</button>
</div>
<div class="container">
    <div class="table-container">
        <div class="table-wrapper">
            <h3>전체 회원 목록</h3>
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
                        <td><a href='detailUser?userno=${UserDTO.userno}'>${UserDTO.name }</a></td>
                        <td>${UserDTO.nickname}</td>
                        <td>${UserDTO.gender }</td>
                        </a>
                    </tr>
                </c:forEach>
            </table>
        </div>
        <div id="content2">
            <h3>회원등급 목록</h3>

            <table class='table table-striped table-hover table-sm'>
                <tr>
                    <th class='w-px60'>등급번호</th>
                    <th class='w-px200'>등급이름</th>
                    <th>설명</th>
                </tr>
                <!-- for(꺼낸 배열 변수를 담을 새로운 변수 (String x) : 배열 변수(list)) -->
                <!-- items : 배열 변수 -->
                <!-- var : 꺼낸 배열 변수를 담을 새로운 변수 -->
                <c:forEach var="Grade" items="${list3}">
                    <tr>
                        <td>${Grade.gradeno }</td>
                        <td><a href='/grade/gradeDetail?gradeno=${Grade.gradeno}'>${Grade.gradename }</a></td>
                        <td>${Grade.comm}</td>
                    </tr>
                </c:forEach>
            </table>
        </div>
    </div>
</div>
<div id="bar">
    <div id="content3">
        <h3>전체 작성글 목록</h3>
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
                    <td><a href='detailUser?userno=${UserDTO.userno}'>${UserDTO.name }</a></td>
                    <td>${UserDTO.nickname}</td>
                    <td>${UserDTO.gender }</td>
                    </a>
                </tr>
            </c:forEach>
        </table>
    </div>
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
                    <a class="page-link"
                       href="/user/userList?curPage=${paging.startPage - paging.pageCount }">&laquo;</a>
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
                    <a class="page-link"
                       href="/user/userList?curPage=${paging.startPage + paging.pageCount }">&raquo;</a>
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

</body>
</html>
