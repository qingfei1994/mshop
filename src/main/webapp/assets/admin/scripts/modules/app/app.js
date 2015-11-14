/**
Core script to handle the entire theme and core functions
*/
var App = function () {
	"use strict";

	// add by YLM ====================================
	var error404Page = '<div class="col-md-12 page-404">' +
							'<div class="number">404</div>' + 
							'<div class="details">' +
								'<h3>哎呀！页面出错了...>_<</h3>' +
								'<p>请求的页面找不到，请刷新页面试试吧...<br>' +
								'或者返回<a href="admin">首页</a>' +
								'</p>' +
							'</div>' +
						'</div>';
	
	var error500Page = '<div class="col-md-12 page-500">' +
							'<div class="number">500</div>' + 
							'<div class="details">' +
								'<h3>哎呀！服务器出错了...>_<</h3>' +
								'<p>也许攻城狮们正在抢修，请稍候再试试吧...<br>' +
								'或者返回<a href="admin">首页</a>' +
								'</p>' +
							'</div>' +
						'</div>';
	
	var errorElsePage = '<div class="col-md-12 page-500">' +
							'<div class="number">SOS</div>' + 
							'<div class="details">' +
								'<h3>哎呀！系统出错了...>_<</h3>' +
								'<p>也许攻城狮们正在抢修，请稍候再试试吧...<br>' +
								'或者返回<a href="admin">首页</a>' +
								'</p>' +
							'</div>' +
						'</div>';
	//后台返回的错误信息的长度上限
	var errorTextMaxLength = 100;
	//后台返回的错误信息的长度上限
	var toastrSuccessTip = "操作成功";
	var toastrErrorTip = "网络错误";
	//=================================================
	
	
    // IE mode
    var isRTL = false;
    var isIE8 = false;
    var isIE9 = false;
    var isIE10 = false;

    var sidebarWidth = 215;
    var sidebarCollapsedWidth = 35;

    var responsiveHandlers = [];

    // theme layout color set
    /*var layoutColorCodes = {
        'blue': '#4b8df8',
        'red': '#e02222',
        'green': '#35aa47',
        'purple': '#852b99',
        'grey': '#555555',
        'light-grey': '#fafafa',
        'yellow': '#ffb848'
    };*/

    // To get the correct viewport width based on  http://andylangton.co.uk/articles/javascript/get-viewport-size-javascript/
    var _getViewPort = function () {
        var e = window, a = 'inner';
        if (!('innerWidth' in window)) {
            a = 'client';
            e = document.documentElement || document.body;
        }
        return {
            width: e[a + 'Width'],
            height: e[a + 'Height']
        };
    };

    // initializes main settings
    var handleInit = function () {

        if ($('body').css('direction') === 'rtl') {
            isRTL = true;
        }

        isIE8 = !! navigator.userAgent.match(/MSIE 8.0/);
        isIE9 = !! navigator.userAgent.match(/MSIE 9.0/);
        isIE10 = !! navigator.userAgent.match(/MSIE 10.0/);

        if (isIE10) {
            jQuery('html').addClass('ie10'); // detect IE10 version
        }
        
        if (isIE10 || isIE9 || isIE8) {
            jQuery('html').addClass('ie'); // detect IE10 version
        }

        /*
          Virtual keyboards:
          Also, note that if you're using inputs in your modal – iOS has a rendering bug which doesn't 
          update the position of fixed elements when the virtual keyboard is triggered  
        */
        var deviceAgent = navigator.userAgent.toLowerCase();
        if (deviceAgent.match(/(iphone|ipod|ipad)/)) {
            $(document).on('focus', 'input, textarea', function () {
                $('.header').hide();
                $('.footer').hide();
            });
            $(document).on('blur', 'input, textarea', function () {
                $('.header').show();
                $('.footer').show();
            });
        }
    };

    var handleSidebarState = function () {
        // remove sidebar toggler if window width smaller than 992(for tablet and phone mode)
        var viewport = _getViewPort();
        if (viewport.width < 992) {
            $('body').removeClass("page-sidebar-closed");
        }
    };

    // runs callback functions set by App.addResponsiveHandler().
    var runResponsiveHandlers = function () {
        // reinitialize other subscribed elements
        for (var i in responsiveHandlers) {
            var each = responsiveHandlers[i];
            each.call();
        }
    };

    // reinitialize the laypot on window resize
    var handleResponsive = function () {
        handleSidebarState();
        handleSidebarAndContentHeight();
        handleFixedSidebar();
        runResponsiveHandlers();
    };

    // initialize the layout on page load
    var handleResponsiveOnInit = function () {
        handleSidebarState();
        handleSidebarAndContentHeight();
    };

    // handle the layout reinitialization on window resize
    var handleResponsiveOnResize = function () {
        var resize;
        if (isIE8) {
            var currheight;
            $(window).resize(function () {
                if (currheight == document.documentElement.clientHeight) {
                    return; //quite event since only body resized not window.
                }
                if (resize) {
                    clearTimeout(resize);
                }
                resize = setTimeout(function () {
                    handleResponsive();
                }, 50); // wait 50ms until window resize finishes.                
                currheight = document.documentElement.clientHeight; // store last body client height
            });
        } else {
            $(window).resize(function () {
                if (resize) {
                    clearTimeout(resize);
                }
                resize = setTimeout(function () {
                    handleResponsive();
                }, 50); // wait 50ms until window resize finishes.
            });
        }
    };

    //* BEGIN:CORE HANDLERS *//
    // this function handles responsive layout on screen size resize or mobile device rotate.


    // Handle header menu
    //misuosi: add by YLM
    /*var handleHeaderMenu = function () {
    	
    	//handle ajax links within page header
    	//misuosi:add by YLM
    	jQuery('.page-header').on('click', '.ajaxify', function (e) {
    		e.preventDefault();
    		App.scrollTop();
    		
    		var url = $(this).attr("url");
    		var pageContent = $('.page-content');
    		var pageContentBody = $('.page-content .page-content-body');
    		
    		var menuContainer = $('.page-sidebar ul');
            menuContainer.children('li.active').removeClass('active');
            menuContainer.children('arrow.open').removeClass('open');
    		
    		App.blockUI(pageContent, false);
    		
    		$.ajax({
    			type: "GET",
    			cache: false,
    			url: url,
    			dataType: "html",
    			success: function (res) {
    				pageContentBody.html(res);
    				App.fixContentHeight(); // fix content height
    				App.initAjax(); // initialize core stuff
    				App.unblockUI(pageContent);
    			},
    			error: function (xhr, ajaxOptions, thrownError) {
    				//使用自定义的错误页面  by YLM
    				if(xhr.status === 404) {
    					pageContentBody.html(error404Page);
    				} else if(xhr.status === 500) {
    					pageContentBody.html(error500Page);
    				} else {
    					pageContentBody.html(errorElsePage);
    				}
    				App.unblockUI(pageContent);
    			}
    		});
    	});
    };*/
    
    // Set proper height for sidebar and content. The content and sidebar height must be synced always.
    var handleSidebarAndContentHeight = function () {
        var content = $('.page-content');
        var sidebar = $('.page-sidebar');
        var body = $('body');
        var height;

        if (body.hasClass("page-footer-fixed") === true && body.hasClass("page-sidebar-fixed") === false) {
            var available_height = $(window).height() - $('.footer').outerHeight();
            if (content.height() < available_height) {
                content.attr('style', 'min-height:' + available_height + 'px !important');
            }
        } else {
            if (body.hasClass('page-sidebar-fixed')) {
                height = _calculateFixedSidebarViewportHeight();
            } else {
                height = sidebar.height() + 20;
            }
            if (height >= content.height()) {
                content.attr('style', 'min-height:' + height + 'px !important');
            }
        }
    };

    // Handle sidebar menu
    var handleSidebarMenu = function () {
        jQuery('.page-sidebar').on('click', 'li > a', function (e) {
            if ($(this).next().hasClass('sub-menu') == false) {
                if ($('.btn-navbar').hasClass('collapsed') == false) {
                    $('.btn-navbar').click();
                }
                if (!$(this).parent().hasClass('btn-menu')) {
                    return;
                }
            }

            if ($(this).next().hasClass('sub-menu.always-open')) {
                return;
            }

            var parent = $(this).parent().parent();
            var the = $(this);

            parent.children('li.open').children('a').children('.arrow').removeClass('open');
            parent.children('li.open').children('.sub-menu').slideUp(200);
            parent.children('li.open').removeClass('open');

            var sub = jQuery(this).next();
            var slideOffeset = -200;
            var slideSpeed = 200;

            if (sub.is(":visible")) {
                jQuery('.arrow', jQuery(this)).removeClass("open");
                jQuery(this).parent().removeClass("open");
                sub.slideUp(slideSpeed, function () {
                    if ($('body').hasClass('page-sidebar-fixed') == false && $('body').hasClass('page-sidebar-closed') == false) {
                        //App.scrollTo(the, slideOffeset);
                    }
                    handleSidebarAndContentHeight();
                });
            } else {
                jQuery('.arrow', jQuery(this)).addClass("open");
                jQuery(this).parent().addClass("open");
                sub.slideDown(slideSpeed, function () {
                    if ($('body').hasClass('page-sidebar-fixed') == false && $('body').hasClass('page-sidebar-closed') == false) {
                        //App.scrollTo(the, slideOffeset);
                    }
                    handleSidebarAndContentHeight();
                });
            }

            e.preventDefault();
        });

        // handle ajax links within page sidebar
        jQuery('.page-sidebar').on('click', ' li > a.ajaxify', function (e) {
            e.preventDefault();
            App.scrollTop();

            var url = $(this).attr("url");

            var pageContent = $('.page-container');
            var pageContentBody = $('.page-content .page-content-body');
            App.blockUI(pageContent, false);
            
            var menuContainer = $('.page-sidebar ul');
            menuContainer.children('li.active').removeClass('active');
            menuContainer.children('arrow.open').removeClass('open');

            $(this).parents('li').each(function () {
                $(this).addClass('active');
                $(this).children('a > span.arrow').addClass('open');
            });
            $(this).parents('li').addClass('active');

            $.ajax({
                type: "GET",
                cache: false,
                url: url,
                dataType: "html",
                success: function (res) {
                    pageContentBody.html(res);
                    App.fixContentHeight(); // fix content height
                    App.initAjax(); // initialize core stuff
                    App.unblockUI(pageContent);
                },
                error: function (xhr, ajaxOptions, thrownError) {
                	//使用自定义的错误页面  by YLM
                	if(xhr.status === 404) {
                    	pageContentBody.html(error404Page);
                    } else if(xhr.status === 500) {
                    	pageContentBody.html(error500Page);
                    } else {
                    	pageContentBody.html(errorElsePage);
                    }
                    App.unblockUI(pageContent);
                }
            });
        });
        
    };

    // Helper function to calculate sidebar height for fixed sidebar layout.
    var _calculateFixedSidebarViewportHeight = function () {
        var sidebarHeight = $(window).height() - $('.header').height() + 1;
        if ($('body').hasClass("page-footer-fixed")) {
            sidebarHeight = sidebarHeight - $('.footer').outerHeight();
        }

        return sidebarHeight;
    };

    // Handles fixed sidebar
    var handleFixedSidebar = function () {
        var menu = $('.page-sidebar-menu');

        if (menu.parent('.slimScrollDiv').size() === 1) { // destroy existing instance before updating the height
            menu.slimScroll({
                destroy: true
            });
            menu.removeAttr('style');
            $('.page-sidebar').removeAttr('style');
        }

        if ($('.page-sidebar-fixed').size() === 0) {
            handleSidebarAndContentHeight();
            return;
        }

        var viewport = _getViewPort();
        if (viewport.width >= 992) {
            var sidebarHeight = _calculateFixedSidebarViewportHeight();

            menu.slimScroll({
                size: '7px',
                color: '#a1b2bd',
                opacity: .3,
                position: isRTL ? 'left' : 'right',
                height: sidebarHeight,
                allowPageScroll: false,
                disableFadeOut: false
            });
            handleSidebarAndContentHeight();
        }
    };

    // Handles the sidebar menu hover effect for fixed sidebar.
    var handleFixedSidebarHoverable = function () {
        if ($('body').hasClass('page-sidebar-fixed') === false) {
            return;
        }

        $('.page-sidebar').off('mouseenter').on('mouseenter', function () {
            var body = $('body');

            if ((body.hasClass('page-sidebar-closed') === false || body.hasClass('page-sidebar-fixed') === false) || $(this).hasClass('page-sidebar-hovering')) {
                return;
            }

            body.removeClass('page-sidebar-closed').addClass('page-sidebar-hover-on');
            $(this).addClass('page-sidebar-hovering');
            $(this).animate({
                width: sidebarWidth
            }, 400, '', function () {
                $(this).removeClass('page-sidebar-hovering');
            });
        });

        $('.page-sidebar').off('mouseleave').on('mouseleave', function () {
            var body = $('body');

            if ((body.hasClass('page-sidebar-hover-on') === false || body.hasClass('page-sidebar-fixed') === false) || $(this).hasClass('page-sidebar-hovering')) {
                return;
            }

            $(this).addClass('page-sidebar-hovering');
            $(this).animate({
                width: sidebarCollapsedWidth
            }, 400, '', function () {
                $('body').addClass('page-sidebar-closed').removeClass('page-sidebar-hover-on');
                $(this).removeClass('page-sidebar-hovering');
            });
        });
    };

    // Handles sidebar toggler to close/hide the sidebar.
    var handleSidebarToggler = function () {
        var viewport = _getViewPort();
        if ($.cookie('sidebar_closed') === '1' && viewport.width >= 992) {
            $('body').addClass('page-sidebar-closed');
        }

        // handle sidebar show/hide
        $('.page-sidebar').on('click', '.sidebar-toggler', function (e) {
            var body = $('body');
            var sidebar = $('.page-sidebar');

            if ((body.hasClass("page-sidebar-hover-on") && body.hasClass('page-sidebar-fixed')) || sidebar.hasClass('page-sidebar-hovering')) {
                body.removeClass('page-sidebar-hover-on');
                sidebar.css('width', '').hide().show();
                $.cookie('sidebar_closed', '0');
                e.stopPropagation();
                runResponsiveHandlers();
                return;
            }

            if (body.hasClass("page-sidebar-closed")) {
                body.removeClass("page-sidebar-closed");
                if (body.hasClass('page-sidebar-fixed')) {
                    sidebar.css('width', '');
                }
                $.cookie('sidebar_closed', '0');
            } else {
                body.addClass("page-sidebar-closed");
                $.cookie('sidebar_closed', '1');
            }
            runResponsiveHandlers();
        });
    };

    /*var handleQuickSearch = function() {

        // handle search for header search input on enter press
        $('.search-form-header').on('keypress', 'input.form-control', function (e) {
            if (e.which == 13) {
                $('.search-form-header').submit();
                return false;
            }
        });

        // handle search for header search input on icon click
        $('.search-form-header').on('click', '.icon-search', function (e) {
            $('.search-form-header').submit();
            return false;
        });

        // handle search for sidebar search input on enter press
        $('.search-form-sidebar').on('keypress', 'input.form-control', function (e) {
            if (e.which == 13) {
                $('.search-form-sidebar').submit();
                return false;
            }
        });

        // handle search for sidebar search input on icon click
        $('.search-form-sidebar').on('click', '.icon-search', function (e) {
            $('.search-form-sidebar').submit();
            return false;
        });
    };*/

    // Handles the go to top button at the footer
    var handleGoTop = function () {
        /* set variables locally for increased performance */
        jQuery('.footer').on('click', '.go-top', function (e) {
            App.scrollTo();
            e.preventDefault();
        });
    };

    // Handles portlet tools & actions
    /*var handlePortletTools = function () {
        jQuery('body').on('click', '.portlet > .portlet-title > .tools > a.remove', function (e) {
            e.preventDefault();
            jQuery(this).closest(".portlet").remove();
        });

        jQuery('body').on('click', '.portlet > .portlet-title > .tools > a.reload', function (e) {
            e.preventDefault();
            var el = jQuery(this).closest(".portlet").children(".portlet-body");
            App.blockUI(el);
            window.setTimeout(function () {
                App.unblockUI(el);
            }, 1000);
        });

        jQuery('body').on('click', '.portlet > .portlet-title > .tools > .collapse, .portlet .portlet-title > .tools > .expand', function (e) {
            e.preventDefault();
            var el = jQuery(this).closest(".portlet").children(".portlet-body");
            if (jQuery(this).hasClass("collapse")) {
                jQuery(this).removeClass("collapse").addClass("expand");
                el.slideUp(200);
            } else {
                jQuery(this).removeClass("expand").addClass("collapse");
                el.slideDown(200);
            }
        });
    };*/

    // Handles custom checkboxes & radios using jQuery Uniform plugin
    var handleUniform = function () {
        if (!jQuery().uniform) {
            return;
        }
        var test = $("input[type=checkbox]:not(.toggle), input[type=radio]:not(.toggle, .star)");
        if (test.size() > 0) {
            test.each(function () {
                if ($(this).parents(".checker").size() == 0) {
                    $(this).show();
                    $(this).uniform();
                }
            });
        }
    };

    // Handles Bootstrap Accordions.
    /*var handleAccordions = function () {
        var lastClicked;
        //add scrollable class name if you need scrollable panes
        jQuery('body').on('click', '.accordion.scrollable .accordion-toggle', function () {
            lastClicked = jQuery(this);
        }); //move to faq section

        jQuery('body').on('show.bs.collapse', '.accordion.scrollable', function () {
            jQuery('html,body').animate({
                scrollTop: lastClicked.offset().top - 150
            }, 'slow');
        });
    };*/

    // Handles Bootstrap Tabs.
    /*var handleTabs = function () {
        // fix content height on tab click
        $('body').on('shown.bs.tab', '.nav.nav-tabs', function () {
            handleSidebarAndContentHeight();
        });

        //activate tab if tab id provided in the URL
        if (location.hash) {
            var tabid = location.hash.substr(1);
            $('a[href="#' + tabid + '"]').click();
        }
    };*/

    // Handles Bootstrap Modals.
    /*var handleModals = function () {

        // fix stackable modal issue: when 2 or more modals opened, closing one of modal will remove .modal-open class. 
        $('body').on('hide.bs.modal', function () {
           if ($('.modal:visible').size() > 1 && $('html').hasClass('modal-open') == false) {
              $('html').addClass('modal-open');
           } else if ($('.modal:visible').size() <= 1) {
              $('html').removeClass('modal-open');
           }
        });
    };*/

    // Handles Bootstrap Tooltips.
    var handleTooltips = function () {
       jQuery('.tooltips').tooltip();
    };

    // Handles Bootstrap Dropdowns
    /*var handleDropdowns = function () {
        
        // For touch supported devices disable the 
        // hoverable dropdowns - data-hover="dropdown"  
        
        if (App.isTouchDevice()) {
            $('[data-hover="dropdown"]').each(function(){
                $(this).parent().off("hover");
                $(this).off("hover");
            });
        }
        
          Hold dropdown on click  
        
        $('body').on('click', '.dropdown-menu.hold-on-click', function (e) {
            e.stopPropagation();
        });
    };*/

    // Handle Hower Dropdowns
    /*var handleDropdownHover = function () {
        $('[data-hover="dropdown"]').dropdownHover();
    };*/

    // Handles Bootstrap Popovers

    // last popep popover
    var lastPopedPopover;

    /*var handlePopovers = function () {
        jQuery('.popovers').popover();

        // close last poped popover

        $(document).on('click.bs.popover.data-api', function (e) {
            if (lastPopedPopover) {
                lastPopedPopover.popover('hide');
            }
        });
    };*/

    // Handles scrollable contents using jQuery SlimScroll plugin.
    /*var handleScrollers = function () {
        $('.scroller').each(function () {
            var height;
            if ($(this).attr("data-height")) {
                height = $(this).attr("data-height");
            } else {
                height = $(this).css('height');
            }
            $(this).slimScroll({
                size: '7px',
                color: ($(this).attr("data-handle-color")  ? $(this).attr("data-handle-color") : '#a1b2bd'),
                railColor: ($(this).attr("data-rail-color")  ? $(this).attr("data-rail-color") : '#333'),
                position: isRTL ? 'left' : 'right',
                height: height,
                alwaysVisible: ($(this).attr("data-always-visible") == "1" ? true : false),
                railVisible: ($(this).attr("data-rail-visible") == "1" ? true : false),
                disableFadeOut: true
            });
        });
    };*/

    // Handles Image Preview using jQuery Fancybox plugin
    /*var handleFancybox = function () {
        if (!jQuery.fancybox) {
            return;
        }

        if (jQuery(".fancybox-button").size() > 0) {
            jQuery(".fancybox-button").fancybox({
                groupAttr: 'data-rel',
                prevEffect: 'none',
                nextEffect: 'none',
                closeBtn: true,
                helpers: {
                    title: {
                        type: 'inside'
                    }
                }
            });
        }
    };*/

    // Fix input placeholder issue for IE8 and IE9
    var handleFixInputPlaceholderForIE = function () {
        //fix html5 placeholder attribute for ie7 & ie8
        if (isIE8 || isIE9) { // ie8 & ie9
            // this is html5 placeholder fix for inputs, inputs with placeholder-no-fix class will be skipped(e.g: we need this for password fields)
            jQuery('input[placeholder]:not(.placeholder-no-fix), textarea[placeholder]:not(.placeholder-no-fix)').each(function () {

                var input = jQuery(this);

                if (input.val() == '' && input.attr("placeholder") != '') {
                    input.addClass("placeholder").val(input.attr('placeholder'));
                }

                input.focus(function () {
                    if (input.val() == input.attr('placeholder')) {
                        input.val('');
                    }
                });

                input.blur(function () {
                    if (input.val() == '' || input.val() == input.attr('placeholder')) {
                        input.val(input.attr('placeholder'));
                    }
                });
            });
        }
    };

    // Handle Select2 Dropdowns
    var handleSelect2 = function() {
        if (jQuery().select2) {
            $('.select2me').select2({
                placeholder: "请选择",
                allowClear: true
            });
        }
    };

    // Handle Theme Settings
    /*var handleTheme = function () {

        var panel = $('.theme-panel');

        if ($('body').hasClass('page-boxed') == false) {
            $('.layout-option', panel).val("fluid");
        }

        $('.sidebar-option', panel).val("default");
        $('.header-option', panel).val("fixed");
        $('.footer-option', panel).val("default");

        //handle theme layout
        var resetLayout = function () {
            $("body").
            removeClass("page-boxed").
            removeClass("page-footer-fixed").
            removeClass("page-sidebar-fixed").
            removeClass("page-header-fixed");

            $('.header > .header-inner').removeClass("container");

            if ($('.page-container').parent(".container").size() === 1) {
                $('.page-container').insertAfter('body > .clearfix');
            }

            if ($('.footer > .container').size() === 1) {
                $('.footer').html($('.footer > .container').html());
            } else if ($('.footer').parent(".container").size() === 1) {
                $('.footer').insertAfter('.page-container');
            }

            $('body > .container').remove();
        };

        var lastSelectedLayout = '';

        var setLayout = function () {

            var layoutOption = $('.layout-option', panel).val();
            var sidebarOption = $('.sidebar-option', panel).val();
            var headerOption = $('.header-option', panel).val();
            var footerOption = $('.footer-option', panel).val();

            if (sidebarOption == "fixed" && headerOption == "default") {
                alert('Default Header with Fixed Sidebar option is not supported. Proceed with Fixed Header with Fixed Sidebar.');
                $('.header-option', panel).val("fixed");
                $('.sidebar-option', panel).val("fixed");
                sidebarOption = 'fixed';
                headerOption = 'fixed';
            }

            resetLayout(); // reset layout to default state

            if (layoutOption === "boxed") {
                $("body").addClass("page-boxed");

                // set header
                $('.header > .header-inner').addClass("container");
                var cont = $('body > .clearfix').after('<div class="container"></div>');

                // set content
                $('.page-container').appendTo('body > .container');

                // set footer
                if (footerOption === 'fixed') {
                    $('.footer').html('<div class="container">' + $('.footer').html() + '</div>');
                } else {
                    $('.footer').appendTo('body > .container');
                }
            }

            if (lastSelectedLayout != layoutOption) {
                //layout changed, run responsive handler:
                runResponsiveHandlers();
            }
            lastSelectedLayout = layoutOption;

            //header
            if (headerOption === 'fixed') {
                $("body").addClass("page-header-fixed");
                $(".header").removeClass("navbar-static-top").addClass("navbar-fixed-top");
            } else {
                $("body").removeClass("page-header-fixed");
                $(".header").removeClass("navbar-fixed-top").addClass("navbar-static-top");
            }

            //sidebar
            if (sidebarOption === 'fixed') {
                $("body").addClass("page-sidebar-fixed");
            } else {
                $("body").removeClass("page-sidebar-fixed");
            }

            //footer 
            if (footerOption === 'fixed') {
                $("body").addClass("page-footer-fixed");
            } else {
                $("body").removeClass("page-footer-fixed");
            }

            handleSidebarAndContentHeight(); // fix content height            
            handleFixedSidebar(); // reinitialize fixed sidebar
            handleFixedSidebarHoverable(); // reinitialize fixed sidebar hover effect
        }

        // handle theme colors
        var setColor = function (color) {
            $('#style_color').attr("href", "assets/admin/css/themes/" + color + ".css");
            $.cookie('style_color', color);
        };

        var setPattern = function (pattern) {
            $.cookie('style_pattern', pattern);
            if (pattern == 'default') {
                $('body').removeClass("page-content-no-pattern");
            } else {
                $('body').addClass("page-content-no-pattern");
            }
        };

        $('.toggler', panel).click(function () {
            $(this).toggleClass("open");
            $('.theme-panel > .theme-options').toggle();            
        });

        $('.theme-colors > ul > li', panel).click(function () {
            var color = $(this).attr("data-style");
            setColor(color);
            $('ul > li', panel).removeClass("current");
            $(this).addClass("current");
        });

        $('.layout-option, .header-option, .sidebar-option, .footer-option', panel).change(setLayout);

        $('.pattern-option').change(function(){
            setPattern($(this).val());
        });

        if ($.cookie('style_color')) {
            setColor($.cookie('style_color'));
        }

        if ($.cookie('style_pattern')) {
            setPattern($.cookie('style_pattern'));
        }
    };*/
    
    //handles ajaxify
    //misuosi:add by YLM
    var handleAjaxify = function() {
    	
        //handle ajax links within main content
        //misuosi:add by YLM
        jQuery('.page-content-body').on('click', '.ajaxify', function (e) {
        	e.preventDefault();
        	App.scrollTop();
        	
        	var url = $(this).attr("url");
            var finalUrl = new URL(url);
            var $pagingPath = $(".page-content-body input[name='pagingPath']");
            if($pagingPath.length > 0) {
                var pagingPath = $pagingPath.val();
                finalUrl.replace('pagingPath', encodeURIComponent(pagingPath));
            }

        	var pageContent = $('.page-content');
        	var pageContentBody = $('.page-content .page-content-body');
        	
        	App.blockUI(pageContent, false);
        	
        	$.ajax({
        		type: "GET",
        		cache: false,
        		url: finalUrl.get(),
        		dataType: "html",
        		success: function (res) {
        			pageContentBody.html(res);
        			App.fixContentHeight(); // fix content height
        			App.initAjax(); // initialize core stuff
        			App.unblockUI(pageContent);
        		},
        		error: function (xhr, ajaxOptions, thrownError) {
        			//使用自定义的错误页面  by YLM
        			if(xhr.status === 404) {
        				pageContentBody.html(error404Page);
        			} else if(xhr.status === 500) {
        				pageContentBody.html(error500Page);
        			} else {
        				pageContentBody.html(errorElsePage);
        			}
        			App.unblockUI(pageContent);
        		}
        	});
        });
        
        //handle ajax to page in document（使用在page-header、page-sizebar、page-content之外的跳转）
        //misuosi:add by YLM
        /*jQuery('body').on('click', '.ajax-to', function (e) {
        	e.preventDefault();
        	App.scrollTop();
        	
        	var url = $(this).attr("url");
        	var pageContent = $('.page-content');
        	var pageContentBody = $('.page-content .page-content-body');
        	
        	App.blockUI(pageContent, false);
        	
        	$.ajax({
        		type: "GET",
        		cache: false,
        		url: url,
        		dataType: "html",
        		success: function (res) {
        			App.unblockUI(pageContent);
        			pageContentBody.html(res);
        			App.fixContentHeight(); // fix content height
        			App.initAjax(); // initialize core stuff
        		},
        		error: function (xhr, ajaxOptions, thrownError) {
        			//使用自定义的错误页面  by YLM
        			if(xhr.status === 404) {
        				pageContentBody.html(error404Page);
        			} else if(xhr.status === 500) {
        				pageContentBody.html(error500Page);
        			} else {
        				pageContentBody.html(errorElsePage);
        			}
        			App.unblockUI(pageContent);
        		}
        	});
        });*/
        
    };
    
    //handles tree table
    //misuosi:add by YLM
    var handleTreeTable = function() {
    	if($(".tree-table").length > 0) {
        	$(".tree-table").treetable({ 
        		expandable: true, 
        		initialState : "expanded"
        	});
    	}
    };
    
	//handles regionalism
    //misuosi:add by HONG
    var handleAjaxRegionalism = function(){
    	if ($('.regionalism').length > 0) {
			$('.regionalism').off('change').on('change', function(){
				$(this).parent().nextAll().children('.regionalism').empty().append('<option>请选择</option>');
				
				var regiId = $(this).val();
				var nextSelect = $(this).parent().next().children('.regionalism');
				if(nextSelect.length > 0 && regiId != '' && regiId != '请选择'){
					$.ajax({
						url: "common/regionalism/getRegionalismList",
						type: "POST",
						cache: false,
						data: {"regiId": regiId},
						dataType: "json",
						success: function(data){
							nextSelect.empty();
							var html = '<option value="">请选择</option>';
							nextSelect.append(html);
							
							if(typeof(data) == "undefined"){
								return;
							}
							
							var dataLength = data.length;
							for(var i=0; i<dataLength; i++){
								var html = '<option value="'+ data[i].regiId +'">'+ data[i].regiName +'</option>';
								nextSelect.append(html);
							}
							
							var cityId = $('#cityId').val();
							var countyId = $('#countyId').val();
							
							if(cityId != "not-value" || countyId != "not-value"){
								$('option[value='+cityId+']').attr('selected', true);
								$('option[value='+countyId+']').attr('selected', true);
								
								if(nextSelect.val() == cityId){
									$('#cityId').val("not-value")
								}else if(nextSelect.val() == countyId){
									$('#countyId').val("not-value");
								}
								nextSelect.trigger('change');
							}
						},
						error: function(res){
							App.toastError(toastrErrorTip);
						}
					});
				}
			});	
    	}
	};
	
	//init regionalism
    //misuosi:add by HONG
	var initRegionalism = function(){
		var regionalismSelect = $('select.regionalism-first');
		if(regionalismSelect.length > 0 && $('select.regionalism-first option').size()<2){
			var province = $('#provinceId').val();
			$.ajax({
				url: "common/regionalism/initRegionalismList",
				type: "POST",
				cache: false,
				dataType: "json",
				success: function(data){
					regionalismSelect.empty();
					var html = '<option value="">请选择</option>';
					regionalismSelect.append(html);
					
					if(typeof(data) == "undefined"){
						return;
					}
					
					var dataLength = data.length;
					for(var i=0; i<dataLength; i++){
						var html = '<option value="'+ data[i].regiId +'">'+ data[i].regiName +'</option>';
						regionalismSelect.append(html);
					}
					
					if(province != 'not-value'){
						$('option[value='+province+']').attr('selected', true);
						regionalismSelect.trigger('change');
					}
					
				},
				error: function(res){
					App.toastError(toastrErrorTip);
				}
			});
		}
	};

    //handles tree table
    //misuosi:add by LXC
    var handleDatePicker = function() {
        if ($('.date-picker').length > 0) {
            $('.date-picker').datepicker({
                language: 'zh-CN',
                autoclose: true,
                rtl: false,
                format: 'yyyy-mm-dd'
            });
        }
        if ($('.form_datetime').length > 0) {
            $('.form_datetime').each(function() {
                var tob = 'bottom';
                var rol = 'left';
                if ($(this).hasClass('top')) {
                    tob = 'top';
                }
                if ($(this).hasClass('right')) {
                    rol = 'right';
                }
                $(this).datetimepicker({
                    language: 'zh-CN',
                    autoclose: true,
                    isRTL: App.isRTL(),
                    format: 'yyyy-mm-dd hh:ii',
                    pickerPosition: tob + '-' + rol
                });
            });
        }
    };
    
    //handles ueditor
    //misuosi:add by HONG
    var handleUEditor = function() {
    	$('.ueditor').each(function(index){
    		var id = Util.getUniqueID('ueditor');
    		$(this).attr('id', id);
    		UE.getEditor(id);
    	});
    };
    
    
    //handles daterangepicker
    //misuosi:add by HONG
    var handleDaterangepicker = function(){
    	var reportrange = $('.reportrange'),
    	label = reportrange.data('label');
        if (reportrange.length > 0) {
            reportrange.append('<i class="glyphicon glyphicon-calendar fa fa-calendar"></i>')
                .append('<small>'+label+' </small><span></span> <b class="caret"></b>')

            $('.reportrange span').html(moment().subtract(29, 'days').format('YYYY/MM/DD') + ' - ' + moment().format('YYYY/MM/DD'));

            reportrange.daterangepicker({
                format: 'MM/DD/YYYY',
                startDate: moment().subtract(29, 'days'),
                endDate: moment(),
                minDate: '01/01/2012',
                maxDate: '12/31/2015',
                dateLimit: { days: 60 },
                showDropdowns: false,
                showWeekNumbers: true,
                timePicker: false,
                timePickerIncrement: 1,
                timePicker12Hour: true,
                ranges: {
                    '今天': [moment(), moment()],
                    '昨天': [moment().subtract(1, 'days'), moment().subtract(1, 'days')],
                    '最近7天': [moment().subtract(6, 'days'), moment()],
                    '最近30天': [moment().subtract(29, 'days'), moment()],
                    '这个月': [moment().startOf('month'), moment().endOf('month')],
                    '上个月': [moment().subtract(1, 'month').startOf('month'), moment().subtract(1, 'month').endOf('month')]
                },
                opens: 'right',
                drops: 'down',
                buttonClasses: ['btn', 'btn-sm'],
                applyClass: 'btn-primary',
                cancelClass: 'btn-default',
                separator: ' to ',
                locale: {
                    applyLabel: '确定',
                    cancelLabel: '取消',
                    fromLabel: '开始时间',
                    toLabel: '结束时间',
                    customRangeLabel: '自定义范围',
                    daysOfWeek: ['日', '一', '二', '三', '四', '五','六'],
                    monthNames: ['1月', '2月', '3月', '4月', '5月', '6月', '7月', '8月', '9月', '10月', '11月', '12月'],
                    firstDay: 1
                }
            }, function(start, end, label) {
                console.log(start.toISOString(), end.toISOString(), label);
                $('.reportrange span').html(start.format('YYYY/MM/DD') + ' - ' + end.format('YYYY/MM/DD'));
            });
        }
    };
    
    //handles dropdown-menu
    //misuosi:add by HONG
    var handleDropdownMenu = function(){
		//$(function() {
            var path = $('input[name="pagingPath"]').val();            
            $('.query-form .dropdown-menu [data-trogger="dropdown-item"]').each(function(index, domEle){
            	var _this = $(this),
            	datafor = _this.data('for'),
            	datalabel = _this.data('label');
            	
            	if (path && path.constants(datafor)) {
                    $('.query-form .dropdown-toggle > span').html(datalabel);
                    
                    $('.query-form .dropdown-menu [data-trogger="dropdown-item"]').each(function(index, domEle){
                    	$('.query-form input[name="'+$(this).data('for')+'"]').hide();
                    });
                    
                    $('.query-form input[name="'+datafor+'"]').show();
                    return;
                }
            })
        //});
		
		$('.query-form .dropdown-menu [data-trogger="dropdown-item"]').on('click', function(){
			var _this = $(this),
			label = _this.data('label');
			$('.query-form .dropdown-toggle > span').html(label);
			
			$('.query-form .dropdown-menu [data-trogger="dropdown-item"]').each(function(index, domEle){
				var _thisEle = $(domEle),
				inputName = _thisEle.data('for'),
				_input = $('.query-form input[name="'+inputName+'"]');
				
				_input.hide();
				_input.val('');
			});
			
			var inputName = _this.data('for'),
			_input = $('.query-form input[name="'+inputName+'"]');
			_input.show();
		});
	};
    
    //* END:CORE HANDLERS *//




    return {//TODO 定位用

        //main function to initiate the theme
        init: function () {

            //IMPORTANT!!!: Do not modify the core handlers call order.

            //core handlers
            handleInit(); // initialize core variables
            handleResponsiveOnResize(); // set and handle responsive    
            handleUniform(); // hanfle custom radio & checkboxes
            //handleScrollers(); // handles slim scrolling contents 
            handleResponsiveOnInit(); // handler responsive elements on page load

            //layout handlers
            handleFixedSidebar(); // handles fixed sidebar menu
            handleFixedSidebarHoverable(); // handles fixed sidebar on hover effect 
            handleSidebarMenu(); // handles main menu
            //handleQuickSearch(); // handles quick search
            handleSidebarToggler(); // handles sidebar hide/show            
            handleFixInputPlaceholderForIE(); // fixes/enables html5 placeholder attribute for IE9, IE8
            handleGoTop(); //handles scroll to top functionality in the footer
            //handleTheme(); // handles style customer tools

            //ui component handlers
            //handlePortletTools(); // handles portlet action bar functionality(refresh, configure, toggle, remove)
            //handleDropdowns(); // handle dropdowns
            //handleTabs(); // handle tabs
            handleTooltips(); // handle bootstrap tooltips
            //handlePopovers(); // handles bootstrap popovers
            //handleAccordions(); //handles accordions 
            //handleModals(); // handle modals
            //handleDropdownHover(); // handles dropdown hover   

            //handleHeaderMenu();//handles page-header menu    misuosi:add by YLM
            handleAjaxify();//handles ajaxify     misuosi:add by YLM
            handleAjaxRegionalism();//handles regionalism     misuosi:add by HONG
            initRegionalism();//init regionalism     misuosi:add by HONG
        },

        //main function to initiate core javascript after ajax complete
        initAjax: function () {
        	
        	//core handlers
            handleUniform(); // hanfle custom radio & checkboxes   
            //handleScrollers(); // handles slim scrolling contents 

            //core ui component handlers
            //handlePortletTools(); // handles portlet action bar functionality(refresh, configure, toggle, remove)
            //handleDropdowns(); // handle dropdowns
            //handleTabs(); // handle tabs
            handleTooltips(); // handle bootstrap tooltips
            //handlePopovers(); // handles bootstrap popovers
            //handleAccordions(); //handles accordions 
            //handleModals(); // handle modals  
            //handleDropdownHover(); // handles dropdown hover    

            //custom component handlers
            //handleAjaxify();//handles ajaxify     misuosi:add by YLM   重复监听导致事件重复触发
            handleAjaxRegionalism();//handles regionalism     misuosi:add by HONG
            initRegionalism();//init regionalism     misuosi:add by HONG
        },
        
        initPlugins: function() {
            //handleFancybox(); // handle fancy box
            handleSelect2(); // handle custom Select2 dropdowns
            handleTreeTable();//handles tree table		misuosi:add by YLM
            handleDatePicker();//handles date picker	misuosi:add by LXC
            handleUEditor();//handles ueditor misuosi:add by HONG
            handleDaterangepicker();//init daterangepicker     misuosi:add by HONG
            handleDropdownMenu();//init dropdown-menu     misuosi:add by HONG
        },

        //public function to fix the sidebar and content height accordingly
        fixContentHeight: function () {
            handleSidebarAndContentHeight();
        },

        //public function to remember last opened popover that needs to be closed on click
        /*setLastPopedPopover: function (el) {
            lastPopedPopover = el;
        },*/

        //public function to add callback a function which will be called on window resize
        /*addResponsiveHandler: function (func) {
            responsiveHandlers.push(func);
        },*/

        // useful function to make equal height for contacts stand side by side
        /*setEqualHeight: function (els) {
            var tallestEl = 0;
            els = jQuery(els);
            els.each(function () {
                var currentHeight = $(this).height();
                if (currentHeight > tallestEl) {
                    tallestColumn = currentHeight;
                }
            });
            els.height(tallestEl);
        },*/

        // wrapper function to scroll(focus) to an element
        scrollTo: function (el, offeset) {
            var pos = (el && el.size() > 0) ? el.offset().top : 0;
            jQuery('html,body').animate({
                scrollTop: pos + (offeset ? offeset : 0)
            }, 'slow');
        },

        // function to scroll to the top
        scrollTop: function () {
            App.scrollTo();
        },

        // wrapper function to  block element(indicate loading)
        blockUI: function (el, centerY) {
            var el = jQuery(el);
            if (el.height() <= 400) {
                centerY = true;
            }
            el.block({
                message: '<img src="assets/admin/img/ajax-loading.gif" align="">',
                centerY: centerY != undefined ? centerY : true,
                css: {
                    top: '10%',
                    border: 'none',
                    padding: '2px',
                    backgroundColor: 'none'
                },
                overlayCSS: {
                    backgroundColor: '#000',
                    opacity: 0.05,
                    cursor: 'wait'
                }
            });
        },

        // wrapper function to  un-block element(finish loading)
        unblockUI: function (el) {
            jQuery(el).unblock({
                onUnblock: function () {
                	if (el.hasClass('page-content')) {
                		return;
                	}
                    jQuery(el).removeAttr("style");
                }
            });
        },

        // initializes uniform elements
        /*initUniform: function (els) {
            if (els) {
                jQuery(els).each(function () {
                    if ($(this).parents(".checker").size() == 0) {
                        $(this).show();
                        $(this).uniform();
                    }
                });
            } else {
                handleUniform();
            }

        },*/

        //wrapper function to update/sync jquery uniform checkbox & radios
        updateUniform: function (els) {
            $.uniform.update(els); // update the uniform checkbox & radios UI after the actual input control state changed
        },

        //public function to initialize the fancybox plugin
        /*initFancybox: function () {
            handleFancybox();
        },*/

        //public helper function to get actual input value(used in IE9 and IE8 due to placeholder attribute not supported)
        getActualVal: function (el) {
            var el = jQuery(el);
            if (el.val() === el.attr("placeholder")) {
                return "";
            }
            return el.val();
        },

        //public function to get a paremeter by name from URL
        /*getURLParameter: function (paramName) {
            var searchString = window.location.search.substring(1),
                i, val, params = searchString.split("&");

            for (i = 0; i < params.length; i++) {
                val = params[i].split("=");
                if (val[0] == paramName) {
                    return unescape(val[1]);
                }
            }
            return null;
        },*/

        // check for device touch support
        /*isTouchDevice: function () {
            try {
                document.createEvent("TouchEvent");
                return true;
            } catch (e) {
                return false;
            }
        },*/

        // check IE8 mode
        isIE8: function () {
            return isIE8;
        },

        // check IE9 mode
        isIE9: function () {
            return isIE9;
        },

        //check RTL mode
        isRTL: function () {
            return isRTL;
        },

        // get layout color code by color name
        /*getLayoutColorCode: function (name) {
            if (layoutColorCodes[name]) {
                return layoutColorCodes[name];
            } else {
                return '';
            }
        },*/
	    
	    //* BEGIN:COMPONNENT FUNCTION *//

        //ajax(post) with data to url for page
        //misuosi:add by YLM 
        post: function(url, data, callback){
        	var finalUrl = new URL(url);
        	var pageContent = $('.page-content');
        	var pageContentBody = $('.page-content .page-content-body');
        	App.blockUI(pageContent, false);
        	var $pagingPath = $(".page-content-body input[name='pagingPath']");
        	if($pagingPath.length > 0) {
        		var pagingPath = $pagingPath.val();
        		finalUrl.replace('pagingPath', encodeURIComponent(pagingPath));
        	}
        	$.ajax({
        		type : "POST",
        		cache : false,
        		url : finalUrl.get(),
        		data : data,
        		success: function (res) {
        			if(callback) {
        				callback(res);
        			} else {
	        			if (res.length < errorTextMaxLength) {
	        				App.toastError(res);
	        			} else {
	        				App.toastSuccess(toastrSuccessTip);
	        				App.scrollTop();
	        				pageContentBody.html(res);
	        				App.fixContentHeight(); // fix content height
	        				App.initAjax(); // initialize core stuff
	        			}
        			}
        			App.unblockUI(pageContent);
        		},
        		error: function (xhr, ajaxOptions, thrownError) {
        			App.toastError(toastrErrorTip);
        			App.unblockUI(pageContent);
        		}
        	});
        },
        
        //ajax(post) with data to url for modal
        //misuosi:add by YLM 
        postModal: function(url, data, callback){
        	var finalUrl = new URL(url);
        	var modal = $('.modal.in');
        	App.blockUI(modal, false);
        	var $pagingPath = $(".modal-body input[name='pagingPath']");
        	if($pagingPath.length > 0) {
        		var pagingPath = $pagingPath.val();
        		finalUrl.replace('pagingPath', encodeURIComponent(pagingPath));
        	}
        	$.ajax({
        		type : "POST",
        		cache : false,
        		url : finalUrl.get(),
        		data : data,
        		success: function (res) {
        			if(callback) {
        				callback(res);
        			} else {
        				if (res.length < errorTextMaxLength) {
        					App.toastError(res);
        				} else {
        					App.toastSuccess(toastrSuccessTip);
        					modal.html(res);
        					App.initAjax(); // initialize core stuff
        				}
        			}
        			App.unblockUI(pageContent);
        		},
        		error: function (xhr, ajaxOptions, thrownError) {
        			App.toastError(toastrErrorTip);
        			App.unblockUI(pageContent);
        		}
        	});
        },
        
        //ajax to url for page
        //misuosi:add by YLM 
        ajaxToPage: function(url){
        	var finalUrl = new URL(url);
        	var pageContent = $('.page-content');
        	var pageContentBody = $('.page-content .page-content-body');
        	App.blockUI(pageContent, false);
			var $pagingPath = $(".page-content-body input[name='pagingPath']");
        	if($pagingPath.length > 0) {
        		var pagingPath = $pagingPath.val();
                finalUrl.replace('pagingPath', encodeURIComponent(pagingPath));
			}
			$.ajax({
				type : "GET",
				cache : false,
				url : finalUrl.get(),
				dataType : "html",
				success: function (res) {
					if (res.length < errorTextMaxLength) {
                    	App.toastError(res);
                    } else {
                    	App.toastSuccess(toastrSuccessTip);
            			App.scrollTop();
	        			pageContentBody.html(res);
	        			App.fixContentHeight(); // fix content height
	        			App.initAjax(); // initialize core stuff
                    }
        			App.unblockUI(pageContent);
        		},
        		error: function (xhr, ajaxOptions, thrownError) {
        			App.scrollTop();
        			//使用自定义的错误页面  by YLM
        			if(xhr.status === 404) {
        				pageContentBody.html(error404Page);
        			} else if(xhr.status === 500) {
        				pageContentBody.html(error500Page);
        			} else {
        				pageContentBody.html(errorElsePage);
        			}
        			App.unblockUI(pageContent);
        		}
			});
		},
		
		//ajax to url for modal
		//misuosi:add by YLM 
		ajaxToModal: function(url){
			var finalUrl = new URL(url);
			var modal = $('.modal.in');
        	var modalContentBody = modal.find('.modal-body');
        	App.blockUI(modal, false);
			var $pagingPath = $(".modal-body input[name='pagingPath']");
        	if($pagingPath.length > 0) {
        		var pagingPath = $pagingPath.val();
                finalUrl.replace('pagingPath', encodeURIComponent(pagingPath));
			}
			$.ajax({
				type : "GET",
				cache : false,
				url : finalUrl.get(),
				dataType : "html",
				success: function (res) {
					if (res.length < errorTextMaxLength) {
                    	App.toastError(res);
                    } else {
                    	App.toastSuccess(toastrSuccessTip);
    					modal.html(res);
    					App.initAjax(); // initialize core stuff
                    }
					App.unblockUI(modal);
				},
				error: function (xhr, ajaxOptions, thrownError) {
					//使用自定义的错误页面  by YLM
					if(xhr.status === 404) {
						modalContentBody.html(error404Page);
					} else if(xhr.status === 500) {
						modalContentBody.html(error500Page);
					} else {
						modalContentBody.html(errorElsePage);
					}
					App.unblockUI(modal);
				}
			});
		},

        //ajax submit in page 
        //misuosi:add by YLM 
        ajaxSubmit: function(url){
        	//处理placeholder在IE8，9的值问题
        	var params = $('.ajax-form').serializeArray();
			for(var i in params) {
				if(params[i] === $('.ajax-form[name="'+ i +'"]').attr("placeholder")) {
					params[i] = "";
				}
			}
			
        	//如果有验证先判断验证
        	var $valid = $(".ajax-form.valid");
        	if($valid.length > 0){
        		if(!$valid.valid()){
        			return;
        		}
        	}
        	
        	var pageContent = $('.page-content');
        	var pageContentBody = $('.page-content .page-content-body');
        	
        	App.blockUI(pageContent, false);
        	
        	
        	$.ajax({
        		type: "POST",
        		cache: false,
        		url: url,
        		data: params,
        		success: function (res) {
        			if (res.length < errorTextMaxLength) {
                    	App.toastError(res);
                    } else {
                    	App.toastSuccess(toastrSuccessTip);
                    	App.scrollTop();
	        			pageContentBody.html(res);
	        			App.fixContentHeight(); // fix content height
	        			App.initAjax(); // initialize core stuff
                    }
        			App.unblockUI(pageContent);
        		},
        		error: function (xhr, ajaxOptions, thrownError) {
        			App.toastError(toastrErrorTip);
        			App.unblockUI(pageContent);
        		}
			});
		},
		
		//ajax submit in modal 
		//misuosi:add by YLM 
		ajaxSubmitModal: function(url){
			var modal = $('.modal.in');
        	//处理placeholder在IE8，9的值问题
        	var params = modal.find('.ajax-form').serializeArray();
			for(var i in params) {
				if(params[i] === modal.find('.ajax-form[name="'+ i +'"]').attr("placeholder")) {
					params[i] = "";
				}
			}

        	
        	//如果有验证先判断验证
        	var $valid = modal.find(".ajax-form.valid");
        	if($valid.length > 0){
        		if(!$valid.valid()){
        			return;
        		}
        	}
        	
        	var pageContent = $('.page-content');
        	var pageContentBody = $('.page-content .page-content-body');

        	App.blockUI(pageContent, false);

            var finalUrl = new URL(url);
            
        	//获取table的url
        	var $pagingPath = $(".page-content-body input[name='pagingPath']");
        	if($pagingPath.length > 0) {
                var pagingPath = $pagingPath.val();
                finalUrl.replace('pagingPath', encodeURIComponent(pagingPath));
			}
        	
        	$.ajax({
        		type: "POST",
        		cache: false,
        		url: finalUrl.get(),
        		data: modal.find(".ajax-form").serializeArray(),
        		success: function (res) {
        			if (res.length < errorTextMaxLength) {
                    	App.toastError(res);
        			} else {
                    	App.toastSuccess(toastrSuccessTip);
        	        	App.scrollTop();
        				modal.modal('hide');
        				pageContentBody.html(res);
        				App.initAjax(); // initialize core stuff
        			}
                    App.unblockUI(pageContent);
        		},
        		error: function (xhr, ajaxOptions, thrownError) {
        			App.toastError(toastrErrorTip);
        			App.unblockUI(modal);
        		}
        	});
		},
		
		//alert function
		//misuosi:add by YLM
		alert: function(options) {

	        options = $.extend(true, {
	            container: "", // alerts parent container(默认在.page-content容器的头部)
	            place: "prepend", // append or prepend in container （在指定了container时生效）
	            type: 'success',  // alert's type（取值范围：success,info,warning,danger）
	            message: "",  // alert's message
	            close: true, // make alert closable
	            reset: true, // close all previouse alerts first
	            focus: true, // auto scroll to the alert after shown
	            closeInSeconds: 30, // auto close after defined seconds（指定为0则不自动关闭）
	            icon: "" // put icon before the message（如成功check，警告warning）
	        }, options);

	        var id = Util.getUniqueID("appAlert");

	        var html = '<div id="'+id+'" class="app-alerts alert alert-'+options.type+' fade in">' + (options.close ? '<button type="button" class="close" data-dismiss="alert" aria-hidden="true"></button>' : '' ) + (options.icon != "" ? '<i class="icon-'+options.icon + '"></i>  ' : '') + options.message+'</div>';

	        if (options.reset) {
	            $('.app-alerts').remove();
	        }

	        if (!options.container) {
	            $('.page-content').prepend(html);
	        } else {
	            if (options.place == "append") {
	                $(options.container).append(html);
	            } else {
	                $(options.container).prepend(html);
	            }
	        }

	        if (options.focus) {
	            App.scrollTo($('#' + id), -55);
	        }

	        if (options.closeInSeconds > 0) {
	            setTimeout(function(){
	                $('#' + id).remove();
	            }, options.closeInSeconds * 1000);
	        }
	    },
	    
	    alertSuccess: function(message, selector, isFocus){
			App.alert({
		 		type: 'success', 
		 		icon: 'ok', 
		 		message: message, 
		 		container: selector, 
		 		place: 'prepend',
		 		closeInSeconds: 0,
	    		focus: isFocus?isFocus:false
		 	});
		},
	    
	    alertDanger: function(message, selector, isFocus){
	    	App.alert({
	    		type: 'danger', 
	    		icon: 'warning-sign', 
	    		message: message, 
	    		container: selector, 
	    		place: 'prepend',
	    		closeInSeconds: 0,
	    		focus: isFocus?isFocus:false
	    	});
	    },
	    
	    toastSuccess: function(message, callback) {
	    	Toastr.options = {
	    			"closeButton": false,
	    			"debug": false,
	    			"positionClass": "toast-top-center",
	    			"showDuration": "1000",
	    			"hideDuration": "1000",
	    			"timeOut": "2000",
	    			"extendedTimeOut": "1000",
	    			"showEasing": "swing",
	    			"hideEasing": "linear",
	    			"showMethod": "slideDown",
	    			"hideMethod": "slideUp",
	    			"onclick": callback
				};
	    	Toastr.success(message);
	    },
	    
	    toastError: function(message, callback) {
	    	Toastr.options = {
	    			"closeButton": false,
	    			"debug": false,
	    			"positionClass": "toast-top-center",
	    			"showDuration": "1000",
	    			"hideDuration": "1000",
	    			"timeOut": "4000",
	    			"extendedTimeOut": "1000",
	    			"showEasing": "swing",
	    			"hideEasing": "linear",
	    			"showMethod": "slideDown",
	    			"hideMethod": "slideUp",
	    			"onclick": callback
				};
	    	Toastr.error(message);
	    }
	    
	    //* END:COMPONNENT FUNCTION *//
		
    };

}();
