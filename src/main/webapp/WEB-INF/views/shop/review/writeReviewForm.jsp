<%--
  Created by IntelliJ IDEA.
  User: Seungjin
  Date: 2024-05-28
  Time: 오후 12:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>리뷰 작성</title>
    <script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
    <script type="text/javascript">

        $(function(){
            $("#write").click(function () {
// 		console.log("버튼 클릭 인식.")
// 		console.log($("#content").val())
// 		console.log($("#touser").val())

                $.ajax({
                    type: "post"
                    , url: "/shop/review/writereview?itemNo=${itemNo}"
                    , data: {
                        title: $("#title").val()
                        , content: $("#content").val()
                    }
                    , dataType: "json"
                    , success: function (res) {
                        console.log("AJAX 성공")
                        console.log(res)

                        if (res > 0) {
                            $(function () {
                                if (window.opener && !window.opener.closed) {
                                    window.opener.location.href = '/shop/detail?itemNo=${itemNo}'
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
            })


            $("#homeBtn").click(function() {
                if (window.opener && !window.opener.closed) {
                    window.opener.location.href = '../../../..';
                    window.close();
                }
            });
        })
    </script>
</head>
<body>
<div id="container">
    <h1>리뷰 작성</h1>
    <div class="btndiv">
        <button id="homeBtn" class="button">메인 화면으로</button>
    </div>
    <hr>
    <div id="reviewContent">
        <div class="form-group">
            <label for="title">리뷰 제목</label>
            <input type="text" id="title" name="title" placeholder="리뷰 제목을 적어주세요">
        </div>
        <div class="form-group">
            <label for="content">리뷰 내용</label>
            <textarea id="content" name="content" placeholder="리뷰 내용을 적어주세요"></textarea>
        </div>
        <button id="write" class="button">리뷰 작성하기</button>
    </div>
    <br>


</div>
</body>
</html>
