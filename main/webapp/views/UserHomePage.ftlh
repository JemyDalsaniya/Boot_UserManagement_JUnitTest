<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>User Home Page</title>
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/css/bootstrap.min.css">
<link rel="stylesheet" href="resources/css/custom.css">
<style type="text/css">
table {
	background-color: #626262 !important;
	color: white;
	font-size: 17px;
}

table.table-bordered {
	border: 2px solid black;
	margin-top: 20px;
}

table.table-bordered>tbody>tr>td {
	border: 1px solid black;
}

td, h2 {
	text-align: center;
}
</style>
</head>

<body class="bg_custom_color">
<#if userDetails??>
 
 				
 									<#assign id="${userDetails.userId}">
 									${id}
									<#assign fullname="${userDetails.userName}">
									<#assign email="${userDetails.userEmail}">
									<#assign contact="${userDetails.userContact}">
							 		<#assign gender="${userDetails.userGender}"> 
							 		<#assign hobby="${userDetails.userHobby}">
							 		<#assign image="${userDetails.base64Image}">
							 		<#assign dob ="${userDetails.userDOB}">
									</#if>

 	<nav class="navbar navbar-inverse">
		<div class="container-fluid">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle collapsed"
					data-toggle="collapse" data-target="#bs-example-navbar-collapse-1"
					aria-expanded="false">
					<span class="sr-only">Toggle navigation</span> <span
						class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<a class="navbar-brand whitecolor" href="userlogin">Home</a>
			</div>
			<div class="collapse navbar-collapse"
				id="bs-example-navbar-collapse-1">
				<ul class="nav navbar-nav">
					<li><a href="editProfile?id=${id!""}&name=userEdit">Edit Profile<span
							class="sr-only">(current)</span></a>
				</ul>
				<ul class="nav navbar-nav navbar-right">
					<li><a href="logout">Logout</a></li>
				</ul>
			</div>
		</div>
	</nav>

	<div class="container">
		<h2>User Details</h2>
		<table class="table table-bordered table-dark table_border">
				<tr>
					<td>Name</td>
					<td>${fullname!""}</td>
				</tr>
				<tr>

					<td>Email</td>
					<td>${email!""}</td>
				</tr>

				<tr>
					<td>Contact</td>
					<td>${contact!""}</td>
				</tr>
				<tr>
					<td>Gender</td>
					<td>${gender!""}</td>
				</tr>
				<tr>
					<td>Hobby</td>
					<td>${hobby!""}</td>
				</tr>
				<tr>
					<td>Profile Picture</td>
					<td><img src="data:image/jpg;base64,${image!""}"
						width="100" height="100"></td>
				</tr>
				<tr>
					<td>Date of Birth</td>
					<td>${dob!""}</td>
				</tr>
				<#list addressList as address>
					<tr>
						<td>Address</td>
						<td>Address ${address?index+1}<br/><br/>
						Street : ${address.addStreet}  <br/>
							Pincode : ${address.addPincode} <br/>
							City : ${address.addCity} <br/>
							State: ${address.addState}
						</td>
					</tr>
					</#list>
					
</table>
	</div>

	<#include "footer.ftlh">
</body>
</html>