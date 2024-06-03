<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<link rel="stylesheet" href="/resources/css/shop/detail.css">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet"
      integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
<script type="text/javascript" src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz"
        crossorigin="anonymous"></script>
<script>
    function incrementQuantity() {
        var quantityInput = document.getElementById('quantity');
        var currentValue = parseInt(quantityInput.value);
        var remain = parseInt("${item.remain}");
        if (currentValue < remain) {
            quantityInput.value = currentValue + 1;
        } else {
            alert("남은 재고를 초과할 수 없습니다.");
        }
    }

    function decrementQuantity() {
        var quantityInput = document.getElementById('quantity');
        var currentValue = parseInt(quantityInput.value);
        if (currentValue > 1) {
            quantityInput.value = currentValue - 1;
        }
    }
</script>
<script type="text/javascript">
    $(function () {


        $(document).ready(function () {
            handelGetReview()
        })

        //장바구니 담기 버튼
        $("#addToCartBtn").click(function () {
            // isLogin 변수에는 로그인 상태를 나타내는 값이 있어야 합니다.
            // 만약 이 값이 서버에서 제공되는 변수라면 그 값을 사용해야 합니다.
            // 예: var isLogin = ${isLogin};
            var itemNo =
            ${item.itemNo}
            var quantity = $("#quantity").val()
            if (${empty isLogin or isLogin < 1}) {
                //로그인 페이지로 이동
                alert("로그인 이후 이용이 가능합니다")
//              $(location).attr('href', './login')
                window.location.href = "/login";
            } else {
                $.ajax({
                    type: "get"
                    , url: "../basket/insert"
                    , data: {
                        itemNo: itemNo
                        , quantity: quantity
                    }
                    , dataType: "json"
                    , success: function (res) {
                        console.log(res)
//     				console.log("AJAX 성공")
//     				console.log(res)
                        $(function () {
                            window.location.href = "../basket/userbasket";
                        })

                    }
                    , error: function () {
                        console.log("AJAX 실패")
                    }
                })

            }
        });

        // 구매하기 버튼 클릭
        $("#purchaseBtn").click(function () {
            var itemNo = ${item.itemNo};
            var quantity = $("#quantity").val();
            if (${empty isLogin or isLogin < 1}) {
                // 로그인 페이지로 이동
                alert("로그인 이후 이용이 가능합니다");
                window.location.href = "/login";
            } else {
                // 로그인 상태일 때 주문서 페이지로 이동
                window.location.href = '../order/ordersheet?itemNo=' + itemNo + '&quantity=' + quantity;
            }
        });

        //신고하기 버튼 클릭
        $("#reportBtn").click(function () {
            var itemNo = ${item.itemNo}
                $(function () {
                    window.location.href = '../report/itemReport?itemNo=' + itemNo;
                })
        })

        //리뷰 AJAX 통신
        function handelGetReview() {
            console.log("getReview")

            var itemNo = '${item.itemNo}'
            $.ajax({
                type: "get"
                , url: "./review/loadreview"
                , data: {
                    itemNo: itemNo
                }
                , dataType: "JSON"
                , success: function (res) {
                    console.log(res)
                    //view에 보이도록 추가.
                    renderReviews(res);

                }
                , error: function () {
                    console.log("AJAX 실패")
                }
            })

        };


        function renderReviews(reviews) {
            var reviewHtml = '';
            var userno_d = ${dto1.userno};
            if (reviews.length > 0) {
                reviews.forEach(function (review) {
                    reviewHtml += '<div class="review">';
                    reviewHtml += '<div class="tit">';
                    reviewHtml += '<h5>' + review.reviewTitle + '</h5>';
                    reviewHtml += '<h6>' + review.nickname + '</h6>';
                    reviewHtml += '</div>';
                    reviewHtml += '<div class="tit">';
                    reviewHtml += '<p>' + review.reviewContent + '</p>';
                    if (userno_d === review.userNo) {
                        reviewHtml += '<div>';
                        reviewHtml += '<a class="editReviewBtn" data-review-No="' + review.reviewNo + '" data-review-title="' + review.reviewTitle + '" data-review-content="' + review.reviewContent + '">수정</a>';
                        reviewHtml += '<a class="deleteReviewBtn" data-review-No="' + review.reviewNo + '"> 삭제</a>';
                        reviewHtml += '</div>';
                    } else if (${dto1.gradeno == 0 || dto1.gradeno == 5000}) {
                        reviewHtml += '<div>';
                        reviewHtml += '<a class="deleteReviewBtn" data-review-No="' + review.reviewNo + '"> 삭제</a>';
                        reviewHtml += '</div>';
                    }
                    reviewHtml += '</div>';
                    reviewHtml += '<small> ' + new Date(review.createReviewDate).toLocaleDateString().replace(/\.$/, '') + '</small>';
                    reviewHtml += '</div><hr>';
                    // 수정 버튼 추가



                });
            } else {
                reviewHtml = '<p>리뷰가 아직 작성되지 않았습니다</p>';
            }
            $("#reviewWrap").html(reviewHtml);
        }

        $('#reviewWrap').on('click', '.deleteReviewBtn', function () {
            var reviewNo = $(this).data('review-no');
            if (confirm('정말 이 리뷰를 삭제하시겠습니까?')) {
                $.ajax({
                    url: '/shop/review/deletereview',
                    type: 'POST',
                    data: {
                        reviewNo: reviewNo
                    },
                    dataType: "JSON",
                    success: function (res) {

                        // 페이지를 새로고침하거나, 삭제된 리뷰를 제거하는 등의 후속 작업을 여기에 추가
                        location.reload();
                    },
                    error: function () {
                        location.reload();

                    }
                });
            }
        });

        $(document).ready(function () {
            // 리뷰 수정 버튼 클릭 이벤트
            $(document).on('click', '.editReviewBtn', function () {
                // 해당 리뷰의 정보 가져오기
                var reviewNo = $(this).data('review-no'); // 변수명을 수정하였습니다.
                var reviewTitle = $(this).data('review-title');
                var reviewContent = $(this).data('review-content');

                // 팝업 창 옵션 설정
                var popOption = "width=500,height=500,top=300,left=300";

                // 수정 팝업 창 열기
                var openUrl = '/shop/review/updateReviewForm?reviewNo=' + reviewNo + '&reviewTitle=' + encodeURIComponent(reviewTitle) + '&reviewContent=' + encodeURIComponent(reviewContent);
                window.open(openUrl, 'popup', popOption);
            });
        });


        popupsendForm.onclick = function () {
            let popOption = "width = 500px, height=500px, top=300px, left=300px"
            let openUrl = '/shop/review/writeReviewForm?itemNo=${item.itemNo}'
            window.open(openUrl, 'popup', popOption);
        }
    })
