<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: c
  Date: 2024-04-04
  Time: 오후 12:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="icon" href="<%=request.getContextPath()%>/resources/img/20191208094528217881320965.png">
    <script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
    <script>
        window.addEventListener("DOMContentLoaded", function () {
            $("#update").click(function () {
                var val = $(event.target).val();
                if ($("#name").val() == null || val.replace(/\s| /gi,"").length == 0) {
                    alert("이름을 입력해 주세요")
                    return;
                }
                if ($("#nickname").val() == null|| val.replace(/\s| /gi,"").length == 0) {
                    alert("닉네임을 입력해 주세요")
                    return;
                }
                if ($("#address").val() == null|| val.replace(/\s| /gi,"").length == 0) {
                    alert("주소를 검색해 주세요")
                    return;
                }
                if ($("#phone").val() == null|| val.replace(/\s| /gi,"").length == 0) {
                    alert("전화번호를 입력해 주세요")
                    return;
                }
                $("#updateform").submit();
            })
        })
            function DaumPostcode() {
            new daum.Postcode({
                oncomplete: function (data) {
                    // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

                    // 각 주소의 노출 규칙에 따라 주소를 조합한다.
                    // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
                    var addr = ''; // 주소 변수
                    var extraAddr = ''; // 참고항목 변수

                    //사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
                    if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
                        addr = data.roadAddress;
                    } else { // 사용자가 지번 주소를 선택했을 경우(J)
                        addr = data.jibunAddress;
                    }

                    // 사용자가 선택한 주소가 도로명 타입일때 참고항목을 조합한다.
                    if (data.userSelectedType === 'R') {
                        // 법정동명이 있을 경우 추가한다. (법정리는 제외)
                        // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
                        if (data.bname !== '' && /[동|로|가]$/g.test(data.bname)) {
                            extraAddr += data.bname;
                        }
                        // 건물명이 있고, 공동주택일 경우 추가한다.
                        if (data.buildingName !== '' && data.apartment === 'Y') {
                            extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
                        }
                        // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
                        if (extraAddr !== '') {
                            extraAddr = ' (' + extraAddr + ')';
                        }
                        // 조합된 참고항목을 해당 필드에 넣는다.
                        document.getElementById("extraAddress").value = extraAddr;

                    } else {
                        document.getElementById("extraAddress").value = '';
                    }

                    // 우편번호와 주소 정보를 해당 필드에 넣는다.
                    document.getElementById('postcode').value = data.zonecode;
                    document.getElementById("address").value = addr;
                    // 커서를 상세주소 필드로 이동한다.
                    document.getElementById("detailAddress").focus();
                }
            }).open();
        }
    </script>
    <link href="/resources/css/user/userUpdate.css" rel="stylesheet" type="text/css">
</head>
<body>
<jsp:include page="/WEB-INF/views/layout/header.jsp"></jsp:include>
<h3>회원정보수정</h3>
<div class="warpper">
    <div class="warp">
        <form action="/user/userUpdate?userno=${dto.userno}" method="post" id="updateform"> <!-- action 값을 안주면 submit을 자기 자신에게 한다 -->
            <div id="namebox">
                <label for="name">이름</label>
                <input type="text" name="name" id="name" value="${dto.name}"/><br>
            </div>
            <div id="nickbox">
                <label for="nickname">닉네임</label>
                <input type="text" name="nickname" id="nickname" value="${dto.nickname}">
                <br>
            </div>
            <div id="genderbox">
                <label>성별</label>
                <input type="radio" name="gender" value="M" checked/>남
                <input type="radio" name="gender" value="F"/>여<br>
            </div>
            <br>
            <div id=emailbox">
                <label for="email">이메일</label>
                <input type="email" name="email" id="email"value="${dto.email}" readonly/><br>
            </div>
            <div id="postcodebox">
                <label for="postcode">우편번호</label>
                <c:choose>
                    <c:when test="${dto.postcode <= 9999}">
                        <input type="text" name="postcode" id="postcode" value="0${dto.postcode}" readonly />
                    </c:when>
                    <c:otherwise>
                        <input type="text" name="postcode" id="postcode" value="${dto.postcode}" readonly />
                    </c:otherwise>
                </c:choose>
                <input type="button" onclick="DaumPostcode()" value="우편번호 찾기"><br>
            </div>
            <div id="addressbox">
                <label for="detailAddress">주소</label>
                <input type="text" name="address" id="address"  readonly value="${dto.address}" ><br>
                <input type="text" name="detailAddress" id="detailAddress" value="${dto.detailAddress}">
                <input type="text" name="extraAddress" id="extraAddress" value="${dto.extraAddress}"><br>
            </div>
            <div id="phonebox">
                <label for="phone">전화번호</label>
                <input type="text" name="phone" id="phone"value="${dto.phone}">
            </div>
            <br><br>
            <input type="button" id="update" name="update" value="수정"/>
            <input type="reset" id="reset" name="reset" value="초기화"/>
        </form>
    </div>
</div>
<jsp:include page="/WEB-INF/views/layout/footer.jsp"></jsp:include>

</body>
</html>
