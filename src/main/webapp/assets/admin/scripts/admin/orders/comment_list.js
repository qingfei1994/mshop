define(function () {

    var initCommentShow = function () {
        var $commentShow = $('.comment_show');
        if ($commentShow.length > 0) {
            $commentShow.on('click', function () {
                var $this = $(this);
                nextI = $this.children('i'),
                    data = nextI.data('msg'),
                    url = $this.attr('url');

                $.ajax({
                    type: "GET",
                    url: url,
                    cache: false,
                    data: {'status': data},
                    dataType: 'json',
                    success: function (res) {
                        if (res.status == 1) {
                            if (res.type == 1) {
                                nextI.removeClass();
                                nextI.addClass('icon-ok text-success text-active');
                                nextI.data('msg', '1');
                            } else if (res.type == 0) {
                                nextI.removeClass();
                                nextI.addClass('icon-remove text-danger text');
                                nextI.data('msg', '0');
                            }
                        } else {
                            App.toastError(App.toastrErrorTip);
                        }
                    },
                    error: function (res) {
                        App.toastError(App.toastrErrorTip);
                    }
                });

            });
        }
    };

//	var initDropdown = function(){
//		$(function() {
//            var path = $('input[name="pagingPath"]').val();
//            if (path && path.constants('wein_nickname')) {
//                $('.query-form .dropdown-toggle > span').html('粉丝昵称');
//                $('.query-form input[name="goor_name"]').hide();
//                $('.query-form input[name="orin_no"]').hide();
//                $('.query-form input[name="wein_nickname"]').show();
//            }else if(path && path.constants('wein_nickname')) {
//                $('.query-form .dropdown-toggle > span').html('订单编号');
//                $('.query-form input[name="goor_name"]').hide();
//                $('.query-form input[name="wein_nickname"]').hide();
//                $('.query-form input[name="orin_no"]').show();
//            }
//        });
//
//		$('.query-form .dropdown-menu .goor-name').on('click', function() {
//            $('.query-form .dropdown-toggle > span').html('商品名称');
//            var $goodName = $('.query-form input[name="goor_name"]');
//            var $weinNickname = $('.query-form input[name="wein_nickname"]');
//            var $orinNo = $('.query-form input[name="orin_no"]');
//            $weinNickname.hide();
//            $weinNickname.val('');
//            $orinNo.hide();
//            $orinNo.val('');
//            $goodName.show();
//        });
//
//        $('.query-form .dropdown-menu .wein-nickname').on('click', function() {
//            $('.query-form .dropdown-toggle > span').html('粉丝昵称');
//            var $goodName = $('.query-form input[name="goor_name"]');
//            var $weinNickname = $('.query-form input[name="wein_nickname"]');
//            var $orinNo = $('.query-form input[name="orin_no"]');
//            $goodName.hide();
//            $goodName.val('');
//            $orinNo.hide();
//            $orinNo.val('');
//            $weinNickname.show();
//        });
//        
//        $('.query-form .dropdown-menu .orin-no').on('click', function() {
//            $('.query-form .dropdown-toggle > span').html('订单编号');
//            var $goodName = $('.query-form input[name="goor_name"]');
//            var $weinNickname = $('.query-form input[name="wein_nickname"]');
//            var $orinNo = $('.query-form input[name="orin_no"]');
//            $goodName.hide();
//            $goodName.val('');
//            $weinNickname.hide();
//            $weinNickname.val('');
//            $orinNo.show();
//        });
//	};

    return {
        init: function () {
            initCommentShow();
//			initDropdown();
        }
    }
});