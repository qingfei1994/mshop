define(['modules/dialog/dialog-modal', 'modules/dialog/dialog-bootbox'], function(Modal, Bootbox){
	return {
		init: function() {
			Modal.init();
			Bootbox.init();
		}
	};
});
