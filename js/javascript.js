$(document).ready(function() {
	var news;
	var infor;
	$.ajaxSettings.async = false;
	$.getJSON('/bibi/getNews', function(json){
		news = json.news;
		infor = json.infor;
	});
	if(infor == 0){
		alert("请先登录");
		window.location.href="http://192.168.204.84:8080/bibitaxi/login.html";
	}
	else {
	document.getElementById("infor_list").innerHTML = infor;
	document.getElementById("right").innerHTML = news;
	document.getElementById("right1").innerHTML = news;
	document.getElementById("right2").innerHTML = news;

	}
});

function show(id){
	document.getElementById(id).style.display="block";
	if(id == 1){
		document.getElementById("2").style.display="none";
		document.getElementById("3").style.display="none";
		document.getElementById("4").style.display="none";
	}
	if(id == 2){
		var record;
		document.getElementById("1").style.display="none";
		document.getElementById("3").style.display="none";
		document.getElementById("4").style.display="none";
		$.ajaxSettings.async = false;
		$.getJSON('/bibi/driverRecord', function (json) {  
                    record = json.record;
                });
		document.getElementById("record_list").innerHTML = record;
	}
	if(id == 3){
		var result;
		var news;
		document.getElementById("2").style.display="none";
		document.getElementById("1").style.display="none";
		document.getElementById("4").style.display="none";
		$.ajaxSettings.async = false;  
		$.getJSON('/bibi/getList', function (json) {  
                    result = json.result;
                });
		$.getJSON('/bibi/getNews', function(json){
		news = json.news;
	});
		document.getElementById("right_list").innerHTML = news;
		document.getElementById("order").innerHTML = result;
	}
	if(id == 4){
		document.getElementById("2").style.display="none";
		document.getElementById("3").style.display="none";
		document.getElementById("1").style.display="none";
	}
}
function showuser(id){
	document.getElementById(id).style.display="block";
	if(id == 1){
		document.getElementById("2").style.display="none";
		document.getElementById("3").style.display="none";
		document.getElementById("4").style.display="none";
		document.getElementById("5").style.display="none";
	}
	if(id == 2){
		document.getElementById("1").style.display="none";
		document.getElementById("3").style.display="none";
		document.getElementById("4").style.display="none";
		document.getElementById("5").style.display="none";
		
	}
	if(id == 3){
		document.getElementById("2").style.display="none";
		document.getElementById("1").style.display="none";
		document.getElementById("4").style.display="none";
		document.getElementById("5").style.display="none";
		
	}
	if(id == 4){
		var news;
		document.getElementById("2").style.display="none";
		document.getElementById("3").style.display="none";
		document.getElementById("1").style.display="none";
		document.getElementById("5").style.display="none";
		$.ajaxSettings.async = false;
		$.getJSON('/bibi/getNews', function(json){
		news = json.news;
	});
	document.getElementById("right3").innerHTML = news;
	}
	if(id == 5){
		var news;
		document.getElementById("2").style.display="none";
		document.getElementById("3").style.display="none";
		document.getElementById("1").style.display="none";
		document.getElementById("4").style.display="none";
		$.ajaxSettings.async = false;
		$.getJSON('/bibi/getNews', function(json){
		news = json.news;
	});
	document.getElementById("right4").innerHTML = news;
	}
}
