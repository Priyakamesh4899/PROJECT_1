package Revature.Employee;

import java.util.Date;


public class Pending {
	private int rId;
	private String eFname;
	private String eLname;
	private String startDate;
	private String endDate;
	private Date reqDate;
	private double amount;
	private String reqType;
	private String status;
	private String description;
    private int eid;
	Pending(){}
	public Pending(int rId,String eFname, String eLname, String startDate, String endDate, Date reqDate, double amount,
			String reqType, String status, String description,int eid) {
		super();
		this.rId=rId;
		this.eFname = eFname;
		this.eLname = eLname;
		this.startDate = startDate;
		this.endDate = endDate;
		this.reqDate = reqDate;
		this.amount = amount;
		this.reqType = reqType;
		this.status=status;
	    this.description=description;
		this.eid = eid;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
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
		return reqType;
	}
	public void setReqType(String reqType) {
		this.reqType = reqType;
	}
	public int getEId() {
		return eid;
	}
	public void setEId(int eid) {
		this.eid = eid;
	}
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	@Override
	public String toString() {
		return "Pending [rId=" + rId + ", eFname=" + eFname + ", eLname=" + eLname + ", startDate=" + startDate
				+ ", endDate=" + endDate + ", reqDate=" + reqDate + ", amount=" + amount + ", ReqType=" + reqType
				+ ", status=" + status + ", Description=" + description + ", eid=" + eid + "]";
	}
	
}
