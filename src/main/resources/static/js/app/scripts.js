
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
		alert('errorrr!');
	}
	
	function onSuccess(data, status){
	    console.log(data);
	    var answerTemplate = $("#answerTemplate").html();
	    var template = answerTemplate.format(data.writer.name, data.formattedCreatedDate, data.content);
	    $(".questionDetail").prepend(template);
	    location.reload();
	}
	
	
	String.prototype.format = function() {
	  var args = arguments;
	  return this.replace(/{(\d+)}/g, function(match, number) {
	    return typeof args[number] != 'undefined'
	        ? args[number]
	        : match
	        ;
	  });
	};