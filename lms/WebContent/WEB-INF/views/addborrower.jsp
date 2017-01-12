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
    			<ul class="nav navbar-nav navbar-right">
        			<li><a href="login"><span class="glyphicon glyphicon-log-in"></span> Logout </a></li>
      			</ul>	
			</div>
			<div class="col-sm-9">
				<div>
					<h2>Create Card</h2>
				</div>
				<div>
					<p>${msg}</p>
				</div>
				<form:form class="form-horizontal" action="createcard" id="borrower-add" commandName="borrower" method="POST">
					<div class="form-group">
						<label class="control-label col-sm-2" for="email">First Name:</label>
						<div class="col-sm-6">
							<form:input type="text" path="name" class="form-control"
								id="name" placeholder="Enter Name"></form:input>
						</div>
					</div>
					
					<div class="form-group">
						<label class="control-label col-sm-2" for="email">Last Name:</label>
						<div class="col-sm-6">
							<form:input type="text" path="lastName" class="form-control"
								id="lastName" placeholder="Enter Last Name"></form:input>
						</div>
					</div>
					
					<div class="form-group">
						<label class="control-label col-sm-2" for="pwd">SSN:</label>
						<div class="col-sm-6">
							<form:input type="text" path="ssn" class="form-control" id="ssn"
								placeholder="Enter SSN"></form:input>
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-sm-2" for="pwd">Email:</label>
						<div class="col-sm-6">
							<form:input type="text" path="email" class="form-control" id="email"
								placeholder="Enter Email"></form:input>
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-sm-2" for="pwd">Address:</label>
						<div class="col-sm-6">
							<form:input type="text" path="address" class="form-control"
								id="address" placeholder="Enter Address"></form:input>
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-sm-2" for="pwd">State:</label>
						<div class="col-sm-6">
							<select class="form-control" id="state">
								<option value="AL">Alabama</option>
								<option value="AK">Alaska</option>
								<option value="AZ">Arizona</option>
								<option value="AR">Arkansas</option>
								<option value="CA">California</option>
								<option value="CO">Colorado</option>
								<option value="CT">Connecticut</option>
								<option value="DE">Delaware</option>
								<option value="DC">District Of Columbia</option>
								<option value="FL">Florida</option>
								<option value="GA">Georgia</option>
								<option value="HI">Hawaii</option>
								<option value="ID">Idaho</option>
								<option value="IL">Illinois</option>
								<option value="IN">Indiana</option>
								<option value="IA">Iowa</option>
								<option value="KS">Kansas</option>
								<option value="KY">Kentucky</option>
								<option value="LA">Louisiana</option>
								<option value="ME">Maine</option>
								<option value="MD">Maryland</option>
								<option value="MA">Massachusetts</option>
								<option value="MI">Michigan</option>
								<option value="MN">Minnesota</option>
								<option value="MS">Mississippi</option>
								<option value="MO">Missouri</option>
								<option value="MT">Montana</option>
								<option value="NE">Nebraska</option>
								<option value="NV">Nevada</option>
								<option value="NH">New Hampshire</option>
								<option value="NJ">New Jersey</option>
								<option value="NM">New Mexico</option>
								<option value="NY">New York</option>
								<option value="NC">North Carolina</option>
								<option value="ND">North Dakota</option>
								<option value="OH">Ohio</option>
								<option value="OK">Oklahoma</option>
								<option value="OR">Oregon</option>
								<option value="PA">Pennsylvania</option>
								<option value="RI">Rhode Island</option>
								<option value="SC">South Carolina</option>
								<option value="SD">South Dakota</option>
								<option value="TN">Tennessee</option>
								<option value="TX">Texas</option>
								<option value="UT">Utah</option>
								<option value="VT">Vermont</option>
								<option value="VA">Virginia</option>
								<option value="WA">Washington</option>
								<option value="WV">West Virginia</option>
								<option value="WI">Wisconsin</option>
								<option value="WY">Wyoming</option>
							</select>
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-sm-2" for="pwd">Country:</label>
						<div class="col-sm-6">
							<form:input type="text" path="country" class="form-control"
								id="country" placeholder="Enter country"></form:input>
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-sm-2" for="pwd">Telephone No.</label>
						<div class="col-sm-6">
							<form:input type="text" path="telephoneNo" class="form-control"
								id="telephoneNo" placeholder="Enter Telephone"></form:input>
						</div>
					</div>
					<div class="form-group">
						<div class="col-sm-offset-2 col-sm-6">
							<button type="submit" class="btn btn-danger">Submit</button>
						</div>
					</div>
				</form:form>




			</div>

		</div>
	</div>

</body>
<script type="text/javascript">

</script>




</html>

