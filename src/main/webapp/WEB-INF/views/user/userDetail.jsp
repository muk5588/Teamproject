<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: c
  Date: 2024-04-04
  Time: 오후 12:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<jsp:include page="/WEB-INF/views/layout/header.jsp"/>
<h3>${dto.nickname } 회원 정보</h3>
<div class="warpper">
    <div class="warp">
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
                <th>아이디</th>
                <td>${dto.userid }</td>
            </tr>
            <tr>
                <th>닉네임</th>
                <td>${dto.nickname }</td>
            </tr>
            <tr>
                <th>성별</th>
                <td>${dto.gender }</td>
            </tr>
            <tr>
                <th>이메일</th>
                <td>${dto.email }</td>
            </tr>
            <tr>
                <th>우편번호</th>
                <td>${dto.postcode }</td>
            </tr>
            <tr>
                <th>주소</th>
                <td>${dto.address }</td>
            </tr>
            <tr>
                <th>상세주소</th>
                <td>${dto.detailAddress }</td>
            </tr>
            <tr>
                <th>참고항목</th>
                <td>${dto.extraAddress }</td>
            </tr>
            <tr>
                <th>전화번호</th>
                <td>${dto.phone }</td>
            </tr>
        </table>
        <c:choose>
            <c:when test="${dto.gradeno == 0 || dto.gradeno == 5000}">
                <div class='btnSet'>
                    <a class='btn-fill' href="/user/userList">고객 목록</a>
                    <a class='btn-fill' href="/user/updateUser?userno=${dto.userno}">수정</a>
                    <a class='btn-fill' href="/user/deleteUser?userno=${dto.userno}">삭제</a>
                </div>
            </c:when>
            <c:otherwise>
                <div class='btnSet'>
                    <a class='btn-fill' href="/user/updateUser?userno=${dto.userno}">수정</a>
                    <a class='btn-fill' href="/user/updatePass?userno=${dto.userno}">비밀번호번경</a>
                    <a class='btn-fill' href="/user/deleteUser?userno=${dto.userno}">삭제</a>
                </div>
            </c:otherwise>
        </c:choose>
        <c:choose>
        <c:when test="${not empty list}">
            <table class="table table-striped table-hover table-sm">
                    <%-- <colgroup> --%>
                    <%-- 	<col style="width: 10%;"> --%>
                    <%-- 	<col style="width: 45%;"> --%>
                    <%-- 	<col style="width: 15%;"> --%>
                    <%-- 	<col style="width: 10%;"> --%>
                    <%-- 	<col style="width: 20%;"> --%>
                    <%-- </colgroup> --%>
                <tr>
                    <th><input type="checkbox" id="checkboxAllCheck"></th>
                    <th>글 번호</th>
                    <th>제목</th>
                    <th>본문</th>
                    <th>작성자 닉네임</th>
                    <th>조회수</th>
                    <th>최초작성일</th>
                    <th>추천수</th>
                </tr>
                <c:forEach var="board" items="${list }" begin="0" end="3">
                    <tr>
                        <td class="checkbox"><input type="checkbox" value="${board.boardNo }" name="deleteNum"
                                                    class="delCheckBox"></td>
                        <td class="no">${board.boardNo }</td>
                        <td class="title">
                            <a href="./view?boardNo=${board.boardNo }&curPage=${curPage}">${board.title }</a>
                        </td>
                        <td class="content">${board.content }</td>
                        <td class="nick">${board.nickName }</td>
                        <td class="hit">${board.boardView }</td>
                        <td class="date">
                            <fmt:formatDate value="${board.createDate }" pattern="yyyy-MM-dd"/>
                        </td>
                        <c:forEach items="${totalrecomm }" var="recommList">
                            <c:if test="${recommList.BOARDNO eq board.boardNo }">
                                <td><a id="totalRecommend">${recommList.GOOD }</a></td>
                            </c:if>
                        </c:forEach>
                    </tr>
                </c:forEach>
                <tr>
                    <td>
                        <button id="deleteBtn">체크 삭제</button>
                    </td>
                </tr>
            </table>
        </c:when>
        <c:otherwise>
        <table class="table table-striped table-hover table-sm">
                <%-- <colgroup> --%>
                <%-- 	<col style="width: 10%;"> --%>
                <%-- 	<col style="width: 45%;"> --%>
                <%-- 	<col style="width: 15%;"> --%>
                <%-- 	<col style="width: 10%;"> --%>
                <%-- 	<col style="width: 20%;"> --%>
                <%-- </colgroup> --%>
            <tr>
               <th>작성한 글이 없습니다</th>
            </tr>
            </c:otherwise>
            </c:choose>
    </div>
</div>
<jsp:include page="/WEB-INF/views/layout/footer.jsp"/>
</body>
</html>
