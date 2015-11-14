define(['jquery_nestable'],function(){
	"use strict";
	
	var initClassificationList = function() {
		// 初始化
        $('#classificationList').nestable({
            group: 0,//去看看初始化插件的具体配置参数还有哪些，分别有什么作用
            maxDepth:2,//可以嵌套的最大数量
        }).on('change', function (e) {
            //处理右侧操作按钮的变化
        });
        $('#classificationListToggle').on('click', function (e) {
            var action = $(this).data('action');
            if (action === 'expand-all') {
                $('#classificationList').nestable('expandAll');
                $('#classificationListToggle').html('<span class="icon-minus"></span> 折叠全部');
                $(this).data('action', 'collapse-all');
            }
            if (action === 'collapse-all') {
                $('#classificationList').nestable('collapseAll');
                $('#classificationListToggle').html('<span class="icon-plus"></span> 展开全部');
                $(this).data('action', 'expand-all');
            }
        });
	};
    
    var handleSaveClassification=function(){
    	$('#saveGroupBtn').on('click',function(){
    		var	nestableData = $('#classificationList').nestable('serialize');
    		var data = JSON.stringify(nestableData);
			App.post('admin/goods/classification/save', {data: data}, function(res) {
				if (res.success == 1) {
                	App.toastSuccess("保存成功");
                }
                if(res.success == 0){
                    App.toastError("保存失败");
                }
            });
		});
    };

    return {
        //main function to initiate the module
        init: function () {
        	initClassificationList();
            handleSaveClassification();
        }
    };
	
});