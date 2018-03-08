(function($){

	var countDownDate = new Date("2018-06-06 16:26:38.0").getTime();

		var x = setInterval(function() {
		    var now = new Date().getTime();
		    var distance = countDownDate - now;
			    
		    var days = Math.floor(distance / (1000 * 60 * 60 * 24));
		    var hours = Math.floor((distance % (1000 * 60 * 60 * 24)) / (1000 * 60 * 60));
		    var minutes = Math.floor((distance % (1000 * 60 * 60)) / (1000 * 60));
		    var seconds = Math.floor((distance % (1000 * 60)) / 1000);
			
		    $("#demo").text("Available in " + days + " days " + hours + "h "
		    + minutes + "m " + seconds + "s ");

		    if (distance < 0) {
     		    clearInterval(x);
        		$("#demo").text("EXPIRED");
    		}
		
		}, 1000);

	$("#logoutBtn").click(function(){
		var user = { 'login': '',
	        	     'name': '' };
	    window.location.href = "../index.html";
		localStorage.setItem('user', JSON.stringify(user));
	});
	

	// BOOK LENDING
	$("#bookWrapper").hide();
	$("#books").hide();

	$("#all, .dropbtn").click(function(event){

		event.preventDefault();
		$("#books").empty().show();
		$("#bookWrapper").empty().hide();
		$("#infoWrapper").hide();
		$("#nav").css({	"border-bottom" : "0" });
		$("#userImg").hide();


		$.ajax({
			url: 'http://localhost:8080/book/allbook',
			data: { },
			error: function(){
				alert("Error all book");
			},
			success: getBooks,
			crossDomain: true,
			dataType: 'jsonp',
			jsonpCallback: 'getBooks',
				contentType: 'application/json',
				type: 'GET'
		});

		function getBooks(data){
			var num = 1;
			for (var i = 0; i < data.name.length; i++){
				// $("#books").append("<div class='book book"+num+"'><span class='bookName"+num+"'>"+data.name[i]+"</span></div>");
 				$("#books").append("<div class='book book"+num+" "+data.name[i]+"'>"+
 					"<img src='../img/books/"+data.name[i]+".jpg'>"+
 					"<div class='bookCover'><span class='bookName'>"+data.name[i]+"</span>"+
 					"<button class='btnLend'>About</button></div></div>");
				num++;
			}
		}

	});

	$(".thisBook").click(function(event){

		event.preventDefault();
		$("#books").empty().show();
		$("#bookWrapper").empty().hide();
		$("#infoWrapper").hide();
		$("#nav").css({	"border-bottom" : "0" });
		$("#userImg").hide();

		var bookId = ((this).id);
		$.ajax({
		url: 'http://localhost:8080/book/genre/' +bookId,
		data: { },
		error: function(){
			alert("Error genre");
		},
		success: getGenres,
		crossDomain: true,
		dataType: 'jsonp',
		jsonpCallback: 'getGenres',
			contentType: 'application/json',
			type: 'GET'
		});

		function getGenres(data){
			var num = 1;
				
			data.name.forEach(function(element){
				$("#books").append("<div class='book book"+num+" "+element+"'>"+
					"<img src='../img/books/"+element+".jpg'>"+
					"<div class='bookCover'><span class='bookName'>"+element+"</span>"+
					"<button class='btnLend'>About</button></div></div>");
				num++;
			});
		}

	});	

	$("#books").on("click", "button", function(){
		
		$("#bookWrapper").empty();
		var bookName = ($(this).prev().text());
		$("#books").fadeOut(500);


		$.ajax({
			url: 'http://localhost:8080/book/info/' +bookName,
			data: { },
			error: function(){
				alert("Error book info");
			},
			success: getInfo,
			crossDomain: true,
			dataType: 'jsonp',
			jsonpCallback: 'getInfo',
				contentType: 'application/json',
				type: 'GET'
		});

		function getInfo(data){

			// Set the date we're counting down to
			
			var status = "";

			if(data.all[6] == 0)
				status = "<div id='available'>Available</div><div id='borrow'><button>Borrow</button></div>";
			else 
				status = "<div id='notAvailable'>Not available</div>";

			$("#bookWrapper").append("<div id='cover'><img src='../img/books/"+data.all[0]+".jpg'></div>");
			$("#bookWrapper").append("<div id='aboutBook'>"+
				"<div id='nameofbook'>"+data.all[0]+"</div>"+
				"<div id='author'>Author: "+data.all[1]+"</div>"+
				"<div id='isbn'><span>ISBN: </span>"+data.all[2]+"</div>"+
				"<div id='genre'><span>Genre: </span>"+data.all[3]+"</div>"+
				"<div id='detailofbook'>"+data.all[4]+"</div>"+
				status+"</div>");
		}
	
		$("#bookWrapper").delay(1000).fadeIn(500);
	});

	// INDEX BOOKS
	$.ajax({
		url: 'http://localhost:8080/book/allbook',
		data: { },
		error: function(){
			alert("Error all book index");
		},
		success: getBooks,
		crossDomain: true,
		dataType: 'jsonp',
		jsonpCallback: 'getBooks',
			contentType: 'application/json',
			type: 'GET'
	});

	function getBooks(data){
		for (var i = 0; i < data.name.length; i+=5){
			$("#indexBooks").append("<div class='book "+data.name[i]+"'>"+
				"<img src='img/books/"+data.name[i]+".jpg'></div>");
		}
	}

	// var retrievedObject = JSON.parse(localStorage.getItem('user'));

	// USER INFO
	// $.ajax({
	// 	url: 'http://localhost:8080/book/userinfo/'+retrievedObject.login,
	// 	data: { },
	// 	error: function(){
	// 		alert("Error user info");
	// 	},
	// 	success: getInfo,
	// 	crossDomain: true,
	// 	dataType: 'jsonp',
	// 	jsonpCallback: 'getInfo',
	// 		contentType: 'application/json',
	// 		type: 'GET'
	// });

	// function getInfo(data){
	// 	$("#profile").text("Hello " +retrievedObject.name);
	// 	$("#aboutInfo").append("<div class='infoW'><div class='infoHeader'>First name:</div><span>"+data.all[0]+"</span></div>"+
	// 		"<div class='infoW'><div class='infoHeader'>Last name:</div><span>"+data.all[1]+"</span></div>"+
	// 		"<div class='infoW'><div class='infoHeader'>Login:</div><span>"+data.all[2]+"</span></div>"+
	// 		"<div class='infoW'><div class='infoHeader'>E-mail address:</div><span>"+data.all[3]+"</span></div>"+
	// 		"<div class='infoW'><div class='infoHeader'>Telephone number:</div><span>"+data.all[4]+"</span></div>");
	// 	$("#address").append("<div class='infoW'><div class='infoHeader'>Address line:</div><span>"+data.all[5]+"</span></div>"+
	// 		"<div class='infoW'><div class='infoHeader'>Town / City</div><span>"+data.all[6]+"</span></div>"+
	// 		"<div class='infoW'><div class='infoHeader'>Postcode</div><span>"+data.all[7]+"</span></div>");
	// 	$("#nav").css({	"border-bottom" : "7px solid #e5e5e5" });
	// }

	$("#profile").click(function(){
		$("#books").hide();
		$("#bookWrapper").hide();
		$("#infoWrapper").delay(500).fadeIn(100);
		$("#nav").css({	"border-bottom" : "7px solid #e5e5e5" });
		$("#userImg").delay(500).fadeIn(200);
	});


})(jQuery);