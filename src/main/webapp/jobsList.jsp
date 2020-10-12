<%@page import="kr.or.ddit.jobs.model.JobsVO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<table border="1">
	<tr>
		<th>Job ID</th>
		<th>Job Title</th>
	</tr>
<% List<JobsVO> jobsList = (List<JobsVO>) request.getAttribute("jobsList"); 
	for(int i = 0; i<jobsList.size(); i++){
%>
	<tr>
		<td><%=jobsList.get(i).getJob_id() %></td>
		<td><%=jobsList.get(i).getJob_title() %></td>
	</tr>
<%
	}
%>
</table>
</body>
</html>