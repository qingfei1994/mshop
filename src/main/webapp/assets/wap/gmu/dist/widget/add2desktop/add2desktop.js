(function(e,t,n){e.define("Add2desktop",{options:{icon:"",container:"",key:"_gmu_adddesktop_key",useFix:!0,position:{bottom:12,left:"50%"},beforeshow:function(e){this.key()&&e.preventDefault()},afterhide:function(){this.key(1)},_isShow:!1},_init:function(){var e=this;e.on("ready",function(){e.$el.find(".ui-add2desktop-close").on("click",function(){e.hide()}),e._options.useFix&&e.$el.fix(e._options.position),e.show()}),e.on("destroy",function(){e.$el.remove()})},_create:function(){var e=this,n,r=t.os.version&&t.os.version.substr(0,3)>4.1?"new":"old";t.os.version&&t.os.version.substr(0,3)>=7&&(r="iOS7");if(e._options.setup){var i=e.$el.children("img").attr("src");i&&(e._options.icon=i)}n=e.$el||(e.$el=t("<div></div>")),n.addClass("ui-add2desktop").appendTo(e._options.container||(e.$el.parent().length?"":document.body)),n.html('<img src="'+e._options.icon+'"/><p>先点击<span class="ui-add2desktop-icon-'+r+'"></span>，<br />再"添加到主屏幕"</p><span class="ui-add2desktop-close"><b></b></span><div class="ui-add2desktop-arrow"><b></b></div>')},key:function(e){var t=window.localStorage;return e!==n?t.setItem(this._options.key,e):t.getItem(this._options.key)},show:function(){var n=this;if(!n._options._isShow){if(!t.os.ios||t.browser.uc||t.browser.qq||t.browser.chrome)return n;var r=new e.Event("beforeshow");n.trigger(r);if(r.isDefaultPrevented())return n;n.$el.css("display","block"),n._options._isShow=!0}return n},hide:function(){var e=this;return e._options._isShow&&(e.$el.css("display","none"),e._options._isShow=!1,e.trigger("afterhide")),e}})})(gmu,gmu.$);