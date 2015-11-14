/**
 * 表格ajax请求工具
 */
define(function() {
	'use strict';

	return {
		ajaxTableToModal: function(url) {
			var finalUrl = new URL(url);
			var modal = $('.modal.in');
			var modalContentBody = modal.find('.modal-body');
			App.blockUI(modal, false);
			var $pagingPath = $(".modal-body input[name='pagingPath']");
			if($pagingPath.length > 0) {
				var pagingPath = $pagingPath.val();
				finalUrl.replace('pagingPath', encodeURIComponent(pagingPath));
			}
			$.ajax({
				type : "GET",
				cache : false,
				url : finalUrl.get(),
				dataType : "html",
				success: function (res) {
					modal.html(res);
					App.initAjax(); // initialize core stuff
					App.unblockUI(modal);
				},
				error: function (xhr, ajaxOptions, thrownError) {
					//使用自定义的错误页面  by YLM
					if(xhr.status === 404) {
						modalContentBody.html(error404Page);
					} else if(xhr.status === 500) {
						modalContentBody.html(error500Page);
					} else {
						modalContentBody.html(errorElsePage);
					}
					App.unblockUI(modal);
				}
			});
		}
	};
});