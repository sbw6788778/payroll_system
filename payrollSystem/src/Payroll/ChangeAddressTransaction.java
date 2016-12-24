package Payroll;

public class ChangeAddressTransaction extends ChangeEmployeeTransaction {
	private String newAddress;
	public ChangeAddressTransaction(int id,String name,PayrollDatebase database){
		super(id,database);
		this.newAddress=name;
	}
	protected void change(Employee e){
		database.changeAddress(e.getId(),newAddress);
	}

}
