define(['modules/form/form-submit', 'modules/form/form-valid', 'modules/form/form-confirm', 'modules/form/form-tip'], function(Submit, Valid, Confirm, Tip){
	return {
		init: function() {
			Submit.init();
			Valid.init();
			Confirm.init();
			Tip.init();
		}
	};
});