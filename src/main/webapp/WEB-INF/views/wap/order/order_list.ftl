<!DOCTYPE html>
<html>
<head lang="en">
    <title>全部订单</title>
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
    <link href="assets/wap/css/order/order_list.css" rel="stylesheet" type="text/css"/>
    <link href="assets/wap/gmu/css/widget/gotop/gotop.css" rel="stylesheet" type="text/css"/>
    <link href="assets/wap/gmu/css/widget/refresh/refresh.default.css" rel="stylesheet" type="text/css"/>
    <link href="assets/wap/gmu/css/widget/dialog/dialog.css" rel="stylesheet" type="text/css"/>
    <link href="assets/wap/gmu/css/widget/dialog/dialog.iOS7.css" rel="stylesheet" type="text/css"/>
    <script>
    	var orderType = localStorage.getItem("orderType");
		var Args = {
			type: orderType,//0：待支付，1：待发货，2：待收货，3：待评价，-1表示全部
			pagination: {
	            pageNo: 1,
	            pageSize: 10
	        },
			urls: {
				orderData: "wap/order/orderData",
				confirmReception: "wap/order/{orinId}/confirmReception"
			}
		}
    </script>
</head>
<body>
	<div class="container myorder">
		<section class="body auto-height">
            <div id="refreshDiv">
	            <div id="listOrder" class="list-order">
				</div>
        		<div class="ui-refresh-down"></div>
			</div>
        </section>
		<footer>
			<#include "../../common/_template/wap/copyright.ftl" />
		</footer>
	</div>
	<div id="gotop"></div>
	<#-- 下面写需要引入的js模块，fastclick是一个解决移动端点击延迟和点透bug的插件，按照页面需要引入；如果页面不需要使用js模块，则把require.js也删掉  -->
	<#include "../../common/_js/require_config_wap.ftl" />
	<script type="text/javascript" src="assets/requirejs/require.js"></script>
	<script type="text/javascript">
        requirejs.config(mssRequireConfig);
        require(['fastclick', 'app', 'refresh_lite', 'gotop'], function(FastClick){
            FastClick.attach(document.body);
            $("#gotop").gotop();
            
            var refreshObject = null;
			var noData = false;
			
			//获取收藏列表数据
			var getOrderData = function(me) {
				$.ajax({
					type: 'GET',
		            url: Args.urls.orderData,
		            data: {
		            	type: Args.type,
		                pageNo : Args.pagination.pageNo,
		                pageSize : Args.pagination.pageSize
		            },
		            dataType: 'json',
		            context: $('body'),
		            success: function(data){
		            	var html = '';
		            	var list = data.list;
				        for(i in list) {
							html = html+ '<div class="orderlist">'+
				            		'<span>'+
										'下单时间：'+ list[i].orinOrderTime +
										'<label>￥'+ list[i].orinTotal.toFixed(2) +'</label>'+
									'</span>'+
									'<ul>';
									var goodOrder = list[i].goodsOrders;
									for(j in goodOrder) {
										html = html+ '<li>'+
												'<a href="wap/order/' + list[i].orinId + '/detail">'+
													'<span>'+
														'<img src="'+ goodOrder[j].goorFirstPicture +'">'+
													'</span>'+
													'<label>'+
														'<span class="table">'+
															'<div class="line1">'+
																'<span>'+ goodOrder[j].goorName +'</span>'+
															'</div>'+
															'<div class="line2">'+
																'<span>￥'+ goodOrder[j].goorPrice.toFixed(2) +'</span>'+
															'</div>'+
															'<div class="line3">'+
																'<div>'+
																	'<span>数量'+ goodOrder[j].goorCount +'</span>'+
						                                        '</div>'+
															'</div>'+
														'</span>'+
													'</label>'+
												'</a>'+
											'</li>';
									}
							var status = '';
							if(list[i].orinStatus == 0) {
								status = '<span class="orderStatus">交易关闭</span>';
							} else if (list[i].orinStatus == 1) {
								if(list[i].orinPayStatus == 0) {
									status = '<span class="orderStatus">待支付</span>'+
											'<a href="wap/payment/pay/?orinNo='+ list[i].orinNo +'" class="btn fr white">去支付</a>';
								} else {
									if(list[i].shipmentsInformation && list[i].shipmentsInformation.shinShipmentsStatus == 0) {
										status = '<span class="orderStatus">待发货</span>';
									} else {
										status = '<span class="orderStatus">已发货，待收货</span>'+
												'<a href="javascript:;" order="'+ list[i].orinId +'" class="comfirm-reception btn fr red-new"">确认收货</a>'+
												'<a href="wap/order/' + list[i].orinId + '/logistics" class="btn fr black-new">查看物流</a>';
									}
								}
							} else if (list[i].orinStatus == 2) {
								if(list[i].goodsOrders && list[i].goodsOrders[0].goodsComment) {
									
										status = '<span class="orderStatus">交易完成</span>'+'<a href="wap/order/' + list[i].orinId + '/reply" class="btn fr red-new">查看评价</a>';
									
									
								} else {
									status = '<span class="orderStatus">交易完成 未评价</span>'+
											'<a href="wap/order/' + list[i].orinId + '/evaluation" class="btn fr red-new">去评价</a>';
								}
							}
							html = html+ '</ul>'+
									'<label>'+ status +'</label>'+
								'</div>';
						}
						
						$("#listOrder").append(html);
						App.removeLoading();
						me.afterDataLoading();
						
		                //如果数据为空
						if($("#listOrder div").length == 0){
							$("#listOrder").addClass("empty");
							me.disable('down', true);
						} else {
							$("#listOrder").removeClass("empty");
							//设置分页选项
			                if (data.pageNo >= Math.ceil(data.totalCount / data.pageSize)) {
                                $('.ui-refresh-down').off('click');
                                $('.ui-refresh-down').html('没有更多了');
			                    me.disable('down');
			                    noData = true;
			                } else {
			                	Args.pagination.pageNo = data.pageNo + 1;
			                }
						}
		            },
		            error: function(xhr, type){
		            	alert("网络错误");
		            }
				});
			}
			
			
			//初始化上拉加载事件
            $('#refreshDiv').refresh({
                load: function(dir, type) {
                	if(noData && type=="pull") {
                		this.afterDataLoading();
                		this.disable('down');
                		return;
                	}
					App.showLoading();
                	refreshObject = this;
                	getOrderData(this);
                }
            });
            
            //初始化数据
        	$('.ui-refresh-down').trigger('click');
            
            $("body").on("click", ".comfirm-reception", function(){
            	var the = $(this);
            	App.confirm("确认收货？", function(){
            		location.href = Args.urls.confirmReception.replace('{orinId}', the.attr("order"));
            	});
            });
        });
    </script>
</body>
</html>