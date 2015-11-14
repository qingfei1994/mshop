<#-- 商品其他信息 -->
<div class="portlet">
    <div class="portlet-title">
        <div class="caption">其他信息</div>
    </div>
    <div class="portlet-body form">
        <!-- BEGIN FORM-->
        <div class="form-horizontal form-row-seperated">
            <div class="form-body">
                <div class="form-group">
                    <label class="control-label col-md-2">商品限购</label>
                    <div class="col-md-10">
                        <div class="radio-list">
                        <#if isEdit??>
                            <label class="radio-inline">
                                <input name="goodPurchaseLimit" type="radio" value="0" <#if goods.goodPurchaseLimit == 0>checked</#if>>不限购
                            </label>
                            <label class="radio-inline">
                                <input name="goodPurchaseLimit" type="radio" value="1" <#if goods.goodPurchaseLimit == 1>checked</#if>>限购
                            </label>
                        <#else>
                            <label class="radio-inline">
                                <input name="goodPurchaseLimit" type="radio" value="0" checked>不限购
                            </label>
                            <label class="radio-inline">
                                <input name="goodPurchaseLimit" type="radio" value="1">限购
                            </label>
                        </#if>
                        </div>
                    </div>
                </div>
            </div>
            <div class="form-body">
                <div class="form-group last">
                    <label class="control-label col-md-2">会员优惠</label>
                    <div class="col-md-10">
                        <div class="checkbox-list">
                            <label class="checkbox-inline">
                            <#if isEdit??>
                                <span>
                                    <input id="goodMemberPrivilege" type="checkbox" <#if goods.goodMemberPrivilege == 1>checked</#if>>
                                    <input name="goodMemberPrivilege" type="hidden" value="${goods.goodMemberPrivilege}">
                                </span>会员价格
                            <#else>
                                <span>
                                    <input id="goodMemberPrivilege" type="checkbox">
                                    <input name="goodMemberPrivilege" type="hidden" value="0">
                                </span>会员价格
                            </#if>
                            </label>
                        </div>
                    </div>
                </div>
            </div>
            <div class="form-body">
                <div class="form-group">
                    <label class="control-label col-md-2">库存计算方式</label>
                    <div class="col-md-10">
                        <div class="radio-list">
                        <#if isEdit??>
                            <label class="radio-inline">
                                <input name="goodStockCalculateType" type="radio" value="0" <#if goods.goodStockCalculateType == 0>checked</#if>>下单减库存
                            </label>
                            <label class="radio-inline">
                                <input name="goodStockCalculateType" type="radio" value="1" <#if goods.goodStockCalculateType == 1>checked</#if>>支付减库存
                            </label>
                        <#else>
                            <label class="radio-inline">
                                <input name="goodStockCalculateType" type="radio" value="0" checked>下单减库存
                            </label>
                            <label class="radio-inline">
                                <input name="goodStockCalculateType" type="radio" value="1">支付减库存
                            </label>
                        </#if>
                        </div>
                    </div>
                </div>
            </div>
            <div class="form-body">
                <div class="form-group">
                    <label class="control-label col-md-2">上架时间</label>
                    <div class="col-md-10">
                        <div class="radio-list">
                        <#if isEdit??>
                            <label class="radio">
                                <input name="goodPutawayType" type="radio" value="0" <#if goods.goodPutawayType == 0>checked</#if>>立即上架 <span class="red">(上架后商品类目、商品属性及商品名称将不能再修改，请确认已填写无误)</span>
                            </label>
                            <label class="radio">
                                <input name="goodPutawayType" type="radio" value="1" <#if goods.goodPutawayType == 1>checked</#if>>暂不上架，放入仓库中
                            </label>
                        <#else>
                            <label class="radio">
                                <input name="goodPutawayType" type="radio" value="0" checked>立即上架 <span class="red">(上架后商品类目、商品属性及商品名称将不能再修改，请确认已填写无误)</span>
                            </label>
                            <label class="radio">
                                <input name="goodPutawayType" type="radio" value="1">暂不上架，放入仓库中
                            </label>
                        </#if>
                        </div>
                        <div class="inline-layout">
                            <div class="radio-list">
                            <#if isEdit??>
                                <label class="radio">
                                    <input name="goodPutawayType" type="radio" value="2" <#if goods.goodPutawayType == 2>checked</#if>>设定商品上下架时间
                                </label>
                            <#else>
                                <label class="radio">
                                    <input name="goodPutawayType" type="radio" value="2">设定商品上下架时间
                                </label>
                            </#if>
                            </div>
                            <div class="inline-layout put-away-time">
                                <span>上架</span>
                                <div class="input-group date form_datetime top right">
                                    <input name="goodPutawayTime" value="${(goods.goodPutawayTime?string("yyyy-MM-dd HH:mm"))!}" type="text" size="16" readonly="" class="form-control date-set">
                                    <span class="input-group-btn">
                                        <#--<button class="btn btn-default date-reset" type="button"><i class="icon-remove"></i></button>-->
                                        <button class="btn btn-default" type="button"><i class="icon-calendar"></i></button>
                                    </span>
                                </div>
                            </div>
                            <div class="inline-layout put-away-time last">
                                <span>下架</span>
                                <div class="input-group date form_datetime top right">
                                    <input name="goodEndPutawayTime" value="${(goods.goodEndPutawayTime?string("yyyy-MM-dd HH:mm"))!}" type="text" size="16" readonly="" class="form-control date-set">
                                    <span class="input-group-btn">
                                        <#--<button class="btn btn-default date-reset" type="button"><i class="icon-remove"></i></button>-->
                                        <button class="btn btn-default" type="button"><i class="icon-calendar"></i></button>
                                    </span>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="form-actions fluid">
                <div class="row">
                    <div class="col-md-12">
                        <div class="col-md-offset-2 col-md-10">
                        <#if isEdit??>
                            <button type="button" class="btn btn-success goods-ajax-submit" url="${base}/admin/goods/edit">保存</button>
                        <#else>
                            <button type="button" class="btn btn-success goods-ajax-submit" url="${base}/admin/goods/add">保存</button>
                        </#if>
                            <button type="button" url="${pagingPath!}" class="btn btn-default ajaxify">取消</button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- END FORM-->
    </div>
</div>