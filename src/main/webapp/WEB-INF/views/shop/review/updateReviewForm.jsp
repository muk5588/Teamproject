<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>리뷰 수정</title>
<link href="/resources/css/shop/admin/update.css" rel="stylesheet" type="text/css">
    <script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
    <script type="text/javascript">
        $(function(){
        	$("#update").click(function () {
        		var reviewNo = $("#reviewNum").val(); // 리뷰 번호를 가져옵니다.
        	    $.ajax({
        	        type: "post",
        	        url: "/shop/review/updatereview",
        	        data: {
        	            reviewNo: reviewNo, 
        	            title: $("#title").val(),
        	            content: $("#content").val()
        	        },
        	        dataType: "json",
        	        success: function (res) {
        	            console.log("AJAX 성공");
        	            console.log(res);
        	            // 리뷰 수정 성공 시, 부모 창 새로고침
        	            if (res > 0) {
        	                if (window.opener && !window.opener.closed) {
        	                    window.opener.location.reload();
        	                }
        	                window.close();
        	            }
        	        },
        	        error: function () {
        	            console.log("AJAX 실패");
        	        }
        	    });
        	});
        });
    </script>
</head>
<body>
<div id="container">
    <h1>리뷰 수정</h1>
    <div class="btndiv">
    </div>
    <hr>
    <div id="reviewContent">
        <div class="form-group">
            <label for="title">리뷰 제목</label>
            <input type="text" id="title" name="title" placeholder="리뷰 제목을 적어주세요" value="${reviewTitle}">
        </div>
        <div class="form-group">
            <label for="content">리뷰 내용</label>
            <textarea id="content" name="content" placeholder="리뷰 내용을 적어주세요">${reviewContent}</textarea>
        </div>
        <input type="hidden" id="reviewNum" value="${reviewNo}">
        <button id="update" class="button">리뷰 수정 완료</button>
    </div>
    <br>
</div>
</body>
</html>
