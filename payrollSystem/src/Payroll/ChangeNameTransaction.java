package Payroll;

public class ChangeNameTransaction extends ChangeEmployeeTransaction {
	private String newName;
	public ChangeNameTransaction(int id,String name,PayrollDatebase database){
		super(id,database);
		this.newName=name;
	}
	protected void change(Employee e){
		database.changeName(e.getId(),newName);
	}

}
