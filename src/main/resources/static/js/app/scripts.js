// // 답변 목록
// getAnswers();

// // 답변 목록 출력 함수
// function getAnswers(){
// 	$.getJSON("/api/questions/" + questionId + "/answers", function(data){

// 		console.log(data);

// 	});
// }
	
	
	
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
	    //error : onError,
	    error: function(status) {
	    	console.log("error!! status : "+ status);
	    },
	    success : function(data, status){
	    	console.log("data: "+data);
		    var answerTemplate = $("#answerTemplate").html();
		    var template = answerTemplate.format(data.writer.name, data.formattedCreatedDate, data.content);
		    $(".questionDetail").prepend(template);
	    	} 
		});
	}

	function onError(status){
		//alert('errorrr!');
		console.log("error!! status : "+ status);
	}
	
	function onSuccess(data, status){
	    
	}
	
	
//	String.prototype.format = function() {
//	  var args = arguments;
//	  return this.replace(/{(\d+)}/g, function(match, number) {
//	    return typeof args[number] != 'undefined'
//	        ? args[number]
//	        : match
//	        ;
//	  });
//	};