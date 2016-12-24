package Payroll;

public class NoAffiliation extends Affiliation {
	public NoAffiliation (PayrollDatebase database){
		super(database);
	}
	public double calculateDeductions(Paycheck c){
		return 0;
	}
	public String toString(){
		return "非工会成员";
	}

}
