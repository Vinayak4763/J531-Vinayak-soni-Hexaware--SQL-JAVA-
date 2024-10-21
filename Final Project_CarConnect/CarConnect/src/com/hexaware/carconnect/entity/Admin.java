/*
 * Author: J531 Vinayak Soni

 * Date: 10/11/24
 * Description: Admin model
 */

package com.hexaware.carconnect.entity;

public class Admin {
	private int adminId;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String username;
    private String password;
    private String role;
    private String joinDate;
    
    public Admin() {
    	super();
    }
    
    
	public Admin(int adminId, String firstName, String lastName, String email, String phoneNumber, String username,
			String password, String role, String joinDate) {
		super();
		this.adminId = adminId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.username = username;
		this.password = password;
		this.role = role;
		this.joinDate = joinDate;
	}


	public int getAdminId() {
		return adminId;
	}


	public void setAdminId(int adminId) {
		this.adminId = adminId;
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


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getPhoneNumber() {
		return phoneNumber;
	}


	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
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


	public String getRole() {
		return role;
	}


	public void setRole(String role) {
		this.role = role;
	}


	public String getJoinDate() {
		return joinDate;
	}


	public void setJoinDate(String joinDate) {
		this.joinDate = joinDate;
	}


	@Override
	public String toString() {
		return String.format("Admin [firstName=%s, lastName=%s, email=%s]", firstName, lastName, email);
	}


	public boolean authenticate(String inputPassword) {
	    return inputPassword != null && inputPassword.equals(this.password);
	}
	
	


}
