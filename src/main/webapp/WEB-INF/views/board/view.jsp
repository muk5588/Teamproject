<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:import url="/WEB-INF/views/layout/header.jsp"/>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz"
        crossorigin="anonymous"></script>
<style type="text/css">

    .wrap {
        width: 1100px;
    }

    table, th {
        text-align: center;
    }

    /* <!-- body { --> */
    /* <!-- 	width: 1500px; --> */
    /* <!-- 	margin: 0 auto; --> */
    /* <!-- } --> */

    /* <!-- h1 { --> */
    /* <!-- 	text-align: center; --> */
    /* <!-- } --> */

    /* <!-- table { --> */
    /* <!-- 	border: 1px solid black; --> */
    /* <!-- 	margin: 0 auto; --> */
    /* <!-- } --> */

    /* <!-- tr, th, td { --> */
    /* <!-- 	border: 1px solid black; --> */
    /* <!-- } --> */

    /* <!-- th { --> */
    /* <!-- 	background-color: #ccc; --> */
    /* <!-- } --> */

    /* <!-- td.no, .title, .id, .nick, .hit, .date { --> */
    /* <!-- 	text-align: center; --> */
    /* <!-- } --> */

    /* <!-- td.title { --> */
    /* <!-- 	width: 200px; --> */
    /* <!-- } --> */

    /* <!-- td.content { --> */
    /* <!-- 	width: 500px; --> */
    /* <!-- } --> */

    /* <!-- td.id, .nick { --> */
    /* <!-- 	width: 150px; --> */
    /* <!-- } --> */

    /* <!-- td.hit { --> */
    /* <!-- 	width: 50px; --> */
    /* <!-- } --> */

    /* <!-- td.date { --> */
    /* <!-- 	width: 200px; --> */
    /* <!-- } --> */
    .recommendBtn {
        display: inline-block;
    }
</style>
<script type="text/javascript">
    $(function () {

        $(document).ready(function () {
            handleCommentRefresh();
            $("#commentRefresh").click()
            if (${empty login || !login}) {
                return false;
            }
            if (${recommend || empty recommend}) {
                $(".doRecomm").toggle()
            }
            if (!${recommend && not empty recommend}) {
                $(".cancle").toggle()
            }

            $("#reBtn").css("visibility", "visible")

        })

        $(document).ready(function () {

            if (${empty login || !login}) {
                return false
            }

            $("#commentInput").css("visibility", "visible")

        })


        $(".recommendBtn").click(function () {
            console.log("#recommendBtn Click")

            $.ajax({
                type: "get"
                , url: "./recommend"
                , data: {
                    boardNo: ${board.boardNo}
                }
                , dataType: "json"
                , success: function (res) {
                    console.log("AJAX 성공")
                    console.log(res)


                    $(".doRecomm").toggle()
                    $(".cancle").toggle()

                    $("#totalRecommend").html(res.totalRecommend)

                }
                , error: function () {
                    console.log("AJAX 실패")
                }
            })

        })

        $("#insertBtn").click(function () {
            console.log("#insertBtn Click")

            if ($("#commentWriter").val() !== '${userid}') {
                alert('로그인한 아이디와 다릅니다')
                return false;
            }

            var contentTerm = $("#commentContent").val().trim()
            if (!contentTerm) {
                alert("댓글 내용을 입력해주세요")
                $("#content").focus()
                return false;
            }

            if (contentTerm.length === 0) {
                alert('댓글내용이 공백만 입력되었습니다 ');
                $("#content").focus()
                return false;
            }

            $.ajax({
                type: "get"
                , url: "../comment/insert"
                , data: {
                    boardno: ${board.boardNo}
                    , userid: $("#commentWriter").val()
                    , content: $("#commentContent").val()
                }
                , dataType: "json"
                , success: function (res) {
                    console.log("AJAX 성공")

                    if (res) {
                        $(function () {
                            $(location).attr('href', './view?boardNo=${board.boardNo }')
                        })
                    }

                }
                , error: function () {
                    console.log("AJAX 실패")
                }
            })

        })

        function handleCommentDelete() {
            $(".commentDelete").click(function () {
                console.log(".commentDelete Click")
                var value = $(this).attr('value')
                console.log(".commentDelete Click", value)

                $.ajax({
                    type: "get"
                    , url: "../comment/delete"
                    , data: {
                        boardno: ${board.boardNo}
                        , commentno: value
                    }
                    , dataType: "json"
                    , success: function (res) {
                        console.log("AJAX 성공")

                        if (res) {
                            $(function () {
                                $(location).attr('href', './view?boardNo=${board.boardNo }')
                            })
                        }
                    }
                    , error: function () {
                        console.log("AJAX 실패")
                    }
                })

            })
        }

        function handleCommentRefresh() {
            $("#commentRefresh").click(function () {
                console.log("#commentRefresh Click")

                $.ajax({
                    type: "get"
                    , url: "../comment/refresh"
                    , data: {
                        boardno: ${board.boardNo}
                    }
                    , dataType: "json"
                    , success: function (res) {
                        console.log(res); // 받아온 데이터 출력

                        // 댓글을 표시하는 부분
                        var comments = res; // JSON 데이터에서 댓글 배열을 가져옴

                        // 댓글을 표시할 HTML 문자열 생성
                        var commentHTML = "";
                        commentHTML += '<table border="1px" style="width: 80%; text-align: center;">';
                        commentHTML += "<thead>";
                        commentHTML += "<tr>";
                        commentHTML += "<th>댓글 순번</th>	"
                        commentHTML += "<th>작성자</th>"
                        commentHTML += "<th>댓글내용</th>"
                        commentHTML += "<th>작성일</th>"
                        commentHTML += "</thead>";
                        commentHTML += "</tr>";
                        commentHTML += "<tbody>";
                        for (var i = 0; i < comments.length; i++) {
                            commentHTML += "<tr>";
                            commentHTML += "<td>" + comments[i].commseq + "</td>";
                            commentHTML += "<td>" + comments[i].userid + "</td>";
                            commentHTML += "<td>" + comments[i].content + "</td>";
                            commentHTML += "<td>" + comments[i].writeDate + "</td>";
                            if ('${userid}' === comments[i].userid) {
                                // 현재 사용자의 아이디와 댓글 작성자의 아이디가 같으면 삭제 버튼을 추가
                                commentHTML += "<td>"; // 삭제 버튼을 생성하는 부분 추가
                                commentHTML += "<button class='commentDelete' value='" + comments[i].commentno + "'>삭제</button>";
                                commentHTML += "</td>";
                            } else {
                                // 아니면 빈 칸으로 처리
                                commentHTML += "<td></td>";
                            }
                            commentHTML += "</tr>";
                            commentHTML += "</tbody>";

                        }
                        commentHTML += "</table>";

                        // 생성한 HTML을 삽입
                        $(".comment").html(commentHTML);

                        handleCommentDelete();

                    }
                    , error: function () {
                        console.log("AJAX 실패")
                    }
                })

            })
        }


    })
