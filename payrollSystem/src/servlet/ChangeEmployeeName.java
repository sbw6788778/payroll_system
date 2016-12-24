package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Payroll.ChangeNameTransaction;
import Payroll.Employee;

/**
 * Servlet implementation class ChangeEmployeeName
 */
public class ChangeEmployeeName extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ChangeEmployeeName() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		String newName=request.getParameter("newName");
		Employee e= (Employee)request.getSession().getAttribute("username");
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out=response.getWriter();
		ChangeNameTransaction cnt=new ChangeNameTransaction(e.getId(), newName, ChooseFunction.database);
		try{
			cnt.execute();
			out.println("修改姓名成功了");
			request.getSession().setAttribute("username",ChooseFunction.database.GetEmployee(e.getId()));
		}catch(Exception p){
			p.printStackTrace();
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
