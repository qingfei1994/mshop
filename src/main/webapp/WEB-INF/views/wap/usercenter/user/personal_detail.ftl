<!DOCTYPE html>
<html>
<head lang="en">
    <title>个人信息</title>
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
    <link href="assets/wap/css/usercenter/user_center.css" rel="stylesheet" type="text/css"/>
</head>
<body>
	<div class="container">
		<section class="user-modify">
			<div>
				<ul class="list-ul">
	                <div>
	                    <li>
	                        <a href="javascript:;" id="memberHead">
								 头像
								 <div class="img-wrap">
								 	<img src="${wechat.weinHeadingimgurl!}" />
								 </div>
	                        </a>
	                    </li>
	                    <li>
	                        <a href="javascript:;" id="memberNickname">
								昵称<label id="nickname">${wechat.weinNickname!}</label>
	                        </a>
	                    </li>
	                </div>
	                <div>
	                    <li>
	                        <a href="wap/payment/consigneesAddress">
								我的收货地址<label>${addressCount!"0"}</label>
	                        </a>
	                    </li>
			        </div>
			    </ul>
			</div>
		</section>
			
		<section class="list-ul-modify-nickname">
			<label class="control-label">修改昵称：</label>
			<ul class="list-ul">
				<div>
					<li>
						<input type="text" value="${wechat.weinNickname!}" id="inputNickname" />
						<span class="clear"></span>
					</li>
				</div>
			</ul>
			<a class="btn-save">保存</a>
			<a class="btn-cancel btn white">取消</a>
		</section>
		<footer class="copyright-fix">
			<#include "../../../common/_template/wap/copyright.ftl" />
		</footer>
	
	</div>
	<#include "../../../common/_js/require_config_wap.ftl" />
	<script type="text/javascript" src="assets/requirejs/require.js"></script>
	<script type="text/javascript">
		requirejs.config(mssRequireConfig);
		require(['fastclick','app'], function(FastClick){
			$(document).ready(function(){
				FastClick.attach(document.body);
				$("#memberNickname").on("click", function(){
					$(".user-modify").hide();
					$(".list-ul-modify-nickname").show();
					$("#inputNickname").val($("#nickname").text());
				});
				$(".clear").on("click", function(){
					$("#inputNickname").val("");
				});
				$(".btn-save").on("click", function(){
					App.showLoading();
					$.post("wap/usercenter/nickname/save", {nickname: $("#inputNickname").val()}, function(res){
						if(res == "success") {
							App.removeLoading();
							$(".list-ul-modify-nickname").hide();
							$(".user-modify").show();
							$("#nickname").text($("#inputNickname").val());
						} else {
							App.removeLoading();
							alert("保存失败");
						}
					}, "json");
				});
				$(".btn-cancel").on("click", function(){
					$(".list-ul-modify-nickname").hide();
					$(".user-modify").show();
				});
			});
		});
	</script>
</body>
</html>