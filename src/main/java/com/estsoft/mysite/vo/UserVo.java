package com.estsoft.mysite.vo;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

public class UserVo {

	private long no;
	
	@NotEmpty//(message="이름은 필수 항목입니다.")
	private String name;
	
	@NotEmpty//(message="이메일은 필수 항목입니다.")
	@Email//(message="이메일 형식이 맞지 않습니다.")
	private String email;
	private String password;
	private String gender;
	
	public long getNo() {
		return no;
	}
	public void setNo(long no) {
		this.no = no;
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
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	@Override
	public String toString() {
		return "UserVo [no=" + no + ", name=" + name + ", email=" + email + ", password=" + password + ", gender="
				+ gender + "]";
	}
	
}
