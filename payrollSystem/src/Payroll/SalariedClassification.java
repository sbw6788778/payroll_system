package Payroll;
public class SalariedClassification implements PaymentClassification {
	private double monthlyPay;
	public SalariedClassification(double salary){
		this.monthlyPay=salary;
	}
	public double getSalary(){
		return this.monthlyPay;
	}
	@Override
	public double calculatePay(Paycheck paycheck) {
		return monthlyPay;

	}
	public String toString(){
		return "领月薪员工";
	}

}
