<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>商品详细信息--米所思商城</title>
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

    <link href="${base}/assets/wap/css/goods/detail3.css" rel="stylesheet">
    <link href="assets/wap/gmu/css/widget/dialog/dialog.css" rel="stylesheet">
    <link href="assets/wap/gmu/css/widget/dialog/dialog.iOS7.css" rel="stylesheet" type="text/css"/>

    <!-- script start -->
    <script type="text/javascript" src="${base}/assets/wap/plugins/swipe/swipe_min.js"></script>
    <!-- script end -->
</head>

<body onselectstart="return true;" ondragstart="return false;">
<div data-role="container" class="container detail4">
    <header data-role="header">
        <div data-role="widget" data-widget="slider-3" class="slider-3">
            <section data-role="widget" data-widget="img-prev-view" class="widget-wrap">
                <div id="slider-3-wrap" class="slider-3-wrap" style="max-height: 320px; height: 320px; visibility: visible;">
                    <ul>
                        <#list goodsPictureList as goodsPicture>
                            <li class="swiper-slide">
                                <a href="javascript:;">
                                    <img src="${goodsPicture.gopiUrl}" style="width: 100%; max-width: 100%;">
                                </a>
                            </li>
                        </#list>
                    </ul>
                    <div id="slider-3-indicate" class="slider-3-indicate"></div>
                </div>
            </section>
            <script type="text/javascript">
                var slider_1 = (function () {
                    var that = {};
                    that.initSlider = function () {
                        that.slider = new Swipe(document.getElementById('slider-3-wrap'), {
                            speed: 500,
                            loop: true,
                            auto: 3000,
                            indicate: "#slider-3-indicate"
                        });
                        return that;
                    }
                    return that;
                })().initSlider();
            </script>
        </div>
    </header>

    <section data-role="body" class="body" style="min-height: 160px;">
        <!--基本信息-->
        <div class="section-body info-basic">
            <div>
                <ul>
                    <li>
                        <div class="tbox title-and-fav">
                            <div id="label-title" class="title" style="width: 100%;">${goodsDetail.good_name}</div>
                            <div>
                                <div class="fav" onclick="collectGood(event);event.stopPropagation();return false;">
                                    <span id="icon-fav1" class="icon-fav <#if (goodsDetail.wein_id)??>on</#if>">&nbsp;</span>
                                    <label>&nbsp;</label>
                                </div>
                            </div>
                        </div>
                        <div class="tbox price-and-fav price-and-sale">
                            <div>
                                <div class="price">
                                    <p>
                                        微信价：<span id="label-price"><label>${goodsDetail.good_real_price?string('#')}</label>${goodsDetail.good_real_price?string("#.00")?substring(goodsDetail.good_price?string("#.00")?index_of("."))}
                                        </span>
                                    </p>
                                    <p>
                                        <span>原价：￥
                                            <span id="label-price-original">
                                                <label>${goodsDetail.gpst_market_price?string('#')}</label>${goodsDetail.gpst_market_price?string("#.00")?substring(goodsDetail.good_real_price?string("#.00")?index_of("."))}
                                            </span>
                                        </span>
                                    </p>
                                </div>
                            </div>
                        </div>
                        <!--/li>
                        <li-->
                        <p class="freight">
                            快递&nbsp;&nbsp;
                            <#if (transportationExpensesResult.totalMixExpenses)??>
                                ${transportationExpensesResult.totalMixExpenses?string('0.00')}元
                            <#elseif (transportationExpensesResult.totalExpressExpenses)??>
                                ${transportationExpensesResult.totalExpressExpenses?string('0.00')}元
                            <#elseif (transportationExpensesResult.totalEmsExpenses)??>
                                ${transportationExpensesResult.totalEmsExpenses?string('0.00')}元
                            <#elseif (transportationExpensesResult.totalPostExpenses)??>
                                ${transportationExpensesResult.totalPostExpenses?string('0.00')}元
                            </#if>
                            <label class="fr">销量&nbsp;&nbsp;${goodsDetail.good_sales}件</label>
                        </p>
                    </li>
                    <!--活动营销-->
                </ul>
            </div>
        </div>
        <!--请选择 尺码 颜色 以及数量-->

        <div id="label-sku-title2-div" class="section-body link-sku-dialog">
            <a id="label-sku-title2" href="javascript:;" class="arrow">
                <label>
                    <div></div>数量：1
                </label>
            </a>
        </div>

        <!--进入店铺-->
        <div class="section-body link-to-shop">
            <a href="wap/home/index" class="arrow">
                <label>米所思旗舰店</label>
                <label>进入店铺</label>
            </a>
        </div>
        <!--参数&详情&评价-->
        <div class="section-body info-detail">
            <!--商品详情-->
            <div data-role="widget" data-widget="title-3" class="title-3">
                <div class="widget-wrap">
                    <label>
                        <p>商品介绍</p>
                    </label>
                </div>
                <section class="section-detail on" data-role="widget" data-widget="img-prev-view">
                    ${goodsDetail.good_details}
                </section>
            </div>
        </div>
    </section>

    <footer data-role="footer">
        <div data-role="widget" data-widget="btip-widget" class="btip-widget hidden" id="btip-widget">
            <div class="widget-wrap">
                <label id="btip-label" class="btip-label"></label>
            </div>
        </div>
        <!---->
        <div data-role="widget" data-widget="">
            <div class="widget-wrap">
                <ul class="fixed-btn">
                    <ol id="fixed-btn" class="tbox" style="position: fixed;">
                        <li>
                            <div style="width: 105px">
                                <a href="javascript:;" id="btn-add-fav"
                                   class="btn-add btn-add-fav <#if (goodsDetail.wein_id)??>on</#if>"
                                   onclick="collectGood(event);event.stopPropagation();return false;">
                                    &nbsp;
                                </a>
                                <a href="wap/usercenter/shopcart" id="btn-link-shopcart" class="btn-add btn-add-shopcart" data-count="${shoppingCartCount!0}">&nbsp;</a>
                            </div>
                        </li>
                        <li>
                            <div class="box">
                                <div>
                                    <a href="javascript:;" id="btn-add-shopcart" class="btn on">
                                        <label>加入购物车</label>
                                    </a>
                                </div>
                                <div>
                                    <a href="javascript:;" id="btn-buy" class="btn red on">
                                        <label>立刻购买</label>
                                    </a>
                                </div>
                            </div>
                        </li>
                    </ol>
                </ul>
            </div>
        </div>
    </footer>
