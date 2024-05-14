<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>join JSP</title>
    <script src="/resources/js/user/user.js" charset="UTF-8"></script>
    <script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
    <link href="/resources/css/userInsert.css" rel="stylesheet" type="text/css">
    
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
<jsp:include page="/WEB-INF/views/layout/header.jsp"></jsp:include>
<!-- <div class="warpper">
    <div class="warp">
    	<ul class="register">
    		<li>
    			<a id="userInsert">회원가입</a>
    		</li>
    	</ul>
    	
            <form action="/user/userInsert" method="post" id="userform"> action 값을 안주면 submit을 자기 자신에게 한다
                <div id="namebox" class="namebox-input input__block namebox-input__block">
                    <input type="text" name="name" id="name" class="input" placeholder="이름"/>
                </div>
                
                <div id="useridbox" class="useridbox-input input__block useridbox-input__block">
                    <input type="text" name="userid" id="userid" class="input" placeholder="아이디">
                    <div id="alertid" name="alertid"></div>
                </div>
                
                <div id="userpwbox" class="userpwbox-input input__block userpwbox-input__block">
                    <input type="password" name="userpw" id="userpw" class="input" placeholder="비밀번호">
                    <div id="alertpw" name="alertpw"></div>

                    <input type="password" name="userpwchk" id="userpwchk" class="input" placeholder="비밀번호 확인">
                    <div id="alertpw2" name="alertpw2"></div>
                </div>
                
                <div id="nickbox" class="nickbox-input input__block nickbox-input__block">
                    <input type="text" name="nickname" id="nickname" class="input" placeholder="닉네임"><br>
                    <div id="alertnick" name="alertnick"></div>
                </div>
                
                <div id="genderbox" class="genderbox-input input__block genderbox-input__block">
                    <label>성별</label>
                    <input type="radio" name="gender" value="M" checked/>남
                    <input type="radio" name="gender" value="F"/>여<br>
                </div>
                
                <div id=emailbox" class="emailbox-input input__block emailbox-input__block">
                    <input type="email" name="email" id="email" class="input" placeholder="이메일"/>
                    <button type="button" id="checkmail" class="send__btn" disabled>인증번호 발송</button><br>
                    <div id="alertemail" name="alertemail"></div>
                    <input type="text" id="checkcode" placeholder="인증번호를 입력해주세요" disabled="disabled">
                    <br>
                </div>
                <div id="postcodebox" class="postcodebox-input input__block postcodebox-input__block">
                    <input type="text" name="postcode" id="postcode" class="input" placeholder="우편번호" readonly>
                    <input type="button" onclick="DaumPostcode()" class="findAdd__btn" value="우편번호 찾기"><br>
                </div>
                <div id="addressbox" class="addressbox-input input__block addressbox-input__block">
                    <input type="text" name="address" id="address" class="input" placeholder="주소" readonly><br>
                    <input type="text" name="detailAddress" id="detailAddress" class="input" placeholder="상세주소">
                    <input type="text" name="extraAddress" id="extraAddress" class="input" placeholder="참고항목"><br>
                </div>
                <div id="phonebox" class="phonebox-input input__block phonebox-input__block">
                    <input type="text" name="phone" id="phone" class="input" placeholder="전화번호">
                </div>
                <br><br>
                <button type="button" id="join" name="join" class="signup__btn">회원가입</button>
                <button type="reset" id="reset" name="reset" class="reset__btn">초기화</button>
            </form>

    </div>
