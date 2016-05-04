package com.estsoft.mysite.vo;

public class GuestBookVo {
	private long no;
	private String name;
	private String regDate;
	private String message;
	private String passwd;
	
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
	public String getRegDate() {
		return regDate;
	}
	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getPasswd() {
		return passwd;
	}
	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}	
	@Override
	public String toString() {
		return "GuestBookVo [no=" + no + ", name=" + name + ", regDate=" + regDate + ", message=" + message
				 + "]";
	}
	
	
	
}