</div>
<!---------------------->
<div data-role="widget" data-widget="sku-dialog" class="sku-dialog hidden" id="sku-dialog" ontouchmove="return false;">
    <div class="widget-mask"></div>
    <div class="widget-wrap">
        <ul>
            <li>
                <dl class="sku-header tbox">
                    <dd>
                        <span class="img-wrap">
                            <img src="${goodsDetail.good_first_picture}">
                        </span>
                    </dd>
                    <dd>
                        <p id="label-sku-price">￥<label>${goodsDetail.good_real_price?string('#.00')}</label></p>
                        <div>${goodsDetail.good_name}</div><p id="label-sku-title">数量：1</p>
                    </dd>
                    <dd>
                        <span class="sku-close" id="sku-close">&nbsp;</span>
                    </dd>
                </dl>
            </li>
            <section id="scroller-sku-list" style="min-height: 135px; overflow: hidden;">
                <div >
                    <div>
                        <li style="position: relative;">
                            <table id="table-number" class="table-number">
                                <tbody>
                                <tr>
                                    <td>
                                        <label style="white-space: pre;">数量</label>
                                    </td>
                                    <td>
                                        <div class="box">
                                            <div>
                                                <input type="button" value="-">
                                            </div>
                                            <div>
                                                <input type="number" value="1" id="sku-number">
                                            </div>
                                            <div>
                                                <input type="button" value="+">
                                            </div>
                                        </div>
                                    </td>
                                    <td class="td-sku-inventory">
                                        <label id="sku-inventory" class="sku-inventory">(剩余${goodsDetail.good_stock})</label>
                                    </td>
                                </tr>
                                </tbody>
                            </table>
                        </li>
                    </div>
                </div>
            </section>
            <li id="sku-btn-group" class="sku-btn-group group-1">
                <a href="javascript:;" id="btn-dialog-add-shopcart">加入购物车</a>
                <a href="javascript:;" id="btn-dialog-buy">立刻购买</a>
                <a href="javascript:;" id="btn-dialog-ok">确认</a>
            </li>
        </ul>
    </div>
</div>
<form id="buyNow" action="wap/order/preview" method="post" style="display: none">
    <input type="hidden" name="shoppingCartItems[0].gpstId" value="${goodsDetail.gpst_id}">
    <input type="hidden" name="shoppingCartItems[0].count" id="count">
