<!-- BEGIN JAVASCRIPTS(Load javascripts at bottom, this will reduce page load time) -->
<script type="text/javascript">
    require(['modules/table/init','modules/dialog/init'], function(Table, Dialog){
        $(document).ready(function(){
            Dialog.init();
            Table.init();
        });
    });
</script>
<!-- END JAVASCRIPTS -->

<!-- BEGIN CSS -->
<link href="${base}/assets/plugins/data-tables/DT_bootstrap.css" rel="stylesheet" type="text/css"/>
<!-- END CSS -->

<!-- BEGIN PAGE CONTENT-->
<div class="row">
    <div class="col-md-12">
        <div class="content-header">
            <div class="form-inline form-body">
                <div class="form-left">
                    <div class="form-group">
                        <div class="title">
                            <i class="icon-list-alt"></i> 账号管理
                        </div>
                    </div>
                </div>
                <div class="form-right">
                    <div class="form-group last">
                        <a id="addRole" href="javascript:;" class="btn btn-success btn-sm ajaxify" url="admin/role/add">
                            <span class="icon-plus"></span> 添加角色
                        </a>
                        <div class="btn-group">
                            <@shiro.hasPermission name="admin:role:save">
                            <a class="btn btn-success btn-sm dropdown-toggle" href="javascript:;" data-toggle="dropdown">
                                批量操作
                                <i class="icon-angle-down"></i>
                            </a>
                            </@shiro.hasPermission>
                            <ul class="dropdown-menu pull-right">
                                <@shiro.hasPermission name="admin:role:delete">
                                <li><a url="admin/role/batchDelete" confirm="确定要批量删除吗？" class="bootbox-batch-confirm" href="javascript:;"><i class="icon-trash"></i> 删除</a></li>
                                </@shiro.hasPermission>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- BEGIN SAMPLE TABLE PORTLET-->
        <div class="portlet content-body">
            <div class="portlet-body form-inline">
                <!-- BEGIN SAMPLE TABLE -->
                <div class="table-responsive paging-table sort-table">
                    <input type="hidden" name="path" value="admin/role/list/" />
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
                            <th>名称</th>
                            <th>字符串</th>
                            <th>描述</th>
                            <th>是否可用</th>
                            <th>操作</th>
                        </tr>
                        </thead>
                        <tbody>
                        <#list roles as role >
                        <tr>
                            <td>
                                <div class="checker">
                                    <span>
                                        <input type="checkbox" class="checkboxes" value="${role.roleId!}">
                                    </span>
                                </div>
                            </td>
                            <td>${role.roleZhName!}</td>
                            <td>${role.roleName!}</td>
                            <td>${role.roleDescription!}</td>
                            <td><#if !role.roleStatus!false>否<#else>是</#if></td>
                            <td>
                            <@shiro.hasPermission name="admin:role:update">
                                <a class="op-btn ajaxify" href="javascript:;" url="admin/role/${role.roleId!}/edit" title="编辑"><i class="icon-edit"></i></a>
                            </@shiro.hasPermission>
                            <@shiro.hasPermission name="admin:role:delete">
                                <a class="op-btn bootbox-confirm" confirm="是否确定删除此角色?" href="javascript:;" url="admin/role/${role.roleId!}/delete" title="删除"><i class="icon-trash"></i></a>
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

<#include "../../common/_js/table_js.ftl" />
