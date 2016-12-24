<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>欢迎登录</title>
</head>
<body>
<a href="register.jsp">员工注册</a>
<form name="form1" method="post" target="main" >
<table border="0" cellspacing="0" cellpadding="0" align="center" width="100%">
<tr height="30">
<td style="font-size:15px;color:#00346D;font-weight:600" align="center">用户登录</td>
</tr>   
<tr height="20"><td>用户名：<input type="text" name="username" size="11" /></td></tr>
<tr height="20"><td>密码：<input type="text" name="password" size="11" /></td></tr>  
<tr height="30"><td>
<input name="rdo" type="radio" value="1" onclick="document.form1.action='servlet/LoginAdmin'"/>管理员
<input name="rdo" type="radio" value="1" onclick="document.form1.action='servlet/LoginEmployee'"/>普通员工
</td></tr>
<tr height="50"><td style="font-size:15px;color:#00346D;font-weight:200" align="center">
<input name="submit1" type="submit" value="登录" />
</td></tr>   
</table>
</form>
</body>
</html>