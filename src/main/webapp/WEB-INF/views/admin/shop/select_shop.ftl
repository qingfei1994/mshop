<!-- BEGIN JAVASCRIPTS -->
<#include "../../common/_js/modal_table_js.ftl" />
<style>
	.icon-ok{
		    color: #468847;
	}
	.icon-remove{
		color: #b94a48;
	}
</style>
<script type="text/javascript">
    require(["modules/mdtable/init", 'admin/shop/show/edit/init'], function(ShowEdit,Table){
        $(document).ready(function(){
        	ShowEdit.init();
        	Table.init();
        });
    });
</script>
<!-- END JAVASCRIPTS -->
<div class="modal-dialog">
    <div class="modal-content">
	    <div class="form-horizontal" role="form">
	        <div class="form-body">
	        	<input type="hidden" id="selectedId" value="${(selectedId)!}">
	        	<input type="hidden" id="selectedGroupId" value="${(selectedGroupId)!}">
	        	<input type="hidden" id="ingoId" value="${(ingoId)!}">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
                    <h4 class="modal-title">选择商品</h4>
                </div>
                
                <div class="modal-body">
                <form class="query-form ajax-form  form-inline form-body">
	                	<div class="form-group">
	                		<div class="col-md-4">
	                			  <div class="form-group">
			                		<select name="goclId" operator="eq" data-type="string" class="form-control">
			                			<option value="">商品分类</option>
			                			<#include "../goods/goods_classification_options.ftl" />
		                			</select>
		                			</div>
	                			</div>
	                			<div class="col-md-8">
		                			<div class="form-group right">
			                   			 <input name="goodName" placeholder="商品名称" operator="like" data-type="string" class="form-control input-small" />
									</div>
										<div class="form-group">		                				
			                				<button type="button" class="btn btn-info btn-sm reset">清空条件</button>
				                    		 <button type="button"  class="btn btn-info btn-sm query">查询</button>
		                				</div>
		                		</div>
							</div>              			
                	</form>	
                	<div class="form-group">
	                   	<div class="table-responsive paging-table sort-table">
							<input type="hidden" name="path" value="admin/shop/show/goodsInformation/list?groupId=${(selectedGroupId)!}&ingoId=${(ingoId)!}" /><!-- 分页、排序、查询用到的path -->
							<table class="table table-striped table-hover"><!-- 统一风格写法 -->
							 <thead>
							 	<tr>
							 		<th ><center>商品</center></th>
							 		<th class="col-md-5"><center>商品名称</center></th>
							 		<th ><center>当前价(￥)</center></th>
							 		<th ><center>操作</center></th>
							 	</tr>
							 </thead>
							 <tbody>
							 <#if goods??>
							 	<#list goods as good>
							 		<tr class="gooditem">
							 			<td ><center value="${(good["gopiUrl"])!}"><img src="${(good["gopiUrl"])!}" style="height:60px;width:60px;"/></center></td>
										
										<td ><span data-id="${(good["goodId"])!}" style="display:none"></span><center value="${(good["goodName"])!}">${(good["goodName"])!}</center></td>
										
							 			<td ><center>${(good["goodPrice"]!)}</center></td>
										<#if selectedId?? && selectedId == good["goodId"]>
											<td ><center><i class="icon-ok"></i></center></td>
										<#else>
											<td ><center><i class="icon-remove"></i></center></td>
										</#if>
							 			
							 		</tr>
							 	</#list>
							 </#if>
							 </tbody>
							 </table>
						</div>
					</div>
                </div>
                <div class="modal-footer">
                    <button type="button" data-dismiss="modal" class="btn btn-default">取消</button>
                    <button type="button" data-dismiss="modal" class="btn btn-success mybutton">保存</button>

                </div>
            </div>
    	</div>
    </div>
</div>