<!-- BEGIN JAVASCRIPTS -->
<#include "../../common/_js/table_js.ftl" />
<script type="text/javascript">
require(["modules/table/init", 'modules/dialog/init', 'admin/orders/init', 'bootstrap_daterangepicker'],
	function(Table, Dialog, Orders, Datarangepicker){
    $(document).ready(function(){
        Table.init();
        Dialog.init();
        Orders.init();
        App.initPlugins();
    });
});
</script>
<!-- END JAVASCRIPTS -->
<!-- BEGIN CSS -->
<link href="${base}/assets/admin/css/style-conquer.css" rel="stylesheet" type="text/css"/>
<link href="${base}/assets/plugins/data-tables/DT_bootstrap.css" rel="stylesheet" type="text/css"/>
<link href="${base}/assets/plugins/bootstrap-daterangepicker/daterangepicker-bs3.css" rel="stylesheet" type="text/css"/>
<link href="${base}/assets/admin/css/pages/orders/image.css" rel="stylesheet" type="text/css" />
<!-- END CSS -->
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">   

<!--test-->
<div class="row">
    <div class="col-md-12">
        <div class="content-header">
            <form class="query-form ajax-form form-inline form-body">
                <div class="form-left">
                    <#include "../../common/_template/pagination_control.ftl" /><!-- 引入每页显示多少条的模板文件 -->
                    <div class="form-group">
                        <label class="control-label">发货状态</label>
                        <select name="shin_shipments_status" operator="eq" data-type="number" class="form-control">
                            <option value="">全部</option>
                            <option value="0">待发货</option>
                            <option value="1">已发货</option>
                        </select>
                    </div>
                    <div class="form-group">
                       <a class="reportrange btn form-control" data-label="下单时间" data-for="goor_create_time" data-type="date"></a>
                        <#--
	                        	<i class="glyphicon glyphicon-calendar fa fa-calendar"></i>
	                        	<small>下单时间</small>
    							<span></span> <b class="caret"></b>
    							-->
                        </a>
                    <#--
                    <select style="display:none;" id="date-from" name="orin_order_time" operator="ge" data-type="date">
                    </select>
                    <select style="display:none;" id="date-to" name="orin_order_time" operator="le" data-type="date">
                    </select>
                    -->
                    </div>
                    <div class="form-group">
                        <a href="admin/orders/shipments/report" class="btn btn-default btn-sm" title="导出订单">
                            <i class="icon-cloud-download"></i>
                        </a>
                    </div>
                    <div class="form-group btn-group">
                        <a class="btn btn-default btn-sm dropdown-toggle" href="javascript:;" data-toggle="dropdown">
                            <span>商品名称</span>
                            <i class="icon-angle-down"></i>
                        </a>
                        <ul class="dropdown-menu">
                            <li><a class="goor-name" data-for="goor_name" data-label="商品名称" data-trogger="dropdown-item" href="javascript:;">商品名称</a></li>
                            <li><a class="goor-code" data-for="goor_code" data-label="商家编号" data-trogger="dropdown-item" href="javascript:;">商家编号</a></li>
                            <li><a class="wein-nickname" data-for="wein_nickname" data-label="粉丝昵称" data-trogger="dropdown-item" href="javascript:;">粉丝昵称</a></li>
                            <li><a class="orin-no" data-for="orin_no" data-label="订单编号" data-trogger="dropdown-item" href="javascript:;">订单编号</a></li>
                            <li><a class="coad-name" data-for="coad_name" data-label="收货人姓名" data-trogger="dropdown-item" href="javascript:;">收货人姓名</a></li>
                            <li><a class="coad-phone" data-for="coad_phone" data-label="收货人电话" data-trogger="dropdown-item" href="javascript:;">收货人电话</a></li>
                            <li><a class="pain-pay-no" data-for="pain_pay_no" data-label="支付单号" data-trogger="dropdown-item" href="javascript:;">支付单号</a></li>
                            <li><a class="pain-serial-number" data-for="pain_serial_number" data-label="商家订单号" data-trogger="dropdown-item" href="javascript:;">商家订单号</a></li>
                        </ul>
                        <input name="goor_name" operator="like" data-type="string" class="form-control input-small">
                        <input style="display: none;" name="goor_code" operator="like" data-type="string" class="form-control input-small">
                        <input style="display: none;" name="wein_nickname" operator="like" data-type="string" class="form-control input-small">
                        <input style="display: none;" name="orin_no" operator="like" data-type="string" class="form-control input-small">
                        <input style="display: none;" name="coad_name" operator="like" data-type="string" class="form-control input-small">
                        <input style="display: none;" name="coad_phone" operator="like" data-type="string" class="form-control input-small">
                        <input style="display: none;" name="pain_pay_no" operator="like" data-type="string" class="form-control input-small">
                        <input style="display: none;" name="pain_serial_number" operator="like" data-type="string" class="form-control input-small">
                    </div>
                    <div class="form-group last">
                        <button type="button" class="btn btn-info btn-sm reset">清空条件</button>
                        <button type="button" class="btn btn-info btn-sm query">查询</button>
                    </div>
                </div>

                <div class="form-right">
                    <div class="form-group last">
                        <div class="btn-group">
                            <a class="btn btn-success btn-sm dropdown-toggle" href="javascript:;" data-toggle="dropdown">
                                批量操作
                                <i class="icon-angle-down"></i>
                            </a>
                            <ul class="dropdown-menu pull-right">
                                <li><a href="javascript:;" data-action="common/waybill/printDistribution" class="js_multy_dispatch" title="打印配送单"><i class="icon-print"></i> 打印配送单</a></li>
                                <li><a href="javascript:;" data-action="common/waybill/printExpress" class="js_multy_dispatch" title="打印快递单"><i class="icon-print"></i> 打印快递单</a></li>
                                <li><a url="admin/orders/shipments/toMark" class="js_to_mark" href="javascript:;" title="标记"><i class="icon-flag"></i> 标记</a></li>
                            </ul>
                        </div>
                    </div>
                </div>
            </form>
        </div>

        <!-- BEGIN SAMPLE TABLE PORTLET-->
        <div class="content-body portlet">
            <div class="portlet-body">
                <!-- BEGIN SAMPLE TABLE -->
                <!--table-->
				<div class="table-responsive paging-table sort-table">
					<input type="hidden" name="path" value="admin/orders/shipments/list/" />
					<table class="table table-striped table-bordered table-hover">
					    <thead>
					        <tr>
					            <th width="20px">
					                <div class="checker">
					                    <span>
					                        <input type="checkbox" class="group-checkable" data-set=".table .checkboxes" />
					                    </span>
					                </div>
					            </th>
					            <th>商品信息</th>
					            <th>实收款</th>
					            <th>支付方式</th>
					            <th>收货地址及备注</th>
					            <th>操作</th>
					        </tr>
					    </thead>
					    <tbody>
					        <!-- 订单状态 -->
					        <#assign index=0>
					        <#list entitys as entity>
					        <#if index!=entity["orin_id"]>
							<tr>
                                <td>
                                	<div class="checker">
					                        <span>
					                            <input type="checkbox" class="checkboxes" value="${(entity["orin_id"])!}">
					                        </span>
					                    </div>
                                </td>
                                <td colspan="7">
                                    <span class="m-r">订单编号：${(entity["orin_no"])!} </span>
                                    <span class="m-r">下单时间：${(entity["orin_order_time"])!} </span>
                                    <span class="m-r">订单来源：${(entity["orin_source"])!} </span>
                                    <span></span>
                                    <a href="javascript:;" url="admin/orders/information/toMark/${entity["orin_id"]!}?type=1" title="${(entity["orin_mark_content"])!"标记"}" class="modal-ajax-static pull-right m-l-sm">
                                      <#if (entity["orin_mark"])??>
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
			                        	<#else>
			                        		<i class="icon-flag icon-lg text-muted m-r-xs"></i>
			                        	</#if>
                                    </a>
                                    <a url="admin/orders/information/detail/${(entity["orin_id"])!}" class="btn btn-default btn-xs pull-right ajaxify" title="订单详情">
                                        <i class="icon-search text-muted m-r-xs"></i>订单详情
                                    </a>
                                </td>
                            </tr>
                            <tr>
                                <!--商品信息-->

                                <td colspan="2">
                                    <ul class="list-group no-borders">
                                        <#list entitys as goodsOrder>
                                        <#if (goodsOrder["orin_id"])?? && goodsOrder["orin_id"] == entity["orin_id"]>
                                        <li class="list-group-item ">
                                            <div class="media">
                                                <span class="pull-left thumb-sm">
                                                    <img src="${(goodsOrder["goor_first_picture"])!}" alt="商品图片">
                                                </span>
                                                  
                                                <div class="pull-right">
                                                    x${(goodsOrder["goor_count"])!}
                                                </div>
                                                <div class="media-body">
                                                    <div><a url="admin/orders/information/detail/${(entity["orin_id"])!}" class="ajaxify">${(goodsOrder["goor_name"])!} </a></div>
                                                    <small class="text-muted">单价： <span class="money">${(goodsOrder["goor_price"])!}</span><span class="m-l-xs"></span><span>【商家编码：${(goodsOrder["goor_code"])!}】</span></small>
                                                </div>
                                            </div>
                                        </li>
                                        </#if>
										</#list>
                                        
                                    </ul>
                                </td>
                                <!--实收款-->
                                <td>
                                    <p class="money m-b-xs">${(entity["orin_total"])!}</p>
                                    <small class="text-muted">[含运费:${(entity["orin_freight"])!}]</small>
                                </td>
                                <!--支付方式-->
                                <td>
                                <#if (entity["orin_pay_way"])??>
									<#if entity["orin_pay_way"] == 0>
										微信支付
									<#elseif entity["orin_pay_way"] == 1>
										支付宝
									<#elseif entity["orin_pay_way"] == 2>
										财付通
									<#elseif entity["orin_pay_way"] == 3>
										银行卡支付
									<#elseif entity["orin_pay_way"] == 4>
										货到付款
									<#elseif entity["orin_pay_way"] == 5>
										余额支付
									</#if>
								</#if>
								</td>
                                <!--粉丝-->
                                
                                <!--收货地址及备注-->
                                <td>
                                    <p>${(entity["coad_name"])!} ${(entity["coad_phone"])!}</p>
                                    <p>${(entity["prov_regi_name"])!} ${(entity["city_regi_name"])!} ${(entity["area_regi_name"])!} ${(entity["coad_detailed_address"])!} </p>
                                    <p></p>
                                </td>
                                <!--操作-->
                                <td>
                                	<#if (entity["shin_shipments_status"])?? && entity["shin_shipments_status"] == 1>
                                		<p>${(entity["exco_name"])!}
	                                		<a href="javascript:;" url="admin/orders/shipments/toModify/${entity["shin_id"]!}" class="modal-ajax-static m-xs" title="编辑">
		                                        <i class="icon-edit text-muted"></i>
		                                    </a>
	                                    </p>
	                                    <p>${(entity["shin_express_no"])!}</p>
                                	<#else>
                                    <a href="javascript:;" url="admin/orders/shipments/toShipments/${entity["shin_id"]!}"  class="modal-ajax-static m-xs" title="发货">
                                        <i class="icon-truck text-muted"></i>
                                    </a>
                                    </#if>
                                </td>
                            </tr>
                            </#if>
                            <#assign index=entity["orin_id"]>
                            </#list>
                            <!-- END -->
					    </tbody>
					</table>
				</div>
				<!--table-->
				<!-- END SAMPLE TABLE PORTLET-->
			</div>
		</div>
	</div>
</div>
<!--test-->

