<!-- BEGIN JAVASCRIPTS -->
<#include "../../common/_js/table_js.ftl" />
<script type="text/javascript">
require(["modules/table/init", 'modules/dialog/init',
    'admin/goods/init', 'select2_zh_cn'], function(Table, Dialog, Goods) {
    $(document).ready(function() {
        Table.init();
        Dialog.init();
        Goods.init();
        App.initPlugins();
    });
});
</script>
<!-- END JAVASCRIPTS -->

<!-- BEGIN CSS -->
<link href="${base}/assets/admin/css/style-conquer.css" rel="stylesheet" type="text/css"/>
<link href="${base}/assets/plugins/select2/select2_conquer.css" rel="stylesheet" type="text/css" />
<link href="${base}/assets/plugins/data-tables/DT_bootstrap.css" rel="stylesheet" type="text/css"/>
<!-- END CSS -->

<div class="row goods">
    <div class="col-md-12">
        <div class="content-header">
            <form class="query-form ajax-form form-inline form-body">
                <div class="form-left">
                    <#include "../../common/_template/pagination_control.ftl" />
                    <div class="form-group">
                        <label class="control-label">上/下架</label>
                        <select name="goodPutawayType" operator="eq" data-type="number" class="form-control">
                            <option value="">全部商品</option>
                            <option value="0" data-set="#goodPutawayType:#goodPutawayTime0:#goodEndPutawayTime0">上架中</option>
                            <option value="1" data-set="#goodPutawayType:#goodPutawayTime1:#goodEndPutawayTime1">下架中</option>
                        </select>
                        <input id="goodPutawayType" type="hidden" name="goodPutawayType"
                               condition="or" operator="eq" data-type="number" value="2" />
                        <input id="goodPutawayTime0" type="hidden" name="goodPutawayTime"
                               condition="and" operator="le" data-type="date" value="${.now}" />
                        <input id="goodEndPutawayTime0" type="hidden" name="goodEndPutawayTime"
                               condition="and" operator="gt" data-type="date" value="${.now}" />
                        <input id="goodPutawayTime1" type="hidden" name="goodPutawayTime"
                               condition="and" operator="gt" data-type="date" value="${.now}" />
                        <input id="goodEndPutawayTime1" type="hidden" name="goodEndPutawayTime"
                               condition="or" operator="le" data-type="date" value="${.now}" />
                    </div>
                    <div class="form-group classification">
                        <select name="goclId" operator="eq" data-type="number" class="form-control select2me" placeholder="按自定义分类筛选">
                            <option value=""></option>
                            <#include "goods_classification_options.ftl" />
                        </select>
                    </div>
                    <div class="form-group btn-group">
                        <a class="btn btn-default btn-sm dropdown-toggle" href="javascript:;" data-toggle="dropdown">
                            <span>商品名称</span>
                            <i class="icon-angle-down"></i>
                        </a>
                        <ul class="dropdown-menu">
                            <li><a class="good-name" href="javascript:;">商品名称</a></li>
                            <li><a class="gpst-code" href="javascript:;">商家编码</a></li>
                        </ul>
                        <input name="goodName" operator="like" data-type="string" class="form-control input-medium" />
                        <input style="display: none;" name="gpstCode" operator="like" data-type="string" class="form-control input-medium" />
                    </div>
                    <div class="form-group last">
                        <button type="button" class="btn btn-info btn-sm reset">清空条件</button>
                        <button type="button"  class="btn btn-info btn-sm query">查询</button>
                    </div>
                </div>
                <div class="form-right">
                    <div class="form-group last">
                        <a href="javascript:;" url="admin/goods/add" class="btn btn-success btn-sm ajaxify">
                            <span class="icon-plus"></span> 新增商品
                        </a>
                        <div class="btn-group">
                            <a class="btn btn-success btn-sm dropdown-toggle" href="javascript:;" data-toggle="dropdown">
                                批量操作
                                <i class="icon-angle-down"></i>
                            </a>
                            <ul class="dropdown-menu pull-right">
                                <li><a url="admin/goods/batchUpdatePutawayType?goodPutawayType=0" confirm="确定要批量上架吗？" class="bootbox-batch-confirm" href="javascript:;"><i class="icon-arrow-up"></i> 上架</a></li>
                                <li><a url="admin/goods/batchUpdatePutawayType?goodPutawayType=1" confirm="确定要批量下架吗？" class="bootbox-batch-confirm" href="javascript:;"><i class="icon-arrow-down"></i> 下架</a></li>
                                <li><a url="admin/goods/batchDelete" confirm="确定要批量删除吗？" class="bootbox-batch-confirm" href="javascript:;"><i class="icon-trash"></i> 删除</a></li>
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
				<div class="table-responsive paging-table sort-table">
					<input type="hidden" name="path" value="admin/goods/list/" />
					<table class="table table-striped table-bordered table-hover">
					    <thead>
					        <tr>
					            <th width="3%">
					                <div class="checker">
					                    <span>
					                        <input type="checkbox" class="group-checkable" data-set=".table .checkboxes" />
					                    </span>
					                </div>
					            </th>
								<th width="27%">商品名称</th>
                                <th width="16%">所属分类</th>
                                <th width="9%">价格</th>
                                <th width="9%">总库存</th>
                                <th width="9%">总销量</th>
                                <th width="11%" class="sorting" key="goodPutawayTime">上/下架时间</th>
                                <th width="6%" class="sorting" key="goodSort">排序</th>
                                <th width="10%">操作</th>
					        </tr>
					    </thead>
					    <tbody>
					        <#list list as entity>
					            <tr>
					                <td>
					                    <div class="checker">
					                        <span>
					                            <input type="checkbox" class="checkboxes" value="${entity.goodId!}" />
					                        </span>
					                    </div>
					                </td>
									<td>
                                        <div class="goods-name">
                                            <img src="${entity.goodFirstPicture!}" />
                                            <div>${entity.goodName!}</div>
                                        </div>
                                    </td>
                                    <td>
                                        <#if entity.goclNameSets??>
                                            <#list entity.goclNameSets as goclNameSet>
                                                ${goclNameSet!}<#break>
                                            </#list>
                                            <#if (entity.goclNameSets?size > 1)>
                                                <div class="btn-group">
                                                    <a class="btn btn-default btn-xs dropdown-toggle" href="javascript:;" data-toggle="dropdown">
                                                        更多[${entity.goclNameSets?size}]
                                                        <i class="icon-angle-down"></i>
                                                    </a>
                                                    <ul class="dropdown-menu pull-right">
                                                        <#list entity.goclNameSets as goclNameSet>
                                                            <li><a href="javascript:;">${goclNameSet!}</a></li>
                                                        </#list>
                                                    </ul>
                                                </div>
                                            </#if>
                                        </#if>
                                    </td>
                                    <td>${entity.goodPrice!}</td>
                                    <td>${entity.goodStock!}</td>
                                    <td>${entity.goodSales!}</td>
                                    <td>
                                        <#if entity.goodPutawayType == 1
                                            || (entity.goodPutawayType == 2
                                            && ((entity.goodPutawayTime?? && entity.goodPutawayTime > .now)
                                            || (entity.goodEndPutawayTime?? && entity.goodEndPutawayTime <= .now)))>
                                            ${entity.goodEndPutawayTime!}
                                        <#else>
                                            ${entity.goodPutawayTime!}
                                        </#if>
                                    </td>
                                    <td>${entity.goodSort!}</td>
                                    <td>
                                        <a href="javascript:;"  url="admin/goods/${entity.goodId}/edit" title="编辑" class="ajaxify"><i class="op-btn icon-edit"></i></a>
                                        <a href="javascript:;"  data-action="admin/goods/${entity.goodId}/detail" title="预览" class="js_multy_dispatch"><i class="op-btn icon-search"></i></a>
                                        <#if entity.goodPutawayType == 1
                                            || (entity.goodPutawayType == 2
                                            && ((entity.goodPutawayTime?? && entity.goodPutawayTime > .now)
                                            || (entity.goodEndPutawayTime?? && entity.goodEndPutawayTime <= .now)))>
                                            <a href="javascript:;"  url="admin/goods/${entity.goodId}/updatePutawayType?goodPutawayType=0" title="上架" confirm="确定要上架吗？" class="bootbox-confirm success"><i class="op-btn icon-arrow-up"></i></a>
                                        <#else>
                                            <a href="javascript:;"  url="admin/goods/${entity.goodId}/updatePutawayType?goodPutawayType=1" title="下架" confirm="确定要下架吗？" class="bootbox-confirm danger"><i class="op-btn icon-arrow-down"></i></a>
                                        </#if>
                                        <a href="javascript:;"  url="admin/goods/${entity.goodId}/delete" title="删除" confirm="确定要删除吗？" class="bootbox-confirm"><i class="op-btn icon-trash"></i></a>
                                    </td>
					            </tr>
					        </#list>
					    </tbody>
					</table>
				</div>
				<!-- END SAMPLE TABLE PORTLET-->
			</div>
		</div>
	</div>
</div>

