<!-- BEGIN JAVASCRIPTS -->
<script>
    require(["modules/form/init", "admin/role/role_edit"], function(Form, RoleEdit) {
    	$(document).ready(function(){
    		Form.init();
            RoleEdit.init();
    	});
    });
</script>
<!-- END JAVASCRIPTS -->

<!-- BEGIN PAGE CONTENT-->
<div class="row">
    <div class="col-md-12">
        <!-- BEGIN VALIDATION STATES-->
        <div class="portlet">
            <div class="portlet-title">
                <div class="caption"><i class="icon-reorder"></i>编辑管理员角色</div>
            </div>
            <div class="portlet-body form">
                <!-- BEGIN FORM-->
                <form class="form-horizontal ajax-form valid">
                    <input type="hidden" name="roleId" value="${(role.roleId)!}">
                    <div class="form-body">
                        <div id="" class="form-group">
                            <label class="control-label col-md-2">名称：</label>
                            <div class="col-md-4">
                                <input type="text" class="form-control" name="roleZhName" placeholder="请填写角色名称" required="true" value="${(role.roleZhName)!}">
                            </div>
                        </div>
                        <div id="" class="form-group">
                            <label class="control-label col-md-2">描述：</label>
                            <div class="col-md-4">
                                <input type="text" class="form-control" name="roleDescription" placeholder="请填写描述" required="true" value="${(role.roleDescription)!}">
                            </div>
                        </div>
                        <div id="" class="form-group">
                            <label class="control-label col-md-2">是否可用：</label>
                            <div class="col-md-4">
                                <select name="roleStatus" class="form-control" required="true">
                                    <option value=""></option>
                                    <option value="0" <#if (role.roleStatus)?? && !role.roleStatus>selected</#if>>否</option>
                                    <option value="1" <#if (role.roleStatus)?? && role.roleStatus>selected</#if>>是</option>
                                </select>
                            </div>
                        </div>
                        <div id="" class="form-group">
                            <label class="control-label col-md-2">权限：</label>
                            <div class="col-md-9">

                                <div class="table-responsive">
                                    <table class="table table-bordered">
                                        <tbody>
                                            <#list permission.availableSubPermissions as permission1 >
                                                <#assign rowspan=0/>
                                                <#list permission1.availableSubPermissions as permission2 >
                                                    <#if permission2.availableSubPermissions?size = 0 >
                                                        <#assign rowspan=rowspan + 1/>
                                                    <#else>
                                                        <#assign rowspan=rowspan + permission2.availableSubPermissions?size/>
                                                    </#if>
                                                </#list>
                                                <tr>
                                                    <td rowspan="${rowspan}" class="v-middle">
                                                        <input type="checkbox" value="${permission1.permId}" name="permId" class="checkboxes ${permission1.permParentTree}" parent-id="${permission1.permParentId}"
                                                                <#list rolePermissions as rolePermission>
                                                                    <#if rolePermission.permId == permission1.permId>
                                                                        checked
                                                                    </#if>
                                                                </#list>>
                                                        ${permission1.permName}
                                                    </td>
                                                <#if permission1.availableSubPermissions?size == 0>
                                                    <td>&nbsp;</td>
                                                    <td>&nbsp;</td>
                                                </tr>
                                                </#if>

                                                <#list permission1.availableSubPermissions as permission2 >
                                                    <#if permission2_index == 0>
                                                        <td rowspan="${permission2.availableSubPermissions?size}" class="v-middle">
                                                            <input type="checkbox" value="${permission2.permId}" name="permId" class="checkboxes ${permission2.permParentTree}" parent-id="${permission2.permParentId}"
                                                                <#list rolePermissions as rolePermission>
                                                                    <#if rolePermission.permId == permission2.permId>
                                                                   checked
                                                                    </#if>
                                                                </#list>>
                                                            ${permission2.permName}
                                                        </td>
                                                        <#if permission2.availableSubPermissions?size == 0>
                                                            <td>&nbsp;</td>
                                                        </tr>
                                                        </#if>

                                                        <#list permission2.availableSubPermissions as permission3 >
                                                            <#if permission3_index == 0>
                                                                <td>
                                                                    <input type="checkbox" value="${permission3.permId}" name="permId" class="checkboxes ${permission3.permParentTree}" parent-id="${permission3.permParentId}"
                                                                        <#list rolePermissions as rolePermission>
                                                                            <#if rolePermission.permId == permission3.permId>
                                                                           checked
                                                                            </#if>
                                                                        </#list>>
                                                                    ${permission3.permName}
                                                                </td>
                                                            </tr>
                                                            <#else>
                                                            <tr>
                                                                <td>
                                                                    <input type="checkbox" value="${permission3.permId}" name="permId" class="checkboxes ${permission3.permParentTree}" parent-id="${permission3.permParentId}"
                                                                        <#list rolePermissions as rolePermission>
                                                                            <#if rolePermission.permId == permission3.permId>
                                                                           checked
                                                                            </#if>
                                                                        </#list>>
                                                                    ${permission3.permName}
                                                                </td>
                                                            </tr>
                                                            </#if>
                                                        </#list>

                                                    <#else>
                                                    <tr>
                                                        <td rowspan="${permission2.availableSubPermissions?size}" class="v-middle">
                                                            <input type="checkbox" value="${permission2.permId}" name="permId" class="checkboxes ${permission2.permParentTree}" parent-id="${permission2.permParentId}"
                                                                <#list rolePermissions as rolePermission>
                                                                    <#if rolePermission.permId == permission2.permId>
                                                                   checked
                                                                    </#if>
                                                                </#list>>
                                                            ${permission2.permName}
                                                        </td>
                                                        <#if permission2.availableSubPermissions?size == 0>
                                                            <td>&nbsp;</td>
                                                        </tr>
                                                        </#if>
                                                        <#list permission2.availableSubPermissions as permission3 >
                                                            <#if permission3_index == 0>
                                                                <td>
                                                                    <input type="checkbox" value="${permission3.permId}" name="permId" class="checkboxes ${permission3.permParentTree}" parent-id="${permission3.permParentId}"
                                                                        <#list rolePermissions as rolePermission>
                                                                            <#if rolePermission.permId == permission3.permId>
                                                                           checked
                                                                            </#if>
                                                                        </#list>>
                                                                    ${permission3.permName}
                                                                </td>
                                                            </tr>
                                                            <#else>
                                                            <tr>
                                                                <td>
                                                                    <input type="checkbox" value="${permission3.permId}" name="permId" class="checkboxes ${permission3.permParentTree}" parent-id="${permission3.permParentId}"
                                                                        <#list rolePermissions as rolePermission>
                                                                            <#if rolePermission.permId == permission3.permId>
                                                                           checked
                                                                            </#if>
                                                                        </#list>>
                                                                    ${permission3.permName}
                                                                </td>
                                                            </tr>
                                                            </#if>
                                                        </#list>
                                                    </#if>
                                                </#list>
                                            </#list>
                                        </tbody>
                                    </table>
                                </div>

                            </div>
                        </div>
                    </div>
                    <div class="form-actions fluid">
                        <div class="col-md-offset-3 col-md-9">
                            <button type="button" url="admin/role/edit" class="btn btn-success ajax-submit">修改</button>
                            <button type="button" url="admin/role/list" class="btn btn-default ajaxify">取消</button>
                        </div>
                    </div>
                </form>
                <!-- END FORM-->
            </div>
        </div>
        <!-- END VALIDATION STATES-->
    </div>
</div>
<!-- END PAGE CONTENT-->