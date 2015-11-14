<!DOCTYPE html>
<html lang="zh-CN" xmlns="http://xmlns.jcp.org/jsf/passthrough">
<head>
    <title>商品列表--米所思商城</title>
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

    <link href="${base}/assets/wap/css/common/wicons.css" rel="stylesheet">
    <link href="${base}/assets/wap/css/goods/glist.css" rel="stylesheet">
    <link href="${base}/assets/wap/css/common/widget_menu.css" rel="stylesheet">

    <link rel="stylesheet" type="text/css" href="${base}/assets/wap/gmu/css/loading.default.css" />
    <link rel="stylesheet" type="text/css" href="${base}/assets/wap/gmu/css/widget/refresh/refresh.default.css" />
    <link href="assets/wap/gmu/css/widget/gotop/gotop.css" rel="stylesheet" type="text/css"/>
</head>
<body>
<div class="content">
<div class="container glist">
    <div class="rotate-body">
        <header data-role="header">
            <div class="glist-header">
                <ul class="tbox">
                    <li>
                        <span>
                            <a id="push-menu" href="javascript:;" class="icon-list" onclick="menuNav(0);"></a>
                        </span>
                    </li>
                    <li>
                        <label onclick="menuNav(1);">默认排序</label>
                    </li>
                    <li>
                        <span>
                            <a href="javascript:;" class="icon-one-grid " onclick="menuNav(2, 0);">
                            </a><a href="javascript:;" class="icon-more-grid on" onclick="menuNav(3, 1);"></a>
                        </span>
                    </li>
                </ul>
            </div>
            <div id="sort-div" class="sort-div">
                <ul>
                    <li>
                        <a href="javascript:;" onclick="classifyList(Args.goclId, 'good_sales', 'desc')">销量从高到低</a>
                    </li>
                    <li>
                        <a href="javascript:;" onclick="classifyList(Args.goclId, 'good_price', 'desc')">价格从高到低</a>
                    </li>
                    <li>
                        <a href="javascript:;" onclick="classifyList(Args.goclId, 'good_price', 'asc')">价格从低到高</a>
                    </li>
                    <li>
                        <a href="javascript:;" onclick="classifyList(Args.goclId, 'good_evaluate', 'desc')">评价从高到低</a>
                    </li>
                </ul>
                <div class="sort-div-mask" ontouchstart="menuNav(1);event.stopPropagation();return false;" onclick="menuNav(1);"></div>
            </div>
        </header>
        <section data-role="body" class="body">
            <div class="ui-refresh">
                <ul id="glist-ul" class="glist-ul glist-grid data-list">
                    <!--商品列表-->
                </ul>
                <div class="ui-refresh-down"></div>
            </div>
        </section>
        <footer data-role="footer">
            <#include "../../common/_template/wap/menubar.ftl" />
        </footer>
    </div>
</div>
</div>
<div id="mask" class="mask mask-animate" onclick="menuNav(0);"></div>

<!--分类导航菜单-->
<div data-role="widget" data-widget="sideBar" class="sideBar" id="sideBar">
    <div class="widget-wrap">
        <ul>
            <#list goodsClassificationList as goodsClassification>
                <li onclick="this.classList.toggle(&#39;on&#39;);event.stopPropagation();" class="on">
                    <a href="javascript:;" class="level1">${(goodsClassification.goclName)!}</a>
                </li>
                <ol>
                    <#if goodsClassification.goodsClassifications?? && goodsClassification.goodsClassifications?size gt 0 >
                        <#list goodsClassification.goodsClassifications as sonGoodsClassification>
                            <li onclick="classifyList('${sonGoodsClassification.goclId}')">
                                <a href="javascript:;">${(sonGoodsClassification.goclName)}</a>
                            </li>
                        </#list>
                    </#if>
                </ol>
            </#list>
        </ul>
    </div>
</div>

<div id="gotop"></div>
<#-- 下面写需要引入的js模块，fastclick是一个解决移动端点击延迟和点透bug的插件，按照页面需要引入；如果页面不需要使用js模块，则把require.js也删掉  -->
<#include "../../common/_js/require_config_wap.ftl" />
<script type="text/javascript" src="assets/requirejs/require.js"></script>
<!-- 前端模板部分 -->
<script type="text/template"  id="GoodsList">
    <%for(var i=0;i<list.length;i++){%>
    <li>
        <a href="wap/goods/<%=list[i].good_id%>/detail" onclick="dataCache.recordInfo(this, event, 0);">
            <div>
                <span class="img-wrap">
                    <img src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAAEAAAABCAYAAAAfFcSJAAAAC0lEQVQIW2NkAAIAAAoAAggA9GkAAAAASUVORK5CYII="
                    style="background-image:url(<%=list[i].good_first_picture%>);">
                </span>
            </div>
            <div>
                <label class="title"><%=list[i].good_name%></label>
                <p>
                    <label class="price1">售价：￥<%=list[i].gpst_sales_price%></label>
                    <span class="icon-fav <% if (list[i].wein_id) { %>on<% } %>"
                          onclick="collectGood(event,'<%=list[i].good_id%>');event.stopPropagation();return false;">&nbsp;</span>
                </p>
            </div>
            <span name="goodsdetailspan" class="tag"><%=list[i].gola_name%></span>
        </a>
    </li>
    <%}%>
