<link href="${base}/assets/plugins/ueditor/themes/default/css/ueditor.min.css" rel="stylesheet" type="text/css"/>

<div class="form-group">
  <div class="col-md-12">
  	<textarea class="ueditor"></textarea>
  </div>
</div>

<script>
	require(['ueditor'], function(ueditor) {
		$(document).ready(function(){
			ueditor.init();
			//App.handleUEditor("container");
			App.initPlugins();
		});
	})
</script>