define(['admin/goods/classification_list', 'admin/goods/goods_list', 'admin/goods/goods_edit'],
	function(ClassificationList, GoodsList, GoodsEdit) {
	return {
		init: function() {
			ClassificationList.init();
			GoodsList.init();
			GoodsEdit.init();
		}
	};
});