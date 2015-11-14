<!DOCTYPE html>
<html>
<head>
    <title>支付订单--嘉宝商城</title>
    <base href="${base}/" />
    <meta charset="utf-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta content="width=device-width, initial-scale=1.0, maximum-scale=1.0,  user-scalable=no" name="viewport" />
    <meta content="yes" name="apple-mobile-web-app-capable" />
    <meta content="black" name=" apple-mobile-web-app-status-bar-style" />
    <meta content="telephone=no" name="format-detection" />
    <meta http-equiv="Cache-Control" content="no-cache" />
	<style type="text/css">
	html, body{
		height:100%;
		width:100%;
	}
	*{ margin:0px; padding:0px;-webkit-box-sizing:border-box;}
	.body { text-align:center; width:100%; padding:60px 20px; }
	.body .ordernum{ font-size:14px; line-height:30px;}
	.body .money{ font-size:20px; font-weight:bold; line-height:60px;}
	.body .time{font-size:16px; font-weight:bold; line-height:30px;}
	.body .btn{ display:block;background:#25a52e; text-decoration:none; border-radius:2px; color:#fff; height:44px; line-height:44px; font-size:18px; margin-top:20px;}

	.dialog{
		position: absolute;
		width: 100%;
		height: 100%;
		left: 0;
		top: 0;
		display:none;
	}
	.dialog.on{
		display:block;
	}
	.dialog .dialog_mask{
		position: absolute;
		left:0;
		top:0;
		width:100%;
		height:100%;
		z-index: 1000;
		background:rgba(0,0,0,0.3);
	}
	.dialog .dialog_body{
		position: absolute;
		left:50%;
		top:50%;
		width:180px;
		z-index: 1100;
		background:#ffffff;
		border-radius: 3px;
		margin:-50px -90px;
		padding:15px 10px 45px;
		font-size:12px;
		color:#333333;
		text-align: center;
	}
	.dialog .dialog_body p{
		display: table-cell;
		width: 160px;
		vertical-align: middle;
	}

	.dialog .dialog_body .dialog_loading{
		display:block;
		height: 0px;
	}
	.dialog .dialog_body .dialog_loading span{
		position: absolute;
		left: 50%;
		bottom: 10px;
		margin-left: -9px;
		z-index: 10;
		width: 18px;
		height: 18px;
		border: 2px solid #237FE5;
		border-radius: 24px;
		-webkit-animation: widget_gif 1s infinite linear;
		clip: rect(0 auto 12px 0);
	}
	@-webkit-keyframes widget_gif{0%{-webkit-transform:rotate(0deg);}100%{-webkit-transform:rotate(360deg);}}
	</style>
</head>
<body>
<section class="body">
    <div class="ordernum">订单号：${orderInformation.orinNo}</div>
    <div class="money">共计金额￥${orderInformation.orinTotal}</div>
    <div class="time">下单时间：${orderInformation.orinOrderTime}</div>
    <a href="javascript:void(0);" class="btn" id="getBrandWCPayRequest">确认支付</a>
</section>

<section id="dialog" class="dialog">
    <div class="dialog_mask">&nbsp;</div>
    <div class="dialog_body">
        <p id="dialog_content">
            正在查询支付结果...
        </p>
        <div class="dialog_loading"><span></span></div>
    </div>
</section>

<#include "../../common/_js/require_config_wap.ftl" />
<script type="text/javascript" src="assets/requirejs/require.js"></script>
<script type="text/javascript">
    requirejs.config(mssRequireConfig);
    require(['fastclick', 'zepto'], function(FastClick) {
        $(document).ready(function() {
            FastClick.attach(document.body);

            $('#getBrandWCPayRequest').on('click', function() {
                callpay();
            });
        });
    });

    var jsApiCall = function() {
        WeixinJSBridge.invoke('getBrandWCPayRequest', ${jsonGbwxpr}, function(res) {
            WeixinJSBridge.log(res.err_msg);
            if (res.err_msg == "get_brand_wcpay_request:ok") {
				alert('支付成功');
				window.location.href = '${base}/wap/order/list';
            } else {
				alert('支付失败');
			}
        });
    };

    var callpay = function() {
        if (typeof WeixinJSBridge == "undefined"){
            if (document.addEventListener) {
                document.addEventListener('WeixinJSBridgeReady', jsApiCall, false);
            } else if (document.attachEvent) {
                document.attachEvent('WeixinJSBridgeReady', jsApiCall);
                document.attachEvent('onWeixinJSBridgeReady', jsApiCall);
            }
        } else {
            jsApiCall();
        }
    };
</script>
</body>
</html>