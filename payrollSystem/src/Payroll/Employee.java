package Payroll;
import java.util.Date;
public class Employee {
	private PayrollDatebase database;
	private int empid;
	private String name;
	private String address;
	private PaymentClassification classification;
	private PaymentSchedule schedule;
	private PaymentMethod method;
	private Affiliation affiliation=new NoAffiliation(database);
	
	public Employee(int empid,String name ,String address,PayrollDatebase database){
		this.database=database;
		this.empid=empid;
		this.name=name;
		this.address=address;
	}
	public int getId(){
		return this.empid;
	}
	public String getName(){
		return name;
	}
	public void setName(String nam){
		this.name=nam;
	}
	public String getAddress(){
		return address;
	}
	public void setAddress(String name){
		 this.address=name;
	}
	public PaymentClassification getClassification(){
		return classification;
	}
	public void setClassification(PaymentClassification a){
		 this.classification=a;
	}
	public PaymentSchedule getSchedule(){
		return schedule;
	}
	public void setSchedule(PaymentSchedule a){
		 this.schedule=a;
	}
	public PaymentMethod getMethod(){
		return method;
	}
	public void setMethod(PaymentMethod a){
		 this.method=a;
	}
	public Affiliation getAffiliation(){
		return affiliation;
	}
	public void setAffiliation(Affiliation name){
		 this.affiliation=name;
	}
	public boolean isPayDate(Date date){
		return schedule.isPayDate(date);
	}
	public void payday(Paycheck paycheck){
		double grossPay=classification.calculatePay(paycheck);
		double deductions=affiliation.calculateDeductions(paycheck);
		double netPay=grossPay-deductions;
		paycheck.setGrossPay(grossPay);
		paycheck.setDeductions(deductions);
		paycheck.setNetPay(netPay);
		method.pay(paycheck);
	}
	public Date getPayPeriodStartDate(Date date){
		return schedule.getPayPeriodStartDate(date);
	}

}
