define(function() {
	"use strict";

	// 点击某一行商品
	var handleIcon = function() {
		$(".modal-body .form-group .gooditem").on(
				'click',
				function() {
					$('.gooditem').find('.icon-ok').addClass("icon-remove")
							.removeClass("icon-ok");
					$(this).find('.icon-remove').addClass("icon-ok")
							.removeClass("icon-remove");
				});
	}

	// 点击Modal的确定按钮
	var handleButton = function() {
		$('.form-body .modal-footer .mybutton').on(
				'click',
				function() {
					var groupId = $('#selectedGroupId').val();
					var ingoId = $('#ingoId').val();
					var goodId = $('.icon-ok').closest('.gooditem').find(
							'td:eq(1) span').attr("data-id");
					App.post("admin/shop/show/addIndexGood", {
						ingoId : ingoId,
						goodId : goodId,
						ingrId : groupId
					});

				});
	}

	return {
		init : function() {
			handleIcon();
			handleButton();
		}
	};
});