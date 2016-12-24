<%@ page language="java" contentType="text/html; charset=utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>员工注册</title>
</head>
<body>
	<form action="servlet/Register" method="post">
		员工号:<br>
		<input type="text" name="id" value="">
		<br>
 		用户名:<br>
		<input type="text" name="username" value="">
		<br>
 		密码:<br>
		<input type="text" name="password" value="">
		<br>
		邮箱:<br>
		<input type="text" name="email" value="">
		<br>
		<input type="submit" value="注册">
	</form> 
</body>
</html>