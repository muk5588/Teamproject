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
    <link rel="icon" href="<%=request.getContextPath()%>/resources/img/20191208094528217881320965.png">
    <link href="/resources/css/grade/gradeDetail.css" rel="stylesheet" type="text/css">

</head>
<body>
<jsp:include page="/WEB-INF/views/layout/header.jsp"></jsp:include>
<h3>${grade.gradename } 고객 정보</h3>
<div class="warpper">
    <div class="warp">
        <table class='w-pct60'>
            <tr>
                <th>등급 번호</th>
                <td name="gradeno">${grade.gradeno }</td>
            </tr>
            <tr>
                <th>등급 이름</th>
                <td>${grade.gradename }</td>
            </tr>
            <tr>
                <th>추가 설명</th>
                <td>${grade.comm }</td>
            </tr>
        </table>
        <div class='btnSet'>
            <a class='btn-fill' href="/grade/gradeList">등급 목록</a>
            <a class='btn-fill' href="/grade/gradeUpdate?gradeno=${grade.gradeno}">수정</a>
            <a class='btn-dtl' href="/grade/deleteGrade?gradeno=${grade.gradeno}">삭제</a>
        </div>
    </div>
</div>
<jsp:include page="/WEB-INF/views/layout/footer.jsp"></jsp:include>
</body>
</html>
