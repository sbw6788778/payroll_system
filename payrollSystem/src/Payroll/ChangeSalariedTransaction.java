package Payroll;

public class ChangeSalariedTransaction extends ChangeClassificationTransaction {
	private double salary;
	public  ChangeSalariedTransaction(int id,double salary,PayrollDatebase database){
		super(id,database);
		this.salary=salary;
	}
	protected PaymentClassification classification(){
		return new SalariedClassification(salary);
	}
	protected  PaymentSchedule schedule(){
		return new MonthlySchedule();
	}
}
