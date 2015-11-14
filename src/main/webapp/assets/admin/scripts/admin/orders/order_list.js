define(function () {

    var initDownload = function () {
        $(document).off('click', '[data-toggle="download"]').on('click', '[data-toggle="download"]', function () {
            var $this = $(this),
                set = $this.data('set'),
                action = $this.data('action'),
                type = $this.data('type') || 'get',
                daterange = $('.reportrange span').html();

            if (daterange) {
                daterange = daterange.split('-');
                var fromdate = new Date(daterange[0].replace('-', '/').replace('-', '/')),
                    todate = new Date(daterange[1].replace('-', '/').replace('-', '/')),
                    days = (todate - fromdate) / 864e5;
                if (days < 31) {
                    var data = [];
                    data.push({
                        name: 'fromdate',
                        value: fromdate
                    });
                    data.push({
                        name: 'todate',
                        value: todate
                    });
                    openWindowWithPost(action, "_blank", data, type);
                } else {
                    App.toastError('每次最多只能导出31天内的订单数据');
                }
            } else {
                App.toastError('请选择日期每次最多只能导出31天内的订单数据');
            }
        });
    };

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

    return {
        init: function () {
            initDownload();
        }
    }
});