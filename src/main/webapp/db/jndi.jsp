<%@page import="java.sql.Connection"%>
<%@page import="javax.sql.DataSource"%>
<%@page import="javax.naming.InitialContext"%>
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
		// jndi를 가져오기 위해 InitialContext 타입 객체 생성
		InitialContext context = new InitialContext();
		DataSource ds = (DataSource)context.lookup("java:comp/env/jdbc/oracleDB");
		
		long startTime = System.currentTimeMillis();
		
		// connection 20번 얻었다가 끊기작업
		for(int i = 0; i <20; i++){
			Connection conn = ds.getConnection();
			conn.close();
		}
		
		long endTime = System.currentTimeMillis();
		
		out.print("<h3>endTime - startTime : " + (endTime - startTime) + "ms </h3>");
		
	%>
</body>
</html>