<!DOCTYPE html>
<html>
<head lang="en">
    <title>订单详情</title>
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
    <link href="assets/wap/gmu/css/widget/dialog/dialog.css" rel="stylesheet" type="text/css"/>
    <link href="assets/wap/gmu/css/widget/dialog/dialog.iOS7.css" rel="stylesheet" type="text/css"/>
    <link href="assets/wap/css/order/order_detail.css" rel="stylesheet" type="text/css"/>
</head>
<body>
	<div class="container orderdetail">
		<div class="auto-height">
			<header>
	            <div>
	                <div class="order-times"><span class="info"></span>请在 <span id="timer">2小时8分56秒</span> 内付款，超时订单将自动取消</div>
	                <div class="order-state-div">
	                    <ul class="tbox">
	                        <li>
	                            <label>订单状态：
									<#if orderInformation.orinStatus == 0>
									    交易关闭
									<#elseif orderInformation.orinStatus == 1>
										<#if orderInformation.orinPayStatus == 0>
											待支付
										<#elseif orderInformation.orinPayStatus == 1>
											<#if orderInformation.shipmentsInformation.shinShipmentsStatus == 0>
                                                已支付，待发货
											<#elseif orderInformation.shipmentsInformation.shinShipmentsStatus == 1>
												待收货
											</#if>
										</#if>
									<#elseif orderInformation.orinStatus == 2>
										交易完成
									</#if>
								</label>
	                        </li>
	                    </ul>
	                </div>
	            </div>
	        </header>
	        <section data-role="body" class="body">
	            <div class="section-container">
	                <div>
	                    <div class="section-header bg-2">
	                        <label>配送信息</label>
	                    </div>
	                    <div class="section-body">
	                        <ul class="list-addr">
	                            <li class="tbox">
	                                <div>
	                                    <label>姓名</label>
	                                </div>
	                                <div>${consigneesAddress.coadName}</div>
	                            </li>
	                            <li class="tbox">
	                                <div>
	                                    <label>电话</label>
	                                </div>
	                                <div>${consigneesAddress.coadPhone}</div>
	                            </li>
	                            <li class="tbox">
	                                <div>
	                                    <label>地址</label>
	                                </div>
	                                <div>${regionalism}${consigneesAddress.coadDetailedAddress}</div>
	                            </li>
	                        </ul>
	                        <!--物流信息TODO-->
	                    </div>
	                </div>
	            </div>
	            <div class="section-container">
	                <div>
	                    <div class="section-header bg-4">
	                        <label>购物清单</label>
	                    </div>
	                    <div class="section-body">
	                        <ul class="list-goods">
	                            <li>
									<#list orderInformation.goodsOrders as goodsOrder>
                                        <a href="wap/goods/${(goodsOrder.goodId)!0}/detail" class="tbox">
                                            <div>
	                                        <span class="img-wrap">
	                                            <img src="${goodsOrder.goorFirstPicture}">
	                                        </span>
                                            </div>
                                            <div>
                                                <p class="title">${goodsOrder.goorName}</p>
                                                <p class="price">￥${goodsOrder.goorPrice}</p>
                                                <p class="">
                                                    数量${goodsOrder.goorCount}
                                                </p>
                                            </div>
                                        </a>

                                        <div class="op-box">
											<#if orderInformation.orinStatus == 2
											&& orderInformation.orinPayStatus == 1
											&& orderInformation.shipmentsInformation.shinShipmentsStatus == 1
											&& orderInformation.shipmentsInformation.shinSignStatus == 1
											&& goodsOrder.goorRefundStatus == 1>
                                                <a href="wap/order/appeal/${goodsOrder.goorId}/apply" class="btn red-1">申请维权</a>
											</#if>
                                        </div>
									</#list>
	                            </li>
	                        </ul>
	                        <div class="sep-wrap">
	                            <div class="sep">&nbsp;</div>
	                        </div>
	                        <ul class="order-info">
	                            <li>
	                                <label>商品金额</label>
	                                <label class="right">￥${orderInformation.orinTotal - orderInformation.orinFreight}</label>
	                            </li>
	                            <li>
	                                <label>运费</label>
	                                <label class="right">￥${orderInformation.orinFreight}</label>
	                            </li>
								<#if orderInformation.orinPayStatus == 1>
                                    <ol>
                                        <li>
                                            <label>支付方式</label>
                                            <label class="right">
											<#if orderInformation.orinPayWay == 0>
                                                微信支付
											<#elseif orderInformation.orinPayWay == 1>
												支付宝
											<#elseif orderInformation.orinPayWay == 2>
												财付通
											<#elseif orderInformation.orinPayWay == 3>
												银行卡支付
											<#elseif orderInformation.orinPayWay == 4>
												货到付款
											<#elseif orderInformation.orinPayWay == 5>
												会员余额
											</#if>
											</label>
                                        </li>
                                        <li>
                                            <label>支付金额</label>
                                            <label class="right">
                                                ￥${orderInformation.orinTotal}
                                            </label>
                                        </li>
                                    </ol>
								</#if>
	                            <div class="order-info-note">
	                                <p>订单编号：${orderInformation.orinNo}</p>
	                                <p>创建时间：${orderInformation.orinCreateTime}</p>
									<#if orderInformation.orinPayStatus == 1>
                                        <p>支付时间：${(payInformation.painPayTime)!''}</p>
									</#if>
									<#if orderInformation.shipmentsInformation.shinShipmentsStatus == 1>
                                        <p>发货时间：${orderInformation.shipmentsInformation.shinShipmentsTime}</p>
									</#if>
									<#if orderInformation.shipmentsInformation.shinSignStatus == 1>
										<p>收货时间：${orderInformation.shipmentsInformation.shinSignTime}</p>
									</#if>
									<#if orderInformation.shipmentsInformation.shinSignStatus == 1>
										<p>完成时间：${orderInformation.shipmentsInformation.shinSignTime}</p>
									</#if>
	                            </div>
	                        </ul>
	                    </div>
	                </div>
	            </div>
	        </section>
		</section>
		<footer>
			<#include "../../common/_template/wap/copyright.ftl" />
			<#if (orderInformation.orinStatus == 1 && orderInformation.orinPayStatus == 0)
				|| orderInformation.orinStatus == 2>
                <div data-role="widget" data-widget="foot" class="foot-menu">
                    <div class="widget-wrapper">
                        <div class="btn-wrapper">
                            <ol class="nav-btn">
								<#if orderInformation.orinStatus == 1 && orderInformation.orinPayStatus == 0>
                                    <a href="wap/order/${orderInformation.orinId}/cancel"><li class="grey on">取消订单</li></a>
								<#elseif orderInformation.orinStatus == 2>
                                    <a href="javascript:;" onclick="deleteOrder(${orderInformation.orinId});"><li class="grey on">删除订单</li></a>
								</#if>
                            </ol>
                        </div>
                    </div>
                </div>
			</#if>
		</footer>
	</div>
	<#-- 下面写需要引入的js模块，fastclick是一个解决移动端点击延迟和点透bug的插件，按照页面需要引入；如果页面不需要使用js模块，则把require.js也删掉  -->
	<#include "../../common/_js/require_config_wap.ftl" />
	<script type="text/javascript" src="assets/requirejs/require.js"></script>
	<script type="text/javascript">
		requirejs.config(mssRequireConfig);
		require(['fastclick', 'app'], function(FastClick){
			FastClick.attach(document.body);
		});

		// 删除订单
		var deleteOrder = function(id) {
			App.confirm('确定要删除该订单吗？', function() {
                App.post('wap/order/' + id + '/delete', null, function(data) {
                    if (data == 200) {
                        window.location.href = '${base}/wap/order/list';
                    } else {
                        App.alert(data.message);
                    }
                });
			});
		};
	</script>
</body>
</html>