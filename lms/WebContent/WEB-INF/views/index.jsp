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
	width: 80px;
	text-decoration:none;
}
.myButton:hover {
	background-color:#68a54b;
}
.myButton:active {
	position:relative;
	top:1px;
}






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

.post-container{
    margin: 20px 20px 0 0;  
    border:5px solid #333;
}

.post-thumb img {
    float: left;
    clear:left;
    margin-right:8px;
}
.post-title{
    float:left;
}
.post-content {float:right;margin-right:8px;}
.clear{clear:both;}

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
		<form class="form-inline"  id="searchForm" method="post" >
			<input type="text" id="searchTerm" class="form-control" size="50"
				placeholder="Enter ISBN, Title or Author Name"></input>
			<button type="submit" id="search" class="btn btn-danger">Search</button>
		</form>
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
			<div class="col-sm-9" id="searchList">
				
			</div>

		</div>
	</div>

</body>


<script type="text/javascript">

jQuery(document).ready(function($) {

	$("#searchForm").submit(function(event) {
		
		event.preventDefault();
		search();
	});

});




function search() {

	var books = {}
	books["searchTerm"] = $("#searchTerm").val();
	
	//alert("AJAX");
	$.ajax({
		type : "POST",
		contentType : "application/json",
		url : "search",
		data : JSON.stringify(books),
		dataType : 'json',
		timeout : 100000,
		success : function(data) {
			console.log("SUCCESS: ", data);
			var a_hashMap=data;
			$("#searchList").empty();
			var length = Object.keys(data).length;
			if(length==0){
				var msgData='<h2>There are no books to display</h2>';
				 $(msgData).attr('id', 'x').appendTo('#searchList');
			}
			else{
			for(var i in a_hashMap) {
			    if (a_hashMap.hasOwnProperty(i)) {
			        console.log('Key is: ' + i + '. Value is: ' + a_hashMap[i]["title"]);
			        
			        var authorList=a_hashMap[i]["authors"];
			        var authors="";
			        for(var j in authorList)
			        	authors=authors+authorList[j]["authorName"]+" ; ";
			        	
			        authors=authors.slice(0,-2);
			        var divData= '<div class="list-group-item list-group-item-action">'+
			        				'<div style="float: left; clear:left; margin-right:8px;">'+
			        						'<img src="'+a_hashMap[i]["coverImage"]+'"/>'+
			        				'</div>';
			        				if(a_hashMap[i]["availableCopies"]>0)
										divData=divData+'<div style="float:right;width:100px;"><a class="myButton" href="checkoutbook?isbn='+a_hashMap[i]["isbn"]+'">Checkout</a></div>'
			        				
			        				
			        				
			        				divData=divData+'<div style="margin:0 auto;">'+
									'<h3 class="list-group-item-heading">'+a_hashMap[i]["title"]+'</h3>'+
									'<p class="list-group-item-text">'+
									
									'<table>'+
									  '<tr>'+
									   ' <td><b>ISBN :</b></td>'+
									   ' <td>'+ a_hashMap[i]["isbn"] + '</td>'+
									   ' <td><b>Publisher :</b></td>'+
									   ' <td>'+ a_hashMap[i]["publisher"] +'</td>'+
									   ' <td><b>Available Copies :</b></td> '+
									   ' <td>' +a_hashMap[i]["availableCopies"]+ '</td>'+
									  '</tr>'+
									'</table>'+
									'<table>'+
										'<tr>'+
										 	'<td><b>Author :</b></td>'+
									    	'<td>'+authors+'</td>'+
										'</tr>'+
									'</table>'+
									
									'</p>'+
									'</div>'+
									'<div class="clear"></div>'+	
									'</div>';		
			        $(divData).attr('id', 'holdy').appendTo('#searchList');
			        //alert('done');
			    }
			}
		  }
		},
		error : function(e) {
			console.log("ERROR: ", e);
			//display(e);
		},
		done : function(e) {
			console.log("DONE");
			
		}
	});

}
</script>







</html>

