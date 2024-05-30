<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Insert title here</title>
    <link rel="icon" href="<%=request.getContextPath()%>/resources/img/20191208094528217881320965.png">
    <script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
    <link rel="stylesheet" type="text/css" href="/resources/css/message/send.css">
    <script type="text/javascript">
        $(function () {

            $("#send").click(function () {
// 		console.log("버튼 클릭 인식.")
// 		console.log($("#content").val())
// 		console.log($("#touser").val())

                $.ajax({
                    type: "get"
                    , url: "./send"
                    , data: {
                        content: $("#content").val()
                        , touser: $("#touser").val()
                    }
                    , dataType: "json"
                    , success: function (res) {
                        console.log("AJAX 성공")
                        console.log(res)

                        if (res > 0) {
                            $(function () {
                                if (window.opener && !window.opener.closed) {
                                    window.opener.location.href = './sendlist';
                                    window.close();
                                }
                            })
                        }
                        window.close();
                    }
                    , error: function () {
                        console.log("AJAX 실패")
                    }
                })
            })//$("#send").click
            // 홈으로 버튼 클릭 시
            $("#homeBtn").click(function() {
                if (window.opener && !window.opener.closed) {
                    window.opener.location.href = '/';
                    window.close();
                }
            });

            // 메시지 함으로 버튼 클릭 시
            $("#messageBtn").click(function() {
                if (window.opener && !window.opener.closed) {
                    window.opener.location.href = './list';
                    window.close();
                }
            });
        })
    </script>
</head>
<body>
<div class="container">
    <h1>쪽지 보내기</h1>
    <div class="btndiv">
        <button id="homeBtn" class="button">메인 화면으로</button>
        <button id="messageBtn" class="button">메시지 함으로</button>
    </div>
    <hr>
    <div id="messageContent">
        <div class="form-group">
            <label for="touser">받는사람</label>
            <input type="text" id="touser" name="touser" placeholder="닉네임을 적어주세요">
        </div>
        <div class="form-group">
            <label for="content">내용</label>
            <textarea id="content" name="content" placeholder="내용을 적어주세요"></textarea>
        </div>
        <button id="send" class="button">쪽지 보내기</button>
    </div>
    <br>

</div>
</body>
</html>