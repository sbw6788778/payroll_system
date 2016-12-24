package servlet;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Hashtable;
import org.apache.commons.mail.EmailException;  
import org.apache.commons.mail.HtmlEmail;  
import com.sun.xml.internal.bind.v2.runtime.unmarshaller.InterningXmlVisitor;

import Payroll.*;

public class Payday {
	public static void execute(){
		Calendar c=Calendar.getInstance();
    	try{
    		PaydayTransaction pt= new PaydayTransaction(c.getTime(),ChooseFunction.database);
    		pt.execute();
    		Hashtable<Integer, Paycheck> paychecks= pt.getPaychecks();
    		if(paychecks==null)
    			return;
    		for(Integer id:paychecks.keySet()){
    			String email=ChooseFunction.database.getEmailById(id);
    			if(email==null)
    				continue;
    			sendEmail(paychecks.get(id),email);
    		}
    	}catch(Exception p){
    		p.printStackTrace();
    		System.out.println("初始化薪水支付时间失败");
    	}
	}
	public static void  sendEmail(Paycheck paycheck,String email){
		HtmlEmail e = new HtmlEmail();  
		String message=null;
        try {  
            // 这里是SMTP发送服务器的名字：163的如下："smtp.163.com"  
            e.setHostName("smtp.163.com");  
            // 字符编码集的设置  
            e.setCharset("utf-8");  
            // 收件人的邮箱  
            e.addTo(email);  
            // 发送人的邮箱  
            e.setFrom("jjhgkhkjgjh@163.com", "管理员");  
            // 如果需要认证信息的话，设置认证：用户名-密码。分别为发件人在邮件服务器上的注册名称和密码  
            e.setAuthentication("jjhgkhkjgjh@163.com", "1248576A");  
            // 要发送的邮件主题  
            e.setSubject("薪水账单");  
            // 要发送的信息，由于使用了HtmlEmail，可以在邮件内容中使用HTML标签  
            Date date= paycheck.getDateTime();
            SimpleDateFormat timeFormater = new SimpleDateFormat("yyyy-MM-dd");
            String time = timeFormater.format(date);
            message="日期:"+time+"总的薪水："+paycheck.getGrossPay()+"工会会费"+paycheck.getDeductions()+"净薪水"+paycheck.getNetPay();
            e.setMsg(message);  
            // 发送  
            e.send();   
        } catch (EmailException p) {  
            p.printStackTrace();  
            System.out.println("邮件发送失败");
        }  
	}
}
