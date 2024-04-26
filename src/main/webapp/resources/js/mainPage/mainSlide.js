// 현재 슬라이드 인덱스를 나타내는 변수 초기화
let slideIndex = 1;
// 첫 번째 슬라이드 표시
showSlides(slideIndex);

// 다음/이전 슬라이드 컨트롤 함수
function plusSlides(n) {
  // 현재 슬라이드 인덱스를 변경하여 다음 또는 이전 슬라이드로 이동
  showSlides(slideIndex += n);
}

// 썸네일 이미지 컨트롤 함수
function currentSlide(n) {
  // 현재 슬라이드 인덱스를 지정된 값으로 설정하여 해당 슬라이드로 이동
  showSlides(slideIndex = n);
}

// 자동 슬라이드쇼 함수
setInterval(function() {
  // 다음 슬라이드로 이동
  plusSlides(1);
}, 5000); // 5초마다 다음 슬라이드로 이동

// 슬라이드를 표시하는 함수
function showSlides(n) {
  let i;
  // 슬라이드 요소들을 가져옴
  let slides = document.getElementsByClassName("mySlides");
  // 도트(페이지 인디케이터) 요소들을 가져옴
  let dots = document.getElementsByClassName("dot");
  // 슬라이드 인덱스가 슬라이드 개수를 초과하면 첫 번째 슬라이드로 이동
  if (n > slides.length) {slideIndex = 1}
  // 슬라이드 인덱스가 1보다 작으면 마지막 슬라이드로 이동
  if (n < 1) {slideIndex = slides.length}
  // 모든 슬라이드를 숨김
  for (i = 0; i < slides.length; i++) {
    slides[i].style.display = "none";
  }
  // 모든 도트의 클래스명에서 "active"를 제거하여 비활성화
  for (i = 0; i < dots.length; i++) {
    dots[i].className = dots[i].className.replace(" active", "");
  }
  // 현재 슬라이드를 표시하고, 해당 도트를 활성화
  slides[slideIndex-1].style.display = "block";
  dots[slideIndex-1].className += " active";
}