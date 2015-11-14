
	<ul class="dialog-wrap">
		<#-- <li class="table-sku">
			<table>
				<td>
					<label>颜色：</label>
				</td>
				<td>
					<label class="label-radio">
						<input type="radio" name="color" value="1"><span>选项一</span>
					</label>
					<label class="label-radio">
						<input type="radio" name="color" value="2"><span>选项二</span>
					</label>
				</td>
			</table>
		</li> -->
		<input type="hidden" name="gpstId" id="gpstId" value="${gpstId!}">
		<li class="table-number">
			<table>
				<td>
					<label>价格：</label>
				</td>
				<td>
					<label>￥${price!}</label>
				</td>
			</table>
		</li>
		<li class="table-number">
			<table>
				<td>
					<label>数量：</label>
				</td>
				<td>
					<input type="button" class="minus" value="-">
				</td>
				<td>
					<input type="number" class="shop-number" value="1" min="1">
				</td>
				<td>
					<input type="button" class="plus" value="+">
				</td>
				<td class="sku-inventory">
					<label>(库存<span id="stock">${stock!}</span>)</label>
				</td>
			</table>
		</li>
		<li class="btn-group">
			<div>
				<button class="btn black fl cancel-addshopcart">取消</button>
				<button class="btn red fr sure-addshopcart">确定</button>
			</div>
		</li>
	</ul>
