<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<ul class="nav nav-sidebar">
	<li class="active"><a href="#">Main <span class="sr-only">(current)</span></a></li>
	<li class="active"><a
		href="${pageContext.request.contextPath }/memberList">사용자</a></li>
	<li class="active"><a
		href="${pageContext.request.contextPath }/jobsList">jobs</a></li>
		
	<!-- 
		표현식 : &lt;%= request.getContextPath() %&gt;
		=> EL 표현 : ${pageContext.request.contextPath }
	
	 -->
</ul>