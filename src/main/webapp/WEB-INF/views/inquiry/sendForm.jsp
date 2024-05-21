<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Insert title here</title>
    <script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
    <link rel="stylesheet" type="text/css" href="/resources/css/message/send.css">
<script type="text/javascript">
$(function () {
    $("#send").click(function () {
        $.ajax({
            type: "get",
            url: "./send",
            data: {
                inquiryDetail : $("#inquiryDetail").val(),
                touser: $("#touser").val()
            },
            dataType: "json",
            success: function (res) {
                console.log("AJAX 성공");
                console.log(res);
                if (res > 0) {
                    window.opener.location.reload(); 
                    window.close(); 
                }
            },
            error: function () {
                console.log("AJAX 실패");
            }
        });
    });

    $("#homeBtn").click(function() {
        if (window.opener && !window.opener.closed) {
            window.opener.location.href = '/';
            window.close();
        }
    });

    $("#inquiryBtn").click(function() {
        if (window.opener && !window.opener.closed) {
            window.opener.location.href = './list';
            window.close();
        }
    });
});

window.onunload = function() {
    if (window.opener && !window.opener.closed) {
        window.opener.location.reload();
    }
};
</script>
</head>
<body>
<div class="container">
    <h1>문의하기</h1>
    <div class="btndiv">
        <button id="homeBtn" class="button">메인 화면으로</button>
        <button id="inquiryBtn" class="button">나의 문의함으로</button>
    </div>
    <hr>
    <div id="inquiryContent">
        <div class="form-group">
            <label for="inquiryDetail">내용</label>
            <textarea id="inquiryDetail" name="inquiryDetail" placeholder="문의할 내용을 적어주세요"></textarea>
        </div>
        <button id="send" class="button">보내기</button>
    </div>
    <br>

</div>
</body>
</html>