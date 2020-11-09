<%@page import="kr.or.ddit.member.model.MemberVO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<script>
	$(document).ready(function() {
		$('#memberList tr').on('click', function() {
			// data-userid
			var userid = $(this).data("userid");

			document.location = "/member/member?userid=" + userid;
		})
	})
</script>



<div class="row">
	tiles : memberListContent.jsp
	<div class="col-sm-8 blog-main">
		<h2 class="sub-header">사용자</h2>
		<div class="table-responsive">
			<table class="table table-striped">
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
				<tbody id="memberList">
					<!-- <tbody> : 테이블의 내용만 crud 하고 싶을 때 분리할 수 있는 간단한 방법 -->
					<c:forEach items="${memberList }" var="member">
						<tr data-userid="${member.userid }">
							<td>${member.userid }</td>
							<td>${member.usernm }</td>
							<td>${member.alias }</td>
							<!-- format : yyyy-MM-dd -->
							<td><fmt:formatDate value="${member.reg_dt }" /></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
		<a href="${cp }/member/memberRegist"
			class="btn btn-default pull-right">사용자 등록</a>
		<div class="text-center">
			<ul class="pagination">
				<c:forEach var="i" begin="1" end="${pages }">
					<c:choose>
						<c:when test="${page == i }">
							<li class="active"><span>${i }</span></li>
						</c:when>
						<c:otherwise>
							<li><a
								href="${pageContext.request.contextPath }/member/memberList?page=${i }&pageSize=7">${i }</a></li>
						</c:otherwise>
					</c:choose>
				</c:forEach>
			</ul>
		</div>
	</div>
</div>
