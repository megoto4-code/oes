$(document).ready(function() {
	setInterval(function() {
		$('#timer').load("${serverPath}timer?startTime=${startTime}&totalTime=${totalTime}");		
		redirect();
	}, 100);
});
function redirect() {
	if($('#timer').text().replace(/\s+/g, '') == '0:0') {
		$('body').load("${serverPath}exitExam?examSummary=yes");
	}	
}