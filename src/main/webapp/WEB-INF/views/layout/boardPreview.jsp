<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<link rel="stylesheet" type="text/css" href="/resources/css/mainPage/boardPreview.css">
<div class="boardContainer">
    <div class="boardPre">
        <span class="category">여행지 정보 - 여행 이야기</span>
        <a class="allboard"
           href="/board/list?categoryNo=11&curPage=${curPage}">+더보기</a>
        <hr>
        <table class="table table-striped table-hover table-sm">
            <%-- <colgroup> --%>
            <%-- 	<col style="width: 10%;"> --%>
            <%-- 	<col style="width: 45%;"> --%>
            <%-- 	<col style="width: 15%;"> --%>
            <%-- 	<col style="width: 10%;"> --%>
            <%-- 	<col style="width: 20%;"> --%>
            <%-- </colgroup> --%>
            <tr>
                <th class="title">제목</th>
                <th class="nick">작성자</th>
            </tr>
            <c:forEach var="board" items="${list }">
                <c:if test="${board.categoryNo == 11}">
                    <%--        <c:if test="${board.categoryNo eq 31}">--%>
                    <tr class="boardcontent">
                        <td class="title">
                            <a class="head"
                               href="../board/view?categoryNo=${board.categoryNo}&boardNo=${board.boardNo }&curPage=${curPage}">${board.title }</a>
                        </td>
                        <td class="nick">${board.nickName }</td>
                    </tr>
                    <%--        </c:if>--%>
                </c:if>
            </c:forEach>

        </table>
    </div>
    <div class="boardPre">
        <span class="category">이벤트 - 이벤트 공지사항</span><a class="allboard"
                                                             href="/board/list?categoryNo=51&curPage=${curPage}">+더보기</a>
        <hr>
        <table class="table table-striped table-hover table-sm">
            <%-- <colgroup> --%>
            <%-- 	<col style="width: 10%;"> --%>
            <%-- 	<col style="width: 45%;"> --%>
            <%-- 	<col style="width: 15%;"> --%>
            <%-- 	<col style="width: 10%;"> --%>
            <%-- 	<col style="width: 20%;"> --%>
            <%-- </colgroup> --%>
            <tr>
                <th class="title">제목</th>
                <th class="nick">작성자</th>
            </tr>
            <c:forEach var="board" items="${list }">
                <c:if test="${board.categoryNo == 51}">

                    <%--        <c:if test="${board.categoryNo eq 31}">--%>
                    <tr class="boardcontent">
                        <td class="title">
                            <a class="head"
                               href="../board/view?categoryNo=${board.categoryNo}&boardNo=${board.boardNo }&curPage=${curPage}">${board.title }</a>
                        </td>
                        <td class="nick">${board.nickName }</td>
                    </tr>
                    <%--        </c:if>--%>
                </c:if>
            </c:forEach>
        </table>
    </div>
    <div class="boardPre">
        <span class="category">여행 Q&A - 꿀팁게시판</span><a class="allboard" href="/board/list?categoryNo=41&curPage=${curPage}">+더보기</a>
        <hr>
        <table class="table table-striped table-hover table-sm">
            <%-- <colgroup> --%>
            <%-- 	<col style="width: 10%;"> --%>
            <%-- 	<col style="width: 45%;"> --%>
            <%-- 	<col style="width: 15%;"> --%>
            <%-- 	<col style="width: 10%;"> --%>
            <%-- 	<col style="width: 20%;"> --%>
            <%-- </colgroup> --%>
            <tr>
                <th class="title">제목</th>
                <th class="nick">작성자</th>
            </tr>
            <c:forEach var="board" items="${list }">
                <c:if test="${board.categoryNo == 41}">

                    <%--        <c:if test="${board.categoryNo eq 31}">--%>
                    <tr class="boardcontent">
                        <td class="title">
                            <a class="head"
                               href="../board/view?categoryNo=${board.categoryNo}&boardNo=${board.boardNo }&curPage=${curPage}">${board.title }</a>
                        </td>
                        <td class="nick">${board.nickName }</td>
                    </tr>
                    <%--        </c:if>--%>
                </c:if>
            </c:forEach>
        </table>
    </div>
    <div class="boardPre">
        <span class="category">지역별 여행 - 지역 리스트</span><a class="allboard"
                                                        href="/board/list?categoryNo=31&curPage=${curPage}">+더보기</a>
        <hr>
        <table class="table table-striped table-hover table-sm">
            <%-- <colgroup> --%>
            <%-- 	<col style="width: 10%;"> --%>
            <%-- 	<col style="width: 45%;"> --%>
            <%-- 	<col style="width: 15%;"> --%>
            <%-- 	<col style="width: 10%;"> --%>
            <%-- 	<col style="width: 20%;"> --%>
            <%-- </colgroup> --%>
            <tr>
                <th class="title">제목</th>
                <th class="nick">작성자</th>
            </tr>
            <c:forEach var="board" items="${list }">
                <c:if test="${board.categoryNo == 31}">

                    <%--        <c:if test="${board.categoryNo eq 31}">--%>
                    <tr class="boardcontent">
                        <td class="title">
                            <a class="head"
                               href="../board/view?categoryNo=${board.categoryNo}&boardNo=${board.boardNo }&curPage=${curPage}">${board.title }</a>
                        </td>
                        <td class="nick">${board.nickName }</td>
                    </tr>
                    <%--        </c:if>--%>
                </c:if>
            </c:forEach>
        </table>
    </div>
</div>