<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>增加小时工</title>
</head>
<body>
	<form action="servlet/AddHourlyEmployee" method="post">
		id:<br>
		<input type="text" name="id" value="">
		<br>
 		姓名:<br>
		<input type="text" name="name" value="">
		<br>
 		家庭住址:<br>
		<input type="text" name="address" value="">
		<br>
		一小时的工资：<br>
		<input type="text" name="hourlyRate" value="">
		<br>
		<input type="submit" value="Submit">
	</form> 
</body>
</html>