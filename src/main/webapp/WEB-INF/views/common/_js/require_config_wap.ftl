<script type="text/javascript">

var mssRequireConfig = {
	baseUrl: 'assets/wap/scripts',
	
	paths: {
		// PLUGINS
		"zepto": '../gmu/dist/zepto.min',
		"gmu": '../gmu/dist/gmu.min',
		"fastclick": '../gmu/dist/fastclick',

		<#--
		"add2desktop": '../gmu/dist/widget/add2desktop/add2desktop',
		-->
		"button": '../gmu/dist/widget/button/button',
		"button_input": '../gmu/dist/widget/button/$input',
		<#--
		"calendar": '../gmu/dist/widget/calendar/calendar',
		"calendar_picker": '../gmu/dist/widget/calendar/$picker',
		-->
		"dialog": '../gmu/dist/widget/dialog/dialog',
		"dialog_position": '../gmu/dist/widget/dialog/$position',
		<#--
		"dropmenu": '../gmu/dist/widget/dropmenu/dropmenu',
		"dropmenu_horizontal": '../gmu/dist/widget/dropmenu/horizontal',
		"dropmenu_placement": '../gmu/dist/widget/dropmenu/placement',
		-->
		"gotop": '../gmu/dist/widget/gotop/gotop',
		"gotop_iscroll": '../gmu/dist/widget/gotop/$iscroll',
		<#--
		"historylist": '../gmu/dist/widget/historylist/historylist',
		-->
		<#--
		"navigator": '../gmu/dist/widget/navigator/navigator',
		"navigator_evenness": '../gmu/dist/widget/navigator/evenness',
		"navigator_scrolltonext": '../gmu/dist/widget/navigator/scrolltonext',
		"navigator_scrollable": '../gmu/dist/widget/navigator/$scrollable',
		-->
		"panel": '../gmu/dist/widget/panel/panel',
		<#--
		"popover": '../gmu/dist/widget/popover/popover',
		"popover_arrow": '../gmu/dist/widget/popover/arrow',
		"popover_collision": '../gmu/dist/widget/popover/collision',
		"popover_dismissible": '../gmu/dist/widget/popover/dismissible',
		"popover_placement": '../gmu/dist/widget/popover/placement',-->
		<#--"progressbar": '../gmu/dist/widget/progressbar/progressbar',
		-->
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

		<#--
		"suggestion": '../gmu/dist/widget/suggestion/suggestion',
		"suggestion_sendrequest": '../gmu/dist/widget/suggestion/sendrequest',
		"suggestion_renderlist": '../gmu/dist/widget/suggestion/renderlist',
		"suggestion_compatdata": '../gmu/dist/widget/suggestion/compatdata',
		"suggestion_quickdelete": '../gmu/dist/widget/suggestion/$quickdelete',
		"suggestion_posadapt": '../gmu/dist/widget/suggestion/$posadapt',
		"suggestion_iscroll": '../gmu/dist/widget/suggestion/$iscroll',
		-->
		<#--
		"tabs": '../gmu/dist/widget/tabs/tabs',
		"tabs_swipe": '../gmu/dist/widget/tabs/$swipe',
		"tabs_ajax": '../gmu/dist/widget/tabs/$ajax',
		-->
		<#--
		"toolbar": '../gmu/dist/widget/toolbar/toolbar',
		"toolbar_position": '../gmu/dist/widget/toolbar/$position'
		-->
        "baidu_template": '../plugins/template/baiduTemplate',

		// CUSTOM
		"app": 'common/app'
    },
    shim: {
    
    	<#--//toolbar
    	"toolbar_position": {
    		deps: ["toolbar"]
    	},
    	"toolbar": {
    		deps: ["gmu"]
    	},-->
    
    	<#--//tabs
    	"tabs_ajax": {
    		deps: ["tabs"]
    	},
    	"tabs_swipe": {
    		deps: ["tabs"]
    	},
    	"tabs": {
    		deps: ["gmu"]
    	},-->
    
    	<#--//suggestion
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
    	},-->
    

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
    
    	<#--//progressbar
    	"progressbar": {
    		deps: ["gmu"]
    	},-->
    
    	<#--//popover
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
    	},-->
    
    	//panel
    	"panel": {
    		deps: ["gmu"]
    	},
    
    	<#--//navigator
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
    	},-->
    
    	<#--//historylist
    	"historylist": {
    		deps: ["gmu"]
    	},-->
    
    	//gotop
    	"gotop_iscroll": {
    		deps: ["gotop"]
    	},
    	"gotop": {
    		deps: ["gmu"]
    	},
    
    	<#--//dropmenu
    	"dropmenu_placement": {
    		deps: ["dropmenu"]
    	},
    	"dropmenu_horizontal": {
    		deps: ["dropmenu"]
    	},
    	"dropmenu": {
    		deps: ["gmu"]
    	},-->
    	
    	//dialog
    	"dialog_position": {
    		deps: ["dialog"]
    	},
    	"dialog": {
    		deps: ["gmu"]
    	},
    	
    	<#--// calendar
    	"calendar_picker": {
    		deps: ["calendar"]
    	},
    	"calendar": {
    		deps: ["gmu"]
    	},-->
    	
    	//button
    	"button_input": {
    		deps: ["button"]
    	},
    	"button": {
    		deps: ["gmu"]
    	},
    	
    	<#--//add2desktop
    	"add2desktop": {
    		deps: ["gmu"]
    	},-->
    	
    	//gmu
    	"gmu": {
    		deps: ["zepto"]
    	},
    	
    	//fastclick
    	"fastclick": {
    		deps: ["zepto"]
    	},
    	
    	//app
    	"app": {
    		deps: ["dialog"]
    	}
    }
};

</script>