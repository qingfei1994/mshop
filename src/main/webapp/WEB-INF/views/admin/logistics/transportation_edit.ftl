<script>
	require(["modules/form/init", 'modules/dialog/init','admin/logistics/init'],
	function(Form, Dialog, Logistics){
	    $(document).ready(function(){
	        Form.init();
	        Dialog.init();
	        Logistics.init();
	    });
	});
</script>

<div class="portlet">
  <div class="portlet-title">
	 <div class="caption"><i class="icon-reorder"></i>新增运费模版</div>
	 <div class="tools">
	 </div>
  </div>
  <div class="portlet-body form">
	 <!-- BEGIN FORM-->
	 <form class="form-horizontal form-bordered ajax-form valid">
		<div class="form-body">
			<input type="hidden" name="teteId" value="${(transportationExpensesTemplate.teteId)!}">
		   <div class="form-group">
			  <label class="control-label col-md-2">模板名称<span class="required">*</span></label>
			  <div class="col-md-4">
				 <input class="form-control" name="teteName" type="text" value="${(transportationExpensesTemplate.teteName)!}" >
				</div>
		   </div>
		   <div class="form-group">
			  <label class="control-label col-md-2">发货地址<span class="required">*</span></label>
			  <div class="col-md-4">
				<select class="form-control" name="addrId">
                 	<#list addressess as addresses>
                		<option value="${(addresses["addr_id"])!}" <#if (transportationExpensesTemplate.addrId)?? && transportationExpensesTemplate.addrId==addresses["addr_id"] >selected</#if>>
                		${(addresses["addr_contacts"])!},${(addresses["addr_phone"])!},${(addresses["province_regi_name"])!}${(addresses["city_regi_name"])!}${(addresses["county_regi_name"])!}${(addresses["addr_detailed_address"])!}</option>
                	</#list>
              	</select>
			  </div>
		   </div>
		   
		   <div class="form-group">
              <label class="col-md-2 control-label">计价方式</label>
              <div class="col-md-4">
              	<!--
                 <div class="radio-list">
                 -->
                    <label class="radio-inline">
                    	<input class="toggle" type="radio" name="tetePricingManner" data-toggle="valuation" data-valid="positiveInteger" id="number" data-unit="个" data-name="件" data-type="number"
                    	 <#if !((transportationExpensesTemplate.tetePricingManner)??) || transportationExpensesTemplate.tetePricingManner != 1 || transportationExpensesTemplate.tetePricingManner != 2>
                    	 	checked 
                    	 </#if>
                    	 value="0"> 按件数
                    </label>
                    <label class="radio-inline">
                    	<input class="toggle" type="radio" name="tetePricingManner" data-toggle="valuation" id="weight" data-unit="kg" data-name="重" data-type="weight" value="1"
                    	<#if (transportationExpensesTemplate.tetePricingManner)?? && transportationExpensesTemplate.tetePricingManner == 1>
                    		checked
                    	</#if>
                    	> 按重量
                    </label>
                    <label class="radio-inline">
                    	<input class="toggle" type="radio" name="tetePricingManner" data-toggle="valuation" id="volume" data-unit="m³" data-name="体积" data-type="volume" value="2"
                    	<#if (transportationExpensesTemplate.tetePricingManner)?? && transportationExpensesTemplate.tetePricingManner == 2>
                    		checked
                    	</#if>
                    	> 按体积
                    </label>  
                 </div>
              <!--
              </div>
              -->
           </div>
		   <div class="form-group">
			  <label class="control-label col-md-2">运送方式</label>
			  <#assign isExpress="false">
			  <#assign isEms="false">
			  <#assign isPost="false">
			  <#if transportationExpensess??>
	            <#list transportationExpensess as transportationExpenses>
	            	<#if (transportationExpenses.trexShippingMethod)?? && transportationExpenses.trexShippingMethod == 0>
	            		<#assign isExpress="true">
	            	<#elseif (transportationExpenses.trexShippingMethod)?? && transportationExpenses.trexShippingMethod == 1>
	            		<#assign isEms="true">
	            	<#elseif (transportationExpenses.trexShippingMethod)?? && transportationExpenses.trexShippingMethod == 2>
	            		<#assign isPost="true">
	            	</#if>
	            </#list>
	          </#if>
			  <div class="col-md-10">                 
				<label class="checkbox-inline" style="padding-left:0px;">
                    <input type="checkbox" value="0" name="shippingMethod" data-delivery="express" data-toggle="delivery" data-name="快递"
                    <#if isExpress == "true">
                    	checked
                    </#if>
                    >快递
                </label>
                <label class="checkbox-inline" style="padding-left:0px;">
                    <input type="checkbox" value="1" name="shippingMethod" data-delivery="ems" data-toggle="delivery" data-name="EMS"
                    <#if isEms == "true">
                    	checked
                    </#if>
                    >EMS
                </label>
                <label class="checkbox-inline" style="padding-left:0px;">
                    <input type="checkbox" value="2" name="shippingMethod" data-delivery="post" data-toggle="delivery" data-name="平邮"
                    <#if isPost == "true">
                    	checked
                    </#if>
                    >平邮
                </label>
			  </div>
		   </div>
		   <div class="form-group">
			  <label class="control-label col-md-2">运费设置</label>
			  <!-- Begin responsive table -->
			  <div class="col-md-7 show-delivery" data-toggle="delivery_list">
			  	<label><label>
			  	<#-- Start Express -->
			  	<#if isExpress == "true">
        			<div class="panel panel-default" id="delivery_item_express" data-delivery="express">
				        <header class="panel-heading">快递运费设置 </header>
				        <div class="table-responsive">
				            <table class="table table-bordered">
				                <thead>
				                    <tr>
				                        <th class="col-sm-4">配送区域
				                        </th>
				                        <#if (transportationExpensesTemplate.tetePricingManner)?? && transportationExpensesTemplate.tetePricingManner == 0>
					                        <th>首件(个)</th>
					                        <th>运费(元)</th>
					                        <th>续件(个)</th>
					                        <th>运费(元)</th>
				                        <#elseif (transportationExpensesTemplate.tetePricingManner)?? && transportationExpensesTemplate.tetePricingManner == 1>
					                        <th>首重(kg)</th>
					                        <th>运费(元)</th>
					                        <th>续重(kg)</th>
					                        <th>运费(元)</th>
				                        <#elseif (transportationExpensesTemplate.tetePricingManner)?? && transportationExpensesTemplate.tetePricingManner == 1>
				                   			<th>首体积(m³)</th>
					                        <th>运费(元)</th>
					                        <th>续体积(m³)</th>
					                        <th>运费(元)</th>
				                   		</#if>
				                    </tr>
				                </thead>
				                <tbody>
				                	<#assign index=0>
				                	<#list transportationExpensess as transportationExpenses>
				                		<#if (transportationExpenses.trexShippingMethod)?? && (transportationExpenses.trexShippingMethod)==0>
						                    <tr>
						                    	<#if (transportationExpenses.trexAllRegion)?? && transportationExpenses.trexAllRegion==1>
						                        	<td>全国默认地区<input type="hidden" name="express[${index}].area" value="all"></td>
						                        <#else>
						                        	<td>指定地区 
														<a href="javascript:;" title="编辑运送区域" url="admin/logistics/transportation/regionalism" class="modal-ajax-static js_edit_area m-r-xs" data-name="express[${index}].area"><i class="op-btn icon-edit"></i></a>
														<a href="javascript:;" title="删除运送区域" data-toggle="removeRow"><i class="icon-trash text-muted"></i></a>
														<span class="help-block  js_area_item"><span class="js_no_area">
															<#if (transportationExpenses.distributionRegions)??>
																<#assign i=0>
																<#assign regiIdstr="">
																<#assign regiNamestr="">
																<#list transportationExpenses.distributionRegions as distributionRegion>
																	<#if (distributionRegion.regionalisms)??>
																		<#list distributionRegion.regionalisms as regionalism>
																			<#if i!=0>, <#assign regiIdstr=regiIdstr+","> <#assign regiNamestr=regiNamestr+","></#if>
																			${(regionalism.regiName)!}
																			<#assign regiIdstr=regiIdstr+(regionalism.regiId)!"">
																			<#assign regiNamestr=regiNamestr+(regionalism.regiName)!"">
																		</#list>
																	</#if>
																	<#assign i=i+1>
																</#list>
																<input type="hidden" class="city-item" name="express[${index}].area" value="${regiIdstr!}">
																<input type="hidden" class="city-name-item" value="${regiNamestr!}">
															</#if>
														</span> 
														</span>
													</td>
						                        </#if>
						                        <td>
													<input type="text" value="${(transportationExpenses.trexStart)!}" class="form-control" data-rule-required="true" data-rule-positiveinteger="true" name="express[${index}].trexStart"></td>
												<td>
													<input type="text" value="${(transportationExpenses.trexStartPrice)!}" class="form-control" data-rule-required="true" data-rule-decimal="2" name="express[${index}].trexStartPrice">
												</td>
												<td>
													<input type="text" value="${(transportationExpenses.trexPlus)!}" class="form-control" data-rule-required="true" data-rule-positiveinteger="true" name="express[${index}].trexPlus">
												</td>
												<td>
													<input type="text" value="${(transportationExpenses.trexPlusPrice)!}" class="form-control" data-rule-required="true" data-rule-decimal="2" name="express[${index}].trexPlusPrice">
												</td> 
						                    </tr>
						                    <#assign index=index+1>
						                 </#if>
						            </#list> 
				                </tbody>
				            </table>
				        </div>
				        <footer class="panel-footer">
				            <a href="javascript:;" class="js_add_area"><i class="fa fa-map-marker text-muted m-r-xs"></i>指定地区城市设置运费</a>
				        </footer>
				    </div>
                </#if>
                <#-- End Express -->
                <#-- Start ems -->
			  	<#if isEms == "true">
        			<div class="panel panel-default" id="delivery_item_ems" data-delivery="ems">
				        <header class="panel-heading">EMS运费设置 </header>
				        <div class="table-responsive">
				            <table class="table table-bordered">
				                <thead>
				                    <tr>
				                        <th class="col-sm-4">配送区域
				                        </th>
				                        <#if (transportationExpensesTemplate.tetePricingManner)?? && transportationExpensesTemplate.tetePricingManner == 0>
					                        <th>首件(个)</th>
					                        <th>运费(元)</th>
					                        <th>续件(个)</th>
					                        <th>运费(元)</th>
				                        <#elseif (transportationExpensesTemplate.tetePricingManner)?? && transportationExpensesTemplate.tetePricingManner == 1>
					                        <th>首重(kg)</th>
					                        <th>运费(元)</th>
					                        <th>续重(kg)</th>
					                        <th>运费(元)</th>
				                        <#elseif (transportationExpensesTemplate.tetePricingManner)?? && transportationExpensesTemplate.tetePricingManner == 1>
				                   			<th>首体积(m³)</th>
					                        <th>运费(元)</th>
					                        <th>续体积(m³)</th>
					                        <th>运费(元)</th>
				                   		</#if>
				                    </tr>
				                </thead>
				                <tbody>
				                	<#assign index=0>
				                	<#list transportationExpensess as transportationExpenses>
				                		<#if (transportationExpenses.trexShippingMethod)?? && (transportationExpenses.trexShippingMethod)==1>
	            							<tr>
						                    	<#if (transportationExpenses.trexAllRegion)?? && transportationExpenses.trexAllRegion==1>
						                        	<td>全国默认地区<input type="hidden" name="ems[${index}].area" value="all"></td>
						                        <#else>
						                        	<td>指定地区 
														<a href="javascript:;" title="编辑运送区域" url="admin/logistics/transportation/regionalism" class="modal-ajax-static js_edit_area m-r-xs" data-name="ems[${index}].area"><i class="op-btn icon-edit"></i></a>
														<a href="javascript:;" title="删除运送区域" data-toggle="removeRow"><i class="icon-trash text-muted"></i></a>
														<span class="help-block  js_area_item"><span class="js_no_area">
															<#if (transportationExpenses.distributionRegions)??>
																<#assign i=0>
																<#assign regiIdstr="">
																<#assign regiNamestr="">
																<#list transportationExpenses.distributionRegions as distributionRegion>
																	<#if (distributionRegion.regionalisms)??>
																		<#list distributionRegion.regionalisms as regionalism>
																			<#if i!=0>, <#assign regiIdstr=regiIdstr+","> <#assign regiNamestr=regiNamestr+","></#if>
																			${(regionalism.regiName)!}
																			<#assign regiIdstr=regiIdstr+(regionalism.regiId)!"">
																			<#assign regiNamestr=regiNamestr+(regionalism.regiName)!"">
																		</#list>
																	</#if>
																	<#assign i=i+1>
																</#list>
																<input type="hidden" class="city-item" name="ems[${index}].area" value="${regiIdstr!}">
																<input type="hidden" class="city-name-item" value="${regiNamestr!}">
															</#if>
														</span> 
														</span>
													</td>
						                        </#if>
						                        <td>
													<input type="text" value="${(transportationExpenses.trexStart)!}" class="form-control" data-rule-required="true" data-rule-positiveinteger="true" name="ems[${index}].trexStart"></td>
												<td>
													<input type="text" value="${(transportationExpenses.trexStartPrice)!}" class="form-control" data-rule-required="true" data-rule-decimal="2" name="ems[${index}].trexStartPrice">
												</td>
												<td>
													<input type="text" value="${(transportationExpenses.trexPlus)!}" class="form-control" data-rule-required="true" data-rule-positiveinteger="true" name="ems[${index}].trexPlus">
												</td>
												<td>
													<input type="text" value="${(transportationExpenses.trexPlusPrice)!}" class="form-control" data-rule-required="true" data-rule-decimal="2" name="ems[${index}].trexPlusPrice">
												</td> 
						                    </tr>
						                    <#assign index=index+1>
						                 </#if>
						            </#list> 
				                </tbody>
				            </table>
				        </div>
				        <footer class="panel-footer">
				            <a href="javascript:;" class="js_add_area"><i class="fa fa-map-marker text-muted m-r-xs"></i>指定地区城市设置运费</a>
				        </footer>
				    </div>
                </#if>
                <#-- End ems -->
                <#-- Start post -->
			  	<#if isPost == "true">
        			<div class="panel panel-default" id="delivery_item_post" data-delivery="post">
				        <header class="panel-heading">平邮运费设置 </header>
				        <div class="table-responsive">
				            <table class="table table-bordered">
				                <thead>
				                    <tr>
				                        <th class="col-sm-4">配送区域
				                        </th>
				                        <#if (transportationExpensesTemplate.tetePricingManner)?? && transportationExpensesTemplate.tetePricingManner == 0>
					                        <th>首件(个)</th>
					                        <th>运费(元)</th>
					                        <th>续件(个)</th>
					                        <th>运费(元)</th>
				                        <#elseif (transportationExpensesTemplate.tetePricingManner)?? && transportationExpensesTemplate.tetePricingManner == 1>
					                        <th>首重(kg)</th>
					                        <th>运费(元)</th>
					                        <th>续重(kg)</th>
					                        <th>运费(元)</th>
				                        <#elseif (transportationExpensesTemplate.tetePricingManner)?? && transportationExpensesTemplate.tetePricingManner == 1>
				                   			<th>首体积(m³)</th>
					                        <th>运费(元)</th>
					                        <th>续体积(m³)</th>
					                        <th>运费(元)</th>
				                   		</#if>
				                    </tr>
				                </thead>
				                <tbody>
				                	<#assign index=0>
				                	<#list transportationExpensess as transportationExpenses>
				                		<#if (transportationExpenses.trexShippingMethod)?? && (transportationExpenses.trexShippingMethod)==2>
	            							<tr>
						                    	<#if (transportationExpenses.trexAllRegion)?? && transportationExpenses.trexAllRegion==1>
						                        	<td>全国默认地区<input type="hidden" name="post[${index}].area" value="all"></td>
						                        <#else>
						                        	<td>指定地区 
														<a href="javascript:;" title="编辑运送区域" url="admin/logistics/transportation/regionalism" class="modal-ajax-static js_edit_area m-r-xs" data-name="post[${index}].area"><i class="op-btn icon-edit"></i></a>
														<a href="javascript:;" title="删除运送区域" data-toggle="removeRow"><i class="icon-trash text-muted"></i></a>
														<span class="help-block  js_area_item"><span class="js_no_area">
															<#if (transportationExpenses.distributionRegions)??>
																<#assign i=0>
																<#assign regiIdstr="">
																<#assign regiNamestr="">
																<#list transportationExpenses.distributionRegions as distributionRegion>
																	<#if (distributionRegion.regionalisms)??>
																		<#list distributionRegion.regionalisms as regionalism>
																			<#if i!=0>, <#assign regiIdstr=regiIdstr+","> <#assign regiNamestr=regiNamestr+","></#if>
																			${(regionalism.regiName)!}
																			<#assign regiIdstr=regiIdstr+(regionalism.regiId)!"">
																			<#assign regiNamestr=regiNamestr+(regionalism.regiName)!"">
																		</#list>
																	</#if>
																	<#assign i=i+1>
																</#list>
																<input type="hidden" class="city-item" name="post[${index}].area" value="${regiIdstr!}">
																<input type="hidden" class="city-name-item" value="${regiNamestr!}">
															</#if>
														</span> 
														</span>
													</td>
						                        </#if>
						                        <td>
													<input type="text" value="${(transportationExpenses.trexStart)!}" class="form-control" data-rule-required="true" data-rule-positiveinteger="true" name="post[${index}].trexStart"></td>
												<td>
													<input type="text" value="${(transportationExpenses.trexStartPrice)!}" class="form-control" data-rule-required="true" data-rule-decimal="2" name="post[${index}].trexStartPrice">
												</td>
												<td>
													<input type="text" value="${(transportationExpenses.trexPlus)!}" class="form-control" data-rule-required="true" data-rule-positiveinteger="true" name="post[${index}].trexPlus">
												</td>
												<td>
													<input type="text" value="${(transportationExpenses.trexPlusPrice)!}" class="form-control" data-rule-required="true" data-rule-decimal="2" name="post[${index}].trexPlusPrice">
												</td> 
						                    </tr>
						                    <#assign index=index+1>
						                </#if>
						            </#list> 
				                </tbody>
				            </table>
				        </div>
				        <footer class="panel-footer">
				            <a href="javascript:;" class="js_add_area"><i class="fa fa-map-marker text-muted m-r-xs"></i>指定地区城市设置运费</a>
				        </footer>
				    </div>
                </#if>
                <#-- End post -->
			  </div>
			  <!-- End responsive table -->
		   </div>
		</div>
		<div class="form-actions fluid">
		   <div class="col-md-offset-2 col-md-9">
			  <button class="btn btn-success ajax-submit" url="admin/logistics/transportation/save">保存</button>
			  <button class="btn btn-default ajaxify" url="admin/logistics/transportation/list" type="button">取消</button>
		   </div>
		</div>
	 </form>
	 <!-- END FORM-->
  </div>
