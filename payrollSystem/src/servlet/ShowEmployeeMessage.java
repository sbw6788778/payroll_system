package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Payroll.Employee;

/**
 * Servlet implementation class ShowEmployeeMessage
 */
public class ShowEmployeeMessage extends HttpServlet {
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShowEmployeeMessage() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out= response.getWriter();
		out.println("<html>");
		out.println("<head>");
		out.println("<title>员工支付系统</title>");
		out.println("</head>");
		out.println("<body>");
		out.println("<h1>所有员工的信息</h1>");
		out.println("<table border=\"1\">");
		out.println("<tr>");
		out.println("  <td>id</td>");
		out.println("  <td>姓名</td>");
		out.println("  <td>地址</td>");
		out.println("  <td>员工类型</td>");
		out.println("</tr> ");
		Set<Integer> employeesId = ChooseFunction.database.GetAllEmployeeIds();
		for (int em:employeesId){
		 	Employee employee= ChooseFunction.database.GetEmployee(em);	
		 	out.println("<tr>");
		 	out.println("  <td>"+employee.getId()+"</td>");
		 	out.println("  <td>"+employee.getName()+"</td>");
		 	out.println("  <td>"+employee.getAddress()+"</td>");
		 	out.println("  <td>"+employee.getClassification()+"</td>");
		 	out.println("</tr> ");
		}
		out.println("</table>");
		out.println("<h2>更改员工类型</h2>");
		out.println("<h3>改为小时工</h3>");
		out.println("<form action=\"ChangeToHourlyEmployee\" method=\"post\" name=\"form1\">");
		out.println("id:<br><input type=\"text\" name=\"id\" value=\"\"><br><br>");
		out.println("一小时的工资:<br><input type=\"text\" name=\"salary\" value=\"\"><br><br>");
		out.println("<input type=\"submit\" value=\"Submit\">");
		out.println("</form>");
		out.println("<h3>改为绩效工</h3>");
		out.println("<form action=\"ChangeToCommissionEmployee\" method=\"post\" name=\"form2\">");
		out.println("id:<br><input type=\"text\" name=\"id\" value=\"\"><br><br>");
		out.println("基本工资:<br><input type=\"text\" name=\"salary\" value=\"\"><br><br>");
		out.println("提成率:<br><input type=\"text\" name=\"commissionRate\" value=\"\"><br><br>");
		out.println("<input type=\"submit\" value=\"Submit\">");
		out.println("</form>");
		out.println("<h3>改为编制人员</h3>");
		out.println("<form action=\"ChangeToSalaryEmployee\" method=\"post\" name=\"form3\">");
		out.println("id:<br><input type=\"text\" name=\"id\" value=\"\"><br><br>");
		out.println("月薪:<br><input type=\"text\" name=\"salary\" value=\"\"><br><br>");
		out.println("<input type=\"submit\" value=\"Submit\">");
		out.println("</form>");
		out.println("</body>");
		out.println("</html>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
