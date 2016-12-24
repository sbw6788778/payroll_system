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
import Payroll.SalesReceiptTransaction;
import Payroll.ServiceChargeTransaction;

/**
 * Servlet implementation class AddSalesReceipt
 */
public class AddSalesReceipt extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddSalesReceipt() {
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
		String[] a=date.split("-");
		Integer[] b=new Integer[3];
		for(int i=0;i<3;i++){
			b[i]=Integer.parseInt(a[i]);
		}
		Date d=new Date(b[0]-1900,b[1]-1,b[2]);
		Employee e= (Employee)request.getSession().getAttribute("username");
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out=response.getWriter();
		SalesReceiptTransaction cnt=new SalesReceiptTransaction(e.getId(),d,amount,ChooseFunction.database);
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
