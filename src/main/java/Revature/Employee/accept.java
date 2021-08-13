package Revature.Employee;

public class accept {
private int r;
private int e;
accept(){}
public accept(int r, int e) {
	super();
	this.r = r;
	this.e = e;
}
public int getR() {
	return r;
}
public void setR(int r) {
	this.r = r;
}
public int getE() {
	return e;
}
public void setE(int e) {
	this.e = e;
}
@Override
public String toString() {
	return "accept [r=" + r + ", e=" + e + "]";
}

}
