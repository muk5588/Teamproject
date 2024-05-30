<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>권한설정</title>
    <link rel="icon" href="<%=request.getContextPath()%>/resources/img/20191208094528217881320965.png">
    <link href="/resources/css/common/common.css" rel="stylesheet" type="text/css">
    <link href="/resources/css/grade/gradeInsert.css" rel="stylesheet" type="text/css">
</head>
<body>
<jsp:include page="/WEB-INF/views/layout/header.jsp"></jsp:include>
<!-- <h3>등급 추가</h3>
<div class="warpper">
    <div class="warp">
        <form action="/grade/insertGrade" method="post"> action 값을 안주면 submit을 자기 자신에게 한다
            <div id="nobox">
                <label for="gradeno">등급 번호</label>
                <input type="text" name="gradeno" id="gradeno"/><br>
            </div>
            <div id="namebox">
                <label for="gradename">등급 이름</label>
                <input type="text" name="gradename" id="gradename"><br>
            </div>
            <div id="commbox">
                <label for="comm">추가 설명</label>
                <input type="text" name="comm" id="comm"><br>
            </div>
            <br><br>
            <input type="submit" id="submit" name="submit" value="추가"/>
            <input type="reset" id="reset" name="reset" value="초기화"/>
        </form>
    </div>
</div> -->


<h3>권한 설정</h3>
<div class="warpper">
    <div class="warp">
    
    <ul class="grade">
    <li>
      <a href="/grade/gradeList" id="gradeList">권한조회</a>
    </li>
    <li>
      <a href="/grade/gradeInsert" id="gradeInsert">권한추가</a>
    </li>
  </ul>
    	<div class="gradeSet">
        <form action="/grade/insertGrade" method="post"> <!-- action 값을 안주면 submit을 자기 자신에게 한다 -->
        
            <div class="no-grade grade__block no-grade__block" id="nobox">
                <!-- <label for="gradeno">등급 번호</label> -->
                <input type="text" name="gradeno" id="gradeno" placeholder="등급번호 ex) 1"/>
            </div>
            <div class="grade__block" id="namebox">
                <!-- <label for="gradename">등급 이름</label> -->
                <input type="text" name="gradename" id="gradename" placeholder="등급이름 ex) 신입 회원"/>
            </div>
            <div class="grade__block" id="commbox">
                <!-- <label for="comm">추가 설명</label> -->
                <input type="text" name="comm" id="comm" placeholder="추가설명 ex) 신입 회원">
            </div>
            <br><br>
            <input class="insert__btn" type="submit" id="submit" name="submit" value="추가"/>
            <input class="reset__btn" type="reset" id="reset" name="reset" value="초기화"/>
            
        </form>
    	</div>
    	
    </div>
</div>
<jsp:include page="/WEB-INF/views/layout/footer.jsp"></jsp:include>
</body>
</html>
