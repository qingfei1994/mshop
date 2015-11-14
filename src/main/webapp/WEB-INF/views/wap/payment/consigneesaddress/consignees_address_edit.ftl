<!DOCTYPE html>
<html>
<head lang="en">
    <title>编辑收货地址</title>
    <base href="${base}/" />
    <meta charset="utf-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta content="width=device-width, initial-scale=1.0, maximum-scale=1.0,  user-scalable=no" name="viewport" />
    <meta content="yes" name="apple-mobile-web-app-capable" />
    <meta content="black" name=" apple-mobile-web-app-status-bar-style" />
    <meta content="telephone=no" name="format-detection" />
    <meta http-equiv="Cache-Control" content="no-cache" />
    <link href="assets/wap/css/common/reset.css" rel="stylesheet" type="text/css"/>
    <link href="assets/wap/css/common/common.css" rel="stylesheet" type="text/css"/>
    <link href="assets/wap/css/payment/consigneesaddress/consignees_address_edit.css" rel="stylesheet" type="text/css"/>
    <link href="assets/wap/gmu/css/widget/dialog/dialog.css" rel="stylesheet" type="text/css"/>
    <link href="assets/wap/gmu/css/widget/dialog/dialog.iOS7.css" rel="stylesheet" type="text/css"/>
    <script>
    	var Args = {
    		urls: {
    			saveAddr: "wap/payment/consigneesAddress/save",
    			addAddr: "wap/payment/consigneesAddress/add",
    			deleteAddr: "wap/payment/consigneesAddress/${(consigneesAddress.coadId)!}/delete",
    			addrList: "wap/payment/consigneesAddress"
    		}
    	}
    </script>
