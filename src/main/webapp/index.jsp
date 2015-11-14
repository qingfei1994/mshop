<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head lang="zh_CN">
    <title>嘉宝微商城</title>
    <meta charset="utf-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta content="width=device-width, initial-scale=1.0, maximum-scale=1.0,  user-scalable=no" name="viewport" />
    <meta content="yes" name="apple-mobile-web-app-capable" />
    <meta content="black" name=" apple-mobile-web-app-status-bar-style" />
    <meta content="telephone=no" name="format-detection" />
    <meta http-equiv="Cache-Control" content="no-cache" />
	
    <link rel="stylesheet" type="text/css" href="assets/wap/css/common/reset.css"/>
    <link rel="stylesheet" type="text/css" href="assets/wap/gmu/css/widget/panel/panel.css" />
    <link rel="stylesheet" type="text/css" href="assets/wap/gmu/css/widget/panel/panel.default.css" />
    <link rel="stylesheet" type="text/css" href="assets/wap/gmu/css/widget/button/button.css" />
    <link rel="stylesheet" type="text/css" href="assets/wap/gmu/css/widget/button/button.default.css" />
    
</head>
<body>
	<div id="page">
		<!-- <img src="/1.jpg" style="width:100%;height:200px;"/> -->
	    <div class="cont">
	        <h1>panel组件</h1><br>
	        <h1>panel组件</h1><br>
	        <h1>panel组件</h1><br>
	        <h1>panel组件</h1><br>
	        <button class="ui-btn" id="open">打开</button>
	        <h1>panel组件</h1><br>
	        <h1>panel组件</h1><br>
	        <h1>panel组件</h1><br>
	        <h1>panel组件</h1><br>
	        <h1>panel组件</h1><br>
	        <h1>panel组件</h1><br>
	        <h1>panel组件</h1><br>
	        <h1>panel组件</h1><br>
	        <h1>panel组件</h1><br>
	        <h1>panel组件</h1><br>
	        <h1>panel组件</h1><br>
	        <h1>panel组件</h1><br>
	        <h1>panel组件</h1><br>
	    </div>
	    <div class="panel wrapper">
	        <div class="panel-scroller">
	        	<h1>panel组件</h1><br>
	        	<h1>panel组件</h1><br>
	        	<h1>panel组件</h1><br>
	        	<h1>panel组件</h1><br>
	        	<button id="close">关闭</button>
	        	<a href="wap/usercenter" id="turn">跳转</a><br>
	        	<h1>panel组件</h1><br>
	        	<h1>panel组件</h1><br>
	        	<h1>panel组件</h1><br>
	        	<h1>panel组件</h1><br>
	        	<h1>panel组件</h1><br>
	        	<h1>panel组件</h1><br>
	        	<h1>panel组件</h1><br>
	        	<h1>panel组件</h1><br>
	        	<h1>panel组件</h1><br>
	        	<h1>panel组件</h1><br>
	        	<h1>panel组件</h1><br>
	        	<h1>panel组件</h1><br>
	        	<h1>panel组件</h1><br>
	        	<h1>panel组件</h1><br>
	        	<h1>panel组件</h1><br>
	        	<h1>panel组件</h1><br>
	        	<h1>panel组件</h1><br>
	        </div>
	    </div>
	</div>


	<script type="text/javascript" src="assets/requirejs/require.js"></script>
	
	<script type="text/javascript" >
		var mssRequireConfig = {
			baseUrl: 'assets/wap/scripts',
			
			paths: {
				// PLUGINS
				"zepto": '../gmu/dist/zepto.min',
				"gmu": '../gmu/dist/gmu.min',
				"add2desktop": '../gmu/dist/widget/add2desktop/add2desktop',
				"button": '../gmu/dist/widget/button/button',
				"button_input": '../gmu/dist/widget/button/$input',
				"calendar": '../gmu/dist/widget/calendar/calendar',
				"calendar_picker": '../gmu/dist/widget/calendar/$picker',
				"dialog": '../gmu/dist/widget/dialog/dialog',
				"dialog_position": '../gmu/dist/widget/dialog/$position',
				"dropmenu": '../gmu/dist/widget/dropmenu/dropmenu',
				"dropmenu_horizontal": '../gmu/dist/widget/dropmenu/horizontal',
				"dropmenu_placement": '../gmu/dist/widget/dropmenu/placement',
				"gotop": '../gmu/dist/widget/gotop/gotop',
				"gotop_iscroll": '../gmu/dist/widget/gotop/$iscroll',
				"historylist": '../gmu/dist/widget/historylist/historylist',
				"navigator": '../gmu/dist/widget/navigator/navigator',
				"navigator_evenness": '../gmu/dist/widget/navigator/evenness',
				"navigator_scrolltonext": '../gmu/dist/widget/navigator/scrolltonext',
				"navigator_scrollable": '../gmu/dist/widget/navigator/$scrollable',
				"panel": '../gmu/dist/widget/panel/panel',
				"popover": '../gmu/dist/widget/popover/popover',
				"popover_arrow": '../gmu/dist/widget/popover/arrow',
				"popover_collision": '../gmu/dist/widget/popover/collision',
				"popover_dismissible": '../gmu/dist/widget/popover/dismissible',
				"popover_placement": '../gmu/dist/widget/popover/placement',
				"progressbar": '../gmu/dist/widget/progressbar/progressbar',
				"refresh": '../gmu/dist/widget/refresh/refresh',
				"refresh_iscroll": '../gmu/dist/widget/refresh/$iscroll',
				"refresh_lite": '../gmu/dist/widget/refresh/$lite',
				"refresh_iOS5": '../gmu/dist/widget/refresh/$iOS5',
				"slider": '../gmu/dist/widget/slider/slider',
				"slider_imgzoom": '../gmu/dist/widget/slider/imgzoom',
				"slider_dots": '../gmu/dist/widget/slider/dots',
				"slider_arrow": '../gmu/dist/widget/slider/arrow',
				"slider_touch": '../gmu/dist/widget/slider/$touch',
				"slider_multiview": '../gmu/dist/widget/slider/$multiview',
				"slider_lazyloadimg": '../gmu/dist/widget/slider/$lazyloadimg',
				"slider_dynamic": '../gmu/dist/widget/slider/$dynamic',
				"slider_autoplay": '../gmu/dist/widget/slider/$autoplay',
				"suggestion": '../gmu/dist/widget/suggestion/suggestion',
				"suggestion_sendrequest": '../gmu/dist/widget/suggestion/sendrequest',
				"suggestion_renderlist": '../gmu/dist/widget/suggestion/renderlist',
				"suggestion_compatdata": '../gmu/dist/widget/suggestion/compatdata',
				"suggestion_quickdelete": '../gmu/dist/widget/suggestion/$quickdelete',
				"suggestion_posadapt": '../gmu/dist/widget/suggestion/$posadapt',
				"suggestion_iscroll": '../gmu/dist/widget/suggestion/$iscroll',
				"tabs": '../gmu/dist/widget/tabs/tabs',
				"tabs_swipe": '../gmu/dist/widget/tabs/$swipe',
				"tabs_ajax": '../gmu/dist/widget/tabs/$ajax',
				"toolbar": '../gmu/dist/widget/toolbar/toolbar',
				"toolbar_position": '../gmu/dist/widget/toolbar/$position'
				
				// CUSTOM
				
		    },
		    shim: {
		    
		    	//toolbar
		    	"toolbar_position": {
		    		deps: ["toolbar"]
		    	},
		    	"toolbar": {
		    		deps: ["gmu"]
		    	},
		    
		    	//tabs
		    	"tabs_ajax": {
		    		deps: ["tabs"]
		    	},
		    	"tabs_swipe": {
		    		deps: ["tabs"]
		    	},
		    	"tabs": {
		    		deps: ["gmu"]
		    	},
		    
		    	//suggestion
		    	"suggestion_iscroll": {
		    		deps: ["suggestion"]
		    	},
		    	"suggestion_posadapt": {
		    		deps: ["suggestion"]
		    	},
		    	"suggestion_quickdelete": {
		    		deps: ["suggestion"]
		    	},
		    	"suggestion_compatdata": {
		    		deps: ["suggestion"]
		    	},
		    	"suggestion_renderlist": {
		    		deps: ["suggestion"]
		    	},
		    	"suggestion_sendrequest": {
		    		deps: ["suggestion"]
		    	},
		    	"suggestion": {
		    		deps: ["gmu"]
		    	},
		    
		    	//slider
		    	"slider_autoplay": {
		    		deps: ["slider"]
		    	},
		    	"slider_dynamic": {
		    		deps: ["slider"]
		    	},
		    	"slider_lazyloadimg": {
		    		deps: ["slider"]
		    	},
		    	"slider_multiview": {
		    		deps: ["slider"]
		    	},
		    	"slider_touch": {
		    		deps: ["slider"]
		    	},
		    	"slider_arrow": {
		    		deps: ["slider"]
		    	},
		    	"slider_dots": {
		    		deps: ["slider"]
		    	},
		    	"slider_imgzoom": {
		    		deps: ["slider"]
		    	},
		    	"slider": {
		    		deps: ["gmu"]
		    	},
		    
		    	//refresh
		    	"refresh_iOS5": {
		    		deps: ["refresh"]
		    	},
		    	"refresh_lite": {
		    		deps: ["refresh"]
		    	},
		    	"refresh_iscroll": {
		    		deps: ["refresh"]
		    	},
		    	"refresh": {
		    		deps: ["gmu"]
		    	},
		    
		    	//progressbar
		    	"progressbar": {
		    		deps: ["gmu"]
		    	},
		    
		    	//popover
		    	"popover_placement": {
		    		deps: ["popover"]
		    	},
		    	"popover_dismissible": {
		    		deps: ["popover"]
		    	},
		    	"popover_collision": {
		    		deps: ["popover"]
		    	},
		    	"popover_arrow": {
		    		deps: ["popover"]
		    	},
		    	"popover": {
		    		deps: ["gmu"]
		    	},
		    
		    	//panel
		    	"panel": {
		    		deps: ["gmu"]
		    	},
		    
		    	//navigator
		    	"navigator_scrollable": {
		    		deps: ["navigator"]
		    	},
		    	"navigator_scrolltonext": {
		    		deps: ["navigator"]
		    	},
		    	"navigator_evenness": {
		    		deps: ["navigator"]
		    	},
		    	"navigator": {
		    		deps: ["gmu"]
		    	},
		    
		    	//historylist
		    	"historylist": {
		    		deps: ["gmu"]
		    	},
		    
		    	//gotop
		    	"gotop_iscroll": {
		    		deps: ["gotop"]
		    	},
		    	"gotop": {
		    		deps: ["gmu"]
		    	},
		    
		    	//dropmenu
		    	"dropmenu_placement": {
		    		deps: ["dropmenu"]
		    	},
		    	"dropmenu_horizontal": {
		    		deps: ["dropmenu"]
		    	},
		    	"dropmenu": {
		    		deps: ["gmu"]
		    	},
		    	
		    	//dialog
		    	"dialog_position": {
		    		deps: ["dialog"]
		    	},
		    	"dialog": {
		    		deps: ["gmu"]
		    	},
		    	
		    	// calendar
		    	"calendar_picker": {
		    		deps: ["calendar"]
		    	},
		    	"calendar": {
		    		deps: ["gmu"]
		    	},
		    	
		    	//button
		    	"button_input": {
		    		deps: ["button"]
		    	},
		    	"button": {
		    		deps: ["gmu"]
		    	},
		    	
		    	//add2desktop
		    	"add2desktop": {
		    		deps: ["gmu"]
		    	},
		    	
		    	//gmu
		    	"gmu": {
		    		deps: ["zepto"]
		    	}
		    }
		};
		requirejs.config(mssRequireConfig);
		require(['panel','button'], function(){
			$(document).ready(function(){
			    $(function ($) {
			
			        //初始化panel，panel是iscroll
			        $('.panel').css({
			            'height': window.innerHeight,
			            'padding-top': 0,
			            'padding-bottom': 0
			        }).iScroll().panel({
			            contentWrap: $('.cont'),
			            scrollMode: 'fix',
			            display: 'overlay',
			            swipeClose: false
			        }).on('open', function () {
			            $('.panel').iScroll('refresh');
			        });
			        
			        function resetHeight() {
			            $('.panel').css('height', window.innerHeight).iScroll('refresh');
			        }
			        $(window).on('scrollStop ortchange resize', resetHeight);
			
			        $("#open").on("tap", function(){
			        	$('.panel').panel('toggle');
			        });
			
			        $("#open").button();
			        $("#close").button();
			        $("#turn").button();
			        $("#close").on("tap", function(){
			        	$('.panel').panel('close');
			        });
			    }(Zepto));
			}); 
		});
	</script>
</body>
</html>