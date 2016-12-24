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
<h1>首页</h1>
<h2>欢迎<%=name %></h2>
<form action="servlet/ChooseEmployeeFunction" method="post"  >
<input type="radio" name="function" value="查看个人信息" checked>查看个人信息
<br>
<%
if(e.getClassification().toString().equals("小时工")){
	out.print("<input type=\"radio\" name=\"function\" value=\"添加时刻表\" checked>添加时刻表");
	out.print("<br>");
}
else if(e.getClassification().toString().equals("领提成加2周薪员工")){
	out.print("<input type=\"radio\" name=\"function\" value=\"添加销售记录\" checked>添加销售记录");
	out.print("<br>");
}
if(e.getAffiliation().toString().equals("工会成员")){
	out.print("<input type=\"radio\" name=\"function\" value=\"添加工会服务费\" checked>添加工会服务费");
	out.print("<br>");
}	
%>

<br>
<input type="submit" value="Submit">
</form> 
</body>
</html>