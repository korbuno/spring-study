package com.example.jpa.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class User {

	// DB에 테이블 생성 시, 낙타식 표기는 _로 표기됨.
	// ex) userPass -> USER_PASS
	// ex) userName -> USER_NAME
	// 주의) userID -> USERID

	// Id : PK, GV : AutoInc
	@Id
	@GeneratedValue
	private long userNo;

	// nullable = false : null값 금지
	// length = 20 : 길이 변경 (기본 255)
	@Column(nullable = false, length = 255, unique = true)
	private String userID;
	private String userPass;
	private String userName;
	private String email;

	@Override
	public String toString() {
		return "User [userNo=" + userNo + ", userID=" + userID + ", userPass=" + userPass + ", userName=" + userName
				+ ", email=" + email + "]";
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

	public void update(User updateUser) {
		this.userPass = updateUser.userPass;
		this.userName = updateUser.userName;
		this.email = updateUser.email;
	}

	public boolean matchPassword(String newPassword) {
		return this.userPass == newPassword ? true : false;
	}

	public boolean matchUserNo(long newUserNo) {
		return this.userNo == newUserNo ? true : false;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (userNo ^ (userNo >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (userNo != other.userNo)
			return false;
		return true;
	}

	
}
