$(document).ready(function(){

	var vue1=new Vue({
		el:'.maxDiv',
		data:()=>{
			return {
				cities:{},
			    data:{},
                userName:{},
			}
		},
		methods:{
			getCity(){
				var that =this;
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
				var that =this;
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
			getUserName(){
				var that =this;
				$.ajax({
					url:"getUserName.do",
					success:function(msg){
						if(msg.status){
                            that.userName=msg.message;
						}else{
							that.userName="none";
						}
					}
				})
			},
			logout(){
				$.ajax({
					url:"logout.do",
					success:function(msg){
						if(msg.status){
							window.location.reload()
						}
					}
				})
			},
			getMsgFromCityArea(cityArea){
				alert(cityArea);
                var that =this;
                $.ajax({
					url:"getMsgFromCityArea.do",
					data:{cityArea:cityArea},
					success:function(msg){
						console.log(msg);
						that.data=msg;
					}
				});
			},
		},
		created:function(){
			this.getUserName();
			this.getCity();
			this.getMsgFromCity();
		}
	})

});

