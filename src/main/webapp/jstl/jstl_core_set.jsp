<%@page import="kr.or.ddit.member.model.MemberVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %> <!-- 경로에 jsp가 붙어있는 jstl/core 태그 사용 -->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h3>속성 설정 전</h3>
	userId : ${userId }<br>
	
	
	set 태그 통해 속성을 저장
	<c:set var="userId" value="brown"/>
	
	<h3>속성 설정 후</h3>
	userId : ${userId }<br>
	
	<br>
	객체에 필드 설정도 가능<br>
	<%
		// memberVO 가 서블릿을 통해 request 객체에 설정되었다고 가정
		// 생성하고 나서 값을 설정하는 부분은 없음.
		MemberVO memberVO = new MemberVO();
		request.setAttribute("memberVO", memberVO);
		//request.removeAttribute("memberVO") : 설정된 속성을 지우기 , but 잘 안씀..
	%>

	memberVO : ${memberVO } <br> <!-- memberVO : MemberVO [userId=null, pass=null] -->
	<br>
	set 태그를 통해 scope 객체에 저장된 VO 객체의 필드를 수정 (like setter)<br>
	<br>
	<c:set target="${memberVO }" property="userid" value="sally"></c:set> <!-- target에는 속성명을 적어주면 되는데 el형식으로 적어주어야 함 -->
	
	memberVO : ${memberVO } <br> <!-- memberVO : MemberVO [userId=sally, pass=null] -->
	
	<h3>remove 태그를 통해 scope 객체에서 속성 제거</h3>
	<c:remove var="memberVO"/>
	memberVO : ${memberVO } <br> <!-- memberVO :       -->
				
</body>
</html>