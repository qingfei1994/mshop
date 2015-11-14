<!-- BEGIN JAVASCRIPTS -->
<script type="text/javascript">
    require(['modules/form/init', 'bootstrap_datepicker_zh_cn'], function(Form){
        $(document).ready(function(){
        	App.initPlugins();
            Form.init();
        });
    });
</script>
<!-- END JAVASCRIPTS -->

<!-- BEGIN CSS -->
<link href="${base}/assets/plugins/bootstrap-datepicker/css/datepicker.css" rel="stylesheet" type="text/css"/>
<!-- END CSS -->

<!-- BEGIN PAGE CONTENT-->
<div class="row">
    <div class="col-md-12">
        <!-- BEGIN VALIDATION STATES-->
        <div class="portlet">
            <div class="portlet-title">
                <div class="caption"><i class="icon-reorder"></i>添加管理员</div>
            </div>
            <div class="portlet-body form">
                <!-- BEGIN FORM-->
                <form class="form-horizontal ajax-form valid">
                    <div class="form-body">
                        <h4 class="form-section">账号信息</h4>
                        <div class="form-group">
                            <label class="control-label col-md-3"><span class="required">*</span>账号</label>
                            <div class="col-md-4" tip="注：一般使用手机号" tip-length="col-md-5">
                                <input name="admiAccount" type="text" class="form-control" required="true" rangelength="6,16"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="control-label col-md-3"><span class="required">*</span>密码</label>
                            <div class="col-md-4">
                                <input id="password" name="admiPassword" value="" type="password" class="form-control" required="true" minlength="6" maxlength="16"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="control-label col-md-3"><span class="required">*</span>确认密码</label>
                            <div class="col-md-4" tip="必须与上方密码匹配">
                                <input name="repassword" value="" type="password" class="form-control" required="true" rangelength="6,16" equalTo="#password"/>
                            </div>
                        </div>
                        <div class="form-group">
                        <label class="control-label col-md-3"><span class="required">*</span>是否启用</label>
                        <div class="col-md-4">
                            <select name="admiStatus" class="form-control" required="true">
                                <option value=""></option>
                                <option value="0">否</option>
                                <option value="1">是</option>
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-md-3"><span class="required">*</span>选择角色</label>
                        <div class="col-md-9">
                            <div class="checkbox-list">
                                <#if roles??>
                                    <#list roles as role>
                                    <label class="checkbox-inline">
                                        <input type="checkbox" name="roleId" value="${role.roleId!}" required="true" > ${role.roleZhName!}
                                    </label>
                                    </#list>
                                </#if>
                            </div>
                        </div>
                    </div>

                    </div>
                    <div class="form-actions fluid">
                        <div class="col-md-offset-3 col-md-9">
                            <button url="admin/administrator/add" type="button" class="btn btn-success ajax-submit">提交</button>
                            <button url="admin/administrator/list" type="button" class="btn btn-default ajaxify">取消</button>
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

