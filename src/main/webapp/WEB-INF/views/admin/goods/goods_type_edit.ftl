<#-- 商品类目 -->
<#list goodsTypeListMap?keys as gotyId>
	<#assign goodsTypeList = goodsTypeListMap[gotyId] />
	<select <#if !gotyId_has_next>name="gotyId"</#if> class="form-control parent-type" required="true">
		<option value="">请选择</option>
		<#list goodsTypeList as goty>
			<option value="${goty.gotyId}" <#if gotyId?number == goty.gotyId>selected</#if>>${goty.gotyName}</option>
		</#list>
	</select>
</#list>
