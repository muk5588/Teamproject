<%--
  Created by IntelliJ IDEA.
  User: c
  Date: 24. 4. 19.
  Time: 오전 10:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
    <link rel="icon" href="resources/img/favicon.ico">
    <script src="/resources/js/user/password.js" charset="UTF-8"></script>
</head>
<body>
<jsp:include page="/WEB-INF/views/layout/header.jsp"/>
<h3>${dto.nickname } 고객 정보</h3>
<div class="warpper">
    <div class="warp">
        <div id=emailbox">
            <label for="email">이메일</label>
            <input type="email" name="email" id="email" value="${dto.email }" readonly>
            <button type="button" id="checkmail">인증번호 발송</button>
            <br>
            <div id="alertemail" name="alertemail"></div>
            <input type="text" id="checkcode" placeholder="인증번호를 입력해주세요" disabled="disabled">
            <br>
        </div>
        <form action="/user/userPass" method="post" id="passwordform">
            <div hidden="hidden">
                <input type="email" name="email" id="email2" value="${dto.email }" readonly>
                <input type="text" name="name" id="name"value="${dto.name}">
            </div>
            <div id="userinfo" hidden>
            <div id="useridbox">
                <label for="userid">아이디</label>
                <input type="text" name="userid" id="userid" value="${dto.userid }" readonly>
                <div id="alertid" name="alertid"></div>
            </div>
            <div id="userpwbox">
                <label for="userpw">비밀번호</label>
                <input type="text" name="userpw" id="userpw">
                <div id="alertpw" name="alertpw"></div>

                <label for="userpwchk">비밀번호 확인</label>
                <input type="text" name="userpwchk" id="userpwchk">
                <div id="alertpw2" name="alertpw2"></div>
            </div>
            <button type="button" id="update" name="update">비밀번호변경</button>
            <button type="reset" id="reset" name="reset">초기화</button>
            </div>
        </form>
    </div>
</div>
<jsp:include page="/WEB-INF/views/layout/footer.jsp"/>
</body>
</html>
