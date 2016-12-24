package Payroll;

public abstract class  Transaction {
	protected PayrollDatebase database;
	public Transaction(PayrollDatebase database){
		this.database=database;
	}
	//要么申明异常要么处理异常
	public abstract void execute()throws Exception;

}
