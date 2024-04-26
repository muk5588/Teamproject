<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:import url="/WEB-INF/views/layout/header.jsp"/>
<title>Insert title here</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet"
      integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz"
        crossorigin="anonymous"></script>
<script type="text/javascript">
    $(function () {

        $("#writeFrom").submit(function (e) {
            console.log("#writeFrom submit")

            var titleTerm = $("#title").val().trim()
            var contentTerm = $("#content").val().trim()
            console.log("#writeFrom submit contentTerm", contentTerm)

            if (!titleTerm) {
                alert("제목을 입력해주세요")
                $("#title").focus()
                return false;
            }

            if (titleTerm.length === 0) {
                alert('제목이 공백만 입력되었습니다 ');
                $("#title").focus()
                return false;
            }


            if (!contentTerm) {
                alert("내용을 입력해주세요")
                $("#content").focus()
                return false;
            }

            if (contentTerm.length === 0) {
                alert('내용이 공백만 입력되었습니다 ');
                $("#content").focus()
                return false;
            }

            $("#title").val(searchTerm)

        })


    })
</script>
</head>
<body>

<h1>글쓰기 페이지</h1>
<hr>


<!-- <form action="./write" method="post" id="writeFrom" enctype="multipart/form-data"> -->
<form action="./write" method="post" id="writeFrom" >

    <!-- 	<label for="title">제목</label> -->
    <!-- 	<input type="text" name="title" id="title"> -->
    <!-- 	<label for="content">내용</label> -->
    <!-- 	<input type="text" name="content" id="content"> -->
    <div class="mb-3">
        <label for="categoryno" class="form-label">게시물 종류</label>
        <select name="categoryno" id="categoryno" placeholder="종류를 선택해 주세요" class="form-option">
            <c:forEach var="category" items="${categorylist }">
                <option value="${category.categoryNo}" >${category.categoryName}</option>
            </c:forEach>
        </select>
    </div>
    <div class="mb-3">
        <label for="title" class="form-label">제목</label>
        <input type="text" class="form-control" id="title" placeholder="제목을 적어주세요" name="title">
    </div>
    <div class="mb-3">
        <label for="content" class="form-label">내용</label>
        <textarea class="form-control" id="content" rows="3" name="content" placeholder="내용을 적어주세요"></textarea>
    </div>

<!--     <div class="mb-3"> -->
<!--         <label for="file" class="form-label">첨부파일</label> -->
<!--         <input type="file" id="file" name="file"> -->
<!--     </div> -->

    <button id="btnWrite">작성하기</button>
</form>
<hr>
<a href="./list">
    <button>게시판으로</button>
</a>

</body>
</html>