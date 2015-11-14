<div class="row">
    <div class="col-md-12">
        <div class="note outer-note note-success">
            <p>
                您好，今天是 ${week}，${date}
            </p>
        </div>
        <!-- BEGIN SAMPLE TABLE PORTLET-->
        <div class="portlet">
            <div class="portlet-title">
                <div class="caption"><i class="icon-cogs"></i>系统信息</div>
            </div>
            <div class="portlet-body">
                <div class="table-responsive">
                    <table class="table table-bottom">
                        <tbody>
                        <tr>
                            <td>1</td>
                            <td>Java版本</td>
                            <td>${javaVersion}</td>
                        </tr>
                        <tr>
                            <td>2</td>
                            <td>系统名称</td>
                            <td>${osName}</td>
                        </tr>
                        <tr>
                            <td>3</td>
                            <td>系统构架</td>
                            <td>${osArch}</td>
                        </tr>
                        <tr>
                            <td>4</td>
                            <td>系统版本</td>
                            <td>${osVersion}</td>
                        </tr>
                        <tr>
                            <td>5</td>
                            <td>Server信息</td>
                            <td>${serverInfo}</td>
                        </tr>
                        <tr>
                            <td>6</td>
                            <td>登录IP地址</td>
                            <td>${loginIP}</td>
                        </tr>
                        <tr>
                            <td>7</td>
                            <td>Servlet版本</td>
                            <td>${servletVersion}</td>
                        </tr>
                        </tbody>
                    </table>
                </div>
                <div class="blank-line"></div>
            </div>
        </div>
        <!-- END SAMPLE TABLE PORTLET-->
    </div>
</div>