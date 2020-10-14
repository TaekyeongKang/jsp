<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form aciont="${pageContext.request.contextPath }/el" method="post">
		<%	// 이런 코드는 사실 jsp에 넣지 않는것이 바람직함 -> servlet에서 코딩하고 속성값으로 넣어주는 방법도 가능
			String scope = request.getParameter("scope");
			String requestParam ="";
			String sessionParam ="";
			String applicationParam ="";
			
			if(scope == null || scope.equals("requestValue")) 
				requestParam="checked";
			else if(scope == null || scope.equals("sessionValue")) 
				sessionParam="checked";  
			else if(scope == null || scope.equals("applicationValue")) 
				applicationParam="checked";
			
		%>
		request	(request)							: <input type="radio" name="scope" value="requestValue" <%=requestParam %>> <br>
		session	(request, session)					: <input type="radio" name="scope" value="sessionValue" <%=sessionParam %>><br>
		application (request, session, application)	: <input type="radio" name="scope" value="applicationValue" <%=applicationParam %>><br>
		<button type="submit">전송</button>
	</form>
	
	attr : ${attr } (page -> request -> session -> application)<br>
	requestScope	 : ${requestScope.attr }<br>
	sessionScope	 : ${sessionScope.attr }<br>
	applicationScope : ${applicationScope.attr }<br><br>
	
	
	scope parameter  : <%=request.getParameter("scope") %><br>
	scope parameter  : ${param.scope }<br>
	
	
	<br><br>
	cookie  : <%=request.getCookies() %><br>	<!-- 쿠키객체의 배열의 첫번째 값 -->
	cookie  : ${cookie.userid.value }<br> <!-- 쿠키캑체중 userid 의 값 -->
	
	<br><br>
	map.key<br>
	rangers.brown : ${rangers.brown }<br>
	rangers.sally : ${rangers.sally }<br>
	
	<br><br>
	list[인덱스] ==> MemberVO, list[인덱스].필드<br>
	rangersList[0].userid : ${rangersList[0].userid }<br> <!-- MemberVO 객체의 userid 속성 값 출력 -->
	rangersList[1] : ${rangersList[1] }<br> <!-- MemberVO 를 toString() 이용하여 출력 -->
</body>
</html>