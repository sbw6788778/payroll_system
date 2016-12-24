package Payroll;

public class AddSalariedEmployee extends AddEmployeeTransaction {
	private double salary;
	public AddSalariedEmployee( int id,String name,String add,double s,PayrollDatebase database){
		super(id,name,add,database);
		this.salary=s;
	}
	protected PaymentClassification makeClassification(){
		return new SalariedClassification(salary);
	}
	protected PaymentSchedule makeSchedule(){
		return new MonthlySchedule();
	}
	

}
