<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
		Date now = new Date();
		request.setAttribute("now", now);
	%>
	
	<!-- 
		us : mm-dd-yyyy
		ko : yyyy. mm. dd  => 2020-10-19, 2020/10/19
	 -->
	<%-- 	<fmt:setLocale value="en_US"/> --%>
	now : ${now }<br>
	now - formatDate : <fmt:formatDate value="${now }"/><br>
	
	now - formatDate-pattern : <fmt:formatDate value="${now }" pattern="YYYY-MM-dd"/><br>
	<!-- date pattern 키워드는 대문자/소문자에 따라 의미가 많이 달라지므로 정확한 키워드를 사용해야 함
		ex) MM : month / mm : minute 
		    DD : 1/1로부터 흐른 날짜 (like d-day) / dd : 해당월의 날짜 
	 -->
	 
	 <!-- 문자 ==> 날짜 
	 	 "2020-10-19 10:15" 문자열을 날짜 타입으로 변경
	 -->
	 <%
	 	request.setAttribute("nowStr", "2020-10-19 10:15");
	 %>
	 parseDate : <fmt:parseDate value="${nowStr }" pattern="YYYY-MM-dd HH:mm"/>
	 
</body>
</html>