<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Insert title here</title>
    <link rel="stylesheet" type="text/css" href="/resources/css/common/paging.css">
    <script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
    <script type="text/javascript">
        $(function(){
            $(".fold").hide(); // 페이지 로드시 fold 클래스 숨기기
            
            $(".view").click(function(){
                // clickedView.next(".fold").toggle(); // 오류를 일으키는 원래 코드
                $(this).next(".fold").toggle(); // 정정된 코드
            });
                
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

                $.ajaxSettings.traditional = true;
                $.ajax({
                    type:"get"
                    ,url:"./delete"
                    ,data:{
                        messageNo: datas
                    }
                    , dataType:"json"
                    ,success: function( res ){
                        console.log("AJAX 성공")

                        $(function(){
                            $(location).attr('href', './sendlist')
                        })

                    }
                    ,error: function(){
                        console.log("AJAX 실패")
                    }
                })

            })

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
            var popupsendForm = document.querySelector('button#popupsendForm');

            popupsendForm.onclick = function (){
                let popOption = "width = 500px, height=500px, top=300px, left=300px"
                let openUrl = './sendForm'
                window.open(openUrl, 'popup', popOption);
            }

        })
    </script>
    <link rel="stylesheet" type="text/css" href="/resources/css/message/list.css">
</head>
<body>
<jsp:include page="/WEB-INF/views/layout/header.jsp"></jsp:include>

<h1>보낸 쪽지함</h1>
<hr>
<div id="content">
    <div class="tit">
        <div>
            <a href="./list">
            <button>받은 쪽지함</button>
            </a>
            <button id="popupsendForm">쪽지쓰기</button>
            <a href="/">
            <button>홈으로</button>
             </a>
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
            <th>받은이</th>
            <th>내용</th>
            <th>전송일</th>
            <th>읽음</th>
        </tr>
        <c:forEach items="${list}" var="list">
            <tr class="view">
                <td class="checkbox"><input type="checkbox" value="${list.messageNo }" name="deleteNum"
                                            class="delCheckBox"></td>
                <td class="sender">${list.sender}</td>
                <td class="content">${list.content}</td>
                <td class="date">
                    <fmt:formatDate value="${list.sendDate }" pattern="yyyy-MM-dd HH:mm:ss"/>
                </td>
                <td class="read">${list.read}</td>
            </tr>
            <tr class="fold">
                <td colspan="5">
                    <div class="fold-content">${list.content}</div>
                </td>
            </tr>
        </c:forEach>
    </table>
    <button id="deleteBtn" class="deletebutton">삭제하기</button>
         <c:import url="/WEB-INF/views/layout/adminPaging.jsp" />
    
    <br><br><br><br>


</div>

</body>
</html>