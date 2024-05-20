<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<script type="text/javascript" src="https://code.jquery.com/jquery-3.7.1.js"></script>

<title>전체 게시판</title>
<style type="text/css">
    /* General styles */
    @import url('https://webfontworld.github.io/NexonMaplestory/NexonMaplestory.css');

    * {
        font-family: 'NexonMaplestory';
        font-weight: 300;
        font-style: normal;
    }
    body {
        font-family: Arial, sans-serif;
        line-height: 1.6;
        margin: 0;
        padding: 0;
        background-color: #f9f9f9;
    }

    h1 {
        color: #333;
        text-align: center;
        margin-top: 20px;
    }

    button {
        background-color: #007bff;
        color: white;
        border: none;
        padding: 10px 20px;
        margin: 10px 0;
        cursor: pointer;
        border-radius: 5px;
    }

    button:hover {
        background: #39a9db;
    }

    /* Container */
    .wrap {
        max-width: 1200px;
        margin: 0 auto;
        padding: 20px;
    }

    .container {
        background-color: #fff;
        padding: 20px;
        border-radius: 5px;
        box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
    }

    /* Search form */
    #searchForm {
        display: flex;
        justify-content: flex-end;
        margin-bottom: 20px;
    }

    #searchForm select,
    #searchForm input[type="text"],
    #searchForm button {
        margin: 0 5px;
        padding: 10px;
        border: 1px solid #ccc;
        border-radius: 5px;
    }

    #searchForm button {
        background-color: #28a745;
        color: white;
    }

    #searchForm button:hover {
        background-color: #218838;
    }

    /* Table styles */
    table {
        width: 100%;
        border-collapse: collapse;
        margin-bottom: 20px;
    }

    table th,
    table td {
        border: 1px solid #ddd;
        padding: 10px;
        text-align: center;
    }

    table th {
        background-color: #f2f2f2;
    }

    table tr:nth-child(even) {
        background-color: #f9f9f9;
    }

    table tr:hover {
        background-color: #f1f1f1;
    }

    .checkbox {
        text-align: center;
    }

    .title a {
        color: #007bff;
        text-decoration: none;
    }

    .title a:hover {
        text-decoration: underline;
    }

    /* Checkbox styling */
    #checkboxAllCheck {
        margin: 0;
        cursor: pointer;
    }

    .delCheckBox {
        cursor: pointer;
    }

    /* Buttons */
    #deleteBtn {
        background-color: #dc3545;
        color: white;
    }

    #deleteBtn:hover {
        background-color: #c82333;
    }

    #btnWrite {
        background-color: #007bff;
        color: white;
    }

    #btnWrite:hover {
        background-color: #0056b3;
    }

    /* Flex container for buttons */
    .button-container {
        display: flex;
        justify-content: flex-end;
        gap: 10px;
    }

    /* Responsive styles */
    @media (max-width: 768px) {
        .container {
            padding: 10px;
        }

        table,
        table th,
        table td {
            font-size: 14px;
        }

        #searchForm {
            flex-direction: column;
            align-items: stretch;
        }

        #searchForm select,
        #searchForm input[type="text"],
        #searchForm button {
            margin: 5px 0;
        }
    }

    /* Container for pagination */
    div ul {
        display: flex;
        list-style-type: none;
        padding: 0;
        justify-content: center;
        gap: 10px; /* Adds consistent spacing between elements */
    }

    /* Individual page items */
    div ul li.page-item {
        display: inline-block;
    }

    /* Links within page items */
    div ul li .page-link {
        display: block;
        padding: 8px 12px;
        text-decoration: none;
        color: #007bff;
        background-color: #fff;
        border: 1px solid #dee2e6;
        border-radius: 3px;
        transition: background-color 0.2s, color 0.2s, border-color 0.2s;
    }

    /* Hover state for links */
    div ul li .page-link:hover {
        background-color: #f8f9fa;
        color: #0056b3;
        border-color: #ced4da;
    }

    /* Active page item */
    div ul li .page-link.active {
        background-color: #007bff;
        color: white;
        border-color: #007bff;
    }

    /* Disabled page items */
    div ul li .page-link.disabled {
        color: #6c757d;
        pointer-events: none;
        background-color: #fff;
        border-color: #dee2e6;
    }

    /* Custom styles for arrows */
    div ul li .page-link:before {
        font-family: FontAwesome;
        margin-right: 5px;
    }

    div ul li .page-link[href*="&larr;"]:before { content: '\f060'; }
    div ul li .page-link[href*="&laquo;"]:before { content: '\f100'; }
    div ul li .page-link[href*="&gt;"]:before { content: '\f061'; }
    div ul li .page-link[href*="&raquo;"]:before { content: '\f101'; }
    div ul li .page-link[href*="끝"]:after { content: '\f061'; margin-left: 5px; }
    div ul li .page-link[href*="처음"]:before { content: '\f060'; margin-right: 5px; }
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
		<a href="/">
            <button class="go_main">Home</button>
        </a>
        <c:if test="${isLogin != 0}">
            <div class="write">
                <form action="./write" method="get">
                    <button id="btnWrite" me>글쓰기</button>
                </form>
            </div>
        </c:if>
        <div>
            <form action="" method="get" id="searchForm">
                <input hidden="hidden" name="categoryNo" value="${param.categoryNo}">
                <select name="searchKind" id="searchKind">
                    <option value="title"  <c:if test="${paging.searchKind == 'title'}">selected</c:if>>제목</option>
                    <option value="content" <c:if test="${paging.searchKind == 'content'}">selected</c:if>>내용</option>
                </select>
                <input type="text" name="search" id="search" value="${paging.search }">
                <input hidden="hidden" name="curPage" value="${curPage}">
                <button id="serchBtn">검색</button>
            </form>
        </div>

        <hr>
        <button id="deleteBtn">체크 삭제</button>

        <table>

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
	            	<tr>
	                    <td class="title" colspan="7">게시글이 존재하지 않습니다
	                    </td>
	                </tr>
	            </c:when>
			</c:choose>

        </table>

    </div>
    <!-- .container End -->


    <c:import url="/WEB-INF/views/layout/boardPaging.jsp"/>


<c:import url="/WEB-INF/views/layout/footer.jsp"/>