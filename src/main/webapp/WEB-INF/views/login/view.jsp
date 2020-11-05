<%@page import="org.apache.tomcat.util.http.Cookies"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <meta name="description" content="">
    <meta name="author" content="">
<!--     <link rel="icon" href="../../favicon.ico"> -->

    <title>Signin Template for Bootstrap</title>

    <!-- Bootstrap core CSS -->
    <link href="${pageContext.request.contextPath }/css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="${pageContext.request.contextPath }/css/signin.css" rel="stylesheet">
    
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath }/js/js.cookie-2.2.1.min.js"></script>
 <script>
 	$(function(){
 	 	// sing in 버튼이 클릭됐을 때 이벤트 핸들러
 	 	$('button').on('click',function(){
			if($("input[type=checkbox]").prop("checked")){
				Cookies.set("REMEMBERME","Y");
				Cookies.set("USERID",$('#inputEmail').val());
			}else{
				Cookies.remove("REMEMBERME");
				Cookies.remove("USERID");
			}
			// submit
			$('form').submit();
 	 	 })
		if(Cookies.get("REMEMBERME")=="Y"){
			$("input[type=checkbox]").prop("checked",true);
			$("#inputEmail").val(Cookies.get("USERID"));
		}
	


 	})

	function getCookieValue(cookieName){
		var cookie = document.cookie;
		var cookies = cookie.split("; ");
		var cookieSplit =  new Array();
		var cookieNames =  new Array();
		var cookieVlaues =  new Array();
		for(i=0; i<cookies.length; i++){
			cookieSplit.push(cookies[i].split("="));
			cookieNames.push(cookieSplit[i][0]);
			cookieVlaues.push(cookieSplit[i][1]);
		}	

		
		for(i=0; i<cookieNames.length; i++){
			if(cookieName==cookieNames[i]){
				return cookieVlaues[i];
			} 
			
		}
		return "";
	}

	

	function setCookie(cookieName, cookieValue, expires){
		var today = new Date();
		// 현재 날짜에서 미래로 +expires 만큼 한 날짜 구하기
		today.setDate(today.getDate()+expires);
		
		document.cookie = cookieName + "=" + cookieValue + "; path=/; expires=" + today.toGMTString() + "; "
		console.log(document.cookie);
	}

	function deleteCookie(cookieName){
		setCookie(cookieName,"",-1);
	}

	window.onload = function(){
		usernm = getCookieValue("USERID");
		rememberme = getCookieValue("REMEMBERME");
// 		if(rememberme == "Y"){  // js 버전
// 			document.getElementById('check').checked = true;
// 			document.getElementById('inputEmail').value = usernm;
// 		}
		notexist = getCookieValue("NOTEXISTS_COOKIE");

	}
	
 </script>
  </head>
 
  
  <body>
  	msg : ${msg} <br>
  	msg_s : ${msg_s} <br>
  	<c:remove var="msg_s" scope="session"/>
  	msg_ra : ${msg_ra} <br>

    <div class="container" >

      <form class="form-signin" action="${pageContext.request.contextPath }/login/process" method="POST">
        <h2 class="form-signin-heading">Please sign in</h2>
        <label for="inputEmail" class="sr-only">Email address</label>
        <input type="email" id="inputEmail" name="userid" class="form-control" placeholder="Email address" required autofocus value="brown">
        <label for="inputPassword" class="sr-only">Password</label>
        <input type="password" id="inputPassword"  name="pass" class="form-control" placeholder="Password" required value="brownPass">
        <div class="checkbox">
          <label>
            <input id="check" type="checkbox" value="remember-me" > Remember me
          </label>
        </div>
        <button class="btn btn-lg btn-primary btn-block" type="button">Sign in</button>
      </form>

    </div> <!-- /container -->
    
    <div id="cookiePrint" onclick="printCookieValue()">
    </div>


  </body>
</html>