package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import Payroll.*;
/**
 * Servlet implementation class AddHourlyEmployee
 */
public class AddHourlyEmployee extends HttpServlet {
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddHourlyEmployee() {
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
		String name= request.getParameter("name");
		String address=request.getParameter("address");
		double hourlyRate=Double.parseDouble(request.getParameter("hourlyRate"));
		int id=Integer.parseInt(request.getParameter("id"));
		Payroll.AddHourlyEmployee ahe=new Payroll.AddHourlyEmployee(id,name,address,hourlyRate,ChooseFunction.database);
		ahe.execute();
		PrintWriter out= response.getWriter();
		out.println("保存成功了");
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
