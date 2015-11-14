/**
 * 表格复选框工具
 */
define(["bootbox"], function(Bootbox) {
	'use strict';

	Bootbox.setLocale('zh_CN');

	var handleCheckbox = function() {
		$('.modal-body .table .group-checkable').change(function() {
			var set = $(this).attr('data-set');
			var checked = $(this).is(':checked');
			$(this).parents('span').toggleClass('checked');
			$(set).each(function() {
				if (checked) {
					$(this).attr('checked', true);
					$(this).parents('span').addClass('checked');
					$(this).parents('tr').addClass('active');
				} else {
					$(this).attr('checked', false);
					$(this).parents('span').removeClass('checked');
					$(this).parents('tr').removeClass('active');
				}
			});
		});

		$('.modal-body .table .checkboxes').change(function() {
			$(this).parents('span').toggleClass('checked');
			$(this).parents('tr').toggleClass('active');
		});
	};

	var handleBatchConfirm = function() {
		if ($('.modal-body .bootbox-batch-confirm').length > 0) {
			$('.modal-body .bootbox-batch-confirm').on('click', function() {
				var ids = '';
				$('.modal-body .table .checkboxes').each(function() {
					if ($(this).is(':checked')) {
						ids += $(this).val() + ',';
					}
				});
				if (ids.length > 0) {
					ids = ids.substring(0, ids.length - 1);
				} else {
					App.toastError('请勾选批量操作的记录');
					return;
				}

				var path = $(this).attr('url');
				var url = new URL(path);
				url.replace('ids', ids);
				var $pagingPath = $('.modal-body input[name="pagingPath"]');
				if ($pagingPath.length > 0) {
					var pagingPath = $pagingPath.val();
					url.replace('pagingPath', encodeURIComponent(pagingPath));
				}
				Bootbox.confirm($(this).attr('confirm'), function(result) {
					if (result) {
						App.ajaxToModal(url.get());
					}
				});
			});
		}
	};

	var handlePath = function() {
		var $input = $('.modal-body input[name="pagingPath"]');
		if ($input.length > 0) {
			return;
		}
		var path = $('.modal-body .table input[name="path"]').val();
		var pathInput = '<input type="hidden" name="pagingPath" value="' + path + '">';
		$(pathInput).insertAfter('.modal-body .table');
	};

	return {
		init: function() {
			handlePath();
			handleCheckbox();
			handleBatchConfirm();
		}
	};
});