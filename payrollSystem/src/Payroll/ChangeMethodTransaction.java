package Payroll;

public abstract class ChangeMethodTransaction extends ChangeEmployeeTransaction {
	public ChangeMethodTransaction(int id,PayrollDatebase database){
		super(id,database);
	}
	public void change(Employee e){
		e.setMethod(getMethod());
		database.changeMethod(e);
	}
	//Ҫ���ʲô����û���밡
	protected abstract PaymentMethod getMethod();
}
