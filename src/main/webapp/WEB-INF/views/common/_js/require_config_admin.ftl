<script type="text/javascript">

var mssRequireConfig = {
	baseUrl: 'assets/admin/scripts',
	
	paths: {
		// PLUGINS
		"jquery": '../../plugins/jquery-1.10.2.min',
		"bootstrap": '../../plugins/bootstrap/js/bootstrap.min',
		"bootstrap_hover_dropdown": '../../plugins/bootstrap-hover-dropdown/twitter-bootstrap-hover-dropdown.min',
		"moment": '../../plugins/bootstrap-daterangepicker/moment.min',
		"jquery_slimscroll": '../../plugins/jquery-slimscroll/jquery.slimscroll.min',
		"jquery_blockui": '../../plugins/jquery.blockui.min',
		"jquery_cookie": '../../plugins/jquery.cookie.min',
		"jquery_uniform": '../../plugins/uniform/jquery.uniform.min',
		"bootstrap_datepicker": '../../plugins/bootstrap-datepicker/js/bootstrap-datepicker',
		"bootstrap_datepicker_zh_cn": '../../plugins/bootstrap-datepicker/js/locales/bootstrap-datepicker.zh-CN',
		"bootstrap_daterangepicker": '../../plugins/bootstrap-daterangepicker/daterangepicker',
        "bootstrap_datetimepicker": '../../plugins/bootstrap-datetimepicker/js/bootstrap-datetimepicker',
        "bootstrap_wizard": '../../plugins/bootstrap-wizard/jquery.bootstrap.wizard.min',
        "bootstrap_datetimepicker_zh_cn": '../../plugins/bootstrap-datetimepicker/js/locales/bootstrap-datetimepicker.zh-CN',
		"jquery_validate" : "../../plugins/jquery-validation/dist/jquery.validate.min",
		"jquery_validator_messages" : "../../plugins/jquery-validation/dist/jquery.validator.messages",
		"bootbox" : "../../plugins/bootbox.min",
		"jquery_treetable" : "../../plugins/ludo-jquery-treetable/jquery.treetable",
		"toastr" : "../../plugins/bootstrap-toastr/toastr.min",
		"ueditor_config" : "../../plugins/ueditor/ueditor.config",
		"ueditor" : "../../plugins/ueditor/ueditor.all",
		"jquery_nestable" : "../../plugins/jquery-nestable/jquery.nestable",
		"moxie" : "../../plugins/plupload/moxie",
		"plupload" : "../../plugins/plupload/plupload.min",
		"jquery_event_drag_live" : "../../plugins/jquery.event.drag/jquery.event.drag.live-2.2",
		"jquery_event_drag" : "../../plugins/jquery.event.drag/jquery.event.drag-2.2",
        "select2" : "../../plugins/select2/select2.min",
        "select2_zh_cn" : "../../plugins/select2/select2_locale_zh-CN",
		"template" : "../../plugins/artTemplate/template-debug",
		"ajaxfileupload" : "../../plugins/ajaxfileupload",
		
		// CUSTOM
		"app": 'modules/app/app',
		"util": 'modules/app/util'
    },
    shim: {
    	//jquery.event.drag
    	"jquery_event_drag_live" : {
    		deps: ["jquery", "jquery_event_drag"]
    	},
    	//jquery_nestable：若需要则引入jquery_nestable即可，by chq
    	"jquery_nestable":{
    		deps:["jquery"]
    	},
    	//plupload: 若需要则引入plupload即可  by HONG
    	"plupload" : {
    		deps: ["jquery", "moxie"],
    		exports: "plupload"
    	},

    	//ueditor:若需要则引入ueditor即可  by HONG
    	"ueditor" : {
    		deps: ["ueditor_config"]
    	},
    	
		//jquery_treetable:若需要则引入jquery_treetable即可
		"jquery_treetable" : {
			deps: ["jquery"]
		},

    	//jquery_validate:若需要则引入jquery_validator_messages即可
    	"jquery_validate": {
    		deps: ["jquery"]
    	},
    	"jquery_validator_messages": {
    		deps: ["jquery_validate"]
    	},
    	
    	//bootbox:若需要则引入bootbox即可
    	"bootbox": {
    		deps: ["bootstrap"],
    		exports: "Bootbox"
    	},
    	
    	//bootstrap_datepicker:若需要则引入bootstrap_datepicker_zh_cn即可
    	"bootstrap_datepicker": {
    		deps: ["bootstrap"]
    	},
    	"bootstrap_datepicker_zh_cn": {
    		deps: ["bootstrap_datepicker"]
    	},
    	
    	//bootstrap_daterangepicker:若需要则引入bootstrap_daterangepicker即可
    	"bootstrap_daterangepicker": {
    		deps: ["bootstrap", "moment"]
    	},

        //bootstrap_datetimepicker:若需要则引入bootstrap_datetimepicker_zh_cn即可
        "bootstrap_datetimepicker": {
            deps: ["bootstrap"]
        },
        "bootstrap_datetimepicker_zh_cn": {
            deps: ["bootstrap_datetimepicker"]
        },
        //bootstrap_wizard:若需要则引入bootstrap_wizard即可 add by chq
		"bootstrap_wizard": {
            deps: ["bootstrap","jquery"]
        },
		//select2:若需要则引入select2_zh_cn即可
        "select2_zh_cn": {
            deps: ["select2"]
        },

    	//toastr:已在index中引入
    	"toastr" : {
    		deps: ["bootstrap"],
    		exports: "Toastr"
    	},
    	
    	//jquery_slimscroll:已在index中引入
    	"jquery_slimscroll": {
    		deps: ["jquery"]
    	},

    	//jquery_blockui:已在index中引入
    	"jquery_blockui": {
    		deps: ["jquery"]
    	},

    	//jquery_cookie:已在index中引入
    	"jquery_cookie": {
    		deps: ["jquery"]
    	},

    	//jquery_uniform:已在index中引入
    	"jquery_uniform": {
    		deps: ["jquery"]
    	},

    	//bootstrap_hover_dropdown:已在index中引入
    	"bootstrap_hover_dropdown": {
    		deps: ["bootstrap"]
    	},

    	//bootstrap:已在index中引入
    	"bootstrap": {
    		deps: ["jquery"]
    	},
    	//app:已在index中引入
    	"util": {
    		deps: ["jquery"]
    	},
    	"app": {
    		deps: ["util", "toastr"]
    	}
    	
    }
};

</script>