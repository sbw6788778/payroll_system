package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Payroll.PaydayTransaction;
import Payroll.PayrollDatebase;
import Payroll.SqlPayrollDatebase;

/**
 * Servlet implementation class ChooseFunction
 */
public class ChooseFunction extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public static SqlPayrollDatebase database=new SqlPayrollDatebase();
    /**
     * @see HttpServlet#HttpServlet()
     */
    @Override
    public void init() throws ServletException {
    	// TODO Auto-generated method stub
    	super.init();
    	Payday.execute();
    }
    public ChooseFunction() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		String function=request.getParameter("function");
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out=response.getWriter();
		if(function.equals("增加小时工"))
			response.sendRedirect("../addHourlyEmployee.jsp");
		else if(function.equals("增加绩效工"))
			response.sendRedirect("../addCommissionEmployee.jsp");
		else if(function.equals("增加编制人员"))
			response.sendRedirect("../addSalaryEmployee.jsp");
		else if(function.equals("删除员工"))
			response.sendRedirect("../deleteEmployee.jsp");
		else if(function.equals("显示员工信息"))
			response.sendRedirect("ShowEmployeeMessage");
		
		out.println("<h1>不行</h1>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
