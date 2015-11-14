/**
 * 初始化form输入的tip
 */
define(function() {
	"use strict";

	var initFormInputTip = function() {
		if($("div[tip]").length >0) {
			$("div[tip]").each(function(){
				var tip = $(this).attr("tip");
				var tipLength = $(this).attr("tip-length")?$(this).attr("tip-length"):"col-md-5";
				var html = '<div class="'+ tipLength +'">'+
                    			'<label class="form-control-static">'+ tip +'</label>'+
                    		'</div>';
				$(html).insertAfter($(this));
			});
		}
	};

	return {
		init: function() {
			initFormInputTip();
		}
	};
});