/*********************************************
* 파일명: jquery-utils.js
* 기  능: JQUERY의 기능들을 유틸리티화 한다.
* 만든이: 이진환
* 날  짜: 2012-02-29
**********************************************/
// 0. 기본 오브젝트 변수
var objInfo	= new Object();
objInfo.jqueryAjax = new jqueryAjax(); //Ajax 관련 오브젝트
objInfo.jqueryUI = new jqueryUI(); //UI 관련 오브젝트
objInfo.jqueryCommon = new jqueryCommon(); //공통 관련 오브젝트
objInfo.jqueryLoading = new jqueryLoading(); //로딩 관련 오브젝트
objInfo.webStorage = new webStorage(); //웹스토리지관련
objInfo.jqueryPopup = new jqueryPopup(); //팝업 오브젝트

/**
 * 공통 관련함수
 */
function jqueryCommon(){}
/*
 * 폼을 초기화한다.
 * 
 *@param 폼ID
 */ 
jqueryCommon.prototype.clearForm = function ( form ) {
    $(':input', form).each(function() {
        var type = this.type;
        var tag = this.tagName.toLowerCase(); // normalize case
        if (type == 'hidden' || type == 'text' || type == 'password' || tag == 'textarea')
            this.value = "";
        else if (type == 'checkbox' || type == 'radio')
            this.checked = false;
        else if (tag == 'select')
            this.selectedIndex = -1;
    });
};
jqueryCommon.prototype.accessLog = function(accessGubun, depAirportCode, depDate, arrAirportCode, arrDate) {	
    $.ajax({    		
		 url: window.location.protocol + "//" + document.location.host + "/common/accessLog.do"
		,type:"POST"
		,dataType:"jsonp"
	    ,data:{
	  		 accessGubun : accessGubun
	 		,depAirportCode : depAirportCode
	 		,depDate : depDate
	 		,arrAirportCode : arrAirportCode
	 		,arrDate : arrDate
    	}
	    //통신을 시작할때 처리
		,beforeSend:function(){
		}
		//통신성공시 처리
		,success:function(args){
		}
		//통신이 완료된 후 처리
		,complete:function(){
		}
		//통신에러발생시 처리
		,error:function(request,status,error){
		}
    });
};
jqueryCommon.prototype.tokenLog = function(channelCode, rsvNo) {
    $.ajax({    		
		 url: window.location.protocol + "//" + document.location.host + "/common/tokenLog.do"
		,type:"POST"
		,dataType:"jsonp"
	    ,data:{
	  		 channelCode : channelCode
	 		,rsvNo : rsvNo
    	}
	    //통신을 시작할때 처리
		,beforeSend:function(){
		}
		//통신성공시 처리
		,success:function(args){
		}
		//통신이 완료된 후 처리
		,complete:function(){
		}
		//통신에러발생시 처리
		,error:function(request,status,error){
		}
    });
};
jqueryCommon.prototype.setCookie = function ( name, value, expires ) {
	var v_cookie_info = name + "=" + escape(value) + ";";
    v_cookie_info += " path=/;";
    v_cookie_info += (expires == null ? "" : " expires=" + expires.toGMTString());
    document.cookie = v_cookie_info;
};
jqueryCommon.prototype.getCookie = function ( Name ) {
	var search = Name + "="
    if (document.cookie.length > 0) { // DmE00! <3A$5G>n @V4Y8i
        offset = document.cookie.indexOf(search)
        if (offset != -1) { // DmE00! A8@gGO8i
            offset += search.length
            // set index of beginning of value
            end = document.cookie.indexOf(";", offset)
            // DmE0 0*@G 86Av87 @'D! @N5&=: 9xH# <3A$
            if (end == -1)
                end = document.cookie.length
            return unescape(document.cookie.substring(offset, end))
        }
    }
    return "";
};
jqueryCommon.prototype.numberWithCommas = function ( amount ) {
	return amount.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
};

/**
 * UI 관련함수
 */
