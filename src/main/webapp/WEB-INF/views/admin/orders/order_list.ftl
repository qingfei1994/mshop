<!-- BEGIN JAVASCRIPTS -->
<#include "../../common/_js/table_js.ftl" />
<script type="text/javascript">
require(["modules/table/init", 'modules/dialog/init', 'admin/orders/init', 'bootstrap_daterangepicker'],
	function(Table, Dialog, Orders, Datarangpicker){
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
<link href="${base}/assets/plugins/select2/select2_conquer.css" rel="stylesheet" type="text/css" />
<link href="${base}/assets/plugins/data-tables/DT_bootstrap.css" rel="stylesheet" type="text/css"/>
<link href="${base}/assets/plugins/bootstrap-daterangepicker/daterangepicker-bs3.css" rel="stylesheet" type="text/css"/>
<link href="${base}/assets/admin/css/pages/orders/image.css" rel="stylesheet" type="text/css" />
<!-- END CSS -->
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">   

<!--test-->
<div class="row">
    <div class="col-md-12">
        <!-- BEGIN SAMPLE TABLE PORTLET-->
        <div class="content-header" style="height:90px;">
            <form class="query-form ajax-form form-inline form-body">
                <div class="form-left">
                    <div>
                        <#include "../../common/_template/pagination_control.ftl" /><!-- 引入每页显示多少条的模板文件 -->
                        <div class="form-group">
                            <a class="reportrange btn form-control" data-label="下单时间"></a>
                        </div>
                        <div class="form-group">
                            <div class="btn-group">
                                <a class="btn btn-default btn-sm dropdown-toggle" href="javascript:;" data-toggle="dropdown">
                                    导出
                                    <i class="icon-angle-down"></i>
                                </a>
                                <ul class="dropdown-menu pull-right">
                                    <li>
                                        <a href="javascript:;" data-action="common/report/order"  data-toggle="download" data-set="download" title="订单">
                                            <i class="icon-download"></i> 导出订单
                                        </a>
                                    </li>
                                    <li>
                                        <a href="javascript:;" data-action="common/report/statement" data-toggle="download" data-set="download" title="对账单">
                                            <i class="icon-download-alt"></i> 导出对账单
                                        </a>
                                    </li>
                                </ul>
                            </div>
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
                            </ul>
                            <input name="goor_name" operator="like" data-type="string" class="form-control input-medium">
                            <input style="display: none;" name="goor_code" operator="like" data-type="string" class="form-control input-medium">
                            <input style="display: none;" name="wein_nickname" operator="like" data-type="string" class="form-control input-medium">
                            <input style="display: none;" name="orin_no" operator="like" data-type="string" class="form-control input-medium">
                        </div>
                        <div class="form-group last">
                            <button type="button" class="btn btn-info btn-sm reset">清空条件</button>
                            <button type="button"  class="btn btn-info btn-sm query">查询</button>
                        </div>
                    </div>

                    <div style="margin-top:8px;">
                        <div class="form-group">
                            <label class="control-label">订单来源</label>
                            <select name="orin_source_type" operator="eq" data-type="number" class="form-control">
                                <option value="">全部订单</option>
                                <option value="0">旺铺</option>
                                <option value="1">分销商</option>
                                <option value="2">微客</option>
                            </select>
                        </div>
                        <div class="form-group">
                            <label class="control-label">订单状态</label>
                            <select name="orin_status" operator="eq" data-type="number" class="form-control">
                                <option value="">全部订单</option>
                                <option value="1">交易中</option>
                                <option value="0">交易关闭</option>
                                <option value="2">交易完成</option>
                            </select>
                        </div>
                        <div class="form-group">
                            <label class="control-label">支付方式</label>
                            <select name="orin_pay_way" operator="eq" data-type="number" class="form-control">
                                <option value="">全部订单</option>
                                <option value="0">微信支付</option>
                                <option value="1">支付宝</option>
                                <option value="2">财付通</option>
                                <option value="3">银行卡支付</option>
                                <option value="4">货到付款</option>
                                <option value="5">会员余额</option>
                            </select>
                        </div>
                        <div class="form-group">
                            <label class="control-label">支付状态</label>
                            <select name="orin_pay_status" operator="eq" data-type="number" class="form-control">
                                <option value="">全部订单</option>
                                <option value="0">待支付</option>
                                <option value="1">已支付</option>
                            </select>
                        </div>
                        <div class="form-group last">
                            <label class="control-label">物流状态</label>
                            <select name="shin_shipments_status" operator="eq" data-type="number" class="form-control">
                                <option value="">全部订单</option>
                                <option value="0">待发货</option>
                                <option value="1">已发货</option>
                                <option value="2">已收货</option>
                            </select>
                        </div>
                    </div>
                <#-- END -->
                </div>

                <div class="form-right">
                    <div class="form-group last">
                        <div class="btn-group">
                            <a class="btn btn-success btn-sm dropdown-toggle" href="javascript:;" data-toggle="dropdown">
                                批量操作
                                <i class="icon-angle-down"></i>
                            </a>
                            <ul class="dropdown-menu pull-right">
                                <li><a url="admin/orders/information/batchDelete" confirm="确定要批量删除吗？" class="bootbox-batch-confirm" href="javascript:;"><i class="icon-trash"></i> 删除</a></li>
                            </ul>
                        </div>
                    </div>
                </div>
            </form>
        </div>
        
        <div class="content-body portlet" style="padding-top:100px;">
        	<!--
            <div class="portlet-title">
                <div class="caption ajaxify">
                    <i class="icon-list-alt"></i>订单管理
                </div>
                <div class="actions">
                    <div class="btn-group">
                        <a class="btn btn-success dropdown-toggle" href="javascript:;" data-toggle="dropdown">
							批量操作
                            <i class="icon-angle-down"></i>
                        </a>
                        <ul class="dropdown-menu pull-right">
                            <li><a url="admin/logistics/addresses/batchDelete" confirm="确定要批量删除吗？" class="bootbox-batch-confirm" href="javascript:;"><i class="icon-trash"></i> 删除</a></li>
                        </ul>
                    </div>
                </div>
            </div>·
            -->
            <div class="portlet-body">
                <!-- BEGIN SAMPLE TABLE -->
                <!--table-->
				<div class="table-responsive paging-table sort-table">
					<input type="hidden" name="path" value="admin/orders/information/list/" />
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
					            <th>状态</th>
					        </tr>
					    </thead>
					    <tbody>
					        <#--Start-->
					        <#assign index=0>
					        <#list pagination as entity>
					        <#if index != entity["orin_id"]>
							<tr>
                                <td>
                                    <input type="checkbox" value="${(entity["orin_id"])!}">
                                </td>
                                <td colspan="7"><span class="m-r">订单编号：${(entity["orin_no"])!} </span>
                                    <span class="m-r">下单时间：${(entity["orin_order_time"])!} </span><span class="m-r">订单来源：${(entity["orin_source"])!} </span>
                                    <span>客户：${(entity["wein_nickname"])!} </span>
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
                                    <a url="admin/orders/information/detail/${(entity["orin_id"])!}" class="btn btn-default btn-xs pull-right ajaxify" title="查看详情">
                                        <i class="icon-search text-muted m-r-xs"></i>订单详情</a>
                                </td>
                            </tr>
                            <tr>
                                <td></td>
                                <td>
                                    <ul class="list-group no-borders">
                                    	<#list pagination as goodsOrder>
                                    	<#if (goodsOrder["orin_id"])?? && goodsOrder["orin_id"] == entity["orin_id"]>
                                        <li class="list-group-item ">
                                            <div class="media">
                                                <span class="pull-left thumb-sm">
                                                    <img src="${(goodsOrder["goor_first_picture"])!}" alt="John said">
                                                </span>
                                                                                                
                                                <div class="pull-right">
                                                    x${(goodsOrder["goor_count"])!"0"}
                                                </div>
                                                <div class="media-body">
                                                    <div><a url="admin/orders/information/detail/${(entity["orin_id"])!}" class="ajaxify">${(goodsOrder["goor_name"])!} </a></div>
                                                    <small class="text-muted">单价： <span class="money">${(goodsOrder["goor_price"])!"0"} </span><span class="m-l-xs">[自然白色] </span><span>【商家编码：${(goodsOrder["goor_code"])!}】</span></small>
                                                </div>
                                            </div>
                                        </li>
                                        </#if>
                                        </#list>
                                    </ul>
                                </td>
                                <td>
                                    <p class="money m-b-xs">
                                        ${(entity["orin_total"])!}
                                    </p>
                                    <small class="text-muted">[含运费:${(entity["orin_freight"])!}]</small>
                                </td>
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
                                <td>
                                    <p></p><p><#if (entity["orin_status"])??><#if entity["orin_status"]==0>交易关闭<#elseif entity["orin_status"]==1>交易中<#elseif entity["orin_status"]==2>交易完成</#if></#if></p><p></p>
                                    
                                </td>
                            </tr>
                            </#if>
                            <#assign index=entity["orin_id"]>
                            </#list>
                            <#--END-->
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

