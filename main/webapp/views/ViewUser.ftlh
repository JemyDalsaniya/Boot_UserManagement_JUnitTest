<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>View User Data</title>
<link
	href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.5.2/css/bootstrap.css">
<!-- <link
	href="https://cdn.datatables.net/responsive/2.2.9/css/dataTables.responsive.css"> -->

<link
	href="https://cdn.datatables.net/1.11.5/css/dataTables.bootstrap4.min.css">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

<link href="resources/css/custom.css" rel="stylesheet">

<style type="text/css">
.switch {
	position: relative;
	display: inline-block;
	width: 50px;
	height: 25px;
}

.switch input {
	opacity: 0;
	width: 0;
	height: 0;
}

.slider {
	position: absolute;
	cursor: pointer;
	top: 0;
	left: 0;
	right: 0;
	bottom: 0;
	background-color: #ccc;
	-webkit-transition: .4s;
	transition: .4s;
}

.slider:before {
	position: absolute;
	content: "";
	height: 20px;
	width: 20px;
	left: 4px;
	bottom: 3px;
	background-color: white;
	-webkit-transition: .4s;
	transition: .4s;
}

input:checked+.slider {
	background-color: #2196F3;
}

input:focus+.slider {
	box-shadow: 0 0 1px #2196F3;
}

input:checked+.slider:before {
	-webkit-transform: translateX(26px);
	-ms-transform: translateX(26px);
	transform: translateX(26px);
}

.slider.round {
	border-radius: 34px;
}

.slider.round:before {
	border-radius: 50%;
}
</style>
</head>
<body class="all_page_background">
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
				<a class="navbar-brand" href="adminHome">Admin</a> <a
					class="navbar-brand content-center">User Management System</a>
			</div>
			<div class="collapse navbar-collapse"
				id="bs-example-navbar-collapse-1">
				<ul class="nav navbar-nav navbar-right">
					<li><a href="userlogin">Logout</a></li>
				</ul>
			</div>
		</div>
	</nav>
	<div class="container">
		<table id="example"
			class="table table-striped table-bordered table_css">
			<thead>
				<tr>
					<th colspan="9" style="text-align: center">User Details</th>
				</tr>

				<tr>
					<th>Name</th>
					<th>Email</th>
					<th>Contact</th>
					<th>Gender</th>
					<th>Hobby</th>
					<th>Profile Picture</th>
					<th>Date of Birth</th>
					<th>Edit/Delete</th>
					<th>Role</th>
				</tr>
			</thead>
			<tbody>
				
				<#list allusers as item>	
						<tr>
						<td>${item.userName}</td>
						<td>${item.userEmail}</td>
						<td>${item.userContact}</td>
						<td>${item.userGender}</td>
						<td>${item.userHobby}</td>
						<td><img src="data:image/jpg;base64,${item.base64Image}"
							width="100" height="100"></td>
						<td>${item.userDOB}</td>
						<td><a href="editProfile?user=adminEdit&id=${item.userId}"><i
								class="fa fa-pencil-square-o fa-lg " aria-hidden="true"></i></a>
							&nbsp;&nbsp;<a id="${item.userId}" class="delete"><i
								class="fa fa-trash fa-lg " aria-hidden="true"></i></a></td>
						<td>
							<!-- Default switch -->
							<div class="custom-control custom-switch">
								<label class="switch"> <input type="checkbox"
									class="role_admin" id="${item.userId}"> <span
									class="slider round"></span>
								</label>
							</div>
						</td>
					</tr>
					</#list>
			</tbody>
		</table>
	</div>
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
	<script src="https://code.jquery.com/jquery-3.5.1.js"></script>
	<script
		src="https://cdn.datatables.net/responsive/2.2.9/js/dataTables.responsive.js"></script>
	<script
		src="https://cdn.datatables.net/1.11.5/js/jquery.dataTables.min.js"></script>
	<script
		src="https://cdn.datatables.net/1.11.5/js/dataTables.bootstrap4.min.js"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/js/bootstrap.min.js"></script>
	<script>
		$(document).ready(function() {
			
			$('#example').DataTable({
				"lengthMenu": [[3, 5, 7, 10, "All"], [3, 5, 7, 10, "All"]]
			});
			
			$(document).on("click", ".delete", function() {

				var rowToDelete = this;
				var userId = +this.id;
				$.ajax({
					url : "DeleteUser",
					type : "post",
					data : ({
						userId : userId,
					}),
					success : function(data) {
						alert("deleted successfully..");
						$(rowToDelete).closest('tr').fadeOut(100, function() {
							$(this).remove();
						});
					},
					error: function(data){
						alert("error")
					}
				});
			});
			
			$(document).on("change",".role_admin" , function() {
				alert("change role called");
				var rowToDelete = this;
				var userId = +this.id;
				alert(userId);
				$.ajax({
					url : "ChangeRole",
					type : "post",
					data : ({
						userId : userId,
					}),
					success : function(data) {
						alert("Role Changed..");
						$(rowToDelete).closest('tr').fadeOut(100, function() {
							$(this).remove();
						});
					},
					error: function(data){
						alert("error")
					}
				})
			});

		});
	</script>
	<#include "footer.ftlh" />
</body>
</html>