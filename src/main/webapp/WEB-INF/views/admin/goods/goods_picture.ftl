<#-- 商品图片 -->
<#list goodsPictures as gopi>
	<li class="imgbox show-item" data-path="${gopi.gopiUrl}">
		<a class="item_new_close item_close" href="javascript:void(0)" url="common/upload/deleteFile" title="删除" data-path="${picPaths[gopi.gopiId?string]}"></a>
		<input type="hidden" value="${gopi.gopiUrl}" name="images">
		<span class="item_box">
			<img src="${gopi.gopiUrl}">
		</span>
	</li>
</#list>
