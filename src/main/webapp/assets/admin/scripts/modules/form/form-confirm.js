/**
 * 初始化ajax submit的确认提交操作
 */
define(["bootbox"], function(Bootbox) {
	"use strict";

	Bootbox.setLocale("zh_CN");
	
	var handlePageSubmitComfirm = function() {
		
		if($('.ajax-submit-confirm').length > 0) {
	        $('.ajax-submit-confirm').on('click', function (e) {
	        	e.preventDefault();
	        	var the = $(this);
		        var url = the.attr('url');
	        	Bootbox.confirm(the.attr("confirm"), function(result) {
		            if (result) {
			        	App.ajaxSubmit(url);
		            }
		        });
	        });
		}
	};
	
	var handleModalSubmitComfirm = function() {
		
		if($('.ajax-submit-modal-confirm').length > 0) {
			$('.ajax-submit-modal-confirm').on('click', function (e) {
				e.preventDefault();
				var the = $(this);
		        var url = the.attr('url');
				Bootbox.confirm(the.attr("confirm"), function(result) {
		            if (result) {
						App.ajaxSubmitModal(url);
		            }
		        });
			});
		}
	};
	
	return {
		init: function() {
			handlePageSubmitComfirm();
			handleModalSubmitComfirm();
		}
	};
});