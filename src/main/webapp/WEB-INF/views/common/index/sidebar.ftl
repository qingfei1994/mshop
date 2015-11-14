<!-- BEGIN SIDEBAR -->
<div class="page-sidebar navbar-collapse collapse">
    <!-- BEGIN SIDEBAR MENU -->
    <ul class="page-sidebar-menu">
        <li>
            <!-- BEGIN SIDEBAR TOGGLER BUTTON -->
            <div class="sidebar-toggler"></div>
            <div class="clearfix"></div>
            <!-- BEGIN SIDEBAR TOGGLER BUTTON -->
        </li>
        <#list permissionList as permission1 >
            <li>
                <a href="javascript:;">
                    <i class="${permission1.permIcon!}"></i>
                    <span class="title">${permission1.permName}</span>
                    <span class="selected"></span>
                    <span class="arrow"></span>
                </a>
                <ul class="sub-menu">
                    <#list permission1.adminAvailableSubPermissions as permission2 >
                        <li>
                            <a class="ajaxify" href="javascript:;" url="${permission2.permUrl!}">${permission2.permName}</a>
                        </li>
                    </#list>
                </ul>
            </li>
        </#list>
    </ul>
    <!-- END SIDEBAR MENU -->
</div>
<!-- END SIDEBAR -->