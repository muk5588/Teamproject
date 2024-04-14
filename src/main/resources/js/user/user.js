window.addEventListener("DOMContentLoaded",function () {
    $("userid").keyup(function () {
        var value = $(event.target).val();
        var num = value.search(/[0-9]/g);
        var eng = value.search(/[a-z]/ig);
        if (value.length < 5){
            $("#alertid").css({
                "color": "red",
                "font-size": "10px"
            });
            $("#alertid").text("! 아이디는 5자리 이상 이여야 합니다.")
        }
        else if(value.replace(/\s| /gi, "").length == 0){
            $("#alertid").css({
                "color": "red",
                "font-size": "10px"
            });
            $("#alertid").text("! 아이디에 공백은 사용 불가합니다.")
        }
        else if(num<0||eng<0){
            $("#alertid").css({
                "color": "red",
                "font-size": "10px"
            });
            $("#alertid").text("! 아이디는 영어+숫자로 이루어저야 합니다.")
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

});