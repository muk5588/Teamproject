<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>join JSP</title>
    <link rel="icon" href="<%=request.getContextPath()%>/resources/img/20191208094528217881320965.png">
    <script src="/resources/js/user/user.js" charset="UTF-8"></script>
    <script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
    <link href="/resources/css/user/userInsert.css" rel="stylesheet" type="text/css">
    
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
        
        window.onload = function() {
            var checkbox = document.getElementById('checkbox');
            var joinButton = document.getElementById('join');
            var sendButton = document.getElementById('checkmail');

            // 이용약관 체크박스가 변경될 때마다 호출되는 함수
            checkbox.addEventListener('change', function() {
                joinButton.disabled = !checkbox.checked; // 체크 여부에 따라 버튼 활성화 상태 변경
                if (!checkbox.checked) {
                    alert('이용약관에 동의해야 회원가입이 가능합니다.');
                }
            });

            // 초기 상태에서 버튼 비활성화
            joinButton.disabled = true;

            // 인증번호 발송 버튼 클릭 시 이벤트
            sendButton.addEventListener('click', function(event) {
                alert('발송 중이니 기다려 주세요.');
            });
            
        };
        
    </script>

</head>
<body>
<jsp:include page="/WEB-INF/views/layout/header.jsp"></jsp:include>
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
                    <input type="text" name="name" id="name" placeholder="*성명"/>
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
    	
    		<div class="scroll-box-title">
    				<p class="bold-text">개인정보 수집 이용 동의</p>
    		</div>
    		<div class="scroll-box">
    				<p class="bold-text">수집하는 개인정보</p>
    				<p>개인식별정보 : 성명, 주소, 전화번호, 휴대전화번호, 이메일을 수집합니다.</p>
					<p class="bold-text">수집한 개인정보의 이용</p>
					<p>제공하신 정보는 위촉절차의 집행 및 관리, 경력‧자격 등 확인(조회 및 검증), 위촉 여부의 결정, 민원처리에 사용 됩니다.
					<p>① 본인 확인 및 범죄경력 조회에 이용: 성명, 생년월일</p>
					<p>② 지원자와의 의사소통 및 정보 전달 등에 이용: 성명, 주소, 전화번호, 휴대전화번호, 이메일</p>
					<p>③ 지원자 평가에 이용: 자격사항, 경력사항 등</p>
					<p>④ 단, 지원자의 기본적 인권 침해의 우려가 있는 민감한 개인정보(인종 및 민족, 사상 및 신조, 정치적 성향 및 범죄기록 등)는 수집하지 않습니다.</p>
					<p class="bold-text">개인정보 보관기간
					<p>수집된 개인정보는 지원서 제출 후 위촉기간 만료 시 또는 지원서 삭제 요청 시까지 위 이용 목적을 위하여 보유‧이용됩니다. 또한 삭제 요청 시 지원자의 개인정보를 재생이 불가능한 방법으로 즉시 파기합니다.</p>
    		</div>
    		
    		<div class="agree_checkbox">
    			<div>
    				<input type="checkbox" id="checkbox" value="true">이용약관 개인정보 수집 및 이용에 모두 동의합니다.
    				<br><br><hr>
    			</div>
    		</div>
    	<div class="button">
    		<div>
                <button type="button" id="join" name="join">회원가입</button>
                <!-- <button type="reset" id="reset" name="reset">초기화</button> -->
                <button type="reset" id="reset" name="reset" onclick="document.getElementById('userform').reset();">초기화</button>
    		</div>
    	</div>

    </div>
</div>
<jsp:include page="/WEB-INF/views/layout/footer.jsp"></jsp:include>
</body>
</html>