<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<link href="/resources/css/board/boardView.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="https://code.jquery.com/jquery-3.7.1.js"></script>
<style type="text/css">

    /* .wrap {
        width: 1100px;
    }

    table, th {
        text-align: center;
    } */

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

</style>
<script type="text/javascript">

    $(function () {

        $(document).ready(function () {
            //HTML전체 로딩이 끝나면 댓글을 비동기통신으로 가져오기 위해.
//             handleGetFile();
            handleFileChk();
            handleCommentDelete();
            $("#commentRefresh").click()
            if (${empty isRecomm or isRecomm eq 0}) {
                $(".cancle").toggle()
            }
            if (${not empty isRecomm and isRecomm eq 1}) {
                $(".do").toggle()
            }

        })

        $(document).ready(function () {


            $("#commentInput").css("visibility", "visible")

        })


        $(".doRecomm").click(function () {
            console.log("#doRecomm   Click")

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


                    $(".do").toggle()
                    $(".cancle").toggle()

                    if (res) {
                        $(function () {
                            $(location).attr('href', './view?categoryNo=${board.categoryNo}&boardNo=${board.boardNo }')
                        })
                    }
                }
                , error: function () {
                    console.log("AJAX 실패")
                }
            })

        })

        $("#insertBtn").click(function () {
            console.log("#insertBtn Click")

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
                , url: "/comment/insert"
                , data: {
                    boardNo: ${board.boardNo}
                    , userNo: $("#userid").val()
                    , commContent: $("#commentContent").val()
                }
                , dataType: "json"
                , success: function (res) {
                    console.log("AJAX 성공")

                    if (res) {
                        $(function () {
                            $(location).attr('href', './view?categoryNo=${board.categoryNo}&boardNo=${board.boardNo }')
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
                    , url: "/comment/delete"
                    , data: {
                        boardNo: ${board.boardNo}
                        , commNo: value
                    }
                    , dataType: "json"
                    , success: function (res) {
                        console.log("AJAX 성공")

                        if (res) {
                            $(function () {
                                $(location).attr('href', './view?categoryNo=${param.categoryNo}&boardNo=${board.boardNo }')
                            })
                        }
                    }
                    , error: function () {
                        console.log("AJAX 실패")
                    }
                })

            })
        }

        function handleFileChk() {
            console.log("handleFileChk")

            $.ajax({
                type: "get"
                , url: "./fileChk"
                , data: {
                    boardno: ${board.boardNo}
                }
                , dataType: "json"
                , success: function (res) {
                    console.log("AJAX 성공")
                    console.log(res)
//                     console.log(res[0].fileNo)
                    if (res.length > 0) {
                        var fileList = "";
                        fileList += '<p>첨부파일: '
                        for (var i = 0; res.length > i; i++) {
                            fileList += '<a href="./fileDown?fileNo=' + res[i].fileNo + '">' + res[i].originName + '<br>';
                        }
                        fileList += '</p>'
                        $("#fileDown").html(fileList);
                    }

                }
                , error: function () {
                    console.log("AJAX 실패")
                }
            })

        }


        function handleGetFile() {
            console.log("이미지 가져오기 실행.")

            $.ajax({
                type: "get"
                , url: "./boardFileChk"
                , data: {
                    boardno: ${board.boardNo}
                }
                , dataType: "json"
                , success: function (res) {
                    console.log("AJAX 성공")

                }
                , error: function () {
                    console.log("AJAX 실패")
                }
            })
        }


    })
</script>
</head>
<body>
<c:import url="/WEB-INF/views/layout/header.jsp"/>

