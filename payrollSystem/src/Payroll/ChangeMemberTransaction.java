package Payroll;

public class ChangeMemberTransaction extends ChangeAffiliationTransaction {
	private int memId;
	private double dues;
	public ChangeMemberTransaction(int id,int memid,double dues,PayrollDatebase database){
		super(id,database);
		this.dues=dues;
		this.memId=memid;
	}
	protected Affiliation  affiliation(){
		return new UnionAffiliation(memId,dues,database);
	}
	protected void recordMembership(Employee e){
		database.addUnionMember(memId,dues,e);
	}
	
}
