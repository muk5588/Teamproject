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

    $("#checkboxAllCheck").click(function(){
        $(".delCheckBox").prop("checked", $(this).prop("checked"));
    })

    $("#deleteBtn").click(function(){
        var datas = [];
        $("input[name=deleteNum]:checked").each(function(){
            var no = $(this).val();
            datas.push(no);
        })
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
        })
    })

    $(".saveCheckBox").on("change", function(e){
        var no = $(e.target).val();
        var check = $(e.target).is(":checked");
        $.ajaxSettings,traditional = true;
        $.ajax({
            type:"get",
            url:"./saveProc",
            data:{
                inquiryNo : no,
                check : check
            },
            dataType:"json",
            success: function(res){
                console.log("AJAX 성공");
                $(location).attr('href', './list');
            },
            error: function(){
                console.log("AJAX 실패");
            }
        })
    })

    var popupsendForm = document.querySelector('button#popupsendForm');
    popupsendForm.onclick = function (){
        var popOption = "width=500px, height=500px, top=300px, left=300px";
        var openUrl = './sendForm';
        window.open(openUrl, 'popup', popOption);
    }
})
</script>
</head>
<body>
<jsp:include page="/WEB-INF/views/layout/smallheader.jsp" />
<h1>고객문의함</h1>
<hr>
<div id="content">
    <a href="inquirylist"><button>보낸 문의함</button></a>
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
                    <input type="checkbox" value="${list.inquiryNo}" name="deleteNum" class="delCheckBox">
                </td>
                <td class="inquiryNo">${list.inquiryNo}</td>
                <td class="sender">${list.userNo}</td>
                <td class="content">${list.inquiryDetail}</td>
                <td class="date">
                    <fmt:formatDate value="${list.inquiryDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
                </td>
                <td class="read">${list.complete}</td>
            </tr>
        </c:forEach>
    </table>
    <br><br><br><br>
</div>
</body>
</html>
