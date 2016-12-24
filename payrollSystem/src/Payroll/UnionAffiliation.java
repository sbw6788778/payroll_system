package Payroll;

import java.util.Hashtable;
import java.util.Date;
public class UnionAffiliation extends Affiliation {
	private double dues;
	private int memId;
	private Hashtable<Date,ServiceCharge> serviceCharge=new Hashtable<Date,ServiceCharge>();
	public  UnionAffiliation(int memId, double dues,PayrollDatebase database){
		super(database);
		this.dues=dues;
		this.memId=memId;
	}
	public  UnionAffiliation(PayrollDatebase database){super(database);}
	public void addServiceCharge(Date d,ServiceCharge s){
		this.serviceCharge.put(d, s);
	}
	public ServiceCharge getServiceCharge(Date d){
		return serviceCharge.get(d);
	}
	public int getMemId(){
		return this.memId;
	}
	public double calculateDeductions(Paycheck p){
		double totlePay=0.0;
		for(Date d:serviceCharge.keySet()){
			if(DateUtil.isInPayPeriod(d, p.getStartDate(), p.getDateTime()))
				totlePay=totlePay+serviceCharge.get(d).getCharge();
		}
		PaymentClassification pc= database.GetUnionMember(memId).getClassification();
		if (pc instanceof SalariedClassification)
			totlePay=4*this.dues+totlePay;
		else if(pc instanceof HourlyClassification)
			totlePay=this.dues+totlePay;
		else if(pc instanceof CommissionedClassification)
			totlePay=2*this.dues+totlePay;
		return totlePay;
	}
	public String toString(){
		return "工会成员";
	}
}
