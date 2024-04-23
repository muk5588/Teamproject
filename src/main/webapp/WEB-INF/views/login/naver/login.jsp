<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.net.URLEncoder" %>
<%@ page import="java.security.SecureRandom" %>
<%@ page import="java.math.BigInteger" %>
<script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
<script type="text/javascript">
$(function(){
	
	$("#link").click(function(){
		console.log("클릭")
		
		
	})
	
	
})
</script>
<html>
  <a href="${apiURL }" id="link"><img height="50" src="http://static.nid.naver.com/oauth/small_g_in.PNG"/></a>
</html>