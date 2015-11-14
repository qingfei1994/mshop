<!DOCTYPE html>
<html>
<head lang="en">
    <title>申请维权</title>
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
    <link href="assets/wap/css/order/appeal/appeal.css" rel="stylesheet" type="text/css"/>
</head>
<body>
	<div class="container">
		<section class="body auto-height">
            <div class="form-wrapper">
                <form method="post" id="formApply">
                    <ul class="rights-list">
                       
                        <li>
                            <div>维权说明</div>
                            <div>
                                <textarea placeholder="200字以内" class="require" maxlength="200" name="clapReason">${(clientAppeal.clapReason)!''}</textarea>
                            </div>
                        </li>
                    </ul>

                    <input type="hidden" name="goorId" value="${goodsOrder.goorId}">
                    
                    <div class="section-div">
                        <!--<div>
                            <span class="caution">*</span>
                            <span>该商品不支持线上退款，请填写相应的收款信息</span>
                        </div>
                    </div>

                    <ul class="rights-list">
                        <li>
                            <div>收款方式</div>
                            <div>
                                <input type="text" placeholder="请填写支付宝或银行名称等" class="require" name="OfflinePaymentType" maxlength="20">
                            </div>
                        </li>
                        <li>
                            <div>收款人姓名</div>
                            <div>
                                <input type="text" placeholder="请填写开户人姓名" class="require" name="OfflinePayeeName" maxlength="10">
                            </div>
                        </li>
                        <li>
                            <div>收款账号</div>
                            <div>
                                <input type="text" placeholder="请填写收款账号" class="require" name="OfflinePaymentNum" maxlength="20">
                            </div>
                        </li>
                    </ul>-->

                    <div class="btn-div">
                        <input type="button" value="提交申请" class="btn-red" onclick="appealApply()">
                    </div>
                </form>
            </div>
        </section>
		<footer>
			<#include "../../../common/_template/wap/copyright.ftl" />
		</footer>
	</div>
	<#-- 下面写需要引入的js模块，fastclick是一个解决移动端点击延迟和点透bug的插件，按照页面需要引入；如果页面不需要使用js模块，则把require.js也删掉  -->
	<#include "../../../common/_js/require_config_wap.ftl" />
	<script type="text/javascript" src="assets/requirejs/require.js"></script>
	<script type="text/javascript">
		requirejs.config(mssRequireConfig);
		require(['fastclick', 'app'], function(FastClick){
			FastClick.attach(document.body);
		});

        // 申请退款
        var appealApply = function() {
            App.confirm('确定要申请退款吗？', function() {
                App.post('wap/order/appeal/apply', $('#formApply').serialize(), function(data) {
                    if (data.success == 1) {
                        App.alert(data.message);
                        window.location.href = '${base}/wap/order/${goodsOrder.orinId}/detail';
                    } else {
                        App.alert(data.message);
                    }
                });
            });
        };
	</script>
</body>
</html>