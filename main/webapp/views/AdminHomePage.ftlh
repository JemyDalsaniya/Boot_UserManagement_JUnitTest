<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Admin Home Page</title>
<link
	href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.5.2/css/bootstrap.css">
<link
	href="https://cdn.datatables.net/1.11.5/css/dataTables.bootstrap4.min.css">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/css/bootstrap.min.css">
<link href="resources/css/custom.css" rel="stylesheet"/>

</head>

<body class="bg_custom_color">

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
				<a class="navbar-brand" href="adminHome">Admin</a>
			</div>
			<div class="collapse navbar-collapse"
				id="bs-example-navbar-collapse-1">
				<ul class="nav navbar-nav">
					<li><a href="editProfile?name=admin">Edit Profile<span
							class="sr-only">(current)</span></a>
					<li><a href="view">View User<span
							class="sr-only">(current)</span></a>
					<li><a href="addUser?name=ADD" id="addLink">Add
							User<span class="sr-only">(current)</span>
					</a>
				</ul>
				<a class="navbar-brand content-center">User Management System</a>
				<ul class="nav navbar-nav navbar-right">
					<li><a href="logout">Logout</a></li>
				</ul>
			</div>
		</div>
	</nav>
	<div class="container">
		<table id="example"
			class="table table-striped table-bordered table_css">
			<thead>
				<tr>
					<th colspan="7" style="text-align: center">Admin Details</th>
				</tr>
				<tr>
					<th>Name</th>
					<th>Email</th>
					<th>Contact</th>
					<th>Gender</th>
					<th>Hobby</th>
					<th>Profile</th>
					<th>Date of Birth</th>
				</tr>
			</thead>
			<tbody>
				
				<#list adminDetails as item>
					<tr>
						<td>${item.userName}</td>
						<td>${item.userEmail}</td>
						<td>${item.userContact}</td>
						<td>${item.userGender}</td>
						<td>${item.userHobby}</td>
						<td><img src="data:image/jpg;base64,${item.base64Image}"
						width="100" height="100"></td>
						<td>${item.userDOB}</td>
					</tr>
					</#list>
			</tbody>
		</table>
	</div>
	<script src="https://code.jquery.com/jquery-3.5.1.js"></script>
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
	<script
		src="https://cdn.datatables.net/1.11.5/js/jquery.dataTables.min.js"></script>
	<script
		src="https://cdn.datatables.net/1.11.5/js/dataTables.bootstrap4.min.js"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/js/bootstrap.min.js"></script>
	<!-- <script src="custom/js/datatable.js"></script> -->
	<script type="resources/js/adduser.js"></script>
	<script>
		$(document).ready(
				function() {
					$('#example').DataTable(
							{
								"lengthMenu" : [ [ 3, 5, 7, 10, "All" ],
										[ 3, 5, 7, 10, "All" ] ]
							});

				});
	</script>
	<#include "footer.ftlh" />
</body>
</html>