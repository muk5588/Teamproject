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
<jsp:include page="/WEB-INF/views/header.jsp" />
<h3>${loginInfo.nickname } 고객 정보</h3>
<div class="warpper">
    <div class="warp">
        <table class='w-pct60'>
            <tr>
                <th>회원번호</th>
                <td name="userno">${loginInfo.userno }</td>
            </tr>
            <tr>
                <th>이름</th>
                <td>${loginInfo.name }</td>
            </tr>
            <tr>
                <th>아이디</th>
                <td>${loginInfo.userid }</td>
            </tr>
            <tr>
                <th>닉네임</th>
                <td>${loginInfo.nickname }</td>
            </tr>
            <tr>
                <th>성별</th>
                <td>${loginInfo.gender }</td>
            </tr>
            <tr>
                <th>이메일</th>
                <td>${loginInfo.email }</td>
            </tr>
            <tr>
                <th>우편번호</th>
                <td>${loginInfo.postcode }</td>
            </tr>
            <tr>
                <th>주소</th>
                <td>${loginInfo.address }</td>
            </tr>
            <tr>
                <th>상세주소</th>
                <td>${loginInfo.detailAddress }</td>
            </tr>
            <tr>
                <th>참고항목</th>
                <td>${loginInfo.extraAddress }</td>
            </tr>
            <tr>
                <th>전화번호</th>
                <td>${loginInfo.phone }</td>
            </tr>
        </table>
        <div class='btnSet'>
            <a class='btn-fill' href="/user/userList">고객 목록</a>
            <a class='btn-fill' href="/user/updateUser?userno=${dto.userno}">수정</a>
            <a class='btn-fill' href="/user/deleteUser?userno${dto.userno}">삭제</a>
        </div>
    </div>
</div>
<jsp:include page="/WEB-INF/views/footer.jsp" />
</body>
</html>
