<!DOCTYPE html>
<html>
<head>
    <script type="text/javascript">
        <!--
        javascript:window.history.forward(1);
        //-->
    </script>
    <base href="${base}/" />
    <meta charset="utf-8" />
    <title>嘉宝商城系统 | 登录</title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta content="width=device-width, initial-scale=1.0" name="viewport" />
    <meta content="" name="description" />
    <meta content="" name="author" />
    <meta name="MobileOptimized" content="320">

    <link href="${base}/assets/plugins/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css"/>
    <link href="${base}/assets/plugins/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
    <link href="${base}/assets/admin/css/style.css" rel="stylesheet" type="text/css"/>
    <link href="${base}/assets/admin/css/pages/login.css" rel="stylesheet" type="text/css"/>
    <link href="${base}/assets/plugins/uniform/css/uniform.default.min.css" rel="stylesheet" type="text/css"/>
    <link rel="shortcut icon" href="${base}/favicon.ico" />
</head>

<body class="login">
	<!-- BEGIN LOGO -->
	<div class="logo">
	    <img src="${base}/assets/admin/img/logo.png" alt="" />
	</div>
	<!-- END LOGO -->
	
	<!-- BEGIN LOGIN -->
	<div class="content">
	    <!-- BEGIN LOGIN FORM -->
	    <form id="loginForm" method="post" action="common/seller/login">
	        <h3 class="form-title">登录商家的账户</h3>
	    <#if error??>
	        <div class="alert alert-danger alert-dismissable">
	            <button type="button" class="close" data-dismiss="alert" aria-hidden="true" style="right: -7px;"></button>
	        ${error!}
	        </div>
	    </#if>
	        <div class="form-group">
	            <!--ie8, ie9 does not support html5 placeholder, so we just show field title for that-->
	            <label class="control-label visible-ie8 visible-ie9">账号</label>
	            <div class="input-icon">
	                <i class="icon-user"></i>
	                <input class="form-control" type="text" autocomplete="off" placeholder="请输入账号" name="username"/>
	            </div>
	        </div>
	        <div class="form-group">
	            <label class="control-label visible-ie8 visible-ie9">密码</label>
	            <div class="input-icon">
	                <i class="icon-lock"></i>
	                <input class="form-control" type="password" autocomplete="off" placeholder="请输入密码" name="password"/>
	            </div>
	        </div>
	        <div class="form-group">
	            <div class="radio-list">
	                <label class="radio-inline">
	                    <input type="radio" class="radio" name="userType" id="optionsRadios4" value="distributor" checked> 分销商
	                </label>
	                <label class="radio-inline">
	                    <input type="radio" class="radio" name="userType" id="optionsRadios5" value="supplier" > 供货商
	                </label>
	            </div>
	        </div>
	        <div class="form-actions">
	            <button type="submit" class="btn btn-info pull-right">
	                登录
	            </button>
	        </div>
	    </form>
	    <!-- END LOGIN FORM -->
	</div>
	<!-- END LOGIN -->
	
	<!-- BEGIN COPYRIGHT -->
	<div class="copyright">
	    2015 &copy; 广州米所思信息科技有限公司 版权所有.
	</div>
	<!-- END COPYRIGHT -->

	<!-- BEGIN JAVASCRIPTS -->
	
	<#include "../common/_js/require_config_admin.ftl" />
	<script src="assets/requirejs/require.js" data-main="assets/admin/scripts/common/seller_login" defer async ></script>
	<!-- END JAVASCRIPTS -->
</body>
</html>