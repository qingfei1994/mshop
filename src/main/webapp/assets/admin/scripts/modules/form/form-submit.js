/**
 * 初始化ajax submit的确认提交操作
 */
define(["bootbox"], function(Bootbox) {
	"use strict";

	var handlePageSubmit = function() {

        //handle form ajax submit in document
        //misuosi:add by YLM
		if($('.ajax-submit').length > 0) {
	        $('.ajax-submit').on('click', function (e) {
	        	e.preventDefault();
	
	        	var url = $(this).attr("url");
	        	App.ajaxSubmit(url);
	        });
		}
        
	};
	var handleModalSubmit = function() {
		
		//handle form ajax submit in modal
		//misuosi:add by YLM
		if($('.ajax-submit-modal').length > 0) {
			$('.ajax-submit-modal').on('click', function (e) {
				e.preventDefault();
				
				var url = $(this).attr("url");
				App.ajaxSubmitModal(url);
			});
		}
	};
	
	return {
		init: function() {
			handlePageSubmit();
			handleModalSubmit();
		}
	};
});