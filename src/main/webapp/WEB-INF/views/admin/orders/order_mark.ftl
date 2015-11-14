<!-- BEGIN JAVASCRIPTS -->
<script type="text/javascript">
    require(['modules/form/init'], function(Form){
        $(document).ready(function(){
            Form.init();
        });
    });
</script>
<!-- END JAVASCRIPTS -->

<div class="modal-dialog">
    <div class="modal-content">
	    <form class="form-horizontal ajax-form valid" role="form">
	        <div class="form-body">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
                    <h4 class="modal-title">回复评论</h4>
                </div>
                <div class="modal-body">
                	<input type="hidden" name="orinId" value="${orinId!}" />
                	<input type="hidden" name="type" value="${type!}" >
                	<#assign index=0>
                	<input type="hidden" name="orinIds" value="<#if orinIds??><#list orinIds as orinId><#if index!=0>,</#if>${orinId!}<#assign index=index+1></#list></#if>" />
                	<div class="col-md-12">
                		<div class="form-group">
		                    <label class="col-md-2 control-label">标记</label>
		                    <div class="col-md-10">
		                        <label class="radio-inline">
		                            <input type="radio" name="orinMark" value="1" <#if (orderInformation.orinMark)?? && (orderInformation.orinMark == 2 || orderInformation.orinMark == 3 || orderInformation.orinMark == 4 || orderInformation.orinMark == 5)><#else>checked="checked"</#if>><i class="icon-flag fa-lg text-danger"></i>
		                        </label>
		                        <label class="radio-inline">
		                            <input type="radio" name="orinMark" value="2"<#if (orderInformation.orinMark)?? && orderInformation.orinMark == 2>checked="checked"</#if>><i class="icon-flag fa-lg text-warning"></i>
		                        </label>
		                        <label class="radio-inline">
		                            <input type="radio" name="orinMark" value="3" <#if (orderInformation.orinMark)?? && orderInformation.orinMark == 3>checked="checked"</#if>><i class="icon-flag fa-lg text-primary"></i>
		                        </label>
		                        <label class="radio-inline">
		                            <input type="radio" name="orinMark" value="4" <#if (orderInformation.orinMark)?? && orderInformation.orinMark == 4>checked="checked"</#if>><i class="icon-flag fa-lg text-info"></i>
		                        </label>
		                        <label class="radio-inline">
		                            <input type="radio" name="orinMark" value="5" <#if (orderInformation.orinMark)?? && orderInformation.orinMark == 5>checked="checked"</#if>><i class="icon-flag fa-lg text-blue"></i>
		                        </label>
		                    </div>
		                </div>
                	</div>
                    <div class="col-md-12">
                        <div class="form-group">
                        	<label class="col-md-2 control-label">标记信息</label>
                            <div class="col-md-8">
                                <textarea class="form-control" rows="3" name="orinMarkContent" required="true" placeholder="请填写标记信息">${(orderInformation.orinMarkContent)!}</textarea>
                            </div>
                        </div>
                     </div>
                </div>
                <div class="modal-footer">
                    <button type="button" data-dismiss="modal" class="btn btn-default">取消</button>
                    <button <#if orinId??>url="admin/orders/information/mark"<#elseif orinIds??>url="admin/orders/shipments/mark"</#if>
                    	type="button" class="btn btn-success ajax-submit-modal">确定</button>

                </div>
            </div>
    	</form>
    </div>
</div>
