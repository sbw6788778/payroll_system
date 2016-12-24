package Payroll;

public class MailMethod implements PaymentMethod {
	private String address;
	private Paycheck paycheck;
	public MailMethod (String a){
		this.address=a;
	}
	public String getAddress(){
		return this.address;
	}
	public void pay(Paycheck c){
		this.paycheck=c;
	}
	public String toString(){
		return "Ö§Æ±ÓÊ¼Ä";
	}
}
