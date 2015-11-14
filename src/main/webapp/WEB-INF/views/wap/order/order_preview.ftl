<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <base href="${base}/"/>
    <title>支付--米所思商城</title>
    <meta charset="utf-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta content="width=device-width, initial-scale=1.0, maximum-scale=1.0,  user-scalable=no" name="viewport"/>
    <meta content="yes" name="apple-mobile-web-app-capable"/>
    <meta content="black" name=" apple-mobile-web-app-status-bar-style"/>
    <meta content="telephone=no" name="format-detection"/>
    <meta http-equiv="Cache-Control" content="no-cache"/>

    <link href="assets/wap/css/common/reset.css" rel="stylesheet" type="text/css"/>
    <link href="assets/wap/css/common/common.css" rel="stylesheet" type="text/css"/>

    <link href="assets/wap/css/order/order_preview.css" rel="stylesheet">
</head>
<body>
    <div class="container ordercreate2">
        <section data-role="body" class="body">
            <form id="orderSubmit" action="wap/order/submit" method="post">
                <div class="section-address">
                    <input type="hidden" name="coadId" value="${(consigneesAddress.coadId)!}">
                    <div id="wrap-address">
                        <a href="wap/payment/consigneesAddress/choose" class="tbox arrow" onclick="myChoice(this, event, &#39;address&#39;);">
                            <div>
                                <span class="icon-wrap icon-address">&nbsp;</span>
                            </div>
                            <div>
                                <p>
                                    <span><label>收货人：</label>${(consigneesAddress.coadName)!}</span>
                                    <span class="fr">${(consigneesAddress.coadPhone)!}</span>
                                </p>
                                <p>${(consigneesAddress.coadDetailedAddress)!}</p>
                            </div>
                        </a>
                    </div>
                </div>

                <#assign allIndex = 0 />
                <div class="section-detail" style="margin-bottom: 8px;">
                    <div>
                        <header>
                            <p>
                                <label class="h8">购物清单</label>
                                <label class="label-amount-goods fr">共${orderItem.totalCount}件 ￥${orderItem.totalPrice?string("0.00")}</label>
                            </p>
                        </header>
                        <ul class="list-goods">
                            <#list orderItem.shoppingCartItems as shoppingCartItem>
                                <input type="hidden" name="shoppingCartItems[${allIndex}].gpstId" value="${(shoppingCartItem.gpstId)!}">
                                <input type="hidden" name="shoppingCartItems[${allIndex}].count" value="${shoppingCartItem.count}">
                                <#assign allIndex = 1 + allIndex />
                                <li>
                                    <a href="javascript:;" class="tbox">
                                        <div>
                                            <span class="img-wrap">
                                                <img src="${(shoppingCartItem.goodPicture)!}">
                                            </span>
                                        </div>
                                        <div>
                                            <p class="title">${(shoppingCartItem.goodName)!}</p>
                                            <p class="price">￥${shoppingCartItem.goodPrice?string("0.00")}</p>
                                            <p class="">数量${shoppingCartItem.count}</p>
                                        </div>
                                    </a>
                                </li>
                            </#list>
                        </ul>
                        <header class="header-transport">
                            <a href="javascript:;">
                                <label class="h8">运送方式</label>
                                <select name="shippingMethod" class="fr" style="margin-top: 13px;border: none;">
                                    <#if orderItem.transportationExpensesResult.totalExpressExpenses??>
                                        <option value="0">快递￥${orderItem.transportationExpensesResult.totalExpressExpenses?string("0.00")}</option>
                                    </#if>
                                    <#if orderItem.transportationExpensesResult.totalEmsExpenses??>
                                        <option value="1">EMS￥${orderItem.transportationExpensesResult.totalEmsExpenses?string("0.00")}</option>
                                    </#if>
                                    <#if orderItem.transportationExpensesResult.totalPostExpenses??>
                                        <option value="2">平邮￥${orderItem.transportationExpensesResult.totalPostExpenses?string("0.00")}</option>
                                    </#if>
                                    <#if orderItem.transportationExpensesResult.totalMixExpenses??>
                                        <option value="3">运费￥${orderItem.transportationExpensesResult.totalMixExpenses?string("0.00")}</option>
                                    </#if>
                                </select>
                            </a>
                        </header>
                    </div>
                </div>

                <div class="section-detail">
                    <div>
                        <ul id="list-transport" class="list-transport"></ul>
                        <div class="mark-msg">
                            <input type="text" name="shinRemark" id="remark" placeholder="备注" maxlength="200">
                        </div>
                        <header class="header-pay">
                            <a href="javascript:;">
                                <label class="h8">支付方式</label>
                            </a>
                        </header>
                        <ul id="pay-list" class="pay-list on">
                            <li>
                                <label class="tbox">
                                    <div>
                                        <span class="pay-weipay">&nbsp;</span>
                                    </div>
                                    <div>
                                        <p>微信支付</p>
                                        <p>推荐已开通微信支付的用户使用</p>
                                    </div>
                                    <div>
                                        <input type="radio" name="paytype" value="24492" class="radio" checked="checked" disabled>
                                    </div>
                                </label>
                            </li>
                        </ul>
                    </div>
                </div>
                <div>
                    <div id="order-submit-1" class="section-price-and-pay align-center order-submit-1">
                        <p>总共支付 <span class="label-amount-total"></span></p>
                        <#if orderItem.transportationExpensesResult.totalExpressExpenses??>
                            <p class="p-delivery-fee">（含运费￥${orderItem.transportationExpensesResult.totalExpressExpenses?string("0.00")}）</p>
                        <#elseif orderItem.transportationExpensesResult.totalEmsExpenses??>
                            <p class="p-delivery-fee">（含运费￥${orderItem.transportationExpensesResult.totalEmsExpenses?string("0.00")}）</p>
                        <#elseif orderItem.transportationExpensesResult.totalPostExpenses??>
                            <p class="p-delivery-fee">（含运费￥${orderItem.transportationExpensesResult.totalPostExpenses?string("0.00")}）</p>
                        <#elseif orderItem.transportationExpensesResult.totalMixExpenses??>
                            <p class="p-delivery-fee">（含运费￥${orderItem.transportationExpensesResult.totalMixExpenses?string("0.00")}）</p>
                        </#if>
                        <p>
                            <span>
                                <input type="submit" href="javascript:;" class="btn red" value="提交订单">
                            </span>
                        </p>
                    </div>
                </div>
            </form>
        </section>
    </div>

    <#-- 下面写需要引入的js模块，fastclick是一个解决移动端点击延迟和点透bug的插件，按照页面需要引入；如果页面不需要使用js模块，则把require.js也删掉  -->
    <#include "../../common/_js/require_config_wap.ftl" />
    <script type="text/javascript" src="assets/requirejs/require.js"></script>
    <script type="text/javascript">
        var Args = {
            shippingMethod : 0,
            totalPrice : parseFloat(${(orderItem.totalPrice?string("0.00"))!0.00}),
            totalExpressExpenses : parseFloat(${(orderItem.transportationExpensesResult.totalExpressExpenses?string("0.00"))!0.00}),
            totalEmsExpenses : parseFloat(${(orderItem.transportationExpensesResult.totalEmsExpenses?string("0.00"))!0.00}),
            totalPostExpenses : parseFloat(${(orderItem.transportationExpensesResult.totalPostExpenses?string("0.00"))!0.00}),
            totalMixExpenses : parseFloat(${(orderItem.transportationExpensesResult.totalMixExpenses?string("0.00"))!0.00})
        };

        var ele = (function () {
            function Ele() {

                Object.defineProperty(this, "shippingMethod", {
                    get: function () {
                        return Args.shippingMethod;
                    },
                    set: function (v) {
                        Args.shippingMethod = v;

                        var totalAmount = 0.00;
                        var expenses = 0.00;
                        if (v == 0) {
                            totalAmount = Args.totalPrice + Args.totalExpressExpenses;
                            expenses = Args.totalExpressExpenses;
                        } else if (v == 1) {
                            totalAmount = Args.totalPrice + Args.totalEmsExpenses;
                            expenses = Args.totalEmsExpenses;
                        } else if (v == 2) {
                            totalAmount = Args.totalPrice + Args.totalPostExpenses;
                            expenses = Args.totalPostExpenses;
                        } else if (v == 3) {
                            totalAmount = Args.totalPrice + Args.totalMixExpenses;
                            expenses = Args.totalMixExpenses;
                        }

                        $('.label-amount-total').html('￥' + totalAmount.toFixed(2));
                        $('.p-delivery-fee').html('（含运费￥' + expenses.toFixed(2) + '）');
                    }
                });
            }
            return new Ele();
        })();

        requirejs.config(mssRequireConfig);
        require(['fastclick'], function (FastClick) {
            $(document).ready(function () {
                FastClick.attach(document.body);

                ele.shippingMethod = parseInt($('select[name="shippingMethod"]').val());

                $('select[name="shippingMethod"]').on('change', function () {
                    ele.shippingMethod = parseInt($(this).val());
                });
            });
        });
    </script>
</body>
</html>