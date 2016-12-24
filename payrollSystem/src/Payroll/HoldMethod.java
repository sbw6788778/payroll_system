package Payroll;

public class HoldMethod implements PaymentMethod {
	private Paycheck paycheck;
	public void pay(Paycheck c){
		this.paycheck=c;
	}
	public String toString(){
		return "支票保存在出纳人员";
	}
}
