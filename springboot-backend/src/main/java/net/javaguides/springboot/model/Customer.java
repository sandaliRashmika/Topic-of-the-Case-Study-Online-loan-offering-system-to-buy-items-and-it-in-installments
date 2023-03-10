package net.javaguides.springboot.model;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Customer")

public class Customer {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(name = "fullName")
	private String fullName;

	
	
	@Column(name = "birthday")
	private String birthday ;
	
	@Column(name = "loanbalance")
	private double loanbalance;
	
	@Column(name = "usedamount")
	private double usedamount ;
	
	@Column(name = "installment_plan")
	private String   installment_plan;
	
   public Customer() {
		
	}
	public Customer(String fullName, String birthday, double loanbalance, double usedamount, String installment_plan) {
		super();
		this.fullName = fullName;
		this.birthday = birthday;
		this.loanbalance = loanbalance;
		this.usedamount = usedamount;
		this.installment_plan = installment_plan;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public double getLoanbalance() {
		return loanbalance;
	}

	public void setLoanbalance(double loanbalance) {
		this.loanbalance = loanbalance;
	}

	public double getUsedamount() {
		return usedamount;
	}

	public void setUsedamount(double usedamount) {
		this.usedamount = usedamount;
	}

	public String getInstallment_plan() {
		return installment_plan;
	}

	public void setInstallment_plan(String installment_plan) {
		this.installment_plan = installment_plan;
	}
	
    

	
	
}
