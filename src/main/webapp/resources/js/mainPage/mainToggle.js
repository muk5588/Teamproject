function toggleLoginMenu(event) {
    var loginMenu = document.getElementById("menu");
    var menuList = document.getElementById("menuList");

    // 메뉴 표시를 토글합니다.
    loginMenu.classList.toggle("show");
}

// 페이지의 다른 부분을 클릭하면 메뉴를 닫습니다.
document.addEventListener('click', function(event) {
    var loginMenu = document.getElementById("menu");
    var menuList = document.getElementById("menuList");

    // menuList 내부 또는 메뉴 아이콘이 아닌 다른 부분을 클릭하면 메뉴를 닫습니다.
    if (!menuList.contains(event.target) && !loginMenu.contains(event.target)) {
        loginMenu.classList.remove("show");
    }
});
function redirectToLogin(event) {
    event.preventDefault();
    alert('로그인이 필요합니다.');
    window.location.href = '/login'; // 로그인 페이지로 리디렉트
}