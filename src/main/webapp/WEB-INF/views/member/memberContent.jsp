<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<script>
	$(function(){
		$('#updateBtn').on('click',function(){
			var userid = "${memberVO.userid}";
			document.location="/member/memberUpdate?userid="+ userid;
		})

		$('#profileDownBtn').on('click',function(){
			document.location="${cp}/member/downloadView?userid=${memberVO.userid}"
		})
	})
	
	
</script>


	
				<form class="form-horizontal" role="form">
				tiles : memberContent
<!-- 					<div class="form-group"> -->
<!-- 						<label for="userNm" class="col-sm-2 control-label">사용자 아이디</label> -->
<!-- 						<div class="col-sm-10"> -->
<!-- 							<input type="text" class="form-control" id="userId" name="userId" -->
<!-- 								placeholder="사용자 아이디"> -->
<!-- 						</div> -->
<!-- 					</div> -->
					<div class="form-group">
						<label for="userNm" class="col-sm-2 control-label">사용자 사진</label>
						<div class="col-sm-10">
<%-- 							<img alt="" src="${cp }/profile/${memberVO.filename}"> --%>
							<img alt="" src="${cp }/member/profileImgView?userid=${memberVO.userid}"><br><br>
							<button id="profileDownBtn" type="button" class="btn btn-default">다운로드 : ${memberVO.realFilename }</button>
						</div>
					</div>
					<div class="form-group">
						<label for="user" class="col-sm-2 control-label">사용자 아이디</label>
						<div class="col-sm-10">
							<label id="userid" class="control-label">${memberVO.userid }</label>
						</div>
					</div>

					<div class="form-group">
						<label for="userNm" class="col-sm-2 control-label">사용자 이름</label>
						<div class="col-sm-10">
							<label class="control-label">${memberVO.usernm }</label>
						</div>
					</div>
					<div class="form-group">
						<label for="userNm" class="col-sm-2 control-label">별명</label>
						<div class="col-sm-10">
							<label class="control-label">${memberVO.alias }</label>
						</div>
					</div>
					<div class="form-group">
						<label for="pass" class="col-sm-2 control-label">Password</label>
						<div class="col-sm-10">
							<label class="control-label">********</label>
						</div>
					</div>
					<div class="form-group">
						<label for="addr1" class="col-sm-2 control-label">주소</label>
						<div class="col-sm-10">
							<label class="control-label">${memberVO.addr1 }</label>
						</div>
					</div>
					<div class="form-group">
						<label for="addr2" class="col-sm-2 control-label">상세주소</label>
						<div class="col-sm-10">
							<label class="control-label">${memberVO.addr2 }</label>
						</div>
					</div>
					<div class="form-group">
						<label for="zipcode" class="col-sm-2 control-label">우편번호</label>
						<div class="col-sm-10">
							<label class="control-label">${memberVO.zipcode }</label>
						</div>
					</div>
					<div class="form-group">
						<label for="reg_dt" class="col-sm-2 control-label">등록일자</label>
						<div class="col-sm-10">
							<label class="control-label">
								<fmt:formatDate value="${memberVO.reg_dt }" pattern="yyyy-MM-dd"/>
							</label>
						</div>
					</div>
					

					<div class="form-group">
						<div class="col-sm-offset-2 col-sm-10">
							<button id="updateBtn" type="button" class="btn btn-default">사용자 수정</button>
						</div>
					</div>
				</form>
</body>
</html>
