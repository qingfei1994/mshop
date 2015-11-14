<script>
	require(["modules/form/init","modules/upload/init","modules/dialog/init","admin/goods/init"],function(ClassificationList,Form,Upload,Dialog){
		$(document).ready(function(){
			ClassificationList.init();
			Form.init();
			Upload.init();
			Dialog.init();
		});
	});
</script>
<script>
	$(document).ready(function(){
		$('.addNewShop').on('click',function(){
			if($(this).parent().parent().parent().find('.dd-list > .row:last > .col-md-4').length % 3 == 0){
				var html = '<div class="row">' ;
				html+='<div class="col-md-4">';
				html+='<div class="portlet">';
				html+='<div class="portlet-body">';
				html+='<input id="fileUpload" name="file" type="file" style="display: none;" />';
				html+='<a class="choose-image" href="javascript:;">';
			    html+='<img src="http://dummyimage.com/640x320/ddd/555"  style="max-width: 100%" />';
			    html+='</a>';
			    html+='<div class="portlet-title">';
			    html+='<div class="caption">';
			    html+='<a href="javascript:;" class=" btn-sm btn-default modal-ajax-static" url="wap/shop/carousel/edit">选择链接</a>';
			    html+=' <div class="tools">';
			    html+='<a href="javascript:;" class="remove bootbox-confirm" confirm="确定删除？" url="wap/shop/carousel/delete"></a>';
				html+='</div>';
				html+='</div>';
				html+='</div>';
				html+='</div>';
				html+='</div>';
				html+='</div>';
				html+='</div>';
				$(this).parent().parent().parent().find('.dd-list').append(html);
			}else if($(this).parent().parent().parent().find('.dd-list > .row:last > .col-md-4').length % 3 > 0){
				var html = '<div class="col-md-4">';
				html+='<div class="portlet">';
				html+='<div class="portlet-body">';
				html+='<input id="fileUpload" name="file" type="file" style="display: none;" />';
				html+='<a class="choose-image" href="javascript:;">';
			    html+='<img src="http://dummyimage.com/640x320/ddd/555"  style="max-width: 100%" />';
			    html+='</a>';
			    html+='<div class="portlet-title">';
			    html+='<div class="caption">';
			    html+='<a href="javascript:;" class=" btn-sm btn-default modal-ajax-static" url="wap/shop/carousel/edit">选择链接</a>';
			    html+=' <div class="tools">';
			    html+='<a href="javascript:;" class="remove bootbox-confirm" confirm="确定删除？" url="wap/shop/carousel/delete"></a>';
				html+='</div>';
				html+='</div>';
				html+='</div>';
				html+='</div>';
				html+='</div>';
				html+='</div>';
				$(this).parent().parent().parent().find('.dd-list > .row:last').append(html);
			}
		});
		
		$('.portlet-body .choose-image').on('click', function() {
            $(this).prev('input[name="file"]').click();
        });
		
		$('.portlet-body').on('change','#fileUpload',function(){
			$(this).next('a').children('img').attr("src","http://dummyimage.com/320x320/ddd/555");
		});
	});
