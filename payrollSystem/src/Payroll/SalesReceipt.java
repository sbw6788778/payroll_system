package Payroll;
import java.util.Date; 
public class SalesReceipt {
	private Date date;
	private double amount;
	public SalesReceipt(Date date,double amount) {
		this.date=date;
		this.amount=amount;
	}
	public Date getDate(){
		return this.date;
	}
	public double getAmount(){
		return this.amount;
	}

}
