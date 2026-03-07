package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Customer_Information")
public class CustomerInformation {

	@Id
	@Column(name="Customer_Id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(name="Customer_Name", nullable=false)
	private String name;
	@Column(name="Customer_Email", nullable=false)
	private String email;
	@Column(name="Customer_Password", nullable=false)
	private String password;
	@Column(name="Customer_MobileNumber", nullable=false)
	private long mobileNumber;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public long getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(long mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	public CustomerInformation() {
		super();
	}
	public CustomerInformation(int id, String name, String email, String password, long mobileNumber) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.password = password;
		this.mobileNumber = mobileNumber;
	}
	@Override
	public String toString() {
		return "CustomerInformation [id=" + id + ", name=" + name + ", email=" + email + ", password=" + password
				+ ", mobileNumber=" + mobileNumber + "]";
	}
	
}
