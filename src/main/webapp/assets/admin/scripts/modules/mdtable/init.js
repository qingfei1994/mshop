define(['modules/mdtable/modal-table-pagination', 'modules/mdtable/modal-table-sort', 'modules/mdtable/modal-table-query-form', 'modules/mdtable/modal-table-checkbox'],
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
