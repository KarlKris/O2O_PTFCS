
jQuery(document).ready(function() {
	
    /*
        Fullscreen background
        */
        $.backstretch("static/htmlTemplates/register/img/backgrounds/1.jpg");

        $('#top-navbar-1').on('shown.bs.collapse', function(){
           $.backstretch("resize");
       });
        $('#top-navbar-1').on('hidden.bs.collapse', function(){
           $.backstretch("resize");
       });

    /*
        Form validation
        */
        $('.registration-form input[type="text"], .registration-form textarea').on('focus', function() {
           $(this).removeClass('input-error');
       });

        $('.registration-form').on('submit', function(e) {

           $(this).find('input[type="text"], textarea').each(function(){
              if( $(this).val() == "" ) {
                 e.preventDefault();
                 $(this).addClass('input-error');
             }
             else {
                 $(this).removeClass('input-error');
             }
         });

       });

        $('.getPhoneVerificationCode').click(function() {
            /* Act on the event */
            var p=/^1[34578]\d{9}$/;
            var phone=$('.phone').val();
            if(!(p.test(phone))){
                alert("手机号码有误！");
            }else{
             $.ajax({
                url:"getPhoneVerificationCode.do",
                data:{phone:phone},
                success:function(msg){
                    if(msg.status){
                        $('.getPhoneVerificationCode').text("已发送");
                    }else{
                        alert(msg.message);
                    }
                }
            });
         }

     });

        $('.registerBtn').click(function() {
            /* Act on the event */
            console.log("正在注册前验证。。。。");
            var p=/^1[34578]\d{9}$/;
            var c=/^\d{6}$/;
            var phone=$('.phone').val();
            var code=$('.code').val();
            if( (p.test(phone)) && (c.test(code)) ) {
                $.ajax({
                    url:"register.do",
                    type:"POST",
                    data:{
                        phone:phone,
                        phoneVerificationCode:code,
                    },
                    success:function(msg){
                        console.log(msg.message);
                        if(msg.status){
                            alert("初始密码为手机号码");
                            location="login.html";
                        }else{
                            alert("验证码有误");  
                        }
                    }
                });
            }else{
                alert("手机号码或验证码有误");
            }
        });
    });
