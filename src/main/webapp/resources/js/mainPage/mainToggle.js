function toggleLoginMenu(event) {
    var loginMenu = document.getElementById("login-menu");
    var menuList = document.getElementById("menuList");

    if (loginMenu.classList.contains("show") && !menuList.contains(event.target)) {
        loginMenu.classList.remove("show");
    } else {
        loginMenu.classList.toggle("show");
    }
}

function closeLoginMenu(event) {
    var loginMenu = document.getElementById("login-menu");
    if (!loginMenu.contains(event.target) && loginMenu.classList.contains("show")) {
        loginMenu.classList.remove("show");
    }
}

document.addEventListener("DOMContentLoaded", function() {
    document.addEventListener("click", closeLoginMenu);
});



