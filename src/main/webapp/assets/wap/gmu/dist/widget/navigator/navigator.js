(function(e,t,n){e.define("Navigator",{options:{content:null,event:"click"},template:{list:"<ul>",item:'<li><a<% if( href ) { %> href="<%= href %>"<% } %>><%= text %></a></li>'},_create:function(){var e=this,r=e._options,i=e.getEl(),s=i.find("ul").first(),o="ui-"+e.widgetName,u,a;!s.length&&r.content?(s=t(e.tpl2html("list")),u=e.tpl2html("item"),a="",r.content.forEach(function(e){e=t.extend({href:"",text:""},typeof e=="string"?{text:e}:e),a+=u(e)}),s.append(a).appendTo(i)):(i.is("ul, ol")&&(s=i.wrap("<div>"),i=i.parent()),r.index===n&&(r.index=s.find(".ui-state-active").index(),~r.index||(r.index=0))),e.$list=s.addClass(o+"-list"),e.trigger("done.dom",i.addClass(o),r),s.highlight("ui-state-hover","li"),s.on(r.event+e.eventNs,"li:not(.ui-state-disable)>a",function(n){e._switchTo(t(this).parent().index(),n)}),e.index=-1,e.switchTo(r.index)},_switchTo:function(t,n){if(t===this.index)return;var r=this,i=r.$list.children(),s=e.Event("beforeselect",n),o;r.trigger(s,i.get(t));if(s.isDefaultPrevented())return;return o=i.removeClass("ui-state-active").eq(t).addClass("ui-state-active"),r.index=t,r.trigger("select",t,o[0])},switchTo:function(e){return this._switchTo(~~e)},unselect:function(){this.index=-1,this.$list.children().removeClass("ui-state-active")},getIndex:function(){return this.index}})})(gmu,gmu.$);