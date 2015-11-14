<#-- 商品物流信息 -->
<div class="portlet">
    <div class="portlet-title">
        <div class="caption">物流信息</div>
    </div>
    <div class="portlet-body form">
        <!-- BEGIN FORM-->
        <div class="form-horizontal form-row-seperated">
            <div class="form-body">
                <div class="form-group last">
                    <label class="control-label col-md-2">运费计算方式</label>
                    <div class="col-md-10">
                        <div class="radio-list">
                            <#if isEdit??>
                                <label class="radio-inline">
                                    <input name="goodAssumeExpenses" type="radio" value="0"
                                           <#if (goods.goodAssumeExpenses)?default(0) == 0>checked</#if>>卖家承担运费
                                </label>
                                <label class="radio-inline">
                                    <input name="goodAssumeExpenses" type="radio" value="1"
                                           <#if (goods.goodAssumeExpenses)?default(0) == 1>checked</#if>>粉丝承担运费
                                </label>
                            <#else>
                                <label class="radio-inline">
                                    <input name="goodAssumeExpenses" type="radio" value="0" checked>卖家承担运费
                                </label>
                                <label class="radio-inline">
                                    <input name="goodAssumeExpenses" type="radio" value="1">粉丝承担运费
                                </label>
                            </#if>
                        </div>
                        <div class="inner-form express"
                             <#if !isEdit?exists || goods.goodAssumeExpenses?default(0) == 0>style="display: none;"</#if>>
                            <div class="form-group">
                                <div class="radio-list">
                                    <#if isEdit??>
                                        <label class="radio-inline">
                                            <input name="freightPriceCount" type="radio" value="0"
                                                   <#if !((goods.teteId)?exists)>checked</#if>>统一运费
                                        </label>
                                        <label class="radio-inline">
                                            <input name="freightPriceCount" type="radio" value="1"
                                                   <#if (goods.teteId)?exists>checked</#if>>使用运费模板
                                        </label>
                                    <#else>
                                        <label class="radio-inline">
                                            <input name="freightPriceCount" type="radio" value="0" checked>统一运费
                                        </label>
                                        <label class="radio-inline">
                                            <input name="freightPriceCount" type="radio" value="1">使用运费模板
                                        </label>
                                    </#if>
                                </div>
                            </div>
                            <div class="form-group unified-express" <#if (goods.teteId)?exists>style="display: none;"</#if>>
                                <#if isEdit??>
                                    <div class="checkbox-list inline-layout">
                                        <label class="checkbox-inline">
                                            <input id="guexExpressPrice" type="checkbox"
                                                    <#if (goodsUnificationExpenses.guexExpressPrice)??>checked</#if>>快递
                                        </label>
                                        <div>
                                            <div class="table-layout">
                                                <input name="guexExpressPrice" type="number" class="form-control"
                                                       <#if !((goodsUnificationExpenses.guexExpressPrice)?exists)>disabled</#if>
                                                       value="${(goodsUnificationExpenses.guexExpressPrice)!}">
                                                <span class="input-group-addon">元</span>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="checkbox-list inline-layout">
                                        <label class="checkbox-inline">
                                            <input id="guexEmsPrice" type="checkbox"
                                                   <#if (goodsUnificationExpenses.guexEmsPrice)??>checked</#if>>EMS
                                        </label>
                                        <div>
                                            <div class="table-layout">
                                                <input name="guexEmsPrice" type="number" class="form-control"
                                                       <#if !((goodsUnificationExpenses.guexEmsPrice)?exists)>disabled</#if>
                                                        value="${(goodsUnificationExpenses.guexEmsPrice)!}"/>
                                                <span class="input-group-addon">元</span>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="checkbox-list inline-layout">
                                        <label class="checkbox-inline">
                                            <input id="guexPostPrice" type="checkbox"
                                                   <#if (goodsUnificationExpenses.guexPostPrice)??>checked</#if>>平邮
                                        </label>
                                        <div>
                                            <div class="table-layout last">
                                                <input name="guexPostPrice" type="number" class="form-control"
                                                       <#if !((goodsUnificationExpenses.guexPostPrice)?exists)>disabled</#if>
                                                       value="${(goodsUnificationExpenses.guexPostPrice)!}">
                                                <span class="input-group-addon">元</span>
                                            </div>
                                        </div>
                                    </div>
                                <#else>
                                    <div class="checkbox-list inline-layout">
                                        <label class="checkbox-inline">
                                            <input id="guexExpressPrice" type="checkbox">快递
                                        </label>
                                        <div>
                                            <div class="table-layout">
                                                <input name="guexExpressPrice" type="number" class="form-control" disabled>
                                                <span class="input-group-addon">元</span>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="checkbox-list inline-layout">
                                        <label class="checkbox-inline">
                                            <input id="guexEmsPrice" type="checkbox">EMS
                                        </label>
                                        <div>
                                            <div class="table-layout">
                                                <input name="guexEmsPrice" type="number" class="form-control" disabled>
                                                <span class="input-group-addon">元</span>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="checkbox-list inline-layout">
                                        <label class="checkbox-inline">
                                            <input id="guexPostPrice" type="checkbox">平邮
                                        </label>
                                        <div>
                                            <div class="table-layout last">
                                                <input name="guexPostPrice" type="number" class="form-control" disabled>
                                                <span class="input-group-addon">元</span>
                                            </div>
                                        </div>
                                    </div>
                                </#if>
                            </div>
                            <div class="form-group expenses-template" <#if !((goods.teteId)?exists)>style="display: none;"</#if>>
                                <div><select name="teteId" class="form-control" required="true" <#if !((goods.teteId)?exists)>disabled</#if>>
                                    <option value="">请选择运费模板</option>
                                    <#if transportationExpensesTemplates??>
                                        <#list transportationExpensesTemplates as tete>
                                            <option value="${tete.teteId!}"
                                                    <#if (goods.teteId)?default(0) == tete.teteId>selected</#if>>${tete.teteName!}</option>
                                        </#list>
                                    </#if>
                                </select></div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- END FORM-->
    </div>
</div>