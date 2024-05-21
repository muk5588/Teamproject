<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<title>Insert title here</title>
<style type="text/css">
    /* 컨테이너 스타일 */
    .container {
        width: 70%;
        margin: 20px auto;
        background: white;
        padding: 20px;
        box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        border-radius: 6px;
        height: 70%;
    }

    /* 제목과 버튼 컨테이너 */
    .title {
        display: flex;
        justify-content: space-between;
        align-items: center;
    }

    /* 버튼 스타일 */
    .btn {
        background: #87cefa;
        color: white;
        border: none;
        padding: 0.5em 1em;
        cursor: pointer;
        transition: background 0.3s ease;
        float: right;
    }

    button:hover, .btn:hover {
        background: #39a9db;
    }

    button:disabled {
        background: #ccc;
        cursor: not-allowed;
    }

    /* 테이블 스타일 */
    table {
        width: 100%;
        border-collapse: collapse;
        margin-bottom: 1em;
    }

    table, th, td {
        border: 1px solid #ddd;
    }

    th, td {
        text-align: center;
        padding: 0.3em;
    }

    th {
        background: #f4f4f4;
    }

    /* 작성 버튼 섹션 스타일 */
    #btnWrite {
        margin-top: 5px;
    }

    #btnWrite table {
        width: 100%;
        border: none;
    }

    #btnWrite th, #btnWrite td {
        border: none;
        padding: 0.5em;
    }

    #btnWrite input[type="text"], #btnWrite input[name="commentContent"] {
        width: 100%;
        padding: 0.5em;
        border: 1px solid #ddd;
        border-radius: 4px;
    }

    #btnWrite button {
        margin-left: 1em;
    }

    /* 랩 스타일 */
    .wrap {
        max-width: 1200px;
        margin: 0 auto;
        padding: 20px;
    }

    /* 제목 스타일 */
    h1 {
        font-size: 2em;
    }

    #searchForm select, #searchForm input[type="text"] {
        padding: 0.5em;
        border: 1px solid #ddd;
        border-radius: 4px;
    }

    #searchForm button {
        padding: 0.5em 1em;
    }

    /* 테이블 내 링크 스타일 */
    td a {
        color: #333;
    }

    td a:hover {
        text-decoration: underline;
    }

</style>
<script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
<script type="text/javascript" src="/resources/editor/js/HuskyEZCreator.js" charset="utf-8"></script>
<script type="text/javascript">
    var oEditors = [];
    nhn.husky.EZCreator.createInIFrame({
        oAppRef: oEditors,
        elPlaceHolder: "ir1",
        sSkinURI: "/resources/editor/SmartEditor2Skin.html",
        fCreator: "createSEditor2"
    });

    //‘저장’ 버튼을 누르는 등 저장을 위한 액션을 했을 때 submitContents가 호출된다고 가정한다.
    function submitContents(elClickedObj) {
        // 에디터의 내용이 textarea에 적용된다.
        oEditors.getById["ir1"].exec("UPDATE_CONTENTS_FIELD", []);

        // 에디터의 내용에 대한 값 검증은 이곳에서
        // document.getElementById("ir1").value를 이용해서 처리한다.

        try {
            elClickedObj.form.submit();
        } catch (e) {
        }
    }

    function save() {
        oEditors.getById["txtContent"].exec("UPDATE_CONTENTS_FIELD", []);
        //스마트 에디터 값을 텍스트컨텐츠로 전달
        var content = document.getElementById("smartEditor").value;
        //확인용. alert로 띄우기
// 	alert(document.getElementById("txtContent").value); 
        document.getElementById("content").value = document.getElementById("txtContent").value;
        // 값을 불러올 땐 document.get으로 받아오기
        document.getElementById('btnWrite').click();
        return;
    }

    // 스마트 에디터 (사진파일 업로드 URL)
    var sUploadURL = '/board/fileupload';
</script>
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
</script>
</head>
<body>
<c:import url="/WEB-INF/views/layout/header.jsp"/>
<div class="container">
    <div class="title">
    <h1>글쓰기 페이지</h1>
    <a href="./list" class="btn">
        게시판으로
    </a>
    </div>
    <hr>
    <form action="./write" method="post" id="writeForm" enctype="multipart/form-data">
        <div class="mb-3">
            <label for="categoryNo" class="form-label" style="margin-right: 30px">게시물 종류: </label>
            <select name="categoryNo" id="categoryNo" placeholder="종류를 선택해 주세요" class="form-option">
                <c:forEach var="category" items="${categorylist}">
                    <option value="${category.categoryNo}">${category.categoryName}</option>
                </c:forEach>
            </select>
        </div>
        <div class="mb-3">
            <label for="title" class="form-label">제목</label><br>
            <input type="text" class="form-control" id="title" placeholder="제목을 적어주세요" name="title" style="width: 100%;height: 5%">
        </div>
        <div class="mb-3">
            <label for="content" class="form-label" >내용</label>
            <input type="text" class="form-control" id="content" hidden="hidden" name="content">
        </div>
        <textarea rows="20" cols="10" id="txtContent" style="width: 100%;"></textarea>
        <div class="mb-3">
            <label for="file" class="form-label">첨부파일</label>
            <input type="file" id="file" name="file">
        </div>
        <div id="se2_sample" style="margin: 10px 0;">
            <input type="button" onclick="save();" value="작성하기" class="btn">
        </div>
        <br>
        <button hidden="hidden" id="btnWrite">작성하기</button>
    </form>
</div>
<script id="smartEditor" type="text/javascript">
    var oEditors = [];
    nhn.husky.EZCreator.createInIFrame({
        oAppRef: oEditors,
        elPlaceHolder: "txtContent",  //textarea ID 입력
        sSkinURI: "/resources/editor/SmartEditor2Skin.html",  //martEditor2Skin.html 경로 입력
        fCreator: "createSEditor2",
        htParams: {
            // 툴바 사용 여부 (true:사용/ false:사용하지 않음)
            bUseToolbar: true,
            // 입력창 크기 조절바 사용 여부 (true:사용/ false:사용하지 않음)
            bUseVerticalResizer: false,
            // 모드 탭(Editor | HTML | TEXT) 사용 여부 (true:사용/ false:사용하지 않음)
            bUseModeChanger: false
        }
    });
</script>


<%-- <jsp:include page="/resources/editor/SmartEditor2.html"/> --%>

</body>
</html>