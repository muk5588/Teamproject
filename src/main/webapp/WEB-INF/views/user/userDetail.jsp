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
    <link href="/resources/css/common/common.css" rel="stylesheet" type="text/css">
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <script src="/resources/js/user/blackList.js" defer></script>

    <style type="text/css">
        @import url('https://webfontworld.github.io/NexonMaplestory/NexonMaplestory.css');

        * {
            font-family: 'NexonMaplestory';
            font-weight: 300;
            font-style: normal;
        }
        body {
            margin: 0;
            padding: 0;
            background-color: #f4f4f9;
        }

        .warpper {
            width: 100%;
            max-width: 1200px;
            margin: 0 auto;
            padding: 20px;
        }

        .warp {
            background: #fff;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
        }

        h3 {
            color: #333;
            margin-bottom: 20px;
            font-size: 1.5em;
            border-bottom: 2px solid #007bff;
            padding-bottom: 10px;
        }

        table {
            width: 100%;
            border-collapse: collapse;
            margin-bottom: 20px;
        }

        th, td {
            padding: 12px;
            border: 1px solid #ddd;
            text-align: left;
        }

        th {
            background-color: #f8f8f8;
            color: #333;
            font-weight: bold;
        }

        tr:nth-child(even) {
            background-color: #f9f9f9;
        }

        .btnSet {
            margin-top: 20px;
        }

        .btn-fill {
            display: inline-block;
            padding: 10px 20px;
            margin-right: 10px;
            background-color: #007bff;
            color: #fff;
            border: none;
            border-radius: 4px;
            text-decoration: none;
            cursor: pointer;
            transition: background-color 0.3s;
        }

        .btn-fill:hover {
            background-color: #0056b3;
        }

        button {
            padding: 10px 20px;
            margin-top: 10px;
            background-color: #28a745;
            color: #fff;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            transition: background-color 0.3s;
        }

        button:hover {
            background-color: #218838;
        }

        .table-hover tbody tr:hover {
            background-color: #f1f1f1;
        }

        .table-sm th, .table-sm td {
            padding: 8px;
        }

        .table-striped tbody tr:nth-of-type(odd) {
            background-color: #f9f9f9;
        }
    </style>
</head>
<body>
<jsp:include page="/WEB-INF/views/layout/header.jsp"/>
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
                    <a class='btn-fill' href="/user/userList">고객 목록</a>
                    <a class='btn-fill' href='javascript:userBlack();' class="btn btn-danger">사용자 정지</a>
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
                    <button onclick="location.href='../board/userbyboardlist?userno=${dto1.userno}'">전체 작성글</button>
                    <table class="table table-striped table-hover table-sm">
                             <colgroup>
                             	 <col style="width: 8%;">
                             	 <col style="width: 50%;">
                             	 <col style="width: 15%;">
                             	 <col style="width: 8%;">
                             	 <col style="width: 10%;">
                                 <col style="width: 8%;">
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
                                    <a href="../board/view?boardNo=${board.boardNo }&curPage=${curPage}">${board.title }</a>
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
                    <button onclick="location.href='../board/userbyrecommlist?userno=${dto1.userno}'">전체 추천글</button>
                    <table class="table table-striped table-hover table-sm">
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
                                    <a href="../board/view?boardNo=${board2.boardNo }&curPage=${curPage}">${board2.title }</a>
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
