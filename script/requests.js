(function($){



	$("#all, .dropbtn").click(function(event){

		event.preventDefault();

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
				$("#books").append("<div class='book book"+num+"'><span class='bookName"+num+"'>"+data.name[i]+"</span></div>");
				num++;
			}
		}

	});

	$(".thisBook").click(function(event){

		event.preventDefault();
		var bookId = ((this).id);
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
				$("#books").append("<div class='book book"+num+"'><span class='bookName"+num+"'>"+element+"</span></div>");;
				num++;
			});

		}
	});	

	// $("#books").on('click', '.book', function(){

	// 	console.log($(this).text());

	// 	$.ajax({
	// 	    type: 'GET',
	// 	    url: 'http://localhost:8080/book/author/' +$(this).text(),
	// 	    success: function(data) {
	// 	    	console.log((this));
	// 	       // $("#books").find($(this)).appendTo("<span>"+data.name+"</span>");
	// 	    },
	// 	    error: function() {
	// 	        console.log('Error');
	// 	    },
	// 	    jsonp: 'jsonp'

	// 	});
	// });


})(jQuery);