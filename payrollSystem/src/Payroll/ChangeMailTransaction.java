package Payroll;

public class ChangeMailTransaction extends ChangeMethodTransaction {
	private String address;
	public  ChangeMailTransaction(int id ,String address,PayrollDatebase database){
		super(id,database);
		this.address=address;
	}
	protected PaymentMethod getMethod(){
		return new MailMethod(address);
	}
}	
