<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
<script type="text/javascript">
$(function(){
	
	$("#send").click(function(){
// 		console.log("버튼 클릭 인식.")
// 		console.log($("#content").val())
// 		console.log($("#touser").val())
		
		$.ajax({
			type:"get"
			,url:"./send"
			,data:{
				content : $("#content").val()
				,touser : $("#touser").val()
			}
			, dataType:"json"
			,success: function( res ){
				console.log("AJAX 성공")
				console.log(res)
				
				if( res > 0){
					$(function(){
						$(location).attr('href', '/')
					})
				}
				
			}
			,error: function(){
				console.log("AJAX 실패")
			}
		})
	})//$("#send").click
	
})
</script>
</head>
<body>
<h1>111</h1>
<hr>

<div id="messageContnet">

받는사람<input type="text" id="touser" name="touser" placeholder="닉네임을 적어주세요"><br>
<input type="text" id="content" name="content" placeholder="내용을 적어주세요"><br>
<button id="send">쪽지 보내기</button>

</div>
<a href="/"><button>홈으로</button></a><br>
<a href="./list"><button>메시지 함으로</button></a>

</body>
</html>