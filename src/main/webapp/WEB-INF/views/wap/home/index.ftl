<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <base href="${base}/" />
    <title><#-- 标题 --></title>
    <meta charset="utf-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta content="width=device-width, initial-scale=1.0, maximum-scale=1.0,  user-scalable=no" name="viewport" />
    <meta content="yes" name="apple-mobile-web-app-capable" />
    <meta content="black" name=" apple-mobile-web-app-status-bar-style" />
    <meta content="telephone=no" name="format-detection" />
    <meta http-equiv="Cache-Control" content="no-cache" />

    <link href="assets/wap/css/common/reset.css" rel="stylesheet" type="text/css"/>
    <link href="assets/wap/css/common/common.css" rel="stylesheet" type="text/css"/>

    <link href="assets/wap/css/home/home.css?v=1" rel="stylesheet">
    <link href="assets/wap/css/common/widget_menu.css" rel="stylesheet">

    <link href="assets/wap/gmu/css/widget/gotop/gotop.css" rel="stylesheet" type="text/css"/>

    <!-- script start -->
    <script type="text/javascript" src="${base}/assets/wap/plugins/swipe/swipe_min.js"></script>
    <!-- script end -->
</head>
<body>
    <div class="container home">
        <header data-role="header"></header>
        <section data-role="body" class="body auto-height">
            <div data-role="widget" data-widget="slider-1" class="slider-1">
                <section class="widget-wrap">
                    <div id="slider-1-wrap" class="slider-1-wrap">
                        <ul>
                        	<#list banner as banner>
                            <li>
                                <a data-fx="Modulefx" href="${(banner.inbaDetailUrl)!}">
                                    <img src="${(banner.inbaImageUrl)!}">
                                </a>
                            </li>
                            </#list>
                        </ul>
                        <div id="slider-1-indicate" class="slider-1-indicate"></div>
                    </div>
                </section>
                <script type="text/javascript">
                    var slider_1 = (function () {
                        var that = {};
                        that.initSlider = function () {
                            that.slider = new Swipe(document.getElementById('slider-1-wrap'), {
                                speed: 500,
                                loop: true,
                                auto: 3000,
                                indicate: "#slider-1-indicate"
                            });
                            return that;
                        }
                        return that;
                    })().initSlider();
                </script>
            </div>
            <#list groups as group>
            <div data-role="widget" data-widget="title-5" class="title-5">
                <div class="widget-wrap">
                    <label>
                       		${(group.ingrName)!}
                        <a data-fx="Modulefx" href="#"></a>
                    </label>
                </div>
            </div>
            <#if group.indexGoodses??>
            	<#list group.indexGoodses as indexGoods>
			            <div data-role="widget" data-widget="topic-2" class="topic-2">
			                <div class="widget-wrap">
			                    <ul>
			                        <li>
			                            <a data-fx="Modulefx" href="wap/goods/${(indexGoods.goodId)!}/detail">
			                                <dl class="tbox">
			                                    <dd>
			                                        <div class="img-wrap">
			                                            <img src="${(indexGoods.goods.goodFirstPicture)!}">
			                                        </div>
			                                    </dd>
			                                    <dd>
			                                        <p>${(indexGoods.goods.goodName)!}</p>
			                                    </dd>
			                                </dl>
			                            </a>
			                        </li>
			                    </ul>
			                </div>
			            </div>
	            </#list>
              </#if>
            </#list>
        </section>
        <footer data-role="footer">
            <#include "../../common/_template/wap/copyright.ftl" />
            <#include "../../common/_template/wap/menubar.ftl" />
        </footer>
    </div>
    <div id="gotop"></div>
    <#-- 下面写需要引入的js模块，fastclick是一个解决移动端点击延迟和点透bug的插件，按照页面需要引入；如果页面不需要使用js模块，则把require.js也删掉  -->
    <#include "../../common/_js/require_config_wap.ftl" />
    <script type="text/javascript" src="assets/requirejs/require.js"></script>
    <script type="text/javascript">
        requirejs.config(mssRequireConfig);
        require(['fastclick', 'gotop'], function(FastClick){
            $(document).ready(function() {
                FastClick.attach(document.body);
                $("#gotop").gotop();
            });
        });
    </script>
</body>
</html>