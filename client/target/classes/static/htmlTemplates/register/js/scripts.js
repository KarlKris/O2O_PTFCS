
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

    $(".getPhoneVerificationCode").click(function(){
        alert("getPhoneVerificationCode");
        $.ajax({
            url:"getPhoneVerificationCode.do",
            success:function(result){
                if(result.status){
                    alert(result.message);
                    $(".getPhoneVerificationCode").val("已发送");
                }else{
                    alert(result.message);
                }
            
        }});
    });
    
    
});
