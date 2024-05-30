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
    <link rel="icon" href="resources/img/favicon.ico">
</head>
<body>
<jsp:include page="/WEB-INF/views/layout/header.jsp"></jsp:include>
<h3>회원등급수정</h3>
<div class="warpper">
    <div class="warp">
        <form action="/grade/updateGrade?gradeno=${grade.gradeno}" method="post"> <!-- action 값을 안주면 submit을 자기 자신에게 한다 -->
            <div id="nobox">
                <label for="gradeno">등급 번호</label>
                <input type="text" name="gradeno" id="gradeno" value="${grade.gradeno}" readonly/><br>
            </div>
            <div id="namebox">
                <label for="gradename">등급 이름</label>
                <input type="text" name="gradename" id="gradename" value="${grade.gradename}"><br>
            </div>
            <div id="commbox">
                <label for="comm">추가 설명</label>
                <input type="text" name="comm" id="comm" value="${grade.comm}"><br>
            </div>
            <br><br>
            <input type="submit" id="submit" name="submit" value="추가"/>
            <input type="reset" id="reset" name="reset" value="초기화"/>
        </form>
    </div>
</div>
<jsp:include page="/WEB-INF/views/layout/footer.jsp"></jsp:include>

</body>
</html>
