package Payroll;
import java.util.Date;
public class TimeCardTransaction extends Transaction {
	private Date date;
	private double hours;
	private int id;
	public TimeCardTransaction(Date date,double hours,int id,PayrollDatebase database){
		super(database);
		this.date=date;
		this.hours=hours;
		this.id=id;
	}
	public void execute() throws Exception{
		Employee e=database.GetEmployee(this.id);
		if(e!=null){
			HourlyClassification hc=(HourlyClassification)e.getClassification();
			if(hc!=null){
				database.addTimeCard(id,new TimeCard(date,hours));
			}
				
			else
				throw new Exception("没有这个钟点工");
		}
		else
			throw new Exception("没这个员工");
	}
}
