<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>员工支付系统</title>
</head>
<body>
<h1>首页</h1>
<h2>欢迎<%=session.getAttribute("username") %></h2>
<form action="servlet/ChooseFunction" method="post"  >
<input type="radio" name="function" value="增加小时工" checked>增加小时工
<br>
<input type="radio" name="function" value="增加绩效工" checked>增加绩效工
<br>
<input type="radio" name="function" value="增加编制人员" checked>增加编制人员
<br>
<input type="radio" name="function" value="删除员工" checked>删除员工
<br>
<input type="radio" name="function" value="修改员工类型" checked>修改员工类型
<br>
<input type="radio" name="function" value="录入账单" checked>录入账单
<br>
<input type="radio" name="function" value="计算薪水" checked>计算薪水
<br>
<input type="radio" name="function" value="显示员工信息" checked>显示员工信息
<br>
<input type="submit" value="Submit">
</form> 
</body>
</html>