(function($){



	$("#all, .dropbtn").click(function(event){

		event.preventDefault();
		$("#books").empty().show();
		$("#bookWrapper").empty().hide();

		$.ajax({
			url: 'http://localhost:8080/book/allbook',
			data: { },
			error: function(){
				alert("Error");
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

		var bookId = ((this).id);
		$.ajax({
		url: 'http://localhost:8080/book/genre/' +bookId,
		data: { },
		error: function(){
			alert("Error");
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
				alert("Error");
			},
			success: getInfo,
			crossDomain: true,
			dataType: 'jsonp',
			jsonpCallback: 'getInfo',
				contentType: 'application/json',
				type: 'GET'
		});

		function getInfo(data){
			console.log(data.all[6]);
			var status = "";

			if(data.all[6] == 0)
				status = "<div id='available'>Available</div><div id='borrow'><button>Borrow</button></div>";
			else 
				status = "<div id='notAvailable'>Not available</div>";
			console.log(status);

			$("#bookWrapper").append("<div id='cover'><img src='../img/books/"+data.all[0]+".jpg'></div>");
			$("#bookWrapper").append("<div id='aboutBook'>"+
				"<div id='nameofbook'>"+data.all[1]+"</div>"+
				"<div id='author'>Author: "+data.all[2]+"</div>"+
				"<div id='isbn'><span>ISBN: </span>"+data.all[3]+"</div>"+
				"<div id='genre'><span>Genre: </span>"+data.all[4]+"</div>"+
				"<div id='detailofbook'>"+data.all[5]+"</div>"+
				status+"</div>");
		}
	
		$("#bookWrapper").delay(1000).fadeIn(500);
	});

	// INDEX BOOKS
	$.ajax({
		url: 'http://localhost:8080/book/allbook',
		data: { },
		error: function(){
			alert("Error");
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
		for (var i = 0; i < data.name.length; i+=2){
			// $("#books").append("<div class='book book"+num+"'><span class='bookName"+num+"'>"+data.name[i]+"</span></div>");
				$("#indexBooks").append("<div class='book book"+num+" "+data.name[i]+"'>"+
					"<img src='img/books/"+data.name[i]+".jpg'></div>");
			num++;
		}
	}

})(jQuery);
		// $.ajax({
		//     type: 'GET',
		//     url: 'localhost:8080/book/author/' +bookName,
		//     success: function(data) {
		//     	console.log(data);
		//     	$("#aboutBook").prepend("<div id='author'>Author: "+data.name+"</div>");
		//     },
		//     error: function(event) {
		//         console.log(event.statusText);
		//     },
		//     jsonp: 'jsonp'

		// });

	// var book = {
	// 	genre: bookId
	// };

	// var jsonBook = JSON.stringify(book);

	// $.ajax({
	// url: "http://localhost:8080/book/genres",
	// type: "POST",
	// processData: false,
	// data: jsonBook,
	// success: getGenre,

	// error: function(){
	// 	alert("Error genre.");
	// },
	// dataType: 'json',
	// contentType: 'application/json',
	// complete: getGenre
	// });

	// function getGenre(data){
	// 	console.log(data);
	// }
	// 