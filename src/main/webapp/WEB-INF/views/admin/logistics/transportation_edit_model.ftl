<script>
	require(['admin/logistics/init'], function(Logistics){
	    $(document).ready(function(){
	        //Logistics.init();
	    });
	});
</script>
<style>
	.region_box .cities{
		max-height: 223px;
		height: 223px;
		overflow-y: auto;
		border: 1px solid #c8d4e5;
		padding: 5px 10px;
	}
</style>
<div class="modal-dialog">
    <div class="modal-content">
	    <form class="form-horizontal ajax-form valid" role="form">
	        <div class="form-body">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
                    <h4 class="modal-title">选择地区</h4>
                </div>
                <div class="modal-body">
                  	<!-- Begin Bootbox -->
                   	<div class="bootbox-body">
                   		<div class="form-horizontal region_box">
                   			<p class="js_selected_cities">
                   			<#if selectedRegionalism??>
						    
						   		 <#list selectedRegionalism as regionalism>
						    			<span data-id="${regionalism.regiId!}">${regionalism.regiName!}</span>
						    	</#list>
						    </#if>
						     </p>
						    <input type="hidden" class="js_selected_regi_id" value="" >
						    <div class="pull-left">
						        <select size="10" class="form-control province js_province">
						        	<#list regionalisms as regionalism>
						            	<option value="${regionalism.regiId!}" data-id="${regionalism.regiId!}" data-name="${regionalism.regiName!}">${regionalism.regiName!}</option>
						            </#list>
						        </select>
						    </div>
						    <div class="cities js_cities checkbox-list">
						    	<!--
						    	<#if secondRegionlisms??>
							    	<#list secondRegionlisms as secondRegionlism>
									    <label>
									        <input type="checkbox" class="js_checkitem" data-name="${(secondRegionlism.regiName)!}" data-id="${(secondRegionlism.regiId)!}" >${(secondRegionlism.regiName)!}
									    </label>
								    </#list>
							    </#if>
							    -->
						    </div>
						</div>
					</div>
                   <!-- End Bootbox -->
                </div>
                <div class="modal-footer">
                    <button type="button" data-dismiss="modal" class="btn btn-default">取消</button>
                    <button type="button" class="btn btn-success sure-area">确定</button>
                </div>
            </div>
    	</form>
    </div>
</div>