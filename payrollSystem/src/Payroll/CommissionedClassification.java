package Payroll;

import java.util.Hashtable;
import java.util.Date;
public class CommissionedClassification implements PaymentClassification {
	private double basePay;
	private double commissionRate;
	private Hashtable<Date, SalesReceipt>  salesReceipt=new Hashtable<Date, SalesReceipt>();
	public CommissionedClassification(double salary,double commissionrate){
		this.basePay=salary;
		this.commissionRate=commissionrate;
	}
	public double getBasePay(){
		return this.basePay;
	}
	public double getCommissionRate(){
		return this.commissionRate;
	}
	public  void addSalesReceipt(SalesReceipt s){
		this.salesReceipt.put(s.getDate(), s);
	};
	public SalesReceipt getSalesReceipt(Date d){
		return salesReceipt.get(d);
	}
	@Override
	public double calculatePay(Paycheck paycheck) {
		double amount=0;
		for(Date d :salesReceipt.keySet()){
			amount+=salesReceipt.get(d).getAmount();
		}
			return basePay+commissionRate*amount;

	}
	public String toString(){
		return "领提成加2周薪员工";
	}

}
