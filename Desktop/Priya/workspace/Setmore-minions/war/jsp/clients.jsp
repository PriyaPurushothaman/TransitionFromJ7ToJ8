<%@ page language="java" contentType="text/html; charset=US-ASCII"
	pageEncoding="US-ASCII"%>
<%@ page import="java.util.List"%>
<%@ page import="com.setmore.minions.jdo.CustomerJdo"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=US-ASCII">
<meta http-equiv="x-ua-compatible" content="ie=edge">
<title>Clients page</title>
<meta name="description" content="">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="x-ua-compatible" content="ie=edge">
<title></title>
<meta name="description" content="">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="apple-touch-icon" href="apple-touch-icon.png">
<!-- Place favicon.ico in the root directory -->
<link rel="stylesheet" href="bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" href="css/icons.css">
<link rel="stylesheet" href="css/normalize.css">
<link rel="stylesheet" href="css/main.css">
<link rel="stylesheet" href="css/style.css">
<link rel="stylesheet" href="css/forms.css">
<link rel="stylesheet" href="bootstrap/css/sweetalert.css">
<script src="bootstrap/js/sweetalert.min.js"></script>
<script src="js/vendor/modernizr-2.8.3.min.js"></script>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<style>
.addbutton span {
	margin-right: 0px;
}

.addbutton {
	border-radius: 25px;
	background-color: #f4511e;
	color: #FFFFFF;
	font-size: 28px;
	margin-left: 290px;
	line-height: 18px;
}

.client-ul li {
	width: 850px;
}
</style>
<script>
	$(document).ready(function(){ 
		loadCustomerListOfAdmin();
	     $('.customersubmit').click(function(){
			var adminName = '<%=session.getAttribute("AdminName")%>';
	 		var adminEmail = '<%=session.getAttribute("AdminEmail")%>';
			var adminId = '<%=session.getAttribute("AdminId")%>';
			var customerName = $("#customerName").val();
			var customerEmail = $("#customerEmail").val();
			var customerMobile = $("#customerMobile").val();
			var customerZip = $("#customerZip").val();
			var customerAddress = $("#customerAddress").val();
			console.log("adminEmail "+adminEmail+"adminName "+adminName
					+"adminId "+adminId+"customerName "+customerName+"customerEmail "+customerEmail
					+"customerMobile "+customerMobile+"customerZip "+customerZip+"customerAddress "+customerAddress);
			var json = {
					"adminName" : adminName,
					"adminEmail" : adminEmail,
					"adminId" : adminId,
					"customerName" : customerName,
					"customerEmail" : customerEmail,
					"customerMobile" : customerMobile,
					"customerZip" : customerZip,
					"customerAddress" : customerAddress,
				};
				$.ajax({
							url : "addCustomer",
							data : JSON.stringify(json),
							type : "POST",
							beforeSend : function(xhr) {
								xhr.setRequestHeader("Accept", "application/json");
								xhr.setRequestHeader("Content-Type", "application/json");
							},
							success : function(data) {
								console.log(JSON.stringify(data))
								console.log(data.id);
								console.log(data.customerName);
								$('#fb-dialog').hide();
								$('<li id="'+data.id+'"class="input-group input-group-lg notesDiv"onclick="loadCustomerDetails('+data.id+')">'
												+data.customerName+'</li>').appendTo("#customerList");
							}
						});
	     });
	     $('#sendEmail').click(function(){
	    	 var customerEMail = $('#customerEmail').val();
	    	 sweetAlert(customerEMail,"Email Sent", "success");
	    	 var json = {
						"customerEmail" : customerEMail,
	    			 };
	    		 $.ajax({
	    	    	 url: "/generatePassword",
	    	    	 type: "POST",
					 data : JSON.stringify(json),
					 beforeSend : function(xhr) {
							xhr.setRequestHeader("Accept", "application/json");
							xhr.setRequestHeader("Content-Type", "application/json");
						},
	    	    	 success: function(data){
	    	    		
	    	    	 }
	    	    	 });
	    		   	 
	   
	     
	 	});

	});

	function loadCustomerDetails(customerId){
	 	var json = { "customerId" : customerId };
			$
			.ajax({
				url : "loadCutomerDataFroDB",
				data : JSON.stringify(json),
				type : "POST",
				beforeSend : function(xhr) {
					xhr.setRequestHeader("Accept", "application/json");
					xhr
							.setRequestHeader("Content-Type",
									"application/json");
				},
				success : function(data) {
					$("#customerName").val(data[0].customerName);
					$("#customerEmail").val(data[0].customerEmail);
					$("#customerMobile").val(data[0].customerMobile);
					$("#customerZip").val(data[0].customerZip);
					$("#customerAddress").val(data[0].customerAddress);
					$("#clear1").text(data[0].customerName);
					$("#clear2").text(data[0].customerEmail);
					$("#clear3").text(data[0].customerMobile);
					$('.contact').fadeIn('fast');
					}
			});
	}

	function loadCustomerListOfAdmin(){
		var libox = "" ;
		var email = '<%=session.getAttribute("AdminEmail")%>';
		var json = {
				
				"adminEmail" : email
				
			};

		
		$
		.ajax({
			url : "getCustomerList",
			type : "POST",
			data : JSON.stringify(json),
			beforeSend : function(xhr) {
				xhr.setRequestHeader("Accept", "application/json");
				xhr.setRequestHeader("Content-Type",
								"application/json");
			},
			success : function(data) {
			var length = data.length;
			for (var i = 0; i< length; i++){
				$('<li id="'+data[i].id+'"class="input-group input-group-lg notesDiv"onclick="loadCustomerDetails('+data[i].id+')">'
						+data[i].customerName+'</li>').appendTo("#customerList");
			}
			}
		});
	}
		function demo() {
			var email = '<%=session.getAttribute("AdminEmail")%>'
			var username = '<%=session.getAttribute("AdminName")%>'
		sweetAlert(username, email, "success");
	}
