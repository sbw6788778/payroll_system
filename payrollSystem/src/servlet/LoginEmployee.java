package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Timer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Payroll.Employee;

/**
 * Servlet implementation class LoginEmployee
 */
public class LoginEmployee extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginEmployee() {
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
		PrintWriter out=response.getWriter();
		String username= request.getParameter("username");
		String password= request.getParameter("password");
		String result= ChooseFunction.database.loginEmployeeVerification(username, password);
		if(result.equals("√‹¬Î’˝»∑")){
			int id=ChooseFunction.database.getIdByUsername(username);
			Employee e= ChooseFunction.database.GetEmployee(id);
			request.getSession().setAttribute("username", e);
			response.sendRedirect("../employeeFunction.jsp");
		}
		else if(result.equals("√‹¬Î¥ÌŒÛ")){
			out.println("√‹¬Î¥ÌŒÛ");
			response.sendRedirect("../index.jsp");
		}
		else {
			out.println("”√ªß√˚¥ÌŒÛ");
			
			response.sendRedirect("../index.jsp");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
