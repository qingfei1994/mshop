<script>
	require(["modules/form/init", 'modules/dialog/init'], 
	function(Form, Dialog){
	    $(document).ready(function(){
	        Form.init();
	        Dialog.init();
	        
	    });
	});
</script>
<link href="${base}/assets/admin/css/pages/orders/image.css" rel="stylesheet" type="text/css" />

<div class="row">
    <div class="col-md-12">
        <!-- BEGIN SAMPLE TABLE PORTLET-->
		<div class="portlet">
		  <div class="portlet-title">
			 <div class="caption"><i class="icon-reorder"></i>订单详情</div>
			 <div class="tools">
			 </div>
		  </div>
		  <div class="portlet-body form">
			 <!-- BEGIN FORM-->
			 <form class="form-horizontal ajax-form valid">
				<div class="alert alert-warning">
                    <h5>订单状态 : <#if (entity["orin_status"])??>
                    				<#if entity["orin_status"] == 0>
                    					交易关闭
                    				<#elseif entity["orin_status"] == 1>
                    					<#if entity["orin_pay_status"] == 0>
                    						待支付
                    					<#elseif entity["orin_pay_status"] == 1>
                    						<#if entity["shin_shipments_status"] == 0>
                    							已支付，待发货
                    						<#elseif entity["shin_shipments_status"] == 1>
                    							已支付，已发货
                    						</#if>
                    					</#if>
                    				<#elseif entity["orin_status"] == 2>
                    					交易完成
                    				</#if>
                    			</#if>
                        <input type="hidden" name="method" id="method">
                        
                        <#if (entity["orin_pay_status"])?? && entity["orin_pay_status"]==0>
                        <a url="admin/orders/information/closeOrder/${(entity["orin_id"])!}" confirm="确定要关闭订单吗？" class="bootbox-batch-confirm btn btn-default btn-sm" href="javascript:;">关闭</a>
                        <a href="javascript:;" url="admin/orders/information/toChangePrice/${entity["orin_id"]!}" class="modal-ajax-static btn btn-default btn-sm" title="修改价格">改价
                        </a>
                        </#if>
                        
                        <#if (entity["orin_pay_status"])?? && entity["orin_pay_status"] == 1>
                        	<#if (entity["shin_shipments_status"])?? && entity["shin_shipments_status"] == 0>
                        		<a href="javascript:;"  url="admin/orders/shipments/toShipments/${entity["shin_id"]!}" title="发货" class="modal-ajax-static btn btn-default btn-sm">发货</a>
                        	</#if>
                        </#if>	
                        
						<a href="javascript:;"  url="admin/orders/information/toMark/${entity["orin_id"]!}?type=0" title="标记" class="modal-ajax-static btn btn-default btn-sm">标记</a>
                    </h5>
                    <#if (entity["orin_mark"])??>
                    <div class="m-1-xl m-b-xs">
                        <p>
                            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;标记 : 
                            <#if entity["orin_mark"] == 1>
                            	<i class="icon-flag fa-lg text-danger m-r-xs"></i>
                        	<#elseif entity["orin_mark"] == 2>
                        		<i class="icon-flag fa-lg text-warning m-r-xs"></i>
                        	<#elseif entity["orin_mark"] == 3>
                        		<i class="icon-flag fa-lg text-primary m-r-xs"></i>
                        	<#elseif entity["orin_mark"] == 4>
                        		<i class="icon-flag fa-lg text-info m-r-xs"></i>
                        	<#elseif entity["orin_mark"] == 5>
                        		<i class="icon-flag fa-lg text-blue m-r-xs"></i>
                        	</#if>
                            <span class="m-l-xs"></span>
                        </p>
                    </div>
                    </#if>
                </div>
                    <header class="panel-heading bg-light">
                        <ul class="nav nav-tabs">
                            <li class="active"><a href="#tabinfo" data-toggle="tab">订单信息</a>
                            </li>
                            <li class=""><a href="#tabdescribe" data-toggle="tab">物流信息</a>
                            </li>
                            <li class=""><a href="#tabspecifications" data-toggle="tab">客户信息</a>
                            </li>
                            <li class=""><a href="#payinfo" data-toggle="tab">支付信息</a>
                            </li>
                        </ul>
                    </header>
                    <div class="panel-body">
                        <div class="tab-content">
                            <div class="tab-pane active" id="tabinfo">
                                <div class="col-sm-12">
                                    <div class="col-sm-6">
                                        <div class="form-group">
                                            <label class="col-sm-2 control-label">订单编号</label>
                                            <div class="col-sm-10 form-control-static">
                                                ${(entity["orin_no"])!}
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-sm-2 control-label">下单时间</label>
                                            <div class="col-sm-10 form-control-static">
                                                 ${(entity["orin_order_time"])!}
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-sm-6">
                                        <div class="form-group">
                                            <label class="col-sm-2 control-label">订单来源</label>
                                            <div class="col-sm-10 form-control-static">
                                                ${(entity["orin_source"])!}
                                            </div>
                                        </div>
                                        
                                    </div>
                                </div>
                                <div class="col-sm-12">
                                    <div class="col-sm-6">
                                        
                                    </div>
                                    
                                </div>
                                <div class="col-sm-12 table-responsive">

                                    <table class="table table-bordered ">
                                        <thead>
                                            <tr>
                                                <th>商品信息</th>
                                                <th>商家编码</th>
                                                <th>单价</th>
                                                <th>数量</th>
                                                <th>优惠信息</th>
                                                <th>实收款</th>
                                                <th class="col-sm-2">总计</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <#assign index = 0>
                                            <#list goodsOrders as goodsOrder>
                                            <tr>
                                                <td>
                                                    <div class="media">
                                                        <span class="pull-left thumb-sm">
                                                            <img src="${(goodsOrder.goorFirstPicture)!}" alt="John said"></span>

                                                        <div class="media-body">
                                                            <div>${(goodsOrder.goorName)!}</div>
                                                            
                                                            <small class="text-muted"><span class="m-l-xs"></span></small>
                                                        </div>
                                                    </div>
                                                </td>
                                                <td><span>${(goodsOrder.goorCode)!}</span></td>
                                                <td><span class="money">${(goodsOrder.goorPrice)!}</span></td>
                                                <td>${(goodsOrder.goorCount)!}</td>
                                                
                                                <#if index==0>
                                                <td rowspan="${goodsOrders?size}" style="vertical-align: middle;"></td>
                                                </#if>
                                                <!--实收款-->
                                                <td>
                                                    <p class="money m-b-xs">
                                                        ${(goodsOrder.goorRealityPay)!}<br>
                                                	</p>
                                                </td>
                                                <#if index==0>
                                                <td rowspan="${goodsOrders?size}" style="vertical-align: middle;">
                                                    <p class="money m-b-xs">
                                                        ${(entity["orin_total"])!}
                                                        <small class="text-muted">[含运费:${(entity["orin_freight"])!}]</small>
                                                    </p>
                                                </td>
                                                </#if>
                                            </tr>
                                            <#assign index=index+1>
                                            </#list>
                                        </tbody>
                                    </table>

                                </div>
                            </div>
                            
                            <div class="tab-pane" id="payinfo">
                                <div class="col-sm-12">
                                    <div class="col-sm-6">
                                        <div class="form-group">
                                            <div class="col-sm-10 form-control-static">
                                                应付款:${(entity["orin_total"])!} <span class="m-l-lg">已收款:<#if (entity["orin_pay_status"])?? && entity["orin_pay_status"]==1>${(entity["orin_total"])!}<#else>0.00</#if></span>
                                            </div>
                                        </div>
                                    </div>


                                </div>
                                <div class="table-responsive">

                                    <table class="table table-bordered table-striped">
                                        <thead>
                                            <tr>
                                                <th>支付时间</th>
                                                <th>支付单号<a href="javascript:;" data-toggle="popover" data-placement="bottom" data-trigger="hover" data-content="商家使用微盟支付服务时，此处显示的是微盟后台>微盟支付>财务明细中的支付单号，未使用微盟支付服务时，此处的支付单号可以暂时忽略" data-original-title="" title=""><i class="fa fa-question fa-lg m-l-xs"></i></a></th>
                                                <th>商户订单号
                                                    <a href="javascript:;" data-toggle="ajaxModal" data-remote="/vshop/Order/OrderDemo">
                                                        <i class="fa fa-question fa-lg m-l-xs"></i></a>
                                                </th>
                                                <th>支付金额</th>
                                                <th>支付方式</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                        	<#if (entity["pain_pay_no"])??>
                                            <tr>
                                                <td>${(entity["pain_pay_time"])!}</td>
                                                <td>${(entity["pain_pay_no"])!}</td>
                                                <td>${(entity["pain_serial_number"])!}</td>
                                                <td>${(entity["pain_pay_money"])!}</td>
                                                <td>${(entity["pain_pay_way"])!}</td>
                                            </tr>
                                            </#if>
                                        </tbody>
                                    </table>

                                </div>
                            </div>
                            <div class="tab-pane" id="tabdescribe" style="overflow: hidden">
                                <div class="form-group">
                                    <label class="col-sm-1 control-label">收货地址</label>
                                    <div class="col-sm-11 form-control-static">
                                        ${(entity["coad_name"])!},${(entity["coad_phone"])!},${(entity["prov_regi_name"])!} ${(entity["city_regi_name"])!} ${(entity["coun_regi_name"])!} ${(entity["coad_detailed_address"])!}
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-1 control-label">配送方式</label>
                                    <div class="col-sm-11 form-control-static">
                                        ${(entity["orin_delivery_method"])!}
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-1 control-label">备注</label>
                                    <div class="col-sm-11 form-control-static">
                                        ${(entity["shin_remark"])!}
                                    </div>
                                </div>
                                
                                <div class="form-group">
                                    <label class="col-sm-5 control-label">暂无物流信息</label>
                                </div>
                                
                            </div>
                            
                            <div class="tab-pane" id="tabspecifications">
                                <div class="col-sm-12">
                                    <div class="col-sm-6">
                                        <div class="form-group">
                                            <label class="col-sm-2 control-label">粉丝昵称</label>
                                            <div class="col-sm-10 form-control-static">
                                                <span class="mx80">${(entity["wein_nickname"])!}</span>
                                                <!--<a href="javascript:;" class="btn btn-default btn-xs"><i class="fa fa-comments-o m-r-xs"></i>微客服</a>-->
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-sm-2 control-label">粉丝性别</label>
                                            <div class="col-sm-10 form-control-static">
                                                ${(entity["wein_sex"])!}
                                            </div>
                                        </div>

                                        <div class="form-group">
                                            <label class="col-sm-2 control-label">会员昵称</label>
                                            <div class="col-sm-10 form-control-static">
                                                ${(entity["mein_name"])!}
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-sm-2 control-label">手机号码</label>
                                            <div class="col-sm-10 form-control-static">
                                                ${(entity["mein_phone"])!}
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-sm-2 control-label">会员余额</label>
                                            <div class="col-sm-10 form-control-static">
                                                <span class="money">${(entity["mein_balance"])!}</span>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-sm-6">
                                        <div class="form-group">
                                            <label class="col-sm-2 control-label">wechatID</label>
                                            <div class="col-sm-10 form-control-static">
                                                ${(entity["wein_unionid"])!}
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-sm-2 control-label">粉丝地区</label>
                                            <div class="col-sm-10 form-control-static">
                                                 ${(entity["wein_country"])!}  ${(entity["wein_province"])!}  ${(entity["wein_city"])!}
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-sm-2 control-label">会员等级</label>
                                            <div class="col-sm-10 form-control-static">
                                                ${(entity["wein_grade"])!}
                                            </div>
                                        </div>
                                        
                                        <div class="form-group">
                                            <label class="col-sm-2 control-label">会员积分</label>
                                            <div class="col-sm-10 form-control-static">
                                                ${(entity["mein_integral"])!}
                                            </div>
                                        </div>
                                    </div>

                                </div>
                            </div>
                            
                        </div>
                    </div>
			 </form>
			 <!-- END FORM-->
		  </div>
		</div>
	</div>
</div>