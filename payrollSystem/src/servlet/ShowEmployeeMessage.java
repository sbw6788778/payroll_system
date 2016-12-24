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
		out.println("<title>Ա��֧��ϵͳ</title>");
		out.println("</head>");
		out.println("<body>");
		out.println("<h1>����Ա������Ϣ</h1>");
		out.println("<table border=\"1\">");
		out.println("<tr>");
		out.println("  <td>id</td>");
		out.println("  <td>����</td>");
		out.println("  <td>��ַ</td>");
		out.println("  <td>Ա������</td>");
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
		out.println("<h2>����Ա������</h2>");
		out.println("<h3>��ΪСʱ��</h3>");
		out.println("<form action=\"ChangeToHourlyEmployee\" method=\"post\" name=\"form1\">");
		out.println("id:<br><input type=\"text\" name=\"id\" value=\"\"><br><br>");
		out.println("һСʱ�Ĺ���:<br><input type=\"text\" name=\"salary\" value=\"\"><br><br>");
		out.println("<input type=\"submit\" value=\"Submit\">");
		out.println("</form>");
		out.println("<h3>��Ϊ��Ч��</h3>");
		out.println("<form action=\"ChangeToCommissionEmployee\" method=\"post\" name=\"form2\">");
		out.println("id:<br><input type=\"text\" name=\"id\" value=\"\"><br><br>");
		out.println("��������:<br><input type=\"text\" name=\"salary\" value=\"\"><br><br>");
		out.println("�����:<br><input type=\"text\" name=\"commissionRate\" value=\"\"><br><br>");
		out.println("<input type=\"submit\" value=\"Submit\">");
		out.println("</form>");
		out.println("<h3>��Ϊ������Ա</h3>");
		out.println("<form action=\"ChangeToSalaryEmployee\" method=\"post\" name=\"form3\">");
		out.println("id:<br><input type=\"text\" name=\"id\" value=\"\"><br><br>");
		out.println("��н:<br><input type=\"text\" name=\"salary\" value=\"\"><br><br>");
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
