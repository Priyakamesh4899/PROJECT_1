package Revature.Employee;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="employee")
public class Employees {
@Id
@GeneratedValue(strategy = GenerationType.AUTO)
@Column(name="eid")
private int id;
@Column(name="efname")
private String firstName;
@Column(name="elname")
private String lastName;
private Date dob; 
private int age;
private String adhaarno;
private String emailid;
public Address address;
private long contactno;
private String designation;
private String password;
Employees(){}
public Employees(String firstName, String lastName, Date dob, int age, String adhaarno, String emailid, Address address,
		long contactno, String designation, String password) {
	super();
	this.firstName = firstName;
	this.lastName = lastName;
	this.dob = dob;
	this.age = age;
	this.adhaarno = adhaarno;
	this.emailid = emailid;
	this.address = address;
	this.contactno = contactno;
	this.designation = designation;
	this.password = password;
}
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public String getFirstName() {
	return firstName;
}
public void setFirstName(String firstName) {
	this.firstName = firstName;
}
public String getLastName() {
	return lastName;
}
public void setLastName(String lastName) {
	this.lastName = lastName;
}
public Date getDob() {
	return dob;
}
public void setDob(Date dob) {
	this.dob = dob;
}
public int getAge() {
	return age;
}
public void setAge(int age) {
	this.age = age;
}
public String getAdhaarno() {
	return adhaarno;
}
public void setAdhaarno(String adhaarno) {
	this.adhaarno = adhaarno;
}
public String getEmailid() {
	return emailid;
}
public void setEmailid(String emailid) {
	this.emailid = emailid;
}
public Address getAddress() {
	return address;
}
public void setAddress(Address address) {
	this.address = address;
}
public long getContactno() {
	return contactno;
}
public void setContactno(long contactno) {
	this.contactno = contactno;
}
public String getDesignation() {
	return designation;
}
public void setDesignation(String designation) {
	this.designation = designation;
}
public String getPassword() {
	return password;
}
public void setPassword(String password) {
	this.password = password;
}
@Override
public String toString() {
	return "Employee [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", dob=" + dob + ", age="
			+ age + ", adhaarno=" + adhaarno + ", emailid=" + emailid + ", address=" + address + ", contactno="
			+ contactno + ", designation=" + designation + ", password=" + password + "]";
}

}
