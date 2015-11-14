<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <title>收货地址</title>
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

    <link href="assets/wap/css/payment/consigneesaddress/consignees_address_choose.css" rel="stylesheet">
</head>
<body onselectstart="return true;" ondragstart="return false;">
<div data-role="container" class="container my_address">
    <div class="auto-height">
        <header data-role="header"></header>
        <section data-role="body" class="body">
            <div>
                <ul id="list_address" class="list_address on">
                    <li>
                        <a href="javascript:;">
                            <label class="tbox" data-default="默认地址">
                                <div>
                                    <span>
                                        <input type="radio" class="radio" name="my_address" value="14995" data-index="0">
                                    </span>
                                </div>
                                <div>
                                    <p>
                                        <label>姓名</label>&nbsp;薛映冰
                                    </p>
                                    <p>
                                        <label>电话</label>&nbsp;15989204349
                                    </p>
                                    <p>
                                        <label>地址</label>&nbsp;广东省 广州市 天河区 新欧西街
                                    </p>
                                </div>
                                <div>
                                    <p style="width:60px;">&nbsp;</p>
                                </div>
                            </label>
                            <div class="icon_edit" data-index="0"></div>
                        </a>
                    </li>
                    <li>
                        <a href="javascript:;">
                            <label class="tbox">
                                <div>
                                    <span>
                                        <input type="radio" class="radio" name="my_address" value="16004" data-index="1">
                                    </span>
                                </div>
                                <div>
                                    <p><label>姓名</label>&nbsp;欧阳明月 </p>
                                    <p><label>电话</label>&nbsp;15989204349 </p>
                                    <p><label>地址</label>&nbsp;广东省 广州市 天河区 柯木塱新欧西街 </p>
                                </div>
                                <div><p style="width:60px;">&nbsp;</p></div>
                            </label>
                            <div class="icon_edit" data-index="1"></div>
                        </a>
                    </li>
                </ul>
            </div>
        </section>
    </div>
    <footer data-role="footer">
    <#include "../../../common/_template/wap/copyright.ftl" />
        <div class="div_section_btn footer_div_section_btn">
            <div class="widget_wrap">
                <ul>
                    <li>
                        <a href="http://378685.m.weimob.com/vshop/378685/UserCenter/NewAddressEdit?id=0" class="btn red"
                           id="btn_addAddress">新增收货地址</a>
                    </li>
                </ul>
            </div>
        </div>
    </footer>
</div>
</body>
</html>