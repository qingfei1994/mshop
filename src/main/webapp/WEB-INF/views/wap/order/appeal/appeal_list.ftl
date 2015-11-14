<!DOCTYPE html>
<html>
<head lang="en">
    <title>维权订单</title>
    <base href="${base}/" />
    <meta charset="utf-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta content="width=device-width, initial-scale=1.0, maximum-scale=1.0,  user-scalable=no" name="viewport" />
    <meta content="yes" name="apple-mobile-web-app-capable" />
    <meta content="black" name=" apple-mobile-web-app-status-bar-style" />
    <meta content="telephone=no" name="format-detection" />
    <meta http-equiv="Cache-Control" content="no-cache" />
      <link href="assets/wap/gmu/css/widget/dialog/dialog.css" rel="stylesheet" type="text/css"/>
    <link href="assets/wap/gmu/css/widget/dialog/dialog.iOS7.css" rel="stylesheet" type="text/css"/>
    <link href="assets/wap/css/common/reset.css" rel="stylesheet" type="text/css"/>
    <link href="assets/wap/css/common/common.css" rel="stylesheet" type="text/css"/>
    <link href="assets/wap/css/order/appeal/appeal_list.css" rel="stylesheet" type="text/css"/>
    <link href="assets/wap/gmu/css/widget/gotop/gotop.css" rel="stylesheet" type="text/css"/>
</head>
<body>
	<div class="container myorder">
		<section class="body auto-height">
            <div id="listOrder" class="list-order">  
	            <div class="orderlist">
					<#list goodsOrders as goodsOrder>
                        <span>申请时间：${goodsOrder.clientAppeal.clapApplicationTime}<#if goodsOrder.clientAppeal.clapFlowStatus==0><a href="javascript:closeClientAppeal(${goodsOrder.clientAppeal.clapId});">撤销</a></#if></span>
                        <ul>
                            <li>
                                <a href="wap/order/appeal/${goodsOrder.goorId}/detail">
                                    <span><img src="${goodsOrder.goorFirstPicture}"></span>
                                    <label>
									<span class="table">
										<div class="line1">
                                            <span>${goodsOrder.goorName}</span>
                                        </div>
										<div class="line2">
                                            <span>￥${goodsOrder.goorPrice}</span>
                                        </div>
										<div class="line3">
                                            <span>数量${goodsOrder.goorCount}</span>
                                        </div>
									</span>
                                    </label>
                                </a>
                            </li>
                        </ul>
                        <label>
                        <span class="order-state fl">
                        	<#if goodsOrder.clientAppeal.clapFlowStatus==0> 
                        			等待商家处理
                        			<#elseif goodsOrder.clientAppeal.clapFlowStatus==1> 
                        			您的申请被拒绝		
                        			<#elseif goodsOrder.clientAppeal.clapFlowStatus==2> 
                        			商家同意退款
                        			<#elseif goodsOrder.clientAppeal.clapFlowStatus==3> 
                        			您已撤销申请				
                        	</#if>
                        </span></label>
					</#list>
				</div>
			</div>
        </section>
		<footer>
			<#include "../../../common/_template/wap/copyright.ftl" />
		</footer>
	</div>
	<div id="gotop"></div>
	<#-- 下面写需要引入的js模块，fastclick是一个解决移动端点击延迟和点透bug的插件，按照页面需要引入；如果页面不需要使用js模块，则把require.js也删掉  -->
	<#include "../../../common/_js/require_config_wap.ftl" />
	<script type="text/javascript" src="assets/requirejs/require.js"></script>
	<script type="text/javascript">
		requirejs.config(mssRequireConfig);
		require(['fastclick', 'gotop','app'], function(FastClick){
			FastClick.attach(document.body);
			$("#gotop").gotop();
			});
		
		var closeClientAppeal = function(clapId){
					App.confirm('确定要撤销退款吗？', function() {
						App.post("wap/order/appeal/close",{clapId:clapId},function(res){
							if(res.success==0) {
								App.alert("撤销失败");
							} else if(res.success=1) {
								App.alert("撤销成功");
								window.location.href='${base}/wap/order/appeal/list';
							}
						});
					});
		}
	</script>
</body>
</html>