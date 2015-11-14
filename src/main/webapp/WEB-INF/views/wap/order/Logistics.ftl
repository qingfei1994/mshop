<!DOCTYPE html>
<html>
<head lang="en">
    <title>物流信息</title>
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
    <link href="assets/wap/css/order/appeal/appeal.css" rel="stylesheet" type="text/css"/>
</head>
<body>
	<div class="container">
		<section class="body auto-height">
			<div class="detail-block">
				<div class="block-title logistics">
					<label>快递公司：</label>
				</div>
				<div class="block-title no-logo">
					<label>快递单号：</label>
				</div>
              
				<ul class="process-list">
                      

                    <li>
						<div>
							<span class="process-point"></span>
						</div>
						<div>
							<span class="process-info">
								暂无物流信息
							</span>
						</div>
					</li>
                    
				
					
					
				</ul>
			</div>
		</section>
		<footer>
			<#include "../../common/_template/wap/copyright.ftl" />
		</footer>
	</div>
	<#-- 下面写需要引入的js模块，fastclick是一个解决移动端点击延迟和点透bug的插件，按照页面需要引入；如果页面不需要使用js模块，则把require.js也删掉  -->
	<#include "../../common/_js/require_config_wap.ftl" />
	<script type="text/javascript" src="assets/requirejs/require.js"></script>
	<script type="text/javascript">
		requirejs.config(mssRequireConfig);
		require(['fastclick'], function(FastClick){
			FastClick.attach(document.body);
		});
	</script>
</body>
</html>