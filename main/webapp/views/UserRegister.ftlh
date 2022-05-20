<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Registration Form</title>
<link
	href="https://fonts.googleapis.com/css2?family=Poppins:ital,wght@0,100;0,200;0,300;0,400;0,500;0,600;0,700;0,800;1,100;1,200;1,300;1,400;1,600;1,700;1,800;1,900&family=Raleway:ital,wght@0,100;0,200;0,300;0,400;0,500;0,600;0,700;0,800;0,900;1,100;1,200;1,300;1,400;1,500;1,600;1,700;1,800;1,900&display=swap"
	rel="stylesheet" />
<link href="https://www.jqueryscript.net/css/jquerysctipttop.css"
	rel="stylesheet" type="text/css">
<link
	href="https://fonts.googleapis.com/css?family=Roboto:300,400,500,700&display=swap"
	rel="stylesheet">
<link
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.9.0/css/all.min.css"
	rel="stylesheet">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/bootswatch/4.3.1/minty/bootstrap.min.css">
<link rel="stylesheet" type="text/css"
	href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.6.4/css/bootstrap-datepicker.css">
<link rel="stylesheet" type="text/css"
	href="https://cdnjs.cloudflare.com/ajax/libs/select2/3.3.2/select2.css">
<link rel="stylesheet" type="text/css"
	href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.6.3/css/bootstrap-select.css">
<!-- Bootstrap -->

<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
	rel="stylesheet">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
<link href="resources/css/custom.css" rel="stylesheet"/>


</head>

<body class="bg-info bg_custom_color">

