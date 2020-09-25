<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script>
	

	$(function(){
		$('#getRadio').on('change',function(){
			method = $(this).val();
			$('form').attr('method',method)
		})
		$('#postRadio').on('change',function(){
			method = $(this).val();
			$('form').attr('method',method)
		})
	})
</script>
</head>
<body>
	<%-- 파라미터 : client 서버로 요청을 보낼 때 추가적으로 보낸 값
				ex) 로그인 요청시 : 사용자 id, 비밀번호
				
		파라미터는 내부적으로 Map<String, String[]> 객체로 관리를 한다.
						 파라미터 이름, 파라미터 값들
						 ==> 동일한 이름의 파라미터를 여러개 보낼 수 있다. 
		
		
		request 객체에 있는 파라미터 관련 메소드 4가지
			1. String getParameter(String param) : param에 해당하는 파라미터 값을 조회한다.
												   파라미터Map에서 첫 번째(인덱스) 값을 리턴 (여러개 있을 경우)
			2. String[] getParameterValues(String param) : param에 해당하는 모든 파라미터 값을 반환한다.
			3. Map<String, String[]> getParameterMap() : request 객체에 생성된 파라미터Map을 반환
			4. Enumeration<String> getParameterNames() : request 객체에 담긴 모든 파라미터 이름을 반환
	 --%>
	 
	 <%-- action : 요청을 보낼 경로
	 	  method : 요청 방식 (form 에서는 GET, POST 두가지만 가능하며 DEFAULT는 GET)
	  --%>
	 <%-- 파라미터 값 한글로 보내면 인코딩 깨짐
	 		get :  server.xml에서 <connector URIEncoding="utf-8"> 태그에 URIEncoding="utf-8" 추가해줌
	 		post : request.setCharacterEncoding("utf-8");
	 			   request.getParameter() 메소드를 호출하기 전에 설정을 해줘야 한다. 
	  --%>
	
	
	 
	 <form action="<%=request.getContextPath() %>/request/getRequestResponse.jsp" >
		 <label>user id : </label>
		 <input name="userId" type="text" value="브라운">
		 <br>
		 <label>user id : </label>
		 <input name="userId" type="text" value="샐리">
		 <br>
		 <label>password : </label>
		 <input name="pass" type="password" value="pass1234">
		 <br>
		 <input id="getRadio" type="radio" value="GET">GET
		 <input id="postRadio" type="radio" value="POST">POST
		 <br>
		 <input type="submit" value="전송">
	 </form>
	 
	 GET POST 두가지를 선택할 수 있는 라디오 버튼을 만들어서
	 FORM 전송시 사용자가 GET, POST 방ㅅ힉을 지정할 수 있도록 


	 
</body>
</html>