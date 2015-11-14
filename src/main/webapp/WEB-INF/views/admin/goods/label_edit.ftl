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
                    <h4 class="modal-title">添加标签</h4>
                </div>
                <div class="modal-body">
                    <div class="col-md-12">
                        <div class="form-group">
                            <label  class="col-md-3 control-label">标签名称<span class="required">*</span></label>
                            <div class="col-md-6">
                                <input type="text" class="form-control" name="golaName" required="true">
                            </div>
                        </div>
                        <div class="form-group">
                            <label  class="col-md-3 control-label">排序<span class="required">*</span></label>
                            <div class="col-md-6">
                                <input type="text" class="form-control"  name="golaSort"  required="true" digits="true">
                            </div>
                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" data-dismiss="modal" class="btn btn-default">取消</button>
                    <button url="admin/goods/label/add"
                    	type="button" class="btn btn-success ajax-submit-modal">确定</button>

                </div>
            </div>
    	</form>
    </div>
</div>
