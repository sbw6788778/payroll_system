package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Payroll.ChangeCommissionedTransaction;
import Payroll.ChangeHourlyTransaction;

/**
 * Servlet implementation class ChangeToCommissionEmployee
 */
public class ChangeToCommissionEmployee extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ChangeToCommissionEmployee() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		int id=Integer.parseInt(request.getParameter("id"));
		double salary=Double.parseDouble(request.getParameter("salary"));
		double commissionRate=Double.parseDouble(request.getParameter("commissionRate"));
		ChangeCommissionedTransaction cht=new ChangeCommissionedTransaction(id, salary,commissionRate,ChooseFunction.database);
		try{
			cht.execute();
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out=response.getWriter();
			out.println("ÐÞ¸Ä³É¹¦");
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
