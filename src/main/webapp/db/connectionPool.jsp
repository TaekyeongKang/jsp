<%@page import="java.sql.Connection"%>
<%@page import="java.rmi.activation.ActivationGroupDesc.CommandEnvironment"%>
<%@page import="org.apache.commons.dbcp2.BasicDataSource"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
		BasicDataSource bd = (BasicDataSource) application.getAttribute("bd");
	
		long startTime = System.currentTimeMillis();
		
		// connection 20번 얻었다가 끊기작업
		for(int i = 0; i <20; i++){
			Connection conn = bd.getConnection();
			conn.close();
		}
		
		long endTime = System.currentTimeMillis();
		
		out.print("<h3>endTime - startTime : " + (endTime - startTime) + "ms </h3>");
	%>
</body>
</html>