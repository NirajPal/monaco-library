<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html lang="en">
<head>
<title></title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<style>
.jumbotron {
	background-color: #ecf0f1;
	color: #fff;
	font-family: Montserrat, sans-serif;
}

.myButton {
	-moz-box-shadow:inset 0px 1px 0px 0px #9acc85;
	-webkit-box-shadow:inset 0px 1px 0px 0px #9acc85;
	box-shadow:inset 0px 1px 0px 0px #9acc85;
	background-color:#74ad5a;
	border:1px solid #3b6e22;
	display:inline-block;
	cursor:pointer;
	color:#ffffff;
	font-family:Arial;
	font-size:13px;
	font-weight:bold;
	padding:6px 12px;
	width: 170px;
	text-decoration:none;
}
.myButton:hover {
	background-color:#68a54b;
}
.myButton:active {
	position:relative;
	top:1px;
}


td {
	padding: 3px;
}

.navbar {
	margin-bottom: 0;
	border-radius: 0;
}

.post-container {
	margin: 20px 20px 0 0;
	border: 5px solid #333;
}

.post-thumb img {
	float: left;
	clear: left;
	margin-right: 8px;
}

.post-title {
	float: left;
}

.post-content {
	float: right;
	margin-right: 8px;
}

.clear {
	clear: both;
}
</style>


</head>
<body>
	<nav class="navbar navbar-inverse">
		<div class="container-fluid">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle" data-toggle="collapse"
					data-target="#myNavbar">
					<span class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="#">Library Management System</a>
			</div>
			<div class="collapse navbar-collapse" id="myNavbar">
				<ul class="nav navbar-nav">
					<li><a href="home">Home</a></li>
					<li><a href="checkin">Check In</a></li>
					<li><a href="fines">Fines</a></li>
					<li><a href="borrower">Borrower Management</a></li>
				</ul>
				<ul class="nav navbar-nav navbar-right">
        			<li><a href="login"><span class="glyphicon glyphicon-log-in"></span> Logout </a></li>
      			</ul>

			</div>
		</div>
	</nav>
	<div class="jumbotron text-center">
		
	</div>


	<div class="container-fluid">
		<div class="row content">
			<div class="col-sm-3 sidenav">
				<h3> About Library</h3>
				<ul class="list-group">
			      <li class="list-group-item"><a href="">Floor Plan</a></li>
			      <li class="list-group-item"><a href="">Direction to Campus</a></li>
			      <li class="list-group-item"><a href="">Policies and Regulations</a></li>
    			</ul>
			</div>
			<div class="col-sm-9">
				<div>
					
					<h5>Details :</h5>
				</div>
				<div id="Details"> 
				
					<div style="float:left; padding: 0px 2px">
						<img src="https://cdn2.iconfinder.com/data/icons/basicset/tick_64.png" />
					</div>
					<div style="float:left;padding: 0px 2px">	
						<h2><b>${msg}</b></h2>
					</div>
					<div style="float: left;padding: 0px 2px">
				 		
					</div>
					<div class="clear"></div>
				</div>
				<div>			
					<a href="fines" class="myButton" width="550px">Return to Fine Payment</a>
				</div>
			</div>

		</div>
	</div>

</body>
<script type="text/javascript">

</script>




</html>