</head>
<body>
	<div class="container my-addressEdit">
		<section class="body auto-height">
			<div class="div-section">
				<form id="formListAddress" action="javascript:;" method="post">
					<ul id="listAddressEdit" class="list-address-edit">
					    <input type="hidden" name="coadId" value="${(consigneesAddress.coadId)!}">
						<li>
							<div>
								<input type="text" id="coadName" name="coadName" value="${(consigneesAddress.coadName)!}" placeholder="姓名" maxlength="50">
							</div>
						</li>
						<li>
							<div>
								<input type="tel" id="coadPhone" name="coadPhone" value="${(consigneesAddress.coadPhone)!}" placeholder="电话" maxlength="11">
							</div>
						</li>
						<li>
							<div class="address-select">
								<select class="regionalism regionalism-first" placeholder="请选择省份">
									<option value="">请选择省份</option>
								</select>
                                <input type="hidden" id="provinceId" value="${provinceId!}" >
							</div>
						</li>
						<li>
							<div class="address-select">
								<select class="regionalism" placeholder="请选择城市" required="true">
									<option value="">请选择城市</option>
								</select>
                                <input type="hidden" id="cityId" value="${cityId!}" >
							</div>
						</li>
						<li>
							<div class="address-select">
								<select id="regiId" class="regionalism" name="regiId" placeholder="请选择区县" required="true">
									<option value="">请选择区县</option>
								</select>
                                <input type="hidden" id="countyId" value="${countyId!}" >
							</div>
						</li>
						<li>
							<div>
								<input type="text" id="coadDetailedAddress" name="coadDetailedAddress" value="${(consigneesAddress.coadDetailedAddress)!}" placeholder="详细地址" maxlength="50">
							</div>
						</li>
						<ol>
							<div class="div-set-default">
								<label>设为默认<input type="checkbox" class="radio" name="coadDefaultAddress" value="1" <#if consigneesAddress?? && consigneesAddress.coadDefaultAddress == 1>checked</#if>></label>
							</div>
						</ol>
					</ul>
				</form>
			</div>
			<div class="div-section-btn">
				<ul>
					<li>
						<a href="javascript:;" class="btn red" id="saveAddress">保存</a>
					</li>
					<li>
						<a href="javascript:;" class="btn white" id="cancelAddress">
							<script>
								var from = localStorage.getItem("from");
							    document.write(from == "edit" ? "删除" : "取消");
							</script>
						</a>
					</li>
				</ul>
			</div>
		</section>
		<footer>
	        <#include "../../../common/_template/wap/copyright.ftl" />
		</footer>
	</div>
	
	<script type="text/javascript" src="./编辑收货地址_files/jweixin-1.0.0.js"></script>
	<script type="text/javascript" src="./编辑收货地址_files/WXShare.js"></script>
	<#-- 下面写需要引入的js模块，fastclick是一个解决移动端点击延迟和点透bug的插件，按照页面需要引入；如果页面不需要使用js模块，则把require.js也删掉  -->
	<#include "../../../common/_js/require_config_wap.ftl" />
	<script type="text/javascript" src="assets/requirejs/require.js"></script>
	<script type="text/javascript">
		requirejs.config(mssRequireConfig);
		require(['fastclick', "app"], function(FastClick){
			$(document).ready(function(){
				FastClick.attach(document.body);
				//handle regionalism
			    //misuosi:add by YLM
				(function(){
					$('.regionalism').on('change', function(){
						$next = $(this).parent().parent().next().children().children('.regionalism');
						$nextNext = $(this).parent().parent().next().next().children().children('.regionalism');
						if($next.length > 0) {
							$next.empty().append('<option value="">'+ $next.attr('placeholder') +'</option>');
						}
						if($nextNext.length > 0) {
							$nextNext.empty().append('<option value="">'+ $nextNext.attr('placeholder') +'</option>');
						}
						
						var regiId = $(this).val();
						if($next.length > 0 && regiId){
							$.ajax({
								url: "common/regionalism/getRegionalismList",
								type: "POST",
								cache: false,
								data: {"regiId": regiId},
								dataType: "json",
								success: function(data){
									
									if(typeof(data) == "undefined"){
										return;
									}
									
									var dataLength = data.length;
									for(var i=0; i<dataLength; i++){
										var html = '<option value="'+ data[i].regiId +'">'+ data[i].regiName +'</option>';
										$next.append(html);
									}
								},
								error: function(res){
									alert("网络错误");
								}
							});
						}
					});	
				}());
				
				//init regionalism
			    //misuosi:add by YLM
				(function(){
					var regionalismSelect = $('select.regionalism-first');
					var provinceId = $('#provinceId').val();
					$.ajax({
						url: "common/regionalism/initRegionalismList",
						type: "POST",
						cache: false,
						dataType: "json",
						success: function(data){
							regionalismSelect.empty();
							var html = '<option value="">'+ regionalismSelect.attr('placeholder') +'</option>';
							regionalismSelect.append(html);
							
							if(typeof(data) == "undefined"){
								return;
							}
							
							var dataLength = data.length;
							for(var i=0; i<dataLength; i++){
								var html = '<option value="'+ data[i].regiId +'">'+ data[i].regiName +'</option>';
								regionalismSelect.append(html);
							}
							
							if(provinceId){
								$('option[value="'+provinceId+'"]').attr('selected', true);
								$next = regionalismSelect.parent().parent().next().children().children('.regionalism');
								$.post("common/regionalism/getRegionalismList", {"regiId": provinceId}, function(data){
									if(typeof(data) == "undefined"){
										return;
									}
									
									var dataLength = data.length;
									for(var i=0; i<dataLength; i++){
										var html = '<option value="'+ data[i].regiId +'">'+ data[i].regiName +'</option>';
										$next.append(html);
									}
									
									var cityId = $('#cityId').val();
									if(cityId) {
										$('option[value="'+cityId+'"]').attr('selected', true);
										$nextNext = $next.parent().parent().next().children().children('.regionalism');
										$.post("common/regionalism/getRegionalismList", {"regiId": cityId}, function(data){
											if(typeof(data) == "undefined"){
												return;
											}
											
											var dataLength = data.length;
											for(var i=0; i<dataLength; i++){
												var html = '<option value="'+ data[i].regiId +'">'+ data[i].regiName +'</option>';
												$nextNext.append(html);
											}
									
											var countyId = $('#countyId').val();
											if(countyId) {
												$('option[value="'+countyId+'"]').attr('selected', true);
											}
										}, "json");
									}
								}, "json");
							}
							
						},
						error: function(res){
							alert("网络错误");
						}
					});
				}());
				
				var formAction = from == "edit" ? Args.urls.saveAddr : Args.urls.addAddr;
				
				$("#saveAddress").on("click", function(){
					if(!$("#coadName").val()) {
						App.alert("姓名不能为空");
					} else if(!$("#coadPhone").val()) {
						App.alert("电话不能为空");
					} else if(!$("#regiId").val()) {
						App.alert("请选择完整的地区");
					} else if(!$("#coadDetailedAddress").val()) {
						App.alert("详细地址不能为空");
					} else {
						App.showLoading();
						App.post(formAction, $("#formListAddress").serializeArray(), function(res){
							location.href = res;
						});
					}
				});
				$("#cancelAddress").on("click", function(){
					var from = localStorage.getItem("from");
					if(from == "edit") {
						App.confirm("确定要删除？", function(){
							App.showLoading();
							location.href = Args.urls.deleteAddr;
						});
					} else {
						location.href = Args.urls.addrList;
					}
				});
			});
		});
	</script>
</body>
</html>