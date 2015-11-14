
<script>
	
	require(["ueditor","modules/form/init"], function(ueditor,Form){
    $(document).ready(function(){
		ueditor.init();
		Form.init();
        App.initPlugins();
		
    });
});
</script>

 <div class="row">
	<div class="col-md-12">
		<!-- BEGIN HEADER -->
        <div class="content-header">
            <div class="form-inline form-body">
                <div class="form-left">
                    <div class="form-group">
                        <div class="title">
                            <i class="icon-list-alt"></i> 订单设置
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- END HEADER -->

        <!-- BEGIN BODY -->
		<div class="content-body portlet">
			<div class="portlet-body form">
				<!-- BEGIN FORM-->
				<form action="#" class="form-horizontal ajax-form valid " novalidate="novalidate">
					<#if orderSetting??>
						<input type="hidden" name="orseId" value="${orderSetting.orseId!}">
						<input type="hidden" name="suppId" value="${orderSetting.suppId!}">
					</#if>
						<div class="form-body">
					   <!--START开启订单自动关闭功能-->
					   <div class="form-group">
							<div class="col-sm-3 col-sm-offset-1">
								<label class="checkbox-inline">
									<#if (orderSetting.orseAutomateClose)??>
											<input type="checkbox" id="automateclose" name="orseAutomateClose" value="1" class="js_checkbox_flag" checked/>开启订单自动关闭功能
										<#else>
											<input type="checkbox" id="automateclose" name="orseAutomateClose" value="1" class="js_checkbox_flag" unchecked/>开启订单自动关闭功能
									  </#if>
								</label>
							</div>
							<label class="col-sm-2 control-label">在线支付未支付订单，</label>
							<div class="col-sm-2">
									<#if (orderSetting.orseAutomateClose)??>
										<input id="automatecloseinput" type="text"  class="form-control" data-rule-range="[1,72]" data-rule-positiveinteger="true" data-rule-required="true" name="orsePayDeadline" value="${orderSetting.orsePayDeadline!}" aria-required="true">
										<#else>
											<input id="automatecloseinput" type="text"  disabled class="form-control" data-rule-range="[1,72]" data-rule-positiveinteger="true" data-rule-required="true" name="orsePayDeadline" value="0" aria-required="true">
									</#if>

							</div>
							<div class="col-sm-2">
								<p class="form-control-static">小时后自动关闭</p>
							</div>
					   </div>
					   <!--END开启订单自动关闭功能-->
					   <h4 class="form-section"></h4>

					   <!--START开启订单自动确认收货功能-->
					   <div class="form-group">
							<div class="col-sm-3 col-sm-offset-1">
								<label class="checkbox-inline">
									<#if orderSetting??>
										<input type="checkbox" name="orseAutomateAffirm" value="${orderSetting.orseAutomateAffirm!}" class="js_checkbox_flag" checked="checked" disabled="disabled">开启订单自动确认收货功能</label>
										<#else>
											<input type="checkbox" name="orseAutomateAffirm" value="" class="js_checkbox_flag" checked="checked" disabled="disabled">开启订单自动确认收货功能</label>
									</#if>
							</div>
							<label class="col-sm-2 control-label">已发货订单，</label>
							<div class="col-sm-2">
								<#if orderSetting??>
									<input type="text" class="form-control" data-rule-range="[1,30]" data-rule-positiveinteger="true" data-rule-required="true" name="orseAutomateAffirmDeadline" value="${orderSetting.orseAutomateAffirmDeadline!}" aria-required="true">
									<#else>
										<input type="text" class="form-control" data-rule-range="[1,30]" data-rule-positiveinteger="true" data-rule-required="true" name="orseAutomateAffirmDeadline" value="" aria-required="true">
								</#if>
							</div>
							<div class="col-sm-4">
								<p class="form-control-static">天后自动确认收货<span class="help-inline m-l">请考虑物流运输时间</span></p>
							</div>
						</div>
						<!--END开启订单自动确认收货功能-->

					   <h4 class="form-section"></h4>
					   <!--START开启退款退货功能-->
					   <div class="form-group">

							<div class="col-sm-3 col-sm-offset-1">
								<label class="checkbox-inline">
									<#if (orderSetting.orseRefund)??>
										<input type="checkbox" id="refundclose" name="orseRefund" value="1" class="js_checkbox_flag" checked="checked"/>开启退款/退货功能
										<#else>
											<input type="checkbox" id="refundclose" name="orseRefund" value="1" class="js_checkbox_flag" unchecked>开启退款/退货功能
									</#if>
								</label>
							</div>
							<label class="col-sm-2 control-label">已收货订单，</label>
							<div class="col-sm-2">
								<#if (orderSetting.orseRefund)??>
									<input type="text" id="refundinput" class="form-control" value="${orderSetting.orseRefundDeadline!}" data-rule-range="[1,30]" data-rule-positiveinteger="true" data-rule-required="true" name="orseRefundDeadline" aria-required="true">
									<#else>
										<input type="text" id="refundinput" disabled class="form-control" value="0" data-rule-range="[1,30]" data-rule-positiveinteger="true" data-rule-required="true" name="orseRefundDeadline" aria-required="true">
								</#if>
							</div>
							<div class="col-sm-2">
								<p class="form-control-static">天后关闭退款/退货功能</p>
							</div>
					   </div>
					   <!--END开启退款退货功能-->

					   <h4 class="form-section"></h4>
							<!--START开启默认好评功能-->
							<!--ID的传值-->
							<#if orderSetting??>
								<input type="hidden" name="orseId" value="${orderSetting.orseId!}">
								<#else>
								<input type="hidden" name="orseId" value="">
							</#if>
							<div class="form-group">
								<div class="col-sm-3 col-sm-offset-1">
								  <label class="checkbox-inline">
										<#if (orderSetting.orseDefaultPraises)??>
											<input id="praiseclose" type="checkbox" name="orseDefaultPraises" value="1" class="js_checkbox_flag js_show" checked="checked"/>开启默认好评功能
											<#else>
												<input id="praiseclose" type="checkbox" name="orseDefaultPraises" value="1" class="js_checkbox_flag js_show" unchecked/>开启默认好评功能
										</#if>
								  </label>
								</div>
								 <label class="col-sm-2 control-label">已收货订单，</label>
								 <div class="col-sm-2">
										<#if (orderSetting.orseDefaultPraises)??>
											<input id="praise" type="text" class="form-control" value="${orderSetting.orseAutomatePraisesDeadline!}" data-rule-positiveinteger="true" name="orseAutomatePraisesDeadline" data-rule-range="[1,30]" data-rule-required="true" aria-required="true">
											<#else>
												<input id="praise" type="text" class="form-control" disabled value="0" data-rule-positiveinteger="true" name="orseAutomatePraisesDeadline" data-rule-range="[1,30]" data-rule-required="true" aria-required="true">
										</#if>
								</div>
								<div class="col-sm-2">
								   <p class="form-control-static">天后自动好评</p>
								</div>
							</div>
							<!--END开启默认好评功能-->

							<!--START填写好评内容-->
								<#if (orderSetting.orseDefaultPraises)??>
									<div  id="contentDiv" class="form-group ishide" >
										 <div class="col-sm-3 col-sm-offset-1"></div>
										 <label class="col-sm-2 control-label">
																					 自动好评内容 <br>
										 <span style="margin-right:7px;color:#999">(最多200字)</span>
										 </label>
										 <div class="col-sm-4">
											<textarea class="form-control valid" rows="5" value="${orderSetting.orsePraisesContent!}" name="orsePraisesContent" data-rule-required="true" data-rule-rangelength="[1,200]" data-msg-rangelength="最多200字" aria-required="true" aria-invalid="false">${orderSetting.orsePraisesContent!}</textarea><span for="commentContent" class="help-block error valid"></span>
										 </div>
									</div>
								  <#else>
										<div  id="contentDiv" class="form-group ishide " style="display:none">
											 <div class="col-sm-3 col-sm-offset-1"></div>
											 <label class="col-sm-2 control-label">
																						 自动好评内容 <br>
											 <span style="margin-right:7px;color:#999">(最多200字)</span>
											 </label>
											 <div class="col-sm-4">
												<textarea class="form-control valid" rows="5" value="" name="orsePraisesContent" data-rule-required="true" data-rule-rangelength="[1,200]" data-msg-rangelength="最多200字" aria-required="true" aria-invalid="false"></textarea><span for="commentContent" class="help-block error valid"></span>
											 </div>
										</div>
								</#if>

							<!--END填写好评内容-->

							<h4 class="form-section"></h4>
						</div>
				</form>
			</div>
		</div>
        <!-- END BODY -->

        <!-- BEGIN FOOTER -->
        <div class="content-footer">
            <div class="center-button">
                <button type="submit" class="btn btn-success ajax-submit" url="admin/orders/setting/save">保存</button>
                <button type="button" class="btn btn-default" data-toggle="back">取消</button>
            </div>
        </div>
        <!-- END FOOTER -->
    </div>
</div>
<script>
  $(document).ready(function(){
  
  		$("#automateclose").click(function(){
		    if($(this).is(':checked')){
		    	 $("#automatecloseinput").attr("disabled",false);
		    }else{
		    	$("#automatecloseinput").attr("disabled","disabled");
		    }
		 });
	    
	    $("#refundclose").click(function(){
		    if($(this).is(':checked')){
		    	 $("#refundinput").attr("disabled",false);
		    }else{
		    	$("#refundinput").attr("disabled","disabled");
		    }
		 });
	    
  		$("#praiseclose").click(function(){
			if($(this).is(':checked')){
				 $("#praise").attr("disabled",false);
			     $("#contentDiv").show();
			}else{
				$("#praise").attr("disabled","disabled");
			    $("#contentDiv").hide();
			}
		});
  });
	
	    	
</script>
						   