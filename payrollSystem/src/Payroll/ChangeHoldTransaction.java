package Payroll;

public class ChangeHoldTransaction extends ChangeMethodTransaction {
	public ChangeHoldTransaction(int id,PayrollDatebase database){
		super(id,database);
	}
	protected PaymentMethod getMethod(){
		return new HoldMethod();
	}
}
