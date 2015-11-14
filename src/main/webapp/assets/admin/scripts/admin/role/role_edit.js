define(["bootbox"], function (Bootbox) {
    "use strict";

    var initEvent = function () {
        $(':checkbox').on('click', function () {
            var checked = $(this).attr("checked");
            if (checked) {
                $(this).attr('checked', false);
            } else {
                $(this).attr('checked', true);
            }
        });

        $(":checkbox").on('click', function () {
            var checked = $(this).attr("checked");

            //勾选事件
            if (checked) {
                //选中所有父节点
                var parentId = $(this).attr("parent-id");
                $("input[value='" + parentId + "']").each(function () {
                    $(this).attr('checked', true);
                    $(this).parents('span').addClass('checked');

                    var parentId1 = $(this).attr("parent-id");
                    $("input[value='" + parentId1 + "']").each(function () {
                        $(this).attr('checked', true);
                        $(this).parents('span').addClass('checked');
                    });
                });

                //选中所有子节点
                var valueId = $(this).val();
                $("input[parent-id='" + valueId + "']").each(function () {
                    $(this).attr('checked', true);
                    $(this).parents('span').addClass('checked');

                    var valueId1 = $(this).val();
                    $("input[parent-id='" + valueId1 + "']").each(function () {
                        $(this).attr('checked', true);
                        $(this).parents('span').addClass('checked');
                    });
                });

                //取消勾选事件
            } else {
                //取消父节点
                var parentId = $(this).attr("parent-id");
                if ($("input[parent-id='" + parentId + "']:checked").length == 0) {
                    $("input[value='" + parentId + "']").each(function () {
                        $(this).attr('checked', false);
                        $(this).parents('span').removeClass('checked');

                        var parentId1 = $(this).attr("parent-id");
                        if ($("input[parent-id='" + parentId1 + "']:checked").length == 0) {
                            $("input[value='" + parentId1 + "']").each(function () {
                                $(this).attr('checked', false);
                                $(this).parents('span').removeClass('checked');
                            });
                        }
                    });
                }

                //取消所有子节点
                var valueId = $(this).val();
                $("input[parent-id='" + valueId + "']").each(function () {
                    $(this).attr('checked', false);
                    $(this).parents('span').removeClass('checked');

                    var valueId1 = $(this).val();
                    $("input[parent-id='" + valueId1 + "']").each(function () {
                        $(this).attr('checked', false);
                        $(this).parents('span').removeClass('checked');
                    });
                });
            }
        });
    }

    return {
        init: function () {
            initEvent();
        }
    };
});