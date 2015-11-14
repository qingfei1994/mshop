<!-- BEGIN JAVASCRIPTS -->
<script type="text/javascript">
	require(['admin/goods/init','modules/dialog/init'], function(ClassificationList, Dialog){
		$(document).ready(function(){
			ClassificationList.init();
			Dialog.init();
		});
	});
</script>
<!-- END JAVASCRIPTS -->
<!-- BEGIN CSS -->
<link href="${base}/assets/plugins/data-tables/DT_bootstrap.css" rel="stylesheet" type="text/css"/>
<link href="${base}/assets/plugins/jquery-nestable/jquery.nestable.css" rel="stylesheet" type="text/css"/>
<!-- END CSS -->
<!-- BEGIN PAGE CONTENT-->
<div class="row">
    <div class="col-md-12">
        <div class="content-header">
            <div class="form-inline form-body">
                <div class="form-left">
                    <div class="form-group">
                        <div class="title">
                            <i class="icon-list-alt"></i> 分组列表
                        </div>
                    </div>
                </div>
                <div class="form-right">
                    <div class="form-group last">
                    	<a href="javascript:;" class="btn btn-sm btn-success modal-ajax-static" url="admin/goods/classification/addparent"><span class="icon-plus"></span> 新增分组</a>
                    	<a id="classificationListToggle" class="btn btn-sm btn-default" data-action="collapse-all"><span class="icon-minus"></span> 折叠全部</a>
                    </div>
                </div>
			</div>
		</div>
        <!-- BEGIN SAMPLE TABLE PORTLET-->
        <div class="content-body portlet">
            <div class="portlet-body form-inline">
			   <!-- BEGIN SAMPLE TABLE -->
			    <div class="dd" id="classificationList">
					<ol class="dd-list">
						<#list groups as group>
							<li class="dd-item" data-id="${group.goclId}">
							  <div class="dd-handle">
								  <img width="35" height="35" src="${group.goclIconUrl!}"></img>
								  <span id="groupName">${group.goclName}</span>
								  <span class="pull-right">
								  <a href="javascript:;" class="modal-ajax-static" url="admin/goods/classification/${group.goclId}/add"><i class="btn btn-sm icon-plus"></i></a>
								  <a href="javascript:;" class="modal-ajax-static" url="admin/goods/classification/${group.goclId}/edit"><i class="btn btn-sm icon-pencil"></i></a>
								  <a url="admin/goods/classification/${group.goclId}/delete" confirm="确定删除吗？" class="bootbox-confirm" href="javascript:;"><i class="btn btn-sm icon-trash"></i></a>
								  </span>
							  </div>
							  <#if group.children??>
								 <ol class="dd-list">
									<#list group.children as child>
										<li class="dd-item" data-id="${child.goclId}">
											<div class="dd-handle">
												<img width="35" height="35" src="${child.goclIconUrl!}"></img>
												<span id="groupName">${child.goclName}</span>
												<span class="pull-right">
													<a href="javascript:;" class="modal-ajax-static" url="admin/goods/classification/${child.goclId}/edit"><i class="btn btn-sm icon-pencil"></i></a>
													<a url="admin/goods/classification/${child.goclId}/delete" confirm="确定删除吗？" class="bootbox-confirm" href="javascript:;"><i class="btn btn-sm icon-trash"></i></a>
												</span>
											</div>
										</li>
									</#list>
								</ol>
							</#if>
						  </li>
						</#list>
					</ol>
				</div>
                <!-- END SAMPLE TABLE -->
            </div>
		</div>
        <!-- END SAMPLE TABLE PORTLET-->
        <div class="content-footer">
            <div class="center-button">
                <button id="saveGroupBtn" type="button" class="btn btn-success">保存</button>
                <button type="button" class="btn">取消</button>
            </div>
        </div>
	</div>
</div>
<!-- END PAGE CONTENT-->

<!-- BEGIN JAVASCRIPTS(Load javascripts at bottom, this will reduce page load time) -->
<!-- END JAVASCRIPTS -->
