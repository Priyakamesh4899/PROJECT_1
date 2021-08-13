package Revature.Employee;

import javax.persistence.Embeddable;

@Embeddable
public class Address {
	private String doornumber;
	private String street;
	private String district;
	private long pincode;
	Address(){}
	public Address(String doornumber, String street, String district, long pincode) {
		super();
		this.doornumber = doornumber;
		this.street = street;
		this.district = district;
		this.pincode = pincode;
	}
	public String getDoornumber() {
		return doornumber;
	}
	public void setDoornumber(String doornumber) {
		this.doornumber = doornumber;
	}
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public String getDistrict() {
		return district;
	}
	public void setDistrict(String district) {
		this.district = district;
	}
	public long getPincode() {
		return pincode;
	}
	public void setPincode(long pincode) {
		this.pincode = pincode;
	}
	@Override
	public String toString() {
		return "Address [doornumber=" + doornumber + ", street=" + street + ", district=" + district + ", pincode="
				+ pincode + "]";
	}
	
}
