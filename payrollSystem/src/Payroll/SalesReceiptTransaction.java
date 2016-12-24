package Payroll;
import java.util.Date;

public class SalesReceiptTransaction extends Transaction {
	private Date date;
	private int id;
	private double amount;
	public SalesReceiptTransaction(int id,Date date,double amount,PayrollDatebase database){
		super(database);
		this.id=id;
		this.date=date;
		this.amount=amount;
	}
	public void execute()throws Exception{
		Employee e=database.GetEmployee(id);
		if(e!=null){
			CommissionedClassification cc=(CommissionedClassification)e.getClassification();
			if(cc!=null)
				database.addSalesReceipt(id,new SalesReceipt(date,amount));
			else
				throw new Exception("没有这个员工");
		}
		else
			throw new Exception("没这个员工");
		
	}

}
