package Model;

import java.util.Scanner;

public abstract class User {
	
	private int ID;
	private String firstName;
	private String lastName;
	private String email;
	private String phoneNumber;
	private String password;
	
	
	//AccountType: 
	//0->Client 
	//1->Admin 
	//2-> DeletedClientAaccount 
	//3-> DeletedAdminAccount
	
	public abstract void showList(Database database , Scanner scan);
	
	public User() {}

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
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

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
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
	

}
