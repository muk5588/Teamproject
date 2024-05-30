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
    <link rel="icon" href="<%=request.getContextPath()%>/resources/img/20191208094528217881320965.png">
    <link href="/resources/css/common/common.css" rel="stylesheet" type="text/css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
    <link href="/resources/css/grade/gradeList.css" rel="stylesheet" type="text/css">
    

</head>
<body>
<jsp:include page="/WEB-INF/views/layout/header.jsp"></jsp:include>
<%-- <div class="warpper">
    <div class="warp">
        <div id="content">
            <h3>회원등급 목록</h3>

            <table class='table table-striped table-hover table-sm'>
                <tr>
                    <th class='w-px60'>등급번호</th>
                    <th class='w-px200'>등급이름</th>
                    <th>설명</th>
                </tr>
                <!-- for(꺼낸 배열 변수를 담을 새로운 변수 (String x) : 배열 변수(list)) -->
                <!-- items : 배열 변수 -->
                <!-- var : 꺼낸 배열 변수를 담을 새로운 변수 -->
                <c:forEach var="Grade" items="${list}">
                    <tr>
                        <td>${Grade.gradeno }</td>
                        <td><a href='/grade/gradeDetail?gradeno=${Grade.gradeno}'>${Grade.gradename }</a></td>
                        <td>${Grade.comm}</td>
                    </tr>
                </c:forEach>
            </table>
        </div>
    </div>
</div> --%>

    <h3>회원등급 목록</h3>
		<div class="warpper">
    <div class="warp">

	<ul class="grade">
    	<li>
      		<a href="/grade/gradeList" id="gradeList">권한조회</a>
    	</li>
   	 	<li>
      		<a href="/grade/gradeInsert" id="gradeInsert">권한추가</a>
    	</li>
  	</ul>

        <div class="content" id="content">
            <div class="list-grade grade__block list-grade__block">
            <table class='table table-striped table-hover table-sm'>
                <tr>
                    <th class='w-px60'>등급번호</th>
                    <th class='w-px200'>등급이름</th>
                    <th>설명</th>
                </tr>
                <!-- for(꺼낸 배열 변수를 담을 새로운 변수 (String x) : 배열 변수(list)) -->
                <!-- items : 배열 변수 -->
                <!-- var : 꺼낸 배열 변수를 담을 새로운 변수 -->
                <c:forEach var="Grade" items="${list}">
                    <tr>
                        <td>${Grade.gradeno }</td>
                        <td><a href='/grade/gradeDetail?gradeno=${Grade.gradeno}'>${Grade.gradename }</a></td>
                        <td>${Grade.comm}</td>
                    </tr>
                </c:forEach>
            </table>
            </div>
        </div>
    </div>
</div>
<div>

    

</div>

<jsp:include page="/WEB-INF/views/layout/footer.jsp"></jsp:include>
</body>
</html>
