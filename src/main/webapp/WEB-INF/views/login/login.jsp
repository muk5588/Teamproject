<%--
  Created by IntelliJ IDEA.
  User: c
  Date: 2024-04-03
  Time: 오후 5:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script type="text/javascript" src="https://code.jquery.com/jquery-3.7.1.js"></script>
    <script type="text/javascript">
        $(function (){
            $("#loginid").focus()
            $("form").submit(function () {
                if($("#loginid").val() == ''){
                    alert("아이디를 입력하세요");
                    $("#loginid").focus()
                    return false;
                }
                if($('#loginpw').val() == ''){
                    alert("비밀번호를 입력하세요");
                    $("#loginpw").focus()
                    return false;
                }
            })
        })
    </script>
</head>
<body>
<h1>로그인 테스트</h1>
<hr>
<c:if test="${empty login}">
    <form action="<%=request.getContextPath()%>/login/login" method="post">
        <input type="hidden" name="no" id="no">
        <label for="loginid">아이디: <input type="text" name="loginid" id="loginid"></label><br>
        <label for="loginpw">비밀번호: <input type="text" name="loginpw" id="loginpw"></label><br><br>
        <button>로그인</button>
    </form>
</c:if>
<c:if test="${not empty login and login }">
    <button onclick="location.href = '<%=request.getContextPath()%>/com.test.com.test.com.test.login/logout'">로그아웃</button>
</c:if>

</body>
</html>
