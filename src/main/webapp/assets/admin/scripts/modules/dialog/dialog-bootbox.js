/**
 * 初始化bootbox确认操作的触发
 */
define(["bootbox"], function(Bootbox) {
	"use strict";
	
	Bootbox.setLocale("zh_CN");
	
	var handlePageConfirm = function() {
		if ($(".page-content-body>div").length > 0) {
			$(".page-content-body>div").on("click", ".bootbox-confirm", function() {
				var the = $(this);
		        var url = the.attr('url');
				Bootbox.confirm(the.attr("confirm"), function(result) {
		            if (result) {
		                App.ajaxToPage(url);
		            }
		        });
		    });
		}
	};
	var handleModalConfirm = function() {
		if ($(".modal-body>div").length > 0) {
			$(".modal-body>div").on("click", ".bootbox-confirm", function() {
				var the = $(this);
				var url = the.attr('url');
				Bootbox.confirm(the.attr("confirm"), function(result) {
					if (result) {
						App.ajaxToModal(url);
					}
				});
			});
		}
	};
	
	return {
		init: function() {
			handlePageConfirm();
			handleModalConfirm();
		}
	};
});