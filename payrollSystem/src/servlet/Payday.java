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
    		System.out.println("��ʼ��нˮ֧��ʱ��ʧ��");
    	}
	}
	public static void  sendEmail(Paycheck paycheck,String email){
		HtmlEmail e = new HtmlEmail();  
		String message=null;
        try {  
            // ������SMTP���ͷ����������֣�163�����£�"smtp.163.com"  
            e.setHostName("smtp.163.com");  
            // �ַ����뼯������  
            e.setCharset("utf-8");  
            // �ռ��˵�����  
            e.addTo(email);  
            // �����˵�����  
            e.setFrom("jjhgkhkjgjh@163.com", "����Ա");  
            // �����Ҫ��֤��Ϣ�Ļ���������֤���û���-���롣�ֱ�Ϊ���������ʼ��������ϵ�ע�����ƺ�����  
            e.setAuthentication("jjhgkhkjgjh@163.com", "1248576A");  
            // Ҫ���͵��ʼ�����  
            e.setSubject("нˮ�˵�");  
            // Ҫ���͵���Ϣ������ʹ����HtmlEmail���������ʼ�������ʹ��HTML��ǩ  
            Date date= paycheck.getDateTime();
            SimpleDateFormat timeFormater = new SimpleDateFormat("yyyy-MM-dd");
            String time = timeFormater.format(date);
            message="����:"+time+"�ܵ�нˮ��"+paycheck.getGrossPay()+"������"+paycheck.getDeductions()+"��нˮ"+paycheck.getNetPay();
            e.setMsg(message);  
            // ����  
            e.send();   
        } catch (EmailException p) {  
            p.printStackTrace();  
            System.out.println("�ʼ�����ʧ��");
        }  
	}
}
