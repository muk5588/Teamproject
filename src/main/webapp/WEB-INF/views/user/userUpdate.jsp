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
    <script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
    <script>
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
</head>
<body>
<jsp:include page="/WEB-INF/views/header.jsp"></jsp:include>
<h3>회원정보수정</h3>
<div class="warpper">
    <div class="warp">
        <form action="/user/userUpdate?userno=${dto.userno}" method="post"> <!-- action 값을 안주면 submit을 자기 자신에게 한다 -->
            <div id="namebox">
                <label for="name">이름</label>
                <input type="text" name="name" id="name" value="${dto.name}"/><br>
            </div>
            <div id="genderbox">
                <label>성별</label>
                <input type="radio" name="gender" value="남" checked/>남
                <input type="radio" name="gender" value="여"/>여<br>
            </div>
            <div id=emailbox">
                <label for="email">이메일</label>
                <input type="email" name="email" id="email"value="${dto.email}"/><br>
            </div>
            <div id="postcodebox">
                <label for="postcode">우편번호</label>
                <input type="text" name="postcode" id="postcode" readonly value="${dto.postcode}">
                <input type="button" onclick="DaumPostcode()" value="우편번호 찾기"><br>
            </div>
            <div id="addressbox">
                <label for="detailAddress">주소</label>
                <input type="text" name="address" id="address"  readonly value="${dto.address}" ><br>
                <input type="text" name="detailAddress" id="detailAddress" value="${dto.detailAddress}">
                <input type="text" name="extralAddress" id="extraAddress" value="${dto.extraAddress}"><br>
            </div>
            <div id="phonebox">
                <label for="phone">전화번호</label>
                <input type="text" name="phone" id="phone"value="${dto.phone}">
            </div>
            <br><br>
            <input type="submit" id="submit" name="submit" value="수정"/>
            <input type="reset" id="reset" name="reset" value="초기화"/>
        </form>
    </div>
</div>
<jsp:include page="/WEB-INF/views/footer.jsp"></jsp:include>

</body>
</html>