function jqueryUI() { }
/*
* spinner를 설정합니다. (/js/spin.min.js => http://fgnass.github.io/spin.js/)
*
* 
* @param 구분 : true 인 경우 보여줌, false인 경우 숨김
*/
jqueryUI.prototype.spinnerObj; //스핀객체
jqueryUI.prototype.spinnerOpt = {
    lines: 7, // The number of lines to draw
    length: 3, // The length of each line
    width: 3, // The line thickness
    radius: 3, // The radius of the inner circle
    corners: 1, // Corner roundness (0..1)
    rotate: 0, // The rotation offset
    direction: 1, // 1: clockwise, -1: counterclockwise
    color: '#000', // #rgb or #rrggbb or array of colors
    speed: 1, // Rounds per second
    trail: 60, // Afterglow percentage
    shadow: false, // Whether to render a shadow
    hwaccel: false, // Whether to use hardware acceleration
    className: 'spinner', // The CSS class to assign to the spinner
    zIndex: 2e9, // The z-index (defaults to 2000000000)
    top: 'auto', // Top position relative to parent in px
    left: 'auto' // Left position relative to parent in px
};
jqueryUI.prototype.spinner = function(mode, argID, v_lines, v_radius) {
    this.spinnerOpt['lines'] = v_lines == null ? 7 : v_lines;
    this.spinnerOpt['radius'] = v_radius == null ? 3 : v_radius;

    //if (this.spinnerObj == null) {
    var target = document.getElementById(argID);
    this.spinnerObj = new Spinner(this.spinnerOpt).spin(target);
    target.appendChild(this.spinnerObj.el);
    //}

    if (mode) {
        $("#" + argID).show();
    } else {
        $("#" + argID).hide();
        //this.spinnerObj.stop();
    }
};

/**
 * Ajax 호출함수..
 */
function jqueryAjax(){}
jqueryAjax.prototype.method = "POST"; // "POST" or "GET"
jqueryAjax.prototype.async = false;
jqueryAjax.prototype.url = "";
jqueryAjax.prototype.dataType = "json"; //전송받을 데이터 타입 : xml, html, script, json, jsonp, text ( 미지정시 자동판단 )
jqueryAjax.prototype.formName = "";
jqueryAjax.prototype.contentType = "application/x-www-form-urlencoded; charset=UTF-8";
jqueryAjax.prototype.resultData;
jqueryAjax.prototype.isCallback = true; 
jqueryAjax.prototype.callback = "";  
jqueryAjax.prototype.callbackMethod = "AJAX_CALLBACK(objInfo.jqueryAjax.callback, objInfo.jqueryAjax.resultData);";

jqueryAjax.prototype.init = function () {
	this.method = "POST"; // "POST" or "GET"
	this.async = false;
	this.url = "";
	this.dataType = "json"; //전송받을 데이터 타입 : xml, html, script, json, jsonp, text ( 미지정시 자동판단 )
	this.formName = "";
	this.contentType = "application/x-www-form-urlencoded; charset=UTF-8";
	this.resultData;
	this.isCallback = true; 
	this.callback = "";  
	this.callbackMethod = "AJAX_CALLBACK(objInfo.jqueryAjax.callback, objInfo.jqueryAjax.resultData);";
};

