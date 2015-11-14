/**
 * 分页工具
 */
define(["modules/mdtable/modal-table-ajaxify"], function(Ajaxify) {
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
		if (md_p_totalCount > 0) {
			var pagingBody = '<div class="row">'
					+ '<div class="col-md-5 col-sm-12">'
					+ '<div class="dataTables_info">'
					+ '共' + md_p_totalCount + '条记录，当前为第' + md_p_pageNo + '页'
					+ '</div>'
					+ '</div>'
					+ '<div class="col-md-7 col-sm-12">'
					+ '<pager class="modal-pager" pageNo="' + md_p_pageNo + '"'
					+ 'pageSize="' + md_p_pageSize + '"'
					+ 'total="' + md_p_totalCount + '"></pager>'
					+ '</div>'
					+ '</div>';

			$(pagingBody).insertAfter($('.modal-body .paging-table'));
			$('.modal-pager').showPagination();

			$('.modal-body .pagination .prev a').on('click', function() {
				if ($(this).hasClass('disabled')) {
					return;
				}
				var path = $('.modal-body input[name="pagingPath"]').val();
				var url = new URL(path);
				var pageNo = parseInt(url.getParam('pageNo'));
				if (pageNo > 1) {
					pageNo -= 1;
					url.replace('pageNo', pageNo);
					Ajaxify.ajaxTableToModal(url.get());
				}
			});

			$('.modal-body .pagination .next a').on('click', function() {
				if ($(this).hasClass('disabled')) {
					return;
				}
				var path = $('.modal-body input[name="pagingPath"]').val();
				var url = new URL(path);
				var pageNo = parseInt(url.getParam('pageNo'));
				var pageCount = Math.ceil(md_p_totalCount / md_p_pageSize);
				if (pageNo < pageCount) {
					pageNo += 1;
					url.replace('pageNo', pageNo);
					Ajaxify.ajaxTableToModal(url.get());
				}
			});

			$('.modal-body .pagination .inactive a').on('click', function() {
				var path = $('input[name="pagingPath"]').val();
				var url = new URL(path);
				var pageNo = parseInt($(this).html().trim());
				url.replace('pageNo', pageNo);
				Ajaxify.ajaxTableToModal(url.get());
			});
		}
	};

	var handlePagingControl = function() {
		$('.modal-body .paging-table-control').on('change', function() {
			var pageSize = $(this).val();
			var path = $('.modal-body input[name="pagingPath"]').val();
			var url = new URL(path);
			url.replace('pageSize', pageSize);

			Ajaxify.ajaxTableToModal(url.get());
		});
	};

	var handlePath = function() {
		var path = $('.modal-body input[name="path"]').val();
		var url = new URL(path);
		url.replace('pageNo', md_p_pageNo);
		url.replace('pageSize', md_p_pageSize);
		var pathInput = '<input type="hidden" name="pagingPath" value="' + url.get() + '">';
		$(pathInput).insertAfter('.modal-body .paging-table');
	};

	return {
		init: function() {
			var $pagingTable = $('.modal-body .paging-table');
			if ($pagingTable.length > 0) {
				handlePath();
				handlePagingControl();
				handlePagination();
			}
		}
	};
});