<!-- wrap 때문에 container가 반응형 X로 바뀜 -->
<div class="wrap mx-auto">

    <header class="header text-center">
        <h1>Spring Board</h1>
    </header>

    <div class="container">

        <h1>상세보기</h1>
        <div class="tit">
            <div>
                <c:choose>
                    <c:when test="${usrno != 0 }">
                        <a href="./userbyboardlist?userno=${dto1.userno}">
                            <button>목록으로</button>
                        </a>
                    </c:when>
                    <c:otherwise>
                        <a href="./list?categoryNo=${param.categoryNo}&curPage=${curPage}">
                            <button>목록으로</button>
                        </a>
                    </c:otherwise>
                </c:choose>
                <c:if test="${board.userNo == dto1.userno }">
                    <a href="./update?boardNo=${board.boardNo }">
                        <button id="btnUpdate">수정</button>
                    </a>
                    <a href="./delete?boardNo=${board.boardNo }&categoryNo=${param.categoryNo}">
                        <button id="btnDelete">삭제</button>
                    </a>
                </c:if>
            </div>
            <div>
                <c:if test="${isLogin > 0}">
                    <div id="reBtn">
                        <div class="recommendBtn doRedomm">
                            <c:if test="${empty isRecomm or isRecomm eq 0 }">
                                <a class="doRecomm do"><img src="/resources/img/board/개추.png" height="13"
                                                            width="15">${recomm }</a>
                            </c:if>
                            <c:if test="${not empty isRecomm and isRecomm eq 1 }">
                                <a class="doRecomm cancel"
                                   style="background: #1e73be; color: white; border: none; padding: 0.5em 1em; text-align: center"><img
                                        src="/resources/img/board/개추.png" height="15" width="15">${recomm }</a>
                            </c:if>
                            <button onclick="location.href='../report/boardReport?categoryNo=${param.categoryNo}&boardno=${board.boardNo}'">
                                신고하기
                            </button>
                        </div>
                    </div>
                </c:if>
            </div>
        </div>
        <hr>

        <div id="file"></div>
        <table class="table">
            <tr>
                <td colspan="6">
                    <div id="fileDown"></div>
                </td>
            </tr>
            <tr>
                <th>글 번호</th>
                <th>제목</th>
                <th>작성자</th>
                <th>조회수</th>
                <th>작성일</th>
                <th>추천수</th>
            </tr>
            <tr class="con">
                <td class="no">${board.boardNo }</td>
                <td class="title">${board.title }</td>
                <td class="nick">${board.nickName }</td>
                <td class="hit">${board.boardView }</td>
                <td class="date">
                    <fmt:formatDate value="${board.createDate }" pattern="yyyy-MM-dd"/>
                </td>
                <td><a id="totalRecommend">${recomm }</a></td>
            </tr>
            <tr>
                <th colspan="6">본문</th>
            </tr>
            <tr class="con">
                <td class="content" colspan="6">${board.content }</td>
            </tr>
        </table>

        <hr>
        <div class="comment">
            <table>
                <tr>
                    <th>댓글 순번</th>
                    <th>작성자</th>
                    <th>댓글내용</th>
                    <th>작성일</th>
                    <c:if test="${isLogin > 0}">
                        <th>신고하기</th>
                        <th>삭제</th>
                    </c:if>
                </tr>
                <c:choose>
                    <c:when test="${not empty comment }">
                        <c:forEach var="comment" items="${comment }">
                            <tr>
                                <td class="no">${comment.commNo}</td>
                                <td>${comment.nickName }</td>
                                <td>${comment.commContent }</td>
                                <td>
                                    <fmt:formatDate value="${comment.commDate }" pattern="yyyy-MM-dd"/>
                                </td>
                                <c:if test="${isLogin > 0}">
                                    <td class="rpt">
                                        <a href='../report/commentReport?commno=${comment.commNo}&boardNo=${board.boardNo }&categoryNo=${param.categoryNo}'>
                                            <img src="/resources/img/board/신고.jpg" height="30" width="30">
                                        </a>
                                    </td>
                                </c:if>
                                <c:choose>
                                    <c:when test="${dto1.userno == comment.userNo }">
                                        <td>
                                            <button class="commentDelete" value="${comment.commNo}">삭제</button>
                                        </td>
                                    </c:when>
                                    <c:otherwise>
                                        <td>
                                        </td>
                                    </c:otherwise>
                                </c:choose>
                            </tr>
                        </c:forEach>

                    </c:when>
                    <c:when test="${empty comment }">
                        <td colspan="7">
                            댓글이 존재하지 않습니다
                        </td>
                    </c:when>
                </c:choose>
            </table>
        </div>
        <hr>
        <br>
        <c:if test="${isLogin > 0}">
            <div id="commentInput">
                <table>
                    <tr hidden="hidden">
                        <td><input type="text" value="${dto1.userno }" id="userid" name="userid"></td>
                    </tr>
                    <tr>
                        <th>닉네임</th>
                        <th>댓글내용</th>
                    </tr>
                    <tr>
                        <td><input class="form-control" type="text" value="${dto1.nickname }" id="commentWriter"
                                   aria-label="Disabled input example" disabled style="text-align: center;"></td>
                        <td>
                            <input name="commentContent" id="commentContent">
                        </td>
                    </tr>
                </table>
                <button id="insertBtn"> 댓글 작성</button>
            </div>
        </c:if>
        <!-- 	  - 로그인아이디, 댓글 입력 창, 입력 버튼 생성 -->
        <!--   - 댓글 리스트(댓글순번, 작성자, 댓글내용, 작성일, 삭제) -->

    </div> <!-- .container End -->


</div>


<c:import url="/WEB-INF/views/layout/footer.jsp"/>