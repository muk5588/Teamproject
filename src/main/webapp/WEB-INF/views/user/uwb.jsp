<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: c
  Date: 24. 5. 24.
  Time: 오후 4:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:choose>
    <c:when test="${not empty list and (userno == dto1.userno) }">
        <a class = "allboard" onclick="location.href='../board/userbyboardlist?userno=${dto1.userno}'">+더보기</a>
    </c:when>
    <c:when test="${not empty list and(dto1.gradeno == 0 || dto1.gradeno == 5000) and (dto1.userno != userno)}">
        <a class = "allboard" onclick="location.href='../board/userbyboardlist?userno=${userno}'">+더보기</a>
    </c:when>
</c:choose>
<%--<a class = "allboard" onclick="location.href='../board/userbyboardlist?userno=${dto1.userno}'">+더보기</a>--%>
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