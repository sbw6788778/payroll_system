package Payroll;

public abstract class ChangeClassificationTransaction extends ChangeEmployeeTransaction {
	public ChangeClassificationTransaction(int id,PayrollDatebase database){
		super(id,database);
	}
	protected void change(Employee e){
		database.DeleteEmployee(id);
		e.setClassification(classification());
		e.setSchedule(schedule());
		database.AddEmployee(id, e);
	}
	protected abstract PaymentClassification classification();
	protected abstract PaymentSchedule schedule();
}