</script>
<link href="${base}/assets/plugins/data-tables/DT_bootstrap.css" rel="stylesheet" type="text/css"/>
<link href="${base}/assets/plugins/jquery-nestable/jquery.nestable.css" rel="stylesheet" type="text/css"/>
<div class="row">
 <div class="col-md-12">
 
 	<!--BEGIN HEADER-->
 	<div class="content-header">
            <div class="form-inline form-body">
                <div class="form-left">
                    <div class="form-group">
                        <div class="title">
                            <i class="icon-list-alt"></i>商品展示(最多10张)
                        </div>
                    </div>
                </div>
                <div class="form-right">
                    <div class="form-group last">
                    	<a href="javascript:;" class="btn btn-sm btn-success modal-ajax-static" url="admin/goods/classification/addparent"><span class="icon-plus"></span> 新增分组</a>
                    	<a id="classificationListToggle" class="btn btn-sm btn-default" data-action="collapse-all"><span class="icon-minus"></span> 折叠全部</a>
                    </div>
                </div>
			</div>
	</div>
	<!--END HEADER-->
	
	<!--BEGIN BODY-->
    <div class="content-body portlet">
    	<div class="portlet-body form-inline">
    	<div class="dd" id="classificationList">
    		<ol class="dd-list">
    			<li class="dd-item" data-id="1">
    			<div class="dd-handle">
						<img width="35" height="35" src="http://dummyimage.com/35x35/ddd/555"></img>
						<span id="groupName">大西瓜</span>
						<span class="pull-right">
						<a href="javascript:;" class="addNewShop" url=""><i class="btn btn-sm icon-plus"></i></a>
						<a href="javascript:;" class="modal-ajax-static" url="admin/goods/classification/1/edit"><i class="btn btn-sm icon-pencil"></i></a>
						<a url="admin/goods/classification/1/delete" confirm="确定删除吗？" class="bootbox-confirm" href="javascript:;"><i class="btn btn-sm icon-trash"></i></a>
						</span>
				</div>
				
				 <ol class="dd-list">
				 <div class="row">
				 	<div class="col-md-4">
		    		<div class="portlet">
			    	  <div class="portlet-body">
			    	  			<input id="fileUpload" name="file" type="file" style="display: none;" />
			    	  			<a class="choose-image" href="javascript:;">
			    					<img src="http://dummyimage.com/640x320/ddd/555"  style="max-width: 100%" />
			         			</a>
			          </div>
			    	  <div class="portlet-title">
				    	  <div class="caption">
				    	  	<a href="javascript:;" class=" btn-sm btn-default modal-ajax-static" url="wap/shop/carousel/edit">选择链接</a>
				    	  	<#--<span class="help-inline">广州米所思信息科技有限公司</span>-->
				    	  </div>
				    	  <div class="tools">
				    	  	<a href="javascript:;" class="remove bootbox-confirm" confirm="确定删除？" url="wap/shop/carousel/delete"></a>
				    	  </div>
			    	  </div>
		    	</div>
		    </div>
				 	<div class="col-md-4">
		    		<div class="portlet">
			    	  <div class="portlet-body">
			    	  			<input id="fileUpload" name="file" type="file" style="display: none;" />
			    	  			<a class="choose-image" href="javascript:;">
			    					<img src="http://dummyimage.com/640x320/ddd/555"  style="max-width: 100%" />
			         			</a>
			          </div>
			    	  <div class="portlet-title">
				    	  <div class="caption">
				    	  	<a href="javascript:;" class=" btn-sm btn-default modal-ajax-static" url="wap/shop/carousel/edit">选择链接</a>
				    	  	<#--<span class="help-inline">广州米所思信息科技有限公司</span>-->
				    	  </div>
				    	  <div class="tools">
				    	  	<a href="javascript:;" class="remove bootbox-confirm" confirm="确定删除？" url="wap/shop/carousel/delete"></a>
				    	  </div>
			    	  </div>
		    	</div>
		    </div>
					<div class="col-md-4">
		    	<div class="portlet">
			    	  <div class="portlet-body">
			    	  			<input id="fileUpload" name="file" type="file" style="display: none;" />
			    	  			<a class="choose-image" href="javascript:;">
			    					<img src="http://dummyimage.com/640x320/ddd/555"  style="max-width: 100%" />
			         			</a>
			          </div>
			    	  <div class="portlet-title">
				    	  <div class="caption">
				    	  	<a href="javascript:;" class=" btn-sm btn-default modal-ajax-static" url="wap/shop/carousel/edit">选择链接</a>
							<#--<span class="help-inline">广州米所思信息科技有限公司</span>-->
				    	  </div>
				    	  <div class="tools">
				    	  	<a href="javascript:;" class="remove bootbox-confirm" confirm="确定删除？" url="wap/shop/carousel/delete"></a>
				    	  </div>
			    	  </div>
		    	</div>
		    </div>
		    </div>
				 </ol>
				</li>
    		</ol>
    		<ol class="dd-list">
    			<li class="dd-item" data-id="1">
    			<div class="dd-handle">
						<img width="35" height="35" src="http://dummyimage.com/35x35/ddd/555"></img>
						<span id="groupName">大冬瓜</span>
						<span class="pull-right">
						<a href="javascript:;" class="modal-ajax-static" url="admin/goods/classification/1/add"><i class="btn btn-sm icon-plus"></i></a>
						<a href="javascript:;" class="modal-ajax-static" url="admin/goods/classification/1/edit"><i class="btn btn-sm icon-pencil"></i></a>
						<a url="admin/goods/classification/1/delete" confirm="确定删除吗？" class="bootbox-confirm" href="javascript:;"><i class="btn btn-sm icon-trash"></i></a>
						</span>
				</div>
				
				 <ol class="dd-list">
				 <div class="row">
				 	<div class="col-md-4">
		    		<div class="portlet">
			    	  <div class="portlet-body">
			    	  			<input id="fileUpload" name="file" type="file" style="display: none;" />
			    	  			<a class="choose-image" href="javascript:;">
			    					<img src="http://dummyimage.com/640x320/ddd/555"  style="max-width: 100%" />
			         			</a>
			          </div>
			    	  <div class="portlet-title">
				    	  <div class="caption">
				    	  	<a href="javascript:;" class=" btn-sm btn-default modal-ajax-static" url="wap/shop/carousel/edit">选择链接</a>
				    	  	<#--<span class="help-inline">广州米所思信息科技有限公司</span>-->
				    	  </div>
				    	  <div class="tools">
				    	  	<a href="javascript:;" class="remove bootbox-confirm" confirm="确定删除？" url="wap/shop/carousel/delete"></a>
				    	  </div>
			    	  </div>
		    	</div>
		    </div>
				 	<div class="col-md-4">
		    		<div class="portlet">
			    	  <div class="portlet-body">
			    	  			<input id="fileUpload" name="file" type="file" style="display: none;" />
			    	  			<a class="choose-image" href="javascript:;">
			    					<img src="http://dummyimage.com/640x320/ddd/555"  style="max-width: 100%" />
			         			</a>
			          </div>
			    	  <div class="portlet-title">
				    	  <div class="caption">
				    	  	<a href="javascript:;" class=" btn-sm btn-default modal-ajax-static" url="wap/shop/carousel/edit">选择链接</a>
				    	  	<#--<span class="help-inline">广州米所思信息科技有限公司</span>-->
				    	  </div>
				    	  <div class="tools">
				    	  	<a href="javascript:;" class="remove bootbox-confirm" confirm="确定删除？" url="wap/shop/carousel/delete"></a>
				    	  </div>
			    	  </div>
		    	</div>
		    </div>
					<div class="col-md-4">
		    	<div class="portlet">
			    	  <div class="portlet-body">
			    	  			<input id="fileUpload" name="file" type="file" style="display: none;" />
			    	  			<a class="choose-image" href="javascript:;">
			    					<img src="http://dummyimage.com/640x320/ddd/555"  style="max-width: 100%" />
			         			</a>
			          </div>
			    	  <div class="portlet-title">
				    	  <div class="caption">
				    	  	<a href="javascript:;" class=" btn-sm btn-default modal-ajax-static" url="wap/shop/carousel/edit">选择链接</a>
							<#--<span class="help-inline">广州米所思信息科技有限公司</span>-->
				    	  </div>
				    	  <div class="tools">
				    	  	<a href="javascript:;" class="remove bootbox-confirm" confirm="确定删除？" url="wap/shop/carousel/delete"></a>
				    	  </div>
			    	  </div>
		    	</div>
		    </div>
		    </div>
				 </ol>
				</li>
    		</ol>
		</div>
    </div>
    </div>
    <!--END CONTENT-BODY-->
    <!--BEGIN FOOTER-->
    <div class="form-actions fluid">
        <div class="col-md-offset-4 col-md-8">                        
            <button type="submit" class="btn btn-success ajax-submit" url="wap/shop/carousel/save"><i class="icon-ok">保存</i></button> 
            <button type="button" class="btn btn-default">取消</button>
        </div>
    </div>
   </div>
</div>
