<!-- BEGIN JAVASCRIPTS -->
<script type="text/javascript">
    require(['modules/form/init'], function(Form){
        $(document).ready(function(){
            Form.init();
        });
    });
</script>
<!-- END JAVASCRIPTS -->
<style>
	.modal-dialog{
		width: 800px !important;
	}
</style>
<div class="modal-dialog">
    <div class="modal-content">
	    <form class="form-horizontal ajax-form valid" role="form">
	        <div class="form-body">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
                    <h4 class="modal-title">修改价格</h4>
                </div>
                <div class="modal-body">
                	<input type="hidden" name="orinId" value="${orinId!}" />
                    <div class="table-responsive ">
	                    <table class="table table-bordered ">
	                        <thead>
	                            <tr>
	                                <th>商品信息</th>
	                                <th style="width: 45px">数量</th>
	                                <th class="col-sm-2">优惠信息</th>
	                                <th style="width: 70px">原价</th>
	                                <th style="width: 60px">优惠</th>
	                                <th style="width: 80px">实收款</th>
	                                <th style="width: 100px">运费</th>
	                                <th style="width: 80px">总计</th>
	                            </tr>
	                        </thead>
	                        <tbody>
	                        	<#assign index=0>
	                        	<#list goodsOrders as goodsOrder>
	                            <tr>
	                                <!--商品信息-->
	                                <td>
	                                    <div class="media">
	                                        <span class="pull-left thumb-sm">
	                                            <img src="${(goodsOrder.goorFirstPicture)!}" alt="John said"></span>
	                                        <div class="media-body">
	                                            <div>${(goodsOrder.goorName)!}</div>
	                                            <small class="text-muted">单价： <span class="money">${(goodsOrder.goorPrice)!}</span><span class="m-l-xs"></span> </small>
	                                        </div>
	                                    </div>
	                                </td>
	
	                                <!--数量-->
	                                <td class="v-middle">${(goodsOrder.goorCount)!}</td>
	                                <!--优惠信息-->
	                                <#if index==0>
	                                <td class="v-middle" rowspan="${(goodsOrders?size)!}">
	                                </td>
	                                </#if>
	                                
	                                <!--原价-->
	                                <td class="v-middle">${(goodsOrder.goorPrice)*(goodsOrder.goorCount)}</td>
	                                <!--优惠-->
	                                <td class="v-middle">${(goodsOrder.goorFavourablePrice)!0.00}</td>
	                                <!--改价-->
	                                <td class="v-middle">
	                                    <input type="hidden" name="lstPrice[${index}].goorId" value="${(goodsOrder.goorId)!}">
									
	                                    <!--实收款展示,这个有可能为负数-->
	                                    
	                                    <input type="text" name="lstPrice[${index}].goorRealityPay" style="padding-left: 5px; padding-right: 0;" data-rule-decimal="2" value="${(goodsOrder.goorRealityPay)!}" class="form-control  js_price_input">
	                                </td>
	
	                                <!--运费-->
	                                <#if index==0>
	                                <td class="v-middle" rowspan="${(goodsOrders?size)!}">
	
	                                    <input type="text" style="padding-left: 5px; padding-right: 0;" name="orinFreight" data-rule-decimal="2" value="${(orderInformation.orinFreight)!}" class="form-control  js_price_input">
	                                    <small class="text-muted">[原运费:${(orderInformation.orinOldFreight)!}]</small>
	                                </td>
	                                
	                                <!--总计-->
	                                <td class="v-middle js_total_show" rowspan="${(goodsOrders?size)!}">${(orderInformation.orinTotal)!}
	                                </td>
	                                </#if>
	                            </tr>
	                            <#assign index=index+1>
	     						</#list>
	                        </tbody>
	                    </table>
	                </div>
                	<span class="help-block">*订单中使用的优惠已经均摊至每个商品，并从相应商品的实收款里扣除</span>
                </div>
                <div class="modal-footer">
                    <button type="button" data-dismiss="modal" class="btn btn-default">取消</button>
                    <button url="admin/orders/information/price"
                    	type="button" class="btn btn-success ajax-submit-modal">确定</button>

                </div>
            </div>
    	</form>
    </div>
</div>
