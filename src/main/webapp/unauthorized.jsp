<html>
<head>
    <%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
    <%
        String path = request.getContextPath();
        String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
    %>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>403 - 您不能访问哦！</title>
    <link href="<%=basePath%>assets/admin/css/error.css" rel="stylesheet" type="text/css"/>
</head>
<body>
<div id="main" class="zh">
    <header id="header">
        <h1><span class="icon">!</span>403</h1>
        <div id="content">
            <p>
                如果您不是正在干坏事，那就联系一下管理员吧^ _ ^<br />
                或者返回<a href="<%=basePath%>admin">首页</a>
            </p>
        </div>
    </header>
</div>
</body>
</html>