//설정된 값을 이용하여, 통신을 한다.
jqueryAjax.prototype.send = function ( method ) {
	this.method = method;
	this.isCallback = (this.callback == "" ? false : true);
	
	var url = this.url;
	var async = this.async;
	var dataType = this.dataType;
	var formName = this.formName;
	var contentType = this.contentType;
	
	switch(method){
		case "MULTIPART": 
			$("#"+formName+"").ajaxForm(
					{url:url
				    ,async:async
					//,dataType: dataType
					,contentType:contentType
					
					//통신을 시작할때 처리
					,beforeSend:function(){
						objInfo.jqueryAjax.result("beforeSend");
					}
					//통신성공시 처리
					,success:function(args){
						objInfo.jqueryAjax.result("success", args );
					}
					//통신이 완료된 후 처리
					,complete:function(){
						objInfo.jqueryAjax.result("complete");
					}
					//통신에러발생시 처리
					,error:function(request,status,error){
						objInfo.jqueryAjax.result("error", error );
					}
				}
			);
			var frm = $("#"+formName+"");
			frm.attr('action', url);
			frm.submit();
			break;
		case "GET":
			$.ajax(
					{url:url
					,type:method
				    ,async: async
					,dataType: "text"
					,data: formName //formName.serialize()
					,contentType:contentType //"application/x-www-form-urlencoded; charset=UTF-8"
	
					//통신을 시작할때 처리
					,beforeSend:function(){
						objInfo.jqueryAjax.result("beforeSend");
					}
					//통신성공시 처리
					,success:function(args){
						objInfo.jqueryAjax.result("success", args );
					}
					//통신이 완료된 후 처리
					,complete:function(){
						objInfo.jqueryAjax.result("complete");
					}
					//통신에러발생시 처리
					,error:function(request,status,error){
						objInfo.jqueryAjax.result("error", error );
					}
				}
			);
			break;
		case "POST":
			$.ajaxSetup({async: async});
			$.post( url
				   ,formName //formName.serialize()
				   ,function(data){
						objInfo.jqueryAjax.result("success", data );
					}
					//,dataType
			);
			$.ajaxSetup({async: true});
			break;
	} 

	return false;
};
//통신완료된 데이터를 처리하는 부문
jqueryAjax.prototype.result = function(trans, args) {
    if (args == null) return;
    //if (args == "undefined") return;

    switch (trans) {
        case "beforeSend": break;
        case "complete": break;
        case "success": 
            if ("json" == this.dataType) {
                this.parseJSON(args);
            } else if ("xml" == this.dataType) {
                this.parseXML(args);
            } else {
                /*-- HEAD data S--*/
                var rphead = new Array();
                rphead[0] = new Array();
                rphead[0]["error"] = false;
                /*-- HEAD data E--*/

                /*-- RESULT data S--*/
                var rpdata = args;
                /*-- RESULT data E--*/

                this.resultData = new Array();
                this.resultData[0] = rphead;
                this.resultData[1] = rpdata;

                //if (this.isCallback) AJAX_CALLBACK(this.callback, this.resultData);
                if (this.isCallback)  $.globalEval(this.callbackMethod);
            }
            break;
        case "error":
            alert("error:" + args);
            //this.resultData = args;
            break;
    }
};
//통신완료된 데이터가 JSON일 경우 데이터를 재가공하여, 리턴한다.
jqueryAjax.prototype.parseJSON = function(jsonDoc) {
    var json_rdata = eval(jsonDoc);

    /*-- HEAD data S--*/
    var rphead = new Array();
    rphead[0] = new Array();
    rphead[0]["error"] = false;
    /*-- HEAD data E--*/

    /*-- RESULT data S--*/
    var rpdata;
    if (json_rdata == null) {
        rpdata = new Array();
    } else {
        if (json_rdata.length == null) {
            rpdata = new Array();
            rpdata[0] = new Array();
            rpdata[0] = json_rdata;
        } else {
            rpdata = json_rdata;
        }
    }
    /*-- RESULT data E--*/

    this.resultData = new Array();
    this.resultData[0] = rphead;
    this.resultData[1] = rpdata;

    //if (this.isCallback) AJAX_CALLBACK(this.callback, this.resultData);
    if (this.isCallback)  $.globalEval(this.callbackMethod);
};
//통신완료된 데이터가 XML일 경우 데이터를 재가공하여, 리턴한다.
jqueryAjax.prototype.parseXML = function(xmldata) {
    /*-- HEAD data S--*/
    var rphead = new Array();
    rphead[0] = new Array();
    rphead[0]["error"] = $(xmldata).find("HEAD").find("error").text();
    rphead[0]["message"] = $(xmldata).find("HEAD").find("message").text();
    /*-- HEAD data E--*/

    /*-- RESULT data S--*/
    var rpdata = new Array();
    {
        $(xmldata).find("RECORD").each(function(index) {
            rpdata[index] = new Array();
            $(this).children().each(function(j){
                rpdata[index][this.tagName] = $(this).text();
            });
        });
    }
    /*-- RESULT data E--*/

    this.resultData = new Array();
    this.resultData[0] = rphead;
    this.resultData[1] = rpdata;

    //if (this.isCallback) AJAX_CALLBACK(this.callback, this.resultData);
    if (this.isCallback)  $.globalEval(this.callbackMethod);
}

