<script>
	require(["modules/form/init", "admin/logistics/init"],
	function(Form, Logistics){
	    $(document).ready(function(){
	        Form.init();
	        Logistics.init();
	    });
	});
</script>
<link href="${base}/assets/plugins/plupload/uploadify.min.css" rel="stylesheet" type="text/css"/>
 <style type="text/css">
    .courier-container {
        width: 766px;
        position: relative;
        overflow: hidden;
        border: 1px solid #dde9f5;
    }

    .courier-container .close {
        position: absolute;
        right: 1px;
    }

    .courier-container .item {
        line-height: 20px;
        float: left;
        position: absolute;
        top: 0px;
        left: 0px;
        color: #666666;
        overflow: hidden;
        word-wrap: break-word;
        filter: alpha(opacity=80);
        -moz-opacity: 0.8;
        opacity: 0.8;
        border: 1px dotted #999999;
        background: #f6fafd;
    }

    .courier-container .selected {
        filter: alpha(opacity=100);
        -moz-opacity: 1;
        opacity: 1;
        border: 1px dotted #1450A0;
    }

    .courier-container pre {
        height: 100%;
        float: left;
        cursor: move;
        width: 100%;
        border: none;
        background: none;
    }

    .courier-container textarea {
        padding-left: 0px;
        margin: 0px;
        font-size: 12px;
        resize: none;
        outline: none;
        overflow: hidden;
        border: none;
    }

    .courier-container .resize {
        height: 6px;
        width: 6px;
        position: absolute;
        bottom: 0px;
        right: 0px;
        overflow: hidden;
        cursor: nw-resize;
        background-color: #aaaaaa;
    }
</style>
<script type="text/javascript">
    var _speedMark = new Date(),
    www_version = 1201405081146;
</script>

<div class="portlet">
  <div class="portlet-title">
	 <div class="caption"><i class="icon-reorder"></i>新增运单模版</div>
	 <div class="tools">
	 </div>
  </div>
  <div class="portlet-body form">
	 <!-- BEGIN FORM-->
	 <form class="form-horizontal form-bordered ajax-form valid">
		<div class="form-body">
		   <input type="hidden" name="wateId" value="${(waybillTemplate.wateId)!}"/>
		   <div class="form-group">
			  <label class="control-label col-md-2" for="inputWarning">模板名称<span class="required">*</span></label>
			  <div class="col-md-4">
				 <input class="form-control" name="wateName" type="text" value="${(waybillTemplate.wateName)!}" >
			  </div>
		   </div>
		   <div class="form-group">
			  <label class="control-label col-md-2" for="inputError">快递公司<span class="required">*</span></label>
			  <div class="col-md-4">
				<select class="form-control" name="excoId">
                 	<option value="">请选择</option>
                 	<#list expressCompanys as expressCompany>
                		<option value="${(expressCompany.excoId)!}" <#if waybillTemplate?? && waybillTemplate.excoId == expressCompany.excoId>selected</#if>>${(expressCompany.excoName)!}</option>
                	</#list>
              	</select>
			  </div>
		   </div>
		   <div class="form-group">
			  <label class="control-label col-md-2" for="inputSuccess">模板尺寸</label>
				  <div class="col-sm-2">
	                <div class="input-group m-b">
	                    <span class="input-group-addon">长</span>
	                    <input name="wateLength" class="form-control" aria-required="true" type="text" maxlength="4" value="${(waybillTemplate.wateLength)!0}" data-rule-required="true">
	                    <span class="input-group-addon">mm</span>
	                </div>
				  </div>
				  
		   </div>
		   
		   <div class="form-group">
			  <label class="control-label col-md-2" for="inputSuccess"></label>
			  <div class="col-sm-2">
	                <div class="input-group m-b">
	                    <span class="input-group-addon">高</span>
	                    <input name="wateHeight" class="form-control" id="height" aria-required="true" type="text" maxlength="4" value="${(waybillTemplate.wateHeight)!0}" data-rule-required="true">
	                    <span class="input-group-addon">mm</span>
	                </div>
			  	  </div>
		   </div>
		   
		   <div class="form-group">
			  <label class="control-label col-md-2">运单图片</label>
			  <div class="col-md-4">
				 <div id="watePictureContainer">
	    			<a href="javascript:;" id="watePictureButton" class="btn btn-default" data-name="watePictureUrl">上传图片</a>
				</div>
				<div class="image-list"></div>
				<input type="hidden" name="watePictureUrl" value="${(waybillTemplate.watePictureUrl)!}" />
			  </div>
		   </div>
		   <div class="form-group">
			  <label class="control-label col-md-2">选择打印项</label>
			  <div class="col-md-10">
			  	<#list printItems as printItem>
					<label class="checkbox-inline" style="margin-left:0px;">
	                    <input type="checkbox" data-value="${(printItem.pritName)!}" id="print_item${(printItem.pritId)!}" 
	                    name="printItem" value="${(printItem.pritId)!}"
	                    <#if waybillPrintItems??> 
		                    <#list waybillPrintItems as waybillPrintItem>
		                    	<#if printItem.pritId == waybillPrintItem.pritId>
		                    		checked
		                    	</#if>
		                    </#list>
	                    </#if>
	                    class="js_print_item" data-text="${(printItem.pritName)!}">${(printItem.pritName)!}
	                </label>
                </#list>
			  </div>
		   </div>
		   <div class="form-group">
			  <label class="control-label col-md-2">内容
			  	<input type="hidden" id="content" name="wateContent">
			  </label>
			  <div class="col-md-10">
				<div class="courier-container js_container">
					<#if (waybillTemplate.wateContent)?? && waybillTemplate.wateContent!="">
						${(waybillTemplate.wateContent)!}
					<#else>
                    	<img width="766" height="510" src="${(waybillTemplate.watePictureUrl)!}" class="jsimg">
                  	</#if>
                </div>
			  </div>
		   </div>
		   <div class="form-group">
			  <label class="control-label col-md-2">偏移量X<span class="required">*</span></label>
			  <div class="col-md-4">
				 <div class="input-icon right">  
					<input class="form-control" type="text" value="${(waybillTemplate.wateOffsetX)!0}" name="wateOffsetX">                                 
				 </div>
			  </div>
		   </div>
		   <div class="form-group">
			  <label class="control-label col-md-2">偏移量Y<span class="required">*</span></label>
			  <div class="col-md-4">
				 <div class="input-icon right">   
					<input class="form-control" type="text" value="${(waybillTemplate.wateOffsetY)!0}" name="wateOffsetY">                                 
				 </div>
			  </div>
		   </div>
		   <div class="form-group">
			  <label class="control-label col-md-2"></label>
			  <div class="col-md-4">
				 <div class="checkbox-list">
                    <label class="checkbox-inline">
                    	<span><input type="checkbox" name="wateDefault" value="1" <#if (waybillTemplate.wateDefault)?? && waybillTemplate.wateDefault == 1>checked</#if>></span> 设置为默认运单模板
                    </label>
                 </div>
			  </div>
		   </div>
		</div>
		<div class="form-actions fluid">
		   <div class="col-md-offset-2 col-md-9">
			  <button class="btn btn-success waybill-template-submit" url="admin/logistics/waybilltemplate/save" type="submit">保存</button>
			  <button class="btn btn-default ajaxify" url="admin/logistics/waybilltemplate/list" type="button">取消</button>
		   </div>
		</div>
	 </form>
	 <!-- END FORM-->
  </div>
</div>