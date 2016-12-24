package Payroll;

import java.util.Set;

public interface PayrollDatebase {
	void AddEmployee(int id ,Employee employee);
	 Employee GetEmployee(int id);
	 void DeleteEmployee(int id);
	 Employee GetUnionMember(int id);
	 void addUnionMember( int memId,double dues,Employee e);
	 void removeUnionMember(int memId);
	 Set<Integer> GetAllEmployeeIds();
	 void addTimeCard(int id,TimeCard t);
	 void addSalesReceipt(int id,SalesReceipt sr);
	 void changeAddress(int id ,String address);
	 void changeName(int id,String newName);
	 void changeMethod(Employee e);
	 public void addServiceCharge(int memId,ServiceCharge sc);
}