<#if userDetails??>
<#assign id="${userDetails.userId}">
<#assign name="${userDetails.userName}">
<#assign contact="${userDetails.userContact}">
<#assign email="${userDetails.userEmail}">
<#assign password="${userDetails.userPassword}">
<#assign cpassword="${userDetails.userPassword}">
<#assign dob="${userDetails.userDOB}">
<#assign image="${userDetails.base64Image}">
<#assign gender = "${userDetails.userGender}">
</#if>
<#if adminDetails??>
<#list adminDetails as item>
<#assign adminId="${item.userId}">
<#assign name="${item.userName}">
<#assign contact="${item.userContact}">
<#assign email="${item.userEmail}">
<#assign password="${item.userPassword}">
<#assign cpassword="${item.userPassword}">
<#assign dob="${item.userDOB}">
<#assign image="${item.base64Image}">
<#assign gender = "${item.userGender}">
</#list>
</#if>

	
	<div class="container">

		<form action="UserRegister" method="POST" id="registration_form" modelAttribute="registerForm" enctype="multipart/form-data">
			<h3 style="text-align: center" class="margin_top_file header_tag">Registration
				Form</h3>
					<#if userDetails??>
					${id}
					<input type="hidden" name="userId" value="${id!""}">
					</#if>
					<#if adminDetails??>
					${adminId}
					<input type="hidden" name="userId" value="${adminId!""}">
					</#if>
					<div class="row form-row">
						<div class="col-md-6">

							<div class="form-group">
							 <label for="img">Profile picture:</label><input type="file"
									id="img" name="file" /><img name="oldImage" id="image_preview" src="data:image/jpg;base64,${image!""}" style="width: 170px; height: 170px;"><br />
								<input type="hidden" value="${image!""}" name="storeImage">
									
								<span id="image_error"></span>

							</div>

						</div>
					</div>

					<div class="row form-row ">
						<div class="col-md-6">
							<!-- First Name -->
							<div class="form-group">
								<label for="fname">Full Name:</label> <input type="text"
									class="form-control text-width" id="name" name="userName"
									placeholder="Enter your name" value="${name!""}"/><span id="name_error"></span>
							</div>
						</div>
						<div class="col-md-6">

							<!-- Phone Number -->
							<div class="form-group">
								<label>Contact No:</label> <input type="text"
									class="form-control" id="contact" placeholder="Phone number"
									name="userContact" value="${contact!""}" /> <span id="contact_error"></span>
							</div>

						</div>
					</div>

					<div class="row form-row">
						<div class="col-md-6">
							<!-- Email  -->
							<div class="form-group">
								<label for="mail">Email:</label> <input type="text"
									class="form-control text-width" id="mail"
									placeholder="Email-id" name="userEmail" value="${email!""}"/> <span id="email_error"></span><br>
								<span id="result_error"></span>
							</div>
						</div>
						<div class="col-md-6">
							<!-- password -->
							<div class="form-group">
								<label for="pwd">Password:</label> <input type="password"
									class="form-control text-width" id="pwd" placeholder="Password"
									name="userPassword" value="${password!""}" /> <span id="password_error"></span>
							</div>
						</div>
					</div>

					<div class="row form-row">

						<!-- Radio btn  -->
						<div class="col-md-6">
							<div class="form-group">
								<label for="id1">Gender:</label><br /> <label
									class="radio-inline radio-class"> <input type="radio"
									name="userGender" id="id1" value="female" /> Female
								</label><br /> <label class="radio-inline radio-class"> <input
									type="radio" name="userGender" id="id2" value="male" /> Male
								</label><br /> <span id="radio_error"></span>
							</div>
						</div>

						<!-- confirm password -->
						<div class="col-md-6">
							<div class="form-group">
								<label for="pwd2">Confirm Password:</label> <input
									type="password" class="form-control text-width" id="cpwd"
									name="cpassword" placeholder="Confirm-Password" value="${cpassword!""}" /> <span
									id="confirm_password_error"></span>
							</div>
						</div>

					</div>

					<div class="row form-row">
						<div class="col-md-6">

							<!-- Hobby -->
							<label>Hobby</label> <br />
							<div class="form-check">
								<input type="checkbox" class="form-check-input" id="check1"
									name="userHobby" value="Sports"> <label
									class="form-check-label" for="check1">Sports</label>
							</div>
							<div class="form-check">
								<input type="checkbox" class="form-check-input" id="check2"
									name="userHobby" value="Study"> <label
									class="form-check-label" for="check2">Study</label>
							</div>
							<div class="form-check">
								<input type="checkbox" class="form-check-input" id="check3"
									name="userHobby" value="Dance"> <label
									class="form-check-label">Dance</label>
							</div>
							<div class="form-check">
								<input type="checkbox" class="form-check-input" id="check4"
									name="userHobby" value="Music"> <label
									class="form-check-label">Music</label> <br /> <span
									id="check_error"></span>
							</div>

						</div>
						<div class="col-md-6">
							<div class="form-group">
								<label for="fname">Date Of Birth:</label> <input type="date"
									class="form-control text-width" id="dob" name="userDOB" value="${dob!""}" /> <span
									id="dob_error"></span>
							</div>
						</div>

					</div>
			
			<!-- jQuery plugin code -->
			<label class="address_label_margin">Address:</label>

			<div class="margin-t-md">
				<div class="customer-form">
					<div class="card"></div>
					
							<#if addressList??>
								
							<div id="main-container">
								
								<#list addressList as addr>
								<div class="panel card container-item">
									<div class="panel-body">
										<div class="panel-body">
										<input type="hidden" name="addressId[]" id="addressId[]"
															value="${addr.addId!""}">
											<div class="row">
												<div class="col-sm-4">
													<div class="form-group">
														<label class="control-label" for="address_line_one_0">Street</label>
														<input type="text" id="street" class="form-control"
															name="address[${addr?index}].addStreet" maxlength="255" placeholder="Street" value="${addr.addStreet!""}"> <span
															id="street_error"></span>
													</div>
												</div>
												<div class="col-sm-4">
													<div class="form-group">
														<label class="control-label" for="address_line_two_0">Landmark</label>
														<input type="text" id="landmark" class="form-control"
															name="address[${addr?index}].addLandmark" maxlength="255" placeholder="Landmark" value="${addr.addLandmark!""}"> <span
															id="landmark_error"></span>
													</div>
												</div>
												<div class="col-sm-4">
													<div class="form-group">
														<label class="control-label" for="address_line_two_0">Pincode</label>
														<input type="text" id="pincode" class="form-control"
															name="address[${addr?index}].addPincode" maxlength="255" placeholder="Pincode" value="${addr.addPincode!""}"> <span
															id="pincode_error"></span>
													</div>
												</div>
											</div>
											<div class="row">

												<div class="col-sm-6">
													<div class="form-group">
														<label class="control-label" for="city_0">City</label> <select
															class="form-control" name="address[${addr?index}].addCity" id="city${addr?index}"
															style="height: auto;">
															<option value="0">Select City</option>
															<option value="Ahemdabad">Ahemdabad</option>
															<option value="Junagadh">Junagadh</option>
															<option value="Mumbai">Mumbai</option>
															<option value="Surat">Surat</option>
															<option value="Vadodara">Vadodara</option>
														</select> <span id="city_error"></span>
													</div>
												</div>


												<div class="col-sm-6">
													<div class="form-group">
														<label class="control-label" for="state_0">State</label> <select
															class="form-control" name="address[${addr?index}].addState" id="state${addr?index}"
															style="height: auto;">
															<option value="0">Select State</option>
															<option value="Gujarat">Gujarat</option>
															<option value="Maharashtra">Maharashtra</option>
															<option value="Goa">Goa</option>
															<option value="Punjab">Punjab</option>
															<option value="Assam">Assam</option>
														</select> <span id="state_error"></span>
													</div>
												</div>
											</div>


											<div class="row">
												<div class="col-sm-12">
													<div>
														<a href="javascript:void(0)"
															class="remove-item btn btn-sm btn-danger remove-social-media">Remove</a>
													</div>
												</div>
											</div>
										</div>
									</div>
								</div>
								</#list>
							</div>
							<div>
								<a class="btn btn-success btn-sm" id="add-more"
									href="javascript:;" role="button"><i class="fa fa-plus"></i>
									Add address</a>
							</div>
							<#else>
							<#--started-->	
								
							<div id="main-container">
							
						<div class="panel card container-item mb-2">
							<div class="panel-body">
								<div class="panel-body pl-2 pr-2 pb-2">
									<div class="flex flex-row gap-8">
										<div class="col-sm-4">
											<label class="block mt-2 text-md font-semibold">
												<span class="text-gray-700 dark:text-gray-400 col-sm-4">Street</span>
										<input type="text"  id="street" value="${street!""}" class="form-control form-control-user" />
										<span id="street_error"></span> </label>
										</div>
									</div>	
									
									<div class="flex flex-row gap-8">
										<div class="col-sm-4">
											<label class="block">
												<span class="text-gray-700 dark:text-gray-400 col-sm-4">Landmark</span>
										<input type="text"  id="landmark" value="${landmark!""}" class="form-control form-control-user" />
										<span id="landmark_error"></span> </label>
										</div>
									</div>	
									
									<div class="flex flex-row gap-8">
										
										<div class="col-sm-4">
											<label class="block mt-2 mt-4 text-md font-semibold">
												<span class="text-gray-700 dark:text-gray-400">Pin
													Code</span> <input type="number" id="pincode"
												value="${pin!""}"
												class="form-control form-control-user"
												placeholder="Pincode" /> <span id="pincode_error"></span>
											</label>
										</div>
									
									<div class="flex flex-row gap-8">
										<div class="w-full ">
											<label
												class="block mt-2 text-gray-700 dark:text-gray-400 font-semibold col-sm-6">City
												<select id="city" 
												class="form-control" style="height:auto">
													<option value="">Select your city</option>
													<option value="Ahmedabad">Ahmedabad</option>
													<option value="Rajkot">Rajkot</option>
													<option value="Surat">Surat</option>
													<option value="Gandhinagar">Gandhinagar</option>
											</select> <span id="city_error"></span>
											</label>
										</div>

										<div class="w-full">
											<label
												class="block mt-2 text-gray-700 dark:text-gray-400 font-semibold col-sm-6">State
												<select id="state" 
												class="form-control" style="height:auto">
													<option value="">Select your state</option>
													<option value="Gujarat">Gujarat</option>
													<option value="Rajasthan">Rajasthan</option>
													<option value="Punjab">Punjab</option>
													<option value="Goa">Goa</option>
											</select> <span id="state_error"></span>
											</label>
										</div>
									</div>

									
										
										<div class="row">
												<div class="col-sm-12">
													<div>
														<a href="javascript:void(0)"
															class="remove-item btn btn-sm btn-danger remove-social-media">Remove</a>
													</div>
												</div>
											</div>

									</div>
								</div>

							</div>

						</div>
					
				</div>


			

					<div>
						<a class="btn btn-success btn-sm" id="add-more" href="javascript:;" role="button"><i class="fa fa-plus"></i>
									Add address</a>
							</div>
							<#--end-->
							</#if>
				</div>
			</div>		
				 	<#if name??>
					<div class="row btn-margin form-row">

						<div class="col-sm-1  submit_btn">

							<input type="submit"
								class="btn btn-default btn-primary register_btn"  value="Submit">
						</div>
						<div class="col-sm-2">

							<a href="userlogin"
								class="btn btn-default btn-primary cancel">Cancel</a>
						</div>
					</div>
					<#else>
					<div class="row btn-margin form-row">

						<div class="col-sm-1  submit_btn">

							<input type="submit"
								class="btn btn-default btn-primary register_btn" id="register_button" value="Submit">
						</div>
						<div class="col-sm-2">

							<a href="userlogin"
								class="btn btn-default btn-primary btn_hide">Back To Login</a>
						</div>
					</div>
					</#if>
		</form>
	</div>

	<!-- address code ends -->

	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js">
		
	</script>
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>

	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.6.4/js/bootstrap-datepicker.js"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/select2/3.3.2/select2.js"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.6.3/js/bootstrap-select.js"></script>
	<script src="https://cdn.ckeditor.com/4.5.1/standard/ckeditor.js"></script>

	<script type="text/javascript" src="resources/js/plugin.js"></script>
	<script type="text/javascript" src="resources/js/customValidation.js"></script>
	<script>
		$(document).ready(function(){
			alert("js called");
				
						<#if userDetails??>		
						$("input[name=userGender][value=${userDetails.userGender}]").prop("checked",true);	
					
					<#list userDetails.userHobby?split(",") as hobby>
						<#if hobby=="Sports">
							$("input[name=userHobby][value=${hobby}]").prop("checked",true);
						</#if>
						<#if hobby =="Study">
							$("input[name=userHobby][value=${hobby}]").prop("checked",true);
						</#if>
						<#if hobby=="Music">
							$("input[name=userHobby][value=${hobby}]").prop("checked",true);
						</#if>
						<#if hobby=="Dance">
							$("input[name=userHobby][value=${hobby}]").prop("checked",true);
						</#if>
						</#list>
				</#if>
						
						
						<#if adminDetails??>		
						$("input[name=userGender][value=${adminDetails.userGender}]").prop("checked",true);	
					
					<#list adminDetails.userHobby?split(",") as hobby>
						<#if hobby=="Sports">
							$("input[name=userHobby][value=${hobby}]").prop("checked",true);
						</#if>
						<#if hobby =="Study">
							$("input[name=userHobby][value=${hobby}]").prop("checked",true);
						</#if>
						<#if hobby=="Music">
							$("input[name=userHobby][value=${hobby}]").prop("checked",true);
						</#if>
						<#if hobby=="Dance">
							$("input[name=userHobby][value=${hobby}]").prop("checked",true);
						</#if>
						</#list>
				</#if>
				
					});	
		</script>
	<script>
		$(document).ready(
				function() {

					$("#mail").change(
							function() {
								var mail = $("#mail").val();
								console.log(mail);
								$.ajax({
									type : 'GET',
									data : {
										email : mail
									},
									url : 'checkEmail',
									success : function(result) {
										console.log("success");
										if (result == "true") {
											console.log("condition true");
											$("#result_error").html(
													"Email already exist!!")
													.css("color", "red");
											$("#mail").css("border-bottom",
													"4px solid #F90A0A");
											$(".register_btn").prop("disabled",
													true);
										} else {
											console.log("condition false");
											$("#result_error").html("");
											$("#mail").css("border-bottom",
													"4px solid #34F458");
											$(".register_btn").prop("disabled",
													false);
										}
									}
								});
							});
							
							<#if addressList??>
					<#list addressList as addr>
							$("#state${addr?index} option[value=${addr.addState}]").attr('selected', 'selected');
							$("#city${addr?index} option[value=${addr.addCity}]").attr('selected', 'selected');
					</#list>
					<#else>
							
							$("#register_button").on("click",function(){

 				var counter=$(".panel .panel-body .panel-body").length;	
				for(var i=0;i<counter;i++){
					$(".panel .panel-body .panel-body label input[type=text]").eq(i).attr("name","address["+i+"].addStreet");
					$(".panel .panel-body .panel-body label input[type=text]").eq(i).attr("name","address["+i+"].addLandmark");
					$('.panel .panel-body .panel-body .flex .col-sm-4 .block option:contains("Ahmedabad")').parent().eq(i).attr("name","address["+i+"].addCity");
					$('.panel .panel-body .panel-body .flex .col-sm-4 .block option:contains("Gujarat")').parent().eq(i).attr("name","address["+i+"].addState");
					$(".panel .panel-body .panel-body .flex label input[type=number]").eq(i).attr("name","address["+i+"].addPincode");			
				}		
		});
		</#if>

				});
	</script>
	<script>
		$(document).ready(function() {	
		
		var parsed = new URL(location);
		var uname = parsed.searchParams.get("name");
		if (uname === 'ADD') {
			$(".btn_hide").hide();
			$(".header_tag").text("Add User");
			$(".register_btn").prop("value", "Add User");
			$("#image_preview").hide();
			$(".cancel").attr("href", "AdminHomePage")
			//$("#register_btn").attr("action","addUser?name=ADD")

		} 
		
		else if (uname === 'adminEdit') {
			$(".btn_hide").hide();
			$(".header_tag").text("Update Profile");
			$(".register_btn").prop("value", "Update");
			$("#mail").attr("readonly", "readonly");
			$("#pwd").attr("readonly", "readonly");
			$("#cpwd").attr("readonly", "readonly");
			$("#registration_form").attr("action", "UserEdit?name=adminEdit");
			$("#image_preview").css({
				"display" : "block",
				"max-width" : "170px",
				"max-height" : "170px"
			});
			$(".cancel").attr("href", "view")
				$("#add-more").on("click",function(){

 				var counter=$(".panel .panel-body .panel-body").length;	
				for(var i=0;i<counter;i++){
					$(".panel .panel-body .panel-body label input[type=text]").eq(i).attr("name","address["+i+"].addStreet");
					$(".panel .panel-body .panel-body label input[type=text]").eq(i).attr("name","address["+i+"].addLandmark");
					$('.panel .panel-body .panel-body .flex .col-sm-4 .block option:contains("Ahmedabad")').parent().eq(i).attr("name","address["+i+"].addCity");
					$('.panel .panel-body .panel-body .flex .col-sm-4 .block option:contains("Gujarat")').parent().eq(i).attr("name","address["+i+"].addState");
					$(".panel .panel-body .panel-body .flex label input[type=number]").eq(i).attr("name","address["+i+"].addPincode");			
				}		
		});
		} 
		
		else if (uname === 'userEdit') {
			$(".btn_hide").hide();
			$(".header_tag").text("Update Profile");
			$(".register_btn").prop("value", "Update");
			$("#mail").attr("readonly", "readonly");
			$("#registration_form").attr("action", "UserEdit?name=userEdit");
			$("#pwd").attr("readonly", "true");
			$("#cpwd").attr("readonly", "true");
			$("#image_preview").css({
				"display" : "block",
				"max-width" : "170px",
				"max-width" : "170px"
			});
			$(".cancel").attr("href", "userHome")
			$("#add-more").on("click",function(){

 				var counter=$(".panel .panel-body .panel-body").length;	
				for(var i=0;i<counter;i++){
					$(".panel .panel-body .panel-body label input[type=text]").eq(i).attr("name","address["+i+"].addStreet");
					$(".panel .panel-body .panel-body label input[type=text]").eq(i).attr("name","address["+i+"].addLandmark");
					$('.panel .panel-body .panel-body .flex .col-sm-4 .block option:contains("Ahmedabad")').parent().eq(i).attr("name","address["+i+"].addCity");
					$('.panel .panel-body .panel-body .flex .col-sm-4 .block option:contains("Gujarat")').parent().eq(i).attr("name","address["+i+"].addState");
					$(".panel .panel-body .panel-body .flex label input[type=number]").eq(i).attr("name","address["+i+"].addPincode");			
				}		
		});

		} else if (uname === 'admin') {
			$(".btn_hide").hide();
			$(".header_tag").text("Update Profile");
			$(".register_btn").prop("value", "Update");
			$("#mail").attr("readonly", "readonly");
			$("#pwd").attr("readonly", "true");
			$("#registration_form").attr("action", "UserEdit?name=admin");
			$("#cpwd").attr("readonly", "true");
			$("#image_preview").css({
				"display" : "block",
				"max-width" : "170px",
				"max-width" : "170px"
			});
			$(".cancel").attr("href", "adminHome")
			$("#add-more").on("click",function(){

 				var counter=$(".panel .panel-body .panel-body").length;	
				for(var i=0;i<counter;i++){
					$(".panel .panel-body .panel-body label input[type=text]").eq(i).attr("name","address["+i+"].addStreet");
					$(".panel .panel-body .panel-body label input[type=text]").eq(i).attr("name","address["+i+"].addLandmark");
					$('.panel .panel-body .panel-body .flex .col-sm-4 .block option:contains("Ahmedabad")').parent().eq(i).attr("name","address["+i+"].addCity");
					$('.panel .panel-body .panel-body .flex .col-sm-4 .block option:contains("Gujarat")').parent().eq(i).attr("name","address["+i+"].addState");
					$(".panel .panel-body .panel-body .flex label input[type=number]").eq(i).attr("name","address["+i+"].addPincode");			
				}		
		});
		}
		
		else if(!uname){
			$("#image_preview").hide();
		}
			});
		</script>
		
</body>
</html>