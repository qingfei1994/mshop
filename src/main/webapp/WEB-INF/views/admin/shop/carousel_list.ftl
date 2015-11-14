<script>
	require(["modules/form/init","modules/upload/init","modules/dialog/init","admin/shop/carousel/init"],function(Carousel,Form,Upload,Dialog){
		$(document).ready(function(){
			Carousel.init();
			Form.init();
			Upload.init();
			Dialog.init();
		});
	});
</script>
<div class="row">
 <div class="col-md-12">
 	<!--BEGIN HEADER-->
 	<div class="content-header">
            <div class="form-inline form-body">
                <div class="form-left">
                    <div class="form-group">
                        <div class="title">
                            <i class="icon-list-alt"></i>轮播图片(最多10张)
                        </div>
                    </div>
                </div>
                <div class="form-right">
                    <div class="form-group last">
                    	<a href="javascript:;" id="addPic" class="btn btn-sm btn-success" url=""><span class="icon-plus"></span>新增图片</a>
                    	<span >建议尺寸:640*320像素</span>
                    </div>
                </div>
			</div>
	</div>
	<!--END HEADER-->
	
	<!--BEGIN BODY-->
    <div class="content-body portlet">
    	<input id="fileUpload" name="file" type="file" style="display: none;" />
    	<#list list as entity>
    		<#if entity_index % 2 == 0>
    			<div class="row">
	    			<div class="col-md-6">
			    	<div class="portlet" id="${entity.inba_id}">
				    	  <div class="portlet-body" >
				    	  			<a class="choose-image" href="javascript:;">
				    					<img src="${(entity.inba_image_url)!}"  style="max-width: 100%" />
				         			</a>
				          </div>
				    	  <div class="portlet-title">
					    	  <div class="caption">
					    	  	<a href="javascript:;" class="myselect btn-sm btn-default modal-ajax-static" url="admin/shop/carousel/goodsInformation/list?inbaId=${(entity.inba_id)!}&goodId=${(entity.good_id)!}">选择链接</a>
					    	  	<span class="help-inline">${(entity.good_name)!}</span>
					    	  	<span id="${entity.inba_id}-goodid" style="display:none">${(entity.good_id)!}</span>
					    	  </div>
					    	  <div class="tools">
					    	  	<a href="javascript:;" class="remove" data-id="${entity.inba_id}"></a>
					    	  </div>
				    	  </div>
			    	</div>
		    	</div>
		    	<#if (entity_index + 1) == (list?size)>
		 				</div>
		 				</div>
		  		</#if>
    		<#elseif entity_index % 2 == 1>
    			<div class="col-md-6">
			    	<div class="portlet" id="${entity.inba_id}">
				    	  <div class="portlet-body">
				    	  			<a class="choose-image" href="javascript:;">
				    					<img src="${(entity.inba_image_url)!}"  style="max-width: 100%" />
				         			</a>
				          </div>
				    	  <div class="portlet-title">
					    	  <div class="caption">
					    	  	<a href="javascript:;" class=" btn-sm btn-default modal-ajax-static" url="admin/shop/carousel/goodsInformation/list?inbaId=${(entity.inba_id)!}&goodId=${(entity.good_id)!}">选择链接</a>
					    	  	<span class="help-inline">${(entity.good_name)!}</span>
					    	  	<span id="${entity.inba_id}-goodid" style="display:none">${(entity.good_id)!}</span>
					    	  </div>
					    	  <div class="tools">
					    	  	<a href="javascript:;" class="remove" confirm="确定删除？" data-id="${entity.inba_id}"></a>
					    	  </div>
				    	  </div>
			    	</div>
		    	</div>
		    	</div>
		    	<#if (entity_index + 1) == (list?size)>
		    		</div>
		    	</#if>
    		</#if>
    	</#list>
    <!--END CONTENT-BODY-->
    <!--BEGIN FOOTER-->
    <div class="form-actions fluid">
        <div class="col-md-offset-4 col-md-8">                        
            <button type="button" class="btn btn-success" id="saveCarouselBtn">保存</button> 
            <button type="button" class="btn btn-default">取消</button>
        </div>
    </div>
   </div>
</div>
