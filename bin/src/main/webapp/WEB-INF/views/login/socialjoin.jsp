
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<script src="/resources/member/join.js" type="text/javascript" ></script>

<link href="/resources/member/join.css" rel="stylesheet">

<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>  <!-- 다음 postcode -->



<script type="text/javascript">
window.onload = function() {
	btnPostcode.onclick = function() {
	}
}
</script>

<script type="text/javascript">

$(function() {
	
	$("#btnPostcode").click(function() {
		
		new daum.Postcode({
			oncomplete: function(data) {
			
			
			
			// 우편번호 적용하기
			$("#zipCode").val( data.zonecode )
			 //$("#input[name~='postcode']").val(data.zonecode)
			 
			
			if( data.userSelectedType == 'J' ) {
				// 사용자가 지번 주소를 선택
			
				$("#address").val( data.jibunAddress )
				
			} else if( data.userSelectedType == 'R') {
				// 사용자가 도로명 주소를 선택
				
				$("#address").val( data.roadAddress )
				
			}
			
			$("#address_detail").trigger("focus")
			
			
			}
		
		
		}).open();
		
		
		
	})
	
})






//1. 처음 포커스 벗어났을 경우 메시지 띄우기
$(function(){
   
    //아이디에 포커스
   $("#userId").focus()
   
   //아이디를 입력하지 않고 넘어갈 경우(필수 정보입니다 띄우기)
   $("#userId").blur(function(){
      if($("#userId").val()==''){
         $("#userId_msg").html("필수정보입니다")
      }else{
         $("#userId_msg").html("")
      }
   })
   
   //비밀번호를 입력하지 않고 넘어갈 경우(필수 정보입니다 띄우기)
   $("#userPw").blur(function(){
      if($("#userPw").val()==''){
         $("#userPw_msg").html("필수정보입니다")
      }else{
         $("#userPw_msg").html("")
      }
   })
   
   //tab으로 다음칸으로 넘어갔을 경우  -- blur function
   ////비밀번호입력과 비밀번호 재확인입력이 동일하지않을 경우 메시지
   $("#userPw_check").blur(function(){   
      
      if($("#userPw").val() != $("#userPw_check").val()){
         $("#userPwcheck_error").html("비밀번호가 일치하지않습니다")
      }else{
         $("#userPwcheck_error").html("비밀번호가 일치합니다")
      }
   })
   
   //비밀번호 재확인칸을 클릭했을 경우 -- click function
   //비밀번호입력과 비밀번호 재확인입력이 동일하지않을 경우 메시지
   $("#userPw_check").click(function(){   
      
      if($("#userPw").val() != $("#userPw_check").val()){
         $("#userPwcheck_error").html("비밀번호가 일치하지않습니다")
      }else{
         $("#userPwcheck_error").html("비밀번호가 일치합니다")
      }
   })
   
   
   //이름을 입력하지 않고 넘어갈 경우(필수 정보입니다 띄우기)
   $("#userName").blur(function(){
      if($("#userName").val()==''){
         $("#userName_msg").html("필수정보입니다")
      }else{
         $("#userName_msg").html("")
      }
   })
   
})
//----------------------------------------------------------------------------------------

//2. 버튼 클릭했을 경우 아이디,비밀번호,이름,생년월일 정규화가 맞지않으면 메시지 띄우기
$(function(){
   
   //유효성 검사 -- 버튼 눌렀을때 push 알람 띄우기
   $("form").submit(function(){
      
      //validate가 틀렸을경우 리턴값 false
      //validate - 아이디,비밀번호 : 정규화 
      if(!validate()){
         return false
      }
      
      return true;
   })
   
   //아이디 입력란 클릭시 밑에 메시지 없애기
   $("#userId").focus(function(){
      $("#userId_msg").html("")   
   })
   //비밀번호 입력란 클릭시 밑에 메시지 없애기
   $("#userPw").focus(function(){
      $("#userPw_msg").html("")   
   })
   //이름 입력란 클릭시 밑에 메시지 없애기
   $("#userName").focus(function(){
      $("#userName_msg").html("")   
   })
})

