<!-- BEGIN JAVASCRIPTS -->
<script type="text/javascript">
    require(['modules/form/init'], function(Form){
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
                    <h4 class="modal-title">审核维权记录</h4>
                </div>
                <div class="modal-body">
                	<input type="hidden" name="goorId" value="${goorId!}" />
	                        <div class="form-group">
                			 			<label for="name"  class="col-md-3 control-label" >审核结果<span class="required">*</span></label>
                					  <div class="col-md-9">
	                             			<div class="radio-list">
	                             				<label class="radio-inline">
                                 					<input type="radio" name="clapFlowStatus"  value="2" checked> 同意
                                	 			</label>
			                                	 <label class="radio-inline">
			                                 			<input type="radio" name="clapFlowStatus"  value="1"> 拒绝
			                                	 </label>
			                                </div>
	                             	 </div>
	                          </div>
	                        <div class="form-group">
                			 		<label for="name"  class="col-md-3 control-label" >审核原因<span class="required">*</span></label>
	                            	<div class="col-md-6">
	                                	<textarea class="form-control" rows="3" name="clapReplyContent" required="true" value=""></textarea>
	                            </div>
	                        </div>
                </div>
                <div class="modal-footer">
                    <button type="button" data-dismiss="modal" class="btn btn-default">取消</button>
                    <button url="admin/orders/appeal/check"
                    	 type="button" class="btn btn-success ajax-submit-modal">确定</button>
                </div>
            </div>
    	</form>
    </div>
</div>
