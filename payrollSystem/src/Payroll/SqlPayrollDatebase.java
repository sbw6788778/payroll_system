package Payroll;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;

import org.omg.CORBA.UnionMember;

public class SqlPayrollDatebase implements PayrollDatebase {
	private Connection conn;
	public SqlPayrollDatebase(){
		try{
			Class.forName("com.mysql.jdbc.Driver");
			this.conn=DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/payrolldatabase", "root", "1248576");
		}catch(Exception p){
			p.printStackTrace();
			System.out.println("数据库加载失败");
		}
	}
	public void AddRegisterMessage(int id,String username,String password,String email){
		try{
			String sql=""+"INSERT INTO employeeloginmessage "+"(EmpId,Username,Password,Email)"
		+"VALUES(?,?,?,?)";
			PreparedStatement ps=conn.prepareStatement(sql);
			ps.setInt(1, id);
			ps.setString(2, username);
			ps.setString(3, password);
			ps.setString(4, email);
			ps.execute();
		}catch(Exception p){
			p.printStackTrace();
			System.out.println("sql语法错误");
		}
	}
	public void AddEmployee(int id ,Employee employee){
		try{
			String sql=""+"INSERT INTO employee "+"(EmpId,Name,Address,ScheduleType,PaymentMethodType,PaymentClassificationType)"
		+"VALUES(?,?,?,?,?,?)";
			PreparedStatement ps=conn.prepareStatement(sql);
			ps.setInt(1, id);
			ps.setString(2, employee.getName());
			ps.setString(3, employee.getAddress());
			ps.setString(4, employee.getSchedule().toString());
			ps.setString(5, employee.getMethod().toString());
			ps.setString(6, employee.getClassification().toString());
			ps.execute();
			savePaymentMethod(employee);
			savePaymentClassification(employee);
		}catch(Exception p){
			p.printStackTrace();
			System.out.println("sql语法错误");
		}
		
	}
	private void savePaymentMethod( Employee employee){
		PaymentMethod pm=employee.getMethod();
		if(pm instanceof DirectMethod){
			String bank=((DirectMethod) pm).getBank();
			String account =((DirectMethod) pm).getAccount();
			String sql="INSERT INTO directdepositaccount (EmpId,Bank,Account) VALUES(?,?,?)";
			try{
				PreparedStatement ps=conn.prepareStatement(sql);
				ps.setInt(1, employee.getId());
				ps.setString(2, bank);
				ps.setString(3, account);
				ps.execute();
			}catch(Exception p)
			{
				p.printStackTrace();
				System.out.println("存directdepositaccount表失败了");
			}
		}
		else if(pm instanceof MailMethod){
			String address=((MailMethod) pm).getAddress();
			String sql="INSERT INTO paycheckaddress (EmpId,Address) VALUES(?,?)";
			try{
				PreparedStatement ps=conn.prepareStatement(sql);
				ps.setInt(1, employee.getId());
				ps.setString(2, address);
				ps.execute();
			}catch(Exception p)
			{
				p.printStackTrace();
				System.out.println("存paycheckaddress表失败了");
			}
		}
		else return;
	}
	private void savePaymentClassification(Employee employee){
		PaymentClassification pc=employee.getClassification();
		if(pc instanceof HourlyClassification){
			double hourlyRate =((HourlyClassification) pc).getHourlyRate();
			String sql="INSERT INTO hourlyclassification (EmpId,HourlyRate) VALUES(?,?)";
			try{
				PreparedStatement ps=conn.prepareStatement(sql);
				ps.setInt(1, employee.getId());
				ps.setDouble(2, hourlyRate);;
				ps.execute();
			}catch(Exception p)
			{
				p.printStackTrace();
				System.out.println("存hourlyclassification表失败了");
			}
		}
		else if(pc instanceof SalariedClassification){
			double salary =((SalariedClassification) pc).getSalary();
			String sql="INSERT INTO salariedclassification (EmpId,Salary) VALUES(?,?)";
			try{
				PreparedStatement ps=conn.prepareStatement(sql);
				ps.setInt(1, employee.getId());
				ps.setDouble(2,salary);;
				ps.execute();
			}catch(Exception p)
			{
				p.printStackTrace();
				System.out.println("存SalariedClassification表失败了");
			}
		}
		else if(pc instanceof CommissionedClassification){
			double salary = ((CommissionedClassification) pc).getBasePay();
			double commissionRate=((CommissionedClassification) pc).getCommissionRate();
			String sql="INSERT INTO commissionedclassification (EmpId,Salary,Commission) VALUES(?,?,?)";
			try{
				PreparedStatement ps=conn.prepareStatement(sql);
				ps.setInt(1, employee.getId());
				ps.setDouble(2,salary);
				ps.setDouble(3,commissionRate);
				ps.execute();
			}catch(Exception p)
			{
				p.printStackTrace();
				System.out.println("存CommissionedClassification表失败了");
			}
		}
		else return;
	}
	public Employee GetEmployee(int id){
		 Employee e=null;
		 try{
			 	String sql="SELECT * FROM employee WHERE EmpId=?";
				PreparedStatement ps=conn.prepareStatement(sql);
				ps.setInt(1, id);
				ResultSet rs= ps.executeQuery();
				while(rs.next())
				{
					String name=rs.getString("Name");
					String address=rs.getString("Address");
					e=new Employee(id,name,address,this);
					loadSchedule(e,rs);
					loadClassificationTable(e,rs);
					loadPaymentMethodTable(e,rs);
					loadTimeCard(e);
					loadSalesreceipt(e);
					loadAffiliation(e);
					loadServiceCharge(e);
  				}
				
			}catch(Exception p){
				System.out.println("sql语法错误");
			}
		 return e;
	 }
	private void loadServiceCharge(Employee e){
		if(e.getAffiliation() instanceof UnionAffiliation) {
			UnionAffiliation ua=(UnionAffiliation)e.getAffiliation();
			double amount=0;
			try{
			 	String sql="SELECT * FROM servicecharge WHERE AffiliationId=?";
				PreparedStatement ps=conn.prepareStatement(sql);
				ps.setInt(1, ua.getMemId());
				ResultSet rs= ps.executeQuery();
				while(rs.next())
				{
					ua.addServiceCharge(rs.getDate("Date"),new ServiceCharge(rs.getDate("Date"), rs.getDouble("Amount")));
				}
			}catch(Exception p){
				System.out.println("sql语法错误");
			}
		}
	}
 	private void loadAffiliation(Employee e){
		int AffiliationId=0;
		double dues=0;
		try{
		 	String sql="SELECT AffiliationId FROM employeeaffiliation WHERE EmpId=?";
			PreparedStatement ps=conn.prepareStatement(sql);
			ps.setInt(1, e.getId());
			ResultSet rs= ps.executeQuery();
			if(rs.next())
			{
				AffiliationId=rs.getInt("AffiliationId");
				sql="SELECT Dues FROM affiliation WHERE Id=?";
				PreparedStatement ps2=conn.prepareStatement(sql);
				ps2.setInt(1, AffiliationId);
				ResultSet rs2= ps2.executeQuery();
				while(rs2.next())
				{
					dues=rs2.getDouble("Dues");
				}
				e.setAffiliation(new UnionAffiliation(AffiliationId, dues, this));
			}
			else 
				e.setAffiliation(new NoAffiliation(this));
		}catch(Exception p){
			System.out.println("sql语法错误");
		}
	}
	 private void loadSalesreceipt(Employee e){
		 if(e.getClassification() instanceof CommissionedClassification){
			 CommissionedClassification cc=(CommissionedClassification)e.getClassification();
			 try{
				 String sql="SELECT * FROM salesreceipt WHERE EmpId=?";
				 PreparedStatement ps_salesreceipt=conn.prepareStatement(sql);
				 ps_salesreceipt.setInt(1, e.getId());
				 ResultSet rs_salesreceipt=ps_salesreceipt.executeQuery();
				 while(rs_salesreceipt.next()){
					cc.addSalesReceipt(new SalesReceipt(rs_salesreceipt.getDate("Date"),rs_salesreceipt.getDouble("Amount")));
				 }
			 }catch(Exception p){
				 p.printStackTrace();
				 System.out.println("加载销售记录失败");
			 }
		 }else return;
		
	 }
	 private void loadTimeCard(Employee e){
		 if(e.getClassification() instanceof HourlyClassification){
			 HourlyClassification hc=(HourlyClassification)e.getClassification();
			 try{
				 String sql="SELECT * FROM timecard WHERE EmpId=?";
				 PreparedStatement ps_timecard=conn.prepareStatement(sql);
				 ps_timecard.setInt(1, e.getId());
				 ResultSet rs_timecard=ps_timecard.executeQuery();
				 while(rs_timecard.next()){
					 Date d=rs_timecard.getDate("Date");
					 Calendar ca=Calendar.getInstance();
					ca.setTime(d);
					hc.addTimeCard(new TimeCard(ca.getTime(), rs_timecard.getDouble("Hours")));
				 }
			 }catch(Exception p){
				 p.printStackTrace();
				 System.out.println("加载时间表失败");
			 }
		 }else return;
		
	 }
	 private void loadSchedule(Employee e,ResultSet rs){
		 try{
			 String scheduletype=rs.getString("ScheduleType");
			 if(scheduletype.equals("周支付")){
				 e.setSchedule(new WeeklySchedule());
			 }
			 else if(scheduletype.equals("两周支付")){
				 e.setSchedule(new BiweeklySchedule());
			 }
			 else if(scheduletype.equals("月支付")){
				 e.setSchedule(new MonthlySchedule());
			 }
			 else{return;}
		 }catch (Exception p) {
			 System.out.println("创建雇员对象时的初始化类型信息失败");
			 p.printStackTrace();
		 }
	 }
	 private void loadClassificationTable(Employee e,ResultSet rs){
		 try{
			 String paymentClassificationType=rs.getString("PaymentClassificationType");
			 if(paymentClassificationType.equals("小时工")){
				 String sql="SELECT * FROM hourlyclassification WHERE EmpId=?";
				 PreparedStatement ps_hourlyclassification=conn.prepareStatement(sql);
				 ps_hourlyclassification.setInt(1, e.getId());
				 ResultSet rs_hourlyclassification=ps_hourlyclassification.executeQuery();
				 while(rs_hourlyclassification.next()){
					 e.setClassification(new HourlyClassification(rs_hourlyclassification.getDouble("HourlyRate")));
				 }
			 }
			 else if(paymentClassificationType.equals("领提成加2周薪员工")){
				 String sql="SELECT * FROM commissionedclassification WHERE EmpId=?";
				 PreparedStatement ps_commissionedclassification=conn.prepareStatement(sql);
				 ps_commissionedclassification.setInt(1, e.getId());
				 ResultSet rs_commissionedclassification=ps_commissionedclassification.executeQuery();
				 while(rs_commissionedclassification.next()){
					 e.setClassification(new CommissionedClassification(rs_commissionedclassification.getDouble("Salary"), 
							 rs_commissionedclassification.getDouble("Commission")));
				 }
			 }
			 else if(paymentClassificationType.equals("领月薪员工")){
				 String sql="SELECT * FROM salariedclassification WHERE EmpId=?";
				 PreparedStatement ps_salariedclassification=conn.prepareStatement(sql);
				 ps_salariedclassification.setInt(1, e.getId());
				 ResultSet rs_salariedclassification=ps_salariedclassification.executeQuery();
				 while(rs_salariedclassification.next()){
					 e.setClassification(new SalariedClassification(rs_salariedclassification.getDouble("Salary")));
				 }
			 }
			 else{return;}
		 }catch (Exception p) {
			 System.out.println("创建雇员对象时的初始化类型信息失败");
			 p.printStackTrace();
		 }
	 }
	 private void loadPaymentMethodTable(Employee e,ResultSet rs){
		 try{
			 String paymentMethod=rs.getString("PaymentMethodType");
			 if(paymentMethod.equals("支票打入银行账户")){
				 String sql="SELECT * FROM directdepositaccount WHERE EmpId=?";
				 PreparedStatement ps_directdepositaccount=conn.prepareStatement(sql);
				 ps_directdepositaccount.setInt(1, e.getId());
				 ResultSet rs_directdepositaccount=ps_directdepositaccount.executeQuery();
				 while(rs_directdepositaccount.next()){
					 e.setMethod(new DirectMethod(rs_directdepositaccount.getString("Bank"), 
							 rs_directdepositaccount.getString("Account")));
				 }
			 }
			 else if(paymentMethod.equals("支票保存在出纳人员")){
				 e.setMethod(new HoldMethod());
			 }
			 else if(paymentMethod.equals("支票邮寄")){
				 String sql="SELECT * FROM paycheckaddress WHERE EmpId=?";
				 PreparedStatement ps_paycheckaddress=conn.prepareStatement(sql);
				 ps_paycheckaddress.setInt(1, e.getId());
				 ResultSet rs_paycheckaddress=ps_paycheckaddress.executeQuery();
				 while(rs_paycheckaddress.next()){
					 e.setMethod(new MailMethod(rs_paycheckaddress.getString("Address")));
				 }
			 }
			 else{e.setMethod(new HoldMethod()); return;}
		 }catch (Exception p) {
			 System.out.println("创建雇员对象时的初始化类型信息失败");
			 p.printStackTrace();
		 }
	 }
	public void DeleteEmployee(int id){
		try{
			String sql=""+"DELETE FROM employee WHERE EmpId=?";
			PreparedStatement ps=conn.prepareStatement(sql);
			ps.setInt(1, id);
			ps.execute();
		}catch(Exception p){
			p.printStackTrace();
			System.out.println("sql语法错误");
		}
		 
	 }
	public void addTimeCard(int id,TimeCard t){
		try{
			String sql="INSERT INTO timecard (Empid,Date,Hours) VALUES (?,?,?)";
			PreparedStatement ps=conn.prepareStatement(sql);
			ps.setInt(1, id);
			java.sql.Date date=new java.sql.Date(t.getDate().getTime());
			ps.setDate(2, date);
			ps.setDouble(3, t.getHours());
			ps.execute();
		}catch(Exception p){
			p.printStackTrace();
			System.out.println("添加时间表错误");
		}
	}
	public void addSalesReceipt(int id,SalesReceipt sr){
		try{
			String sql="INSERT INTO salesreceipt (Empid,Date,Amount) VALUES (?,?,?)";
			PreparedStatement ps=conn.prepareStatement(sql);
			ps.setInt(1, id);
			java.sql.Date date=new java.sql.Date(sr.getDate().getTime());
			ps.setDate(2, date);
			ps.setDouble(3, sr.getAmount());
			ps.execute();
		}catch(Exception p){
			p.printStackTrace();
			System.out.println("添加销售记录错误");
		}
	}
	public void addServiceCharge(int memId,ServiceCharge sc){
		try{
			String sql="INSERT INTO servicecharge (AffiliationId,Date,Amount) VALUES (?,?,?)";
			PreparedStatement ps=conn.prepareStatement(sql);
			ps.setInt(1, memId);
			java.sql.Date date=new java.sql.Date(sc.getDate().getTime());
			ps.setDate(2, date);
			ps.setDouble(3, sc.getCharge());
			ps.execute();
		}catch(Exception p){
			p.printStackTrace();
			System.out.println("添加工会服务费错误");
		}
	}
	public void changeAddress(int id,String newAddress){
		try{
			String sql="UPDATE employee SET Address=? WHERE EmpId=?";
			PreparedStatement ps= conn.prepareStatement(sql);
			ps.setString(1, newAddress);
			ps.setInt(2, id);
			ps.execute();
		}catch(Exception p){
			p.printStackTrace();
			System.out.println("更改地址失败");
		}
	}
	public void changeName(int id,String newName){
		try{
			String sql="UPDATE employee SET Name=? WHERE EmpId=?";
			PreparedStatement ps= conn.prepareStatement(sql);
			ps.setString(1, newName);
			ps.setInt(2, id);
			ps.execute();
		}catch(Exception p){
			p.printStackTrace();
			System.out.println("更改姓名失败");
		}
	}
	public void changeMethod(Employee e){
		try{
			String sql="UPDATE employee SET PaymentMethodType=? WHERE EmpId=?";
			PreparedStatement ps=conn.prepareStatement(sql);
			ps.setString(1, e.getMethod().toString());
			ps.setInt(2,e.getId());
			ps.execute();
			deleteMethod(e);
			savePaymentMethod(e);
		}catch(Exception p){
			p.printStackTrace();
		}
	}
	public void deleteMethod(Employee e){
		try{
			String sql="DELETE FROM paycheckaddress WHERE EmpId=?";
			 PreparedStatement ps=conn.prepareStatement(sql);
			 ps.setInt(1, e.getId());
			 ps.execute();
			 sql="DELETE FROM directdepositaccount WHERE EmpId=?";
			 PreparedStatement ps2=conn.prepareStatement(sql);
			 ps2.setInt(1, e.getId());
			 ps2.execute();
		}catch(Exception p){
			p.printStackTrace();
			System.out.println("删除支付方法失败");
		}
	}
	public Employee GetUnionMember(int id){
		int empid=0;
		try{
		 	String sql="SELECT EmpId FROM employeeaffiliation WHERE AffiliationId=?";
			PreparedStatement ps=conn.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs= ps.executeQuery();
			while(rs.next())
			{
				empid=rs.getInt("EmpId");
			}
			
		}catch(Exception p){
			System.out.println("sql语法错误");
		}
		return GetEmployee(empid);
		
	 }
	public  void addUnionMember( int memId,double dues,Employee e){
		 try{
			 String sql=""+"INSERT INTO affiliation "+"(Id,Dues)"
						+"VALUES(?,?)";
			 PreparedStatement ps=conn.prepareStatement(sql);
			 ps.setInt(1,memId);
			 ps.setDouble(2, dues);
			 ps.execute();
			 sql="INSERT INTO employeeaffiliation "+"(EmpId,AffiliationId)"
						+"VALUES(?,?)";
			 PreparedStatement ps2=conn.prepareStatement(sql);
			 ps2.setInt(1,e.getId());
			 ps2.setInt(2,memId);
			 ps2.execute();
		 }catch(Exception p){
			 p.printStackTrace();
			 System.out.println("添加工会失败");}
	 }
	 public void removeUnionMember(int memId){
		 try{
				String sql="DELETE FROM affiliation WHERE Id=?";
				 PreparedStatement ps=conn.prepareStatement(sql);
				 ps.setInt(1,memId);
				 ps.execute();
			}catch(Exception p){
				p.printStackTrace();
				System.out.println("删除支付方法失败");
			}
	 }
	 public Set<Integer> GetAllEmployeeIds(){
		 Set<Integer> ids=new HashSet<Integer>();
		 try{
			 String sql="SELECT EmpId FROM employee";
			 PreparedStatement ps=conn.prepareStatement(sql);
			 ResultSet rs=ps.executeQuery();
			 while(rs.next()){
				 ids.add(rs.getInt("EmpId"));
			 }
		 }catch(Exception p){
			 p.printStackTrace();
			 System.out.println("获取所有id时错误");
		 }
		 return ids;
	 }
	 public String loginEmployeeVerification(String username,String password){
		 try{
			 String sql="SELECT Password FROM employeeloginmessage WHERE Username=?";
			 PreparedStatement p= conn.prepareStatement(sql);
			 p.setString(1, username);
			 ResultSet r=p.executeQuery();
			 while(r.next()){
				 if(password.equals(r.getString("Password")))
					 return "密码正确";
				 else return "密码错误";
			 }
		 }catch(Exception p){
			 System.out.println("登录验证出现异常");
			 p.printStackTrace();
		 }
		 return "没有此人";
		 
	 }
	 public String loginAdminVerification(String username,String password){
		 try{
			 String sql="SELECT Password FROM adminloginmessage WHERE Username=?";
			 PreparedStatement p= conn.prepareStatement(sql);
			 p.setString(1, username);
			 ResultSet r=p.executeQuery();
			 while(r.next()){
				 if(password.equals(r.getString("Password")))
					 return "密码正确";
				 else return "密码错误";
			 }
		 }catch(Exception p){
			 System.out.println("登录验证出现异常");
			 p.printStackTrace();
		 }
		 return "没有此人";
		 
	 }
	 public int getIdByUsername(String username){
		 int id=0;
		 try{
			 String sql="SELECT EmpId FROM employeeloginmessage WHERE Username=?";
			 PreparedStatement p= conn.prepareStatement(sql);
			 p.setString(1, username);
			 ResultSet r=p.executeQuery();
			 while(r.next()){
				 
				 id=r.getInt("EmpId");
			 }
		 }catch(Exception p){
			 System.out.println("登录验证出现异常");
			 p.printStackTrace();
		 }
		 return id;
	 }
	 public String getEmailById(int id){
		 String email=null;
		 try{
			 String sql="SELECT Email FROM employeeloginmessage WHERE EmpId=?";
			 PreparedStatement p= conn.prepareStatement(sql);
			 p.setInt(1, id);
			 ResultSet r=p.executeQuery();
			 while(r.next()){
				 
				 email=r.getString("Email");
			 }
		 }catch(Exception p){
			 System.out.println("登录验证出现异常");
			 p.printStackTrace();
		 }
		 return email;
	 }
}
