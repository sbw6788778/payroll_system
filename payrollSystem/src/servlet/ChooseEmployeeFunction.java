package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ChooseEmployeeFunction
 */
public class ChooseEmployeeFunction extends HttpServlet {
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ChooseEmployeeFunction() {
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
		if(function.equals("�鿴������Ϣ"))
			response.sendRedirect("../showPersonalMessage.jsp");
		else if(function.equals("���ʱ�̱�"))
			response.sendRedirect("../addTimeCard.jsp");
		else if(function.equals("������ۼ�¼"))
			response.sendRedirect("../addSalesReceipt.jsp");
		else if(function.equals("��ӹ�������"))
			response.sendRedirect("../addServiceCharge.jsp");
		
		out.println("<h1>����</h1>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
