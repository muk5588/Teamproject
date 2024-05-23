<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: c
  Date: 2024-04-04
  Time: 오후 12:47
  To change this template use File | Settings | File Templates.
--%>
<link rel="stylesheet" type="text/css" href="/resources/css/user/userDetail.css">
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link href="/resources/css/common/common.css" rel="stylesheet" type="text/css">
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <script src="/resources/js/user/blackList.js" defer></script>

    <style type="text/css">
        @import url('https://webfontworld.github.io/NexonMaplestory/NexonMaplestory.css');


    </style>
</head>
<body>
<jsp:include page="/WEB-INF/views/layout/header.jsp"/>
<jsp:include page="/WEB-INF/views/layout/boardmenu.jsp"/>

<div class="warpper">
    <div class="warp">
        <c:choose>
            <c:when test="${(dto1.gradeno == 0 || dto1.gradeno == 5000) && (dto1.userno != userno)}">
                <h3>${dto.nickname } 회원 정보</h3>
                <table class='w-pct60'>
                    <tr>
                        <th>회원번호</th>
                        <td name="userno">${dto.userno }</td>
                    </tr>
                    <tr>
                        <th>이름</th>
                        <td>${dto.name }</td>
                    </tr>
                    <tr>
                        <th>닉네임</th>
                        <td>${dto.nickname }</td>
                    </tr>
                    <tr>
                        <th>정지여부</th>
                        <td>${dto.black }</td>
                    </tr>
                </table>
                <div class='btnSet'>
                	<c:choose>
                		<c:when test="${dto.black == 'Y'}">
                    		<a class='btn-fill' href="/user/userList">고객 목록</a>
                    		<a class='btn-fill' href='javascript:userWhite();' class="btn btn-danger">사용자 해제</a>
                    		<input type="checkbox" name="chk" class="chk" onclick="chkClicked()" value="${dto.userno}" checked hidden>
                    	</c:when>
                		<c:when test="${dto.black == 'N'}">
                    		<a class='btn-fill' href="/user/userList">고객 목록</a>
                    		<a class='btn-fill' href='javascript:userBlack();' class="btn btn-danger">사용자 정지</a>
                    		<input type="checkbox" name="chk" class="chk" onclick="chkClicked()" value="${dto.userno}" checked hidden>
                    	</c:when>
                    </c:choose>
                </div>
            </c:when>
            <c:otherwise>
                <h3>${dto1.nickname } 회원 정보</h3>
                <table class='w-pct60'>
                    <tr>
                        <th>이름</th>
                        <td>${dto1.name }</td>
                    </tr>
                    <tr>
                        <th>아이디</th>
                        <td>${dto1.userid }</td>
                    </tr>
                    <tr>
                        <th>닉네임</th>
                        <td>${dto1.nickname }</td>
                    </tr>
                    <tr>
                        <th>성별</th>
                        <td>${dto1.gender }</td>
                    </tr>
                    <tr>
                        <th>이메일</th>
                        <td>${dto1.email }</td>
                    </tr>
                    <tr>
                        <th>우편번호</th>
                        <c:choose>
                            <c:when test="${dto1.postcode <= 9999}">
                                <td>0${dto1.postcode}</td>
                            </c:when>
                            <c:otherwise>
                                <td>${dto1.postcode}</td>
                            </c:otherwise>
                        </c:choose>
                    </tr>
                    <tr>
                        <th>주소</th>
                        <td>${dto1.address }</td>
                    </tr>
                    <tr>
                        <th>상세주소</th>
                        <td>${dto1.detailAddress }</td>
                    </tr>
                    <tr>
                        <th>참고항목</th>
                        <td>${dto1.extraAddress }</td>
                    </tr>
                    <tr>
                        <th>전화번호</th>
                        <td>${dto1.phone }</td>
                    </tr>
                </table>
                <div class='btnSet'>
                    <a class='btn-fill' href="/user/updateUser?userno=${dto1.userno}">수정</a>
                    <a class='btn-fill' href="/user/updatePass?userno=${dto1.userno}">비밀번호번경</a>
                    <a class='btn-fill' href="/user/deleteUser?userno=${dto1.userno}">탈퇴</a>
                </div>
            </c:otherwise>
        </c:choose>
        <a href="/basket/userbasket">
            <button>장바구니</button>
        </a>
        <div>
            <h3>작성한 게시물</h3>
            <c:choose>
                <c:when test="${not empty list and (userno == dto1.userno)}">
                    <a class = "allboard" onclick="location.href='../board/userbyboardlist?userno=${dto1.userno}'">+더보기</a>
                    <table>
                             <colgroup>
                             	 <col style="width: 7%;">
                             	 <col style="width: 54%;">
                             	 <col style="width: 15%;">
                             	 <col style="width: 7%;">
                             	 <col style="width: 10%;">
                                 <col style="width: 7%;">
                             </colgroup>
                        <tr>
                            <th>글 번호</th>
                            <th>제목</th>
                            <th>작성자 닉네임</th>
                            <th>조회수</th>
                            <th>최초작성일</th>
                            <th>추천수</th>
                        </tr>
                        <c:forEach var="board" items="${list }" begin="0" end="4">
                            <tr>
                                <td class="no">${board.boardNo }</td>
                                <td class="title">
                                    <a href="../board/view?categoryNo=${board.categoryNo}&boardNo=${board.boardNo }&curPage=${curPage}">${board.title }</a>
                                </td>
                                <td class="nick">${board.nickName }</td>
                                <td class="hit">${board.boardView }</td>
                                <td class="date">
                                    <fmt:formatDate value="${board.createDate }" pattern="yyyy-MM-dd"/>
                                </td>
                                <c:forEach items="${totalrecomm }" var="recommList">
                                    <c:if test="${recommList.BOARDNO eq board.boardNo }">
                                        <td><a id="totalRecommend2">${recommList.GOOD }</a></td>
                                    </c:if>
                                </c:forEach>
                            </tr>
                        </c:forEach>
                    </table>
                </c:when>
                <c:otherwise>
                    <span>작성한 게시글이 없습니다.</span>
                </c:otherwise>
            </c:choose>
        </div>
        <div>
            <h3>추천한 게시물</h3>
            <c:choose>
                <c:when test="${not empty list2 and (userno == dto1.userno)}">
                    <a class = "allboard" onclick="location.href='../board/userbyrecommlist?userno=${dto1.userno}'">+더보기</a>
                    <table>
                             <colgroup>
                             	<col style="width: 7%;">
                             	<col style="width: 60%;">
                             	<col style="width: 15% ;">
                             	<col style="width: 7%;">
                             	<col style="width: 10%;">
                             </colgroup>
                        <tr>
                            <th>글 번호</th>
                            <th>제목</th>
                            <th>작성자 닉네임</th>
                            <th>조회수</th>
                            <th>최초작성일</th>
                        </tr>
                        <c:forEach var="board2" items="${list2 }" begin="0" end="4">
                            <tr>
                                <td class="no">${board2.boardNo }</td>
                                <td class="title">
                                    <a href="../board/view?categoryNo=${board2.categoryNo}&boardNo=${board2.boardNo }&curPage=${curPage}">${board2.title }</a>
                                </td>
                                <td class="nick">${board2.nickName }</td>
                                <td class="hit">${board2.boardView }</td>
                                <td class="date">
                                    <fmt:formatDate value="${board2.createDate }" pattern="yyyy-MM-dd"/>
                                </td>
                            </tr>
                        </c:forEach>
                    </table>
                </c:when>
                <c:otherwise>
                    <span>추천한 글이 없습니다.</span>
                </c:otherwise>
            </c:choose>
        </div>
    </div>
</div>
<jsp:include page="/WEB-INF/views/layout/footer.jsp"/>
</body>
</html>
