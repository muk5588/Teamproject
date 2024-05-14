window.addEventListener("DOMContentLoaded", function () {
    $("#email").keyup(function () {
        var regex = new RegExp("^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[0-9a-zA-Z]{2,3}$");
        if (!regex.test($("#email").val())) {
            $("#alertemail").css({
                "color": "red",
                "text-align": "center",
                "font-size": "15px"
            });
            $("#alertemail").text("이메일 형식이 맞지 않습니다.")
        } else {
            $("#alertemail").text("")
            $("#checkmail").attr("disabled", false);
        }
    })
    var code = "";
    $("#checkmail").click(function () {
        $.ajax({
            type: "post",
            url: "/user/checkEmail",
            data: {
                email: $("#email").val()
            },
            dataType: "json",
            success: function (data) {
                console.log("data", data)
                if (data.result == "error") {
                    alert("서버와 통신 중 에러가 발생했습니다. ")
                    $("#alertemail").css({
                        "color": "red",
                        "text-align": "center",
                        "font-size": "15px"
                    });
                    $("#alertemail").text("서버와 통신 중 에러가 발생했습니다. ")
                } else {
                    console.log("aaaa", data)
                    alert("이메일이 발송 되었습니다")
                    $("#alertemail").css({
                        "text-align": "center",
                        "color": "red",
                        "font-size": "15px"
                    });
                    $("#alertemail").text("인증번호를 입력해 주세요")
                    code = data.authNum;
                    $("#checkmail").attr("disabled", true)
                    $("#checkcode").attr("disabled", false)
                }
            }
        })
    });
    $("#checkcode").keyup(function () {
        if ($("#checkcode").val().length != 6) {
            $("#alertemail").css({
                "text-align": "center",
                "color": "red",
                "font-size": "15px"
            });
            $("#alertemail").text("인증번호가 일치하지 않습니다")
        } else if ($("#checkcode").val() == code) {
            $("#alertemail").css({
                "text-align": "center",
                "color": "green",
                "font-size": "15px"
            });
            $("#alertemail").text("인증이 완료 되었습니다")

        }
    })
    $("#search").click(function () {

        if ($("#name").val() == null) {
            alert("이름을 입력해 주세요")
            return;
        }
        if ($("#email").val() == null) {
            alert("이메일을 입력해 주세요")
            return;
        }
        if ($("#alertemail").text() != "인증이 완료 되었습니다") {
            alert("메일 인증을 해주세요")
            return;
        }
        $("#useridform").submit();
    })
// ________________________________________________________________________________________________________________________
    $("#email2").keyup(function () {
        var regex = new RegExp("^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[0-9a-zA-Z]{2,3}$");
        if (!regex.test($("#email2").val())) {
            $("#alertemail2").css({
                "text-align": "center",
                "color": "red",
                "font-size": "15px"
            });
            $("#alertemail2").text("이메일 형식이 맞지 않습니다.")
        } else {
            $("#alertemail2").text("")
            $("#checkmail2").attr("disabled", false);
        }
    })
    var code = "";
    $("#checkmail2").click(function () {
        $.ajax({
            type: "post",
            url: "/user/checkEmail",
            data: {
                email: $("#email2").val()
            },
            dataType: "json",
            success: function (data) {
                console.log("data", data)
                if (data.result == "error") {
                    alert("서버와 통신 중 에러가 발생했습니다. ")
                    $("#alertemail2").css({
                        "text-align": "center",

                        "color": "red",
                        "font-size": "15px"
                    });
                    $("#alertemail2").text("서버와 통신 중 에러가 발생했습니다. ")
                } else {
                    console.log("aaaa", data)
                    alert("이메일이 발송 되었습니다")
                    $("#alertemail2").css({
                        "text-align": "center",
                        "color": "red",
                        "font-size": "15px"
                    });
                    $("#alertemail2").text("인증번호를 입력해 주세요")
                    code = data.authNum;
                    $("#checkmail2").attr("disabled", true)
                    $("#checkcode2").attr("disabled", false)
                }
            }
        })
    });
    $("#checkcode2").keyup(function () {
        if ($("#checkcode2").val().length != 6) {
            $("#alertemail2").css({
                "color": "red",
                "text-align": "center",

                "font-size": "15px"
            });
            $("#alertemail2").text("인증번호가 일치하지 않습니다")
        } else if ($("#checkcode2").val() == code) {
            $("#alertemail2").css({
                "text-align": "center",

                "color": "green",
                "font-size": "15px"
            });
            $("#alertemail2").text("인증이 완료 되었습니다")

        }
    })
    $("#search2").click(function () {
        if ($("#name2").val() == null) {
            alert("이름을 입력해 주세요")
            $("#userid").focus()
            return;
        }
        if ($("#email2").val() == null) {
            alert("이메일을 입력해 주세요")
            $("#email2").focus()
            return;
        }
        if ($("#alertemail2").text() != "인증이 완료 되었습니다") {
            alert("메일 인증을 해주세요")
            return;
        }
        $("#userpwform").submit();
    })
});