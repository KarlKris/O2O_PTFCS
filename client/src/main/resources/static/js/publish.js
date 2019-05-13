$(document).ready(function(){


	// select 插件
 	Vue.component('vue-select',VueSelect.VueSelect); 
 	//full-calendar
 	//Vue.component('full-calendar',VueFullcalendar);
 	//Vue.component('mz-datepicker',VueDatepickercalendar);

	var v = new Vue({
		el:'.maxDiv',
		data:()=>{
			return {
				range:[new Date(),new Date()],
				days:'1',
				userName:'',
				id:'',
				selectArea:'',
				role:'',
				areas:{},
				cities:{},
				courses:[],
				selected:[],  //存放选择的科目id
			}
		},
		methods:{
			publish:function(){
				alert(111);
			},
			getDay:function(){
				var that = this;
				var start = that.range[0].toLocaleDateString();
				var end = that.range[1].toLocaleDateString();
				var total = Date.parse(end) -  Date.parse(start);
				that.days = Math.floor(total / (24 * 3600 * 1000))+1;
				
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
			getCity(){
				var that =this;
				$.ajax({
					url:"getCity.do",
					success:function(msg){
						that.cities=msg;
					}});
			},
			getCourses(){
				var that = this;
				$.ajax({
					url:'getCourses.do',
					type:'GET',
					success:function(msg){
						if (msg.status) {
							that.courses = msg.data;
						}
					}
				})
			},
			selected_courses(values){
				var that = this;
				that.selected = values.map(function(obj) {
					return obj.id;
				})


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
							    var str=msg.message;
							    that.role = str.role;
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
			//插件初始化
			wizard(){
				var that = this;
				/*  Activate the tooltips      */
            	$('[rel="tooltip"]').tooltip();

            	// Code for the Validator
            	var $validator = $('.wizard-card form').validate({
        			  rules: {
            	    	},
        		});

                // Wizard Initialization
          		$('.wizard-card').bootstrapWizard({
            	    'tabClass': 'nav nav-pills',
            	    'nextSelector': '.btn-next',
            	    'previousSelector': '.btn-previous',

            	    onNext: function(tab, navigation, index) {
            	    	if (index==1) {
            	    		var length = that.selected.length;
            	    		if (length<=0) {
            	    			alert("请选择科目！！");
            	    			return false;
            	    		}
            	    	}
            	    	var $valid = $('.wizard-card form').valid();
            	    	if(!$valid) {
            	    		$validator.focusInvalid();
            	    		return false;
            	    	}
            	    },

                	onInit : function(tab, navigation, index){
                	    //check number of tabs and fill the entire row
                	    var $total = navigation.find('li').length;
                	    $width = 100/$total;
  
                	  navigation.find('li').css('width',$width + '%');

                	},

                	onTabClick : function(tab, navigation, index){
						var $valid = $('.wizard-card form').valid();

               	    	if(!$valid){
               	         return false;
               	    	} else{
               	    	    return true;
               	    	}

               		},

                	onTabShow: function(tab, navigation, index) {
                	    var $total = navigation.find('li').length;
                	    var $current = index+1;

                	    var $wizard = navigation.closest('.wizard-card');

                	    // If it's the last tab then hide the last button and show the finish instead
                	    if($current >= $total) {
                	        $($wizard).find('.btn-next').hide();
                	        $($wizard).find('.btn-finish').show();
                	    } else {
               	        	$($wizard).find('.btn-next').show();
               	         	$($wizard).find('.btn-finish').hide();
                	    }

                    	//update progress
                    	var move_distance = 100 / $total;
                    	move_distance = move_distance * (index) + move_distance / 2;

                    	$wizard.find($('.progress-bar')).css({width: move_distance + '%'});
                    	//e.relatedTarget // previous tab

                    	$wizard.find($('.wizard-card .nav-pills li.active a .icon-circle')).addClass('checked');

               	 }
	        	});


                // Prepare the preview for profile picture
                $("#wizard-picture").change(function(){
                    v.readURL(this);
                });

                $('[data-toggle="wizard-radio"]').click(function(){
                    wizard = $(this).closest('.wizard-card');
                    wizard.find('[data-toggle="wizard-radio"]').removeClass('active');
                    $(this).addClass('active');
                    $(wizard).find('[type="radio"]').removeAttr('checked');
                    $(this).find('[type="radio"]').attr('checked','true');
                });

                $('[data-toggle="wizard-checkbox"]').click(function(){
                    if( $(this).hasClass('active')){
                        $(this).removeClass('active');
                        $(this).find('[type="checkbox"]').removeAttr('checked');
                    } else {
                        $(this).addClass('active');
                        $(this).find('[type="checkbox"]').attr('checked','true');
                    }
                });

                $('.set-full-height').css('height', 'auto');
            },
            readURL(input){
            	if (input.files && input.files[0]) {
                var reader = new FileReader();

                reader.onload = function (e) {
                    $('#wizardPicturePreview').attr('src', e.target.result).fadeIn('slow');
                }
                reader.readAsDataURL(input.files[0]);
            	}
            }

		},
		created:function(){
			this.getCity();
			this.getCourses();
			this.getUserName();
			this.getUserMsg();
		},
		//使用插件时且不需要请求数据时，须在这里进行初始化，否则按钮会失效，
		//若需要请求数据，例如分页插件，则直接将从后台传来的数据赋值在插件初始化时所需要的数据变量上
		//vue会代理body，初始化body上面的数据，
		//这也就意味着你之前绑定的所有事件均会失效，但是绑定在body上面的不会；
		//这种绑定需要在mounted里面绑定，必须在mounted里面，因为mounted意味着元素被挂载了
		mounted: function(){
			this.wizard();
		}

	})
})