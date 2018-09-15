/**
 * 
 */
	$(".answer-create input[type=submit]").click(addAnswer);
	
	function addAnswer(e) {
	    e.preventDefault();
	    console.log("click!!");
	
	var queryString = $(".answer-create").serialize();
	    console.log("queryString : " + queryString);
	
	var url = $(".answer-create").attr("action");
	    console.log("url : " + url);
	    
	$.ajax({
		type : 'post',
	    url : url,
	    data : queryString,
	    dataType : 'json',
	    error : onError,
	    success : onSuccess});
	}

	function onError(){
	
	}
	
	function onSuccess(data, status){
	    console.log(data);
	}