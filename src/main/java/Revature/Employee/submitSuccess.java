package Revature.Employee;
public class submitSuccess {
private int sts;
public submitSuccess(){}
public submitSuccess(int sts) {
	super();
	this.sts = sts;
}
public int getSts() {
	return sts;
}
public void setSts(int sts) {
	this.sts = sts;
}
@Override
public String toString() {
	return "submitSuccess [sts=" + sts + "]";
}
}
