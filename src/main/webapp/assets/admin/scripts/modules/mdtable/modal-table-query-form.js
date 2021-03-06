/**
 * 列表查询工具
 */
define(["modules/mdtable/modal-table-ajaxify"], function(Ajaxify) {
	"use strict";
	
	/**
	 * 解释后台回传回来的查询参数
	 */
	var handleParseQueryList = function() {
		if ($.trim(md_f_query_list) != '') {
			var queryList = $.parseJSON(md_f_query_list);
			var path = $('.modal-body input[name="pagingPath"]').val();
			var url = new URL(path);

			for (var i in queryList) {
				var queryItem = queryList[i];
				var name = queryItem.property;
				var operator = getOperator(queryItem.operator);
				var value = queryItem.value;
				if (operator == 'is') {
					value = value.replace(" ", "_");
				}

				var $input = $('.modal-body input[name="' + name + '"][operator="' + operator + '"]');
				var $select = $('.modal-body select[name="' + name + '"][operator="' + operator + '"]');
				if ($input.length > 0 && !$input.attr('condition')) {
					if ($input.attr('type') == 'checkbox') {
						$input.attr('checked', true);
						if ($input.parent().parent().hasClass('checker')) {
							$input.parent().addClass('checked');
						}
					} else {
						$input.val(value);
					}
				}
				if ($select.length > 0) {
					$select.find('option[value=' + value + ']').attr('selected', 'selected');
				}

				url.replace(queryItem.queryKey, value);
			}

			$('.modal-body input[name="pagingPath"]').val(url.get());
		}
	};

	var getOperator = function(operator) {
		switch (operator) {
			case '=':
				operator = 'eq';
				break;
			case '>':
				operator = 'gt';
				break;
			case '>=':
				operator = 'ge';
				break;
			case '<':
				operator = 'lt';
				break;
			case '<=':
				operator = 'le';
				break;
		}
		return operator;
	};

	/**
	 * 拼接传到后台的查询参数
	 */
	var handleGatherQueryList = function() {
		var path = $('.modal-body input[name="pagingPath"]').val();
		var url = new URL(path);

		$('.modal-body .query-form .form-group input, .modal-body .query-form .form-group > select').each(function() {
			var operator = $(this).attr('operator');
			var type = getType($(this).attr('data-type'));
			var name = $(this).attr('name');
			if (!name) {
				return;
			}
			var queryKey = 'q_' + operator + '_' + type + '_' + name;

			if ($(this).attr('condition')) {
				return;
			}

			var value;
			if ($(this).attr('type') == 'checkbox') {
				if ($(this).is(':checked')) {
					value = true;
				} else {
					value = '';
				}
			} else {
				value = $(this).val();
			}

			var domObject = $(this)[0];
			if (domObject.tagName == 'SELECT') {
				var $option = $(this).find('option:selected');
				value += getCondition($option);
			}

			url.replace(queryKey, value);
		});

		url.replace('pageNo', 1); // 查询时重置页码
		Ajaxify.ajaxTableToModal(url.get());
	};

	var getType = function(dataType) {
		var type = 's'; // 默认为字符串类型
		if (dataType === 'string') {
			type = 's';
		} else if (dataType === 'number') {
			type = 'n';
		} else if (dataType === 'date') {
			type = 'd';
		}
		return type;
	};

	var getCondition = function($el) {
		var val = '';
		if ($el.attr('data-set')) {
			var dataSet = $el.attr('data-set');
			var ids = dataSet.split(':');
			for (var i in ids) {
				var id = ids[i];
				var $input = $(id);
				var condition = $input.attr('condition');
				var operator = $input.attr('operator');
				var type = getType($input.attr('data-type'));
				var name = $input.attr('name');
				var value = $input.val();
				val += "_" + condition + "_" + 'q_' + operator + '_' + type + '_' + name + '=' + value;
			}
		}
		return val;
	};

	/**
	 * 绑定表单事件
	 */
	var handleBindEvents = function() {
		$('.modal-body .query-form .form-group .query').on('click', function() {
			handleGatherQueryList();
		});

		$('.modal-body .query-form .form-group .reset').on('click', function() {
			$('.modal-body .query-form')[0].reset();
			$('.modal-body .query-form .form-group > select').each(function() {
				$(this).find('option[value=""]').attr('selected', 'selected');
			});
			$('.modal-body .query-form .form-group input[type="checkbox"]').each(function() {
				$(this).attr('checked', false);
				if ($(this).parent().parent().hasClass('checker')) {
					$(this).parent().removeClass('checked');
				}
			});
			handleGatherQueryList();
		});

		$('.modal-body .query-form .form-group > input').on('keydown', function(e) {
			if (e.keyCode === 13) {
				handleGatherQueryList();
			}
		});

		$('.modal-body .query-form .form-group > select').on('change', function() {
			handleGatherQueryList();
		});

		$('.modal-body .query-form .form-group input[type="checkbox"]').on('change', function() {
			handleGatherQueryList();
		});
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
			handleParseQueryList();
			handleBindEvents();
		}
	};
});