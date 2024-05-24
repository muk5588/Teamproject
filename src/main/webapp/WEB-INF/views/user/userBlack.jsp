<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
	<script src="/resources/js/user/blackList.js" defer></script>

</head>
<body>
<jsp:include page="/WEB-INF/views/layout/header.jsp"></jsp:include>

<div class="warpper">
    <div class="warp">
        <div class="container">
            <h3>회원 목록</h3>
            
           <form class="d-flex mb-3" action="${URL }" method="get">
               <select class="form-select me-2" name="searchKind" aria-label="Search Type">
                   <option value="nickName" <c:if test="${not empty paging.searchKind and paging.searchKind eq 'nickName'}">selected="selected"</c:if>>닉네임</option>
                   <option value="userno" <c:if test="${not empty paging.searchKind and paging.searchKind eq 'userno'}">selected="selected"</c:if>>회원 번호</option>
               </select>
               <input class="form-control me-2" type="search" name="search" placeholder="Search" aria-label="Search" value="${paging.search }">
               <button class="btn btn-outline-success" type="submit">Search</button>
           </form>
            
            <table class="table table-striped table-hover table-sm">
                <tr>
                	<th>
                     <label class="checkbox-inline">
                     <input type="checkbox" id="allCheckBox" onclick="allChecked()">
                     </label>
                    <th class='w-px60'>회원번호</th>
                    <th>아이디</th>
                    <th class='w-px200'>이름</th>
                    <th>닉네임</th>
                    <th>성별</th>
                    <th>정지여부</th>
                </tr>
                <!-- for(꺼낸 배열 변수를 담을 새로운 변수 (String x) : 배열 변수(list)) -->
                <!-- items : 배열 변수 -->
                <!-- var : 꺼낸 배열 변수를 담을 새로운 변수 -->
                <c:forEach var="UserDTO" items="${list}">
                    <tr>
                    	<td>
                         <label class="checkbox-inline">
                         	<input type="checkbox" name="chk" class="chk"onclick="chkClicked()" value="${UserDTO.userno }">
                         </label>
                        <td>${UserDTO.userno }</td>
                        <td>${UserDTO.userid }</td>
                        <td><a href='/user/detailUser?userno=${UserDTO.userno}'>${UserDTO.name }</a></td>
                        <td>${UserDTO.nickname}</td>
                        <td>${UserDTO.gender }</td>
                        <td>${UserDTO.black }</td>
                    </tr>
                </c:forEach>
            </table>
    	</div>
	</div>
</div>
<br>
	<div class="d-flex justify-content-end container">
      <!-- <a id="btnBlack" class="btn btn-danger">블랙</a> -->
      <a href='javascript:userBlack();' class="btn btn-danger">정지</a>
      <a href='javascript:userWhite();' class="btn btn-warning">해제</a>
    </div>

<div>

<!--     <ul class="pagination pagination-sm justify-content-center"> -->
<%--         첫 페이지로 이동 --%>
<%--         <c:if test="${paging.curPage ne 1 }"> --%>
<!--             <li class="page-item"> -->
<%--                 <a class="page-link" href="${URL}">&larr; 처음</a> --%>
<!--             </li> -->
<%--         </c:if> --%>



<%--         이전 페이징 리스트 이동 --%>
<%--         <c:choose> --%>
<%--             <c:when test="${paging.startPage ne 1 }"> --%>
<!--                 <li class="page-item"> -->
<%--                     <a class="page-link" href="${URL}?curPage=${paging.startPage - paging.pageCount }">&laquo;</a> --%>
<!--                 </li> -->
<%--             </c:when> --%>

<%--             <c:when test="${paging.startPage eq 1 }"> --%>
<!--                 <li class="page-item"> -->
<!--                     <a class="page-link disabled">&laquo;</a> -->
<!--                 </li> -->
<%--             </c:when> --%>
<%--         </c:choose> --%>



<%--         이전 페이지로 이동 --%>
<%--         <c:if test="${paging.curPage > 1 }"> --%>
<!--             <li class="page-item"> -->
<%--                 <a class="page-link" href="${URL}?curPage=${paging.curPage - 1 }">&lt;</a> --%>
<!--             </li> -->
<%--         </c:if> --%>



<%--         페이징 번호 목록 --%>
<%--         <c:forEach var="i" begin="${paging.startPage }" end="${paging.endPage }"> --%>
<%--             <c:if test="${paging.curPage eq i }"> --%>
<!--                 <li class="page-item"> -->
<%--                     <a class="page-link active" href="${URL}?curPage=${i }">${i }</a> --%>
<!--                 </li> -->
<%--             </c:if> --%>

<%--             <c:if test="${paging.curPage ne i }"> --%>
<!--                 <li class="page-item"> -->
<%--                     <a class="page-link" href="${URL}?curPage=${i }">${i }</a> --%>
<!--                 </li> -->
<%--             </c:if> --%>
<%--         </c:forEach> --%>



<%--         다음 페이지로 이동 --%>
<%--         <c:if test="${paging.curPage < paging.totalPage }"> --%>
<!--             <li class="page-item"> -->
<%--                 <a class="page-link" href="${URL}?curPage=${paging.curPage + 1 }">&gt;</a> --%>
<!--             </li> -->
<%--         </c:if> --%>



<%--         다음 페이징 리스트 이동 --%>
<%--         <c:choose> --%>
<%--             <c:when test="${paging.startPage ne paging.totalPage }"> --%>
<!--                 <li class="page-item"> -->
<%--                     <a class="page-link" href="${URL}?curPage=${paging.startPage + paging.pageCount }">&raquo;</a> --%>
<!--                 </li> -->
<%--             </c:when> --%>

<%--             <c:when test="${paging.startPage eq paging.totalPage }"> --%>
<!--                 <li class="page-item"> -->
<!--                     <a class="page-link disabled">&raquo;</a> -->
<!--                 </li> -->
<%--             </c:when> --%>
<%--         </c:choose> --%>



<%--         마지막 페이지로 이동 --%>
<%--         <c:if test="${paging.curPage ne paging.totalPage }"> --%>
<!--             <li class="page-item"> -->
<%--                 <a class="page-link" href="${URL}?curPage=${paging.totalPage }">끝 &rarr;</a> --%>
<!--             </li> -->
<%--         </c:if> --%>
<!--     </ul> -->
         <c:import url="/WEB-INF/views/layout/adminPaging.jsp" />

</div>

<!-- ------------------------------------------------------------------------- -->

<jsp:include page="/WEB-INF/views/layout/footer.jsp"></jsp:include>
</body>
</html>