<%--
  Created by IntelliJ IDEA.
  User: c
  Date: 24. 4. 30.
  Time: 오후 2:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<jsp:include page="/WEB-INF/views/layout/header.jsp"/>
<div class="warpper">
    <div class="warp">
        <h3>게시물 신고</h3>
        <form name="reportform" method="post" action="">
            <table class="table table-striped table-hover table-sm">
                <tr>
                    <th>신고 종류</th>
                    <td>
                        <select name="categoryno" id="categoryno" placeholder="종류를 선택해 주세요" class="form-option">
                            <c:forEach var="reportType" items="${list }">
                                <option value="${reportType.reportTypeNo}">${reportType.reportType}</option>
                            </c:forEach>
                        </select>
                    </td>
                </tr>
                <tr>
                    <th>신고내용</th>
                    <td class="content"><textarea name="reportContent" id="reportContent" placeholder="신고내용을 입력해 주세요" rows="10" cols="50" style="resize: none"></textarea>
                    </td>
                </tr>
                <tr>
                    <th>신고하는 글번호</th>
                    <td class="no">${board.boardNo }</td>
                </tr>
                <tr>
                    <th>신고하는 글 제목</th>
                    <td class="title">${board.title }</td>
                </tr>
                <tr>
                    <th>작정자닉네임</th>
                    <td class="nick">${board.nickName }</td>
                </tr>
            </table>
            <button type="button" id="join" name="join">신고</button>
        </form>
    </div>
</div>
<jsp:include page="/WEB-INF/views/layout/footer.jsp"/>
</body>
</html>
