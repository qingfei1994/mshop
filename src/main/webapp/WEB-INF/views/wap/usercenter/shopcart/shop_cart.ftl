<!DOCTYPE html>
<html lang="zh-CN" class="ng-scope">
<head>
    <title>购物车--米所思商城</title>
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

    <link href="assets/wap/css/usercenter/shopcart/shopcart.css" rel="stylesheet">
</head>
<body>
    <div class="container shopcart">
        <section data-role="body" class="body auto-height">
            <div class="section_goods <#if shoppingCartItemList?size == 0>empty</#if>">
                <form id="form-shopcart-list" action="wap/order/preview" method="post">
                    <div class="shopcart-list-header">
                        <li class="tbox">
                            <label>
                                <input type="checkbox" class="radio selectAll" name="default" value="">
                            </label>
                            <div style="width: 100%;">
                                <label>全选</label>
                            </div>
                            <div>
                                <a href="javascript:;" class="icon-del" id="delGoods" style="display: none">&nbsp;</a>
                            </div>
                        </li>
                    </div>
                    <ul id="shopcart-list-body" class="shopcart-list-body list-goods">
                        <#list shoppingCartItemList as shoppingCartItem>
                        <li class="shopcart-part">
                            <div class="tbox">
                                <label>
                                    <input type="checkbox" name="shoppingCartItems[${shoppingCartItem_index}].gpstId"
                                              shcaid="${shoppingCartItem.shcaId}"
                                              value="${shoppingCartItem.gpstId}"
                                              class="radio selectOne select_${shoppingCartItem_index}"
                                              onclick="selectOne(this, event, ${shoppingCartItem.goodPrice?string("#.00")}, ${shoppingCartItem_index})">
                                </label>
                                <div>
                                    <span class="img-wrap">
                                        <a href="wap/goods/${shoppingCartItem.goodId}/detail">
                                            <img src="${shoppingCartItem.goodPicture!}">
                                        </a>
                                    </span>
                                </div>
                                <div>
                                    <p class="title ng-binding">${shoppingCartItem.goodName!}</p>
                                    <p class="price ng-binding">￥${shoppingCartItem.goodPrice?string("#.00")}</p>
                                    <p class="ng-binding"></p>
                                    <p></p>
                                    <dl class="ng-scope">
                                        <dd>
                                            <a href="javascript:;" onclick="changeSum(this, event, '-', ${shoppingCartItem.goodPrice?string("0.00")}, ${shoppingCartItem_index});">&nbsp;</a>
                                        </dd>
                                        <dd>
                                            <input type="tel" name="shoppingCartItems[${shoppingCartItem_index}].count" class="sum_${shoppingCartItem_index}" value="${shoppingCartItem.count!1}" readonly="true">
                                        </dd>
                                        <dd>
                                            <a href="javascript:;" onclick="changeSum(this, event, '+', ${shoppingCartItem.goodPrice?string("0.00")}, ${shoppingCartItem_index});">&nbsp;</a>
                                        </dd>
                                    </dl>
                                    <p></p>
                                </div>
                            </div>
                            <input name="prize" type="hidden" class="goods_prize_${shoppingCartItem_index}" value="${shoppingCartItem.goodPrice?string("0.00")}">
                        </li>
                        </#list>
                    </ul>
                </form>
            </div>
            <div class="hidden" style="display: hidden;">
                <a href="javascript:;" class="btn-del-invalid-goods">清空失效商品</a>
            </div>
        </section>
        <footer data-role="footer">
            <#include "../../../common/_template/wap/copyright.ftl" />
            <div data-role="widget" data-widget="footer-sub-btn" class="footer-sub-btn">
                <div class="widget-wrap hidden" style="display: inherit;">
                    <ul>
                        <ol class="tbox activity" style="visibility: hidden;">
                            <dd>
                                <label class="ng-binding">您可以参加活动</label>
                            </dd>
                            <dd>
                                <label class="ng-binding ng-hide">已减 - ￥10</label>
                            </dd>
                        </ol>
                        <ol class="tbox">
                            <li>
                                <div class="price-des" id="price-des">
                                    <p>总计<span class="price-total ng-binding">￥0.00</span></p>
                                    <p class="ng-binding">(共0件，不含运费)</p>
                                </div>
                            </li>
                            <li>
                                <a href="javascript:;" class="btn red" id="btn-buy">去结算</a>
                            </li>
                        </ol>
                    </ul>
                </div>
            </div>
        </footer>
    </div>

    <#include "../../../common/_js/require_config_wap.ftl" />
    <script type="text/javascript" src="assets/requirejs/require.js"></script>
    <script type="text/javascript">
        Args =
        {
            totalCount : 0,
            totalMoney : 0,
            urls : {
                delGoodsUrl : "wap/usercenter/shopcart/delete"
            }
        };

        /** 页面js */
        requirejs.config(mssRequireConfig);
        require(['fastclick', 'app', 'zepto'], function(FastClick) {
            $(document).ready(function() {
                FastClick.attach(document.body);

                $('#btn-buy').on("click", function() {
                    if($('.selectOne:checked').length > 0) {
                        $('#form-shopcart-list').submit();
                    }
                });

                /** 删除 **/
                $('#delGoods').on("click", function() {
                    var chk_value =[];
                    $('.selectOne:checked').each(function(){
                        chk_value.push($(this).attr("shcaid"));
                    });
                    App.post(
                            Args.urls.delGoodsUrl,
                            { shcaIds : chk_value.join(',') },
                            function(res) {
                                if(res) {
                                    window.location.reload();
                                } else {
                                    console.log("delete fail!");
                                }
                            }
                    );
                });

                /** 全选 **/
                $(".selectAll").on("click", function () {
                    if ($(this).is(":checked")) {
                        $('input[type=checkbox]').prop('checked', true);
                        $("#delGoods").show();

                        $('.shopcart-part').each(function (index) {
                            var skuStr = $('.sum_' + index).val();
                            var skuCount = parseInt(skuStr);
                            var prize = $(this).find('.goods_prize_' + index).val();
                            Args.totalCount += skuCount;
                            Args.totalMoney += skuCount * prize;
                        });
                    } else {
                        $('input[type=checkbox]').prop('checked', false);
                        $("#delGoods").hide();
                        Args.totalCount = 0;
                        Args.totalMoney = 0;
                    }

                    setTotal(Args.totalMoney, Args.totalCount);
                });

                /** 选中一个 **/
                window.selectOne = function (thi, evt, money, index) {
                    var skuStr = $('.sum_' + index).val();
                    var skuCount = parseInt(skuStr);

                    if (!$(thi).is(":checked")) {
                        if ($(".selectOne:checked").length == 0) {
                            $(".selectAll").prop('checked', false);
                            $("#delGoods").hide();
                        }

                        Args.totalMoney -= skuCount * money;
                        Args.totalCount -= skuCount;
                    } else {
                        $("#delGoods").show();
                        if ($(".selectOne:checked").length == $(".selectOne").length) {
                            $(".selectAll").prop('checked', true);
                        }

                        Args.totalMoney += skuCount * money;
                        Args.totalCount += skuCount;
                    }

                    setTotal(Args.totalMoney, Args.totalCount);
                }

                /** 总价 **/
                window.changeSum = function (thi, evt, type, money, index) {
                    var skuStr = $('.sum_' + index).val();
                    var skuCount = parseInt(skuStr);

                    if (skuCount == 1 && '-' == type) return; //不能少于1
                    if (skuCount == 200 && '+' == type) return; //不能大于100

                    skuCount += (type == '+' ? 1 : -1);
                    $('.sum_' + index).val(skuCount);

                    if ($(".select_" + index + ":checked").length > 0) {
                        if (type == '+') {
                            Args.totalMoney += money;
                            Args.totalCount += 1;
                        } else {
                            Args.totalMoney -= money;
                            Args.totalCount -= 1;
                        }

                        setTotal(Args.totalMoney, Args.totalCount);
                    }
                }

                var setTotal = function (totalMoney, totalCount) {
                    var content =
                            '<p>总计<span class="price-total ng-binding">￥' + totalMoney.toFixed(2) + '</span></p>' +
                            '<p class="ng-binding">(共' + totalCount + '件，不含运费)</p>';
                    $('#price-des').html(content);
                }
            });
        });
    </script>
</body>
</html>