//아이디,비밀번호,이름,생년월일 정규화가 틀렸을 경우 버튼푸시 실패
function validate(){

   //가입하기버튼 눌렀을 때 아이디 유효성검사
   if($('#userId').val()==''){
      $('#userId_msg').html("아이디를 입력해주세요")
      return false
   }
   
   //아이디 정규화
   var idReg = /^[a-z]+[a-z0-9]{5,19}$/g;
   
   if(!idReg.test($("#userId").val())){
         $("#userId_msg").html("아이디는 6자 이상, 20자 이하의 영문자,숫자만 가능합니다!")
         return false
   }
   
   //가입하기버튼 눌렀을 때 비밀번호 유효성검사
   if($('#userPw').val()==''){
      $('#userPw_msg').html("비밀번호를 입력해주세요")
      return false
   }
   
   //비밀번호 정규화
    var pwReg =/^(?=.*[a-zA-Z])(?=.*[0-9])(?=.*[!@#$%^&*])[a-zA-Z0-9!@#$%^&*]{8,20}$/;
         
   if( !pwReg.test( $("#userPw").val() ) )  {
         $("#userPw_msg").html("비밀번호는 알파벳 대소문자, 숫자, 특수기호 조합으로 8자 이상, 20자 이하로 작성하세요")
         return false
   }
    
     //가입하기버튼 눌렀을 때 이름 유효성검사
   if($('#userName').val()==''){
      $('#userName_msg').html("이름을 입력해주세요")
      return false
   }

     //이름 정규화
     var nameReg = /^[가-힣]{1,10}$/

   if( !nameReg.test( $("#userName").val() ) )  {
      $("#userName_msg").html("이름은 10자이내 한글만 가능합니다!")
      return false
   }
     return true;
  
}

//3. 버튼 클릭했을 경우 비밀번호와 비밀번호 재확인이 일치하지 않으면 메시지 띄우기
$(function(){
   
   //유효성 검사 -- 버튼 눌렀을때 push 알람 띄우기
   $("form").submit(function(){
      
      //validate가 틀렸을경우 리턴값 false
      //compare_check - 비밀번호,비밀번호재확인 일치 
      if(!compare_check()){
         return false
      }
      return true;
   })
  })
//버튼을 클릭했을 경우 비밀번호와 비밀번호재확인이 일치하지않으면 버튼푸시 실패
//비밀번호와 비밀번호 재확인 입력 유효성검사
function compare_check(){
   
   //가입하기버튼 눌렀을 때 비밀번호 확인 유효성 검사
   if($("#userPw").val() != $("#userPw_check").val()){
      $("#userPwcheck_error").html("비밀번호가 일치하지않습니다")
      return false;
   }
   return true
}



</script>


<script type="text/javascript">


//1. 처음 포커스 벗어났을 경우 메시지 띄우기
$(function(){
   
    //아이디에 포커스
   $("#userName").focus()
   
   //아이디를 입력하지 않고 넘어갈 경우(필수 정보입니다 띄우기)
   $("#userId").blur(function(){
      if($("#userId").val()==''){
         $("#userId_msg").html("필수정보입니다")
      }else{
         $("#userId_msg").html("")
      }
   })
   
   //비밀번호를 입력하지 않고 넘어갈 경우(필수 정보입니다 띄우기)
   $("#userPw").blur(function(){
      if($("#userPw").val()==''){
         $("#userPw_msg").html("필수정보입니다")
      }else{
         $("#userPw_msg").html("")
      }
   })
   
   //tab으로 다음칸으로 넘어갔을 경우  -- blur function
   ////비밀번호입력과 비밀번호 재확인입력이 동일하지않을 경우 메시지
   $("#userPw_check").blur(function(){   
      
      if($("#userPw").val() != $("#userPw_check").val()){
         $("#userPwcheck_error").html("비밀번호가 일치하지않습니다")
      }else{
         $("#userPwcheck_error").html("비밀번호가 일치합니다")
      }
   })
   
   //비밀번호 재확인칸을 클릭했을 경우 -- click function
   //비밀번호입력과 비밀번호 재확인입력이 동일하지않을 경우 메시지
   $("#userPw_check").click(function(){   
      
      if($("#userPw").val() != $("#userPw_check").val()){
         $("#userPwcheck_error").html("비밀번호가 일치하지않습니다")
      }else{
         $("#userPwcheck_error").html("비밀번호가 일치합니다")
      }
   })
   
   
   //이름을 입력하지 않고 넘어갈 경우(필수 정보입니다 띄우기)
   $("#userName").blur(function(){
      if($("#userName").val()==''){
         $("#userName_msg").html("필수정보입니다")
      }else{
         $("#userName_msg").html("")
      }
   })
   
})
//----------------------------------------------------------------------------------------

//2. 버튼 클릭했을 경우 아이디,비밀번호,이름,생년월일 정규화가 맞지않으면 메시지 띄우기
$(function(){
   
   //유효성 검사 -- 버튼 눌렀을때 push 알람 띄우기
   $("form").submit(function(){
      
      //validate가 틀렸을경우 리턴값 false
      //validate - 아이디,비밀번호 : 정규화 
      if(!validate()){
         return false
      }
      
      return true;
   })
   
   //아이디 입력란 클릭시 밑에 메시지 없애기
   $("#userId").focus(function(){
      $("#userId_msg").html("")   
   })
   //비밀번호 입력란 클릭시 밑에 메시지 없애기
   $("#userPw").focus(function(){
      $("#userPw_msg").html("")   
   })
   //이름 입력란 클릭시 밑에 메시지 없애기
   $("#userName").focus(function(){
      $("#userName_msg").html("")   
   })
})

//아이디,비밀번호,이름,생년월일 정규화가 틀렸을 경우 버튼푸시 실패
function validate(){

   //가입하기버튼 눌렀을 때 아이디 유효성검사
   if($('#userId').val()==''){
      $('#userId_msg').html("아이디를 입력해주세요")
      return false
   }
   
   //아이디 정규화
   var idReg = /^[a-z]+[a-z0-9]{5,19}$/g;
   
   if(!idReg.test($("#userId").val())){
         $("#userId_msg").html("아이디는 6자 이상, 20자 이하의 영문자,숫자만 가능합니다!")
         return false
   }
   
   //가입하기버튼 눌렀을 때 비밀번호 유효성검사
   if($('#userPw').val()==''){
      $('#userPw_msg').html("비밀번호를 입력해주세요")
      return false
   }
   
   //비밀번호 정규화
    var pwReg =/^(?=.*[a-zA-Z])(?=.*[0-9])(?=.*[!@#$%^&*])[a-zA-Z0-9!@#$%^&*]{8,20}$/;
         
   if( !pwReg.test( $("#userPw").val() ) )  {
         $("#userPw_msg").html("비밀번호는 알파벳 대소문자, 숫자, 특수기호 조합으로 8자 이상, 20자 이하로 작성하세요")
         return false
   }
    
     //가입하기버튼 눌렀을 때 이름 유효성검사
   if($('#userName').val()==''){
      $('#userName_msg').html("이름을 입력해주세요")
      return false
   }

     //이름 정규화
     var nameReg = /^[가-힣]{1,10}$/

   if( !nameReg.test( $("#userName").val() ) )  {
      $("#userName_msg").html("이름은 10자이내 한글만 가능합니다!")
      return false
   }
     return true;
  
}

//3. 버튼 클릭했을 경우 비밀번호와 비밀번호 재확인이 일치하지 않으면 메시지 띄우기
$(function(){
   
   //유효성 검사 -- 버튼 눌렀을때 push 알람 띄우기
   $("form").submit(function(){
      
      //validate가 틀렸을경우 리턴값 false
      //compare_check - 비밀번호,비밀번호재확인 일치 
      if(!compare_check()){
         return false
      }
      return true;
   })
  })
//버튼을 클릭했을 경우 비밀번호와 비밀번호재확인이 일치하지않으면 버튼푸시 실패
//비밀번호와 비밀번호 재확인 입력 유효성검사
function compare_check(){
   
   //가입하기버튼 눌렀을 때 비밀번호 확인 유효성 검사
   if($("#userPw").val() != $("#userPw_check").val()){
      $("#userPwcheck_error").html("비밀번호가 일치하지않습니다")
      return false;
   }
   return true
}



// 이메일 인증

$(function(){
	
	$('#mail-Check-Btn').click(function() {
		const email = $('#emailF').val() + $("#middle").text() + $('#email2').val(); // 이메일 주소값 얻어오기!
		console.log('완성된 이메일 : ' + email); // 이메일 오는지 확인
		const checkInput = $('.mail-check') // 인증번호 입력하는곳 
		
				alert('인증번호가 전송되었습니다.')

		// 이메일을 적지 않았을 때 ajax를 막아야한다,,
		if($('#emailF').val()==''){
			$('#mail-check-warn').html('이메일 인증 해주세요!')
			$('#mail-check-warn').css('color','red')
			return;
		}
		
		
		$.ajax({
			type : 'get',
			url : '/member/mailCheck?email='+email, // GET방식이라 Url 뒤에 email을 붙일수있다.
			success : function (data) {	// data는 serviceImpl의 joinEmail에서 보내주는 authNumber
				console.log("data : " +  data);
				  if(data){
				checkInput.attr('disabled',false);
				
				  }
			}
		
		  ,error : function() {
	            console.log("실패!")
	         }
	      }); // end ajax
	   }); // end send email

	   var code = ""; // 인증번호 저장을 위한 코드
	   var isCertification = false;


	//인증번호 비교 
	// blur -> focus가 벗어나는 경우 발생
	$('.mail-check').blur(function () {
		const inputCode = $(this).val();
		const $resultMsg = $('#mail-check-warn');
		
		
	    //사용자가 입력한 인증번호를 비교하는 AJAX
	      $.ajax({
	         type : 'POST',
	         data: {inputCode: inputCode},
	         url : '/member/mailCheck',
	         success : function (data) {
	            console.log("data : " +  data);
		
		if(data){
			isCertification = true;

			$resultMsg.html('인증번호가 일치합니다.');
			$resultMsg.css('color','green');
			$('#mail-Check-Btn').attr('disabled',true);
			$('#email').attr('readonly',true);
			$('#email2').attr('readonly',true);
			$('#email2').attr('onFocus', 'this.initialSelect = this.selectedIndex');
	         $('#email2').attr('onChange', 'this.selectedIndex = this.initialSelect');
		}else{
			isCertification = false;
			$resultMsg.html('인증번호가 불일치 합니다. 다시 확인해주세요!.');
			$resultMsg.css('color','red');
		}
		
	         }
	         
	         ,error : function() {
	             console.log("실패!")
	          }
	       }); // end ajax
	    });
	
	
	
	
	//이메일주소 가져오기
	$("#emailF").blur(function(){
		email();	
	});

	$("#email2").change(function(){
		email();	
	});

})



function email() {
	const emailF = $("#emailF").val();
	const middle = $("#middle").text();
	const address = $("#email2").val();
	
	if(emailF != "" && address != "") {
		$("#email").val(emailF + middle + address);
	}
};



$(function(){
	
$("#idDu").on("click", function(){
      
      var userId = $("#userId").val();
      var data = {userId : userId}

      if(userId == ''){
         $('#userId_msg').html("아이디를 입력해주세요")
         return
      }
      
      //회원가입시 아이디 중복검사
      $.ajax({
         type:"get",
         url: " /member/join/idDu",
         data : data,
         success: function(result){
            //
            if(result != "fail"){
               //중복아이디가 없어서 사용가능한 아이디입니다
//                $(".id_input1").css("display","inline-block");
               //span input2는 안보이게
//                $(".id_input2").css("display","none");

				$("#userId_msg").html("사용가능한 아이디입니다.")
				$("#userId_msg").css('color', 'green')
				
            }else{
				$("#userId_msg").html("사용불가능한 아이디입니다.")
               //중복아이디이므로 '아이디가 이미 존재합니다' 띄우기
//                $(".id_input1").css("display","none");
//                $(".id_input2").css("display","inline-block");
            }
            
         }
      })

	})

	
	
	$("#nickDu").on("click", function(){
      
      var userNick = $("#userNick").val();
      var data = {userNick : userNick}

      if(userNick == ''){
         $('#userNick_msg').html("닉네임을 입력해주세요")
         return
      }
      
      //회원가입시 아이디 중복검사
      $.ajax({
         type:"get",
         url: " /member/join/nickDu",
         data : data,
         success: function(result){
            //
            if(result != "fail"){
               //중복아이디가 없어서 사용가능한 아이디입니다
//                $(".id_input1").css("display","inline-block");
               //span input2는 안보이게
//                $(".id_input2").css("display","none");

				$("#userNick_msg").html("사용가능한 닉네임입니다.")
				$("#userNick_msg").css('color', 'green')
            }else{
				$("#userNick_msg").html("사용불가능한 닉네임입니다.")
               //중복아이디이므로 '아이디가 이미 존재합니다' 띄우기
//                $(".id_input1").css("display","none");
//                $(".id_input2").css("display","inline-block");
            }
            
         }
      })

	})
	
	
	
})



</script>









<div id="join">

<form action="./socialjoin" method="post">

   
   <div class="select">
      <label for="userName">이름</label>
      <input type="text"  id="userName" name="userName">
      <span id="userName_msg" class="msg"></span>
   </div>
   
   <div class="select">
      <label for="userId" >아이디</label><br><button type="button" id="idDu">중복체크</button>
      <input type="text" id="userId" name="userId"  placeholder="6자 이상, 16자 이하의 영문자,숫자만 가능">
      <span id="userId_msg" class="msg"></span>
   </div>
      
   <div class="select">
      <label for="userPw">비밀번호</label>
      <input type="password" id="userPw" name="userPw" placeholder="알파벳 대소문자, 숫자, 특수기호 조합으로 6자 이상, 16자 이하로 작성">
      <span id="userpw_msg" class="msg"></span>
   </div>
   
   <div class="select">
      <label for="userPw_check">비밀번호 재확인</label>
      <input type="password" id="userPw_check" name="userPw_check" >
      <span id="userPwcheck_error" class="msg"></span>
   </div>
   
   <div class="select">
      <label for="userNick">닉네임</label><button id="nickDu" type="button">중복체크</button>
      <input type="text" id="userNick" name="userNick" >
      <span id="userNick_msg" class="msg"></span>
   </div>

		<div class="select" style="margin:10px 90px;">
			<label for="email" >이메일</label>
			
			<div class="input-group">
				<input type="text" class="form-control" name="emailF" id="emailF" placeholder="이메일" style="width:150px"> 
					<span id="middle">@</span>
					<input type="text" id="email2" list="email3" style="width:150px">
					<datalist class="form-control" id="email3">
						<option value="naver.com">naver.com</option>
						<option value="daum.net">daum.net</option>
						<option value="gmail.com">gmail.com</option>
						<option>직접입력</option>
					</datalist>
					<input type="hidden" id="email" name="email" value="">
					<button type="button"id="mail-Check-Btn" class="input-group-addon">본인인증</button>
				<div class="btn">
					<input class="mail-check"
						placeholder="인증번호를 입력해주세요!" disabled="disabled" maxlength="6">
				</div> <!-- btn -->
				<span id="mail-check-warn"></span>
			</div><!-- input-group -->
			
		</div><!-- select -->
   

   <div class="select">
      <label for="address">주소</label>   <button id="btnPostcode" type="button">우편번호 찾기</button>
      <input type="text" id="zipCode" name="zipCode" >	
      <input type="text"  id="address" name="address" >	
      <input type="text" id="detailaddress" name="detailaddress" >	
      <span id="address_msg" class="msg"></span>
   </div>
   
   <div class="select">
      <label for="phone">휴대전화</label>
      <input type="text"  id="phone" name="phone" pattern="[0-9]+" placeholder="ex)01012345678">
      <span id="phone_msg" class="msg"></span>
   </div>
   
   
   <div class="select">
      <label><input type="radio" name="positionNo" value="1"> 일반 </label>
      <label><input type="radio" name="positionNo" value="2"> 병원관계자 </label>
   </div>
   
   
   
   
   <div class="select">
      <button id="btn">가입하기</button>
   </div>
   

</form>






</div>  <!-- join -->









