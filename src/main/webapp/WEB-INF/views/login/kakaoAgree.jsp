<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Insert title here</title>
    <link rel="icon" href="<%=request.getContextPath()%>/resources/img/20191208094528217881320965.png">
</head>
<body>
    <c:if test="${userId eq null}">
        <a href="https://kauth.kakao.com/oauth/authorize?response_type=code&client_id=51dc08395da36cf607b66233b4371516&redirect_uri=http://localhost:8088/login/kakaoLogin">
            <img src="/resources/img/kakao_login_medium_narrow.png"></a>
        </a>
    </c:if>
    
    <c:if test="${userId ne null}">
        <h1>로그인 성공입니다</h1>
        <input type="button" value="로그아웃" onclick="location.href='./logout'">
    </c:if>
</body>
</html>




