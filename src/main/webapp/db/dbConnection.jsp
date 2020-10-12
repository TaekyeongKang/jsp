<%@page import="java.sql.Statement"%>
<%@page import="java.sql.SQLException"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Connection"%>
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
	
	

	/*
	 	JDBC를 이용한 데이터베이스 처리 순서
	 	
	 	순서 :  JDBC 드라이버 로딩 → 해당 DB에 접속 → 질의 (SQL명령을 수행) → 질의 결과를 받아서 처리한다. → 종료 (자원반납)
	 	
	 */
		
			
			// DB 작업에 필요한 객체변수 선언
			Connection conn = null;
			Statement stmt = null;
			ResultSet rs = null; 	// 쿼리문이 select인 경우에 사용함.
			
			try {
				// 1. 드라이버 로딩
				Class.forName("oracle.jdbc.driver.OracleDriver");
				
				// 2. DB에 접속 (Connection 객체 생성)
				String url = "jdbc:oracle:thin:@localhost:1521/xe";
				String userId = "KTK";
				String password = "java";
				
				
				// 커넥션을 20번 했다가 끊는데 걸리는 시간 체크
				long startTime = System.currentTimeMillis();
				
				for(int i =0; i<20; i++){
					conn = DriverManager.getConnection(url, userId, password);
					conn.close();
				}
				
				long endTime = System.currentTimeMillis();
				
				out.print("<h3>endTime - startTime : " + (endTime - startTime) + "ms </h3>");
				
			
				
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				
				// 6. 종료 (사용했던 자원을 모두 반납한다.)
				
				if(rs != null) try {rs.close();} catch (SQLException e) {}
				if(stmt != null) try {stmt.close();} catch (SQLException e) {}
				if(conn != null) try {conn.close();} catch (SQLException e) {}
				
			}
			
			
			
			
			
		
	

	%>
</body>
</html>