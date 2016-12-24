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
    //用来把员工添加到数据库
    public void execute(){
    	PaymentClassification pc=makeClassification();
    	PaymentSchedule ps=makeSchedule();
    	//默认是出纳人员保持支票
    	HoldMethod pm =new HoldMethod();
    	Employee e=new Employee(empid,name,address,database);
    	e.setClassification(pc);
    	e.setSchedule(ps);
    	e.setMethod(pm);
    	database.AddEmployee(empid, e);
    } 

}
