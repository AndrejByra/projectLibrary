(function($){



	$("#all, .dropbtn").click(function(event){

		event.preventDefault();
		$("#books").hide();

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
			$("#books").empty();
			for (var i = 0; i < data.name.length; i++){
				// $("#books").append("<div class='book book"+num+"'><span class='bookName"+num+"'>"+data.name[i]+"</span></div>");
 				$("#books").append("<div class='book book"+num+" "+data.name[i]+"'>"+
 					"<img src='../img/books/"+data.name[i]+".jpg'>"+
 					"<div class='bookCover'><span class='bookName'>"+data.name[i]+"</span>"+
 					"<button class='btnLend'>Lend</button></div></div>");
				num++;
			}
			$("#books").fadeOut(150).delay(250).fadeIn(150);
		}

	});

	$(".thisBook").click(function(event){

		event.preventDefault();
		$("#books").hide();

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
			$("#books").empty();
			data.name.forEach(function(element){
				$("#books").append("<div class='book book"+num+" "+element+"'>"+
					"<img src='../img/books/"+element+".jpg'>"+
					"<div class='bookCover'><span class='bookName'>"+element+"</span>"+
					"<button class='btnLend'>Lend</button></div></div>");
				num++;
			});

			$("#books").fadeOut(150).delay(250).fadeIn(150);
		}

	});	

	$("#bookWrapper").hide();

	$("#books").on("click", "button", function(){
		var bookName = ($(this).prev().text());
		$("#books").fadeOut(400);
		$("#bookWrapper").append("<div id='cover'><img src='../img/books/"+bookName+".jpg'></div>").delay(500).fadeIn(400);
	});

})(jQuery);

	// $("#books").on('mouseenter', '.book', function(){
	// 	$.ajax({
	// 	    type: 'GET',
	// 	    url: 'http://localhost:8080/book/author/' +$(this).text(),
	// 	    success: function(data) {
	// 	    	console.log(data);
	// 	    },
	// 	    error: function() {
	// 	        console.log('Error');
	// 	    },
	// 	    jsonp: 'jsonp'

	// 	});

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