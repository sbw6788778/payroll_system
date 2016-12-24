package Payroll;

public class ChangeHourlyTransaction extends ChangeClassificationTransaction {
	private double hourlyRate;
	public  ChangeHourlyTransaction(int id,double hourlyRate,PayrollDatebase database){
		super(id,database);
		this.hourlyRate=hourlyRate;
	}
	protected PaymentClassification classification(){
		return new HourlyClassification(hourlyRate);
	}
	protected  PaymentSchedule schedule(){
		return new WeeklySchedule();
	}

}
