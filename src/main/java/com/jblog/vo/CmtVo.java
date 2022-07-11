package com.jblog.vo;

public class CmtVo {
	
	private int cmtNo;
	private int postNo;
	private String id;
	private int userNo;
	private String userName;
	private String cmtContent;
	private String regDate;
	
	public CmtVo() {}

	public CmtVo(int cmtNo, int postNo, int userNo, String cmtContent, String regDate) {
		this.cmtNo = cmtNo;
		this.postNo = postNo;
		this.userNo = userNo;
		this.cmtContent = cmtContent;
		this.regDate = regDate;
	}
	
	public CmtVo(int cmtNo, int postNo, String id, int userNo, String userName, String cmtContent, String regDate) {
		super();
		this.cmtNo = cmtNo;
		this.postNo = postNo;
		this.id = id;
		this.userNo = userNo;
		this.userName = userName;
		this.cmtContent = cmtContent;
		this.regDate = regDate;
	}

	public int getCmtNo() {
		return cmtNo;
	}

	public void setCmtNo(int cmtNo) {
		this.cmtNo = cmtNo;
	}

	public int getPostNo() {
		return postNo;
	}

	public void setPostNo(int postNo) {
		this.postNo = postNo;
	}
	
	public String id() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getUserNo() {
		return userNo;
	}

	public void setUserNo(int userNo) {
		this.userNo = userNo;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getCmtContent() {
		return cmtContent;
	}

	public void setCmtContent(String cmtContent) {
		this.cmtContent = cmtContent;
	}

	public String getRegDate() {
		return regDate;
	}

	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}

	@Override
	public String toString() {
		return "CmtVo [cmtNo=" + cmtNo + ", postNo=" + postNo + ", id=" + id + ", userNo=" + userNo + ", userName="
				+ userName + ", cmtContent=" + cmtContent + ", regDate=" + regDate + "]";
	}

}
