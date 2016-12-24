package Payroll;

import java.io.InvalidObjectException;

public abstract class ChangeEmployeeTransaction extends Transaction {
	protected int id;
	public ChangeEmployeeTransaction(int id,PayrollDatebase database){
		super(database);
		this.id=id;
	}
	@Override
	public void execute() throws Exception{
		Employee e=database.GetEmployee(id);
		if(e!=null) change(e);
		else
			throw new Exception("û�������Ա��");

	}
	protected abstract void change(Employee e);

}
