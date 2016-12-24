package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Payroll.Employee;
import Payroll.ServiceChargeTransaction;
import Payroll.TimeCardTransaction;

/**
 * Servlet implementation class AddServiceCharge
 */
public class AddServiceCharge extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddServiceCharge() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		double amount=Double.parseDouble(request.getParameter("amount"));
		String date=request.getParameter("date");
		int unionId=Integer.parseInt(request.getParameter("unionId"));
		String[] a=date.split("-");
		Integer[] b=new Integer[3];
		for(int i=0;i<3;i++){
			b[i]=Integer.parseInt(a[i]);
		}
		Date d=new Date(b[0]-1900,b[1]-1,b[2]);
		Employee e= (Employee)request.getSession().getAttribute("username");
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out=response.getWriter();
		ServiceChargeTransaction cnt=new ServiceChargeTransaction(unionId,amount,d,ChooseFunction.database);
		try{
			cnt.execute();
			out.println("添加成功了");
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
