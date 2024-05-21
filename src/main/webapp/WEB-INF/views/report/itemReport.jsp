<%--
  Created by IntelliJ IDEA.
  User: c
  Date: 24. 4. 30.
  Time: 오후 2:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <link rel="stylesheet" type="text/css" href="/resources/css/report/report.css">

</head>
<body>
<jsp:include page="/WEB-INF/views/layout/header.jsp"/>
<div class="warpper">
    <div class="warp">
        <h3>상품 신고</h3>
        <form id="reportform" method="post" action="./itemReport">
            <div>
                <label>신고하는 상품 : </label>
                <td class="title">${item.itemName }</td>
            </div>
            <div>
                <label>신고 종류</label>
                <select name="itemReportNo" id="reportTypeNo" placeholder="종류를 선택해 주세요" class="form-option">
                    <c:forEach var="reportType" items="${reportType }">
                        <option value="${reportType.itemReportNo}">${reportType.itemReport}</option>
                    </c:forEach>
                </select>
            </div>
            <div>
                <label for="reportContent">신고내용</label>
                <textarea name="reportContent" id="reportContent" placeholder="신고내용을 입력해 주세요" rows="10" cols="50"
                          style="resize: none"></textarea>
            </div>
            <div hidden="hidden">
                <label>신고하는 상품 번호 : </label>
                <input type="text" name="itemNo" id="itemNo" class="boardNo" value="${item.itemNo}"
                       style="border: 1px white" readonly>
            </div>

            <button  id="report" name="report">신고</button>
        </form>
    </div>
</div>
<jsp:include page="/WEB-INF/views/layout/footer.jsp"/>
