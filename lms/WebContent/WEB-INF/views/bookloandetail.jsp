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
					<h2>Checkin Book</h2>
					<h5>Details :</h5>
				</div>
				<div id="Details"> 
				
					<div style="float:left; padding: 0px 2px">
						<img src=${bookLoans.book.coverImage}/>
					</div>
					<div style="float:left;padding: 0px 2px">		
						<p><b>${bookLoans.book.title}</b></p>
						<p>ISBN: ${bookLoans.book.isbn}</p>
						<p>Date Issued: ${bookLoans.dateOut} Due Date:  ${bookLoans.dueDate}</p>
					</div>
					<div style="float: left;padding: 0px 2px">
				 		<p><b>Checkout Details:</b></p>
				 		<p>Loan Id: ${bookLoans.loanId}</p>
				 		<c:if test="${not empty bookLoans.fineAmount}">
    						<p>Fines: ${bookLoans.fineAmount}</p>
						</c:if>
					</div>
					<div class="clear"></div>
					
				</div>
				<div>
					<form:form class="form-horizontal" action="checkinbook" id="checkin-add" commandName="bookLoans" method="POST">
							
							<form:input type="hidden" value="${bookLoans.extraDays}" path="extraDays"></form:input>
							<form:input type="hidden" value="${bookLoans.loanId}" path="loanId"></form:input>
							<form:input type="hidden" value="${bookLoans.book.isbn}" path="book.isbn"></form:input>
							<center><button type="submit" id="checkin">Check In Book</button></center>
							
					</form:form>
				</div>
				
			</div>

		</div>
	</div>

</body>
<script type="text/javascript">

</script>




</html>

