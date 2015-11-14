<!-- BEGIN JAVASCRIPTS -->
<script type="text/javascript">
    require(["modules/form/init","modules/upload/init"], function(Form,Upload) {
       $(document).ready(function(){
			Form.init();
			Upload.init();
		});
    });
</script>
<!-- END JAVASCRIPTS -->
<!--BEGIN CSS-->
<link href="${base}/assets/plugins/plupload/uploadify.min.css" rel="stylesheet" type="text/css"/>
<!--END CSS-->
<div class="modal-dialog">
    <div class="modal-content">
    	<form class="form-horizontal ajax-form valid" id="addGroupForm" role="form">
	        <div class="form-body">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
                    <h4 class="modal-title"><#if flag=="edit">编辑分组<#elseif flag="add">添加分组</#if></h4>
                </div>
                <div class="modal-body">
                    <input id="parentId" type="hidden" name="goclParentId" value="${(parent.goclId)!}"/>
                    <input id="goclId" type="hidden" name="goclId" value="${(classification.goclId)!}">
                    <div class="col-md-12">
                        <div class="form-group">
							<label for="name"  class="col-md-3 control-label" >分组名称<span class="required">*</span></label>
			  				<div class="col-md-9">
			  					<input id="addGoclName" type="text" name="goclName" required="true" maxlength="10" class="form-control" placeholder="请输入分组名称" value="${(classification.goclName)!}">
			  					<span class="help-block"></span>
			  				</div>
				  		</div>
				  	</div>
				  	<#if flag='edit'>
						<div class="form-group">
							<label for="img"  class="col-md-3 control-label">分组图片</label>
							<div class="col-md-9">
								<img src="${classification.goclIconUrl!}" style="margin-left:6px;"width="78" height="78"></img>
							</div>
						</div>
					</#if>
				  	<div class="col-md-12">
	                    <div class="form-group">
							<label for="img"  class="col-md-3 control-label">
								<#if flag='edit'>
									修改
									<#else>
										分组图片
								</#if>
							</label>
							<div class="col-md-9">
								<div>
									<button id="addIconBtn" type="button" class="plupload-image btn btn-default" data-width="78" data-height="78" data-name="image">选择图片</button>
									<span>图片像素：240*240像素</span>
							 	</div>
							 	<#include "../../common/_template/show_image.ftl" />
							 </div>
					    </div>
					 </div>
			  	</div>
                <div class="modal-footer">
                    <button type="button" data-dismiss="modal" class="btn btn-default">取消</button>
                    <button id="addClassification" url="<#if flag=='edit'>admin/goods/classification/edit<#elseif flag='add'>admin/goods/classification/add</#if>" type="button" class="btn btn-success ajax-submit-modal">确定</button>
                </div>
            </div>
    	</form>
    </div>
</div>
