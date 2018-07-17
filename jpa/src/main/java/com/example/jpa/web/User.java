package com.example.jpa.web;

public class User {

	private String userID;
	private String userPass;
	private String userName;
	private String email;
	
	
	
	@Override
	public String toString() {
		return "User [userID=" + userID + ", userPass=" + userPass + ", userName=" + userName + ", email=" + email
				+ "]";
	}
	public String getUserID() {
		return userID;
	}
	public void setUserID(String userID) {
		this.userID = userID;
	}
	public String getUserPass() {
		return userPass;
	}
	public void setUserPass(String userPass) {
		this.userPass = userPass;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

	
	

}
