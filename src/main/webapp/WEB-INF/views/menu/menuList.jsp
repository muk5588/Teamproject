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
            <h3>회원등급 목록</h3>

            <table class='w-pct60'>
                <tr>
                    <th class='w-px60'>회원번호</th>
                    <th class='w-px200'>회원이름</th>
                    <th>등급번호</th>
                    <th>등급이름</th>
                    <th>추가사항</th>
                </tr>
                <!-- for(꺼낸 배열 변수를 담을 새로운 변수 (String x) : 배열 변수(list)) -->
                <!-- items : 배열 변수 -->
                <!-- var : 꺼낸 배열 변수를 담을 새로운 변수 -->
                <c:forEach var="Menu" items="${list}">
                    <tr>
                        <td>${Menu.userno }</td>
                        <td>${Menu.userid }</td>
                        <td>${Menu.gradeno }</td>
                        <td>${Menu.gradename }</td>
                        <td>${Menu.comm}</td>
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
