var App = function () {
	"use strict";
	
	if($('link[href="assets/wap/gmu/css/widget/dialog/dialog.css"]').length > 0) {
		var alert = gmu.Dialog({
	        autoOpen: false,
	        closeBtn: false,
	        buttons: {
	            '关闭': function(){
	                this.close();
	            }
	        },
	        maskClick: function(){
	        	this.close();
	        }
	    });
		
	    var confirmCallback = null;
		var confirm = gmu.Dialog({
	        autoOpen: false,
	        closeBtn: false,
	        buttons: {
	            '确定': function(){
	            	this.close();
	                confirmCallback();
	            },
	            '取消': function(){
	            	this.close();
	            }
	        }
	    });
	}
	
	return {
		//显示遮罩Loading
		showLoading: function(){
			$("body").append('<div class="loading-bg"><div class="loading"></div></div>');
		},
		
		//移除遮罩Loading
		removeLoading: function(){
			$("body .loading-bg").remove();
		},
		
		//提示
		alert: function(message){
			alert.content(message).open();
		},
		
		//确认
		confirm: function(message, callback){
			confirmCallback = callback;
			confirm.content(message).open();
		},
		
		//ajax(post) with data to url for page
        //misuosi:add by YLM 
        post: function(url, data, callback){
        	App.showLoading();
        	$.ajax({
        		type : "POST",
        		cache : false,
        		url : url,
        		data : data,
        		success: function (res) {
        			if(callback) {
        				callback(res);
        			}
        			App.removeLoading();
        		},
        		error: function (xhr, ajaxOptions, thrownError) {
        			App.alert("网络错误");
        			App.removeLoading();
        		},
        		timeout: 30000
        	});
        },
        
        isPhone: function(str) {
        	var tel = /^0{0,1}(1[3,4,5,8][0-9])\d{8}$/;
			return tel.test(str);
        }
	};
}();