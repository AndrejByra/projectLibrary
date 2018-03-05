(function($){

	// LOG IN VERIFICATION
	$("#loginBtn").click(function(event){
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
    });

	// SIGN UP VERIFICATION
	$("#sendBtn").click(function(){
		var error = false;
	    var emailRegex = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/,
			emailValue = $("#email").val(),
			nameRegex = /^([A-Za-z]{2,})$/,
			firstName = $("#firstName").val(),
			lastName = $("#lastName").val(),
			password = $("#password").val(),
			confirmPassword = $("#confirmPassword").val(),
			login = $("#login").val(),
			addressLine = $("#addressLine").val(),
			townCity = $("#townCity").val(),
			postcode = $("#postcode").val(),
			phoneNumberRegex = /^(\d{9,20})$/,
			phoneNumber = $("#phoneNumber").val();

		if(emailRegex.test(emailValue) == false){	
			$(".emailErr").text("Invalid email address.")
			error = true;
		}
		else {
			$(".emailErr").text("");
		}

		if(nameRegex.test(firstName) == false || nameRegex.test(lastName) == false){
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

		if(login.length < 4)
			$(".loginErr").text("Login must contain at least 4 characters.");
		else {
			$(".loginErr").text("");
		}

		if(addressLine.length < 2 || townCity.length < 2 || postcode.length < 3)
			$(".addressErr").text("Invalid address.")
		else {
			$(".addressErr").text("");
		}

	});

	$("#resetBtn").click(function(){
		$("input").val("");
		$(".error").text("");
	});

	// CSS
	var loginWidth = $("#logo").css("width");
	$("#logInBox").css({width: loginWidth});

	var logoHeight = $("#logo img").height(),
		infoHeight = $("#logInInfo").height();
	infoHeight += logoHeight + 20;
	$("#logInBox").css({height : infoHeight});

	$(".dropdown").hover(function(){
		$(".dropdown-content").stop().slideToggle();
	});

	// SCROLL
	$("#leftNav a").click(function() {
		console.log("#"+$(this).text());
	    $('html, body').animate({
	        scrollTop: $("#"+$(this).text()).offset().top
	    }, 1000);
	});

	$(window).scroll(function(){
        if($(window).scrollTop() > $(window).height()/2+40){
            $("#navLogo img").show();
        }
        else {
            $("#navLogo img").hide();
        }
    });

	$(window).scroll(function(){
        if($(window).scrollTop() > $(window).height())
            $(".indexNav").css({"background-color":"rgba(153,54,54,1)"});

        else 
            $(".indexNav").css({"background-color":"transparent"});
    });

})(jQuery);