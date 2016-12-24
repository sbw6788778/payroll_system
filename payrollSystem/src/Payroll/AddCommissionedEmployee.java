package Payroll;

public class AddCommissionedEmployee extends AddEmployeeTransaction{
	private double salary;
	private double commissionRate;
	public AddCommissionedEmployee( int id,String name,String add,double s,double c,PayrollDatebase database){
		super(id,name,add,database);
		this.salary=s;
		this.commissionRate=c;
	}
	protected PaymentClassification makeClassification(){
		return new CommissionedClassification(salary,commissionRate);
	}
	protected PaymentSchedule makeSchedule(){
		return new BiweeklySchedule();
	}
}
