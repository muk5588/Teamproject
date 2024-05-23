<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="/resources/css/message/list.css">
<link rel="stylesheet" type="text/css" href="/resources/css/common/paging.css">
<script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
<script type="text/javascript">
$(function(){
    $(".fold").hide(); // 페이지 로드시 fold 클래스 숨기기
    
    $(".view").click(function(){
        var clickedView = $(this); // 클릭한 view 요소를 저장
        clickedView.next(".fold").toggle(); // 클릭시 해당 요소 다음에 있는 fold 클래스 토글
        var messageNo = clickedView.find(".delCheckBox").val();
        console.log("펼쳐진 메시지의 번호:", messageNo);
        
        $.ajax({
            type: "get",
            url: "./readProc",
            data: {
                messageNo: messageNo,
            },
            dataType: "json",
            success: function(res){
                console.log("AJAX 성공");
                console.log(res);
                if( res > 0){
                	var readStatus = res.read;
                    var readText = readStatus === 'Y' ? 'Y' : 'Y';
                    clickedView.find('.read').text(readText); // 수정된 부분
                }
            },
            error: function(){
                console.log("AJAX 실패");
            }
        });
        
    });

    $("#checkboxAllCheck").click(function(){
        $(".delCheckBox").prop("checked", $(this).prop("checked"));
    });

    
    $("#deleteBtn").click(function(){
        var datas = [];
        $("input[name=deleteNum]:checked").each(function(){
            var no = $(this).val();
            datas.push(no);
        });

        if(datas.length <= 0){
            return false;
        }

        $.ajaxSettings.traditional = true;
        $.ajax({
            type: "get",
            url: "./delete",
            data: {
                messageNo: datas
            },
            dataType: "json",
            success: function(res){
                $(location).attr('href', './list');
            },
            error: function(){
                console.log("AJAX 실패");
            }
        });
    });

    $(".saveCheckBox").on("change", function(e) {
        var no = $(e.target).val();
        var check = $(e.target).is(":checked");

        $.ajaxSettings.traditional = true;
        $.ajax({
            type: "get",
            url: "./saveProc",
            data: {
                messageNo: no,
                check: check
            },
            dataType: "json",
            success: function(res){
                $(location).attr('href', './list');
            },
            error: function(){
                console.log("AJAX 실패");
            }
        });
    });

    var popupsendForm = document.querySelector('button#popupsendForm');
    popupsendForm.onclick = function (){
        let popOption = "width = 500px, height=500px, top=300px, left=300px";
        let openUrl = './sendForm';
        window.open(openUrl, 'popup', popOption);
    };
});
</script>
</head>
<body>
<jsp:include page="/WEB-INF/views/layout/header.jsp" />
<h1>받은 쪽지함</h1>
<hr>
<div id="content">
    <div class="tit">
        <div>
            <a href="./sendlist"><button>보낸 쪽지함</button></a>
            <button id="popupsendForm">쪽지쓰기</button>
            <a href="/"><button>홈으로</button></a>
        </div>
        <div>
            <form action="" method="get" id="searchForm">
                <input type="text" name="search" id="search" placeholder="검색하실 내용을 작성해 주세요" value="${paging.search }">
                <input hidden="hidden" name="curPage" value="${curPage}">
                <button id="serchBtn">검색</button>
            </form>
        </div>
    </div>



    <table>
        <tr>
            <th><input type="checkbox" id="checkboxAllCheck"></th>
            <th>보낸이</th>
            <th>내용</th>
            <th>전송일</th>
            <th>읽음 여부</th>
            <th>저장 여부</th>
        </tr>
        <c:forEach items="${list}" var="list">
            <tr class="view">
                <td class="checkbox"><input type="checkbox" value="${list.messageNo }" name="deleteNum" class="delCheckBox"></td>
                <td class="sender">${list.sender}</td>
                <td class="content">${list.content}</td>
                <td class="date">
                    <fmt:formatDate value="${list.sendDate }" pattern="yyyy-MM-dd HH:mm:ss"/>
                </td>
                <td class="read">${list.read}</td>
                <td class="save">
                    <c:if test="${not empty list.save and list.save eq 'Y'}">
                        <input name="saveNum" class="saveCheckBox" type="checkbox" value="${list.messageNo}" checked="checked">
                    </c:if>
                    <c:if test="${empty list.save or list.save eq 'N'}">
                        <input name="saveNum" class="saveCheckBox" type="checkbox" value="${list.messageNo}" >
                    </c:if>
                </td>
            </tr>
            <tr class="fold">
                <td colspan="6">
                    <div class="fold-content">${list.content}</div>
                </td>
            </tr>
        </c:forEach>
    </table>
    <button id="deleteBtn">삭제하기</button>
    <c:import url="/WEB-INF/views/layout/adminPaging.jsp" />

    <br><br><br><br>
</div>
</body>
</html>
