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
	 } catch(e) {} }
	 
function save(){
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
<h1>글쓰기 페이지</h1>
<hr>


<form action="./write" method="post" id="writeFrom" enctype="multipart/form-data">
<!-- <form action="./write" method="post" id="writeFrom" > -->

    <!-- 	<label for="title">제목</label> -->
    <!-- 	<input type="text" name="title" id="title"> -->
    <!-- 	<label for="content">내용</label> -->
    <!-- 	<input type="text" name="content" id="content"> -->
    <div class="mb-3">
        <label for="categoryNo" class="form-label">게시물 종류</label>
        <select name="categoryNo" id="categoryNo" placeholder="종류를 선택해 주세요" class="form-option">
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
        <input type="text" class="form-control" id="content" hidden="hidden" name="content">
<!--         <textarea class="form-control" id="content" rows="3" name="content" placeholder="내용을 적어주세요"></textarea> -->
<!-- 	<iframe src="/resources/editor/SmartEditor2.html" width="90%" height="100%" style="text-align: center;"/> -->
    </div>

    <div class="mb-3">
        <label for="file" class="form-label">첨부파일</label>
        <input type="file" id="file" name="file">
    </div>
<textarea rows="10" cols="100" id="txtContent" style="width: 100%;"></textarea>
<div id="se2_sample" style="margin:10px 0;">
	<input type="button" onclick="save();" value="작성하기">
</div>

    <button hidden="hidden" id="btnWrite">작성하기</button>
</form>

<script id="smartEditor" type="text/javascript"> 
	var oEditors = [];
	nhn.husky.EZCreator.createInIFrame({
	    oAppRef: oEditors,
	    elPlaceHolder: "txtContent",  //textarea ID 입력
	    sSkinURI: "/resources/editor/SmartEditor2Skin.html",  //martEditor2Skin.html 경로 입력
	    fCreator: "createSEditor2",
	    htParams : { 
	    	// 툴바 사용 여부 (true:사용/ false:사용하지 않음) 
	        bUseToolbar : true, 
		// 입력창 크기 조절바 사용 여부 (true:사용/ false:사용하지 않음) 
		bUseVerticalResizer : false, 
		// 모드 탭(Editor | HTML | TEXT) 사용 여부 (true:사용/ false:사용하지 않음) 
		bUseModeChanger : false 
	    }
	});
</script>

<hr>
<a href="./list">
    <button>게시판으로</button>
</a>
<div style="width: 90%;">
</div>
<%-- <jsp:include page="/resources/editor/SmartEditor2.html"/> --%>

</body>
</html>