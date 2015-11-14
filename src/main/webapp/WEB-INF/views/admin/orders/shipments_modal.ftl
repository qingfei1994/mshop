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
                	<input type="hidden" name="orinId" value="${(entity["orin_id"])!}" />
                	<#-- BEGIN -->
                		<div class="alert alert-warning m-b">
		                    <p>订单编号：<span class="m-l-xs">${(entity["orin_no"])!}</span></p>
		                    <p>下单时间：<span class="m-l-xs">${(entity["orin_order_time"])!}</span></p>
		                    <p>收货信息：<span class="m-l-xs">${(entity["coad_name"])!},${(entity["coad_phone"])!}</span></p>
		                </div>
		                <div class="bs-example bs-example-tabs">
		                    <ul id="myTab" class="nav nav-tabs">
		                        <li class="active"><a href="#logistics" data-toggle="tab">物流信息</a></li>
		                        <li><a href="#nlogistics" data-toggle="tab">无需物流</a></li>
		                    </ul>
		                    <div id="myTabContent" class="tab-content">
		                        <div class="tab-pane m-t fade in active" id="logistics">
		                            <div class="form-group">
		                                <label class="col-sm-2 control-label">发货地址</label>
		                                <div class="col-sm-10 must">
		                                    <select class="form-control " data-toggle="chosen" name="addrId" data-placeholder="选择规格" data-rule-required="true" aria-required="true">
		                                        <#list addressess as addresses>
		                                        <option value="${(addresses["addr_id"])!}">${(addresses["addr_company_name"])!},${(addresses["addr_phone"])!},${(addresses["province_regi_name"])!} ${(addresses["city_regi_name"])!} ${(addresses["county_regi_name"])!}  ${(addresses["addr_detailed_address"])!}</option>
		                                        </#list>
		                                    </select>
		                                </div>
		                            </div>
		
		                            <div class="form-group">
		                                <label class="col-sm-2 control-label">快递公司</label>
		                                <div class="col-sm-7 must">
		                                    <select class="form-control" name="excoId" >
		                                        <option value="">请选择快递</option>
		                                        <#list expressCompanies as expressCompany>
		                                        <option value="${(expressCompany.excoId)!}">${(expressCompany.excoName)!}</option>
		                                        </#list>
		                                    </select>
		                                </div>
		                            </div>
		                            <div class="form-group">
		                                <label class="col-sm-2 control-label">快递单号</label>
		                                <div class="col-sm-7 must">
		                                    <input type="text" class="form-control"  name="shinExpressNo">
		                                </div>
		                            </div>
		
		                        </div>
		                        <div class="tab-pane m-t fade" id="nlogistics">
		                            <p>如果该物品无需物流运送(如虚拟产品)</p>
		                        </div>
		
		                    </div>
		                </div>
                	<#-- END -->
                </div>
                <div class="modal-footer">
                    <button type="button" data-dismiss="modal" class="btn btn-default">取消</button>
                    <button url="admin/orders/shipments/shipments"
                    	type="button" class="btn btn-success ajax-submit-modal">确定</button>

                </div>
            </div>
    	</form>
    </div>
</div>
