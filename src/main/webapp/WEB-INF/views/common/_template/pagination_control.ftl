<#if needPaging?default(false) >
<label class="paging-control-label">
    <select class="form-control paging-table-control input-sm">
        <option value="10" <#if (pageSize == 10) >selected="selected"</#if>>10</option>
        <option value="20" <#if (pageSize == 20) >selected="selected"</#if>>20</option>
        <option value="50" <#if (pageSize == 50) >selected="selected"</#if>>50</option>
        <option value="100" <#if (pageSize == 100) >selected="selected"</#if>>100</option>
    </select>
</label>
</#if>