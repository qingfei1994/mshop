<!-- BEGIN JAVASCRIPTS -->
<script type="text/javascript">
    require(["modules/form/init"], function(Form) {
        $(document).ready(function(){
            Form.init();
        });
    });
</script>
<!-- END JAVASCRIPTS -->

<div class="modal-dialog">
    <div class="modal-content">
	    <form class="form-horizontal ajax-form valid" role="form">
	        <div class="form-body">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
                    <h4 class="modal-title">添加子权限</h4>
                </div>
                <div class="modal-body">
                    <input id="permParentId" name="permParentId" type="hidden"
                           value="<#if parentPermission??>${(parentPermission.permId)!}<#else>${(permission.permParentId)!}</#if>">
                    <input id="permId" name="permId" type="hidden" value="${(permission.permId)!}">
                    <input id="permParentTree" name="permParentTree" type="hidden" value="${(permission.permParentTree)!}">
                    <div class="col-md-12">
                        <div class="form-group">
                            <label  class="col-md-3 control-label">父权限名称</label>
                            <div class="col-md-6">
                                <input type="text" class="form-control" id="parentPermName" disabled value="${(parentPermission.permName)!}">
                            </div>
                        </div>
                        <div class="form-group">
                            <label  class="col-md-3 control-label">权限名称</label>
                            <div class="col-md-6">
                                <input type="text" class="form-control"  placeholder="请输入权限名称" name="permName" value="${(permission.permName)!}" required="true">
                            </div>
                        </div>
                        <div class="form-group">
                            <label  class="col-md-3 control-label">权限描述</label>
                            <div class="col-md-6">
                                <input type="text" class="form-control"  placeholder="请输入权限描述" name="permDescription" value="${(permission.permDescription)!}">
                            </div>
                        </div>
                        <div class="form-group">
                            <label  class="col-md-3 control-label">权限排序</label>
                            <div class="col-md-6">
                                <input type="text" class="form-control"  placeholder="请输入权限排序" name="permSort" value="${(permission.permSort)!}">
                            </div>
                        </div>
                        <div class="form-group">
                            <label  class="col-md-3 control-label">权限图标</label>
                            <div class="col-md-6">
                                <input type="text" class="form-control"  placeholder="请输入权限图标" name="permIcon" value="${(permission.permIcon)!}">
                            </div>
                        </div>
                        <div class="form-group">
                            <label  class="col-md-3 control-label">权限范围</label>
                            <div class="col-md-6">
                                <select name="permScope" class="form-control" required="true">
                                    <option value=""></option>
                                    <option value="1" <#if (permission.permScope)?? && permission.permScope == 1>selected</#if>>页面权限</option>
                                    <option value="2" <#if (permission.permScope)?? && permission.permScope == 2>selected</#if>>链接</option>
                                    <option value="3" <#if (permission.permScope)?? && permission.permScope == 3>selected</#if>>模块</option>
                                </select>
                            </div>
                        </div>
                        <div class="form-group">
                            <label  class="col-md-3 control-label">是否可用</label>
                            <div class="col-md-6">
                                <select name="permStatus" class="form-control" required="true">
                                    <option value=""></option>
                                    <option value="0" <#if (permission.permStatus)?? && !permission.permStatus>selected</#if>>否</option>
                                    <option value="1" <#if (permission.permStatus)?? && permission.permStatus>selected</#if>>是</option>
                                </select>
                            </div>
                        </div>
                        <div class="form-group">
                            <label  class="col-md-3 control-label">权限字符串</label>
                            <div class="col-md-6">
                                <input type="text" class="form-control"  placeholder="请输入权限字符串" name="permPermission" value="${(permission.permPermission)!}">
                            </div>
                        </div>
                        <div class="form-group">
                            <label  class="col-md-3 control-label">权限url</label>
                            <div class="col-md-6" tip="非url类型可不填" tip-length="col-md-3">
                                <input type="text" class="form-control"  placeholder="请输入权限url" name="permUrl" value="${(permission.permUrl)!}">
                            </div>
                        </div>

                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" data-dismiss="modal" class="btn btn-default">取消</button>
                    <#if permission??>
                        <button id="sureSubmitEditPermission" url="admin/permission/edit"
                                type="button" class="btn btn-success ajax-submit-modal">更新</button>
                    <#else>
                        <button id="sureSubmitAddPermission" url="admin/permission/add"
                                type="button" class="btn btn-success ajax-submit-modal">确定</button>
                    </#if>

                </div>
            </div>
    	</form>
    </div>
</div>
