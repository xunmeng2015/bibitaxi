$(document).ready(function() {
	var news;
	var infor;
	$.ajaxSettings.async = false;
	$.getJSON('/servlet/checkLogin', function(json){
		news = json.news;
		infor = json.infor;
	});
	if(infor == 0){
		alert ("ÇëÏÈµÇÂ¼");
		window.location.href="http://192.168.204.84:8080/DBS/login.html";
	}
	else {
	document.getElementById("infor_list").innerHTML = infor;
	document.getElementById("right").innerHTML = news;
	document.getElementById("right1").innerHTML = news;
	document.getElementById("right2").innerHTML = news;
	}
});