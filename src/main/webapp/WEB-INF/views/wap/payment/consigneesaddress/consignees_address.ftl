<!DOCTYPE html>
<html>
<head lang="en">
    <title>收货地址</title>
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
    <link href="assets/wap/css/payment/consigneesaddress/consignees_address.css" rel="stylesheet" type="text/css"/>
</head>
<body>
	<div class="container">
		<section class="auto-height">
			<ul class="list-ul">
				<#list addressList as address>
					<li>
						<a href="wap/payment/consigneesAddress/${address["coad_id"]!}/edit" class="edit">
							<div class="li-wrap">
								<div class="box">
									<label class="control-label">姓名</label>
									<p class="control-static">${address["coad_name"]!}</p>
									<#if address["coad_default_address"] == 1>
										<p class="flag-fr">默认地址</p>
									</#if>
								</div>
								<div class="box">
									<label class="control-label">电话</label>
									<p class="control-static">${address["coad_phone"]!}</p>
								</div>
								<div class="box">
									<label class="control-label">地址</label>
									<p class="control-static">${address["province_name"]!} ${address["city_name"]!} ${address["county_name"]!} ${address["coad_detailed_address"]!}</p>
								</div>
							</div>
						</a>
					</li>
				</#list>
				<li>
					<a href="javascript:;">
						<div class="li-wrap">
							<p class="static">选用微信收货地址</p>
						</div>
					</a>
				</li>
			</ul>
		</section>
		<footer>
			<#include "../../../common/_template/wap/copyright.ftl" />
			<div style="height:60px;">
				<div class="button-wrap">
					<a href="wap/payment/consigneesAddress/edit" class="btn-add">新增收货地址</a>
			    </div>
		    </div>
		</footer>
	</div>
	<#-- 下面写需要引入的js模块，fastclick是一个解决移动端点击延迟和点透bug的插件，按照页面需要引入；如果页面不需要使用js模块，则把require.js也删掉  -->
	<#include "../../../common/_js/require_config_wap.ftl" />
	<script type="text/javascript" src="assets/requirejs/require.js"></script>
	<script type="text/javascript">
		requirejs.config(mssRequireConfig);
		require(['fastclick', "zepto"], function(FastClick){
			$(document).ready(function(){
				FastClick.attach(document.body);
				$(".edit").on("click", function(){
					localStorage.setItem("from","edit");
				});
				$(".btn-add").on("click", function(){
					localStorage.setItem("from","add");
				});
			});
		});
	</script>
</body>
</html>