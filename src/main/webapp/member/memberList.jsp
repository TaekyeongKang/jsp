<%@page import="kr.or.ddit.member.model.MemberVO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
<meta name="description" content="">
<meta name="author" content="">
<link rel="icon" href="../../favicon.ico">

<title>Jsp</title>

<%@ include file="/layout/commonLib.jsp" %>


<script>
	$(function(){
		pageSize = $('#pageSize').val();
	})
</script>
</head>

<body>

<%@ include file="/layout/header.jsp" %>	

<div class="container-fluid">
		<div class="row">
			
<div class="col-sm-3 col-md-2 sidebar">
	<%@ include file="/layout/left.jsp" %>
</div><div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
				

<div class="row">
	<div class="col-sm-8 blog-main">
		<h2 class="sub-header">사용자</h2>
		<div class="table-responsive">
			<table class="table table-striped">
					<select id="pageSize" name="pageSize">
						<option value="5" selected>5</option>
						<option value="10">10</option>
					</select>
				<tr>
					<th>사용자 아이디</th>
					<th>사용자 이름</th>
					<th>사용자 별명</th>
					<th>등록일시</th>
				</tr>
				<!-- 스크립틀릿으로  memberList를 꺼내오지 않아도 사용할 수 있다.!!!!!!!!
<%-- 					<%  List<memberVO> memberList = (List<memberVO>) request.getAttribute("memberList"); 
							int pages = (Integer) request.getAttribute("pages");
							int page = (Integer) request.getAttribute("page");
						%> 																							--%>
				  !!!!!!!!!!!!!!!!!!!!!!!!   생략가능    !!!!!!!!!!!!!!!!!!!!!!!!
				 
				 -->
					<c:forEach items="${memberList }" var="member">
						<tr>
							<td>${member.userid }</td>
							<td>${member.usernm }</td>
							<td>${member.alias }</td>
							<td>${member.reg_dt }</td>
						</tr>
					</c:forEach>
			</table>
		</div>
		<a class="btn btn-default pull-right">사용자 등록</a>
		<div class="text-center">
			<ul class="pagination">
				<c:forEach var="i" begin="1" end="${pages }">
					<c:choose>
						<c:when test="${page == i }">
							<li class="active"><span>${i }</span></li>
						</c:when>
						<c:otherwise>
							<li><a href="${pageContext.request.contextPath }/memberList?page=${i }&pageSize=7">${i }</a></li>
						</c:otherwise>
					</c:choose>
				</c:forEach>
			</ul>
		</div>
	</div>
</div>
	</div>
		</div>
	</div>
</body>
</html>
