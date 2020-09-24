<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%	// 스크립틀릿 : 자바 로직을 작성하는 공간
		Date date = new Date();
	%>
	
	
	<!-- 
		$lt;%= %> : 표현식(expression) - 자바값을 화면에 출력해준다.
					writer.print() 기능  
	-->
	현재시간 : <%= date %> 
</body>
</html>