<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
<meta name="description" content="">
<meta name="author" content="">
<link rel="icon" href="../../favicon.ico">

<title>Jsp</title>

<%@ include file="/layout/commonLib.jsp" %>

<script src="https://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script>
$(function(){
	
	$('#updateBtn').on('click',function(){
		// client side - validation(유효성 검증)
		// server side - validation(유효성 검증) : parameter 받은 다음에 java코드로 검증
		// 검증을 두단계 거치는게 안전함. 악의적으로  script 가능

		// vaildation 일단 생략
		
		$('#frm').submit();
		
	})

})

</script>
</head>

<body>

	<%@include file="/layout/header.jsp" %>
	
	<div class="container-fluid">
		<div class="row">

			<div class="col-sm-3 col-md-2 sidebar">
				<%@ include file="/layout/left.jsp" %>
			</div>
			<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
	

				<form id="frm" class="form-horizontal" role="form"	action="${cp }/memberUpdate" method="POST" 
					enctype="multipart/form-data">
					
					<!-- label 태그의 "for" 속성 : 시각장애인 등을 위한 스크립리더 기기에서 읽을때 무엇을 위한것인지 설명하는데 사용됨 -->
					<div class="form-group">
						<label for="userNm" class="col-sm-2 control-label">사용자 사진</label>
						<div class="col-sm-10">
							<img alt="" src="${cp }/profileImg?userid=${memberVO.userid}" >
							<input type="file" name="realFilename"/>
						</div>
					</div>
					<div class="form-group">
						<label for="userid" class="col-sm-2 control-label">사용자 아이디</label>
						<div class="col-sm-10">
							<input type="text" class="form-control" id="userid" name="userid" 
 								placeholder="사용자 아이디" value="${memberVO.userid}" readonly>
						</div>
					</div>

					<div class="form-group">
						<label for="userNm" class="col-sm-2 control-label">사용자 이름</label>
						<div class="col-sm-10">
							<input type="text" class="form-control" id="usernm" name="usernm" 
 								placeholder="사용자 이름" value="${memberVO.usernm }">
						</div>
					</div>
					<div class="form-group">
						<label for="alias" class="col-sm-2 control-label">별명</label>
						<div class="col-sm-10">
							<input type="text" class="form-control" id="alias" name="alias" 
 								placeholder="사용자 별명" value="${memberVO.alias }">
						</div>
					</div>
					<div class="form-group">
						<label for="pass" class="col-sm-2 control-label">Password</label>
						<div class="col-sm-10">
							<input type="password" class="form-control" id="pass" name="pass" 
 								placeholder="사용자 비밀번호" value="${memberVO.pass }">
						</div>
					</div>
					<div class="form-group">
						<label for="addr1" class="col-sm-2 control-label">주소</label>
						<div class="col-sm-10">
							<!-- 주소의 경우 우편번호를 검색하여 자동으로 입력되므로 사용자가 입력할 수 없도록  "readonly" 속성 넣어준다. -->
							<input type="text" class="form-control" id="addr1" name="addr1" 
 								placeholder="주소" readonly value="${memberVO.addr1 }">
 							<button type="button" class="btn btn-default" id="zipcodeBtn">우편번호 찾기</button>
						</div>
					</div>
					<div class="form-group">
						<label for="addr2" class="col-sm-2 control-label">상세주소</label>
						<div class="col-sm-10">
							<input type="text" class="form-control" id="addr2" name="addr2" 
 								placeholder="상세주소" value="${memberVO.addr2 }">
						</div>
					</div>
					<div class="form-group">
						<label for="zipcode" class="col-sm-2 control-label">우편번호</label>
						<div class="col-sm-10">
							<input type="text" class="form-control" id="zipcode" name="zipcode" 
 								placeholder="사용자 우편번호" readonly value="${memberVO.zipcode }">
						</div>
					</div>
					

					<div class="form-group">
						<div class="col-sm-offset-2 col-sm-10">
						<!-- 사용자가 입력한 값을 검증하는 단계를 거치기 위해 type을  submit 아닌  button 으로 -->
							<button id="updateBtn" type="button" class="btn btn-default">사용자 수정</button>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>
</body>
</html>
