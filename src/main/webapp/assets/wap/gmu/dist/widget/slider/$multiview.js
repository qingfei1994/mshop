(function(e,t,n){t.extend(e.Slider.options,{viewNum:2,travelSize:2}),e.Slider.register("multiview",{_arrange:function(e,t){var n=this._items,r=this._options.viewNum,i=t%r,s=0,o=this.perWidth=Math.ceil(e/r),u,a;this._slidePos=new Array(n.length);for(a=n.length;s<a;s++)u=n[s],u.style.cssText+="width:"+o+"px;"+"left:"+s*-o+"px;",u.setAttribute("data-index",s),s%r===i&&this._move(s,s<t?-e:s>t?e:0,0,Math.min(r,a-s));this._container.css("width",o*a)},_move:function(e,t,n,r,i){var s=this.perWidth,o=this._options,u=0;i=i||o.viewNum;for(;u<i;u++)this.origin(o.loop?this._circle(e+u):e+u,t+u*s,n,r)},_slide:function(e,t,n,r,i,s,o){var u=this,a=s.viewNum,f=this._items.length,l,c;return s.loop||(t=Math.min(t,n>0?e:f-a-e)),c=u._circle(e-n*t),s.loop||(n=Math.abs(e-c)/(e-c)),t%=f,f-t<a&&(t=f-t,n=-1*n),l=Math.max(0,a-t),o||(this._move(c,-n*this.perWidth*Math.min(t,a),0,!0),this._move(e+l*n,l*n*this.perWidth,0,!0)),this._move(e+l*n,r*n,i),this._move(c,0,i),this.index=c,this.trigger("slide",c,e)},prev:function(){var e=this._options,t=e.travelSize;return(e.loop||(this.index>0,t=Math.min(this.index,t)))&&this.slideTo(this.index-t),this},next:function(){var e=this._options,t=e.travelSize,n=e.viewNum;return(e.loop||this.index+n<this.length&&(t=Math.min(this.length-1-this.index,t)))&&this.slideTo(this.index+t),this}})})(gmu,gmu.$);