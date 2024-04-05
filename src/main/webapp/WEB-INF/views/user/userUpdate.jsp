<%--
  Created by IntelliJ IDEA.
  User: c
  Date: 2024-04-04
  Time: 오후 12:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<jsp:include page="/WEB-INF/views/header.jsp"></jsp:include>
<h3>회원정보수정</h3>
<div class="warpper">
    <div class="warp">
        <form action="/user/userUpdate" method="post"> <!-- action 값을 안주면 submit을 자기 자신에게 한다 -->
            <div id="namebox">
                <label for="name">이름</label>
                <input type="text" name="name" id="name" value="${dto.name}"/><br>
            </div>
            <div id="genderbox">
                <label>성별</label>
                <input type="radio" name="gender" value="남" checked/>남
                <input type="radio" name="gender" value="여"/>여<br>
            </div>
            <div id=emailbox">
                <label for="email">이메일</label>
                <input type="email" name="email" id="email"value="${dto.name}"/><br>
            </div>
            <div id="postcodebox">
                <label for="postcode">우편번호</label>
                <input type="text" id="postcode" placeholder="우편번호" readonlyvalue="${dto.name}">
                <input type="button" onclick="DaumPostcode()" value="우편번호 찾기"><br>
            </div>
            <div id="addressbox">
                <label for="detailAddress">주소</label>
                <input type="text" id="address" placeholder="주소" readonlyvalue="${dto.name}"><br>
                <input type="text" id="detailAddress" placeholder="상세주소"value="${dto.name}">
                <input type="text" id="extraAddress" placeholder="참고항목"value="${dto.name}"><br>
            </div>
            <div id="phonebox">
                <label for="phone">전화번호</label>
                <input type="text" name="phone" id="phone"value="${dto.name}">
            </div>
            <br><br>
            <input type="submit" id="submit" name="submit" value="수정"/>
            <input type="reset" id="reset" name="reset" value="초기화"/>
        </form>
    </div>
</div>
<jsp:include page="/WEB-INF/views/footer.jsp"></jsp:include>

</body>
</html>
