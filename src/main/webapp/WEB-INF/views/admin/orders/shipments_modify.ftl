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
                    <h4 class="modal-title">发货信息</h4>
                </div>
                <div class="modal-body">
                	<input type="hidden" name="shinId" value="${shinId!}" />
                	<#-- BEGIN -->
                		<div class="form-group">
		                    <label class="col-sm-3 control-label must">快递公司</label>
		                    <div class="col-sm-7">
		                        <select name="excoId" class="form-control" data-rule-required="true" aria-required="true" aria-invalid="true">
		                            <option value="">请选择快递</option>
		                            <#list expressCompanies as expressCompany>
                                    <option value="${(expressCompany.excoId)!}" <#if (expressCompany.excoId)?? && (entity["exco_id"])?? && expressCompany.excoId==entity["exco_id"]>selected</#if> >${(expressCompany.excoName)!}</option>
                                    </#list>
		                        </select>
		                    </div>
		                </div>
		                <div class="form-group">
		                    <label class="col-sm-3 control-label must">快递单号</label>
		                    <div class="col-sm-7">
		                        <input type="text" name="shinExpressNo" value="${(entity["shin_express_no"])!}" class="form-control" data-rule-required="true" aria-required="true">
		                    </div>
		                </div>
                	<#-- END -->
                </div>
                <div class="modal-footer">
                    <button type="button" data-dismiss="modal" class="btn btn-default">取消</button>
                    <button url="admin/orders/shipments/modify"
                    	type="button" class="btn btn-success ajax-submit-modal">确定</button>

                </div>
            </div>
    	</form>
    </div>
</div>
