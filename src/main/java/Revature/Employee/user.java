package Revature.Employee;

public class user {
private int uid;
private String pwd;
private String npwd;
user(){}
public user(int uid, String pwd,String npwd) {
	super();
	this.uid = uid;
	this.pwd = pwd;
	this.npwd = npwd;
}
public int getUid() {
	return uid;
}
public void setUid(int uid) {
	this.uid = uid;
}
public String getPwd() {
	return pwd;
}
public void setPwd(String pwd) {
	this.pwd = pwd;
}
public String getNpwd() {
	return npwd;
}
public void setNpwd(String npwd) {
	this.npwd = npwd;
}
@Override
public String toString() {
	return "user [uid=" + uid + ", pwd=" + pwd + ", npwd=" + npwd + "]";
}
}
