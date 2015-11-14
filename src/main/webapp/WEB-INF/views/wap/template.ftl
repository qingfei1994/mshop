<!DOCTYPE html>
<html>
<head lang="en">
    <title><#-- 标题 --></title>
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
    <#-- 下面写需要引入的css -->
</head>
<body>
	<div class="container">
		<#-- 此处写页面内容 -->
		<#-- <header></header> -->
		<section>
		</section>
		<footer>
			<#include "../common/_template/wap/copyright.ftl" />
		</footer>
	</div>
	<#-- 下面写需要引入的js模块，fastclick是一个解决移动端点击延迟和点透bug的插件，按照页面需要引入；如果页面不需要使用js模块，则把require.js也删掉  -->
	<#include "../common/_js/require_config_wap.ftl" />
	<script type="text/javascript" src="assets/requirejs/require.js"></script>
	<script type="text/javascript">
		requirejs.config(mssRequireConfig);
		require(['fastclick'], function(FastClick){
			$(document).ready(function(){
				FastClick.attach(document.body);
			});
		});
	</script>
</body>
</html>