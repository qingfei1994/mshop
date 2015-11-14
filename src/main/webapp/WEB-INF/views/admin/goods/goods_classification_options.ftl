<#-- 商品分组 -->
<#list goodsClassifications as goodsClassification>
	<#if goodsClassification.goodsClassifications?exists>
		<optgroup value="${goodsClassification.goclId}" label="${goodsClassification.goclName}">
			<#assign goodsClassifications = goodsClassification.goodsClassifications />
			<#include "goods_classification_options.ftl" />
		</optgroup>
	<#else>
    	<option value="${goodsClassification.goclId}"
			<#if goodsClassificationList??><#list goodsClassificationList as gocl>
				<#if gocl.goclId == goodsClassification.goclId>selected</#if>
			</#list></#if>>${goodsClassification.goclName}</option>
	</#if>
</#list>