package com.jblog.vo;

public class PostVo {
	
	private String id;
	private String userName;
	private int postNo;
	private int cateNo;
	private String postTitle;
	private String postContent;
	private String regDate;
	
	public PostVo() {}

	public PostVo(String id, String userName, int postNo, int cateNo, String postTitle, String postContent, String regDate) {
		this.id = id;
		this.userName = userName;
		this.postNo = postNo;
		this.cateNo = cateNo;
		this.postTitle = postTitle;
		this.postContent = postContent;
		this.regDate = regDate;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public int getPostNo() {
		return postNo;
	}

	public void setPostNo(int postNo) {
		this.postNo = postNo;
	}

	public int getCateNo() {
		return cateNo;
	}

	public void setCateNo(int cateNo) {
		this.cateNo = cateNo;
	}

	public String getPostTitle() {
		return postTitle;
	}

	public void setPostTitle(String postTitle) {
		this.postTitle = postTitle;
	}

	public String getPostContent() {
		return postContent;
	}

	public void setPostContent(String postContent) {
		this.postContent = postContent;
	}

	public String getRegDate() {
		return regDate;
	}

	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}

	@Override
	public String toString() {
		return "PostVo [id=" + id + ", userName=" + userName + ", postNo=" + postNo + ", cateNo=" + cateNo
				+ ", postTitle=" + postTitle + ", postContent=" + postContent + ", regDate=" + regDate + "]";
	}
	
}
