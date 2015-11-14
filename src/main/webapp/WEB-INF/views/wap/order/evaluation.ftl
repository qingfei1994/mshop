<!DOCTYPE html>
<html>
<head lang="en">
    <title>评价</title>
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
    <link href="assets/wap/css/order/evaluation.css" rel="stylesheet" type="text/css"/>
</head>
<body>
	<div class="container">
        <section class="body auto-height">
			<div class="form-wrapper">
				<form action="" method="post" id="formComments">
					<input type="hidden" name="stars-default" value="5">

					<#list orderInformation.goodsOrders as goodsOrder>
					<div class="comments-block">
						<a href="wap/goods/${goodsOrder.goodId}/detail">
							<div class="goods-info">
								<span class="pic">
									<img src="${goodsOrder.goorFirstPicture}">
								</span>
								<span class="info">${goodsOrder.goorName}</span>
								<input type="hidden" name="data" value="${goodsOrder.goorId}">
							</div>
						</a>

						<#if goodsOrder.goodsComment??>
                            <div class="comments-stars">
                                <span>星级</span>
								<span class="re${goodsOrder.goodsComment.gocoSatisfaction}">
									<ul class="list-stars">
                                        <li data-value="1">&nbsp;</li>
                                        <li data-value="2">&nbsp;</li>
                                        <li data-value="3">&nbsp;</li>
                                        <li data-value="4">&nbsp;</li>
                                        <li data-value="5">&nbsp;</li>
                                    </ul>
								</span>
                                <span class="retext">
									<#if (goodsOrder.goodsComment.gocoSatisfaction >= 4)>
                                        好评
									<#elseif (goodsOrder.goodsComment.gocoSatisfaction >= 2)>
                                        中评
									<#else>
                                        差评
									</#if>
                                </span>
                                <input type="hidden" name="stars" value="${goodsOrder.goodsComment.gocoSatisfaction}">
                            </div>
                            <div class="comments-text">
                                <span>内容</span>
                                <span><textarea id="comments1" name="content" placeholder="你的评价将会对其他买家很有帮助~" maxlength="200">${goodsOrder.goodsComment.gocoCommentContent}</textarea></span>
                            </div>
						<#else>
                            <div class="comments-stars">
                                <span>星级</span>
								<span class="re5">
									<ul class="list-stars">
                                        <li data-value="1">&nbsp;</li>
                                        <li data-value="2">&nbsp;</li>
                                        <li data-value="3">&nbsp;</li>
                                        <li data-value="4">&nbsp;</li>
                                        <li data-value="5">&nbsp;</li>
                                    </ul>
								</span>
                                <span class="retext">好评</span>
                                <input type="hidden" name="stars" value="5">
                            </div>
                            <div class="comments-text">
                                <span>内容</span>
                                <span><textarea id="comments1" name="content" placeholder="你的评价将会对其他买家很有帮助~" maxlength="200"></textarea></span>
                            </div>
						</#if>

					</div>
					</#list>

				</form>
			</div>
	    </section>
		<footer>
			<#include "../../common/_template/wap/copyright.ftl" />
			<div data-role="widget" data-widget="foot" class="foot-menu">
				<div class="widget-wrapper">
					<div class="btn-wrapper">
						<ol class="nav-btn">
							<a href="javascript:;" id="btn-comments-ok"><li class="red">提交评价</li></a>
						</ol>
					</div>
				</div>
			</div>       
		</footer>
	</div>
	<#-- 下面写需要引入的js模块，fastclick是一个解决移动端点击延迟和点透bug的插件，按照页面需要引入；如果页面不需要使用js模块，则把require.js也删掉  -->
	<#include "../../common/_js/require_config_wap.ftl" />
	<script type="text/javascript" src="assets/requirejs/require.js"></script>
	<script type="text/javascript">
		requirejs.config(mssRequireConfig);
		require(['fastclick', 'app'], function(FastClick){
			FastClick.attach(document.body);

			$('.list-stars li').on('click', function() {
                changeCommentsStars($(this));
			});

			$('#btn-comments-ok').on('click', function() {
				$.post('${base}/wap/order/evaluation', $('#formComments').serialize(), function(data) {
					App.alert(data);
				}, 'json');
			});
		});

		var changeCommentsStars = function($el) {
            var val = $el.attr('data-value');
            var cla = 're' + val;
            var $parent = $el.parent().parent();
            if ($parent.hasClass(cla)) {
                $parent.removeClass().addClass('re' + (--val));
            } else {
                $parent.removeClass().addClass(cla);
            }

            var str;
            if (val >= 4) {
				str = '好评';
			} else if (val >= 2) {
                str = '中评';
			} else {
				str = '差评';
			}
            $('.retext').html(str);

            $parent.next().next().val(val);
		};
	</script>
</body>
</html>