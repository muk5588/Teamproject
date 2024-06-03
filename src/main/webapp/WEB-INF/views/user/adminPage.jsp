<%--
  Created by IntelliJ IDEA.
  User: c
  Date: 2024-04-09
  Time: 오후 2:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Title</title>
    <link rel="icon" href="<%=request.getContextPath()%>/resources/img/20191208094528217881320965.png">
    <link href="/resources/css/common/common.css" rel="stylesheet" type="text/css">
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
        .container1 {
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
<jsp:include page="/WEB-INF/views/layout/header.jsp" />
<div id="button">
    <button><a href="/user/userList">회원리스트</a></button>
    <button onclick="location.href = '/grade/gradeList'">권한조회</button>
    <button onclick="location.href = '/menu/menuList'">권한부여상황</button>
    <button onclick="location.href = '/board/list'">게시글조회</button>
    <button onclick="location.href = '/user/userLog'">전체로그</button>
    <button onclick="location.href = '/user/userBlack'">블랙리스트</button>
    <button onclick="location.href = '/shop/admin/list'">상품 관리</button>
    <button onclick="location.href = '/order/admin/list'">결제 관리</button>
    <button onclick="location.href = '/report/list'">신고 관리</button>
</div>
<div class="container1">
    <div class="table-container">
        <div class="table-wrapper">
            <h3>전체 회원 목록</h3>
            <table class="table table-striped table-hover table-sm">
                <tr>
                    <th class='w-px60'>회원번호</th>
                    <th class='w-px200'>닉네임</th>
                    <th></th>
                    <th>성별</th>
                    <th>최근 접속 시간</th>
                    <th>누적 접속일</th>
                </tr>
                <!-- for(꺼낸 배열 변수를 담을 새로운 변수 (String x) : 배열 변수(list)) -->
                <!-- items : 배열 변수 -->
                <!-- var : 꺼낸 배열 변수를 담을 새로운 변수 -->
                <c:forEach var="login" items="${list2 }">
                    <tr>
                        <td class="no">${login.userNo }</td>
                        <td class="nick">${login.nickName }</td>
                        <td class="name">${login.name }</td>
                        <td class="name">${login.gender }</td>
                        <td class="hit"><fmt:formatDate value="${login.lastAccessDate }" pattern="yyyy-MM-dd"/></td>
                        <td class="count">${login.accessCount }</td>
                    </tr>
                </c:forEach>
            </table>
            <c:import url="/WEB-INF/views/layout/adminPaging.jsp" />
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
</body>
</html>