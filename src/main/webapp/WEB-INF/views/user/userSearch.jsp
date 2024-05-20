<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: c
  Date: 24. 4. 16.
  Time: 오전 11:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script src="/resources/js/user/search.js" charset="UTF-8"></script>
	<link href="/resources/css/user/userSearch.css" rel="stylesheet" type="text/css">

</head>
<body>
<jsp:include page="/WEB-INF/views/layout/header.jsp"></jsp:include>
<h3>회원정보 찾기</h3>
<%-- <div class="warpper">
    <div class="warp">
        <c:if test="${value == 'id'}">
            <div id="searchid">
                <form action="/user/searchId" method="post" id="useridform"> <!-- action 값을 안주면 submit을 자기 자신에게 한다 -->
                    <div id="namebox">
                        <label for="name">이름</label>
                        <input type="text" name="name" id="name"/><br>
                    </div>
                    <div id="emailbox">
                        <label for="email">이메일</label>
                        <input type="email" name="email" id="email"/>
                        <button type="button" id="checkmail" disabled>인증번호 발송</button>
                        <br>
                        <div id="alertemail" name="alertemail"></div>
                        <input type="text" id="checkcode" placeholder="인증번호를 입력해주세요" disabled="disabled">
                        <br>
                        <button type="button" id="search" name="search">아이디찾기</button>
                    </div>
                </form>
            </div>
        </c:if>
        <c:if test="${value == 'pw'}">
            <div id="searchpw">
                <form action="/user/searchPw" method="post" id="userpwform"> <!-- action 값을 안주면 submit을 자기 자신에게 한다 -->
                    <div id="namebox2">
                        <label for="name2">이름</label>
                        <input type="text" name="name2" id="name2"/><br>
                    </div>
                    <div id="useridbox2">
                        <label for="userid2">아이디</label>
                        <input type="text" name="userid2" id="userid2">
                        <div id="alertid2" name="alertid2"></div>
                    </div>
                    <div id=emailbox2">
                        <label for="email2">이메일</label>
                        <input type="email" name="email2" id="email2"/>
                        <button type="button" id="checkmail2" disabled>인증번호 발송</button>
                        <br>
                        <div id="alertemail2" name="alertemail2"></div>
                        <input type="text" id="checkcode2" placeholder="인증번호를 입력해주세요" disabled="disabled">
                        <br>
                        <button type="button" id="search2" name="search2">비밀번호찾기</button>
                    </div>
                </form>
            </div>
        </c:if>
    </div>
</div> --%>

<div class="warpper">
  <div class="warp">
  <ul class="idpwFind">
    <li>
      <a href="/user/searchUser?value=id" id="idFind">아이디찾기</a>
    </li>
    <li>
      <a href="/user/searchUser?value=pw" id="pwFind">비밀번호찾기</a>
    </li>
  </ul>
  
  <c:if test="${value == 'id'}">
  <div id="searchid">
    <form action="/user/searchId" method="post" id="useridform"> <!-- action 값을 안주면 submit을 자기 자신에게 한다 -->
    
      <div class="id-find find__block id-find__block" id="namebox">
        <input type="text" name="name" id="name" class="input" placeholder="이름"/>
      </div>
      
      <div class="find__block" id="emailbox">
        <input type="email" name="email" id="email" placeholder="이메일"/>
        <button class="send__btn" type="button" id="checkmail" disabled>인증번호 발송</button>
        <br>
        <div id="alertemail" name="alertemail"></div>
        <input type="text" id="checkcode" placeholder="인증번호를 입력해주세요" disabled="disabled">
        <br>
        <button class="search__btn" type="button" id="search" name="search">아이디찾기</button>
      </div>
    </form>
  </div>
  </c:if>
  
  
  <c:if test="${value == 'pw'}">
  <div id="searchpw">
    <form action="/user/searchPw" method="post" id="userpwform"> <!-- action 값을 안주면 submit을 자기 자신에게 한다 -->
    
      <div class="pw-find find__block pw-find__block"  id="namebox2">
        <input type="text" name="name2" id="name2" class="input" placeholder="이름" />
      </div>
      
      <div class="find__block" id="useridbox2">
        <input type="text" name="userid2" id="userid2" placeholder="아이디"/>
        <div id="alertid2" name="alertid2"></div>
      </div>
      
      <div class="find__block" id="emailbox2">
        <input type="email" name="email2" id="email2" placeholder="이메일"/>
        <button class="send__btn" type="button" id="checkmail2" disabled>인증번호 발송</button>
        <br>
        <div id="alertemail2" name="alertemail2"></div>
        <input type="text" id="checkcode2" placeholder="인증번호를 입력해주세요" disabled="disabled">
        <br>
        <button class="search__btn" type="button" id="search2" name="search2">비밀번호찾기</button>
      </div>
    </form>
  </div>
  </c:if>
  </div>
</div>


</body>
</html>
