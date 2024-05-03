<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:import url="/WEB-INF/views/layout/header.jsp"/>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet"
      integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
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
</style>
<script type="text/javascript">
    $(function () {

        var blank_pattern = /^\s+|\s+$/g;

        $("#searchForm").submit(function (e) {
            console.log("#searchForm submit")

            var searchTerm = $("#search").val().trim(); // 입력값의 앞뒤 공백 제거

            if (!searchTerm) {
                alert("검색어를 입력해주세요")
                return false;
            }

            if (searchTerm.length === 0) {
                alert(' 공백만 입력되었습니다 ');
                return false;
            }

            $("#search").val(searchTerm)

        })


        $("#deleteBtn").click(function () {

            var datas = [];
            $("input[name=deleteNum]:checked").each(function () {
                var no = $(this).val();
                datas.push(no);
            })

            $.ajaxSettings, traditional = true
            $.ajax({
                type: "get"
                , url: "./listDelete"
                , data: {
                    boardNo: datas
                }
                , dataType: "json"
                , success: function (res) {
// 				console.log("AJAX 성공")
// 				console.log(res)

                    $(function () {
                        $(location).attr('href', './list?curPage=${curPage}')
                    })

                }
                , error: function () {
                    console.log("AJAX 실패")
                }
            })

        })

        $("#checkboxAllCheck").click(function () {
            //attr => 속성값, prop해서 변경해야함
            $(".delCheckBox").prop("checked", $(this).prop("checked"));

        })

    })
</script>
</head>
<body>

<!-- wrap 때문에 container가 반응형 X로 바뀜 -->
<div class="wrap mx-auto">


    <div class="container">

        <h1>내 작성글</h1>
        <a href="../main">
            <button>메인 페이지로</button>
        </a>
        <div>
            <form action="" method="get" id="searchForm">
                <select name="searchKind" id="searchKind">
                    <option value="title">제목</option>
                    <option value="content">내용</option>
                </select>
                <input type="text" name="search" id="search">
                <input hidden="hidden" name="curPage" value="${curPage}">
                <button id="serchBtn">검색</button>
            </form>
        </div>
        <hr>

        <table class="table table-striped table-hover table-sm">

            <%-- <colgroup> --%>
            <%-- 	<col style="width: 10%;"> --%>
            <%-- 	<col style="width: 45%;"> --%>
            <%-- 	<col style="width: 15%;"> --%>
            <%-- 	<col style="width: 10%;"> --%>
            <%-- 	<col style="width: 20%;"> --%>
            <%-- </colgroup> --%>
            <tr>
                <th><input type="checkbox" id="checkboxAllCheck"></th>
                <th>글 번호</th>
                <th>제목</th>
                <th>본문</th>
                <th>작성자 닉네임</th>
                <th>조회수</th>
                <th>최초작성일</th>
                <th>추천수</th>
            </tr>
            <c:forEach var="board" items="${list }">
                <tr>
                    <td class="checkbox"><input type="checkbox" value="${board.boardNo }" name="deleteNum"
                                                class="delCheckBox"></td>
                    <td class="no">${board.boardNo }</td>
                    <td class="title">
                        <a href="./view?boardNo=${board.boardNo }&curPage=${curPage}">${board.title }</a>
                    </td>
                    <td class="content">${board.content }</td>
                    <td class="nick">${board.nickName }</td>
                    <td class="hit">${board.boardView }</td>
                    <td class="date">
                        <fmt:formatDate value="${board.createDate }" pattern="yyyy-MM-dd"/>
                    </td>
                    <c:forEach items="${totalrecomm }" var="recommList">
                        <c:if test="${recommList.BOARDNO eq board.boardNo }">
                            <td><a id="totalRecommend">${recommList.GOOD }</a></td>
                        </c:if>
                    </c:forEach>
                </tr>
            </c:forEach>
            <tr>
                <td>
                    <button id="deleteBtn">체크 삭제</button>
                </td>
            </tr>
        </table>

    </div>
    <!-- .container End -->
    <c:if test="${isLogin != 0}">
    <div>
        <form action="./write" method="get">
            <button id="btnWrite" me>글쓰기</button>
        </form>
    </div>
    </c:if>

    <c:import url="/WEB-INF/views/layout/boardPaging.jsp"/>


<c:import url="/WEB-INF/views/layout/footer.jsp"/>