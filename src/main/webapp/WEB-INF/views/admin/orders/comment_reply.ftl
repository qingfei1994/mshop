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
                    <h4 class="modal-title">回复评论</h4>
                </div>
                <div class="modal-body">
                	<input type="hidden" name="gocoId" value="${gocoId!}" />
                    <div class="col-md-12">
                        <div class="form-group">
                            <div class="col-md-12">
                                <textarea class="form-control" rows="3" name="gocoReplyContent" required="true" value="">${(goodsComment.gocoReplyContent)!}</textarea>
                            	<div class="help-block">回复成功后评论自动显示</div>
                            </div>
                        </div>
                     </div>
                </div>
                <div class="modal-footer">
                    <button type="button" data-dismiss="modal" class="btn btn-default">取消</button>
                    <button url="admin/order/comment/reply"
                    	type="button" class="btn btn-success ajax-submit-modal">确定</button>

                </div>
            </div>
    	</form>
    </div>
</div>
