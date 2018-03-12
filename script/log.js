(function($){


	$("#loginBtn").click(function(event){
		event.preventDefault();

		var login = $("#enterLogin").val(),
			password = $("#enterPassword").val();

		var error = false,
        	name = $("#enterLogin").val(),
        	password = $("#enterPassword").val();
        if(name.trim().length<2 || password.trim().length<2){
            $("#error").html("Invalid login or password.");
            error = true;
        }
        else {
            $("#error").html("");
        }

        $("#enterLogin").keyup(function(){
        	var name = $("#enterLogin").val();
            if(name.trim().length>1){
                $("#error").html("");
            }
        });
        $("#enterPassword").keyup(function(){
			var password = $("#enterPassword").val();
            if(password.trim().length>1){
                $("#error").html("");
            }
        });


		var settings = {
			  "async": true,
			  "crossDomain": true,
			  "url": "http://localhost:8080/login/login",
			  "method": "POST",
			  "headers":{
			  		"content-type": "application/json"
			},
			  "processData": false,
			  "data": "{\n\t\"username\":\""+login+"\",\n\t\"password\":\""+password+"\"\n}"	
		}

		$.ajax(settings).done(function (data) {
			if(error == false){
			var user = { 'login': $("#enterLogin").val(),
	        			  'name': data.name,
	        			  'token': data.token };
			localStorage.setItem('user', JSON.stringify(user));
			window.location.href = "../html/bookLend.html";
			}
			else {
				$("#error").text("Invalid login or password.");
			}
		}).fail(function(jqXHR, textStatus){
				$("#error").text("User does not exist.");
			});;
	});

	$("#resetBtn").click(function(){
		$("input").val("");
		$(".error").text("");
	});



	$("#sendBtn").click(function(event){
		event.preventDefault();

		// VERIFICATION

	    var emailRegex = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/,
			emailValue = $("#email").val(),
			nameRegex = /^([A-Za-z])$/,
			firstName = $("#firstName").val(),
			lastName = $("#lastName").val(),
			password = $("#password").val(),
			email = $("#email").val(),
			confirmPassword = $("#confirmPassword").val(),
			login = $("#login").val(),
			addressLine = $("#addressLine").val(),
			townCity = $("#townCity").val(),
			postcode = $("#postcode").val(),
			phoneNumberRegex = /^(\d{9,20})$/,
			phoneNumber = $("#phoneNumber").val();
		
		// SIGN UP REQUEST
			var settings = {
		  		"async": true,
		  		"crossDomain": true,
		  		"url": "http://localhost:8080/login/registration",
		  		"method": "POST",
		  		"headers": {
		    		"content-type": "application/json",
	   			},
		  		"processData": false,
		  		"data": "{\n\t\"name\":\""+firstName+"\",\n\t\"surename\":\""+lastName+"\",\n\t\"username\":\""+login+
		  		"\",\n\t\"password\":\""+password+"\",\n\t\"phone\":\""+phoneNumber+"\",\n\t\"email\":\""+email+
		  		"\",\n\t\"address\":\""+addressLine+"\",\n\t\"city\":\""+townCity+"\",\n\t\"postcode\":\""+postcode+"\"\n}"
			}
			
		$.ajax(settings).done(function (data) {
			var error = false;
			if(emailRegex.test(emailValue) == false){	
				$(".emailErr").text("Invalid email address.");
				error = true;
			}
			else {
				$(".emailErr").text("");
			}

			if((nameRegex.test(firstName) == false || nameRegex.test(lastName) == false) && (firstName.length < 2) || lastName.length < 2){
				$(".nameErr").text("Invalid first or last name. Must contain at least 2 characters.");
				error = true;
			}
			else {
				$(".nameErr").text("");
			}

			if(password.length < 8 || password != confirmPassword){
				$(".passwordErr").text("Password must contain at least 8 characters and match the confirm password.");
				error = true;
			}
			else {
				$(".passwordErr").text("");
			}

			if(phoneNumberRegex.test(phoneNumber) == false) {
				$(".phoneNumberErr").text("Phone number must contain at least 9 numbers.");
				error = true;
			}
			else {
				$(".phoneNumberErr").text("");
			}

			if(login.length < 4) {
				$(".loginErr").text("Login must contain at least 4 characters.");
				error = true;
			}
			else {
				$(".loginErr").text("");
			}

			if(addressLine.length < 2 || townCity.length < 2 || postcode.length < 3) {
				$(".addressErr").text("Invalid address.");
				error = true;
			}
			else 
				$(".addressErr").text("");
			
			if(error == false) 
				$("#overlay").fadeIn(150);
			
		}).fail(function(data){
			alert("Registation failed. Try again");
			$("input").val("");
		});

	});

	

})(jQuery);