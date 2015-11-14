/**
 * 初始化ajax modal的触发
 */
define(function() {
	"use strict";

	var handleAjaxModal = function() {
		var $body = $('body');
		$body.find(".modal").remove();
		//监听modal-ajax
		var id = Util.getUniqueID("modal");
		var html = '<div class="modal fade" id="'+ id +'" tabindex="-1" aria-hidden="true">'+
					'</div>';
		$body.append(html);
		var $modal = $("#"+id);
		$(".page-content-body>div").on('click', ".modal-ajax", function(){
			App.blockUI($body, false);
			$modal.load($(this).attr("url"), function(response, status, xhr){
				App.unblockUI($body);
				App.initAjax();
				if (status == "success") {
					$modal.modal();
				} else {
                	App.toastError("请求失败");
				}
			});
		});
		
		//监听modal-ajax-static
		var staticId = Util.getUniqueID("staticModal");
		var staticHtml = '<div class="modal fade" id="'+ staticId +'" tabindex="-1" data-backdrop="static" data-keyboard="false" aria-hidden="true">'+
					'</div>';
		$body.append(staticHtml);
		var $staticModal = $("#"+staticId);
		$(".page-content-body>div").on('click', ".modal-ajax-static", function(){
			App.blockUI($body, false);
			$staticModal.load($(this).attr("url"), function(response, status, xhr){
				App.unblockUI($body);
				App.initAjax();
				if (status == "success") {
					$staticModal.modal();
				} else {
                	App.toastError("请求失败");
				}
			});
		});
		
	};
	
	//解决调用隐藏modal函数后立即移除modal产生的无法移除遮罩层的bug
	var timeoutTohandleAjaxModal = function() {
		setTimeout(function() {
			if($(".modal-backdrop").length > 0) {
				timeoutTohandleAjaxModal();
    		} else {
        		handleAjaxModal();
    		}
    	}, 200);
	};
	
	return {
		init: function() {
			timeoutTohandleAjaxModal();
		}
		
	};
});