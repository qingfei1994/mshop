<!-- BEGIN JAVASCRIPTS -->
<#include "../../common/_js/table_js.ftl" />
<script type="text/javascript">
require(["modules/table/init", 'modules/dialog/init'], function(Table, Dialog){
    $(document).ready(function(){
        Table.init();
        Dialog.init();
    });
});

</script>
<!-- END JAVASCRIPTS -->
<!-- BEGIN CSS -->
<link href="${base}/assets/admin/css/style-conquer.css" rel="stylesheet" type="text/css"/>
<link href="${base}/assets/plugins/data-tables/DT_bootstrap.css" rel="stylesheet" type="text/css"/>
<!-- END CSS -->
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">   
<!--test-->
<div class="row">
    <div class="col-md-12">
        <div class="content-header">
            <form class="form-body">
                <div class="form-left">
                    <div class="form-group">
                        <div class="title">
                            <i class="icon-list-alt"></i> 运单模板设置
                        </div>
                    </div>
                </div>
                <div class="form-right">
                    <div class="form-group last">
                        <a href="javascript:;" url="admin/logistics/waybilltemplate/toAddWaybillTemplate" class="btn btn-sm btn-success ajaxify"><span class="icon-plus"></span> 新增运单模板</a>
                        <div class="btn-group">
                            <a class="btn btn-sm btn-success dropdown-toggle" href="javascript:;" data-toggle="dropdown">
                                批量操作
                                <i class="icon-angle-down"></i>
                            </a>
                            <ul class="dropdown-menu pull-right">
                                <li><a url="admin/logistics/waybilltemplate/batchDelete" confirm="确定要批量删除吗？" class="bootbox-batch-confirm" href="javascript:;"><i class="icon-trash"></i> 删除</a></li>
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
					<input type="hidden" name="path" value="admin/logistics/waybilltemplate/list/" />
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
					            <th>运单模板名称</th>
					            <th>快递公司</th>
					            <th>默认</th>
					            <th>操作</th>
					        </tr>
					    </thead>
					    <tbody>
					        <#list entitys as entity >
					            <tr>
					                <td>
					                    <div class="checker">
					                        <span>
					                            <input type="checkbox" class="checkboxes" value="${entity["wate_id"]!}">
					                        </span>
					                    </div>
					                </td>
					                <td>${entity["wate_name"]!}</td>
					                <td>${entity["exco_name"]!}</td>
					                <td><#if (entity["wate_default"])?? && entity["wate_default"] == 1><i class="icon-ok text-success"></i><#else><i class="icon-remove text-danger"></i></#if></td>
					                <td>
					                    <a href="javascript:;"  url="admin/logistics/waybilltemplate/edit/${entity["wate_id"]!}" title="编辑" class="ajaxify"><i class="op-btn icon-edit"></i></a>
                                        <a href="javascript:;" confirm="确定删除？" url="admin/logistics/waybilltemplate/delete/${entity["wate_id"]!}"
                                           title="删除" class="bootbox-confirm">
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

