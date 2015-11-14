<!DOCTYPE html>
<html>
<head lang="en">
    <title>收藏</title>
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
    <link href="assets/wap/css/usercenter/collect/collect.css" rel="stylesheet">
    <link href="assets/wap/gmu/css/widget/gotop/gotop.css" rel="stylesheet" type="text/css"/>
    <link href="assets/wap/gmu/css/widget/refresh/refresh.default.css" rel="stylesheet" type="text/css"/>
    <link href="assets/wap/gmu/css/widget/dialog/dialog.css" rel="stylesheet" type="text/css"/>
    <link href="assets/wap/gmu/css/widget/dialog/dialog.iOS7.css" rel="stylesheet" type="text/css"/>
	<script>
    	var Args = {
    		pagination: {
	            pageNo: 1,
	            pageSize: 10
	        },
    		urls: {
    			collectData: "wap/usercenter/collect/collectData"
    		},
    		
    	}
    </script>
</head>
<body>
	<div class="container myfavorite">
		<section class="body auto-height">
			<div id="refreshDiv">
	            <ul id="list" class="favorite-list">
	            </ul>
        		<div class="ui-refresh-down"></div>
            </div>
        </section>
		<footer>
			<#include "../../../common/_template/wap/copyright.ftl" />
		</footer>
	</div>
	<div class="sku-dialog"></div>
	<div id="gotop"></div>
	<#-- 下面写需要引入的js模块，fastclick是一个解决移动端点击延迟和点透bug的插件，按照页面需要引入；如果页面不需要使用js模块，则把require.js也删掉  -->
	<#include "../../../common/_js/require_config_wap.ftl" />
	<script type="text/javascript" src="assets/requirejs/require.js"></script>
	<script type="text/javascript">
		requirejs.config(mssRequireConfig);
		require(['fastclick', 'app', 'refresh_lite', 'gotop'], function(FastClick){
			$(document).ready(function(){
				FastClick.attach(document.body);
				$("#gotop").gotop();
				
				var refreshObject = null;
				var noData = false;
				
				//获取收藏列表数据
				var getCollectData = function(me) {
					$.ajax({
						type: 'GET',
			            url: Args.urls.collectData,
			            data: {
			                pageNo : Args.pagination.pageNo ,
			                pageSize : Args.pagination.pageSize
			            },
			            dataType: 'json',
			            context: $('body'),
			            success: function(data){
			            	var html = '';
			            	var list = data.list;
					        for(i in list) {
								html = html+ '<li>'+
					            		'<a href="wap/goods/'+ list[i]["good_id"] +'/detail">'+
					            			'<span>'+
					            				'<img src="'+ list[i]["good_first_picture"] +'">'+
					            			'</span>'+
					            			'<label>'+
					            				'<span class="table">'+
					            					'<div class="line1">'+
					            						'<span>'+ list[i]["good_name"] +'</span>'+
					            					'</div>'+
					            					'<div class="line2">'+
					            						'<span>￥'+ list[i]["good_price"].toFixed(2) +'</span>'+
					            					'</div>'+
					            				'</span>'+
					            			'</label>'+
					            		'</a>'+
					            		'<label>'+
					            			'<a href="javascript:;" class="add2shopcart" goods="'+ list[i]["good_id"] +'">加入购物车</a>'+
					            			<#-- <a href="javascript:;" onclick="add2Help(this, event, 88040);">加入帮购</a> -->
					            			'<a href="javascript:;" class="delete" cogo="'+ list[i]["cogo_id"] +'">删除</a>'+
					            		'</label>'+
					            	'</li>';
							}
							
							$("#list").append(html);
							App.removeLoading();
							me.afterDataLoading();
							
			                //如果数据为空
							if($("#list li").length == 0){
								$("#list").addClass("empty");
								me.disable('down', true);
							} else {
								$("#list").removeClass("empty");
								//设置分页选项
				                if (data.pageNo >= Math.ceil(data.totalCount / data.pageSize)) {
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
                    	getCollectData(this);
                    }
                });
                
                //初始化数据
            	$('.ui-refresh-down').trigger('click');
            	
            	$("body").on('click', '.delete', function(){
            		var the = $(this);
            		App.confirm("确定要删除？", function(){
						App.showLoading();
						location.href = "wap/usercenter/collect/"+ the.attr("cogo") +"/delete";
					});
            	});
            	$("body").on('click', '.add2shopcart', function(){
            		App.showLoading();
            		$(".sku-dialog").load("wap/usercenter/collect/"+ $(this).attr("goods") +"/add2shopcart", function(){
            			App.removeLoading();
            			$(".sku-dialog").addClass("on");
            		});
            	});
            	$("body").on('click', '.cancel-addshopcart', function(){
            		$(".sku-dialog").removeClass("on");
            	});
            	$("body").on('click', '.sure-addshopcart', function(){
            		//var stock = parseInt($("#stock").text());
            		$(".sku-dialog").removeClass("on");
            		var number = parseInt($(".shop-number").val());
            		var gpstId = parseInt($("#gpstId").val());
            		App.post("wap/usercenter/collect/add2shopcart", {gpstId: gpstId, shcaCount: number}, function(res){
						App.alert("成功加入购物车");
					});
            	});
            	$("body").on('click', '.minus', function(){
            		var number = parseInt($(".shop-number").val());
            		if(number > 1) {
            			$(".shop-number").val(number-1);
            		}
            	});
            	$("body").on('click', '.plus', function(){
            		var number = parseInt($(".shop-number").val());
            		$(".shop-number").val(number+1);
            	});
			});
		});
	</script>
</body>
</html>