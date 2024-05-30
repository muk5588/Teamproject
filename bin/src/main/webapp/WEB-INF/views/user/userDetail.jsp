<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
    <link rel="icon" href="resources/img/favicon.ico">
</head>
<body>
<jsp:include page="/WEB-INF/views/layout/header.jsp"/>
<h3>${dto.nickname } 고객 정보</h3>
<div class="warpper">
    <div class="warp">
        <table class='w-pct60'>
            <tr>
                <th>회원번호</th>
                <td name="userno">${dto.userno }</td>
            </tr>
            <tr>
                <th>이름</th>
                <td>${dto.name }</td>
            </tr>
            <tr>
                <th>아이디</th>
                <td>${dto.userid }</td>
            </tr>
            <tr>
                <th>닉네임</th>
                <td>${dto.nickname }</td>
            </tr>
            <tr>
                <th>성별</th>
                <td>${dto.gender }</td>
            </tr>
            <tr>
                <th>이메일</th>
                <td>${dto.email }</td>
            </tr>
            <tr>
                <th>우편번호</th>
                <td>${dto.postcode }</td>
            </tr>
            <tr>
                <th>주소</th>
                <td>${dto.address }</td>
            </tr>
            <tr>
                <th>상세주소</th>
                <td>${dto.detailAddress }</td>
            </tr>
            <tr>
                <th>참고항목</th>
                <td>${dto.extraAddress }</td>
            </tr>
            <tr>
                <th>전화번호</th>
                <td>${dto.phone }</td>
            </tr>
        </table>
        <c:if test="${not empty isLogin and isLogin and dto.gradeno >= 1}">
            <div class='btnSet'>
                <a class='btn-fill' href="/user/updateUser">수정</a>
                <a class='btn-fill' href="/user/updatePass?userno=${dto.userno}">비밀번호번경</a>
                <a class='btn-fill' href="/user/deleteUser">삭제</a>
            </div>
        </c:if>
        <c:if test="${dto.gradeno < 1}">
            <div class='btnSet'>
                <a class='btn-fill' href="/user/userList">고객 목록</a>
                <a class='btn-fill' href="/user/updateUser?userno=${dto.userno}">수정</a>
                <a class='btn-fill' href="/user/deleteUser?userno=${dto.userno}">삭제</a>
            </div>

        </c:if>


    </div>
</div>
<jsp:include page="/WEB-INF/views/layout/footer.jsp"/>
</body>
</html>
