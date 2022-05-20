$(document).ready(function() {

	$("#name_error").hide();
	$("#email_error").hide();
	$("#password_error").hide();
	$("#confirm_password_error").hide();
	$("#contact_error").hide();
	$("#radio_error").hide();
	$("#check_error").hide();
	$("#image_error").hide();
	$("#pincode_error").hide();
	$("#landmark_error").hide();
	$("#street_error").hide();
	$("#city_error").hide();
	$("#state_error").hide();
	$("#dob_error").hide();

	var name_error = false;
	var email_error = false;
	var password_error = false;
	var confirm_password_error = false;
	var contact_error = false;
	var radio_error = false;
	var check_error = false;
	var image_error = false;
	var pincode_error = false;
	var landmark_error = false;
	var street_error = false;
	var city_error = false;
	var state_error = false;
	var dob_error = false;
	//var selector;

	//Other field validation
	$("#name").focusout(function() {
		$("#name_error").hide();
		$("#name").css("border-bottom", "none");
	});
	$("#name").keyup(function() {
		check_name();
	});
	$("#name").focus(function() {
		check_name();
	});

	$("#mail").focusout(function() {
		$("#email_error").hide();
		$("#mail").css("border-bottom", "none");
	});
	$("#mail").keyup(function() {
		check_email();
	});
	$("#mail").focus(function() {
		check_email();
	});

	$("#pwd").focusout(function() {
		$("#password_error").hide();
		$("#pwd").css("border-bottom", "none");
	});
	$("#pwd").keyup(function() {
		check_password();
	});
	$("#pwd").focus(function() {
		check_password();
	});

	$("#cpwd").focusout(function() {
		$("#confirm_password_error").hide();
		$("#cpwd").css("border-bottom", "none");
	});
	$("#cpwd").keyup(function() {
		check_confirm_password();
	});
	$("#cpwd").focus(function() {
		check_confirm_password();
	});

	$("#contact").focusout(function() {
		$("#contact_error").hide();
		$("#contact").css("border-bottom", "none");
	});
	$("#contact").keyup(function() {
		check_contact();
	});
	$("#contact").focus(function() {
		check_contact();
	});


	/*$('input[name="userGender"]').change(function() {
		check_radio();
	});
	$('input[name="userGender"]').focusout(function() {
		$("#radio_error").hide();
	});*/

	$('input[name="userHobby"]').change(function() {
		check_checkbox();
	});
	$('input[name="userHobby"]').focusout(function() {
		$("#check_error").hide();
	});

	$("#img").change(function() {
		check_file();
		img_preview(this);
	});
	$("#img").focusout(function() {
		$("#image_error").hide();
	});


	$("#dob").change(function() {
		check_date();
	});
	$("#dob").focusout(function() {
		$("#dob_error").hide();
	});


	$('a#add-more').cloneData({
		mainContainerId: 'main-container', // Main container Should be ID
		cloneContainer: 'container-item', // Which you want to clone
		removeButtonClass: 'remove-item', // Remove button for remove cloned HTML
		removeConfirm: true, // default true confirm before delete clone item
		removeConfirmMessage: 'Are you sure want to delete?', // confirm delete message
		//append: '<a href="javascript:void(0)" class="remove-item btn btn-sm btn-danger remove-social-media">Remove</a>', // Set extra HTML append to clone HTML
		minLimit: 1, // Default 1 set minimum clone HTML required
		/* 			maxLimit : 5, // Default unlimited or set maximum limit of clone HTML
		 */
		defaultRender: 1,
		afterRender: function() {

			for (var i = 0; i < $(".container-item").length; i++) {
				var selector = `.container-item[data-index="${i}"]`;

				setAllCallbacks(selector);

			}
		},
	});

	//Address field validation
	var defaultSelector = '.container-item[data-index="0"]';
	setAllCallbacks(defaultSelector);

	function setAllCallbacks(selector) {

		//street
		$(selector + " #street").focusout(function() {
			$(selector + " #street_error").hide();
			$(selector + " #street").css("border-bottom", "1px solid #ccc");
			check_street(selector);
		});
		$(selector + " #street").keyup(function() {
			check_street(selector);
		});
		$(selector + " #street").focus(function() {
			check_street(selector);
		});

		//pincode
		$(selector + " #pincode").focusout(function() {
			$(selector + " #pincode_error").hide();
			$(selector + " #pincode").css("border-bottom", "1px solid #ccc");
			check_pincode(selector);

		});
		$(selector + " #pincode").keyup(function() {
			check_pincode(selector);
		});
		$(selector + " #pincode").focus(function() {
			check_pincode(selector);
		});

		//landmark
		$(selector + " #landmark").focusout(function() {
			$(selector + " #landmark_error").hide();
			$(selector + " #landmark").css("border-bottom", "1px solid #ccc");
			check_landmark(selector);

		});
		$(selector + " #landmark").keyup(function() {
			check_landmark(selector);
		});
		$(selector + " #landmark").focus(function() {
			check_landmark(selector);
		});

		//city
		$(selector + " #city").change(function() {
			check_city(selector);
		});
		$(selector + " #city").focusout(function() {
			$(selector + " #city_error").hide();
			//check_city(defaultSelector);

		});

		//state
		$(selector + " #state").change(function() {
			check_state(selector);
		});
		$(selector + " #state").focusout(function() {
			$(selector + " #state_error").hide();

		});

	}

	//Dynamic address field validation
	function checkAllAddress() {
		for (var i = 0; i < $(".container-item").length; i++) {
			var selector = `.container-item[data-index="${i}"]`;

			check_street(selector);
			check_landmark(selector);
			check_pincode(selector);
			check_city(selector);
			check_state(selector);
		}
	}
	// validation for pincode
	function check_pincode(selector) {

		var pattern = /^[0-9]+$/;
		var pincode = $(selector + " #pincode").val();
		if (pincode.length == 0) {
			$(selector + " #pincode_error").html("pincode can not be empty!!").css("color", "red");
			$(selector + " #pincode_error").show();
			$(selector + " #pincode").css("border-bottom", "4px solid #F90A0A");
			pincode_error = true;
		} else if (!pattern.test(pincode)) {
			$(selector + " #pincode_error").html("Pincode contain only numbers").css("color", "red");
			$(selector + " #pincode_error").show();
			$(selector + " #pincode").css("border-bottom", "4px solid #F90A0A");
			pincode_error = true;
		} else {
			$(selector + " #pincode_error").hide();
			$(selector + " #pincode").css("border-bottom", "4px solid #34F458");
		}
	}

	function check_landmark(selector) {

		var landmark = $(selector + " #landmark").val();
		if (landmark.length == 0) {
			$(selector + " #landmark_error").html("landmark can not be empty!!").css("color", "red");
			$(selector + " #landmark_error").show();
			$(selector + " #landmark").css("border-bottom", "4px solid #F90A0A");
			landmark_error = true;
		} else {
			$(selector + " #landmark_error").hide();
			$(selector + " #landmark").css("border-bottom", "4px solid #34F458");
		}
	}
	function check_street(selector) {
		var street = $(selector + " #street").val();
		if (street.length == 0) {
			$(selector + " #street_error").html("Street can not be empty!!").css("color", "red");
			$(selector + " #street_error").show();
			$(selector + " #street").css("border-bottom", "4px solid #F90A0A");
			street_error = true;
		} else {
			$(selector + " #street_error").hide();
			$(selector + " #street").css("border-bottom", "4px solid #34F458");
		}

	}
	function check_state(selector) {
		var state = $(selector + " #state").val();
		if (state == "0") {
			$(selector + " #state_error").html("Please select State!!").css("color", "red");
			$(selector + " #state_error").show();
			state_error = true;
		} else {
			$(selector + " #state_error").hide();
		}
	}
	function check_city(selector) {
		var city = $(selector + " #city").val();
		if (city == "0") {
			$(selector + " #city_error").html("Please select City!!").css("color", "red");
			$(selector + " #city_error").show();
			city_error = true;
		} else {
			$(selector + " #city_error").hide();
		}
	}

	function check_name() {
		var pattern = /^[a-zA-Z\s]+$/;
		var name = $("#name").val();
		if (name.length == 0) {
			$("#name_error").html("Name can not be empty!!").css("color", "red");
			$("#name_error").show();
			$("#name").css("border-bottom", "4px solid #F90A0A");
			name_error = true;
		} else if (!pattern.test(name)) {
			$("#name_error").html("Should contain only Characters").css("color", "red");
			$("#name_error").show();
			$("#name").css("border-bottom", "4px solid #F90A0A");
			name_error = true;
		} else {
			$("#name_error").hide();
			$("#name").css("border-bottom", "4px solid #34F458");
		}
	}


	function check_email() {
		var pattern = /^([\w-\.]+@([\w-]+\.)+[\w-]{2,4})?$/;
		var email = $("#mail").val();
		if (email.length == 0) {
			$("#email_error").html("Email can not be empty!!").css("color", "red");
			$("#email_error").show();
			$("#mail").css("border-bottom", "4px solid #F90A0A");
			email_error = true;
		} else if (!pattern.test(email)) {
			$("#email_error").html("Invalid Email!!").css("color", "red");
			$("#email_error").show();
			$("#mail").css("border-bottom", "4px solid #F90A0A");
			email_error = true;
		} else {
			$("#email_error").hide();
			$("#mail").css("border-bottom", "4px solid #34F458");
			email_error = false;
		}

	}
	function check_password() {
		var password_length = $("#pwd").val().length;
		if (password_length == 0) {
			$("#password_error").html("Password can not be empty!!").css("color", "red");
			$("#password_error").show();
			$("#pwd").css("border-bottom", "4px solid #F90A0A");
			password_error = true;
		} else {
			$("#password_error").hide();
			$("#pwd").css("border-bottom", "4px solid #34F458");
			password_error = false;
		}
	}

	function check_confirm_password() {
		var password = $("#pwd").val();
		var retype_password = $("#cpwd").val();
		if (retype_password === '') {
			$("#confirm_password_error").html("confirm password can not be empty!!").css("color", "red");
			$("#confirm_password_error").show();
			$("#cpwd").css("border-bottom", "4px solid #F90A0A");
			confirm_password_error = true;
		}
		else if (password !== retype_password) {
			$("#confirm_password_error").html("Passwords Did not Matched").css("color", "red");
			$("#confirm_password_error").show();
			$("#cpwd").css("border-bottom", "4px solid #F90A0A");
			confirm_password_error = true;
		} else {
			$("#confirm_password_error").hide();
			$("#cpwd").css("border-bottom", "4px solid #34F458");
		}
	}

	function check_contact() {
		var contact_pattern = /([0-9]{10})|(\([0-9]{3}\)\s+[0-9]{3}\-[0-9]{4})/;
		var contact = $("#contact").val();
		var contact_length = $("#contact").val().length;
		if (contact === '') {
			$("#contact_error").html("contact can not be empty!!").css("color", "red");
			$("#contact_error").show();
			$("#contact").css("border-bottom", "4px solid #F90A0A");
			contact_error = true;
		}
		else if (contact_length < 10 || contact_length > 10) {
			$("#contact_error").html("only 10 digits are allowed!!").css("color", "red");
			$("#contact_error").show();
			$("#contact").css("border-bottom", "4px solid #F90A0A");
			contact_error = true;
		} else if (!contact_pattern.test(contact)) {
			$("#contact_error").html("only digits are allowed!!").css("color", "red");
			$("#contact_error").show();
			$("#contact").css("border-bottom", "4px solid #F90A0A");
			contact_error = true;
		}
		else {
			$("#contact_error").hide();
			$("#contact").css("border-bottom", "4px solid #34F458");
		}
	}

	function check_radio() {
		if ($('input[name="userGender"]:checked').length == 0) {
			console.log("one must selected");
			$("#radio_error").html("one input must be selected!!").css("color", "red");
			$("#radio_error").show();
			radio_error = true;
		}
		else {
			$("#radio_error").hide();
		}
	}

	function check_checkbox() {
		if ($("input:checkbox").filter(":checked").length == 0) {
			console.log("one must selected");
			$("#check_error").html("one input must be selected!!").css("color", "red");
			$("#check_error").show();
			check_error = true;
		} else {
			$("#check_error").hide();
		}
	}

	function check_file() {
		var file = $("#img").val();
		var fileType = file.split('.').pop().toLowerCase();
		 if ($.inArray(fileType, ['png', 'jpg', 'jpeg']) == -1) {
			$("#image_error").html("Please select valid file!!").css("color", "red");
			$("#image_error").show();
			$("#image_preview").css("display", "none");
			image_error = true;
		}
	}	
	

	function img_preview(input) {
		if (input.files && input.files[0]) {
			var file = new FileReader();
			file.onload = function(e) {
				$("#image_preview").attr('src', e.target.result).css({ "display": "block", "max-width": "170px", "max-height": "170px" });
			}
			file.readAsDataURL(input.files[0]);
		} 
	}

	function check_date() {

		var date = $("#dob").val();
		if (date.length == 0) {
			$("#dob_error").html("Date of Birth can not be empty!!").css("color", "red");
			$("#dob_error").show();
			dob_error = true;
		} else {
			$("#dob_error").hide();
		}
	}
	
	function check_result(){
		var value = $("#result_error").val();
		if(value.length == 0){
			result_error = true;
		}
	}


	$("#registration_form").submit(function() {
		name_error = false;
		email_error = false;
		password_error = false;
		confirm_password_error = false;
		contact_error = false;
		radio_error = false;
		check_error = false;
		image_error = false;
		pincode_error = false;
		landmark_error = false;
		street_error = false;
		city_error = false;
		state_error = false;
		dob_error = false;
		result_error= false;
	

		check_name();
		check_email();
		check_password();
		check_confirm_password();
		check_contact();
		check_radio();
		check_checkbox();
		check_file();
		checkAllAddress();
		check_date();
		check_result();	

		if (name_error === false && email_error === false && password_error === false && confirm_password_error === false && contact_error === false &&
			radio_error === false && check_error === false &&
			image_error === false && pincode_error === false && landmark_error === false && street_error === false && state_error === false && city_error === false && dob_error === false && result_error === true ) {
			alert("Registration Successfull");
			return true;
		}else {
			alert("Please Fill the form Correctly");
			return false;
		}
	});

	
});