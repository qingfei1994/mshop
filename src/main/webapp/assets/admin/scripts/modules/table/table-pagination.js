/**
 * 分页工具
 */
define(["modules/table/table-ajaxify"], function(Ajaxify) {
	"use strict";
	
	$.fn.showPagination = function() {
		var pageNo = parseInt($(this).attr('pageNo'));
		var pageSize = parseInt($(this).attr('pageSize'));
		var total = parseInt($(this).attr('total'));
		var pageCount = Math.ceil(total / pageSize);
		var hasPrevious = pageNo > 1;
		var hasNext = pageCount > pageNo;
		
		if (pageCount > 0) {
			var html = '<div class="dataTables_paginate paging_bootstrap"><ul class="pagination">';

			html += '<li class="prev ';
			if (!hasPrevious) {
				html += ' disabled"><a href="javascript:;" title="上一页"><i class="icon-angle-left"></i></a></li>';
			} else {
				html += '"><a href="javascript:;" title="上一页"><i class="icon-angle-left"></i></a></li>';
			}
			
			if (pageCount < 10) {
				for (var i = 1; i <= pageCount; i++) {
					html += '<li';
					if (pageNo === i) {
						html += ' class="active"><a href="javascript:;">' + i + '</a></li>';
					} else {
						html += ' class="inactive"><a href="javascript:;">' + i + '</a></li>';
					}
				}
			} else {
				if (pageNo <= 5) {
					for (var i = 1; i <= 7; i++) {
						html += '<li';
						if (pageNo === i) {
							html += ' class="active"><a href="javascript:;">' + i + '</a></li>';
						} else {
							html += ' class="inactive"><a href="javascript:;">' + i + '</a></li>';
						}
					}
					html += '<li class="disabled"><a>&hellip;</a></li>';
					html += '<li class="inactive"><a href="javascript:;">' + pageCount + '</a></li>';
				} else if (pageNo < pageCount - 4) {
					html += '<li class="inactive"><a href="javascript:;">1</a></li>';
					html += '<li class="disabled"><a>&hellip;</a></li>';
					for (var i = pageNo - 2; i <= pageNo + 2; i++) {
						html += '<li';
						if (pageNo === i) {
							html += ' class="active"><a href="javascript:;">' + i + '</a></li>';
						} else {
							html += ' class="inactive"><a href="javascript:;">' + i + '</a></li>';
						}
					}
					html += '<li class="disabled"><a>&hellip;</a></li>';
					html += '<li class="inactive"><a href="javascript:;">' + pageCount + '</a></li>';
				} else {
					html += '<li class="inactive"><a href="javascript:;">1</a></li>';
					html += '<li class="disabled"><a>&hellip;</a></li>';
					for (var i = pageCount - 6; i <= pageCount; i++) {
						html += '<li';
						if (pageNo === i) {
							html += ' class="active"><a href="javascript:;">' + i + '</a></li>';
						} else {
							html += ' class="inactive"><a href="javascript:;">' + i + '</a></li>';
						}
					}
				}
			}

			html += '<li class="next ';
			if (!hasNext) {
				html += ' disabled"><a href="javascript:;" title="下一页"><i class="icon-angle-right"></i></a></li>';
			} else {
				html += '"><a href="javascript:;" title="下一页"><i class="icon-angle-right"></i></a></li>';
			}
			
			html += '</ul></div>';
			
			$(this).html(html);
		}
	};

	var handlePagination = function() {
		if (p_totalCount > 0) {
			var pagingBody = '<div class="content-footer"><div class="pagination-body">'
					+ '<div class="table-info">'
					+ '共' + p_totalCount + '条记录，当前为第' + p_pageNo + '页'
					+ '</div>'
					+ '<pager class="page-content-pager" pageNo="' + p_pageNo + '"'
					+ 'pageSize="' + p_pageSize + '"'
					+ 'total="' + p_totalCount + '"></pager>'
					+ '</div></div>';
			/*var pagingBody = '<div class="row">'
				+ '<div class="col-md-5 col-sm-12">'
				+ '<div class="dataTables_info">'
				+ '共' + p_totalCount + '条记录，当前为第' + p_pageNo + '页'
				+ '</div>'
				+ '</div>'
				+ '<div class="col-md-7 col-sm-12">'
				+ '<pager class="page-content-pager" pageNo="' + p_pageNo + '"'
				+ 'pageSize="' + p_pageSize + '"'
				+ 'total="' + p_totalCount + '"></pager>'
				+ '</div>'
				+ '</div>';*/


			$(pagingBody).insertAfter($('.page-content-body .portlet'));
			$('.page-content-pager').showPagination();

			$('.page-content-body .pagination .prev a').on('click', function() {
				if ($(this).hasClass('disabled')) {
					return;
				}
				var path = $('.page-content-body input[name="pagingPath"]').val();
				var url = new URL(path);
				var pageNo = parseInt(url.getParam('pageNo'));
				if (pageNo > 1) {
					pageNo -= 1;
					url.replace('pageNo', pageNo);
					Ajaxify.ajaxTableToPage(url.get());
				}
			});

			$('.page-content-body .pagination .next a').on('click', function() {
				if ($(this).hasClass('disabled')) {
					return;
				}
				var path = $('input[name="pagingPath"]').val();
				var url = new URL(path);
				var pageNo = parseInt(url.getParam('pageNo'));
				var pageCount = Math.ceil(p_totalCount / p_pageSize);
				if (pageNo < pageCount) {
					pageNo += 1;
					url.replace('pageNo', pageNo);
					Ajaxify.ajaxTableToPage(url.get());
				}
			});

			$('.page-content-body .pagination .inactive a').on('click', function() {
				var path = $('input[name="pagingPath"]').val();
				var url = new URL(path);
				var pageNo = parseInt($(this).html().trim());
				url.replace('pageNo', pageNo);
				Ajaxify.ajaxTableToPage(url.get());
			});
		}
	};

	var handlePagingControl = function() {
		$('.page-content-body .paging-table-control').on('change', function() {
			var pageSize = $(this).val();
			var path = $('.page-content-body input[name="pagingPath"]').val();

			var url = new URL(path);
			url.replace('pageSize', pageSize);

			Ajaxify.ajaxTableToPage(url.get());
		});
	};

	var handlePath = function() {
		var path = $('.page-content-body .paging-table input[name="path"]').val();
		var url = new URL(path);
		url.replace('pageNo', p_pageNo);
		url.replace('pageSize', p_pageSize);
		var pathInput = '<input type="hidden" name="pagingPath" value="' + url.get() + '">';
		$(pathInput).insertAfter('.page-content-body .paging-table');
	};

	return {
		init: function() {
			var $pagingTable = $('.page-content-body .paging-table');
			if ($pagingTable.length > 0) {
				handlePath();
				handlePagingControl();
				handlePagination();
			}
		}
	};
});