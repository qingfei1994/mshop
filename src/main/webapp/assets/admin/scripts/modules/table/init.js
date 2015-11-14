define(['modules/table/table-pagination', 'modules/table/table-sort', 'modules/table/table-query-form', 'modules/table/table-checkbox'],
	function(Pagination, Sort, QueryForm, Checkbox){
	return {
		init: function() {
			Pagination.init();
			Sort.init();
			QueryForm.init();
			Checkbox.init();
		}
	};
});
