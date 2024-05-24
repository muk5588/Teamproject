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
    <link href="/resources/css/common/common.css" rel="stylesheet" type="text/css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz"
            crossorigin="anonymous"></script>

</head>
<body>
<jsp:include page="/WEB-INF/views/layout/header.jsp"></jsp:include>
<div class="warpper">
    <div class="warp">
        <div id="content">
            <h3>회원 목록</h3>
            <div>
                <form class="d-flex mb-3" action="${URL }" method="get">
                    <select class="form-select me-2" name="searchKind" aria-label="Search Type">
                        <option value="nickName"
                                <c:if test="${not empty paging.searchKind and paging.searchKind eq 'nickName'}">selected="selected"</c:if>>
                            닉네임
                        </option>
                        <option value="userno"
                                <c:if test="${not empty paging.searchKind and paging.searchKind eq 'userno'}">selected="selected"</c:if>>
                            회원 번호
                        </option>
                    </select>
                    <input class="form-control me-2" type="search" name="search" placeholder="Search"
                           aria-label="Search" value="${paging.search }">
                    <button class="btn btn-outline-success" type="submit">Search</button>
                </form>
            </div>
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
                            <td><a href='/user/detailUser?userno=${UserDTO.userno}'>${UserDTO.userid }</a></td>
                            <td>${UserDTO.name }</td>
                            <td>${UserDTO.nickname}</td>
                            <td>${UserDTO.gender }</td>

                    </tr>

                </c:forEach>
            </table>
        </div>
    </div>
</div>
<div>

    <c:import url="/WEB-INF/views/layout/adminPaging.jsp"/>

</div>
<jsp:include page="/WEB-INF/views/layout/footer.jsp"></jsp:include>
</body>
</html>
