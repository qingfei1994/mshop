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
        <!-- BEGIN HEADER -->
        <div class="content-header">
            <div class="form-inline form-body">
                <div class="form-left">
                    <div class="form-group">
                        <div class="title">
                            <i class="icon-list-alt"></i> 运费模板
                        </div>
                    </div>
                </div>
                <div class="form-right">
                    <div class="form-group">
                        <a href="javascript:;" url="admin/logistics/transportation/toAddTransExpeTemplate" class="btn btn-success ajaxify"><span class="icon-plus"></span> 新建运费模板</a>
                    </div>
				</div>
            </div>
        </div>
        <!-- END HEADER -->

        <!-- BEGIN SAMPLE TABLE PORTLET-->
        <div class="content-body portlet">
            <div class="portlet-body">
                <!-- BEGIN SAMPLE TABLE -->
                <!-- BEGIN SECTION -->
                <section class="scrollable wrapper w-f">
                
				    <#list transportationExpensesTemplates as transportationExpensesTemplate>
				    	<!-- Begin section -->
				    	<section class="panel panel-default ">
							<header class="panel-heading">
								<span class="m-r">${(transportationExpensesTemplate["tete_name"])!}</span><span class="pull-right"> 
									   <span class="help-inline m-r">最后编辑时间：${(transportationExpensesTemplate["tete_modify_time"])!}</span>
									 <a href="javascript:;" url="admin/logistics/transportation/copy/${(transportationExpensesTemplate["tete_id"])!}" confirm="确定复制？" class="op-btn bootbox-confirm" title="复制">
										<i class="icon-copy text-muted"></i>
									</a>
									<a href="javascript:;" url="admin/logistics/transportation/edit/${(transportationExpensesTemplate["tete_id"])!}" class="ajaxify" title="编辑">
										<i class="icon-edit text-muted"></i>
									</a>
									<a href="javascript:;" url="admin/logistics/transportation/delete/${(transportationExpensesTemplate["tete_id"])!}" confirm="确定删除？" class="op-btn bootbox-confirm" title="删除">
										<i class="icon-trash text-muted"></i>
									</a>
								</span>
							</header>
							<div class="table-responsive">
								<table class="table   m-b-none">
									<thead>
										<tr>
										   <th class="col-sm-1 mn130">运送方式</th>
											<th class="col-sm-3">运送到</th>
											<#if transportationExpensesTemplate["tete_pricing_manner"]?? && transportationExpensesTemplate["tete_pricing_manner"] == 0>
												<th>首件(个)</th>
												<th>运费(元)</th>
												<th>续件(个)</th>
												<th>运费(元)</th>
											<#elseif transportationExpensesTemplate["tete_pricing_manner"]?? && transportationExpensesTemplate["tete_pricing_manner"] == 1>
												<th>首重(kg)</th>
												<th>运费(元)</th>
												<th>续重(kg)</th>
												<th>运费(元)</th>
											<#elseif transportationExpensesTemplate["tete_pricing_manner"]?? && transportationExpensesTemplate["tete_pricing_manner"] == 2>
												<th>首体积(m³)</th>
												<th>运费(元)</th>
												<th>续体积(m³)</th>
												<th>运费(元)</th>
											</#if>
										</tr>
									</thead>
									<tbody>
										<#list transportationExpensess as transportationExpenses>
											<#if transportationExpenses.teteId == transportationExpensesTemplate["tete_id"]>
												<tr>
													<td>
														<#if (transportationExpenses.trexShippingMethod)?? && (transportationExpenses.trexShippingMethod)==0>
															快递
														<#elseif (transportationExpenses.trexShippingMethod)?? && (transportationExpenses.trexShippingMethod)==1>
															EMS
														<#elseif (transportationExpenses.trexShippingMethod)?? && (transportationExpenses.trexShippingMethod)==2>
															平邮
														</#if>
													</td>
													<td>
														<#if (transportationExpenses.distributionRegions)??>
															<#assign i=0>
															<#list transportationExpenses.distributionRegions as distributionRegion>
																<#if (distributionRegion.regionalisms)??>
																	<#list distributionRegion.regionalisms as regionalism>
																		<#if i!=0>,</#if>
																		${regionalism.regiName}
																	</#list>
																</#if>
																<#assign i=i+1>
															</#list>
														<#else>
															全国
														</#if>
													</td>
													<td>${(transportationExpenses.trexStart)!}</td>
													<td>${(transportationExpenses.trexStartPrice)!}</td>
													<td>${(transportationExpenses.trexPlus)!}</td>
													<td>${(transportationExpenses.trexPlusPrice)!}</td>
												</tr>
											</#if>
								   		</#list>
									</tbody>
								</table>
							</div>
						</section>
				    	<!-- End section -->
				    </#list>
				</section>
				<!-- END SECTION -->
				<!-- END SAMPLE TABLE PORTLET-->
			</div>
		</div>
	</div>
</div>
<!--test-->

