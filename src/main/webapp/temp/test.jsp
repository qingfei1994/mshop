<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2015/7/16 0016
  Time: 15:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta content="width=device-width, initial-scale=1.0, maximum-scale=1.0,  user-scalable=no" name="viewport" />
    <title>test</title>
</head>
<body>
    <input name="phone_no" format="*m" value="13"/>
    <do type="option" label="呼出号">
        <go href="wtai://wp/mc;$(phone_no)" mce_href="wtai://wp/mc;$(phone_no)"/>
    </do><br/>

    <a href="wtai://wp/mc;13800138000*" mce_href="wtai://wp/mc;13800138000*">拨打电话</a>

    <a href="tel:+8613800138000" mce_href="tel:+8613800138000">报警联系我</a>
</body>
</html>
