<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="<%=request.getContextPath() %>/sumCalculation" method="POST">
		<input type="text" name="start" value="1">
		~
		<input type="text" name="end" value="5">
		<button type="submit">sum</button>
	</form>
</body>
</html>