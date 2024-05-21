<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: c
  Date: 24. 5. 17.
  Time: 오후 12:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<c:import url="/WEB-INF/views/layout/header.jsp"/>
<html>
<head>
    <title>신고내역</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <link href="https://netdna.bootstrapcdn.com/font-awesome/4.0.3/css/font-awesome.css" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css?family=Oswald:700" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css?family=Droid+Serif" rel="stylesheet">
    	<link href="/resources/css/report/reportList.css" rel="stylesheet" type="text/css">
    	
<script>
  $(function(){
    $(".fold-table tr.view").on("click", function(){
      if($(this).hasClass("open")) {
        $(this).removeClass("open").next(".fold").removeClass("open");
      } else {
        $(".fold-table tr.view").removeClass("open").next(".fold").removeClass("open");
        $(this).addClass("open").next(".fold").addClass("open");
      }
    });
  });
</script>

</head>

<%-- <body>
<h3>신고 내역</h3>
<div class="content">
<table>
    <h3>게시물 신고</h3>
    <tr>
        <th>신고번호</th>
        <th>신고내용</th>
        <th>신고일자</th>
        <th>신고종류</th>
        <th>작성된게시글</th>
        <th>닉네임</th>
        <th></th>
    </tr>
<c:forEach items="${boardlist}" var="list">
<tr>
    <td>${list.boardReportNo}</td>
    <td>${list.reportContent}</td>
    <td><fmt:formatDate value="${list.reportDate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
    <td>${list.reportType}</td>
    <td>${list.content}</td>
    <td>${list.nickname}</td>
    <td><a class='btn-fill' href="/report/deleteReport?reportno=${list.boardReportNo}"><button>삭제</button></a></td>
</tr>
</c:forEach>
</table>
<h3>댓글 신고</h3>
<table>
<tr>
    <th>신고번호</th>
    <th>신고내용</th>
    <th>신고일자</th>
    <th>신고종류</th>
    <th>작성된댓글</th>
    <th>닉네임</th>
    <th></th>
</tr>
<c:forEach items="${commlist}" var="commlist">
    <tr>
        <td>${commlist.commReportNo}</td>
        <td>${commlist.content}</td>
        <td><fmt:formatDate value="${commlist.commReportDate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
        <td>${commlist.commReport}</td>
        <td>${commlist.commContent}</td>
        <td>${commlist.nickname}</td>
        <td><a class='btn-fill' href="/report/deleteCommReport?reportno=${commlist.commReportNo}"><button>삭제</button></a></td>
    </tr>
</c:forEach>
</table>
</div>
<div class="btn-fill">
	<a href="/user/adminPage">
  		<button>돌아가기</button>
	</a>
</div>

</body>
 --%>
 
<body>
<h3>신고 내역</h3>
<div class="content">
	<div class="table-wrapper">
	<div class="report-section">
	<h3 class="boardReport">게시글 신고</h3>
	<div class="table-container">
        <table class="fold-table">
        <thead>
            <tr>
                <th class="bold-text">신고번호</th>
                <th class="bold-text">신고일자</th>
                <th class="bold-text">신고종류</th>
                <th class="bold-text">작성된게시글</th>
                <th class="bold-text">닉네임</th>
                <th></th>
            </tr>
        </thead>
        <tbody>
    <c:forEach items="${boardlist}" var="list">
        <tr class="view">
            <td class="report">
               <label for="board-${list.boardReportNo}">
                 ${list.boardReportNo}
               </label>
            </td>
            <td class="report"><fmt:formatDate value="${list.reportDate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
            <td class="report">${list.reportType}</td>
            <td class="report">${list.content}</td>
            <td class="report">${list.nickname}</td>
            <td><a class='btn-fill' href="/report/deleteReport?reportno=${list.boardReportNo}"><button>삭제</button></a></td>
            </tr>
            <tr class="fold">
                    <td colspan="7">
                        <input type="checkbox" id="board-${list.boardReportNo}">
                        <div class="fold-content">
                        	${list.reportContent}
                        </div>
                    </td>
                </tr>
    </c:forEach>
</tbody>
    </table>
	</div>
	</div>
	 <div class="report-section">
    <h3 class="commReport">댓글 신고</h3>
    <div class="table-container">
    <table class="fold-table">
        <thead>
            <tr>
                <th class="bold-text">신고번호</th>
                <th class="bold-text">신고일자</th>
                <th class="bold-text">신고종류</th>
                <th class="bold-text">작성된댓글</th>
                <th class="bold-text">닉네임</th>
                <th></th>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${commlist}" var="commlist">
                <tr class="view">
                    <td>
                        <label for="comm-${commlist.commReportNo}">
                            ${commlist.commReportNo}
                        </label>
                    </td>
<%--                     <td>${commlist.content}</td> --%>
                    <td><fmt:formatDate value="${commlist.commReportDate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                    <td>${commlist.commReport}</td>
                    <td>${commlist.commContent}</td>
                    <td>${commlist.nickname}</td>
                    <td><a class='btn-fill' href="/report/deleteCommReport?reportno=${commlist.commReportNo}"><button>삭제</button></a></td>
                </tr>
                <tr class="fold">
                    <td colspan="7">
                        <input type="checkbox" id="comm-${commlist.commReportNo}">
                        <div class="fold-content">
                    		${commlist.content}
                        </div>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
    </div>
    </div>
<div class="report-section">
    <h3 class="itemReport">상품 신고</h3>
    <div class="table-container">
    <table class="fold-table">
        <thead>
            <tr>
                <th class="bold-text">신고번호</th>
                <th class="bold-text">신고일자</th>
                <th class="bold-text">신고종류</th>
                <th class="bold-text">작성된상품</th>
                <th class="bold-text">닉네임</th>
                <th></th>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${itemlist}" var="item">
                <tr class="view">
                    <td>
                        <label for="item-${item.reportNo}">
                            ${item.reportNo}
                        </label>
                    </td>
                    <td><fmt:formatDate value="${item.reportDate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                    <td>${item.itemReport}</td>
                    <td>${item.itemName}</td>
                    <td>${item.nickName}</td>
                    <td><a class='btn-fill' href="/report/deleteItemReport?reportno=${item.reportNo}"><button>삭제</button></a></td>
                </tr>
                <tr class="fold">
                    <td colspan="7">
                        <input type="checkbox" id="item-${item.itemReportNo}">
                        <div class="fold-content">
                            ${item.reportContent}
                        </div>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
    </div>
    </div>    
    
    
</div>
</div>
<div class="btn-fill">
    <a href="/user/adminPage">
        <button>돌아가기</button>
    </a>
</div>
</body>

</html>
