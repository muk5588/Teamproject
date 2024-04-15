window.addEventListener("DOMContentLoaded",function () {
    $("#name").keyup(function () {
        var value = $(event.target).val();

    })
    $("#userid").keyup(function () {
        var value = $(event.target).val();
        var num = value.search(/[0-9]/g);
        var eng = value.search(/[a-z]/ig);
        if (value.length < 5){
            $("#alertid").css({
                "color": "red",
                "font-size": "10px"
            });
            $("#alertid").text("아이디는 5자리 이상 이여야 합니다.")
        }
        else if(value.replace(/\s| /gi, "").length == 0){
            $("#alertid").css({
                "color": "red",
                "font-size": "10px"
            });
            $("#alertid").text("아이디에 공백은 사용 불가합니다.")
        }
        else if(num<0||eng<0){
            $("#alertid").css({
                "color": "red",
                "font-size": "10px"
            });
            $("#alertid").text("아이디는 영어+숫자로 이루어저야 합니다.")
        }
        else {
            $.ajax({
                url: "/user/idChk",
                type: "post",
                dataType: "json",
                data: {"userid": $("#userid").val()},
                success: function (data) {
                    if (data == 1) {
                        $("#alertid").css({
                            "color": "red",
                            "font-size": "10px"
                        });
                        $("#alertid").text("중복된 아이디입니다.");
                    } else if (data == 0) {
                        $("#alertid").css({
                            "color": "black",
                        });
                        $("#alertid").text("사용가능한 아이디입니다.");
                    }
                }
            });
        }
    });
    $("#userpw").keyup(function () {
        var val = $(event.target).val();
        var num = val.search(/[0-9]/g);
        var eng = val.search(/[a-z]/ig);
        var spe = val.search(/['~!@#$%^&*|\\\'\";\/?]/gi);
        if(val.length <8){
            $("#alertpw").css({
                "color": "red",
                "font-size": "10px"
            });
            $("#alertpw").text("비밀번호는 8자리 이상이여야 합니다")
        }
        else if (val.replace(/\s| /gi,"").length == 0){
            $("#alertpw").css({
                "color": "red",
                "font-size": "10px"
            });
            $("#alertpw").text("비밀번호에 공백은 사용할 수 없습니다.")
        }
        else if(num<0||eng>0||spe<0){
            $("#alertpw").css({
                "color": "red",
                "font-size": "10px"
            });
            $("#alertpw").text("비밀번호는 영어+숫자+특수문자로 이루어저야 합니다.")
        }
        else{
            $("#alertpw").css({
                "color": "black",
                "font-size": "12px"
            });
            $("#alertpw").text("사용가능한 비밀번호 입니다")
        }
    })
    $("#userpwchk").keyup(function () {
        var val = $("#userpwchk").val()
        if(val != $("#userpw").val())
        {
            $("#alertpw2").css({
                "color": "red",
                "font-size": "10px"
            });
            $("#alertpw2").text("비밀번호가 일치하지 않습니다.")
            return;
        }
            $("#alertpw2").css({
                "color": "black",
                "font-size": "12px"
            });
            $("#alertpw2").text("비밀번호가 일치합니다.")
    });
        var code="";
       $("#checkemail").click(function () {
           $.ajax({
               type: "post",
               url: "/user/checkEmail",
               data:{
                   email: $("#email").val()
               },
               dataType: "json",
               success: function (data){
                   if (data.result == "error"){
                       alert("서버와 통신 중 에러가 발생했습니다. ")
                       $("#alertemail").css({
                           "color": "red",
                           "font-size": "10px"
                       });
                       $("#alertemail").text("서버와 통신 중 에러가 발생했습니다. ")
                   }else {
                       alert("이메일이 발송 되었습니다")
                       $("#alertemail").css({
                           "color": "red",
                           "font-size": "10px"
                       });
                       $("#alertemail").text("인증번호를 입력해 주세요")
                       code = data.code;
                       $("#email").attr("disabled",false)
                       $("#checkemail").attr("disabled",true)
                   }
               }
           })
       }) ;
       $("#checkcode").keyup(function () {
           if ($("#checkcode").val().length != 6){
               $("#alertemail").css({
                   "color": "red",
                   "font-size": "10px"
               });
               $("#alertemail").text("인증번호가 일치하지 않습니다")
           } else if ($("#checkcode").val() == code){
               $("#alertemail").css({
                   "color": "green",
                   "font-size": "10px"
               });
               $("#alertemail").text("인증이 완료 되었습니다")
           }
       })
    $("#join").click(function () {
        if ($("#alertid").text() != "사용가능한 아이디입니다."){
            alert("아이디 중복 확인을 해주세요")
            return;
        }
        if($("#alertpw").text() != "비밀번호가 일치합니다."){
            alert("비밀번호를 확인 해주세요")
            return;;
        }
        if ($("#alertemail").text() != "인증이 완료 되었습니다"){
            alert("메일 인증을 해주세요")
            return;
        }
        $("#userform").submit();
    })

});