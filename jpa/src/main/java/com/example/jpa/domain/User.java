package com.example.jpa.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class User {

	// Id : PK, GV : AutoInc
	@Id @GeneratedValue
	private long userNo;
	
	//nullable = false : null값 금지
	//length = 20 : 길이 변경 (기본 255)
	@Column(nullable=false, length=20)
	private String userID;
	private String userPass;
	private String userName;
	private String email;

	@Override
	public String toString() {
		return "User [userID=" + userID + ", userPass=" + userPass + ", userName=" + userName + ", email=" + email
				+ "]";
	}

	public long getUserNo() {
		return userNo;
	}

	public void setUserNo(long userNo) {
		this.userNo = userNo;
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
