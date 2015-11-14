define(['plupload'], function(plupload){
	
	/**
	 * 图片上传
	 */
	var initImagePlupload = function() {
		$('.plupload-image').each(function(index){
			var _plupload = $(this);
			var _imagelist = _plupload.parent('div').next('.image-list:first');
			var _imageshowlist = _plupload.parent('div').nextAll('ul.image-show-list:first');

			//上传按钮
			var pluploadId = Util.getUniqueID("plupload");
			_plupload.attr('id', pluploadId);
			
			var containerId = Util.getUniqueID("container");
			_plupload.parent('div').attr('id', containerId);
			
			var width = _plupload.attr('data-width');
			var height = _plupload.attr('data-height');
			var imageName = _plupload.attr('data-name');
			
			var url = base+'/common/upload/image';
			if(width != undefined && height != undefined){
				url = url+"?width="+width+"&height="+height;
			}
			
			var uploader = new plupload.Uploader({
				runtimes : 'html5,flash,silverlight,html4',
				browse_button : pluploadId,
				container: containerId,
				url : url,
				flash_swf_url : '../js/Moxie.swf',
				silverlight_xap_url : '../js/Moxie.xap',
				
				filters : {
					max_file_size : '10mb',
					mime_types: [
						{title : "Image files", extensions : "jpg,gif,png,jpeg"}
					]
				},

				init: {
					PostInit: function() {
						_imagelist.html('');
					},

					FilesAdded: function(up, files) {
						plupload.each(files, function(file) {
							_imagelist.append('<div id="' + file.id + '">' + file.name + ' (' + plupload.formatSize(file.size) + ') <b></b></div>');
						});
						up.start();
					},

					UploadProgress: function(up, file) {
						document.getElementById(file.id).getElementsByTagName('b')[0].innerHTML = '<span>' + file.percent + "%</span>";
					},

					Error: function(up, err) {
						App.alertDanger("上传失败，请重试！");
					},
					
					FileUploaded: function(up, file, responseObject){
						var json = JSON.parse(responseObject.response);//字符串转化为json
						if(json.success == "1"){
							var html = '<li class="imgbox show-item" data-path="'+ json.src +'">'+
							'<a class="item_new_close item_close" href="javascript:void(0)" url="common/upload/deleteFile" title="删除" data-path="'+ json.filePath +'" ></a>'+
							'<input type="hidden" value="'+ json.src +'" name="'+imageName+'"/>'+
							'<span class="item_box"><img src="'+json.src +'"></span>'+
							'</li>';
							_imageshowlist.append(html);
						}else{
							App.alertDanger("上传失败，请重试！");
						}
						
						_imagelist.html('');;
					}
				}
			});

			uploader.init();
		});
	};
	
	/**
	 * 文件上传
	 */
	var initFilePlupload = function(){
		$('.plupload-file').each(function(){
			var _plupload = $(this);
			var _filelist = _plupload.parent('div').nextAll('.file-list:first').children('table').children('tbody.files');
			
			//上传按钮
			var pluploadId = Util.getUniqueID("plupload");
			_plupload.attr('id', pluploadId);
			
			var containerId = Util.getUniqueID("container");
			_plupload.parent('div').attr('id', containerId);
			
			var dataName = _plupload.attr('data-name');
			
			var uploader = new plupload.Uploader({
				runtimes : 'html5,flash,silverlight,html4',
				browse_button : pluploadId,
				container: containerId,
				url : base+'/common/upload/file',
				flash_swf_url : '../js/Moxie.swf',
				silverlight_xap_url : '../js/Moxie.xap',
				
				filters : {
					max_file_size : '10mb',
					mime_types: [
						{title : "Image files", extensions : "jpg,gif,png,jpeg"},
						{title : "Zip files", extensions : "zip"},
						{title : "Office files", extensions : "docx,doc,xls,xlsx"},
						{title : "Note files", extensions : "txt"}
					]
				},

				init: {
					PostInit: function() {
						_filelist.html('');
					},

					FilesAdded: function(up, files) {
						plupload.each(files, function(file) {
							//_filelist.append('<div id="' + file.id + '">' + file.name + ' (' + plupload.formatSize(file.size) + ') <b></b></div>');
							_filelist.append('<tr class="show-item template-upload fade in">'+
												  '<td>'+
													  '<span class="preview"></span>'+
												  '</td>'+
												  '<td>'+
													  '<p class="name">' + file.name + '</p>'+
												  '</td>'+
												  '<td>'+
													  '<p class="size">' + plupload.formatSize(file.size) + ' <b  id="' + file.id + '"></b></p>'+
												  '</td>'+
												  '<td class="' + file.id + '">'+
													  '<a class="btn btn-info cancel">'+
														  '<span>上传中...</span>'+
													  '</a>'+
												  '</td>'+
											'</tr>');
						});
						up.start();
					},

					UploadProgress: function(up, file) {
						document.getElementById(file.id).innerHTML = '<span>' + file.percent + "%</span>";
					},

					Error: function(up, err) {
						App.alertDanger("上传失败，请重试！");
					},
					
					FileUploaded: function(up, file, responseObject){
						var json = JSON.parse(responseObject.response);//字符串转化为json
						if(json.success == "1"){
							var html = '<a class="btn btn-danger item_new_close" url="common/upload/deleteFile" title="删除" data-path="'+ json.src +'">'+
										  '<input type="hidden" value="'+ json.src +'" name="'+dataName+'"/>'+
										  '<i class="icon-ban-circle"></i>'+
										  '<span>删除</span>'+
									  '</a>';
							$('td.'+file.id).html(html);
						}else{
							App.alertDanger("上传失败，请重试！");
						}
					}
				}
			});

			uploader.init();
		});
	};
	
	/**
	 * 文件删除
	 */
	var deleteFile = function(){
		$('ul.image-show-list,table.files-show-list').on('click', 'a.item_new_close', function(){
			var _this = $(this);
			var data = _this.attr('data-path');
			var url = base+"/"+_this.attr('url');
			//var url = base+"/deleteFile";
			$.ajax({
				type : "POST",
				cache : false,
				url : url,
				dataType : "json",
				data: {"filepath": data},
				success: function (res) {
					if(res.Status == 'success'){
						_this.parents('.show-item:first').remove();
					}else{
						App.alertDanger("删除失败，请重试！");
					}
        		},
        		error: function (xhr, ajaxOptions, thrownError) {
        			App.alertDanger("删除失败，请重试！");
        		}
			});
		});
	};
	
	return {
		init: function(){
			initImagePlupload();
			initFilePlupload();
			deleteFile();
		}
	}
	
});