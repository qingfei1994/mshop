<!-- BEGIN JAVASCRIPTS -->
<style>
	.icon-ok{
		    color: #468847;
	}
	.icon-remove{
		color: #b94a48;
	}
</style>
<script type="text/javascript">
    require(["modules/mdtable/init", 'modules/form/init','distributor/qrcode/selectshop/init'], function(Table, Form, Clickshop){
        $(document).ready(function(){
        	Table.init();
            Clickshop.init();
        });
    });
</script>
<!-- END JAVASCRIPTS -->
<div class="modal-dialog">
    <div class="modal-content">
	    <div class="form-horizontal" role="form">
	        <div class="form-body">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
                    <h4 class="modal-title">选择商品</h4>
                </div>
                
                <div class="modal-body">
                	<form class="query-form ajax-form">
                	
	                	<div class="form-group">
	                		<div class="col-md-5">
		                		<#--<select name="shopInformationType" class="form-control">
		                			<option value="">请选择商品分类</option>
		                			<#list goodsClassifications as goodsClassifications1>
		                				<#if goodsClassifications1.goodsClassifications?? && goodsClassifications1.goodsClassifications?size gt 0>
		                					<optgroup value="${goodsClassifications1.goclId}" label="${goodsClassifications1.goclName}">
		                					<#list goodsClassifications1.goodsClassifications as goodsClassifications2>
		                						<option value="${goodsClassifications2.goclId}">${goodsClassifications2.goclName}</option>
		                					</#list>
		                					</optgroup>
		                				<#else>
		                					<option value="${goodsClassifications1.goclId}">${goodsClassifications1.goclName}</option>
		                				</#if>
		                			</#list>
	                			</select>
	                			-->
	                		</div>
	                		
	                		<div class="col-md-7">
	                			<div class="form-inline">
		                   			 <input name="goodName" placeholder="商品名称" operator="like" data-type="string" class="form-control input-small" />
		                   		
		                    		 <button type="button" class="btn btn-info btn-sm reset">清空条件</button>
		                    		 <button type="button"  class="btn btn-info btn-sm query">查询</button>
	                			</div>
	                		</div>
	                	</div>
                	</form>	
                	<div class="form-gruop">
	                   	<div class="table-responsive paging-table sort-table">
							<input type="hidden" name="path" value="distributor/qrcodeManage/QRmanage/goodsInformation/list" /><!-- 分页、排序、查询用到的path -->
							<table class="table table-striped table-hover"><!-- 统一风格写法 -->
							 <thead>
							 	<tr>
							 		<th ><center>商品</center></th>
							 		<th class="col-md-5"><center></center></th>
							 		<th ><center>当前价(￥)</center></th>
							 		<th ><center>操作</center></th>
							 	</tr>
							 </thead>
							 <tbody>
							 <#--
							 <#if goods??>
							 	<#list goods as good>
							 		<tr class="gooditem">
							 			<td ><center value="${(good["gopi_url"])!}"><img src="${(good["gopi_url"])!}" style="height:60px;width:60px;"/></center></td>
										
										<td ><center value="${(good["good_name"])!}">${(good["good_name"])!}</center></td>
										
							 			<td ><center>${(good["good_price"]!)}</center></td>

							 			<td ><center><i class="icon-remove"></i></center></td>
							 		</tr>
							 	</#list>
							 </#if>
							 -->
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