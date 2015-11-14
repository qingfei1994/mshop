define(['jquery', 'template', 'bootbox'], function ($, template, Bootbox) {
    Bootbox.setLocale("zh_CN");
    var way;

    Array.prototype.in_array = function (e) {
        for (i = 0; i < this.length; i++) {
            if (this[i] == e)
                return true;
        }
        return false;
    };

    /**
     * 运送方式复选框点击事件.
     */
    var initShippingMethod = function () {
        var delivery = $('[data-toggle="delivery"]');
        if (delivery.length > 0) {
            delivery.on('click', function () {
                //获得计价方式
                var pricingManner = $('[data-toggle="valuation"]:checked'),
                    tran = $('[data-toggle="delivery_list"]');

                var data = {},
                    _this = $(this);

                data.delivery = _this.data('delivery'),
                    data.name = _this.data('name'),
                    data.custom = null,
                    data.valuation = pricingManner.data(),
                    data.normal = {
                        delivery: data.delivery,
                        valid: pricingManner.data('valid'),
                        index: 0
                    };

                var deliveryItem = $('#delivery_item_' + data.delivery);
                if (this.checked) {
                    var html = template('delivery_setting_tpl', data);
                    deliveryItem.length > 0 ? deliveryItem.show() : tran.append(html),
                        deliveryItem.closest('div.panel');
                } else {
                    deliveryItem.hide();
                }
            });
        }
    };

    /**
     * 添加运费
     */
    var initAddArea = function () {
        var showDelivery = $('.show-delivery');
        if (showDelivery.length > 0) {
            showDelivery.on('click', '.js_add_area', function () {
                var _this = $(this),
                    panel = _this.closest('div.panel'),
                    table = $('table:first', panel),
                    trLength = $('tbody tr', panel).length,
                    delivery = panel.data('delivery')
                data = {
                    area: '<span class="js_no_area"> 未指定地区</span>'
                };

                data.normal = {
                    delivery: delivery,
                    index: trLength
                };

                var html = template('delivery_area_tpl', data);
                table.append(html);
            });
        }
    };

    /**
     * 计价方法点击事件
     */
    var initPricingManner = function () {
        //$.uniform.restore('[data-toggle="valuation"]');
        var pricingManner = $('[data-toggle="valuation"]');
        if (pricingManner.length > 0) {
            pricingManner.on('click', function (e) {
                var _this = $(this),
                    initCheck = function () {
                        _this.prop("checked", !0),
                            $('[data-toggle="delivery_list"]').empty().append('<label><label>'),
                            $('[data-toggle="delivery"]').prop("checked", !1),
                            App.updateUniform($('[data-toggle="delivery"]')),
                            way = _this.data("type");
                    },
                    exchange = function () {
                        e && e.preventDefault(),
                        way != _this.data("type") && Bootbox.confirm("切换计价方式后,所设置当前模版的运费信息将被清空,确认继续", function (a) {
                            a && initCheck();
                        });
                    };

                (0 == $('[data-toggle="delivery_list"]').find("table").length) ? initCheck() : exchange();
            });
        }
    };

    var initRemoveRow = function () {
        var $showDelivery = $('[data-toggle="delivery_list"]');
        if ($showDelivery.length > 0) {
            $showDelivery.on('click', '[data-toggle="removeRow"]', function () {
                var _this = $(this),
                    _tr = _this.closest('tr');
                _tr.remove();
            })
        }

    };

    /**
     * modal城市选择
     */
    var initCityCheckBox = function () {

        //存放选择的地区id
        var selectedMap = new Array(),
            selectNameMap = new Array(),
            index = 1,
            delivery = null,
            tableRow = null;

        //省份复选框change事件
        $(document).on('change', '.js_province', function () {
            var $this = $(this),
                dataId = $this.val(),
                cities = $('.js_cities');

            $.ajax({
                type: "GET",
                url: 'admin/logistics/transportation/childRegionalism',
                cache: false,
                data: {'regiId': dataId},
                dataType: 'json',
                success: function (res) {
                    cities.empty();
                    var length = res.length;
                    //省份复选框
                    if (length > 0) {
                        var html = '<label>' +
                            '<input type="checkbox" class="js_checkitem js_checkitem_all select_all" data-name="' + $this.find("option:selected").data('name') +
                            '" data-id="' + $this.find("option:selected").data('id') + '" >' + $this.find("option:selected").data('name') +
                            '</label>';
                        cities.append(html);
                    }

                    for (var i = 0; i < length; i++) {
                        var html = '<label>' +
                            '<input type="checkbox" class="js_checkitem" data-name="' + res[i].regiName + '" data-id="' + res[i].regiId + '" ';
                        if (selectedMap.in_array(res[i].regiId)) {
                            html = html + "checked"
                        }
                        html = html + '>' + res[i].regiName + '</label>';
                        cities.append(html);
                    }
                    App.initAjax();
                },
                error: function (res) {
                    App.toastError(App.toastrErrorTip);
                }
            });
        });

        //城市复选框change事件
        $(document).on('change', '.js_cities .js_checkitem', function () {
            var $this = $(this),
                dataId = $this.data('id'),
                dataName = $this.data('name'),
                isCheck = $this.prop('checked'),
                showCities = $('.js_selected_cities');

            if (isCheck) {
                selectedMap.push(dataId);
                selectNameMap.push(dataName);
            } else {
                selectedMap.splice($.inArray(dataId, selectedMap), 1);
                selectNameMap.splice($.inArray(dataName, selectNameMap), 1);
            }

            showCities.html(selectNameMap.join(','));
        });

        //
        $(document).on('click', '.modal-footer .sure-area', function () {
            var $modal = $('.modal.in'),
                $jsAreaItem = tableRow.nextAll('.js_area_item:first');

            $jsAreaItem.html(selectNameMap.join(','));
            $jsAreaItem.append('<input type="hidden" class="city-item" name="' + delivery + '[' + index + '].area" value="' + selectedMap.join(',') + '">');
            $jsAreaItem.append('<input type="hidden" class="city-name-item" value="' + selectNameMap.join(',') + '">');

            $modal.modal('hide');
        });

        $(document).on('click', '.js_edit_area', function () {
            var $this = $(this);

            index = $this.data('index'),
                delivery = $this.data('delivery'),
                tableRow = $this;

            var idItems = $this.nextAll('.js_area_item:first').children('.city-item:first').val();
            if (idItems) {
                selectedMap = idItems.split(',');
            } else {
                selectedMap = new Array();
            }

            var nameItems = $this.nextAll('.js_area_item:first').children('.city-name-item:first').val();
            if (nameItems) {
                selectNameMap = nameItems.split(',');
            } else {
                selectNameMap = new Array();
            }

        });
    };

    return {
        init: function () {
            initShippingMethod();
            initAddArea();
            initPricingManner();
            initRemoveRow();
            initCityCheckBox();
        }
    }
});