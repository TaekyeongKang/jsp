<%@page import="kr.or.ddit.member.model.MemberVO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<script>
	// 해당 html이 로딩이 완료 되었을 때 실행되는 이벤트 핸들러 함수
	$(document).ready(function() {

		memberListAjaxHTML(1);

		$('#memberList').on('click', 'tr', function() {
			// data-userid
			var userid = $(this).data("userid");
			document.location = "/member/memberAjaxPage?userid=" + userid;
		})
		
	})
	
	// ajax call을 통해 1페이지에 해당하는 사용자 정보를 json으로 받는다.
	function memberListAjax(p){
		$.ajax({ url : "/member/listAjax",
				 data : { page		: p,
					 	  pageSize	: 5},
			   // data : "page=1&pageSize=5",
			   // data : JSON.stringify({page:1, pageSize:5}), 
			   //		Controller에서 @RequestBody   JSON  <----> JAVA OBJECT  (마샬링, 언마샬링)					 	  
				 method : "get",
				 success : function(data){
					 var i = 0;
					 var html = "";
					 // memberList tbody 영역에 들어갈 html 문자열 생성
				 	 for(i=0; i<data.memberList.length; i++){
					 	 var member = data.memberList[i];
					 	 html += "<tr data-userid='"+member.userid+"' >";
					 	 html +=	"<td>"+member.userid+"</td>";
					 	 html += 	"<td>"+member.usernm+"</td>";
					 	 html += 	"<td>"+member.alias+"</td>";
					 	 html += 	"<td>"+member.fmt_reg_dt+"</td>";
					 	 html += "</tr>";
					 }
					 
					 $('#memberList').html(html);
		
		// 			$('#memberList tr').on('click', function() {
		// 				// data-userid
		// 				var userid = $(this).data("userid");
			
		// 				document.location = "/member/member?userid=" + userid;
		// 			})
		
					// 페이지 네비게이션  html 문자열 동적으로 생성하기
					page = "";
					 for(i=1; i<=data.pages; i++){
						 if(data.pageVO.page == i){
							page +=  "<li class=\"active\"><span>"+i+"</span></li>"; 
						 } else{
							 			// < a href="javascript:memberListAjax(1);"/>
							page +=  "<li><a href=\"javascript:memberListAjax("+i+");\"/ >"+i+"</a></li>";
						 }
						 
					 }
					$('.pagination').html(page);
					
				 }
		})
	
	 		
	}


	// 응답을 jsp로 받는 방법
	function memberListAjaxHTML(p){
		$.ajax({ url : "/member/listAjaxHTML",
				 data : { page		: p,
					 	  pageSize	: 5},
			   // data : "page=1&pageSize=5",
			   // data : JSON.stringify({page:1, pageSize:5}), 
			   //		Controller에서 @RequestBody   JSON  <----> JAVA OBJECT  (마샬링, 언마샬링)					 	  
				 method : "get",
				 success : function(data){

					 var html = data.split("$$$SEPERATOR$$$");
						
					 $('#memberList').html(html[0]);
		
					 $('.pagination').html(html[1]);
					
				 }
		})
	
	 		
	}

</script>



<div class="row">
	tiles : listAjaxPage.jsp
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
					
				</tbody>
			</table>
		</div>
		<a href="${cp }/member/memberRegist" class="btn btn-default pull-right">사용자 등록</a>
		<div class="text-center">
			<ul class="pagination">
			</ul>
		</div>
	</div>
</div>