</script>
<!-- 前端模板结束 -->
<script type="text/javascript">
    /** 页面参数 **/
    var Args = {
        goclId : "",
        orderStr : "",
        orderType : "",
        pagination: {
            pageNo: 1,
            pageSize: 10
        },
        urls : {
            loadDataUrl : "wap/goods/list/data",
            collectUrl : "wap/usercenter/collect/",
            goodsListUrl : "wap/goods/list"
        }
    }

    /** 头部菜单模块 **/
    window.menuNav = function(type) {
        switch(type) {
            case 0 :
                var container = document.querySelectorAll(".container")[0];
                if(container.getAttribute("style")){
                    window.myScroll&&myScroll.sleep(false);
                    container.removeAttribute("style");
                    document.getElementById("sideBar").classList.remove("on");
                    setTimeout(function(){
                        container.classList.remove("animate");
                        $("#mask").removeClass("mask-move");
                        $(".rotate-body").removeAttr("style");
                    }, 350);
                    $("#mask").hide();
                } else {
                    window.myScroll&&myScroll.sleep(true);
                    var height = $(window).height();
                    container.classList.add("animate");
                    $("#mask").show();
                    container.setAttribute("style", "height:"+height+"px;");
                    $(".rotate-body").height(height);
                    document.getElementById("sideBar").classList.add("on");
                    $("#mask").addClass("mask-move");
                }
                break;
            case 1 :
                $("#sort-div").toggleClass("on");
                break;
            case 2 :
                $("#glist-ul").addClass("glist-big");
                $(".icon-one-grid, .icon-more-grid").removeClass("on").eq(arguments[1]).addClass("on");
                break;
            case 3 :
                $("#glist-ul").removeClass("glist-big");
                $(".icon-one-grid, .icon-more-grid").removeClass("on").eq(arguments[1]).addClass("on");
                break;
            default:
                break;
        }
    };

    /** 收藏模块 **/
    window.collectGood = function (evt, goodId) {
        evt.preventDefault();
        $.getJSON(Args.urls.collectUrl+goodId, function(response){
            if (response) {
                evt.target.classList.toggle("on");
            }
        })
    };

    /** 分类模块(保存分类数据) **/
    window.classifyList = function (goclId, orderStr, orderType) {
        if (goclId) {
            localStorage.goclId = goclId;
        }
        if (orderStr) {
            localStorage.orderStr = orderStr;
        }
        if (orderType) {
            localStorage.orderType = orderType;
        }
        window.location.href = Args.urls.goodsListUrl;
    };

    /** 加载页面列表数据 **/
    var loadData = function(me) {
        $.ajax({
            type: 'GET',
            url: Args.urls.loadDataUrl,
            data: {
                goclId: Args.goclId,
                orderStr : Args.orderStr,
                orderType : Args.orderType,
                pageNo : Args.pagination.pageNo ,
                pageSize : Args.pagination.pageSize
            },
            dataType: 'json',
            context: $('body'),
            success: function(data){
                var $list = $('.data-list'),
                        html = (function (data) {      //数据渲染
                            return baidu.template('GoodsList', {list: data.list});
                        })(data);
                $list['append'](html);
                me.afterDataLoading();    //数据加载完成后改变状态

                if (data.pageNo >= Math.ceil(data.totalCount / data.pageSize)) {
                    me.disable('down');
                }

                Args.pagination.pageNo = data.pageNo+1;
                Args.pagination.pageSize = data.pageSize;
            },
            error: function(xhr, type){
                alert('Ajax error!');
                me.afterDataLoading();
            }
        })
    };

    /** 初始化页面参数 **/
    var initArgs = function() {
        if (localStorage.goclId) {
            Args.goclId = localStorage.goclId;
        }
        if (localStorage.orderStr) {
            Args.orderStr = localStorage.orderStr;
        }
        if (localStorage.orderType) {
            Args.orderType = localStorage.orderType;
        }
        localStorage.clear();
    };

    /** 页面js **/
    requirejs.config(mssRequireConfig);
    require(['fastclick','refresh','refresh_lite', 'gotop', 'baidu_template'], function(FastClick){
        $(document).ready(function() {
            FastClick.attach(document.body);

            initArgs();

            $("#gotop").gotop();

            $('.ui-refresh').refresh({
                load: function (dir, type) {
                    loadData(this);
                }
            });

            $('.ui-refresh-down').trigger('click');
        });
    });
</script>
</body>
</html>