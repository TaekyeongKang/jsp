<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="${pageContext.request.contextPath }/mulCalculation" method="POST">
		<input type="text" name="param1" value="1">
		*
		<input type="text" name="param2" value="5">
		<button type="submit">multiply</button>
	</form>
</body>
</html>