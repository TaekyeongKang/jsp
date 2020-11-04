<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script>

</script>
</head>
<body>

	<!-- client form method : Post 
					 encType : multipart/form-data
		 server - servlet : @MultipartConfig
		        - spring Framework multipartResolver
	-->
	
	<form action="${cp }/fileupload/upload" method="POST" encType="multipart/form-data">
		파일 업로드 : <input type="file" name="file"><br>
		userid   : <input type="text" name="userid" value="브라운"><br>
		<input type="submit" value="전송"> 
	</form>
</body>
</html>