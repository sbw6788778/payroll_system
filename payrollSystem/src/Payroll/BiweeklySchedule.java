package Payroll;
import java.util.Calendar;
import java.util.Date;
//双周结算
public class BiweeklySchedule implements PaymentSchedule {
	public boolean isPayDate(Date date) {
		Calendar ca=Calendar.getInstance();
		ca.setTime(date);
		return (ca.get(Calendar.DAY_OF_WEEK)==Calendar.FRIDAY)&&(ca.get(Calendar.DATE)%2==0);
	}
	public Date getPayPeriodStartDate(Date date){
		Calendar ca = Calendar.getInstance();
		ca.setTime(date);
		ca.add(Calendar.DATE, -13);
		return ca.getTime();
	}
	public String toString (){
		return "两周支付";
	}
}
