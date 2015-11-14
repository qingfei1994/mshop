define(['jquery_event_drag_live', 'plupload'], function (JQueryEventDragLive, plupload) {

    var _container = $(".js_container"),
        _index = 1,
        _background = $("#background"),
        _width = $("#width"),
        _height = $("#height");

    //拖动div
    var initDrag = function (a) {

        $('.item').drag("start", function (a, c) {
            var _this = $(this);
            c.width = _this.width(),
                c.height = _this.height(),
                c.limit = {
                    right: _container.innerWidth() - _this.outerWidth(),
                    bottom: _container.innerHeight() - _this.outerHeight()
                },
                c.isResize = $(a.target).hasClass("resize")
        }).drag(function (a, c) {
            var _this = $(this);
            c.isResize ? _this.css({
                width: Math.max(20, Math.min(c.width + c.deltaX, _container.innerWidth() - _this.position().left) - 2),
                height: Math.max(20, Math.min(c.height + c.deltaY, _container.innerHeight() - _this.position().top) - 2)
            }).find("textarea").blur() : _this.css({
                top: Math.min(c.limit.bottom, Math.max(0, c.offsetY)),
                left: Math.min(c.limit.right, Math.max(0, c.offsetX))
            })
        }, {
            relative: !0
        }).mousedown(function () {
            $(this).css("z-index", _index++)
        }).click(function () {
            var _this = $(this);
            _container.find("div.item").not(_this).removeClass("selected"),
                _this.toggleClass("selected")
        }),
            $(document).on("click", "div.js_container .close", function () {
                var item = $(this).closest(".item");
                item.remove(),
                    $("#" + item.data("id")).prop("checked", !1)
            })
    };


    var initEvent = function () {
        _container = $(".js_container");
        _index = 1;

        //添加
        $(".js_print_item").on("click", function () {
            var _this = $(this),
                str = (_this.val(), _this.attr("id")),
                isCheck = _this.prop("checked"),
                newStr = $("#" + "item_{0}".format(str));
            var k = '<div class="item" style="width: 171px; height: 41px;" data-id="{1}">\n<a  href="javascript:;" class="close" >&times;</a><pre>{0}</pre><div class="resize"></div></div>';
            if (isCheck) {
                if (0 == newStr.length) {
                    var g = $(k.format(_this.data("text"), str));
                    g.attr("id", "item_{0}".format(str)),
                        initDrag(g.appendTo(_container));
                }
            } else
                newStr.remove()
        });

        $('form button[type="submit"]').on("click", function (e) {
            e.preventDefault();

            var a = $("<div>{0}</div>".format(_container.html()));
            $(".close", a).remove();
            var c = $("div.item", a);
            return $.each(c, function (a, c) {
                var d = $(c);
                $("pre", d).text($("#" + d.data("id")).data("value"))
            }),
                a.find(".close").remove(),
                $("#content").val(a.html().trim()),
                App.ajaxSubmit($(this).attr("url"));
        });


        $('.item').drag("start", function (a, c) {
            var d = $(this);
            c.width = d.width(),
                c.height = d.height(),
                c.limit = {
                    right: _container.innerWidth() - d.outerWidth(),
                    bottom: _container.innerHeight() - d.outerHeight()
                },
                c.isResize = $(a.target).hasClass("resize")
        }).drag(function (a, c) {
            var d = $(this);
            c.isResize ? d.css({
                width: Math.max(20, Math.min(c.width + c.deltaX, _container.innerWidth() - d.position().left) - 2),
                height: Math.max(20, Math.min(c.height + c.deltaY, _container.innerHeight() - d.position().top) - 2)
            }).find("textarea").blur() : d.css({
                top: Math.min(c.limit.bottom, Math.max(0, c.offsetY)),
                left: Math.min(c.limit.right, Math.max(0, c.offsetX))
            })
        }, {
            relative: !0
        }).mousedown(function () {
            $(this).css("z-index", _index++)
        }).click(function () {
            var a = $(this);
            _container.find("div.item").not(a).removeClass("selected"),
                a.toggleClass("selected")
        }),
            $(document).on("click", "div.js_container .close", function () {
                var item = $(this).closest(".item");
                item.remove(),
                    $("#" + item.data("id")).prop("checked", !1),
                    //$("#" + item.data("id")).parent('span').attr('class', '');
                    App.updateUniform($("#" + item.data("id")));
            })
    };


    String.prototype.format = function () {
        var args = arguments;
        return this.replace(/\{(\d+)\}/g, function (s, index) {
            return args[index];
        });
    };


    var initWaybillImageUpload = function () {
        var _imagelist = $('.image-list');
        var uploader = new plupload.Uploader({
            runtimes: 'html5,flash,silverlight,html4',
            browse_button: 'watePictureButton',
            container: 'watePictureContainer',
            url: base + '/common/upload/image',
            flash_swf_url: '../js/Moxie.swf',
            silverlight_xap_url: '../js/Moxie.xap',

            filters: {
                max_file_size: '10mb',
                mime_types: [
                    {title: "Image files", extensions: "jpg,gif,png,jpeg"}
                ]
            },

            init: {
                PostInit: function () {
                },

                FilesAdded: function (up, files) {
                    plupload.each(files, function (file) {
                        _imagelist.append('<div id="' + file.id + '">' + file.name + ' (' + plupload.formatSize(file.size) + ') <b></b></div>');
                    });
                    up.start();
                },

                UploadProgress: function (up, file) {
                    document.getElementById(file.id).getElementsByTagName('b')[0].innerHTML = '<span>' + file.percent + "%</span>";
                },

                Error: function (up, err) {
                    App.alertDanger("上传失败，请重试！");
                },

                FileUploaded: function (up, file, responseObject) {
                    var json = JSON.parse(responseObject.response);//字符串转化为json
                    if (json.success == "1") {
                        $('.js_container').children('img').attr('src', json.src);
                        $('input[name="watePictureUrl"]').val(json.src);
                    } else {
                        App.alertDanger("上传失败，请重试！");
                    }
                    _imagelist.html('');
                }
            }
        });

        uploader.init();
    };


    return {
        init: function () {
            JQueryEventDragLive.init();
            initEvent();
            initWaybillImageUpload();
        }
    }
});