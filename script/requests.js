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
			for (var i = 0; i < data.name.length; i++){
				$("#books").append("<div class='book book"+num+"'>"+data.name[i]+"</div>");
				num++;
			}
			console.log(data.name);

		}

	});

	$(".thisBook").click(function(event){

		event.preventDefault();
		var bookId = ((this).id);
		var book = {
			genre: bookId
		};

		var jsonBook = JSON.stringify(book);
		alert(jsonBook);

		$.ajax({
		url: 'http://localhost:8080/book/genres',
		data: jsonBook,
		success: getGenre,

		error: function(){
			alert("Error genre.");
		},
		crossDomain: true,
		dataType: 'jsonp',
		jsonpCallback: 'getGenre',
			contentType: 'application/json; charset=utf-8',
			type: 'POST'
		});

		function getGenre(data){
			console.log(data);
		}
	
	});	

})(jQuery);