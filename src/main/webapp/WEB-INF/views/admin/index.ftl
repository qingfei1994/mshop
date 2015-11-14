<#include "../common/index/header.ftl" />

	<!-- BEGIN HEADER -->
	<div class="header navbar navbar-inverse navbar-fixed-top">
	    <!-- BEGIN TOP NAVIGATION BAR -->
	    <div class="header-inner">
	        <!-- BEGIN LOGO -->
	        <a class="navbar-brand" href="admin/">
	            <img src="${base}/assets/admin/img/logo.png" alt="logo" class="img-responsive"/>
	        </a>
	        <!-- END LOGO -->
	        <!-- BEGIN RESPONSIVE MENU TOGGLER -->
	        <a href="javascript:;" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
	            <img src="${base}/assets/admin/img/menu-toggler.png" alt=""/>
	        </a>
	        <!-- END RESPONSIVE MENU TOGGLER -->
	        <!-- BEGIN TOP NAVIGATION MENU -->
	        <ul class="nav navbar-nav pull-right">
	            <li class="devider">&nbsp;</li>
	            <!-- BEGIN USER LOGIN DROPDOWN -->
	            <li class="dropdown user">
	                <a href="javascript:;" class="dropdown-toggle" data-toggle="dropdown" data-hover="dropdown"
	                   data-close-others="true">
	                    <img alt="" src="${base}/assets/admin/img/avatar_small.png"/>
	                    <span class="username"><@shiro.principal/>(超级管理员)</span>
	                    <i class="icon-angle-down"></i>
	                </a>
	                <ul class="dropdown-menu">
	                    <li><a href="javascript:;"><i class="icon-user"></i>个人信息</a>
	                    </li>
	                    <li class="divider"></li>
	                    <li><a href="admin/logout"><i class="icon-key"></i>退出登录</a>
	                    </li>
	                </ul>
	            </li>
	            <!-- END USER LOGIN DROPDOWN -->
	        </ul>
	        <!-- END TOP NAVIGATION MENU -->
	    </div>
	    <!-- END TOP NAVIGATION BAR -->
	</div>
	<!-- END HEADER -->
	<div class="clearfix"></div>
	<!-- BEGIN CONTAINER -->
	<div class="page-container">
		<#include "../common/index/sidebar.ftl" />
	    <!-- BEGIN PAGE -->
	    <div class="page-content">
	        <!-- BEGIN PAGE HEADER-->
	        <!-- BEGIN PAGE CONTENT-->
	        <div class="page-content-body">
				<#include "../common/index/content.ftl"/>
	        </div>
	        <!-- END PAGE CONTENT-->
	    </div>
	    <!-- END PAGE -->
	</div>
	<!-- END CONTAINER -->

<#include "../common/index/footer.ftl" />