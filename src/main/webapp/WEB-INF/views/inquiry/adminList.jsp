<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>관리자 문의함</title>
<link rel="stylesheet" type="text/css" href="/resources/css/inquiry/list.css">
<link rel="stylesheet" type="text/css" href="/resources/css/common/paging.css">
<script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
<script type="text/javascript">
$(function(){
	// 전체 선택 체크박스 클릭 시 개별 체크박스 상태 변경
    $("#checkboxAllCheck").change(function(){
        $(".delCheckBox").prop("checked", $(this).prop("checked"));
    });

	 // 개별 체크박스 클릭 시 전체 선택 체크박스 상태 변경
    $(".delCheckBox").change(function(){
        var allChecked = true;
        $(".delCheckBox").each(function(){
            if (!this.checked) {
                allChecked = false;
            }
        });
        $("#checkboxAllCheck").prop("checked", allChecked);
    });

 	// 삭제 버튼 클릭 시 선택된 문의 삭제
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
                $(location).attr('href', './adminList');
            },
            error: function(){
                console.log("AJAX 실패");
            }
        });
    });
	
 	// 문의 클릭 시 답변 상태에 따라 답변 내용 토글
    $(".inquiryTitle").click(function() {
        var complete = $(this).closest("tr").find(".read").text().trim();
        var inquiryNo = $(this).closest("tr").find(".inquiryNo").text().trim();
        if (complete === 'N') {      // 문의 답변이 안됐을 경우
            var inquiryDetail = $(this).text();
            var popOption = "width=500px, height=500px, top=300px, left=300px";
            var openUrl = './sendForm?mode=answer&inquiryNo=' + inquiryNo + '&inquiryDetail=' + encodeURIComponent(inquiryDetail);
            window.open(openUrl, 'popup', popOption);
        } else if (complete === 'Y') {		// 문의 답변이 됐을 경우
            var answer = $(this).closest("tr").find(".answer").text().trim();
            var answerDate = $(this).closest("tr").find(".answerDate").text().trim();
            var slideContent = "<tr class='answerSlide'><td colspan='6'><b>답변 내용:</b> " + answer + "<br><b>답변 날짜:</b> " + answerDate + "</td></tr>";
            if ($(this).closest("tr").next(".answerSlide").length) {
                $(this).closest("tr").next(".answerSlide").remove();
            } else {
                $(this).closest("tr").after(slideContent);
            }
        }
    });
    
 	// 검색 버튼 클릭 시 검색어를 서버로 전송
    $("#searchBtn").click(function(){
        var searchKeyword = $("#searchKeyword").val().trim();
        window.location.href = "./adminList?search=" + encodeURIComponent(searchKeyword);
    });

    // 검색어 입력하지 않고 검색 버튼 클릭 시 전체 문의 조회
    $("#searchBtn").click(function(){
        var searchKeyword = $("#searchKeyword").val().trim();
        if(searchKeyword === ''){
            window.location.href = "./adminList";
        } else {
            window.location.href = "./adminList?search=" + encodeURIComponent(searchKeyword);
        }
    });
    
});
</script>
</head>
<body>
<jsp:include page="/WEB-INF/views/layout/header.jsp" />
<h1>관리자 문의함</h1>
<hr>
<div id="content">

	<input type="text" id="searchKeyword" placeholder="검색어를 입력하세요">
    <button id="searchBtn">검색</button>

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
                <td class="content inquiryTitle" data-inquiryNo="${inquiry.inquiryNo}" data-inquiryDetail="${inquiry.inquiryDetail}">
                    ${inquiry.inquiryDetail}
                </td>
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
                <td class="answerDate" style="display: none;">${inquiry.answerDate}</td>
            </tr>
        </c:forEach>
    </table>
    <c:import url="/WEB-INF/views/layout/adminPaging.jsp" />
    
    <br><br><br><br>
</div>
</body>
</html>
