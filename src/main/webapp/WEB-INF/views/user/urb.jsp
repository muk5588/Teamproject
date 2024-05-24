<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: c
  Date: 24. 5. 24.
  Time: 오후 4:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
