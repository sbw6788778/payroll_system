package Payroll;
import java.util.Calendar;
import java.util.Date;
public class WeeklySchedule implements PaymentSchedule {
	public  boolean isPayDate(Date date) {
		Calendar ca=Calendar.getInstance();
		ca.setTime(date);
		return ca.get(Calendar.DAY_OF_WEEK)==Calendar.FRIDAY;
	}
	public Date getPayPeriodStartDate(Date date){
		Calendar ca = Calendar.getInstance();
		ca.setTime(date);
		ca.add(Calendar.DATE, -6);
		return ca.getTime();
	}
	public String toString(){
		return "÷‹÷ß∏∂";
	}
}
