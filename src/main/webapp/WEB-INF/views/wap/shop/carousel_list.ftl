<script>
	require(["modules/form/init","modules/upload/init","modules/dialog/init"],function(Form,Upload,Dialog){
		$(document).ready(function(){
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
    	<#list list as entity>
    		<#if entity_index % 2 == 0>
    			<div class="row">
    			<div class="col-md-6">
		    	<div class="portlet">
			    	  <div class="portlet-body">
			    	  			<input id="fileUpload" name="file" type="file" style="display: none;" />
			    	  			<a class="choose-image" href="javascript:;">
			    					<img src="${(entity.inbaImageUrl)!}"  style="max-width: 100%" />
			         			</a>
			          </div>
			    	  <div class="portlet-title">
				    	  <div class="caption">
				    	  	<a href="javascript:;" class=" btn-sm btn-default modal-ajax-static" url="wap/shop/carousel/goodsInformation/list">选择链接</a>
				    	  	<span class="help-inline">${(entity.inbaDetailUrl)!}</span>
				    	  </div>
				    	  <div class="tools">
				    	  	<a href="javascript:;" class="remove bootbox-confirm" confirm="确定删除？" url="wap/shop/carousel/delete?inbaId=${entity.inbaId}"></a>
				    	  </div>
			    	  </div>
		    	</div>
		    	</div>
		    	<#if (entity_index + 1) == (list?size)>
		 				</div>
		  		</#if>
    		<#elseif entity_index % 2 == 1>
    			<div class="col-md-6">
		    	<div class="portlet">
			    	  <div class="portlet-body">
			    	  			<input id="fileUpload" name="file" type="file" style="display: none;" />
			    	  			<a class="choose-image" href="javascript:;">
			    					<img src="${(entity.inbaImageUrl)!}"  style="max-width: 100%" />
			         			</a>
			          </div>
			    	  <div class="portlet-title">
				    	  <div class="caption">
				    	  	<a href="javascript:;" class=" btn-sm btn-default modal-ajax-static" url="wap/shop/carousel/goodsInformation/list">选择链接</a>
				    	  	<span class="help-inline">${(entity.inbaDetailUrl)!}</span>
				    	  </div>
				    	  <div class="tools">
				    	  	<a href="javascript:;" class="remove bootbox-confirm" confirm="确定删除？" url="wap/shop/carousel/delete?inbaId=${entity.inbaId}"></a>
				    	  </div>
			    	  </div>
		    	</div>
		    	</div>
		    	</div>
    		</#if>
    	</#list>
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
<script>
	$(document).ready(function(){
		$('#addPic').on('click',function(){
			if($('.row:last > .col-md-6').length == 2){
	    		var html = "<div class='row'>";
	    		html+="<div class='col-md-6'>";
				html+="<div class='portlet'>";
				html+="<div class='portlet-body'>";
				html+="<input id='fileUpload' name='file' type='file' style='display: none;' />";
				html+="<img src='http://dummyimage.com/640x320/ddd/555'  style='max-width: 100%' />";
				html+="</div>";
				html+="<div class='portlet-title'>";
				html+="<div class='caption'>";
				html+="<a href='javascript:;' class=' btn-sm btn-default modal-ajax-static' url='admin/goods/classification/addparent'>选择链接</a>";
				html+="<span class='help-inline'>广州米所思信息科技有限公司</span>";
				html+="</div>";
				html+="<div class='tools'>";
				html+="<a href='javascript:;' class='remove bootbox-confirm' confirm='确定删除？' url='wap/shop/carousel/delete'></a>";
				html+="</div>";
				html+="</div>";
				html+="</div>";
				html+="</div>";
				html+="</div>";
				$('.row:last').parent().append(html);
			}else if($('.row:last > .col-md-6').length == 1){
					var html = "<div class='col-md-6'>";
					html+="<div class='portlet'>";
					html+="<div class='portlet-body'>";
					html+="<input id='fileUpload' name='file' type='file' style='display: none;' />";
					html+="<img src='http://dummyimage.com/640x320/ddd/555'  style='max-width: 100%' />";
					html+="</div>";
					html+="<div class='portlet-title'>";
					html+="<div class='caption'>";
					html+="<a href='javascript:;' class=' btn-sm btn-default modal-ajax-static' url='admin/goods/classification/addparent'>选择链接</a>";
					html+="<span class='help-inline'>广州米所思信息科技有限公司</span>";
					html+="</div>";
					html+="<div class='tools'>";
					html+="<a href='javascript:;' class='remove bootbox-confirm' confirm='确定删除？' url='wap/shop/carousel/delete'></a>";
					html+="</div>";
					html+="</div>";
					html+="</div>";
					html+="</div>";
					$('.row:last').append(html);
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