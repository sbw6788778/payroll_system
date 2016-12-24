package Payroll;

public abstract class ChangeAffiliationTransaction extends ChangeEmployeeTransaction {
	public ChangeAffiliationTransaction(int id,PayrollDatebase database){
		super(id,database);
	}
	protected void change(Employee e){
		recordMembership(e);
		e.setAffiliation(affiliation());
	}
	protected abstract Affiliation  affiliation();
	protected abstract void recordMembership(Employee e);
}
