define(function(){
	"use strict";
	//点击某一行商品
	var handleIcon = function(){
		$(".modal-body .form-group .gooditem").on('click',function(){
			$('.gooditem').find('.icon-ok').addClass("icon-remove").removeClass("icon-ok");
			$(this).find('.icon-remove').addClass("icon-ok").removeClass("icon-remove");
		});
	}

	var getJsonFromLocalStorage = function (name, json) {
		var storage = window.localStorage;
		if(storage.getItem(name)==null) {
			return null;
		} else {
			var info=JSON.parse(storage.getItem(name));
			return info;
		}
	
	}
	var setJsonToLocalStorage = function (name, json) {
		var storage = window.localStorage;
		storage.setItem(name,JSON.stringify(json));  
	}
	//点击Modal的确定按钮
	var handleButton = function(){
		$('.form-body .modal-footer .mybutton').on('click',function(){
			var id = $('input[type=hidden]').val();
			var imagename = $('.icon-ok').closest('.gooditem').find('td:eq(1) center').attr("value");
			var goodId=$('.icon-ok').closest('.gooditem').find('.goodid').html();
			console.log(goodId);
			$('#'+id+'-goodid').html(goodId);
			$('#'+id+'  .portlet-title span.help-inline').html(imagename);
			var name="inacId"+id;
			var info=getJsonFromLocalStorage(name);
			if(info==null) {
				info={id:id,goodId:goodId,goodName:imagename,image:""};
			} else {
				info.goodId=goodId;
				info.goodName=imagename;
			}
			setJsonToLocalStorage(name,info);
		});
	}

	
	 return {
	        init: function () {
	        	handleIcon();
	        	handleButton();
	        }
	    };
});