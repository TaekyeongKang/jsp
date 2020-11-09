<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!-- form tag 라이브러리 : 에러메세지 관리하는데 사용 -->

<script src="https://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script>
$(document).ready(function(){
	$('#zipcodeBtn').on('click', function(){
	    new daum.Postcode({
	        // data : 검색어를 입력했을 때 나오는 목록중에 클릭한 한개
	        oncomplete: function(data) {
	            // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분입니다.
	            // 예제를 참고하여 다양한 활용법을 확인해 보세요.
				console.log(data);
				$('#addr1').val(data.roadAddress);
				$('#zipcode').val(data.zonecode);
	            
	        }
	    }).open();
	})

	$('#regBtn').on('click',function(){
		// client side - validation(유효성 검증)
		// server side - validation(유효성 검증) : parameter 받은 다음에 java코드로 검증
		// 검증을 두단계 거치는게 안전함. 악의적으로  script 가능

		// vaildation 일단 생략

		$('#frm').submit();
		
	})

// 	initData();
})

function initData(){
	$('#userid').val("ktk");
	$('#usernm').val("강태경");
	$('#alias').val("pc-13");
	$('#pass').val("pass1234");
	$('#addr1').val("대전 중구 중앙로 76");
	$('#addr2').val("영민빌딩 4층 404호");
	$('#zipcode').val("34940");
}
	
</script>

				<form id="frm" class="form-horizontal" role="form"	action="${cp }/member/registProcess" method="POST" 
					enctype="multipart/form-data">
					tiles : memberRegistContent
					<!-- label 태그의 "for" 속성 : 시각장애인 등을 위한 스크립리더 기기에서 읽을때 무엇을 위한것인지 설명하는데 사용됨 -->
					<div class="form-group">
						<label for="userNm" class="col-sm-2 control-label">사용자 사진</label>
						<div class="col-sm-10">
							<input type="file" name="profile"/>
						</div>
					</div>
					<div class="form-group">
						<label for="userid" class="col-sm-2 control-label">사용자 아이디</label>
						<div class="col-sm-10">
							<input type="text" class="form-control" id="userid" name="userid" 
 								placeholder="사용자 아이디" value="${param.userid }">
						</div>
					</div>

					<div class="form-group">
						<label for="userNm" class="col-sm-2 control-label">사용자 이름</label>
						<div class="col-sm-10">
							<input type="text" class="form-control" id="usernm" name="usernm" 
 								placeholder="사용자 이름" value="${param.usernm }">
 								<span style="color:red"><form:errors path="memberVO.usernm"></form:errors></span>
 								<!-- form 태그에 의해서 에러메시지가 있으면 보여주고 없으면 안보여줌.
 								     rejectValue로 설정해둔 값이 넘어오면 에러메세지 있음 -->
						</div>
					</div>
					<div class="form-group">
						<label for="alias" class="col-sm-2 control-label">별명</label>
						<div class="col-sm-10">
							<input type="text" class="form-control" id="alias" name="alias" 
 								placeholder="사용자 별명" value="${param.alias }">
						</div>
					</div>
					<div class="form-group">
						<label for="pass" class="col-sm-2 control-label">Password</label>
						<div class="col-sm-10">
							<input type="password" class="form-control" id="pass" name="pass" 
 								placeholder="사용자 비밀번호" value="${param.pass }">
						</div>
					</div>
					<div class="form-group">
						<label for="addr1" class="col-sm-2 control-label">주소</label>
						<div class="col-sm-10">
							<!-- 주소의 경우 우편번호를 검색하여 자동으로 입력되므로 사용자가 입력할 수 없도록  "readonly" 속성 넣어준다. -->
							<input type="text" class="form-control" id="addr1" name="addr1" 
 								placeholder="주소" readonly value="${param.addr1 }">
 							<button type="button" class="btn btn-default" id="zipcodeBtn">우편번호 찾기</button>
						</div>
					</div>
					<div class="form-group">
						<label for="addr2" class="col-sm-2 control-label">상세주소</label>
						<div class="col-sm-10">
							<input type="text" class="form-control" id="addr2" name="addr2" 
 								placeholder="상세주소" value="${param.addr2 }">
						</div>
					</div>
					<div class="form-group">
						<label for="zipcode" class="col-sm-2 control-label">우편번호</label>
						<div class="col-sm-10">
							<input type="text" class="form-control" id="zipcode" name="zipcode" 
 								placeholder="사용자 우편번호" readonly value="${param.zipcode }">
						</div>
					</div>
					

					<div class="form-group">
						<div class="col-sm-offset-2 col-sm-10">
						<!-- 사용자가 입력한 값을 검증하는 단계를 거치기 위해 type을  submit 아닌  button 으로 -->
							<button id="regBtn" type="button" class="btn btn-default">사용자 등록</button>
						</div>
					</div>
				</form>
</body>
</html>
