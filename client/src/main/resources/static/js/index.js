$(document).ready(function(){

	getCity();

    //对于给动态添加的元素添加监听事件，要使用on（）方法，前面给定静态的父选择器（.city-name）,后面方法参数则指定要添加监听的元素（a）
	$(".city-name").on('click', 'a',function(event) {
		var city=event.target.innerHTML;
		console.log(city);
		$(".city").text(city);

		getMsgFromCity();


	});

});

function getMsgFromCity(){
	var cityName=getCityName();
	$.ajax({
		url:"getMsgFromCity.do",
		success:function(result){
			alert(1)
			console.log(result);
		}});
}

function getCityName(){
	var city=$(".city").text();
	console.log(city);
	return city;
}

function getCity(){
	$.ajax({
		url:"getCity.do",
		success:function(result){
			alert(1)
			console.log(result);
			var length=result.length;
			for(i=0;i<length;i++){
				buildCity(result[i]);
			};
		}});


}

function buildCity(cityName){
	var city=$(".city-name");
	city.append('<a class="dropdown-item" href="jacascript::void(0);">'+cityName+'</a>')
}
