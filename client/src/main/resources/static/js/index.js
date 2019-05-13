$(document).ready(function(){

	var v=new Vue({
		el:'.maxDiv',
		data:()=>{
			return {
				cities:{},
				data:{},
				userName:{},
				areas:{},
				selectArea:'',
				role:'',
				id:'',
				status:''
			}
		},
		methods:{
			message:function(msg){
				if (msg.status) {
                 	//消息提示框---用jquery的Growl插件
                 	$.growl.notice({title: "", message: msg.message });
                }else{
                	//消息提示框---用jquery的Growl插件
                	$.growl.error({title: "", message: msg.message });
                }
			},
			picUpload(){
				// 图片上传初始化
                $('#dropify-front').dropify({
                    messages: {
                        default: '身份证正面照',
                        replace: '换一张',
                        remove:  '删除',
                        error:   '错误'
                    }
                });
                $('#dropify-reverse').dropify({
                    messages: {
                        default: '身份证反面照',
                        replace: '换一张',
                        remove:  '删除',
                        error:   '错误'
                    }
                });
			},
			auth(){
				var that = this;
				var tempFront = new Array();
				tempFront =  $('#front').find('img').attr('src').split(",");
				var frontBase64 = tempFront[1];
				var tempReverse = $('#reverse').find('img').attr('src').split(",");
				var reverseBase64 = tempReverse[1];
				$.ajax({
					url:'auth.do',
					type:'POST',
					data:{
						id:that.id,
						frontBase64: frontBase64,
						reverseBase64: reverseBase64
					},
					success:function(msg){
						v.message(msg);
						$('#auth').modal('hide');
					}
				})

			},
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
							var a = new Array();
							a =  msg.message.split(",");
							that.userName=a[0];
							that.id = a[1];
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
                            //location='login.html';
						}else{
							//false为0true为1
							if (msg.message == null) {
								//用户第一次查看用户信息
								that.role = '2'
						    }else{
							    //用户查看信息
							    //赋值市区下拉框
								that.areas=msg.area;
							    $('#userName').val(msg.userName);
							    var str=msg.message;
							    that.role = str.role;
							    //角色单选框赋值 0-学生；1-家长
							    if (str.role==2) {
							    	$('#person').click();
							    }else if (str.role==1) {}{
							    	$('#student').click();
							    }
							    //默认不允许再次选择
							    $('#person').attr('disabled', 'true');
							    $('#student').attr('disabled', 'true');
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
			updateUserMsg(){
				if (confirm("确认提交保存吗")) {
					var data={
						userName:$('#userName').val(),
						role:$('input:radio[name="role"]:checked').val(),
						cityName:$('#Ucity option:selected').val(),
						cityArea:$('#Uarea option:selected').val(),
						addressDetail:$('#addrDetail').val(),
						payId:$('#pay').val()
					}
					console.log(data);
					$.ajax({
						url: 'updateUserMsg.do',
						type: 'POST',
						data: data,
						success:function(msg){
                        	v.message(msg);
                        	//关闭模态框
                        	$('#message').modal('hide');
						}
					});
					
				}
			},
			getStudentMsg(){
				var that = this;
				$.ajax({
						url: 'getMsg.do',
						type: 'POST',
						data: {
							id : that.id
						},
						success:function(msg){
                        	console.log(msg);
                        	if (msg.status) {
                        		//赋值
                        		var data = msg.data;
                        		if (data.classify) {
                        			//理科
                        			$('#science').click();
                        			$('#comScience').val(data.comprehensive_liberal_or_science);
                        		}else{
                        			$('#arts').click();
                        			$('#comLiberal').val(data.comprehensive_liberal_or_science);
                        		}
                        		//大学，专业
                        		$('#university').val(data.university);
                        		$('#major').val(data.major);
                        		//语数英
                        		$('#chinese').val(data.chinese);
                        		$('#math').val(data.math);
                        		$('#english').val(data.english);
                        		//判断是否认证,如果已认证则不允许修改
                        		if (that.status == '1') {
                        			$('#arts').attr('disabled', 'true');
                        			$('#comLiberal').attr('disabled', 'true');
                        			$('#science').attr('disabled', 'true');
                        			$('#comScience').attr('disabled', 'true');
                        			$('#university').attr('disabled', 'true');
                        			$('#major').attr('disabled', 'true');
                        			$('#chinese').attr('disabled', 'true');
                        			$('#math').attr('disabled', 'true');
                        			$('#english').attr('disabled', 'true');
                        		}

                        	}

						}
					});
			},
			updateStudentMsg(){
				var that =this;
				if (confirm("确认提交保存吗")) {
					var data={
						id:that.id,
						classify:$('input:radio[name="classify"]:checked').val(),
						university:$('#university').val(),
						major:$('#major').val(),
						chinese:$('#chinese').val(),
						math:$('#math').val(),
						english:$('#english').val(),
						comScience:$('#comScience').val(),
						comLiberal:$('#comLiberal').val()
					}
					console.log(data);
					$.ajax({
						url: 'updateMsg.do',
						type: 'POST',
						data: data,
						success:function(msg){
                        	v.message(msg);
                        	$('#studentMsg').modal('hide')
						}
					});
					
				}
			},
			changePsw(){
				var orgPsw = $('#orgPsw').val();
				var newPsw = $('#newPsw').val();
				var comPsw = $('#comPsw').val();
				if (newPsw!=comPsw) {
					alert("确认密码与新密码不一致");
					return;
				}
				$.ajax({
					url:'changePsw.do',
					type:'POST',
					data:{
						orgPsw:orgPsw,
						newPsw:newPsw,
					},
					success:function(msg){
						v.message(msg);
						if (msg.status) {
							$('#changePsw').modal('hide')
							$('#orgPsw').val("");
							$('#newPsw').val("");
							$('#comPsw').val("");
						}
					}
				})
			}
		},
		created:function(){
			this.getUserName();
			this.getCity();
			this.getMsgFromCity();
			this.getUserMsg();
		}
	})

/*	    function changepic(obj){
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
        }*/

        //$('#student').click(function() {
        //	/* Act on the event */
        //	$('#classify').fadeIn();
        //	$('#score').fadeIn();
        //});
        //$('#person').click(function() {
        //	/* Act on the event */
        //	$('#classify').fadeOut();
        //	$('#score').fadeOut();
        //});
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

