<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
		request.setAttribute("name", "brown");
	%>
	
	<c:if test="${name == 'sally'}">  <!-- 문자열끼리 비교할 시 "equals" 대신 "==" 비교연산자 사용해도 알아서 해줌 -->
		sally
	</c:if>
	
	<c:if test="${name  == 'brown'}">
		brown
	</c:if>
	
	jstl_core_if.jsp

</body>
</html>