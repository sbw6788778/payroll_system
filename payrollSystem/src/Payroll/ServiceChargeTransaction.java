package Payroll;

import java.io.InvalidObjectException;
import java.util.Date;

public class ServiceChargeTransaction extends Transaction{
	private Date date;
	private double amount;
	private int memberID;
	public ServiceChargeTransaction(int id,double amount,Date date,PayrollDatebase database){
		super(database);
		this.memberID=id;
		this.amount=amount;
		this.date=date;
	}
	public void execute()throws Exception{
		Employee e=database.GetUnionMember(memberID);
		
		if(e!=null){
			UnionAffiliation  ua=null;
			if(e.getAffiliation() instanceof UnionAffiliation)
				ua=(UnionAffiliation)e.getAffiliation();
			if(ua!=null){
				ua.addServiceCharge(date,new ServiceCharge(date,amount));
				database.addServiceCharge(memberID,new ServiceCharge(date,amount));
			}
				
			else
				throw new Exception("没有这个员工");
		}
		else
			throw new Exception("没这个工会员工");
	}
	
}
