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
                        <label class="control-label">评论等级</label>
                        <select name="goco_satisfaction" operator="eq" data-type="number" class="form-control">
                            <option value="">全部</option>
                            <option value="5" data-set="#exSatisfaction">好评</option>
                            <option value="3" data-set="#miSatisfaction">中评</option>
                            <option value="1" data-set="#coSatisfaction">差评</option>
                        </select>
                        <input type="hidden" id="exSatisfaction" name="goco_satisfaction"
                               condition="or" operator="eq" data-type="number" value="4">
                        <input type="hidden" id="miSatisfaction" name="goco_satisfaction"
                               condition="or" operator="eq" data-type="number" value="2">
                        <input type="hidden" id="coSatisfaction" name="goco_satisfaction"
                               condition="or" operator="eq" data-type="number" value="0">
                    </div>
                    <div class="form-group">
                        <a class="reportrange btn form-control" data-label="评价时间" data-for="goco_comment_time" data-type="date"></a>
                    </div>
                    <div class="form-group btn-group">
                        <a class="btn btn-default btn-sm dropdown-toggle" href="javascript:;" data-toggle="dropdown">
                            <span>商品名称</span>
                            <i class="icon-angle-down"></i>
                        </a>
                        <ul class="dropdown-menu">
                            <li><a class="goor-name" data-for="order.goor_name" data-label="商品名称" data-trogger="dropdown-item" href="javascript:;">商品名称</a></li>
                            <li><a class="wein-nickname" data-for="wein_nickname" data-label="粉丝昵称" data-trogger="dropdown-item" href="javascript:;">粉丝昵称</a></li>
                            <li><a class="orin-no" data-for="orin_no" data-label="订单编号" data-trogger="dropdown-item" href="javascript:;">订单编号</a></li>
                        </ul>
                        <input name="goor_name" operator="like" data-type="string" class="form-control input-medium">
                        <input style="display: none;" name="wein_nickname" operator="like" data-type="string" class="form-control input-medium">
                        <input style="display: none;" name="orin_no" operator="like" data-type="string" class="form-control input-medium">
                    </div>
                    <div class="form-group">
                        <button type="button" class="btn btn-info btn-sm reset">清空条件</button>
                        <button type="button"  class="btn btn-info btn-sm query">查询</button>
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
                                <li><a url="admin/order/comment/batchDelete" confirm="确定要批量删除吗？" class="bootbox-batch-confirm" href="javascript:;"><i class="icon-trash"></i> 删除</a></li>
                            </ul>
                        </div>
                    </div>
                </div>
            </form>
        </div>
        
        <div class="content-body portlet">
            <div class="portlet-body">
                <!-- BEGIN SAMPLE TABLE -->
                <!--table-->
				<div class="table-responsive paging-table sort-table">
					<input type="hidden" name="path" value="admin/order/comment/list/" />
					<table class="table table-striped table-bordered table-hover">
					    <thead>
					        <tr>
					            <th width="20px">
					                <div class="checker">
					                    <span>
					                        <input type="checkbox" class="group-checkable checkboxes" data-set=".table .checkboxes" />
					                    </span>
					                </div>
					            </th>
					            <th>商品信息</th>
					            <th>客户</th>
					            <th>评论内容</th>
					            <th>评论等级</th>
					            <th>评论时间</th>
					            <th>是否显示</th>
					            <th>操作</th>
					        </tr>
					    </thead>
					    <tbody>
					        <#list entitys as entity >
					            <tr>
					                <td>
					                    <div class="checker">
					                        <span>
					                            <input type="checkbox" class="checkboxes" value="${(entity["goco_id"])!}">
					                        </span>
					                    </div>
					                </td>
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
	                                	${(entity["goco_comment_content"])!}
	                                </td>
	                                <td>
	                                	<#assign num = (entity["goco_satisfaction"])!0>
	                                	<#list 1..num as i>
	                                    <i class="icon-star text-danger"></i>
	                                    </#list>
	                                </td>
	                                <td>
	                                    ${(entity["goco_comment_time"])!}
	                                </td>
	                                
	                                <td>
	                                    <a href="javascript:;" url="admin/order/comment/isShow/${(entity["goco_id"])!}" class="comment_show">
	                                    <#if (entity["goco_show_status"])?? && entity["goco_show_status"] == 1>
	                                    <i class="icon-ok text-success text-active" data-msg="1" ></i>
	                                    <#else>
	                                    <i class="icon-remove text-danger text" data-msg="0"></i>
	                                    </#if>
	                                    </a>
	                                </td>
					                <td>
					                    <a href="javascript:;"  url="admin/order/comment/toReply/${entity["goco_id"]!}" title="回复" class="modal-ajax-static"><i class="op-btn icon-comments-alt"></i></a>
                                        <a href="javascript:;" confirm="确定删除？" url="admin/order/comment/delete/${(entity["goco_id"])!}" title="删除" class="bootbox-confirm">
                                            <i class="op-btn icon-trash"></i>
                                        </a>
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

