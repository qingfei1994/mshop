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
                    <div class="form-group">
                        <div class="title">
                            <i class="icon-list-alt"></i> 微信会员管理
                        </div>
                    </div>
					<#include "../../common/_template/pagination_control.ftl" />
                </div>
            </form>
        </div>
        <!-- BEGIN SAMPLE TABLE PORTLET-->
        <div class="content-body portlet">
            <div class="portlet-body">
                <!-- BEGIN SAMPLE TABLE -->
                <!--table-->
				<div class="table-responsive paging-table sort-table">
					<input type="hidden" name="path" value="admin/clients/wechatmembers/list/" /><!-- 分页、排序、查询用到的path -->
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
					            
					            <th>姓名</th>
					            <th>手机号码</th>
								<th>领卡时间</th>
					            <th>余额</th>
					            <th>剩余积分</th>
					        </tr>
					    </thead>
					    <tbody>
					        <#if list??>
					        	<#list list as entity >
					            <tr>
					                <td>
					                    <div class="checker">
					                        <span>
					                            <input type="checkbox" class="checkboxes" value="${entity.meinId!}">
					                        </span>
					                    </div>
					                </td>
					                
					                <td>${entity.meinName!}</td>
					                <td>${entity.meinPhone!}</td>
					                <td>${entity.meinCreateTime!}</td>
					                <td>${entity.meinBalance!}</td>
					                <td>${entity.meinIntegral!}</td>
					                
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

