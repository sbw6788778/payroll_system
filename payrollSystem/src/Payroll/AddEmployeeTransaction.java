package Payroll;

public abstract class AddEmployeeTransaction extends Transaction {
	private int empid;
	private String name;
	private String address;
	public AddEmployeeTransaction( int id ,String n,String a,PayrollDatebase database){
		super(database);
		this.empid = id;
		this.name=n;
		this.address=a;
	}
    protected abstract PaymentClassification makeClassification();
    protected abstract PaymentSchedule makeSchedule();
    //������Ա����ӵ����ݿ�
    public void execute(){
    	PaymentClassification pc=makeClassification();
    	PaymentSchedule ps=makeSchedule();
    	//Ĭ���ǳ�����Ա����֧Ʊ
    	HoldMethod pm =new HoldMethod();
    	Employee e=new Employee(empid,name,address,database);
    	e.setClassification(pc);
    	e.setSchedule(ps);
    	e.setMethod(pm);
    	database.AddEmployee(empid, e);
    } 

}
