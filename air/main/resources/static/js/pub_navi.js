$(function(){
	/* navigation */
	$('.TMv2_header .topMenu .abR1').click(function(){
		var bodyST = $(window).scrollTop();
		var body = $(window).height();
		$('body').css('margin-top',-bodyST).css('height', body+bodyST);
		$('.dimmedLayer.nav').fadeIn();
		$('#TMv2_nav').show().animate({left:'0'},400).addClass('on');
		$('body').css('position','fixed');
	});	
	$('.TMv2_nav #navClose').click(function(){	
		var moveH = $('body').css('margin-top').replace('px','')*-1;
		$('body').removeAttr('style');
		$(window).scrollTop(moveH);
		$('.dimmedLayer.nav').fadeOut();
		$('#TMv2_nav').animate({left:'100%'},500);
		$('#TMv2_nav').removeClass('on');		
	});	
});