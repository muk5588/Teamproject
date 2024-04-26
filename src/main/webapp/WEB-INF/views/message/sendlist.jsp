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
	
		$(".saveCheckBox").on("change", function(e) {
			console.log("qqqeqwewq")
			console.log("tar value",  $(e.target).val(), $(e.target).is(":checked"))
			var no = $(e.target).val();
			var check = $(e.target).is(":checked");

		$.ajaxSettings,traditional = true
		$.ajax({
			type:"get"
			,url:"./saveProc"
			,data:{
				messageNo : no
				,check : check
			}
			, dataType:"json"
			,success: function( res ){
				console.log("AJAX 성공")
				console.log(res)
				
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

<h1>보낸 메일함</h1>
<hr>
<div id="content">
<table>
<tr>
	<th>받은이</th>
	<th>내용</th>
	<th>전송일</th>
	<th>읽음</th>
</tr>
<c:forEach items="${list}" var="list">
<tr>
	<td class="sender">${list.sender}</td>
	<td class="content">${list.content}</td>
	<td class="date">
		<fmt:formatDate value="${list.sendDate }" pattern="yyyy-MM-dd HH:mm:ss"/>
	</td>
	<td class="read">${list.read}</td>
</tr>
</c:forEach>
</table>
<button id="deleteBtn">삭제하기</button>
<br><br><br><br>


</div>
<a href="./list"><button>받은 쪽지함</button></a><br>
<a href="./sendForm"><button>쪽지쓰기</button></a><br>
<a href="/"><button>홈으로</button></a><br>
</body>
</html>