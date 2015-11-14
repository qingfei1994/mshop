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
                    <h4 class="modal-title">编辑地址库</h4>
                </div>
                <div class="modal-body">
                	<input type="hidden" name="addrId" value="${(map["addr_id"])!}" />
                    <div class="col-md-12">
                        <div class="form-group">
                            <label class="col-md-3 control-label">联系人<span class="required">*</span></label>
                            <div class="col-md-6">
                                <input type="text" class="form-control" name="addrContacts" required="true" value="${(map["addr_contacts"])!}">
                            </div>
                        </div>
                     </div>
                     <div class="col-md-12">
                        <div class="form-group">
                            <label class="col-md-3 control-label">地区<span class="required">*</span></label>
                            <div class="col-md-9">
                                <#include "../../common/_template/regionalism_select.ftl"/>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-12">
                        <div class="form-group">
                            <label class="col-md-3 control-label">详细地址<span class="required">*</span></label>
                            <div class="col-md-6">
                                <input type="text" class="form-control" name="addrDetailedAddress" required="true" value="${(map["addr_detailed_address"])!}">
                            </div>
                        </div>
                    </div>
                    <div class="col-md-12">
                        <div class="form-group">
                            <label class="col-md-3 control-label">电话<span class="required"></span></label>
                            <div class="col-md-6">
                                <input type="text" class="form-control" name="addrPhone" value="${(map["addr_phone"])!}">
                            </div>
                        </div>
                    </div>
                    <div class="col-md-12">
                        <div class="form-group">
                            <label class="col-md-3 control-label">手机号码<span class="required">*</span></label>
                            <div class="col-md-6">
                                <input type="text" class="form-control" name="addrMobilePhone" required="true" phone="true" value="${(map["addr_mobile_phone"])!}">
                            </div>
                        </div>
                    </div>
                    <div class="col-md-12">
                        <div class="form-group">
                            <label class="col-md-3 control-label">公司名称<span class="required">*</span></label>
                            <div class="col-md-9">
                                <input type="text" class="form-control" name="addrCompanyName" required="true" value="${(map["addr_company_name"])!}">
                            </div>
                        </div>
                     </div>
                     <div class="col-md-12">
                     	<div class="form-group">
                     		<label class="col-md-3 control-label"></label>
                     		<div class="col-md-9">
                                 <div class="checkbox-list">
                                    <label class="checkbox-inline">
                                    	<span><input type="checkbox" name="addrDefaultSendAddress" value="1" <#if (map["addr_default_send_address"])?? && map["addr_default_send_address"] == 1>checked</#if>></span> 设置为默认发货地址
                                    </label>
                                    <label class="checkbox-inline">
                                    	<span><input type="checkbox" name="addrDefaultReturnAddress" value="1" <#if (map["addr_default_return_address"])?? && map["addr_default_return_address"] == 1>checked</#if>></span> 设置为默认退货地址
                                    </label> 
                                 </div>
                              </div>
                     	</div>
                     </div>
                </div>
                <div class="modal-footer">
                    <button type="button" data-dismiss="modal" class="btn btn-default">取消</button>
                    <button url="admin/logistics/addresses/addAddresses"
                    	type="button" class="btn btn-success ajax-submit-modal">确定</button>

                </div>
            </div>
    	</form>
    </div>
</div>
