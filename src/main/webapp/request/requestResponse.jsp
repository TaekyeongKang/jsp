<%@page import="java.util.Enumeration"%>
<%@page import="org.apache.commons.collections.bag.SynchronizedSortedBag"%>
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
		request.setCharacterEncoding("utf-8");
	%>
	<h1><%=request.getMethod() %></h1>
	userId 파라미터는 brown, sally  두개를 보내지만 getParameter를 호출하면 첫 번째 파라미터 값을 반환<br>
	request.getParameter("userId") : <%=request.getParameter("userId")%>
	<br>
	String[]을 반환<br>
	request.getParameterValues("userId") : 
	<%
		String[] userIds = request.getParameterValues("userId");
		for(String userId : userIds){
	%>
			<%=userId %>
	<%
		}
	%>
	<br><br>
	
	parameterMap : Map&lt;String, String[]&gt; <br>
	request.getParameterMap() : <%=request.getParameterMap() %>
	
	<h3>요청에 존재하는 파라미터 이름 출력하기</h3>
	
	request.getParameterNames() : 
	<%
		Enumeration<String> parameters = request.getParameterNames();
		while(parameters.hasMoreElements()){
		 	String parameter = parameters.nextElement();
	%>
			<%=parameter %>
	<%	 	
		}
	%>
	
	
</body>
</html>