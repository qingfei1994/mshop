<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <title>所有商品分类--嘉宝商城</title>
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

    <link href="assets/wap/css/goods/classify.css" rel="stylesheet">
    <link href="assets/wap/css/common/wicons.css" rel="stylesheet">
    <link href="assets/wap/css/common/widget_menu.css" rel="stylesheet">
</head>

<body onselectstart="return true;" ondragstart="return false;">
<div data-role="container" class="container classify2">
    <section data-role="body" class="body" style="min-height: 400px;">
        <div data-role="widget" data-widget="classify-2" class="classify-2" ontouchmove="return false;" id="classify-2">
            <!--组件部分 开始-->
            <div class="widget-wrap box ofh">
                <div data-role="widget" data-widget="list-nav" class="list-nav">
                    <div class="widget-wrap">
                        <div id="scroller-nav" style="position: absolute; overflow: hidden;">
                            <ul id="list-nav" class="list-nav"
                                style="transition-property: transform; -webkit-transition-property: transform; transform-origin: 0px 0px 0px; transform: translate(0px, 0px) translateZ(0px);">
                                <#list goodsClassificationList as goodsClassification>
                                    <li data-index="${goodsClassification_index}">
                                        <a href="javascript:;" class="<#if goodsClassification_index=0>on</#if>">
                                            ${goodsClassification.goclName}
                                        </a>
                                    </li>
                                </#list>
                            </ul>
                        </div>
                    </div>
                </div>
                <div data-role="widget" data-widget="list-body" class="list-body">
                    <div class="widget-wrap">
                        <div id="scroller-body" style="height: 480px; overflow: hidden;">
                            <div style="transition-property: transform; -webkit-transition-property: transform; transform-origin: 0px 0px 0px; transform: translate(0px, 0px) scale(1) translateZ(0px);">
                                <#list goodsClassificationList as goodsClassification>
                                    <ul class="list-ul <#if goodsClassification_index == 0>on</#if>">
                                        <#if (goodsClassification.goodsClassifications)?? && goodsClassification.goodsClassifications?size gt 0 >
                                            <#list goodsClassification.goodsClassifications as sonGoodsClassification>
                                            <li>
                                                <a href="javascript:;" onclick="classifyList('${sonGoodsClassification.goclId}')">
                                                    <div>
                                                        <span>
                                                            <img src="${sonGoodsClassification.goclIconUrl}">
                                                        </span>
                                                    </div>
                                                    <div>
                                                        <label>${sonGoodsClassification.goclName}</label>
                                                    </div>
                                                </a>
                                            </li>
                                            </#list>
                                        </#if>
                                    </ul>
                                </#list>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <!--组件部分 结束-->
        </div>
    </section>
    <footer data-role="footer">
        <#include "../../common/_template/wap/menubar.ftl" />
    </footer>
</div>

<#-- 下面写需要引入的js模块，fastclick是一个解决移动端点击延迟和点透bug的插件，按照页面需要引入；如果页面不需要使用js模块，则把require.js也删掉  -->
<#include "../../common/_js/require_config_wap.ftl" />
<script type="text/javascript" src="assets/requirejs/require.js"></script>
<script type="text/javascript">
    requirejs.config(mssRequireConfig);
    require(['fastclick','zepto','gmu'], function(FastClick){
        $(document).ready(function() {
            FastClick.attach(document.body);

            /** 初始化页面 **/
            var $eles = {}, ele = {};
            function initPage(){
                $eles.iscroll_wraps.css({"height":($(window).height()-51)+"px", "overflow":"auto"});

                $eles.list_nav_lis.on("click", function(evt){
                    ele.index = parseInt(this.getAttribute("data-index") );
                });

                ele.scroller_nav = new iScroll('scroller-nav', {
                    vScrollbar: false,
                    handleClick: false,
                    onBeforeScrollStart: function(e){}
                });

                ele.scroller_body = new iScroll('scroller-body', {
                    vScrollbar: false,
                    handleClick: false,
                    onBeforeScrollStart: function(e){}
                });

                ele.scroller_body.wrapper.addEventListener("click", function(){
                    sessionStorage.setItem("ele-idx", ele.index);
                }, false);

                var idx = sessionStorage.getItem("ele-idx");
                if(idx){
                    ele.index = idx;
                    sessionStorage.removeItem("ele-idx");
                }
            }

            /** 分类模块(保存分类数据) */
            window.classifyList = function (goclId) {
                if (goclId) {
                    localStorage.goclId = goclId;
                }
                window.location.href="wap/goods/list"
            };

            $(function(){
                /**　初始化数据 **/
                $eles = {
                    classify_2: $("#classify-2"),
                    list_nav: $("#list-nav"),
                    list_nav_lis: $("#list-nav li"),
                    iscroll_wraps: $("#scroller-nav, #scroller-body")
                }

                /** 分类菜单事件 **/
                ele = (function(){
                    function Ele(){
                        var index = 0;
                        this.scroller_nav = null;
                        this.scroller_body = null;

                        Object.defineProperty(this, "index", {
                            get: function(){
                                return index;
                            },
                            set: function(v){
                                index = v;
                                console.log(index);
                                $("#list-nav li a, .list-ul").removeClass("on");
                                $(".list-ul").eq(ele.index).addClass("on");
                                $eles.list_nav_lis.find("a").eq(ele.index).addClass("on");
                                this.scroller_body.refresh();
                            }
                        });

                    }
                    return new Ele();
                })();

                initPage();
            });
        });
    });
</script>
</body>
</html>