</script>
</head>
<body>
<c:import url="/WEB-INF/views/layout/header.jsp"/>
<c:import url="/WEB-INF/views/layout/boardmenu.jsp"/>

<div class="container">
    <div class="tit">
        <h1>상품 정보</h1>
        <div>
            <a href="./">
                <button>목록 페이지로</button>
            </a>
        </div>
    </div>

    <div id="itemwarp">
        <table>
            <tr>
                <td>
                    <c:choose>
                        <c:when test="${not empty files}">
                            <c:forEach items="${files}" var="files">
                                <c:if test="${not empty file}">
                                    <img alt="ItemImg" src="/resources/img/shop/upload/${file.storedName}">
                                </c:if>
                                <c:if test="${empty files.itemNo}">
                                    <img src="/resources/img/shop/nullimg.jpg" alt="notready">
                                </c:if>
                            </c:forEach>
                        </c:when>
                        <c:when test="${empty imgFiles}">
                            <img src="/resources/img/shop/nullimg.jpg" alt="notready">
                        </c:when>
                    </c:choose>
                </td>
            </tr>
            <tr>
                <td>${item.itemName}: <fmt:setLocale value="ko_KR"/><fmt:formatNumber type="currency"
                                                                                      value="${item.price}"/></td>
            </tr>
            <tr>
                <td>재고 : ${item.remain}</td>
            </tr>
            <!--         <tr> -->
            <!--             <td> -->
            <!--                 Quantity Selector -->
            <!--                 <label for="quantity">수량:</label> -->
            <!--                 <input type="number" id="quantity" name="quantity" min="1" value="1"> -->
            <!--             </td> -->
            <!--         </tr> -->
            <tr>
                <td>
                    <!-- 수량 선택기에 증가 및 감소 버튼 추가 -->

                    <div class="quantity-selector">
                        <label for="quantity">수량: </label>
                        <input type="text" id="quantity" name="quantity" value="1" readonly="readonly">
                        <button type="button" onclick="incrementQuantity()">+</button>
                        <button type="button" onclick="decrementQuantity()">-</button>
                    </div>
                </td>
            </tr>
            <tr>
                <td>
                    <!-- 장바구니 담기 버튼 -->
                    <button type="button" id="addToCartBtn">장바구니 담기</button>
                    <!-- 구매하기 버튼 -->
                    <button type="button" id="purchaseBtn">구매하기</button>
                    <!-- 신고하기 버튼 -->
                    <button type="button" id="reportBtn">신고하기</button>
                </td>
            </tr>
            <tr>
                <td>${item.itemComm}</td>
            </tr>

        </table>

    </div>

    <form id="" action="" hidden="hidden" method="post"></form>

    <div id="reviewWrap">


    </div>
    <br>
    <c:if test="${countMyOrder > 0}">
        <button id="popupsendForm">리뷰 쓰기</button>
    </c:if>


</div>
<!-- .container End -->
<%--    <c:if test="${item.userNo eq dto1.userno}">--%>


<%--    </c:if>--%>


<c:import url="/WEB-INF/views/layout/footer.jsp"/>