<style>
	.form-inline{
		background: #ffffff !important;
		padding: 0px !important;
		padding-bottom: 0px !important;
		border: 0px !important;
		border-bottom: 0 !important;
	}
</style>

<div class="form-inline">
	<div class="form-group">
		<input type="hidden" id="provinceId" name="provinceId" value="${provinceId!}" >
		<input type="hidden" id="cityId" name="cityId" value="${cityId!}" >
		<input type="hidden" id="countyId" name="countyId" value="${countyId!}" >
		<div class="col-md-4 regi">
			<select class="form-control regionalism regionalism-first" name="province">
				<option value="">请选择</option>
			</select>
		</div>
		<div class="col-md-4">
			<select class="form-control regionalism" name="city">
				<option value="">请选择</option>
			</select>
		</div>
		<div class="col-md-4">
			<select class="form-control regionalism" name="county" required="true">
				<option value="">请选择</option>
			</select>
		</div>
	</div>
</div>
