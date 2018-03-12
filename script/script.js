(function($){

	$("#signupBtn").click(function(){
		window.location.href = "../html/signup.html";
	});

	$(".succesBtn").click(function(){
		window.location.href = "../html/login.html";
	});

	// CSS
	var loginWidth = $("#logo").css("width");
	$("#logInBox").css({width: loginWidth});

	var logoHeight = $("#logo img").height(),
		infoHeight = $("#logInInfo").height();
	infoHeight += logoHeight + 20;
	$("#logInBox").css({height : infoHeight});

	$("#library").hover(function(){
		$(".contentLibrary").stop().slideToggle();
	});
	$("#logout").hover(function(){
		$(".contentLogout").stop().slideToggle();
	});

	// SCROLL
	$("#leftNav a").click(function() {
		console.log("#"+$(this).text());
	    $('html, body').animate({
	        scrollTop: $("#"+$(this).text()).offset().top
	    }, 1000);
	});

	$("#indexDropDownContent a").click(function() {
		console.log("#"+$(this).text());
	    $('html, body').animate({
	        scrollTop: $("#"+$(this).text()).offset().top
	    }, 1000);
	});

	$(window).scroll(function(){
        if($(window).scrollTop() > $(window).height()/2+40){
            $("#navLogo img").stop().fadeIn(200);
        }
        else {
            $("#navLogo img").stop().hide();
        }
    });

	$(window).scroll(function(){
        if($(window).scrollTop() > $(window).height())
            $(".indexNav").css({"background-color":"rgba(153,54,54,1)"});
        else 
            $(".indexNav").css({"background-color":"transparent"});
    });

	$(window).scroll(function(){
        if($(window).scrollTop() > $(window).height())
            $("#indexDropDown").css({"background-color":"rgba(153,54,54,1)"});
        else 
            $("#indexDropDown").css({"background-color":"transparent"});
    });


    $(".menuBtn").click(function(){
    	$("#smallDropDownContent").stop().slideToggle(200);
    });

    $("#libraryDropDownBtn").click(function(){
    	$("#libraryDropDownContent").stop().slideToggle(200);
    });

	$(".menuBtn").click(function(){
    	$("#indexDropDownContent").stop().slideToggle(200);
    });


})(jQuery);