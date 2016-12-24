package Payroll;

public abstract class Affiliation {
	protected PayrollDatebase database;
	public Affiliation(PayrollDatebase database){
		this.database=database;
	}
	public abstract double calculateDeductions(Paycheck p);
}
