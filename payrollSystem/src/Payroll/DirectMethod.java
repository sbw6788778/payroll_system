package Payroll;

public class DirectMethod implements PaymentMethod {
	private String bank;
	private String account;
	private Paycheck paycheck;
	public DirectMethod(String bank,String account){
		this.bank=bank;this.account=account;
	}
	public String getBank(){
		return this.bank;
	}
	public String getAccount(){
		return this.account;
	}
	public void pay(Paycheck c){
		this.paycheck=c;
	}
	public String toString(){
		return "支票打入银行账户";
	}
}
