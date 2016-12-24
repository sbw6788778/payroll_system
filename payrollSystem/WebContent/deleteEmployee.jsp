<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>删除员工</title>
</head>
<body>
	<form action="servlet/DeleteEmployee" method="post">
		id:<br>
		<input type="text" name="id" value="">
		<br>
		<input type="submit" value="Submit">
	</form> 
</body>
</html>