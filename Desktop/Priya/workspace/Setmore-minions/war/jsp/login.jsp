<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>

<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="bootstrap/css/bootstrap.min.css">
<script src="bootstrap/js/jquery.js"></script>
<script src="bootstrap/js/bootstrap.min.js"></script>
<script type="text/javascript" src="../js/jquery.validate.min.js"></script>

<style type="text/css">

.login-page {
	width: 360px;
	padding: 8% 0 0;
	margin: auto;
}

.form {
	position: relative;
	z-index: 1;
	background: #FFFFFF;
	max-width: 360px;
	margin: 0 auto 100px;
	padding: 45px;
	text-align: center;
	box-shadow: 0 0 20px 0 rgba(0, 0, 0, 0.2), 0 5px 5px 0
		rgba(0, 0, 0, 0.24);
	font-family: initial;
}

.form input {
	font-family: sans-serif;
	outline: 0;
	background: #f2f2f2;
	width: 100%;
	border: 0;
	margin: 0 0 15px;
	padding: 15px;
	box-sizing: border-box;
	font-size: 14px;
}

.form button {
	text-transform: uppercase;
	outline: 0;
	background: #24afa9;
	width: 100%;
	border: 0;
	padding: 15px;
	color: #FFFFFF;
	font-size: 14px;
	-webkit-transition: all 0.3 ease;
	transition: all 0.3 ease;
	cursor: pointer;
	font-weight: 800;
	FONT-FAMILY: SANS-SERIF;
}

.form button:hover, .form button:active, .form button:focus {
	background: #24afa9;
}

.form .message {
	margin: 15px 0 0;
	color: #b3b3b3;
	font-size: 16px;
	font-weight: 800;
}

.form .message a {
	color: #4CAF50;
	text-decoration: none;
}

.form .register-form {
	display: none;
}

.container {
	position: relative;
	z-index: 1;
	max-width: 300px;
	margin: 0 auto;
}

.container:before, .container:after {
	content: "";
	display: block;
	clear: both;
}

.container .info {
	margin: 50px auto;
	text-align: center;
}

.container .info h1 {
	margin: 0 0 15px;
	padding: 0;
	font-size: 36px;
	font-weight: 300;
	color: #1a1a1a;
}

.container .info span {
	color: #4d4d4d;
	font-size: 12px;
}

.container .info span a {
	color: #000000;
	text-decoration: none;
}

.container .info span .fa {
	color: #EF3B3A;
}

body {
	background: #24afa9; /* fallback for old browsers */
	font-family: "Roboto", sans-serif;
	-webkit-font-smoothing: antialiased;
	-moz-osx-font-smoothing: grayscale;
}

.form-control {
	font-family: " sans-serif;
	outline: 0;
	background: #f2f2f2;
	width: 300px;
	border: 0;
	margin: 0 0 15px;
	padding: 15px;
	box-sizing: border-box;
	font-size: 14px;
	height: 50px;
}

.submitButton {
	text-transform: uppercase;
	outline: 0;
	background: #24afa9;
	width: 300px;
	height: 50px;
	border: 0;
	padding: 15px;
	color: #FFFFFF;
	font-size: 14px;
	-webkit-transition: all 0.3 ease;
	transition: all 0.3 ease;
	cursor: pointer;
	FONT-FAMILY: sans-serif;
	FONT-WEIGHT: 800;
}

label.error {
	color: #FB3A3A;
	display: inline-block;
	/*     margin: 6px 0 5px 40px;
 */
	padding: 0;
	text-align: left;
	width: 220px;
	font-weight: 800;
	FONT-FAMILY: SANS-SERIF;
}

.msg {
	color: #FB3A3A;
	display: inline-block;
	/*     margin: 6px 0 5px 40px;
 */
	padding: 0;
	text-align: left;
	width: 220px;
	font-weight: 800;
	margin-bottom: -7px;
	margin-left: 31px;
	margin-top: 20px;
}
</style>

<script type="text/javascript">


