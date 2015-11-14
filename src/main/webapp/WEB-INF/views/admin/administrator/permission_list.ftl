<!-- BEGIN JAVASCRIPTS -->
<script type="text/javascript">
    require(['modules/dialog/init', 'jquery_treetable'], function(Dialog){
        $(document).ready(function() {
            Dialog.init();
            App.initPlugins();
        });
    });
</script>
<!-- END JAVASCRIPTS -->

<!-- BEGIN CSS -->
<link href="${base}/assets/plugins/data-tables/DT_bootstrap.css" rel="stylesheet" type="text/css"/>
<link href="${base}/assets/plugins/ludo-jquery-treetable/css/jquery.treetable.css" rel="stylesheet" type="text/css"/>
<!-- END CSS -->

<!-- BEGIN PAGE CONTENT-->
<div class="row">
    <div class="col-md-12">
        <!-- BEGIN SAMPLE TABLE PORTLET-->
        <div class="portlet">
            <div class="portlet-title">
                <div class="caption">
                    <i class="icon-list-alt"></i>权限列表
                </div>
            </div>
            <div class="portlet-body form-inline">
                <!-- BEGIN SAMPLE TABLE -->
                <div class="table-responsive paging-table sort-table">
                    
                    <table class="table table-striped table-bordered table-hover tree-table">
                        <thead>
                        <tr>
                            <th>名字</th>
                            <th>类型</th>
                            <th>url</th>
                            <th>权限字符串</th>
                            <th>排序</th>
                            <th>是否可用</th>
                            <th>操作</th>
                        </tr>
                        </thead>
                        <tbody>
                        <#list permissions as permission >
                        <tr data-tt-id="${permission.permId}"
                            <#if permission.permParentId??>data-tt-parent-id="${permission.permParentId}"</#if>>
                            <td>
                                <a href="javascript:;" class="tooltips" data-original-title="${permission.permDescription!}">
                                    <i class="${permission.permIcon!}"></i>${permission.permName!}
                                </a>
                            </td>
                            <td>
                                <#if (permission.permScope)?? && permission.permScope == 1>页面权限
                                <#elseif (permission.permScope)?? && permission.permScope == 2>链接
                                <#elseif (permission.permScope)?? && permission.permScope == 3>模块
                                </#if>
                            </td>
                            <td>${permission.permUrl!}</td>
                            <td><code>${permission.permPermission!}</code></td>
                            <td>${permission.permSort!}</td>
                            <td><#if !permission.permStatus!false>否<#else>是</#if></td>
                            <td>
                            <@shiro.hasPermission name="admin:permission:update">
                                <a class="op-btn modal-ajax-static" href="javascript:;" url="admin/permission/${permission.permId}/edit" title="编辑"><i class="icon-edit"></i></a>
                            </@shiro.hasPermission>

                            <@shiro.hasPermission name="admin:permission:delete">
                                <#if permission_index == 0 >
                                    <span class="op-btn">
                                        <i class="icon-trash"></i>
                                    </span>
                                <#else>
                                    <a class="op-btn bootbox-confirm"  confirm="是否确定删除此权限及此权限下的所有子权限?" href="javascript:;" url="admin/permission/${permission.permId}/delete" title="删除">
                                        <i class="icon-trash"></i>
                                    </a>
                                </#if>
                            </@shiro.hasPermission>

                            <@shiro.hasPermission name="admin:permission:save">
                                <a class="op-btn modal-ajax-static" href="javascript:;" url="admin/permission/${permission.permId}/add" title="添加子权限"><i class="icon-plus"></i></a>
                            </@shiro.hasPermission>
                            </td>
                        </tr>
                        </#list>
                        </tbody>
                    </table>
                </div>
                <!-- END SAMPLE TABLE -->
            </div>
        </div>
        <!-- END SAMPLE TABLE PORTLET-->
    </div>
</div>
<!-- END PAGE CONTENT-->
