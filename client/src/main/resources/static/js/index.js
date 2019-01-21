$(document).ready(function(){

	var vue1=new Vue({
		el:'.maxDiv',
		data:()=>{
			return {
				cities:{},
				data:{},
				userName:{},
				areas:{},
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
				$.ajax({
					url:"getMsgFromCity.do",
					data:{cityName:cityName},
					success:function(msg){
						console.log(msg);
						that.data=msg;
					}});
			},
			getPersonMsgFromCity(event){
				var that =this;
				var cityName=event.target.value;
				$.ajax({
					url:"getPersonMsgFromCity.do",
					data:{cityName:cityName},
					success:function(msg){
						console.log(msg);
						that.areas=msg;
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
			getUserMsg(){
				alert(1);
				$.ajax({
					url:"getUserMsg.do",
					type:"POST",
					success:function(msg){
						console.log(msg);
					}
				});
			}
		},
		created:function(){
			this.getUserName();
			this.getCity();
			this.getMsgFromCity();
		}
	})

	    function changepic(obj){
            //console.log(obj.files[0]);//这里可以获取上传文件的name
            var newsrc=getObjectURL(obj.files[0]);
            $(".show").attr('src', newsrc);

        }

        function getObjectURL(file){
        	var url = null ;
            // 下面函数执行的效果是一样的，只是需要针对不同的浏览器执行不同的 js 函数而已
            if (window.createObjectURL!=undefined) { // basic
            	url = window.createObjectURL(file) ;
            } else if (window.URL!=undefined) { // mozilla(firefox)
            	url = window.URL.createObjectURL(file) ;
            } else if (window.webkitURL!=undefined) { // webkit or chrome
            	url = window.webkitURL.createObjectURL(file) ;
            }
            return url ;
        }

        $('#student').click(function() {
        	/* Act on the event */
        	$('#classify').fadeIn();
        	$('#score').fadeIn();
        });
        $('#person').click(function() {
        	/* Act on the event */
        	$('#classify').fadeOut();
        	$('#score').fadeOut();
        });
        $('#arts').click(function() {
        	/* Act on the event */
        	$('#scienceScore').fadeOut();
        	$('#artsScore').fadeIn();
        });
        $('#science').click(function() {
        	/* Act on the event */
        	$('#artsScore').fadeOut();
        	$('#scienceScore').fadeIn();
        });


    });

