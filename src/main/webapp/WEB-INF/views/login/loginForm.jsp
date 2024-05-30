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
	<link rel="icon" href="<%=request.getContextPath()%>/resources/img/20191208094528217881320965.png">
	<link href="/resources/css/login/loginForm.css" rel="stylesheet" type="text/css">
    <script type="text/javascript" src="https://code.jquery.com/jquery-3.7.1.js"></script>
    <script type="text/javascript">
        $(function () {
            $("#userid").focus()
            $("form").submit(function () {
                if ($("#userid").val() == '') {
                    alert("아이디를 입력하세요");
                    $("#userid").focus()
                    return false;
                }
                if ($('#userpw').val() == '') {
                    alert("비밀번호를 입력하세요");
                    $("#userpw").focus()
                    return false;
                }
            })
        })
    </script>
</head>
<body>
<div class="container">
	<h1>
		<a onclick="location.href='/'">
			<img alt="로고" src="<%= request.getContextPath() %>/resources/img/title/18px.png">
		</a>
	</h1>
	<ul class="links">
		<li>
			<a>로그인</a>
		</li>
	</ul>
		
		
	<c:if test="${empty dto}">
    <form action="/login/loginProc" method="post">
    	<div class="id-input input__block id-input__block">
        <input type="hidden" name="no" id="no">
        <input type="text" placeholder="ID" class="input" name="userid" id="userid" autocomplete="off"/><br>
    	</div>
    	
    	<div class="input__block">
        <input type="password" placeholder="Password" class="input" name="userpw" id="userpw" autocomplete="off"></label><br><br>
    	</div>
    	
    	
        <button class="signin__btn">로그인</button>
    </form>
        
        <!-- <button><a href="/user/insertUser">회원가입</a></button> -->
        <!-- <button type="button" onClick="location.href='/user/insertUser'">회원가입</button> -->
        
        <!-- <div class="kakao">
            <a href="https://kauth.kakao.com/oauth/authorize?response_type=code&client_id=51dc08395da36cf607b66233b4371516&redirect_uri=http://localhost:8088/login/kakaoLogin">
            <a href="/login/kakao/login">
                <img src="/resources/img/kakao_login_medium_narrow.png"></a>
             <a href="/login/naver/login"><img height="50" src="http://static.nid.naver.com/oauth/small_g_in.PNG"/></a>
	</div> -->
	<div class="social">
		<div class="kakao">
    		<a href="https://kauth.kakao.com/oauth/authorize?response_type=code&client_id=51dc08395da36cf607b66233b4371516&redirect_uri=http://localhost:8088/login/kakaoLogin">
        		<img src="/resources/img/kakao_login_medium_narrow.png">
    		</a>
		</div>
		<div class="naver">
    		<a href="/login/naver/login">
        		<!-- <img height="50" src="http://static.nid.naver.com/oauth/small_g_in.PNG"/> -->
        		<img src="/resources/img/naver_Bn_Green.png"/>
    		</a>
		</div>
    </div>
	</c:if>
</div>
<c:if test="${not empty dto and dto }">
    <a href='userDetail?userid=${dto.userid}'>마이페이지</a>
</c:if>

</body>
</html>