/**
 * 검색로딩 관련함수
 */
function jqueryLoading(){}
jqueryLoading.prototype.search = function (v_mode, v_text) {
	switch(v_mode)
	{
		case true:
			$('.dimmedLoading').remove();
			var html = '';
			html += '<div class="dimmedLayer dimmedLoading">';
			if(v_text !== undefined) {
				html += '<div class="loading dimmedpageTxt">';
				html += '<img src="http://mdomair.ttang.com/images/common/loading/loading_all.gif" class="imgLoading" alt="loading">';
				html += '<p>'+v_text+'</p>';
				html += '</div>';
			} else {
				html += '<div class="loading dimmedpage"><img src="http://mdomair.ttang.com/images/common/loading/loading_all.gif" class="imgLoading" alt="loading"></div>';
			}
			html += '</div>';
			$('body').append(html);
			break;
		case false:
			$('.dimmedLoading').remove();
			break;
	}
};
jqueryLoading.prototype.realtimeAnimate = function (){
	$("#id_loading_comm .progress .ico_plane").attr("style","left:0%");
	$("#id_loading_comm .progress .ing").attr("style","left:0%");
	
	$("#id_loading_comm .progress .ico_plane" ).animate({left: "100%"}, 5000, 'linear', function(){
		objInfo.jqueryLoading.realtimeAnimate();
    });
	$("#id_loading_comm .ing" ).animate({width: "100%"}, 5000, 'linear', function(){
		objInfo.jqueryLoading.realtimeAnimate();
    });
};

/**
 * 웹스토리지 관련함수
 *   - 도메인마다 따로 생성된다.
 *   - local : 영구적, session : 윈도우객체와 동일
 *   - 메소드 
 *        length, key(index), getItem(key), setItem(key,data), removeItem(key), clear()
 *   - KEY => 채널코드 + "_" + 구분코드(FA,RSV)
 *   - VAL => json 구조체로 생성
 */
function webStorage() { }
webStorage.prototype.mode = "local"; //로컬:local, 세션:session
webStorage.prototype.isSupported = function() {
	return window.Storage?true:false;
};
webStorage.prototype.setCookie = function(v_channel, v_gubun, v_value) {
	if(!this.isSupported()) return;

	var v_key = v_channel+"_"+v_gubun;
	switch(this.mode)
	{
		case "local":
			localStorage[v_key] = v_value;
			break;
		case "session":
			sessionStorage[v_key] = v_value;
			break;
	}
};
webStorage.prototype.getCookie = function(v_channel, v_gubun) {
	if(!this.isSupported()) return;

	var v_key = v_channel+"_"+v_gubun;
	var v_value = "";
	
	switch(this.mode)
	{
		case "local":
			v_value = localStorage[v_key];
			break;
		case "session":
			v_value = sessionStorage[v_key];
			break;
	}
	return v_value == null ? "" : v_value;
};

/**
 * bPopup 호출
 */
function jqueryPopup(){}
jqueryPopup.prototype.open = function (loadUrl, options) {
	$.ajax({
	  url: loadUrl
	}).done(function(data) {
		var defaults = {
			onClose: function() {
				$('.layerLoadWrap').attr('style', '').empty();
            }
		};
		$('.layerLoadWrap').html(data).bPopup($.extend(defaults, options||{}));
	});
};
/**
 * Date format 유틸
 */
Date.prototype.getFormatDate = function(format) {
    var yyyy = this.getFullYear().toString();
    format = format.replace(/yyyy/g, yyyy)
    var mm = (this.getMonth()+1).toString(); 
    format = format.replace(/mm/g, (mm[1]?mm:"0"+mm[0]));
    var dd  = this.getDate().toString();
    format = format.replace(/dd/g, (dd[1]?dd:"0"+dd[0]));
    var hh = this.getHours().toString();
    format = format.replace(/hh/g, (hh[1]?hh:"0"+hh[0]));
    var ii = this.getMinutes().toString();
    format = format.replace(/ii/g, (ii[1]?ii:"0"+ii[0]));
    var ss  = this.getSeconds().toString();
    format = format.replace(/ss/g, (ss[1]?ss:"0"+ss[0]));
    return format;
};