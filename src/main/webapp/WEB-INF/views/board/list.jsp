<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<script type="text/javascript" src="https://code.jquery.com/jquery-3.7.1.js"></script>
<link rel="stylesheet" type="text/css" href="/resources/css/common/paging.css">
<link rel="stylesheet" type="text/css" href="/resources/css/board/boardList.css">
<title>${name }</title>

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
                        $(location).attr('href', './list?categoryNo=${param.categoryNo}&curPage=${curPage}')
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
<c:import url="/WEB-INF/views/layout/header.jsp"/>
<jsp:include page="/WEB-INF/views/layout/boardmenu.jsp"/>
<!-- wrap 때문에 container가 반응형 X로 바뀜 -->
<div class="wrap mx-auto">


    <div class="container">

        <h1>${name } 게시판</h1>
        <div class="title">
            <div class="write">
                <a href="/">
                    <button class="go_main">Home</button>
                </a>
                <c:if test="${(isLogin > 0 && (gradeNo <= dto1.gradeno ))||(dto1.gradeno == 0 || dto1.gradeno == 5000)}">
                    <form action="./write?ca" method="get">
                        <button id="btnWrite" >글쓰기</button>
                        <input hidden="hidden" name="categoryNo" value="${param.categoryNo}">
                    </form>
                </c:if>
            </div>
            <div>
                <form action="" method="get" id="searchForm">
                    <input hidden="hidden" name="categoryNo" value="${param.categoryNo}">
                    <select name="searchKind" id="searchKind">
                        <option value="title" <c:if test="${paging.searchKind == 'title'}">selected</c:if>>제목</option>
                        <option value="content" <c:if test="${paging.searchKind == 'content'}">selected</c:if>>내용
                        </option>
                    </select>
                    <input type="text" name="search" id="search" value="${paging.search }">
                    <input hidden="hidden" name="curPage" value="${curPage}">
                    <button id="searchBtn">검색</button>
                </form>
            </div>
        </div>
        <hr style="clear:both; margin-bottom: 10px">


        <table>

            <%-- <colgroup> --%>
            <%-- 	<col style="width: 10%;"> --%>
            <%-- 	<col style="width: 45%;"> --%>
            <%-- 	<col style="width: 15%;"> --%>
            <%-- 	<col style="width: 10%;"> --%>
            <%-- 	<col style="width: 20%;"> --%>
            <%-- </colgroup> --%>
            <tr>
                <c:if test="${dto1.gradeno == 0 || dto1.gradeno == 5000}">
                    <th><input type="checkbox" id="checkboxAllCheck"></th>
                </c:if>
                <th>글 번호</th>
                <th>제목</th>
                <th>작성자 닉네임</th>
                <th>조회수</th>
                <th>최초작성일</th>
                <th>추천수</th>
            </tr>
            <c:choose>
                <c:when test="${not empty list }">
                    <c:forEach var="board" items="${list }">
                        <tr>
                            <c:if test="${dto1.gradeno == 0 || dto1.gradeno == 5000}">
                                <td class="checkbox"><input type="checkbox" value="${board.boardNo }" name="deleteNum"
                                                            class="delCheckBox"></td>
                            </c:if>
                            <td class="no">${board.boardNo }</td>
                            <td class="title">
                                <c:if test="${not empty param.categoryNo }">
                                    <a href="./view?categoryNo=${param.categoryNo}&boardNo=${board.boardNo}&curPage=${curPage}">${board.title}</a>
                                </c:if>
                                <c:if test="${empty param.categoryNo }">
                                    <a href="./view?&boardNo=${board.boardNo}&curPage=${curPage}">${board.title}</a>
                                </c:if>
                            </td>
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
                </c:when>
                <c:when test="${empty list }">
                    <td colspan="7">
                        게시글이 존재하지 않습니다
                    </td>
                </c:when>
            </c:choose>

        </table>
        <c:if test="${dto1.gradeno == 0 || dto1.gradeno == 5000}">
            <button id="deleteBtn">체크 삭제</button>
        </c:if>
    </div>
    <!-- .container End -->


    <c:import url="/WEB-INF/views/layout/boardPaging.jsp"/>
</div>

<c:import url="/WEB-INF/views/layout/footer.jsp"/>