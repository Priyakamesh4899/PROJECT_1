package Revature.Employee;

public class update {
	private String lname;
	private String email;
    private String door;
	private String st;
	private String dst;
	private long pin;
	private long phn;
	update(){}
	public update(String lname, String email, String door, String st, String dst, long pin, long phn) {
		super();
		this.lname = lname;
		this.email = email;
		this.door = door;
		this.st = st;
		this.dst = dst;
		this.pin = pin;
		this.phn = phn;
	}
	public String getLname() {
		return lname;
	}
	public void setLname(String lname) {
		this.lname = lname;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getDoor() {
		return door;
	}
	public void setDoor(String door) {
		this.door = door;
	}
	public String getSt() {
		return st;
	}
	public void setSt(String st) {
		this.st = st;
	}
	public String getDst() {
		return dst;
	}
	public void setDst(String dst) {
		this.dst = dst;
	}
	public long getPin() {
		return pin;
	}
	public void setPin(long pin) {
		this.pin = pin;
	}
	public long getPhn() {
		return phn;
	}
	public void setPhn(long phn) {
		this.phn = phn;
	}
	@Override
	public String toString() {
		return "update [lname=" + lname + ", email=" + email + ", door=" + door + ", st=" + st + ", dst=" + dst
				+ ", pin=" + pin + ", phn=" + phn + "]";
	}
	
}
