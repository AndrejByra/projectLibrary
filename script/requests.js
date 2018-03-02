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
		$("#bookWrapper").append("<div id='cover'><img src='../img/books/"+bookName+".jpg'>"+
			"</div>").append("<div id='aboutBook'><div id='nameofbook'>"+bookName+"</div></div>");

		$.ajax({
			url: 'http://localhost:8080/book/infoauthor/' +bookName,
			data: { },
			error: function(){
				alert("Error");
			},
			success: getAuthor,
			crossDomain: true,
			dataType: 'jsonp',
			jsonpCallback: 'getAuthor',
				contentType: 'application/json',
				type: 'GET'
		});

		function getAuthor(data){
			data.Author.forEach(function(element){
				$("#aboutBook").append("<div id='author'>Author: "+element+"</div>");
			});
		}

		$.ajax({
			url: 'http://localhost:8080/book/infoisbn/' +bookName,
			data: { },
			error: function(){
				alert("Error");
			},
			success: getISBN,
			crossDomain: true,
			dataType: 'jsonp',
			jsonpCallback: 'getISBN',
				contentType: 'application/json',
				type: 'GET'
		});

		function getISBN(data){
			data.ISBN.forEach(function(element){
				$("#aboutBook").append("<div id='isbn'><span>ISBN: </span>"+element+"</div>");
			});
		}

		$.ajax({
			url: 'http://localhost:8080/book/infogenre/' +bookName,
			data: { },
			error: function(){
				alert("Error");
			},
			success: getInfoGenre,
			crossDomain: true,
			dataType: 'jsonp',
			jsonpCallback: 'getInfoGenre',
				contentType: 'application/json',
				type: 'GET'
		});

		function getInfoGenre(data){
			data.Genre.forEach(function(element){
				$("#aboutBook").append("<div id='genre'><span>Genre: </span>"+element+"</div>");
			});
		}

		$.ajax({
			url: 'http://localhost:8080/book/infoabout/' +bookName,
			data: { },
			error: function(){
				alert("Error");
			},
			success: infoAbout,
			crossDomain: true,
			dataType: 'jsonp',
			jsonpCallback: 'infoAbout',
				contentType: 'application/json',
				type: 'GET'
		});

		function infoAbout(data){
			data.About.forEach(function(element){
				$("#aboutBook").append("<div id='detailofbook'>"+element+"</div>");
				$("#aboutBook").append("<div id='available'>Available</div>");
				$("#aboutBook").append("<div id='borrow'><button>Borrow</button></div>");
			});
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