package Revature.Employee;


public class submit {
	private String sDate;
	private String eDate;
	private double at;
	private String rt;
	private String para;
	submit(){}
	public submit(String sDate, String eDate, double at, String rt, String para) {
		super();
		this.sDate = sDate;
		this.eDate = eDate;
		this.at = at;
		this.rt = rt;
		this.para = para;
	}
	public String getsDate() {
		return sDate;
	}
	public void setsDate(String sDate) {
		this.sDate = sDate;
	}
	public String geteDate() {
		return eDate;
	}
	public void seteDate(String eDate) {
		this.eDate = eDate;
	}
	public double getAt() {
		return at;
	}
	public void setAt(double at) {
		this.at = at;
	}
	public String getRt() {
		return rt;
	}
	public void setRt(String rt) {
		this.rt = rt;
	}
	public String getPara() {
		return para;
	}
	public void setPara(String para) {
		this.para = para;
	}
	@Override
	public String toString() {
		return "submit [sDate=" + sDate + ", eDate=" + eDate + ", at=" + at + ", rt=" + rt + ", para=" + para + "]";
	}
	
}
