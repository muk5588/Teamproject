<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<title>Insert title here</title>
<link href="/resources/css/board/boardWrite.css" rel="stylesheet" type="text/css">
<script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
<script type="text/javascript">
	// 스마트 에디터 (사진파일 업로드 URL)
	var sUploadURL = '/board/fileupload';
</script>
<script type="text/javascript" src="/resources/editor/js/HuskyEZCreator.js" charset="utf-8"></script>
<script type="text/javascript">
	//‘저장’ 버튼을 누르는 등 저장을 위한 액션을 했을 때 submitContents가 호출된다고 가정한다.
	function submitContents(elClickedObj) {
		// 에디터의 내용이 textarea에 적용된다.
		oEditors.getById["txtContent"].exec("UPDATE_CONTENTS_FIELD", []);

		// 에디터의 내용에 대한 값 검증은 이곳에서
		// document.getElementById("ir1").value를 이용해서 처리한다.

		try {
			elClickedObj.form.submit();
		} catch (e) {
		}
	}

	function save() {
		// 에디터의 내용을 textarea에 업데이트합니다.
		oEditors.getById["ir1"].exec("UPDATE_CONTENTS_FIELD", []);
		// 업데이트된 에디터 내용 가져오기
		var content = document.getElementById("ir1").value;
//      확인용으로 alert에 내용 출력
//      alert(content);
     
	     if (content.trim() === "") {
		        // 내용이 비어있는 경우 경고 메시지 표시
		        alert("내용이 없습니다");
		        return; // 함수 종료
	 	}
	     if (content === '<p>&nbsp;</p>') {
		        // 내용이 비어있는 경우 경고 메시지 표시
		        alert("내용이 없습니다");
		        return; // 함수 종료
	 	}
	     // 숨겨진 input 요소에 내용 설정
	     document.getElementById("content").value = content;
	     // form 제출
	     document.getElementById('btnWrite').click();
     
	}



</script>
<script type="text/javascript">
	$(function () {

		$("#btnWrite").click(function (e) {
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
			<h1>수정 페이지</h1>
			<a href="./list" class="btn">
				게시판으로
			</a>
		</div>
		<hr>
		<form action="./update" method="post" id="updateForm" enctype="multipart/form-data">
			<div hidden="hidden">
				<input type="text" class="form-control" id="boardNo" hidden="hidden" name="boardNo" value="${board.boardNo }">
			</div>
			<div class="mb-3">
				<label for="categoryNo" class="form-label" style="margin-right: 30px">게시물 종류: </label>
				<select name="categoryNo" id="categoryNo" placeholder="종류를 선택해 주세요" class="form-option">
					<c:forEach var="category" items="${categorylist}">
                    	<option value="${category.categoryNo}" <c:if test="${category.categoryNo eq categoryNoInt }">selected="selected"</c:if>>${category.categoryName}</option>
					</c:forEach>
				</select>
			</div>
			<div class="mb-3">
				<label for="title" class="form-label">제목</label><br>
				<input type="text" class="form-control" id="title" name="title" style="width: 100%;height: 5%" value="${board.title }">
			</div>
			<div class="mb-3">
				<label for="content" class="form-label" >내용</label>
				<input type="text" class="form-control" id="content" hidden="hidden" name="content">
			</div>
			<div class="mb-3">
				<label for="file" class="form-label">첨부파일</label>
				<input type="file" id="file" name="file">
			</div>
			<textarea rows="20" cols="10" id="ir1" name="ir1" class="ir1" style="width: 100%;">${board.content }</textarea>
			<div id="se2_sample" style="margin: 10px 0;">
				<input type="button" onclick="save();" value="수정하기" class="btn">
			</div>
			<br>
			<button hidden="hidden" id="btnWrite">작성하기</button>
		</form>
	</div>
	<script type="text/javascript">
		var oEditors = [];
		nhn.husky.EZCreator.createInIFrame({
			oAppRef: oEditors,
			elPlaceHolder: "ir1",  //textarea ID 입력
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
	
	
	
	
<c:import url="/WEB-INF/views/layout/footer.jsp"/>
