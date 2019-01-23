$(document).ready(function(){

	var vue1=new Vue({
		el:'.maxDiv',
		data:()=>{
			return {
				cities:{},
				data:{},
				userName:{},
				areas:{},
				selectArea:''
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
					}
				});
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
				var that =this;
				$.ajax({
					url:"getUserMsg.do",
					type:"POST",
					success:function(msg){
						console.log(msg);
						if (!msg.status) {
                            //用户未登录
                            location='login.html';
						}else{
							//false为0true为1
							if (msg.message == null) {
								//用户第一次查看用户信息
						    }else{
							    //用户查看信息
							    //赋值市区下拉框
								that.areas=msg.area;
							    $('#userName').val(msg.userName);
							    var str=msg.message;
							    if (str.role) {
								    //群众
								}else{
									//大学生
									//模拟点击大学生事件
									$('#student').click();
									//语数英成绩赋值
									$('#chinese').val(str.chinese);
									$('#math').val(str.math);
									$('#english').val(str.english);
									//文科 理科成绩赋值
									if(str.arts_or_science){
										//理科
										//模拟点击理科
										$('#science').click();
										$('#comScience').val(str.comprehensive_liberal_or_science);
									}else{
										//文科
										//模拟点击文科
										$('#arts').click();
										$('#comLiberal').val(str.comprehensive_liberal_or_science);
									}
								
								}
								//不可选取角色
								$('#student').attr('disabled', true);
								$('#person').attr('disabled', true);
								//大学跟专业
								$('#university').val(str.university);
								$('#major').val(str.major);

								//居住地址
								var city=str.cityName;var area=str.cityArea;
								var optionCity="option:contains('"+city+"')";
								var optionArea="option:contains('"+area+"')";
								that.selectArea = area;
								// console.log(optionArea);
								$('#Ucity').find(optionCity).attr('selected', true);
								// $('#Uarea').find(optionArea).attr('selected', true);
								$('#addrDetail').val(str.addressDetail);
								//支付宝账号
								$('#pay').val(str.payId);
							}
						}
					}	
				});
			},
			setUserMsg(){
				if (confirm("确认提交保存吗")) {
					var data={
						userName:$('#userName').val(),
						role:$('input:radio[name="role"]:checked').val(),
						classify:$('input:radio[name="classify"]:checked').val(),
						university:$('#university').val(),
						major:$('#major').val(),
						chinese:$('#chinese').val(),
						math:$('#math').val(),
						english:$('#english').val(),
						comScience:$('#comScience').val(),
						comLiberal:$('#comLiberal').val(),
						city:$('#Ucity option:selected').val(),
						area:$('#Uarea option:selected').val(),
						addrDetail:$('#addrDetail').val(),
						payId:$('#pay').val(),
					}
					console.log(data);
				}
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

