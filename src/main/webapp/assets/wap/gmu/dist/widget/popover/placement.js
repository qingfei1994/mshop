(function(e,t){t.extend(e.Popover.options,{placement:"bottom",offset:null}),e.Popover.option("placement",function(e){return~["top","bottom","left","right"].indexOf(e)},function(){function s(e,t){return e==="right"||e==="bottom"?t:e==="center"?t/2:0}function o(e,t,n,r,o){var u=i.of,a=i.coord,f=i.offset,l=u.top,c=u.left;return c+=s(t,u.width)-s(r,a.width),l+=s(n,u.height)-s(o,a.height),f=typeof f=="function"?f.call(null,{left:c,top:l},e):f||{},{left:c+(f.left||0),top:l+(f.top||0)}}var e=this,n={top:"center top center bottom",right:"right center left center",bottom:"center bottom center top",left:"left center right center"},r={},i;t.each(n,function(e,t){t=t.split(/\s/g),t.unshift(e),r[e]=function(){return o.apply(null,t)}}),this.on("placement",function(e,t,n){var s=this,o=s._options,u=o.placement,a;i={coord:t.offset(),of:n.offset(),placement:u,$el:t,$of:n,offset:o.offset},a=r[u](),s.trigger("before.placement",a,i,r),i.preset&&(i.placement=i.preset),t.offset(a),s.trigger("after.placement",a,i)}),t(window).on("ortchange",function(){e._visible&&e.trigger("placement",e.$target,e.$root)})})})(gmu,gmu.$);