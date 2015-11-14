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
            <form class="query-form ajax-form form-inline form-body">
                <div class="form-left">
					<#include "../../common/_template/pagination_control.ftl" />
                    <div class="form-group">
                        <label class="control-label">标签名称</label>
                        <input name="golaName" operator="like" data-type="string" class="form-control input-small" />
                    </div>

                    <div class="form-group form-left">
                        <button type="button" class="btn btn-info btn-sm reset">清空条件</button>
                        <button type="button"  class="btn btn-info btn-sm query ajax-submit">查询</button>
                    </div>
                </div>
                <div class="form-right">
                    <a href="javascript:;" url="admin/goods/label/addGoodsLabel" class="btn btn-sm btn-success modal-ajax-static"><span class="icon-plus"></span> 新增标签</a>
                    <div class="btn-group">
                        <a class="btn btn-sm btn-success dropdown-toggle" href="javascript:;" data-toggle="dropdown">
                            批量操作
                            <i class="icon-angle-down"></i>
                        </a>
                        <ul class="dropdown-menu pull-right">
                            <li><a url="admin/goods/label/batchDelete" confirm="确定要批量删除吗？" class="bootbox-batch-confirm" href="javascript:;"><i class="icon-trash"></i> 删除</a></li>
                        </ul>
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
					<input type="hidden" name="path" value="admin/goods/label/list/" /><!-- 分页、排序、查询用到的path -->
					<table class="table table-striped table-bordered table-hover"><!-- 统一风格写法 -->
					    <thead>
					        <tr>
					            <th width="20px">
					                <div class="checker">
					                    <span>
					                        <input type="checkbox" class="group-checkable" data-set=".table .checkboxes" />
					                    </span>
					                </div>
					            </th>
					            
					            <th>商品标签名称</th>
					            <th class="sorting" key="golaSort">排序</th>

					            <th>操作</th>
					        </tr>
					    </thead>
					    <tbody>
					        <#if list??>
					        	<#list list as entity >
					            <tr>
					                <td>
					                    <div class="checker">
					                        <span>
					                            <input type="checkbox" class="checkboxes" value="${entity.golaId!}">
					                        </span>
					                    </div>
					                </td>
					                
					                <td>${entity.golaName!}</td>
					                <td>${entity.golaSort!}</td>
					                
					                <td>
					                    <a href="javascript:;"  url="admin/goods/label/modify?golaId=${entity.golaId!}" title="编辑" class="modal-ajax-static"><i class="op-btn icon-edit"></i></a>
                                        <a href="javascript:;" confirm="确定删除？" url="admin/goods/label/delete?golaId=${entity.golaId!}"
                                           title="删除" class="bootbox-confirm">
                                            <i class="op-btn icon-trash"></i>
                                        </a>
									</td>
					            </tr>
					        	</#list>
					        </#if>
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

