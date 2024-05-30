<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%> <!-- 타일을 사용하기 위한 라이브러리 -->
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>${category eq 'cu' ? '고객 관리' : (category eq 'no' ? '공지사항' : (category eq 'bo' ? '방명록' : (category eq 'da' ? '공공 데이터' : (category eq 'join' ? '회원가입' : '') ) ) ) } : IoT</title>
    <link rel="icon" href="<%=request.getContextPath()%>/resources/img/20191208094528217881320965.png">
    <!-- 브라우저 탭의 작은 아이콘 설정 -->
    <link rel="icon" type="image/x-icon" href="img/icon.ico" />
</head>
<body>
<tiles:insertAttribute name="header" />
<tiles:insertAttribute name="content" />
<tiles:insertAttribute name="footer" />
</body>
</html>
