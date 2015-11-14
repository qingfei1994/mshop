/**
 * 表格ajax请求工具
 */
define(function() {
	'use strict';

	return {
		ajaxTableToPage: function(url) {
			var finalUrl = new URL(url);
			var pageContent = $('.page-content');
			var pageContentBody = $('.page-content .page-content-body');
			App.blockUI(pageContent, false);
			var $pagingPath = $(".page-content-body input[name='pagingPath']");
			if ($pagingPath.length > 0) {
				var pagingPath = $pagingPath.val();
				finalUrl.replace('pagingPath', encodeURIComponent(pagingPath));
			}
			$.ajax({
				type : "GET",
				cache : false,
				url : url,
				dataType : "html",
				success: function(res) {
					App.scrollTop();
					pageContentBody.html(res);
					App.fixContentHeight(); // fix content height
					App.initAjax(); // initialize core stuff
					App.unblockUI(pageContent);
				},
				error: function(xhr, ajaxOptions, thrownError) {
					App.scrollTop();
					//使用自定义的错误页面  by YLM
					if (xhr.status === 404) {
						pageContentBody.html(error404Page);
					} else if (xhr.status === 500) {
						pageContentBody.html(error500Page);
					} else {
						pageContentBody.html(errorElsePage);
					}
					App.unblockUI(pageContent);
				}
			});
		}
	};
});