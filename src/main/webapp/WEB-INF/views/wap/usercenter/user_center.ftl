<!DOCTYPE html>
<html>
<head lang="en">
    <title>用户中心</title>
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
    <link href="assets/wap/css/common/wicons.css" rel="stylesheet">
    <link href="assets/wap/css/common/widget_menu.css" rel="stylesheet">
</head>
<body>
	<div class="container">
		<header>
			<section class="header-section-user">
				<a class="tbox" href="wap/usercenter/personalDetail">
					<div>
						<span class="img-wrap">
							<img src="${(wechat.weinHeadingimgurl)!}" />
						</span>
					</div>
					<div>
	                    <p>${(wechat.weinNickname)!}</p>
	                    <p>
	                    	<#if member??>
		                        <label>会员</label>
		                        <span>${member.meinIntegral!"0"}&nbsp;积分</span>
		                    <#else>
		                    	<label>非会员</label>
	                        </#if>
	                    </p>
	                </div>
				</a>
			</section>
			<section class="header-section-order">
	            <ul>
	                <li>
	                    <a class="order-type" data-type="0" href="wap/order/list">
	                        <span data-tip="${orderStatistic["type0_count"]!}">&nbsp;</span>
	                        <p>待支付</p>
	                    </a>
	                </li>
	                <li>
	                    <a class="order-type" data-type="1" href="wap/order/list">
	                        <span data-tip="${orderStatistic["type1_count"]!}">&nbsp;</span>
	                        <p>待发货</p>
	                    </a>
	                </li>
	                <li>
	                    <a class="order-type" data-type="2" href="wap/order/list">
	                        <span data-tip="${orderStatistic["type2_count"]!}">&nbsp;</span>
	                        <p>待收货</p>
	                    </a>
	                </li>
	                <li>
	                    <a class="order-type" data-type="3" href="wap/order/list">
	                        <span data-tip="${orderStatistic["type3_count"]!}">&nbsp;</span>
	                        <p>待评价</p>
	                    </a>
	                </li>
	                <li>
	                    <a href="wap/order/appeal/list">
	                        <span>&nbsp;</span>
	                        <p>维权</p>
	                    </a>
	                </li>
	            </ul>
	        </section>
		</header>
		<section class="center-body">
			<div>
				<ul class="list-ul">
	                <div>
	                    <li>
	                        <a href="wap/order/list" id="linkOrders">
	                            <span>&nbsp;</span>全部订单
	                        </a>
	                    </li>
	                </div>

			        <div>
			            <#--<li>
			                <a href="javascript:;" id="linkWishList">
			                    <span>&nbsp;</span>我的心愿单
	                            <label>敬请期待</label>
			                </a>
			            </li>
			            <li>
			                <a href="javascript:;" id="linkHelpBuy">
			                    <span>&nbsp;</span>我的帮购
	                            <label>敬请期待</label>
			                </a>
			            </li>-->
			            <li>
			                <a href="wap/usercenter/collect" id="linkFavourite">
			                    <span>&nbsp;</span>我的收藏
			                </a>
			            </li>
			            <li>
			                <a href="wap/usercenter/shopcart" id="linkShopcart">
			                    <span>&nbsp;</span>我的购物车
			                </a>
			            </li>
			        </div>
			    </ul>
			</div>
		</section>

        <footer data-role="footer">
			<#include "../../common/_template/wap/copyright.ftl" />
            <#include "../../common/_template/wap/menubar.ftl" />
        </footer>
		
	</div>
	<#-- 下面写需要引入的js模块，fastclick是一个解决移动端点击延迟和点透bug的插件，按照页面需要引入；如果页面不需要使用js模块，则把require.js也删掉  -->
	<#include "../../common/_js/require_config_wap.ftl" />
	<script type="text/javascript" src="assets/requirejs/require.js"></script>
	<script type="text/javascript">
        requirejs.config(mssRequireConfig);
        require(['fastclick', 'zepto'], function(FastClick){
        	FastClick.attach(document.body);
			$(".order-type").on('click', function(){
				localStorage.setItem("orderType", $(this).data("type"));
			});
			$("#linkOrders").on('click', function(){
				localStorage.setItem("orderType", -1);
			});
        });
    </script>
</body>
</html>