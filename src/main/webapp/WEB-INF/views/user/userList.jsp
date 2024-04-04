<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: c
  Date: 2024-04-04
  Time: 오후 12:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<jsp:include page="/WEB-INF/views/header.jsp"></jsp:include>
<div class="warpper">
    <div class="warp">
        <div id="content">
            <h3>회원 목록</h3>
            <table class='w-pct60'>
                <tr>
                    <th class='w-px60'>회원번호</th>
                    <th class='w-px200'>이름</th>
                    <th>아이디</th>
                    <th>성별</th>
                    <th>이메일</th>
                    <th>우편번호</th>
                    <th>주소</th>
                    <th>상세주소</th>
                    <th>참고항목</th>
                    <th>전화번호</th>
                </tr>
                <!-- for(꺼낸 배열 변수를 담을 새로운 변수 (String x) : 배열 변수(list)) -->
                <!-- items : 배열 변수 -->
                <!-- var : 꺼낸 배열 변수를 담을 새로운 변수 -->
                <c:forEach items="${list }" var="dto">
                    <tr><a href='detail?id=${dto.userid}'>
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
                    </a>
                    </tr>
                </c:forEach>
            </table>
        </div>
    </div>
</div>
<jsp:include page="/WEB-INF/views/footer.jsp"></jsp:include>
</body>
</html>
