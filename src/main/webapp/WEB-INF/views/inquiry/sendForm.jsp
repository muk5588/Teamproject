<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>문의하기</title>
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
                    touser: $("#touser").val(),
                    inquiryNo: $("#inquiryNo").val(),
                    answer: $("#answer").val(),
                    answerDate: $("#answerDate").val()
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

        $("#sendForUser").click(function () {
            $.ajax({
                type: "post",
                url: "./answerProc",
                data: {
                    inquiryNo: $("#inquiryNo").val(),
                    answer: $("#answer").val(),
                    answerDate: $("#answerDate").val()

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
    <h1>
        <c:choose>
            <c:when test="${param.mode == 'answer'}">
                답변하기
            </c:when>
            <c:when test="${param.mode == 'inquiry'}">
                문의하기
            </c:when>
        </c:choose>
    </h1>
    <div class="btndiv">
        <button id="homeBtn" class="button">메인 화면으로</button>
        <button id="inquiryBtn" class="button">문의함으로</button>
    </div>
    <hr>
    <div id="inquiryContent">
        <div class="form-group">
            <label for="inquiryDetail">${param.mode == 'inquiry' ? '문의 내용' : '유저 문의 내용'}</label>
            <textarea id="inquiryDetail" name="inquiryDetail" ${param.mode == 'inquiry' ? '' : 'readonly'} placeholder="${param.mode == 'inquiry' ? '문의할 내용을 적어주세요' : ''}">${param.inquiryDetail}</textarea>
        </div>
        <c:if test="${param.mode == 'answer'}">
            <div class="form-group">
                <label for="answer">답변 내용</label>
                <textarea id="answer" name="answer" placeholder="답변할 내용을 적어주세요"></textarea>
            </div>
            <button id="sendForUser" class="button">답변하기</button>
        </c:if>
        <input type="hidden" id="inquiryNo" name="inquiryNo" value="${param.inquiryNo}"/>
        
        <c:if test="${param.mode == 'inquiry'}">
            <button id="send" class="button">문의하기</button>
        </c:if>
    </div>
    <br>
</div>
</body>
</html>
