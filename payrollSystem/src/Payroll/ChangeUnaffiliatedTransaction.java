package Payroll;

public class ChangeUnaffiliatedTransaction extends ChangeAffiliationTransaction {
	public ChangeUnaffiliatedTransaction(int id,PayrollDatebase database){ super(id,database);}
	protected Affiliation  affiliation(){
		return new NoAffiliation(database);
	}
	protected void recordMembership(Employee e){
		UnionAffiliation unionAffiliation=(UnionAffiliation)e.getAffiliation();
		int memId=unionAffiliation.getMemId();
		database.removeUnionMember(memId);
	}
}
