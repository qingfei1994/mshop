(function(e,t){var n=e.Popover;n.options.dismissible=!0,n.option("dismissible",!0,function(){function i(n){var r=e.$target.add(e.$root).get(),i=r.length;while(i--)if(r[i]===n||t.contains(r[i],n))return!0;return!1}var e=this,n=t(document),r="click"+e.eventNs;e.on("show",function(){n.off(r).on(r,function(t){i(t.target)||e.hide()})}),e.on("hide",function(){n.off(r)})})})(gmu,gmu.$);