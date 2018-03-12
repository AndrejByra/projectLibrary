(function($){

	$("#logoutBtn, .logoutBtn").click(function(){
		var retrievedObject = JSON.parse(localStorage.getItem('user'));
		var user = { 'login': '',
	        	     'name': '',
	        	     'token': '' };
	    window.location.href = "../index.html";
		localStorage.setItem('user', JSON.stringify(user));
		
		$.ajax({
			url: 'http://localhost:8080/login/logout/' +retrievedObject.token,
			data: { },
			error: function(){
				alert("Error logout");
			    window.location.href = "../index.html";
			},
			crossDomain: true,
			dataType: 'jsonp',
			contentType: 'application/json',
			type: 'GET'
		});
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
		console.log(bookName);

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

			console.log((data.all[0]).trim());
			var status = "";

			if(data.all[5] == 0)
				status = "<div id='available'>Available</div><div id='borrow'><button>Borrow</button></div>";
			else 
				status = "<div id='notAvailable' class=''>Not available</div>";

			$("#bookWrapper").append("<div id='cover'><img src='../img/books/"+data.all[0]+".jpg'></div>");
			$("#bookWrapper").append("<div id='aboutBook' class=''>"+
				"<div id='nameofbook'>"+data.all[0]+"</div>"+
				"<div id='author'>Author: "+data.all[1]+"</div>"+
				"<div id='isbn'><span>ISBN: </span>"+data.all[2]+"</div>"+
				"<div id='genre'><span>Genre: </span>"+data.all[3]+"</div>"+
				"<div id='detailofbook'>"+data.all[4]+"</div>"+
				status+"</div>");
		}

		$.ajax({
			url: 'http://localhost:8080/book/time/' +bookName,
			data: { },
			error: function(){
				alert("Error time");
			},
			success: getDate,
			crossDomain: true,
			dataType: 'jsonp',
			jsonpCallback: 'getDate',
				contentType: 'application/json',
				type: 'GET'
		});

		function getDate(data){
			$("#bookWrapper").find("#notAvailable").empty();

			var countDownDate = new Date(data.date[0]).getTime();

			var x = setInterval(function() {
			    var now = new Date().getTime();
			    var distance = countDownDate - now;
				    
			    var days = Math.floor(distance / (1000 * 60 * 60 * 24));
			    var hours = Math.floor((distance % (1000 * 60 * 60 * 24)) / (1000 * 60 * 60));
			    var minutes = Math.floor((distance % (1000 * 60 * 60)) / (1000 * 60));
			    var seconds = Math.floor((distance % (1000 * 60)) / 1000);
				
			    $("#bookWrapper").find("#notAvailable").text("Available in " + days + " days");

			    if (distance < 0) {
		 		    clearInterval(x);
		    		$("#demo").text("EXPIRED");
				}
			
			}, 1000);

			console.log(data.date[0]);
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

	$("#nav #profile").click(function(){
		$("#books").hide();
		$("#bookWrapper").hide();
		$("#infoWrapper").delay(500).fadeIn(100);
		$("#nav").css({	"border-bottom" : "7px solid #e5e5e5" });
		$("#userImg").delay(500).fadeIn(200);
	});

	$("#smallDropDown #profile").click(function(){
		$("#books").hide();
		$("#bookWrapper").hide();
		$("#infoWrapper").delay(500).fadeIn(100);
		$("#nav").css({	"border-bottom" : "7px solid #e5e5e5" });
	});



})(jQuery);