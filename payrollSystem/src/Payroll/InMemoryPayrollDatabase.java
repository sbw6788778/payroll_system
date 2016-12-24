package Payroll;

import java.util.HashMap;
import java.util.Set;

public class InMemoryPayrollDatabase implements PayrollDatebase {
	private static HashMap<Integer,Employee> employees=new HashMap<Integer,Employee>(); 
	private static HashMap<Integer,Employee>  unionMember=new HashMap<Integer,Employee>();
	
	public  void AddEmployee(int id ,Employee employee){
		employees.put(id, employee);
	}
	public  Employee GetEmployee(int id){
		return employees.get(id);
	}
	public  void DeleteEmployee(int id){
		employees.remove(id);
	}
	public  Employee GetUnionMember(int id)
	{
		return unionMember.get(id);
	}
	public  void addUnionMember( int memId,double dues,Employee e){
		unionMember.put(memId, e);
	}
	public  void removeUnionMember(int memId){
		unionMember.remove(memId);
	}
	public  Set<Integer> GetAllEmployeeIds(){
		return employees.keySet();
	}
	public void addTimeCard(int id,TimeCard t){
		
	}
	public void addSalesReceipt(int id,SalesReceipt sr){
		
	}
	public void changeAddress(int id ,String address){}
	public void changeName(int id,String newName){}
	public void changeMethod(Employee e){}
	public void addServiceCharge(int memId,ServiceCharge sc){}
}
