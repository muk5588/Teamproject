<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>나의 문의함</title>
<link rel="stylesheet" type="text/css" href="/resources/css/inquiry/list.css">
<script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
<script type="text/javascript">
$(function(){
    $("#checkboxAllCheck").change(function(){
        $(".delCheckBox").prop("checked", $(this).prop("checked"));
    });

    $(".delCheckBox").change(function(){
        var allChecked = true;
        $(".delCheckBox").each(function(){
            if (!this.checked) {
                allChecked = false;
            }
        });
        $("#checkboxAllCheck").prop("checked", allChecked);
    });

    $("#deleteBtn").click(function(){
        var datas = [];
        $(".delCheckBox:checked").each(function(){
            datas.push(this.value);
        });
        if(datas.length <= 0){
            return false;
        }
        $.ajaxSettings.traditional = true;
        $.ajax({
            type:"get",
            url:"./delete",
            data:{
                inquiryNo: datas
            },
            dataType:"json",
            success: function(res){
                console.log("AJAX 성공");
                $(location).attr('href', './list');
            },
            error: function(){
                console.log("AJAX 실패");
            }
        });
    });

    $("#popupsendForm").click(function() {
        var popOption = "width=500px, height=500px, top=300px, left=300px";
        var openUrl = './sendForm?mode=inquiry';
        window.open(openUrl, 'popup', popOption);
    });

    // 답변을 보여주는 함수
    $(".content").click(function() {
        var complete = $(this).closest("tr").find(".read").text().trim();
        var inquiryNo = $(this).closest("tr").find(".inquiryNo").text().trim();
        if (complete === 'Y') {
            var content = $(this).text();
            var answer = $(this).closest("tr").find(".answer").text();
            var answerDate = $(this).closest("tr").find(".answerDate").text();
            var slideContent = "<tr class='answerSlide'><td colspan='6'>" + answer + answerDate + "</td></tr>";
            if ($(this).closest("tr").next(".answerSlide").length) {
                $(this).closest("tr").next(".answerSlide").remove();
            } else {
                $(this).closest("tr").after(slideContent);
            }
        }
    });

});
</script>
</head>
<body>
<jsp:include page="/WEB-INF/views/layout/smallheader.jsp" />
<h1>고객문의함</h1>
<hr>
<div id="content">
    <button id="popupsendForm">문의하기</button>
    <a href="/"><button>홈으로</button></a>
    <button id="deleteBtn" class="deletebutton">삭제하기</button>
    <table>
        <tr>
            <th><input type="checkbox" id="checkboxAllCheck"></th>
            <th>문의번호</th>
            <th>회원번호</th>
            <th>내용</th>
            <th>등록일</th>
            <th>답변 여부</th>
        </tr>
        <c:forEach items="${list}" var="inquiry">
            <tr>
                <td class="checkbox">
                    <input type="checkbox" value="${inquiry.inquiryNo}" name="deleteNum" class="delCheckBox">
                </td>
                <td class="inquiryNo">${inquiry.inquiryNo}</td>
                <td class="sender">${inquiry.userNo}</td>
                <td class="content">${inquiry.inquiryDetail}</td>
                <td class="date">
                    <fmt:formatDate value="${inquiry.inquiryDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
                </td>
                <td class="read">
                    <c:choose>
                        <c:when test="${inquiry.complete == 'Y'}">Y</c:when>
                        <c:otherwise>N</c:otherwise>
                    </c:choose>
                </td>
                <td class="answer" style="display: none;">${inquiry.answer}</td>
            </tr>
        </c:forEach>
    </table>
    <br><br><br><br>
</div>
</body>
</html>
