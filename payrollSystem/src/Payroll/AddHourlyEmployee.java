package Payroll;

public class AddHourlyEmployee extends AddEmployeeTransaction {
	private double hourlyRate;
	public AddHourlyEmployee( int id,String name,String add,double s,PayrollDatebase database){
		super(id,name,add,database);
		this.hourlyRate=s;
	}
	protected PaymentClassification makeClassification(){
		return new HourlyClassification(hourlyRate);
	}
	protected PaymentSchedule makeSchedule(){
		return new WeeklySchedule();
	}
}
