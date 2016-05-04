package com.estsoft.mysite.vo;

public class BoardVo {
	private long no;
	private long userNo;
	private String userName;
	private String title;
	private String text;
	private String regDate;
	private long hit;
	private long groupNo;
	private long orderNo;
	private long depth;
	public long getNo() {
		return no;
	}
	public void setNo(long no) {
		this.no = no;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getRegDate() {
		return regDate;
	}
	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}
	public long getHit() {
		return hit;
	}
	public void setHit(long hit) {
		this.hit = hit;
	}
	public long getGroupNo() {
		return groupNo;
	}
	public void setGroupNo(long groupNo) {
		this.groupNo = groupNo;
	}
	public long getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(long orderNo) {
		this.orderNo = orderNo;
	}
	public long getDepth() {
		return depth;
	}
	public void setDepth(long depth) {
		this.depth = depth;
	}
	@Override
	public String toString() {
		return "BoardVo [no=" + no + ", userNo=" + userNo + ", userName=" + userName + ", title=" + title + ", text="
				+ text + ", regDate=" + regDate + ", hit=" + hit + ", groupNo=" + groupNo + ", orderNo=" + orderNo
				+ ", depth=" + depth + "]";
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public void setUserNo(long userNo) {
		this.userNo = userNo;
	}
	public long getUserNo(){
		return userNo;
	}
	
}
