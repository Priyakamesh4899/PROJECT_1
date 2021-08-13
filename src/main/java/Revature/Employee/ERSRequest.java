package Revature.Employee;


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="ERSRequest")
public class ERSRequest {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="rId")
	private int rId;
	private String eFname;
	private String eLname;
	private String startDate;
	private String endDate;
	private Date reqDate;
	private double amount;
	private String ReqType;
	private String status;
	private String Description;
	@ManyToOne
    @JoinColumn(name = "eid")
    private Employees emp;
	ERSRequest(){}
	public ERSRequest(String eFname, String eLname, String startDate, String endDate, Date reqDate, double amount,
			String reqType, String status, String description,Employees emp) {
		super();
		this.eFname = eFname;
		this.eLname = eLname;
		this.startDate = startDate;
		this.endDate = endDate;
		this.reqDate = reqDate;
		this.amount = amount;
		ReqType = reqType;
		this.status=status;
	    Description=description;
		this.emp = emp;
	}
	public String getDescription() {
		return Description;
	}
	public void setDescription(String description) {
		Description = description;
	}
	public int getrId() {
		return rId;
	}
	public void setrId(int rId) {
		this.rId = rId;
	}
	public String geteFname() {
		return eFname;
	}
	public void seteFname(String eFname) {
		this.eFname = eFname;
	}
	public String geteLname() {
		return eLname;
	}
	public void seteLname(String eLname) {
		this.eLname = eLname;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public Date getReqDate() {
		return reqDate;
	}
	public void setReqDate(Date reqDate) {
		this.reqDate = reqDate;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public String getReqType() {
		return ReqType;
	}
	public void setReqType(String reqType) {
		ReqType = reqType;
	}
	public Employees getEmp() {
		return emp;
	}
	public void setEmp(Employees emp) {
		this.emp = emp;
	}
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	@Override
	public String toString() {
		return "ERSRequest [rId=" + rId + ", eFname=" + eFname + ", eLname=" + eLname + ", startDate=" + startDate
				+ ", endDate=" + endDate + ", reqDate=" + reqDate + ", amount=" + amount + ", ReqType=" + ReqType
				+ ", status=" + status + ", Description=" + Description + ", emp=" + emp + "]";
	}
	
	
}