</div> -->
<!-- <div class="warpper">
    <div class="warp">
    	<h2>회원가입을 위해<br>정보를 입력해주세요.</h2>
    	
            <form action="/user/userInsert" method="post" id="userform"> action 값을 안주면 submit을 자기 자신에게 한다
                <div id="namebox">
                    <label for="name">이름</label>
                    <input type="text" name="name" id="name"/><br>
                </div>
                <div id="useridbox">
                    <label for="userid">아이디</label>
                    <input type="text" name="userid" id="userid">
                    <div id="alertid" name="alertid"></div>
                </div>
                <div id="userpwbox">
                    <label for="userpw">비밀번호</label>
                    <input type="password" name="userpw" id="userpw">
                    <div id="alertpw" name="alertpw"></div>

                    <label for="userpwchk">비밀번호 확인</label>
                    <input type="password" name="userpwchk" id="userpwchk">
                    <div id="alertpw2" name="alertpw2"></div>
                </div>
                <div id="nickbox">
                    <label for="nickname">닉네임</label>
                    <input type="text" name="nickname" id="nickname"><br>
                    <div id="alertnick" name="alertnick"></div>
                </div>
                <div id="genderbox">
                    <label>성별</label>
                    <input type="radio" name="gender" value="M" checked/>남
                    <input type="radio" name="gender" value="F"/>여<br>
                </div>
                <div id=emailbox">
                    <label for="email">이메일</label>
                    <input type="email" name="email" id="email"/>
                    <button type="button" id="checkmail" disabled>인증번호 발송</button><br>
                    <div id="alertemail" name="alertemail"></div>
                    <input type="text" id="checkcode" placeholder="인증번호를 입력해주세요" disabled="disabled">
                    <br>
                </div>
                <div id="postcodebox">
                    <label for="postcode">우편번호</label>
                    <input type="text" name="postcode" id="postcode" placeholder="우편번호" readonly>
                    <input type="button" onclick="DaumPostcode()" value="우편번호 찾기"><br>
                </div>
                <div id="addressbox">
                    <label for="detailAddress">주소</label>
                    <input type="text" name="address" id="address" placeholder="주소" readonly><br>
                    <input type="text" name="detailAddress" id="detailAddress" placeholder="상세주소">
                    <input type="text" name="extraAddress" id="extraAddress" placeholder="참고항목"><br>
                </div>
                <div id="phonebox">
                    <label for="phone">전화번호</label>
                    <input type="text" name="phone" id="phone">
                </div>
                <br><br>
                <button type="button" id="join" name="join">회원가입</button>
                <button type="reset" id="reset" name="reset">초기화</button>
            </form>

    </div>
</div> -->
<div class="warpper">
    <div class="total">
    	<h2>회원가입을 위해<br>정보를 입력해주세요.</h2>
    	
    	<div class="memberinfo_box">
            <form action="/user/userInsert" method="post" id="userform"> <!-- action 값을 안주면 submit을 자기 자신에게 한다 -->
                <div id="namebox">
                    <input type="text" name="name" id="name" placeholder="*이름"/>
                    <br><br>
                </div>
                <div id="useridbox">
                    <input type="text" name="userid" id="userid" placeholder="*아이디">
                    <br><br>
                    <div id="alertid" name="alertid"></div>
                </div>
                <div id="userpwbox">
                    <input type="password" name="userpw" id="userpw" placeholder="*비밀번호">
                    <br><br>
                    <div id="alertpw" name="alertpw"></div>

                    <input type="password" name="userpwchk" id="userpwchk" placeholder="*비밀번호">
                    <br><br>
                    <div id="alertpw2" name="alertpw2"></div>
                </div>
                <div id="nickbox">
                    <input type="text" name="nickname" id="nickname" placeholder="*닉네임">
                    <br><br>
                    <div id="alertnick" name="alertnick"></div>
                </div>
                <div id=emailbox">
                    <input type="email" name="email" id="email" placeholder="*이메일"/>
                    <button type="button" id="checkmail" disabled>인증번호 발송</button><br>
                    <div id="alertemail" name="alertemail"></div>
                    <input type="text" id="checkcode" placeholder="인증번호를 입력해주세요" disabled="disabled">
                    <br><br>
                </div>
                <div id="postcodebox">
                    <input type="text" name="postcode" id="postcode" placeholder="*우편번호" readonly>
                    <input type="button" onclick="DaumPostcode()" value="우편번호 찾기">
                    <br><br>
                </div>
                <div id="addressbox">
                    <input type="text" name="address" id="address" placeholder="*주소" readonly>
                    <input type="text" name="detailAddress" id="detailAddress" placeholder="상세주소">
                    <input type="text" name="extraAddress" id="extraAddress" placeholder="참고항목">
                    <br><br>
                </div>
                <div id="phonebox">
                    <input type="text" name="phone" id="phone" placeholder="*전화번호">
                    <br><br>
                </div>
               <div class="memberinfo_radio">
                <div id="genderbox_M">
                    <input type="radio" name="gender" value="M" checked/>남성
                    </div>
                <div id="genderbox_F">
                    <input type="radio" name="gender" value="F"/>여성
                </div>
               </div>
            </form>
    	</div>
    		<div class="agree_checkbox">
    			<div>
    				<input type="checkbox">이용약관 개인정보 수집 및 이용, 마케팅 활용 선택에 모두 동의합니다.
    				<br><br><hr>
    			</div>
    		</div>
    	<div class="button">
    		<div>
                <button type="button" id="join" name="join">회원가입</button>
                <button type="reset" id="reset" name="reset">초기화</button>
    		</div>
    	</div>

    </div>
</div>
<jsp:include page="/WEB-INF/views/layout/footer.jsp"></jsp:include>
</body>
</html>
