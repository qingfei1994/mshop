<!-- BEGIN JAVASCRIPTS -->
<script type="text/javascript">
require(['admin/goods/init', 'ueditor', "modules/upload/init", "modules/form/init",
    'select2_zh_cn', 'bootstrap_datetimepicker_zh_cn'], function(Goods, Ueditor, Upload, Form) {
    $(document).ready(function() {
        Goods.init();
        Ueditor.init();
        Upload.init();
        App.initPlugins();
        Form.init();
    });
});
</script>
<!-- END JAVASCRIPTS -->

<!-- BEGIN CSS -->
<link href="${base}/assets/admin/css/style-conquer.css" rel="stylesheet" type="text/css"/>
<link href="${base}/assets/plugins/select2/select2_conquer.css" rel="stylesheet" type="text/css" />
<link href="${base}/assets/plugins/plupload/uploadify.min.css" rel="stylesheet" type="text/css"/>
<link href="${base}/assets/plugins/bootstrap-datetimepicker/css/datetimepicker.css" rel="stylesheet" type="text/css"/>
<!-- END CSS -->

<div class="row detail-content goods">
    <div class="col-md-12">
        <div class="content-header">
            <div class="title">商品详情</div>
        </div>
        <form class="ajax-form valid">
            <input name="goodId" type="hidden" value="${(goods.goodId)!}" />
            <input name="gpstId" type="hidden" value="${(goodsPriceStock.gpstId)!}" />
            <input name="gpstSales" type="hidden" value="${(goodsPriceStock.gpstSales)!}" />
            <div class="portlet">
                <div class="portlet-title">
                    <div class="caption">基本信息</div>
                </div>
                <div class="portlet-body form">
                    <!-- BEGIN FORM-->
                    <div class="form-horizontal form-row-seperated">
                        <div class="form-body">
                            <div class="form-group">
                                <label class="control-label col-md-2"><span class="required">*</span> 商品名称</label>
                                <div class="col-md-8">
                                    <input name="goodName" value="${(goods.goodName)!}" type="text" class="form-control" required="true" maxlength="100">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="control-label col-md-2">商品规格</label>
                                <div class="col-md-10">
                                    <div class="radio-list">
                                        <label class="radio-inline">
                                            <input type="radio" name="specification" value="1" checked>统一规格
                                        </label>
                                        <label class="radio-inline">
                                            <input type="radio" name="specification" value="2" disabled>多规格
                                        </label>
                                    </div>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="control-label col-md-2">商家编码</label>
                                <div class="col-md-2">
                                    <input name="gpstCode" value="${(goodsPriceStock.gpstCode)!}" type="text" class="form-control" maxlength="30">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="control-label col-md-2"><span class="required">*</span> 销售价</label>
                                <div class="col-md-2">
                                    <input name="gpstSalesPrice" value="${(goodsPriceStock.gpstSalesPrice)!}" type="number" class="form-control" required="true" range="0,9999999">
                                </div>
                                <div class="col-md-8">
                                    <span class="desc">注：商品折扣=销售价/市场价X会员等级折扣率</span>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="control-label col-md-2">市场价</label>
                                <div class="col-md-2">
                                    <input name="gpstMarketPrice" value="${(goodsPriceStock.gpstMarketPrice)!}" type="number" class="form-control" range="0,9999999">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="control-label col-md-2"><span class="required">*</span> 库存</label>
                                <div class="col-md-2">
                                    <input name="gpstStock" value="${(goodsPriceStock.gpstStock)!}" type="digits" class="form-control" required="true" range="0,99999999999">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="control-label col-md-2">重量</label>
                                <div class="col-md-2 table-layout">
                                    <input name="gpstWeight" value="${(goodsPriceStock.gpstWeight)!}" type="number" class="form-control">
                                    <span class="input-group-addon">kg</span>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="control-label col-md-2">体积</label>
                                <div class="col-md-2 table-layout">
                                    <input name="gpstVolume" value="${(goodsPriceStock.gpstVolume)!}" type="number" class="form-control">
                                    <span class="input-group-addon">m³</span>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="control-label col-md-2"><span class="required">*</span> 商品图片</label>
                                <div class="col-md-10">
                                    <div>
                                        <button type="button" class="btn btn-default plupload-image" data-width="640" data-height="569" data-name="images">商品图片</button>
                                        <span class="inline-text desc">建议尺寸为640像素x569像素，大小不超过500kb 默认显示第1张图片，最多20张 <#--(可拖拽图片调整显示顺序)--></span>
                                    </div>
                                    <#include "../../common/_template/show_image.ftl" />
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="control-label col-md-2">商品详情</label>
                                <div class="col-md-10">
                                    <textarea name="goodDetails" class="ueditor">${(goods.goodDetails)!}</textarea>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="control-label col-md-2"><span class="required">*</span> 商品分组</label>
                                <div class="col-md-3">
                                    <select name="goclIds" class="form-control select2me" multiple required="true">
                                        <option value=""></option>
                                        <#include "goods_classification_options.ftl" />
                                    </select>
                                </div>
                            </div>
                            <div class="form-group last">
                                <label class="control-label col-md-2">商品标签</label>
                                <div class="col-md-3">
                                    <select name="golaIds" class="form-control select2me" multiple>
                                        <option value=""></option>
                                        <#list goodsLabels as goodsLabel>
                                            <option value="${goodsLabel.golaId}"
                                                <#if goodsLabelList??><#list goodsLabelList as gola>
                                                    <#if gola.golaId == goodsLabel.golaId>selected</#if>
                                                </#list></#if>>${goodsLabel.golaName}</option>
                                        </#list>
                                    </select>
                                </div>
                            </div>
                        </div>
                    </div>
                    <!-- END FORM-->
                </div>
            </div>
            <#include "goods_logistics_information.ftl" />
            <#include "goods_other_information.ftl" />
            <input type="hidden" name="pagingPath" value="${pagingPath!}" />
        </form>
    </div>
</div>

