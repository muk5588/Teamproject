<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<script type="text/javascript" src="https://code.jquery.com/jquery-3.7.1.js"></script>

<link rel="stylesheet" type="text/css" href="/resources/css/common/paging.css">
<link rel="stylesheet" type="text/css" href="/resources/css/board/boardList.css">
<title>내 작성글</title>
<link rel="icon" href="<%=request.getContextPath()%>/resources/img/20191208094528217881320965.png">
<style type="text/css">

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
<jsp:include page="/WEB-INF/views/layout/header.jsp"/>
<jsp:include page="/WEB-INF/views/layout/boardmenu.jsp"/>
<!-- wrap 때문에 container가 반응형 X로 바뀜 -->
<div class="wrap mx-auto">
    <div class="container">
        <h1>내 작성글</h1>
        <div class="title">
            <div class="write">
                <a href="/">
                    <button class="go_main">Home</button>
                </a>
                <c:if test="${isLogin != 0}">
                        <form action="./write" method="get">
                            <button id="btnWrite" me>글쓰기</button>
                        </form>
                </c:if>
            </div>
            <div>
                <form action="" method="get" id="searchForm">
                    <select name="searchKind" id="searchKind">
                        <option value="title" <c:if test="${paging.searchKind == 'title'}">selected</c:if>>제목</option>
                        <option value="content" <c:if test="${paging.searchKind == 'content'}">selected</c:if>>내용
                        </option>
                    </select>
                    <input type="text" name="search" id="search" value="${paging.search }">
                    <input hidden="hidden" name="curPage" value="${curPage}">
                    <button id="serchBtn">검색</button>
                </form>
            </div>
        </div>
            <hr style="clear: both; margin-bottom: 10px;">

            <table>

                <%--             <colgroup>--%>
                <%--             	 <col style="width: 5%;">--%>
                <%--             	 <col style="width: 8%;">--%>
                <%--             	 <col style="width: 45%;">--%>
                <%--             	 <col style="width: 15%;">--%>
                <%--             	 <col style="width: 8%;">--%>
                <%--                 <col style="width: 10%;">--%>
                <%--                 <col style="width: 10%;">--%>
                <%--             </colgroup>--%>
                <tr>
                    <th><input type="checkbox" id="checkboxAllCheck"></th>
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
                                <td class="checkbox"><input type="checkbox" value="${board.boardNo }" name="deleteNum"
                                                            class="delCheckBox"></td>
                                <td class="no">${board.boardNo }</td>
                                <td class="title">
                                    <a href="./view?boardNo=${board.boardNo }&curPage=${curPage}&usrno=${board.userNo}">${board.title }</a>
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
                        <tr>
                            <td class="title" colspan="7">게시글이 존재하지 않습니다
                            </td>
                        </tr>
                    </c:when>
                </c:choose>
            </table>


            <button id="deleteBtn">체크 삭제</button>

        </div>
        <!-- .container End -->
    </div>

    <c:import url="/WEB-INF/views/layout/boardPaging.jsp"/>


<c:import url="/WEB-INF/views/layout/footer.jsp"/>