</div>

<script type="text/html" id="delivery_setting_tpl">
    <div class="panel panel-default" id="delivery_item_{{delivery}}" data-delivery="{{delivery}}">
        <header class="panel-heading">{{name}}运费设置 </header>
        <div class="table-responsive">
            <table class="table table-bordered">
                <thead>
                    <tr>
                        <th class="col-sm-4">配送区域
                        </th>
                        <th>首{{valuation.name}}({{valuation.unit}})</th>
                        <th>运费(元)</th>
                        <th>续{{valuation.name}}({{valuation.unit}})</th>
                        <th>运费(元)</th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td>全国默认地区<input type="hidden" name="{{delivery}}[0].area" value="all"></td>
                        {{include 'edit_fee_tpl' normal}} 
                    </tr>
                </tbody>
            </table>
        </div>
        <footer class="panel-footer">
            <a href="javascript:;" class="js_add_area"><i class="fa fa-map-marker text-muted m-r-xs"></i>指定地区城市设置运费</a>
        </footer>
    </div>
</script>

<script type="text/html" id="delivery_area_tpl">
	<tr>
		<td>指定地区 
			<a href="javascript:;" title="编辑运送区域" url="admin/logistics/transportation/regionalism" class="modal-ajax-static js_edit_area m-r-xs" data-name="{{normal.delivery}}[{{normal.index}}].area" data-delivery="{{normal.delivery}}" data-index="{{normal.index}}"><i class="op-btn icon-edit"></i></a>
			<a href="javascript:;" title="删除运送区域" data-toggle="removeRow"><i class="icon-trash text-muted"></i></a>
			<span class="help-block  js_area_item">{{#area}}
			</span>
		</td>
		{{include 'edit_fee_tpl' normal}} 
	</tr>
</script>

<script type="text/html" id="edit_fee_tpl">
	<td>
		<input type="text" class="form-control" data-rule-required="true" {{if valid=="positiveInteger"}} data-rule-positiveInteger="true"{{else}}data-rule-decimal="2"{{/if}} name="{{delivery}}[{{index}}].trexStart" /></td>
	<td>
		<input type="text" class="form-control" data-rule-required="true" data-rule-decimal="2" name="{{delivery}}[{{index}}].trexStartPrice" />
	</td>
	<td>
		<input type="text" class="form-control" data-rule-required="true" {{if valid=="positiveInteger"}} data-rule-positiveInteger="true"{{else}}data-rule-decimal="2"{{/if}} name="{{delivery}}[{{index}}].trexPlus" />
	</td>
	<td>
		<input type="text" class="form-control" data-rule-required="true" data-rule-decimal="2" name="{{delivery}}[{{index}}].trexPlusPrice" />
	</td>
</script>