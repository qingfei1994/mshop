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
        <!-- BEGIN SAMPLE TABLE PORTLET-->
        <div class="content-header">
            <form class="query-form ajax-form form-inline form-body">
                <div class="form-left">
					<#include "../../common/_template/pagination_control.ftl" /><!-- 引入每页显示多少条的模板文件 -->
                    <div class="form-group">
                        <label class="control-label">状态</label>
                        <select name="client_appeal.clap_flow_status" operator="eq" data-type="number" class="form-control">
                            <option value="">全部</option>
                            <option value="0">待审核</option>
                            <option value="1">拒绝退款</option>
                            <option value="2">同意退款</option>
                            <option value="3">撤销退款</option>
                        </select>
                    </div>
                    <div class="form-group">
                        <a class="reportrange btn form-control" data-label="评价时间"></a>
                    </div>
                    <div class="form-group btn-group">
                            <a class="btn btn-default btn-sm dropdown-toggle" href="javascript:;" data-toggle="dropdown">
                                <span>商品名称</span>
                                <i class="icon-angle-down"></i>
                            </a>
                            <ul class="dropdown-menu">
                                <li><a class="goor-name" data-for="goor_name" data-label="商品名称" data-trogger="dropdown-item" href="javascript:;">商品名称</a></li>
                                <li><a class="wein-nickname" data-for="wein_nickname" data-label="粉丝昵称" data-trogger="dropdown-item" href="javascript:;">粉丝昵称</a></li>
                                <li><a class="orin-no" data-for="orin_no" data-label="订单编号" data-trogger="dropdown-item" href="javascript:;">订单编号</a></li>
                            </ul>
                            <input name="goor_name" operator="like" data-type="string" class="form-control input-medium">
                            <input style="display: none;" name="wein_nickname" operator="like" data-type="string" class="form-control input-medium">
                            <input style="display: none;" name="orin_no" operator="like" data-type="string" class="form-control input-medium">
                        </div>
                        <div class="form-group last">
                            <button type="button" class="btn btn-info btn-sm reset">清空条件</button>
                            <button type="button"  class="btn btn-info btn-sm query">查询</button>
                        </div>
                </div>

                <!--<div class="form-right">
                    <div class="form-group last">
                        <div class="btn-group">
                            <a class="btn btn-success btn-sm dropdown-toggle" href="javascript:;" data-toggle="dropdown">
                                批量操作
                                <i class="icon-angle-down"></i>
                            </a>
                            <ul class="dropdown-menu pull-right">
                                <li><a url="admin/order/comment/batchDelete" confirm="确定要批量删除吗？" class="bootbox-batch-confirm" href="javascript:;"><i class="icon-trash"></i> 删除</a></li>
                            </ul>
                        </div>
                    </div>
                </div>-->
            </form>
        </div>
        
        <div class="content-body portlet">
            <div class="portlet-body">
                <!-- BEGIN SAMPLE TABLE -->
                <!--table-->
				<div class="table-responsive paging-table sort-table">
					<input type="hidden" name="path" value="admin/orders/appeal/list" />
					<table class="table table-striped table-bordered table-hover">
					    <thead>
					        <tr>
					            <!--<th width="20px">
					                <div class="checker">
					                    <span>
					                        <input type="checkbox" class="group-checkable" data-set=".table .checkboxes" />
					                    </span>
					                </div>
					            </th>-->
					            <th>商品信息</th>
					            <th>客户</th>
					            <th>退款原因</th>
					            <th>退款金额</th>
					            <th>支付方式</th>
					            <th>申请时间</th>
					            <th>状态</th>
					            <th>操作</th>
					        </tr>
					    </thead>
					    <tbody>
					        <#list entitys as entity >
					            <tr>
					               <!-- <td>
					                    <div class="checker">
					                        <span>
					                            <input type="checkbox" class="checkboxes" value="${(entity["goco_id"])!}">
					                        </span>
					                    </div>
					                </td>-->
					                <td>
	                                    <div class="media">
	                                        <span class="pull-left thumb-sm">
	                                            <img src="${(entity["goor_first_picture"])!}" alt="商品图片">
	                                        </span>
	                                        <div class="media-body">
	                                            <div><a url="admin/orders/information/detail/${(entity["orin_id"])!}" class="ajaxify">${(entity["goor_name"])!}</a></div>
	                                            <small class="text-muted">单价： <span class="money">${(entity["goor_price"])!}</span><span class="m-l-xs"></span></small>
	                                        </div>
	                                    </div>
	                                </td>
	                                <td>
	                                  	${(entity["wein_nickname"])!}
	                                </td>
	                                <td>
	                                	${(entity["clap_reason"])!}
	                                </td>
	                                <td>
	                                	￥${(entity["clap_money"])!}
	                                </td>
	                                <td>
	                                	<#assign pay=entity.clap_pay_way>
	                                	<#if pay==0>
	                                		 	微信支付
	                                		  <#elseif pay==1>
	                                		  	支付宝
	                                		    <#elseif pay==2>
	                                		    财付通
	                                		    <#elseif pay==3>
	                                		    银行卡支付
	                                		    <#elseif pay==4>
	                                		    货到付款
	                                		    <#elseif pay==5>
	                                		    会员卡余额
	                                	</#if>
	                             
	                                </td>
	                                <td>
	                                    ${(entity["clap_application_time"])!}
	                                </td>
	                                <#assign status=entity.clap_flow_status>
	                                <td>
	                                	<#if status==0>
	                                			待审核
	                                			<#elseif status==1>
	                                			拒绝退款
	                                			<#elseif status==2>
	                                				<#if entity.rein_status==0>
	                                					待退款
	                                					<#else>
	                                					退款成功
	                                				</#if>
	                                			<#else>
	                                			撤销退款
	                                	</#if>
	                                </td>
	                                <td>
	                                	<#if status==0>
								  				<a href="javascript:;" class="modal-ajax-static" url="admin/orders/appeal/${entity.goor_id}/check"><i class="btn btn-sm icon-check"></i></a>
	                                	<#elseif status==2>	
	                                			<#if entity.rein_status==0>
	                                			      <a url="admin/orders/appeal/${entity.rein_id}/refund" title="确认退款" confirm="确认已退款成功吗？" class="bootbox-confirm" href="javascript:;"><i class="btn btn-sm icon-signin"></i> </a>
	                                		   </#if>
	                                	</#if>
	                                </td>
	                                
					            </tr>
					        </#list>
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

