package Payroll;

public abstract class  Transaction {
	protected PayrollDatebase database;
	public Transaction(PayrollDatebase database){
		this.database=database;
	}
	//Ҫô�����쳣Ҫô�����쳣
	public abstract void execute()throws Exception;

}
