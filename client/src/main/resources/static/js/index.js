$(document).ready(function(){

	var vue1=new Vue({
		el:'.maxDiv',
		data:()=>{
			return {
				cities:{},
			data:{},
			}
		},
		methods:{
			getCity(){
				var that =this
				$.ajax({
					url:"getCity.do",
					success:function(msg){
						that.cities=msg;
					}});
			},
			setCity(event){
				var city=event.target.innerHTML;
				$(".city").text(city);
				this.getMsgFromCity();

			},
			getMsgFromCity(){
				var that =this
				var cityName=$(".city").text();
				// alert(cityName);
				$.ajax({
					url:"getMsgFromCity.do",
					data:{cityName:cityName},
					success:function(msg){
						console.log(msg);
						that.data=msg;
					}});
			},
		},
		created:function(){
			this.getCity();
			this.getMsgFromCity();
		}
	})


});

