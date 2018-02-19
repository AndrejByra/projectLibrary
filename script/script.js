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
    var emailRegex = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/,
		emailValue = $("#email").val();
	console.log(emailValue);

		if(emailRegex.test(emailValue) == true)
			$("#email").css({border: "2px solid green", borderRadius: 5});
		else 
			$("#email").css({border: "2px solid red", borderRadius: 0});
	});

	$("#resetBtn").click(function(){
		$("input").val("");
	});

	// CSS
	var loginWidth = $("#logo").css("width");
	$("#logInBox").css({width: loginWidth});

	var logoHeight = $("#logo img").height(),
		infoHeight = $("#logInInfo").height();
	infoHeight += logoHeight + 20;

	$("#logInBox").css({height : infoHeight});
	
})(jQuery);