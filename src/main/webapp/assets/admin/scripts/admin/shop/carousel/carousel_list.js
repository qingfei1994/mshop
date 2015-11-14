define(['bootbox','ajaxfileupload'],function(BootBox){
	"use strict";
	
	var handleAddPic = function () {
		$('#addPic').on('click',function(){
				var count = $(".col-md-6").length;
				App.post("admin/shop/carousel/addpic",{count:count},function(res){
					if (res.length < 200) {
        				App.toastError(res);
        			} else {
        				App.toastSuccess("操作成功！");
        				App.scrollTop();
        			
        				$('.page-content .page-content-body').html(res);
        				restoreMessage();
        				App.fixContentHeight(); // fix content height
        				App.initAjax(); // initialize core stuff
        			}
					
				});
				
		});
		
	}
	
	//点击"添加图片"按钮,暂时portlet的id是写死了，到时候根据count动态生成
	var handleAddCarousel = function(){
		$('#addPic').on('click',function(){
			var count = $('.row .choose-image').length;
			console.log("图片有"+count);//输出轮播图片总共有几张
			var id="h"+(count+1);
			if(count == 10 ){//当图片为10张时
				App.toastError("幻灯片不能超过10张");
			}else{
				var row=$('div.row:last').length;
				var length=$('div.row:last > .col-md-6').length;
				if(count%2==0){
		    		var html = "<div class='row'>";
		    		html += "<div class='col-md-6'>";
					html+="<div class='portlet' id='"+id+"'>";
					html+="<div class='portlet-body'>";
					html+="<a class='choose-image' href='javascript:;'>";
					html+="<img src='http://dummyimage.com/640x320/ddd/555'  style='max-width: 100%' />";
					html+="</a>"
					html+="</div>";
					html+="<div class='portlet-title'>";
					html+="<div class='caption'>";
					html+="<a href='javascript:;' class=' btn-sm btn-default modal-ajax-static' url='admin/shop/carousel/goodsInformation/list?inbaId=" +id+"'";
					html+=">选择链接</a>";
					html+="<span class='help-inline'></span>";
					html+="<span id='"+id+"-goodid' style='display:none'></span>";
					html+="</div>";
					html+="<div class='tools'>";
					html+="<a href='javascript:;' class='remove bootbox-confirm' confirm='确定删除？' url='wap/shop/carousel/delete'></a>";
					html+="</div>";
					html+="</div>";
					html+="</div>";
					html+="</div>";
					html+="</div>";
					$('.row:last').parent('.content-body').append(html);
				}else if(count%2==1){
						var html = "<div class='col-md-6'>";
						html+="<div class='portlet' id='"+id+"'>";
						html+="<div class='portlet-body'>";
						html+="<a class='choose-image' href='javascript:;'>";
						html+="<img src='http://dummyimage.com/640x320/ddd/555'  style='max-width: 100%' />";
						html+="</a>"
						html+="</div>";
						html+="<div class='portlet-title'>";
						html+="<div class='caption'>";
						html+="<a href='javascript:;' class=' btn-sm btn-default modal-ajax-static' url='admin/shop/carousel/goodsInformation/list?inbaId=" +id+"'";
						html+=">选择链接</a>";
						html+="<span class='help-inline'></span>";
						html+="<span id='"+id+"-goodid' style='display:none'></span>";
						html+="</div>";
						html+="<div class='tools'>";
						html+="<a href='javascript:;' class='remove bootbox-confirm' confirm='确定删除？' url='wap/shop/carousel/delete'></a>";
						html+="</div>";
						html+="</div>";
						html+="</div>";
						html+="</div>";
						$('.row:last').append(html);
				}
			}
		});
	}
	var restoreMessage = function() {
		var list=getAllInacInfoFromLocalStorage();
		for(var i=0;i<list.length;i++) {
			var obj=JSON.parse(list[i]);
			var id=obj.id;
			var goodId=obj.goodId;
			var goodName=obj.goodName;
			var image=obj.image;
			var $temp=$('.portlet #'+id);
			if($temp!=null) {
				$temp.find('span.help-inline').html(goodName);
				$temp.find('#'+id+'-goodid').html(goodId);
				if(image!="") {
					$temp.find("img").attr('src',image);
				}

			}
		}
	}
	//获取所有的轮播图片的信息
	var getAllInacInfoFromLocalStorage = function () {
		var storage = window.localStorage;
		var list=new Array();
		var j=0;
		for(var i=0;i<storage.length;i++) {
			var key=storage.key(i);
			if(key.substr(0,6)=="inacId") {
				list[j++]=storage.getItem(key);
			}
		}	
		return list;
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
	var removeFromLocalStorage = function (name) {
		var storage = window.localStorage;
		storage.removeItem(name);  
	}
	//图片上传
	var handleUploadPic = function(){
		var dataId ;
		$('.content-body').on('click', '.choose-image', function() {
			dataId = $(this).closest('.portlet').attr('id');
			console.log(dataId);
            $('#fileUpload').click();
        });
		
		$('.content-body').on('change', '#fileUpload', function(){
			var width = 640, height = 320;
            $.ajaxFileUpload({
                url: base+'/common/upload/image',
                type: 'post',
                secureuri: false,
                fileElementId: 'fileUpload',
                dataType: 'json',
                data:{"width": width, "height": height},
                success: function(data) {
                    if (data && data.success == 1) {
                    	$('#'+dataId+' > .portlet-body > .choose-image > img').attr("src",data.src);
                    	var name="inacId"+dataId;
                    	var info=getJsonFromLocalStorage(name);
            			if(info==null) {
            				info={id:id,goodId:goodId,goodName:imagename,image:""};
            			} else {
            				
            				info.image=data.src;
            			}
                    } else {
                        alert('图片上传失败');
                    }
                },
                error: function(data) {
                    alert('图片上传失败');
                }
            });
		});
	}
	var handleSaveCarousel=function(){
			$('#saveCarouselBtn').on('click',function(){
					console.log("ddd");
					var isChooseGoods=true;
					var list=new Array();
					$('div.col-md-6 > .portlet').each(function(index){
							var goodName = $(this).find('.portlet-title span.help-inline').html();
							if(goodName.length==0) {
								App.toastError("未选择商品！");
								isChooseGoods=false;
								return false;
							}
							console.log($(this).attr('class'));
							var id=$(this).attr('id');
							var goodId=$(this).find('#'+id+'-goodid').html();
							var image=$(this).find('img').attr('src');
							var info={id:id,goodId:goodId,image:image};
							list[index]=info;
					});
					if(isChooseGoods) {
						App.post("admin/shop/carousel/save",{data:JSON.stringify(list)});
					}
					
			});
	}
	var handleRemove =function() {
		$('.remove').on('click',function(){
			BootBox.setLocale("zh_CN");
			var the=$(this);
			BootBox.confirm("确定要删除吗？", function(result) {
	            if (result) {
	               var id=the.attr('data-id');
	               App.post("admin/shop/carousel/delete",{inbaId:id},function(res){
	            	   if (res.length < 200) {
	        				App.toastError(res);
	        			} else {
	        				App.toastSuccess("操作成功！");
	        				App.scrollTop();
	        				removeFromLocalStorage("inacId"+id);
	        				$('.page-content .page-content-body').html(res);
	        				restoreMessage();
	        				App.fixContentHeight(); // fix content height
	        				App.initAjax(); // initialize core stuff
	        			}
	               });
	            }
	        });
		});
	}
	return {
		init : function(){
			//handleAddCarousel();
			 handleAddPic();
			handleUploadPic();
			handleSaveCarousel();
			handleRemove();
		}
	};
});