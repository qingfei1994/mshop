(function(e,t,n){var r=t.fx.cssPrefix,i=t.fx.transitionEnd,s=" translateZ(0)";e.define("Slider",{options:{loop:!1,speed:400,index:0,selector:{container:".ui-slider-group"}},template:{item:'<div class="ui-slider-item"><a href="<%= href %>"><img src="<%= pic %>" alt="" /></a><% if( title ) { %><p><%= title %></p><% } %></div>'},_create:function(){var e=this,n=e.getEl(),r=e._options;e.index=r.index,e._initDom(n,r),e._initWidth(n,e.index),e._container.on(i+e.eventNs,t.proxy(e._tansitionEnd,e)),t(window).on("ortchange"+e.eventNs,function(){e._initWidth(n,e.index)})},_initDom:function(e,n){var r=n.selector,i=n.viewNum||1,s,o;o=e.find(r.container),o.length||(o=t("<div></div>"),n.content?this._createItems(o,n.content):e.is("ul")?(this.$el=o.insertAfter(e),o=e,e=this.$el):o.append(e.children()),o.appendTo(e)),(s=o.children()).length<i+1&&(n.loop=!1);while(n.loop&&o.children().length<3*i)o.append(s.clone());this.length=o.children().length,this._items=(this._container=o).addClass("ui-slider-group").children().addClass("ui-slider-item").toArray(),this.trigger("done.dom",e.addClass("ui-slider"),n)},_createItems:function(e,t){var n=0,r=t.length;for(;n<r;n++)e.append(this.tpl2html("item",t[n]))},_initWidth:function(e,t,n){var r=this,i;if(!n&&(i=e.width())===r.width)return;r.width=i,r._arrange(i,t),r.height=e.height(),r.trigger("width.change")},_arrange:function(e,t){var n=this._items,r=0,i,s;this._slidePos=new Array(n.length);for(s=n.length;r<s;r++)i=n[r],i.style.cssText+="width:"+e+"px;"+"left:"+r*-e+"px;",i.setAttribute("data-index",r),this._move(r,r<t?-e:r>t?e:0,0);this._container.css("width",e*s)},_move:function(e,t,n,r){var i=this._slidePos,s=this._items;if(i[e]===t||!s[e])return;this._translate(e,t,n),i[e]=t,r&&s[e].clientLeft},_translate:function(e,t,n){var i=this._items[e],o=i&&i.style;if(!o)return!1;o.cssText+=r+"transition-duration:"+n+"ms;"+r+"transform: translate("+t+"px, 0)"+s+";"},_circle:function(e,t){var n;return t=t||this._items,n=t.length,(e%n+n)%t.length},_tansitionEnd:function(e){if(~~e.target.getAttribute("data-index")!==this.index)return;this.trigger("slideend",this.index)},_slide:function(e,t,n,r,i,s){var o=this,u;return u=o._circle(e-n*t),s.loop||(n=Math.abs(e-u)/(e-u)),this._move(u,-n*r,0,!0),this._move(e,r*n,i),this._move(u,0,i),this.index=u,this.trigger("slide",u,e)},slideTo:function(e,t){if(this.index===e||this.index===this._circle(e))return this;var n=this._options,r=this.index,i=Math.abs(r-e),s=i/(r-e),o=this.width;return t=t||n.speed,this._slide(r,i,s,o,t,n)},prev:function(){return(this._options.loop||this.index>0)&&this.slideTo(this.index-1),this},next:function(){return(this._options.loop||this.index+1<this.length)&&this.slideTo(this.index+1),this},getIndex:function(){return this.index},destroy:function(){return this._container.off(this.eventNs),t(window).off("ortchange"+this.eventNs),this.$super("destroy")}})})(gmu,gmu.$);