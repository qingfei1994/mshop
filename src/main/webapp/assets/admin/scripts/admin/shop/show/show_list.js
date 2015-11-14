define(['jquery_nestable','ajaxfileupload'],function(){
	"use strict";
	
	var initNestableList = function () {
		  $('#goodsList').nestable({
	            group: 0,
	            maxDepth:2,//可以嵌套的最大数量
	        }).on('change', function (e) {
	            //处理右侧操作按钮的变化
	        });
	      $('#classificationListToggle').on('click', function (e) {
	            var action = $(this).data('action');
	            if (action === 'expand-all') {
	                $('#goodsList').nestable('expandAll');
	                $('#classificationListToggle').html('<span class="icon-minus"></span> 折叠全部');
	                $(this).data('action', 'collapse-all');
	            }
	            if (action === 'collapse-all') {
	                $('#goodsList').nestable('collapseAll');
	                $('#classificationListToggle').html('<span class="icon-plus"></span> 展开全部');
	                $(this).data('action', 'expand-all');
	            }
	        });
	}
	/*var handleAddShow = function(){
		$('.addNewShop').on('click',function(){
			if($(this).parent().parent().parent().find('.dd-list > .row:last > .col-md-4').length % 3 == 0){
				var html = '<div class="row">' ;
				html+='<div class="col-md-4">';
				html+='<div class="portlet" id="new2">';
				html+='<div class="portlet-body">';
				html+='<a class="choose-image" href="javascript:;">';
			    html+='<img src="http://dummyimage.com/640x640/ddd/555"  style="max-width: 100%" />';
			    html+='</a>';
			    html+='</div>';
			    html+='<div class="portlet-title">';
			    html+='<div class="caption">';
			    html+='<a href="javascript:;" class=" btn-sm btn-default modal-ajax-static" url="admin/shop/show/goodsInformation/list">选择链接</a>';
			    html+='</div>';
			    html+='<div class="tools">';
			    html+='<a href="javascript:;" class="remove bootbox-confirm" confirm="确定删除？" url="admin/shop/show/delete"></a>';
				html+='</div>';
				html+='</div>';
				html+='</div>';
				html+='</div>';
				html+='</div>';
				$(this).parent().parent().parent().find('.dd-list').append(html);
			}else if($(this).parent().parent().parent().find('.dd-list > .row:last > .col-md-4').length % 3 > 0){
				var html = '<div class="col-md-4">';
				html+='<div class="portlet" id="new1">';
				html+='<div class="portlet-body">';
				html+='<a class="choose-image" href="javascript:;">';
			    html+='<img src="http://dummyimage.com/640x640/ddd/555"  style="max-width: 100%" />';
			    html+='</a>';
			    html+='</div>';
			    html+='<div class="portlet-title">';
			    html+='<div class="caption">';
			    html+='<a href="javascript:;" class=" btn-sm btn-default modal-ajax-static" url="admin/shop/show/goodsInformation/list">选择链接</a>';
			    html+='</div>';
			    html+=' <div class="tools">';
			    html+='<a href="javascript:;" class="remove bootbox-confirm" confirm="确定删除？" url="admin/shop/show/delete"></a>';
				html+='</div>';
				html+='</div>';
				html+='</div>';
				html+='</div>';
				$(this).parent().parent().parent().find('.dd-list > .row:last').append(html);
			}
		});
	};*/
	
	var handleUploadPic = function(){
		var dataId ;
		$('.content-body').on('click','.choose-image', function() {
			dataId = $(this).closest('.portlet').attr("id");
            $('#fileUpload').click();
        });
		
		$('.content-body').on('change','#fileUpload',function(){
			var width = 320, height = 320;
            $.ajaxFileUpload({
                url: base+'/common/upload/image',
                type: 'post',
                secureuri: false,
                fileElementId: 'fileUpload',
                dataType: 'json',
                data:{"width": width, "height": height},
                success: function(data) {
                    if (data && data.success == 1) {
                    	$('#'+dataId+' > .portlet-body > .choose-image > img').attr("src",data.src);
                    } else {
                        alert('图片上传失败');
                    }
                },
                error: function(data) {
                    alert('图片上传失败');
                }
            });
		});
	};
	var handleSaveClassification =  function () {
		$('#saveButton').on('click',function(){
			var check=true;
			var	nestableData = $('#goodsList').nestable('serialize');
			for(var i=0;i<nestableData.length;i++) {
				var id=nestableData[i].id;
				if(id.substr(0,5)=="child") {
					check=false;
					break;
				}
				var children=nestableData[i].children;
				if(children!=undefined) {
					for(var j=0;j<children.length;j++) {
						var child=children[j].id;
						if(child.substr(0,6)=="parent") {
							check=false;
							break;
						}
					} 
				}
			}
			console.log(JSON.stringify(nestableData));
			if(!check) {
				App.toastError("分组和商品位置不正确！");
			} else {
				App.post("admin/shop/show/save",{data:JSON.stringify(nestableData)});
			}
			
		});
	}
	
	return{
		init : function(){
			initNestableList();
			handleUploadPic();
			handleSaveClassification();
		}
	};
});