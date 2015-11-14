define(function() {
	"use strict";

    var handleGoodsTypeSelect = function($el) {
        var gotyId = $el.val();
        if ($.trim(gotyId) == '') {
            $($el).nextAll('.parent-type').remove();
            $('.goods-attribute-name-mark').prevAll('.goods-attribute-name').remove();
            return;
        }
        // 请求下级商品分类
        $.get(base + '/admin/goods/goodsTypes', {parentId: gotyId}, function(data) {
            $($el).nextAll('.parent-type').remove();
            $('.goods-attribute-name-mark').prevAll('.goods-attribute-name').remove();
            if (data && data.goodsTypes) {
                var goodsTypes = data.goodsTypes;
                var html = '<select name="gotyId" class="form-control parent-type margin-left10" required="true">';
                html += '<option value="">请选择</option>';
                for (var i in goodsTypes) {
                    var goty = goodsTypes[i];
                    html += '<option value="' + goty.gotyId + '">' + goty.gotyName + '</option>';
                }
                html += '</select>';
                $($el).removeAttr('name');
                $(html).insertAfter($el);
            } else {
                // 请求商品分类下的商品属性
                $.get(base + '/admin/goods/goodsAttributeNames', {gotyId: gotyId}, function(data) {
                    var $mark = $('.goods-attribute-name-mark');
                    if (data && data.goodsAttributeNames) {
                        var goodsAttributeNames = data.goodsAttributeNames;
                        var html = '<div class="form-group goods-attribute-name">';
                        for (var i in goodsAttributeNames) {
                            if (i != 0 && i % 3 == 0) {
                                html += '</div>';
                                html += '<div class="form-group goods-attribute-name">';
                            }

                            var gana = goodsAttributeNames[i];
                            html += '<div class="col-md-4">';
                            html += '<label class="control-label">' + gana.ganaName + '</label>';
                            html += '<select name="gavaIds" class="form-control">';
                            html += '<option value="">请选择</option>';
                            var goodsAttributeValues = gana.goodsAttributeValues;
                            for (var j in goodsAttributeValues) {
                                var gava = goodsAttributeValues[j];
                                html += '<option value="' + gava.gavaId + '">' + gava.gavaValue + '</option>';
                            }
                            html += '</select>';
                            html += '</div>';
                        }
                        html += '</div>';
                        $(html).insertBefore($mark);
                    }
                }, 'json');
            }
        }, 'json');
    };

    var handleModifyGoodsType = function() {
        var $gotyIdInput = $('input[name="gotyId"]');
        var gotyId = $gotyIdInput.val();
        $gotyIdInput.remove();
        $.ajax({
            type : 'GET',
            cache : false,
            url : base + '/admin/goods/goodsTypeTree',
            dataType : 'html',
            data: {
                gotyId: gotyId
            },
            success: function (res) {
                $('.goods-type-select').html(res);
            }
        });
    };

    // 物流信息
    var handleLogisticsInformation = function() {
        $('input[name="goodAssumeExpenses"]').on('change', function() {
            var val = $('input[name="goodAssumeExpenses"]:checked').val();
            if (val == 0) {
                $('.express').hide();
            } else {
                $('.express').show();
            }
        });

        $('input[name="freightPriceCount"]').on('change', function() {
            var val = $('input[name="freightPriceCount"]:checked').val();
            if (val == 0) {
                $('.unified-express').show();
                $('.expenses-template').hide();
                $('.expenses-template select[name="teteId"]').attr('disabled', true);
            } else {
                $('.unified-express').hide();
                $('.expenses-template').show();
                $('.expenses-template select[name="teteId"]').removeAttr('disabled');
            }
        });

        $('#guexExpressPrice').on('change', function() {
            var checked = $(this).is(':checked');
            if (checked) {
                $('input[name="guexExpressPrice"]').removeAttr('disabled');
            } else {
                $('input[name="guexExpressPrice"]').attr('disabled', true);
            }
        });

        $('#guexEmsPrice').on('change', function() {
            var checked = $(this).is(':checked');
            if (checked) {
                $('input[name="guexEmsPrice"]').removeAttr('disabled');
            } else {
                $('input[name="guexEmsPrice"]').attr('disabled', true);
            }
        });

        $('#guexPostPrice').on('change', function() {
            var checked = $(this).is(':checked');
            if (checked) {
                $('input[name="guexPostPrice"]').removeAttr('disabled');
            } else {
                $('input[name="guexPostPrice"]').attr('disabled', true);
            }
        });
    };

    var initData = function() {
        $(function() {
            var goodId = parseInt($('input[name="goodId"]').val());
            if (goodId > 0) {
                $.ajax({
                    type : 'GET',
                    cache : false,
                    url : base + '/admin/goods/goodsPictures',
                    dataType : 'html',
                    data: {
                        goodId: goodId
                    },
                    success: function (res) {
                        $('.image-show-list').html(res);
                    }
                });
            }
        });
    };

	var initEvent = function() {
        $(function() {
            // 修改商品分类
            $('.modify-goods-type').on('click', function() {
                handleModifyGoodsType();
            });

            // 下拉商品分类
            $('.goods-type-select').on('change', '.parent-type', function() {
                handleGoodsTypeSelect($(this));
            });

            $('.modify-attribute-value').on('click', function() {
                $('.goods-attribute-name select').removeAttr('disabled');
                $(this).remove();
            });

            handleLogisticsInformation();

            // 会员优惠
            $('#goodMemberPrivilege').on('change', function() {
                var checked = $(this).is(':checked');
                if (checked) {
                    $('input[name="goodMemberPrivilege"]').val(1);
                } else {
                    $('input[name="goodMemberPrivilege"]').val(0);
                }
            });

            $('.goods-ajax-submit').on('click', function (e) {
                e.preventDefault();
                $('.goods-attribute-name select').removeAttr('disabled');

                var imgCount = $('.image-show-list .show-item').length;
                if (imgCount < 1) {
                    App.toastError('至少上传一张商品图片');
                    $('.plupload-image').focus();
                    return;
                } else if (imgCount > 20) {
                    App.toastError('最多只能上传20张商品图片');
                    $('.plupload-image').focus();
                    return;
                }

                var url = $(this).attr("url");
                App.ajaxSubmit(url);
            });
        });
	};

    return {
        init: function () {
            initData();
            initEvent();
        }
    };
	
});