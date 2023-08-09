/* contents > min-height */
$(window).load(function(){
	contAddH();
});
$(window).resize(function(){	
	contAddH();
});
function contAddH(){
	if($(window).height() > $('body').height()){		
		$('.TMv2_section').css('min-height',$(window).height() - $('.TMv2_headerWrap').height() - $('.TMv2_footer').height() );	
	}
}
/* for iso App / input + fixed인 경우 (171204 add) */
$("input.trans, input.def, textarea.def, textarea.trans, select.def").each(function(){
	$(this).bind("focus", function(){
	    $(".TMv2_headerWrap, .rsltFootBtn.page, .TMv2_contents.bottomFix .btn_area.btmFix").css("position", "absolute");	    
	});
	$(this).bind("blur",function(){
    	$(".TMv2_headerWrap, .rsltFootBtn.page, .TMv2_contents.bottomFix .btn_area.btmFix").css("position", "fixed");    	
	});
});
/*go to top*/
$(window).scroll(function() {
	if($(window).scrollTop() > 300 ){
		$('.gototop').fadeIn('fast');
	}
	else {
		$('.gototop').fadeOut('fast');
	}
});