</script>
</head>
<body>

<!-- wrap 때문에 container가 반응형 X로 바뀜 -->
<div class="wrap mx-auto">

    <header class="header text-center">
        <h1>Spring Board</h1>
    </header>

    <div class="container">

        <h1>상세보기</h1>

        <a href="./list?curPage=${curPage }">
            <button>목록으로</button>
        </a>
        <a href="./update?boardNo=${board.boardNo }">
            <button id="btnUpdate">수정</button>
        </a>
        <a href="./delete?boardNo=${board.boardNo }">
            <button id="btnDelete">삭제</button>
        </a>

        <hr>

        <div>
            <button id="commentRefresh">댓글창 새로고침</button>
        </div>
        <div id="file"></div>
        <table class="table table-striped table-hover table-sm">
            <tr>
                <th>글 번호</th>
                <th>제목</th>
                <th>본문</th>
                <th>작정자 닉네임</th>
                <th>조회수</th>
                <th>최초작성일</th>
                <th>추천수</th>
            </tr>

            <tr>
                <td class="no">${board.boardNo }</td>
                <td class="title">${board.title }</td>
                <td class="content">${board.content }</td>
                <td class="nick">${board.nickName }</td>
                <td class="hit">${board.boardView }</td>
                <td class="date">
                    <fmt:formatDate value="${board.createDate }" pattern="yyyy-MM-dd"/>
                </td>
                <td><a id="totalRecommend">${recomm }</a></td>
            </tr>
        </table>

        <div id="reBtn" style="visibility: hidden">
            <div class="recommendBtn cancle">
                <%-- 	<c:if test="${not empty recommend  and recommend }"> --%>
                <a>
                    <button class="cancle">추천취소하기</button>
                </a>
            </div>


            <div class="recommendBtn doRedomm">
                <a>
                    <button class="doRecomm">추천하기</button>
                </a>
            </div>
        </div>

        <div class="comment">
            <hr>
            <!-- 	<table border="1px" style="width: 80%; text-align: center;"> -->
            <!-- 		<tr> -->
            <!-- 			<th>댓글 순번</th>		 -->
            <!-- 			<th>작성자</th>		 -->
            <!-- 			<th>댓글내용</th>		 -->
            <!-- 			<th>작성일</th>		 -->
            <!-- 		</tr> -->
            <%-- 		<c:forEach var="comment" items="${comment }"> --%>
            <!-- 		<tr> -->
            <%-- 			<td class="no">${comment.commseq}</td>		 --%>
            <%-- 			<td>${comment.userid }</td>		 --%>
            <%-- 			<td>${comment.content }</td>		 --%>
            <!-- 			<td> -->
            <%-- 				<fmt:parseDate value="${comment.writeDate }" pattern="yyyy-MM-dd HH:mm:ss" var="writeDate"/> --%>
            <%-- 				<fmt:formatDate value="${writeDate }" pattern="yyyy-MM-dd HH:mm:ss"/> --%>
            <!-- 			</td> -->
            <%-- 			<c:if test="${userid eq comment.userid }"> --%>
            <%-- 				<td><button class="commentDelete" value="${comment.commentno}">삭제</button></td> --%>
            <%-- 			</c:if>		 --%>
            <!-- 		</tr> -->
            <%-- 		</c:forEach> --%>
            <!-- 	</table> -->
        </div>

        <div id="commentInput" style="visibility: hidden">
            <hr>
            <br>
            <table>
                <tr>
                    <th>아이디</th>
                    <th>댓글내용</th>
                </tr>
                <tr>
                    <td><input class="form-control" type="text" value="${userid }" id="commentWriter"
                               aria-label="Disabled input example" disabled style="text-align: center;"></td>
                    <td>
                        <input name="commentContent" id="commentContent">
                    </td>
                    <td>
                        <button id="insertBtn"> 댓글 작성</button>
                    </td>
                </tr>
            </table>
        </div>
        -
        <!-- 	  - 로그인아이디, 댓글 입력 창, 입력 버튼 생성 -->
        <!--   - 댓글 리스트(댓글순번, 작성자, 댓글내용, 작성일, 삭제) -->

    </div> <!-- .container End -->


</div>


<c:import url="/WEB-INF/views/layout/footer.jsp"/>