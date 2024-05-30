<%--
  Created by IntelliJ IDEA.
  User: c
  Date: 2024-03-25
  Time: 오전 9:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Title</title>
    <link rel="icon" href="resources/img/favicon.ico">
    <script type="text/javascript" src="https://code.jquery.com/jquery-3.7.1.js"></script>
    <script type="text/javascript">
        $(function () {
            $("#loginid").focus()
            $("form").submit(function () {
                if ($("#loginid").val() == '') {
                    alert("아이디를 입력하세요");
                    $("#loginid").focus()
                    return false;
                }
                if ($('#loginpw').val() == '') {
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
<c:if test="${empty dto}">
    <form action="/login/loginProc" method="post">
        <input type="hidden" name="no" id="no">
        <label for="userid">아이디: <input type="text" name="userid" id="userid" autocomplete="off"></label><br>
        <label for="userpw">비밀번호: <input type="password" name="userpw" id="userpw" autocomplete="off"></label><br><br>
        <button>로그인</button>
        <button><a href="/user/insertUser">회원가입</a></button>
        <div class="kakao">
            <a href="https://kauth.kakao.com/oauth/authorize?response_type=code&client_id=51dc08395da36cf607b66233b4371516&redirect_uri=http://localhost:8088/login/kakaoLogin">
                <img src="/resources/img/kakao_login_medium_narrow.png"></a>
             <a href="/login/naver/login"><img height="50" src="http://static.nid.naver.com/oauth/small_g_in.PNG"/></a>
        </div>
    </form>
</c:if>
<c:if test="${not empty dto and dto }">
    <a href='userDetail?userid=${dto.userid}'>마이페이지</a>
</c:if>

</body>
</html>