</form>
<!---------------------->
<#-- 下面写需要引入的js模块，fastclick是一个解决移动端点击延迟和点透bug的插件，按照页面需要引入；如果页面不需要使用js模块，则把require.js也删掉  -->
<#include "../../common/_js/require_config_wap.ftl" />
<script type="text/javascript" src="assets/requirejs/require.js"></script>
<script type="text/javascript">
    /** 页面参数 **/
    var Args = {
        weinId : ${weinId},
        goodsId : ${goodsDetail.good_id},
        gpstId : ${goodsDetail.gpst_id},
        collceted : <#if (goodsDetail.wein_id)??> true <#else> false </#if>,
        curSku : {
            stock : ${goodsDetail.good_stock},
            sum : 1
        },
        urls : {
            collectUrl: "wap/usercenter/collect/${goodsDetail.good_id}",
            addShopcartUrl: "wap/usercenter/shopcart/add",
            shopcartUrl: "wap/usercenter/shopcart",
        }
    }

    ele = (function(){
        function Ele(){

            var skuTitle = "";
            var sku_dialog_show = false;

            Object.defineProperty(this, "curSku", {
                get: function(){
                    return Args.curSku;
                },
                set: function(v){
                    Args.curSku = v;

                    Args.curSku.sum = Math.max(0, Math.min(Args.curSku.sum, Args.curSku.stock));
                    if(Args.curSku.inventory){
                        Args.curSku.sum = Math.max(1, Args.curSku.sum);
                    }
                    $eles.sku_number.val(Args.curSku.sum);

                    $eles.label_sku_title.html(ele.skuTitle);
                    $eles.label_sku_title2.find("label").eq(0).html(ele.skuTitle);
                }
            });

            Object.defineProperty(this, "skuTitle", {
                get: function(){
                    return '<div></div>数量：' + Args.curSku.sum;
                },
                set: function(v){}
            });
            //显示sku弹窗
            Object.defineProperty(this, "sku_dialog_show", {
                get: function(){
                    return sku_dialog_show;
                },
                set: function(v){
                    sku_dialog_show = v;
                    $eles.sku_dialog[sku_dialog_show[0]?"removeClass":"addClass"]("hidden");
                    $eles.sku_btn_group.attr("class","sku-btn-group").addClass(sku_dialog_show[2]?"group-2":"group-1");
                }
            });

            //收藏商品
            var collected = false;
            Object.defineProperty(this, "collected", {
                get: function(){
                    return collected;
                },
                set: function(v){
                    collected = v;
                    //
                    $("#icon-fav1, #btn-add-fav")[collected?"addClass":"removeClass"]("on");
                }
            });
        }
        //
        return new Ele();
    })();

    /** 页面js */
    requirejs.config(mssRequireConfig);
    require(['fastclick', 'app', 'zepto'], function(FastClick){
        $(document).ready(function() {
            FastClick.attach(document.body);

            $eles = {
                label_sku_title2: $("#label-sku-title2"),
                sku_close: $("#sku-close"),
                table_number: $("#table-number"),
                sku_number: $("#sku-number"),
                sku_dialog: $("#sku-dialog"),
                sku_btn_group: $("#sku-btn-group"),
                sku_inventory: $("#sku-inventory"),
                label_price: $("#label-price"),
                label_sku_price: $("#label-sku-price"),
                label_sku_title: $("#label-sku-title"),
                btn_add_shopcart: $("#btn-add-shopcart"),
                btn_buy: $("#btn-buy"),
                btn_link_shopcart: $("#btn-link-shopcart"),
                btn_dialog_add_shopcart : $("#btn-dialog-add-shopcart"),
                btn_dialog_buy : $("#btn-dialog-buy"),
            }

            /** 打开sku窗口 **/
            $eles.label_sku_title2.on("click", function(evt){
                ele.sku_dialog_show = [true, 1];
            });
            /** 关闭sku窗口 **/
            $eles.sku_close.on("click", function(evt){
                ele.sku_dialog_show = [false, 1];
            });

            /** 修改购买数量 **/
            $eles.table_number.on("click", function(evt){
                var et = evt.target, en = et.tagName, newSku = Args.curSku;
                if("INPUT" == en && "button" == et.type){
                    newSku.sum += ("+" == et.value?1:-1);
                    ele.curSku = newSku;
                    $eles.label_sku_title.html(ele.skuTitle);
                    $eles.label_sku_title2.find("label").eq(0).html(ele.skuTitle);
                }
            });
            /** 数字修改时修改存 **/
            $eles.sku_number.on("change", function(evt){
                var newSku = Args.curSku;
                newSku.sum = parseInt(this.value);
                Args.curSku = newSku;
            });

            /** 添加商品到购物车 **/
            $eles.btn_add_shopcart.on("click", function() {
                addToShopcart();
            });
            $eles.btn_dialog_add_shopcart.on("click", function() {
                addToShopcart();
                ele.sku_dialog_show = [false, 1];
            });
            var addToShopcart = function() {
                $.post(
                        Args.urls.addShopcartUrl,
                        {
                            gpstId : Args.gpstId,
                            shcaCount : Args.curSku.sum
                        },
                        function(res) {
                            if (res) {
                                var sumCount = $eles.btn_link_shopcart.attr('data-count');
                                $eles.btn_link_shopcart.attr('data-count', Args.curSku.sum + parseInt(sumCount));
                                App.confirm("是否前往购物车结算", function() {
                                    window.location.href=Args.urls.shopcartUrl;
                                });
                            }
                        }
                );
            }

            /** 立即购买 **/
            $eles.btn_buy.on("click", function() {
                buy();
            });
            $eles.btn_dialog_buy.on("click", function() {
                buy();
            });
            var buy = function() {
                localStorage.gpstId = Args.gpstId;
                localStorage.buyCount = Args.curSku.sum;

                $("#buyNow #count").val(Args.curSku.sum);
                $("#buyNow").submit();
            }

            /** 收藏模块 **/
            window.collectGood = function (evt) {
                evt.preventDefault();
                $.getJSON(Args.urls.collectUrl, function(response){
                    if (response) {
                        if (Args.collceted) {
                            ele.collected = false;
                            Args.collceted = false;
                        } else {
                            ele.collected = true;
                            Args.collceted = true;
                        }
                    }
                });
            };

        });
    });
</script>
</body>
</html>