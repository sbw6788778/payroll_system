package Payroll;

public class ChangeDirectTransaction extends ChangeMethodTransaction {
	private String bank;
	private String account;
	public ChangeDirectTransaction(int id,String bank,String account,PayrollDatebase database){
		super(id,database);
		this.bank=bank;
		this.account=account;
	}
	protected PaymentMethod getMethod(){
		return new DirectMethod(bank,account);
	}

}
