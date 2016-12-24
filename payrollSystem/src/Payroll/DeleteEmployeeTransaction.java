package Payroll;

public class DeleteEmployeeTransaction extends Transaction{
	private int id ;
	public DeleteEmployeeTransaction (int id,PayrollDatebase database){
		super(database);
		this.id=id;
	}
	public void execute(){
		database.DeleteEmployee(id);
	}
}
