// 토글 함수에 이벤트 처리를 추가합니다.
function toggleLoginMenu() {
    var loginMenu = document.getElementById("login-menu");
    var menuList = document.getElementById("menuList");

    // 로그인 메뉴가 보이는 상태이면서 메뉴 목록을 클릭한 경우에만 메뉴를 숨깁니다.
    if (loginMenu.classList.contains("show") && !menuList.contains(event.target)) {
        loginMenu.classList.remove("show");
    } else {
        loginMenu.classList.toggle("show");
    }
}

// 페이지가 로드될 때, 슬라이드 영역 외의 클릭 이벤트를 추가합니다.
window.onload = function() {
    document.addEventListener("click", function(event) {
        var loginMenu = document.getElementById("login-menu");
        var menuList = document.getElementById("menuList");

        // 슬라이드 영역 이외의 영역을 클릭하고 있고, 로그인 메뉴가 열려있는 경우에만 메뉴를 닫습니다.
        if (!loginMenu.contains(event.target) && !menuList.contains(event.target) && loginMenu.classList.contains("show")) {
            loginMenu.classList.remove("show");
        }
    });
};