</script>
</head>
<body>
	<div id="side-menu">
		<ul>
			<li><a href="/logout" class="icon-home"><small>Home</small></a></li>
			<li><a href="#" class="icon-calendar-31"><small>Calendar</small></a></li>
			<li class="active"><a href="" class="icon-users2"><small>Clients</small></a></li>
			<li><a href="#" class="icon-laptop-phone"><small>Bookings</small></a></li>
			<li><a href="#" class="icon-cog"><small>Settings</small></a></li>
			<li id="premium-icon"><a href="#" class="icon-shield-alert"><small>Premium</small></a></li>
			<li><a href="#" class="icon-user" onclick="demo()"><small>user</small></a></li>
		</ul>
	</div>
	<div id="app-bar" class="headroom">
		<nav class="small-hide">
		<ul class="block left">
			<li id="logo"><a href="#">&nbsp;</a></li>
			<li><a href="#">Dashboard</a></li>
			<li><a href="#">Calendar</a></li>
			<li><a href="#">Clients</a></li>
			<li><a href="#">Online Booking</a></li>
			<li><a href="#">Settings</a></li>
		</ul>
		<ul class="block right">
			<li><a href="#">My Account</a></li>
		</ul>
		</nav>
		<header id="toolbar">
		<div id="main-logo"></div>
		<h1>
			<span class="icon-calendar-31"></span>Clients
		</h1>
		<div>
			<button class="addbutton">
				<span> + </span>
			</button>
		</div>
		</header>
	</div>
	<div id="app-wrap">
		<div class="container main-content">
			<div class="row client-wrapper">
				<div id="fixed-sidebar" class="three columns">
					<div id="client-cats" class="card no-click">
						<header>
						<h4>Search...</h4>
						</header>
						<ul class="sidebar-nav cats fixed">
							<li class="active ripple"><a href="#">Upcoming Clients</a></li>
							<li><a href="#" class="ripple">Recently Booked</a></li>
							<li><a href="#" class="ripple">Upcoming Birthdays</a></li>
							<li><a href="#" class="ripple">30 Days Inactive</a></li>
						</ul>
					</div>
					<div class="card no-click">
						<ul class="sidebar-nav cats fixed">
							<li><a href="#">Import / Export...</a></li>
						</ul>
					</div>
				</div>
				<div id="client-cards" class="nine columns card no-click">
					<header>
					<h3 class="left">Upcoming Clients</h3>
					<h4 class="right">A/Z</h4>
					</header>
					<ul class="client-ul" id="customerList" name="customeritems">
					</ul>
				</div>
			</div>
		</div>

	</div>
	<div class="dialog-overlay contact" id="fb-dialog">
		<div class="dialog-wrapper">
			<header> <a class="close-btn left"><span
				class="icon-cross"></span></a>
			<h3 class="left">Client Details</h3>
			</header>
			<div class="dialog-body">
				<div class="dialog-sidebar contact-sidebar">
					<img src="img/img-holder.png" class="left" />
					<h2 id="clear1"></h2>
					<p id="clear2"></p>
					<p id="clear3"></p>
					<button class="button button-primary">Book Appointment</button>
					<button id="sendEmail" class="button button-secondary">Send
						Login Details</button>
				</div>
				<div class="dialog-content full-width">
					<div class="twelve columns">
						<ul class="tabs">
							<li class="contact-details active-tab">Contact Details</li>
						</ul>
						<form id="result" class="result" onsubmit="return false;"
							novalidate>
							<fieldset class="row">
								<label for="" class="">Contact Details</label>
								<div>
									<span class="on"></span> <input class="" type="text" Required
										Name="customerName" placeholder="Customer Name"
										title="Customer Name" id="customerName"> <span
										class="on"></span> <input class="" type="text"
										placeholder="Mobile" title="Mobile" id="customerMobile"
										Name="customerMobile"> <span class="off"></span> <input
										class="" type="email" placeholder="Email" title="Email"
										id="customerEmail" Required Name="customerEmail""> <input
										type="hidden" name="useremail"
										value="<%=session.getAttribute("AdminEmail")%>"> <input
										type="hidden" name="username"
										value="<%=session.getAttribute("AdminName")%>"> <input
										type="hidden" name="adminId"
										value="<%=session.getAttribute("AdminId")%>">
								</div>
							</fieldset>
							<fieldset class="row">
								<label for="" class="">Address</label>
								<div>
									<span class="off"></span> <input class="" type="text"
										Name="customerAddress" placeholder="Street Address"
										title="Street Address" id="customerAddress"> <span
										class="off"></span> <input class="" type="text"
										placeholder="City, State, Zip" Name="customerZip"
										title="City, State, Zip" id="customerZip">
								</div>
							</fieldset>
							<a href="" class="customersubmit"><small><button
										class="new-green-btn" id="customersubmit"
										style="width: 100px; height: 40px;">Save</button> </small></a> <a href=""><small><button
										class="new-gray-btn closing-btn"
										style="width: 100px; height: 40px;">Cancel</button> </small></a>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
<script>
		(function(b, o, i, l, e, r) {
			b.GoogleAnalyticsObject = l;
			b[l] || (b[l] = function() {
				(b[l].q = b[l].q || []).push(arguments)
			});
			b[l].l = +new Date;
			e = o.createElement(i);
			r = o.getElementsByTagName(i)[0];
			e.src = 'https://www.google-analytics.com/analytics.js';
			r.parentNode.insertBefore(e, r)
		}(window, document, 'script', 'ga'));
		ga('create', 'UA-XXXXX-X', 'auto');
		ga('send', 'pageview');
	</script>
	<script>
		$('.customerlist').click(function() {
			$('.contact').fadeIn('fast');
		});
		$('.close-btn').click(function() {
			$(this).parent().parent().parent().hide();
			$('.contact').hide();
		});
		$('.closing-btn').click(function() {
			$('.contact').hide();
		});
		$('.addbutton').click(function() {
			$("#customerName").val("");
			$("#customerEmail").val("");
			$("#customerMobile").val("");
			$("#customerZip").val("");
			$("#customerAddress").val("");
			$("#clear1").text('');
			$("#clear2").text('');
			$("#clear3").text('');
			$('.contact').fadeIn('fast');
		});
	</script>
</body>
</html>
