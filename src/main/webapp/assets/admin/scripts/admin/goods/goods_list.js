define(function() {
	"use strict";
	
	var initGoodsList = function() {
        $(function() {
            var path = $('input[name="pagingPath"]').val();
            if (path && path.constants('gpstCode')) {
                $('.query-form .dropdown-toggle > span').html('商家编码');
                $('.query-form input[name="goodName"]').hide();
                $('.query-form input[name="gpstCode"]').show();
            }
        });

		$('.query-form .dropdown-menu .good-name').on('click', function() {
            $('.query-form .dropdown-toggle > span').html('商品名称');
            var $goodName = $('.query-form input[name="goodName"]');
            var $gpstCode = $('.query-form input[name="gpstCode"]');
            $gpstCode.hide();
            $gpstCode.val('');
            $goodName.show();
        });

        $('.query-form .dropdown-menu .gpst-code').on('click', function() {
            $('.query-form .dropdown-toggle > span').html('商家编码');
            var $goodName = $('.query-form input[name="goodName"]');
            var $gpstCode = $('.query-form input[name="gpstCode"]');
            $goodName.hide();
            $goodName.val('');
            $gpstCode.show();
        });
	};

    return {
        init: function () {
            initGoodsList();
        }
    };
	
});