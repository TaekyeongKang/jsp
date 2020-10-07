<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">
<style>
	#cookiePrint{
		border : 1px solid black;
	}
</style>
 <script>
	function getCookieValue(cookieName){
		var search = cookieName + "=";
		var cookie = document.cookie;
		var cookies = cookie.split("; ");
		console.log("cookies: "+cookies);
		var cookieSplit =  new Array();
		var cookieNames =  new Array();
		var cookieVlaues =  new Array();
		for(i=0; i<cookies.length; i++){
			console.log("cookies "+i+" : "+cookies[i]);
			cookieSplit.push(cookies[i].split("="));
			console.log("cookieSplit "+i+" : "+cookieSplit);
			console.log("cookieSplit ["+i+"][0]"+cookieSplit[i][0]);
			console.log("cookieSplit ["+i+"][1]"+cookieSplit[i][1]);
			cookieNames.push(cookieSplit[i][0]);
			cookieVlaues.push(cookieSplit[i][1]);
		}	
		console.log("cookieNames : "+cookieNames);
		console.log("cookieVlaues : "+cookieVlaues);

		
		for(i=0; i<cookieNames.length; i++){
			console.log("(파라미터) : "+cookieName);
			if(cookieName==cookieNames[i]){
				return cookieVlaues[i];
			} 
			
		}
		return "";
	}

	window.onload = function(){
		usernm = getCookieValue("USERID");
		rememberme = getCookieValue("REMEMBERME");
		notexist = getCookieValue("NOTEXISTS_COOKIE");
		console.log("usernm :"+usernm);
		console.log("rememberme :"+rememberme);
		console.log("notexist :"+notexist);
		res = "username : "+usernm+"<br>";
		res += "rememberme : "+rememberme+"<br>";
		res += "notexist : "+notexist+"<br>";
		document.getElementById('cookiePrint').innerHTML=res;
	}
	
 </script>
 
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" href="../../favicon.ico">

    <title>Signin Template for Bootstrap</title>

    <!-- Bootstrap core CSS -->
    <link href="<%=request.getContextPath() %>/css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="<%=request.getContextPath() %>/css/signin.css" rel="stylesheet">
  </head>

  <body>

    <div class="container" >

      <form class="form-signin">
        <h2 class="form-signin-heading">Please sign in</h2>
        <label for="inputEmail" class="sr-only">Email address</label>
        <input type="email" id="inputEmail" class="form-control" placeholder="Email address" required autofocus>
        <label for="inputPassword" class="sr-only">Password</label>
        <input type="password" id="inputPassword" class="form-control" placeholder="Password" required>
        <div class="checkbox">
          <label>
            <input type="checkbox" value="remember-me"> Remember me
          </label>
        </div>
        <button class="btn btn-lg btn-primary btn-block" type="submit">Sign in</button>
      </form>

    </div> <!-- /container -->
    
    <div id="cookiePrint" onclick="printCookieValue()">
    </div>


  </body>
</html>