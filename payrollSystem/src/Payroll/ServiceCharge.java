package Payroll;

import java.util.Date;

public class ServiceCharge {
	private Date date;
	private double amount;
	public ServiceCharge(Date date,double amount){
		this.date=date;
		this.amount=amount;
	}
	public Date getDate(){
		return date;
	}
	public double getCharge(){
		return amount;
	}

}
