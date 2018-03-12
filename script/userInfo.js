(function($){

// USER INFO
	var retrievedObject = JSON.parse(localStorage.getItem('user'));

	$.ajax({
		url: 'http://localhost:8080/book/userinfo/'+retrievedObject.login,
		data: { },
		error: function(){
		    window.location.href = "../index.html";
		},
		success: getInfo,
		crossDomain: true,
		dataType: 'jsonp',
		jsonpCallback: 'getInfo',
			contentType: 'application/json',
			type: 'GET'
	});

	function getInfo(data){
		console.log(data);
		$("#logout #profile").text("Hello " +retrievedObject.name);

		$("#aboutInfo").append("<div class='infoW'><div class='infoHeader'>First name:</div><span>"+data.all[0]+"</span></div>"+
			"<div class='infoW'><div class='infoHeader'>Last name:</div><span>"+data.all[1]+"</span></div>"+
			"<div class='infoW'><div class='infoHeader'>Login:</div><span>"+data.all[2]+"</span></div>"+
			"<div class='infoW'><div class='infoHeader'>E-mail address:</div><span>"+data.all[3]+"</span></div>"+
			"<div class='infoW'><div class='infoHeader'>Telephone number:</div><span>"+data.all[4]+"</span></div>");
		$("#address").append("<div class='infoW'><div class='infoHeader'>Address line:</div><span>"+data.all[5]+"</span></div>"+
			"<div class='infoW'><div class='infoHeader'>Town / City</div><span>"+data.all[6]+"</span></div>"+
			"<div class='infoW'><div class='infoHeader'>Postcode</div><span>"+data.all[7]+"</span></div>");
		$("#nav").css({	"border-bottom" : "7px solid #e5e5e5" });
	}

	$.ajax({
		url: 'http://localhost:8080/book/lendedbook/'+retrievedObject.login,
		data: { },
		error: function(){
		    alert("Error loaned books")
		},
		success: Books,
		crossDomain: true,
		dataType: 'jsonp',
		jsonpCallback: 'Books',
			contentType: 'application/json',
			type: 'GET'
	});

	function Books (data){
		console.log(data.Books.length);
		for (var i = 0; i < data.Books.length; i++) {
			$("#loanedBook").append("<div class='infoW'><div class='infoHeader'>Name of book</div><span>"+data.Books[i]+"</span></div>");
		}
	}

})(jQuery);