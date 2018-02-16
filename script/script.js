(function($){

	var loginWidth = $("#logo").css("width");
	$("#logInBox").css({width: loginWidth});

	var logoHeight = $("#logo img").height(),
		infoHeight = $("#logInInfo").height();
	infoHeight += logoHeight + 20;

	$("#logInBox").css({height : infoHeight});

	
})(jQuery);