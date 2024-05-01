<%--
  Created by IntelliJ IDEA.
  User: c
  Date: 24. 5. 1.
  Time: 오전 10:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<jsp:include page="/WEB-INF/views/layout/header.jsp"/>
<div class="warpper">
    <div class="warp">
        <h3>게시물 신고</h3>
        <form id="reportform" method="post" action="./commentReport">
            <div>
                <label>신고 종류</label>
                <select name="reportTypeNo" id="reportTypeNo" placeholder="종류를 선택해 주세요" class="form-option">
                    <c:forEach var="reportType" items="${list }">
                        <option value="${reportType.reportTypeNo}">${reportType.reportType}</option>
                    </c:forEach>
                </select>
            </div>
            <div>
                <label for="reportContent">신고내용</label>
                <textarea name="reportContent" id="reportContent" placeholder="신고내용을 입력해 주세요" rows="10" cols="50" style="resize: none"></textarea>
            </div>
            <div>
                <label>신고하는 글번호</label>
                <input type="text" name="boardNo" id="boardNo" class="boardNo" value="${comment.boardNo }" style="border: 1px white" readonly>
            </div>
            <div>
                <label>신고하는 댓글번호</label>
                <input type="text" name="commNo" id="commNo" class="boardNo" value="${comment.commNo }" style="border: 1px white" readonly>
            </div>
            <div>
                <label>신고하는 댓글 제목</label>
                <td class="title">${comment.commContent }</td>
            </div>
            <div>
                <label>작정자닉네임</label>
                <td class="nick">${comment.nickName }</td>
            </div>
            <button type="button" id="report" name="report">신고</button>
        </form>
    </div>
</div>
<jsp:include page="/WEB-INF/views/layout/footer.jsp"/>
</body>
</html>
