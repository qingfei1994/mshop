<script>
	require(["modules/form/init","modules/upload/init","modules/dialog/init",'admin/shop/show/init'],function(Form,Upload,Dialog,Show){
		$(document).ready(function(){
			Show.init();
			Form.init();
			Upload.init();
			Dialog.init();
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
                            <i class="icon-list-alt"></i>商品展示
                        </div>
                    </div>
                </div>
                <div class="form-right">
                    <div class="form-group last">
                    	<a href="javascript:;" class="btn btn-sm btn-success modal-ajax-static" url="admin/shop/show/addparent"><span class="icon-plus"></span> 新增分组</a>
                    	<a id="classificationListToggle" class="btn btn-sm btn-default" data-action="collapse-all"><span class="icon-minus"></span> 折叠全部</a>
                    </div>
                </div>
			</div>
	</div>
	<!--END HEADER-->
	
	<!--BEGIN BODY-->
    <div class="content-body portlet">
   	 	<input id="fileUpload" name="file" type="file" style="display: none;" />
    	<div class="portlet-body form-inline">
    		<div class="dd" id="goodsList">
    	 		<ol class="dd-list">
    			<#if groups??>
	    			<#list groups as group>
	    			<li class="dd-item" data-id="parent${(group.ingrId)!}">
	    			<div class="dd-handle">
						<img width="35" height="35" src="${(group.ingrIconUrl)!}"></img>
						<span id="groupName">${(group.ingrName)!}</span>
						<span class="pull-right">
							<a href="javascript:;" class="modal-ajax-static" url="admin/shop/show/goodsInformation/list?groupId=${(group.ingrId)!}"><i class="btn btn-sm icon-plus"></i></a>
							<a href="javascript:;" class="modal-ajax-static" url="admin/shop/show/editParent?ingrId=${(group.ingrId)!}"><i class="btn btn-sm icon-pencil"></i></a>
							<a url="admin/shop/show/deleteParent?ingrId=${(group.ingrId)!}" confirm="确定删除吗？" class="bootbox-confirm" href="javascript:;"><i class="btn btn-sm icon-trash"></i></a>
						</span>
					</div>
	    		<#if group.indexGoodses??>
	    			<ol class="dd-list">
	    			<#list group.indexGoodses as indexGoods>
	    				<li class="dd-item" data-id="child${(indexGoods.ingoId)!}">
							<div class="dd-handle">
								<img width="35" height="35" src="${(indexGoods.goods.goodFirstPicture)!}"></img>
								<span id="groupName">${(indexGoods.goods.goodName)!}</span>
								<span class="pull-right">
									<a href="javascript:;" class="modal-ajax-static" url="admin/shop/show/goodsInformation/list?groupId=${(indexGoods.ingrId)}&ingoId=${(indexGoods.ingoId)!}"><i class="btn btn-sm icon-pencil"></i></a>
									<a url="admin/shop/show/deleteChild?ingoId=${(indexGoods.ingoId)!}" confirm="确定删除吗？" class="bootbox-confirm" href="javascript:;"><i class="btn btn-sm icon-trash"></i></a>
								</span>
							</div>
						</li>
	    			</#list>
	    			</ol>
	    		</#if>
	    		</li>
	    	</#list>
    	</#if>
    	</ol>
		</div>
    </div>
    </div>
    <!--END CONTENT-BODY-->
    <!--BEGIN FOOTER-->
    <div class="form-actions fluid">
        <div class="col-md-offset-4 col-md-8">                        
            <button type="submit" class="btn btn-success" id="saveButton" ><i class="icon-ok">保存</i></button> 
            <button type="button" class="btn btn-default">取消</button>
        </div>
    </div>
   </div>
</div>
