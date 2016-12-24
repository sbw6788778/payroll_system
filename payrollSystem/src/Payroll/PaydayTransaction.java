package Payroll;
import java.util.Date;
import java.util.Hashtable;
import java.util.Set;

public class PaydayTransaction extends Transaction {
	private Date payDate;
	private Hashtable<Integer, Paycheck> paychecks = new Hashtable<Integer, Paycheck>();
	public PaydayTransaction(Date paydate,PayrollDatebase database){
		super(database);
		this.payDate=paydate;
		
	}
	public Paycheck getPaycheck(int id){
		return paychecks.get(id);
	}
	public Hashtable<Integer, Paycheck> getPaychecks(){
		return this.paychecks;
	}
	@Override
	public void execute() {
		Set<Integer> empIds=database.GetAllEmployeeIds();
		for(int empid : empIds){
			Employee employee=database.GetEmployee(empid);
			if(employee.isPayDate(payDate)){
				System.out.println(empid);
				Date startDate=employee.getPayPeriodStartDate(payDate);
				Paycheck pc=new Paycheck(payDate,startDate);
				paychecks.put(empid, pc);
				employee.payday(pc);
			}
		}

	}

}