function hide(){
	$("label.error").css("display","none");
	$("#error").css("display","none");
	
}


	(function($, W, D) {
		var JQUERY4U = {};

		JQUERY4U.UTIL = {
			setupFormValidation : function() {
				//form validation rules
				$("#login-form")
						.validate(
								{
									rules : {

										email : {
											required : true,
											email : true
										},
										password : {
											required : true,
											minlength : 6
										},
									},
									messages : {

										password : {
											required : "Provide a password",
											minlength : "Password must be 6 characters long"
										},
										email : "Enter a valid email address",
									},
									submitHandler : function(form) {
										form.submit();
									}
								});
			}
		}

		//when the dom has loaded setup form validation rules
		$(D).ready(function($) {
			JQUERY4U.UTIL.setupFormValidation();
		});

	})(jQuery, window, document);
</script>
<script type="text/javascript">
	/**
	 * Basic jQuery Validation Form Demo Code
	 * Copyright Sam Deering 2012
	 * Licence: http://www.jquery4u.com/license/
	 */
	(function($, W, D) {
		var JQUERY4U = {};

		JQUERY4U.UTIL = {
			setupFormValidation : function() {
				//form validation rules
				$("#register-form")
						.validate(
								{
									rules : {
										username : {
											required : true,
										},
										email : {
											required : true,
											email : true
										},
										password : {
											required : true,
											minlength : 6
										},
									},
									messages : {
										username : {
											required : "Provide a name",

										},
										password : {
											required : "Please provide a password",
											minlength : "Password must be 6 characters long"
										},
										email : "Enter a valid email address",
									},
									submitHandler : function(form) {
										form.submit();
									}
								});
			}
		}

		//when the dom has loaded setup form validation rules
		$(D).ready(function($) {
			JQUERY4U.UTIL.setupFormValidation();
		});

	})(jQuery, window, document);
	
	
	

	<%-- function load() {
		var params = {};

		params.username = document.forms["register-form"]["username"].value;
		params.email = document.forms["register-form"]["email"].value;
		params.password = document.forms["register-form"]["password"].value;
		var username = '<%=session.getAttribute("AdminName")%>';
 		var email = '<%=session.getAttribute("AdminEmail")%>';
		var AdminId = '<%=session.getAttribute("AdminId")%>';
	
		$.ajax({
			url : "/signup",
			type : "POST",
			contentType : "application/json;",
			async: false,
			data : JSON.stringify(params),
			dataType : "json",
			   success: function (data) {
		       		if(data == true)
		       		{
		       			window.location = "/clients"
		       		}
		       		else
		       		{
		       			window.location = "/signup"
		       		}
			   }
		});
	} --%>
	
</script>
</head>

<body>



	<div class="login-page">
		<div class="form">
			<form action="/signin" method="post" id="login-form"
				novalidate="novalidate">

				<img style="-webkit-user-select: none" src="../img/setmorelogo.png"
					width="100px">
				<div class="msg" id="error">${errorMsg}</div>
				<div class="fieldgroup">

					<input type="text" placeholder="Email" id="lemail" name="email"
						style="margin-top: 20px" />
				</div>
				<input type="password" placeholder="Password" id="password"
					name="password" /><br>



				<button type="submit">login</button>


				<p class="message">
					Not registered? <a href="#myModal" onclick="hide()"
						data-toggle="modal">Create an account</a>
				</p>

			</form>
		</div>
	</div>


	<div class="modal fade" id="myModal" role="dialog">
		<div class="modal-dialog modal-lg">

			<!-- Modal content-->
			<div class="modal-content" style="height: 550px">
				<div class="modal-header" style="height: 70px">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 class="modal-title">
						<center>
							<strong>Create an Account for FREE</strong>
						</center>
					</h4>
				</div>
				<div class="modal-body">
					<div class="modal-body">

						<form role="form" id="register-form" action="/signup"
							method="post" class="registration-form" style="margin-top: 20px">
							<center>

								<div class="form-group">
									<input type="text" name="username" placeholder="Name"
										class="form-control" id="username">
								</div>

								<div class="form-group">
									<input type="text" name="email" placeholder="Email"
										class="form-email form-control" id="email">
								</div>
								<div class="form-group">
									<input type="password" name="password" placeholder="Password"
										class="form-password form-control" id="password">

								</div>
								<button type="submit" class="submitButton" onclick="load()">Sign
									me up!</button>
							</center>
						</form>

					</div>
				</div>
			</div>

		</div>
</body>

</html>