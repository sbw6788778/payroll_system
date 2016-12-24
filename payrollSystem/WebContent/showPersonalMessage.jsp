<%@page import="Payroll.Employee"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>员工支付系统</title>
<% Employee e=(Employee)session.getAttribute("username");
	String name=e.getName();
	%>
</head>
<body>
<h1>查看个人信息</h1>
<table border="1">
<tr>
	<td>员工号</td>
	<td>姓名</td>
	<td>地址</td>
	<td>员工类型</td>
	<td>配送方式</td>
	<td>是否是工会成员</td>
</tr>
<tr>
	<td><%=e.getId() %></td>
	<td><%=e.getName() %></td>
	<td><%=e.getAddress() %></td>
	<td><%=e.getClassification() %></td>
	<td><%=e.getMethod() %></td>
	<td><%=e.getAffiliation() %></td>
</tr>
</table>
<h2>修改姓名</h2>
<form action="servlet/ChangeEmployeeName" method="post" name="form1">
	新的名字:<br><input type="text" name="newName" value=""><br><br>
	<input type="submit" value="Submit">
</form>
<h2>修改地址</h2>
<form action="servlet/ChangeEmployeeAddress" method="post" name="form2">
	新的地址:<br><input type="text" name="newAddress" value=""><br><br>
	<input type="submit" value="Submit">
</form>
<h2>修改配送方式</h2>
<h3>改为支票邮寄</h3>
<form action="servlet/ChangeMailMethod" method="post" name="form3">
	配送地址:<br><input type="text" name="address" value=""><br><br>
	<input type="submit" value="Submit">
</form>
<h3>改为直接打入银行账户</h3>
<form action="servlet/ChangeDirectMethod" method="post" name="form3">
	银行:<br><input type="text" name="bank" value=""><br><br>
	帐号:<br><input type="text" name="account" value=""><br><br>
	<input type="submit" value="Submit">
</form>
<h3>改为寄存在工作人员</h3>
<form action="servlet/ChangeHoldMethod" method="post" name="form3">
	<input type="submit" value="Submit">
</form>
<h2>加入工会</h2>
<form action="servlet/ChangeEmployeeAffiliation" method="post" name="form4">
	工会号:<br><input type="text" name="unionId" value=""><br><br>
	每周要缴纳的费用:<br><input type="text" name="dues" value=""><br><br>
	<input type="submit" value="Submit">
</form>
<h2>退出工会</h2>
<form action="servlet/ChangeEmployeeunAffiliation" method="post" name="form5">
	<input type="submit" value="Submit">
</form>
</body>
</html>