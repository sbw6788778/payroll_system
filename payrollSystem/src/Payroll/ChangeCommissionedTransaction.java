package Payroll;

public class ChangeCommissionedTransaction extends ChangeClassificationTransaction{
	private double salary;
	private double commissionRate;
	public  ChangeCommissionedTransaction(int id,double salary,double commissionRate,PayrollDatebase database){
		super(id,database);
		this.salary=salary;
		this.commissionRate=commissionRate;
	}
	protected PaymentClassification classification(){
		return new CommissionedClassification(salary,commissionRate);
	}
	protected  PaymentSchedule schedule(){
		return new BiweeklySchedule();
	}

}
