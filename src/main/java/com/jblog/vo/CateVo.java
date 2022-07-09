package com.jblog.vo;

public class CateVo {
	
	private int cateNo;
	private String id;
	private String cateName;
	private String desc;
	private String regDate;
	private int countPost;
	
	public CateVo() {}

	public CateVo(int cateNo, String id, String cateName, String desc, String regDate) {
		this.cateNo = cateNo;
		this.id = id;
		this.cateName = cateName;
		this.desc = desc;
		this.regDate = regDate;
	}
	
	public CateVo(int cateNo, String id, String cateName, String desc, String regDate, int countPost) {
		super();
		this.cateNo = cateNo;
		this.id = id;
		this.cateName = cateName;
		this.desc = desc;
		this.regDate = regDate;
		this.countPost = countPost;
	}

	public int getCateNo() {
		return cateNo;
	}

	public void setCateNo(int cateNo) {
		this.cateNo = cateNo;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCateName() {
		return cateName;
	}

	public void setCateName(String cateName) {
		this.cateName = cateName;
	}

	public String getdesc() {
		return desc;
	}

	public void setdesc(String desc) {
		this.desc = desc;
	}

	public String getRegDate() {
		return regDate;
	}

	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}

	public int getCountPost() {
		return countPost;
	}

	public void setCountPost(int countPost) {
		this.countPost = countPost;
	}
	
	//countPost 제외
	@Override
	public String toString() {
		return "CateVo [cateNo=" + cateNo + ", id=" + id + ", cateName=" + cateName + ", desc=" + desc
				+ ", regDate=" + regDate + "]";
	}
	
}
