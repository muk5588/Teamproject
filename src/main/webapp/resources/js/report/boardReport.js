window.addEventListener("DOMContentLoaded", function () {
    $("#report").click(function () {
        var val = $("#reportContent").val().trim();
        if ($("#reportContent").val().length === 0) {
            alert("내용을 입력해주세요");
            $("#reportContent").focus();
            return false;
        } else if (val.replace(/\s| /gi, "").length == 0) {
            alert('내용이 공백만 입력되었습니다 ');
            $("#reportContent").focus();
            return false;
        } else {
            if (confirm("신고하시겠습니까?")) {
                $("#reportform").submit();
                alert("신고 완료 되었습니다");
            } else {
                alert("신고가 취소되었습니다");
            }
        }
    });
});
