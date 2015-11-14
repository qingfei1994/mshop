define(function () {
    var initPrintDistribution = function () {
        $(document).off("click", ".js_multy_dispatch").on("click", ".js_multy_dispatch", function () {

            var ids = '';
            $('.page-content-body .table .checkboxes').each(function () {
                if ($(this).is(':checked')) {
                    ids += $(this).val() + ',';
                }
            });
            if (ids.length > 0) {
                ids = ids.substring(0, ids.length - 1);
                //ids = '[' + ids + ']';
            } else {
                App.toastError('请勾选批量操作的记录');
                return;
            }

            var a = $(this),
                action = a.data("action"),
                type = a.data("type") || "post",
            //set = a.data("set"),
                data = [];
            data.push({
                name: "ids",
                value: ids
            }),
                openWindowWithPost(action, "_blank", data, type)
        })
    };

    //打开新窗口 url,_blank,data,type
    var openWindowWithPost = function (_url, _target, _data, _type) {
        var form = document.createElement("form");
        form.setAttribute("method", _type),
            form.setAttribute("action", _url),
            form.setAttribute("target", _target);
        for (var i in _data) {
            var input = document.createElement("input");
            input.type = "hidden",
                input.name = _data[i].name,
                input.value = _data[i].value,
                form.appendChild(input)
        }
        document.body.appendChild(form),
            form.submit(),
            document.body.removeChild(form);
    };

    var initToMark = function () {
        $(document).off('click', '.js_to_mark').on('click', '.js_to_mark', function () {
            var ids = '';
            $('.page-content-body .table .checkboxes').each(function () {
                if ($(this).is(':checked')) {
                    ids += $(this).val() + ',';
                }
            });
            if (ids.length > 0) {
                ids = ids.substring(0, ids.length - 1);
                //ids = '[' + ids + ']';
            } else {
                App.toastError('请勾选批量操作的记录');
                return;
            }

            var $body = $('body');
            var staticId = Util.getUniqueID("staticModal");
            var staticHtml = '<div class="modal fade" id="' + staticId + '" tabindex="-1" data-backdrop="static" data-keyboard="false" aria-hidden="true">' +
                '</div>';
            $body.append(staticHtml);
            var $staticModal = $("#" + staticId);
            App.blockUI($body, false);
            $staticModal.load($(this).attr("url") + "?ids=" + ids, function (response, status, xhr) {
                App.unblockUI($body);
                App.initAjax();
                if (status == "success") {
                    $staticModal.modal();
                } else {
                    App.toastError("请求失败");
                }
            });
        })
    };

    return {
        init: function () {

            initPrintDistribution();
            initToMark();
        }
    }
});