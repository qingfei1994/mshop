/**
 * 表格排序工具
 */
define(["modules/mdtable/modal-table-ajaxify"], function(Ajaxify) {
	"use strict";
	
	var sort = function(property, order) {
		var path = $('.modal-body input[name="pagingPath"]').val();
		var url = new URL(path);
		url.replace('sort_key', property + ',' + order);
		url.replace('pageNo', 1); // 排序时重置页码
		Ajaxify.ajaxTableToModal(url.get());
	};

	var handleSortTable = function() {
		if (!Util.isBlank(md_t_sort_key)) {
			var temp = md_t_sort_key.split(',');
			if (temp.length == 2) {
				var $property = $('.modal-body .sort-table th[key="' + temp[0] + '"]');
				var css = 'sorting_' + temp[1];
				$property.addClass(css).removeClass('sorting');
			}
		}

		var callback = function(e) {
			var property = $(this).attr('key');
			if ($(this).hasClass('sorting')) {
				$('.modal-body .sort-table .sorting_asc').addClass('sorting').removeClass('sorting_asc');
				$('.modal-body .sort-table .sorting_desc').addClass('sorting').removeClass('sorting_desc');
				$(this).addClass('sorting_asc');
				$(this).removeClass('sorting');

				sort(property, 'asc');
			} else if ($(this).hasClass('sorting_asc')) {
				$(this).addClass('sorting_desc');
				$(this).removeClass('sorting_asc');

				sort(property, 'desc');
			} else if ($(this).hasClass('sorting_desc')) {
				$(this).addClass('sorting_asc');
				$(this).removeClass('sorting_desc');

				sort(property, 'asc');
			}
		};

		$('.modal-body .sort-table .sorting').on('click', callback);
		$('.modal-body .sort-table .sorting_asc').on('click', callback);
		$('.modal-body .sort-table .sorting_desc').on('click', callback);
	};

	var handlePath = function() {
		var $input = $('.modal-body input[name="pagingPath"]');
		if ($input.length < 1) {
			var path = $('.modal-body .sort-table input[name="path"]').val();
			var pathInput = '<input type="hidden" name="pagingPath" value="' + path + '">';
			$(pathInput).insertAfter('.modal-body .sort-table');
			$input = $('.modal-body input[name="pagingPath"]');
		}
		if (!Util.isBlank(md_t_sort_key)) {
			var path = $input.val();
			var url = new URL(path);
			url.replace('sort_key', md_t_sort_key);
			$input.val(url.get());
		}
	};

	return {
		init: function() {
			var $sortTable = $('.modal-body .sort-table');
			if ($sortTable.length > 0) {
				handlePath();
				handleSortTable();
			}
		}
	};
});