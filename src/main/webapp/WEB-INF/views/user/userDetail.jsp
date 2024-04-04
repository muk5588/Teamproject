<%--
  Created by IntelliJ IDEA.
  User: c
  Date: 2024-04-04
  Time: 오후 12:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<jsp:include page="/WEB-INF/views/header.jsp"></jsp:include>
<h3>[ ${dto.name } ]고객 정보</h3>
<div class="warpper">
    <div class="warp">
        <table class='w-pct60'>
            <tr>
                <th class='w-px160'>성별</th>
                <td>${dto.gender }</td>
            </tr>
            <tr>
                <th>이메일</th>
                <td>${dto.email }</td>
            </tr>
            <tr>
                <th>전화번호</th>
                <td>${dto.no }</td>
                <td>${dto.name }</td>
                <td>${dto.userid }</td>
                <td>${dto.gender }</td>
                <td>${dto.email }</td>
                <td>${dto.postcode }</td>
                <td>${dto.address }</td>
                <td>${dto.detailAddress }</td>
                <td>${dto.extraAddress }</td>
                <td>${dto.phone }</td>
            </tr>
        </table>
        <div class='btnSet'>
            <a class='btn-fill' href="user/userList">고객 목록</a>
            <a class='btn-fill'>수정</a>
            <a class='btn-fill'>삭제</a>
        </div>
        출처: https://upcake.tistory.com/320?category=909772 [오늘이라도:티스토리]
    </div>
</div>
<jsp:include page="/WEB-INF/views/footer.jsp"></jsp:include>
</body>
</html>
