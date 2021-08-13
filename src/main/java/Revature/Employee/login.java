package Revature.Employee;

public class login {
private String value;
private int id;
private String des;
public login(){}
public login(String value, int id, String des) {
	super();
	this.value = value;
	this.id = id;
	this.des = des;
}
public String getValue() {
	return value;
}
public void setValue(String value) {
	this.value = value;
}
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public String getDes() {
	return des;
}
public void setDes(String des) {
	this.des = des;
}
@Override
public String toString() {
	return "login [value=" + value + ", id=" + id + ", des=" + des + "]";
}

}
