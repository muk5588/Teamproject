<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
<script type="text/javascript">
$(function(){

	$("#checkboxAllCheck").click(function(){
		//attr => 속성값, prop해서 변경해야함
		$(".delCheckBox").prop("checked", $(this).prop("checked"));
	})
	
		$("#deleteBtn").click(function(){
		
		var datas = [];
		$("input[name=deleteNum]:checked").each(function(){
			var no = $(this).val();
			datas.push(no);
		})
// 		console.log("값:",datas)
		if( datas.length <= 0){
// 			console.log("값이 없음")
			
			return false;
		}
		
		$.ajaxSettings,traditional = true
		$.ajax({
			type:"get"
			,url:"./delete"
			,data:{
				messageNo : datas
			}
			, dataType:"json"
			,success: function( res ){
				console.log("AJAX 성공")
				
				$(function(){
					$(location).attr('href', './list')
				})
				
			}
			,error: function(){
				console.log("AJAX 실패")
			}
		})
		
	})
	
})
</script>
</head>
<body>

<h1>메시지함</h1>
<hr>
<div id="content">
<table style="align-content: center;">
<tr>
	<th><input type="checkbox" id="checkboxAllCheck"></th>
	<th>보낸이</th>
	<th>내용</th>
	<th>전송일</th>
	<th>읽음 여부</th>
	<th>저장 여부</th>
</tr>
<c:forEach items="${list}" var="list">
<tr>
	<td class="checkbox"><input type="checkbox" value="${list.messageNo }" name="deleteNum" class="delCheckBox"></td>
	<td class="sender">${list.sender}</td>
	<td class="content">${list.content}</td>
	<td class="date">
		<fmt:formatDate value="${list.sendDate }" pattern="yyyy-MM-dd HH:mm:ss"/>
	</td>
	<td class="read">${list.read}</td>
	<td class="save"><input type="checkbox" value="${not empty list.read || list.save eq 'Y'}"></td>
	<td >
</tr>
</c:forEach>
</table>
<button id="deleteBtn">삭제하기</button>
<br><br><br><br>


</div>
<a href="./sendForm"><button>쪽지쓰기</button></a><br>
<a href="/"><button>홈으로</button></a><br>
</body>
</html>