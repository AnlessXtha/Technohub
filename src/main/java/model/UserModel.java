package model;

import java.io.File;
import java.io.Serializable;
import java.time.LocalDate;

import javax.servlet.http.Part;

import util.StringUtils;

public class UserModel implements Serializable {
	private static final long serialVersionUID = 1L;
	
	
	private String firstName;
	private String lastName;
	private String userType;
	private String address;
	private String contactNumber;
	private String email;
	private String username;
	private String password;
	
	public UserModel() {
		
	}
	
	public UserModel(String firstName, String lastName, String userType, String address, String contactNumber,
			String email, String username, String password) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.userType = userType;
		this.address = address;
		this.contactNumber = contactNumber;
		this.email = email;
		this.username = username;
		this.password = password;
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

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
