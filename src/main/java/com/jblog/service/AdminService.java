package com.jblog.service;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.jblog.dao.BlogDao;
import com.jblog.dao.CateDao;
import com.jblog.dao.PostDao;
import com.jblog.vo.BlogVo;
import com.jblog.vo.CateVo;
import com.jblog.vo.PostVo;
import com.jblog.vo.UserVo;

@Service
public class AdminService {
	
	@Autowired
	private BlogDao blogDao;
	@Autowired
	private CateDao cateDao;
	@Autowired
	private PostDao postDao;
	
	
	/**************************************** 블로그 설정 *****************************************/
	public BlogVo adminBasic(String id) {
		System.out.println("AdminService > adminBasic()");
		
		BlogVo blogVo = blogDao.blogBasic(id);
		
		return blogVo;
	}
	
	
	/************************************* 블로그 기본설정변경 **************************************/
	public void basicUpdate(UserVo authUser, BlogVo blogVo) {
		System.out.println("AdminService > basicUpdate()");
		
		blogVo.setId(authUser.getId());
		
		MultipartFile file = blogVo.getFile();

		if(!file.isEmpty()) {
			
			//save directory 
			String saveDir = "C:\\javaStudy\\upload";
			
			//original file name
			String orgName = file.getOriginalFilename();
			
			//extension
			String exName = orgName.substring(orgName.lastIndexOf("."));
			
			//save file name
			String saveName = System.currentTimeMillis()+UUID.randomUUID().toString()+exName;
			
			//file path
			String filePath = saveDir + "\\" + saveName;
			
			blogVo.setLogoFile(saveName);

			try {
				byte[] fileData = file.getBytes();
				OutputStream os = new FileOutputStream(filePath);
				BufferedOutputStream bos = new BufferedOutputStream(os);
				
				bos.write(fileData);
				bos.close();
				
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		blogDao.basicUpdate(blogVo);
		
	}
	
	
	/************************************* 블로그 카테고리 설정 **************************************/
	public BlogVo adminCate(String id) {
		System.out.println("AdminService > adminCate()");
		
		BlogVo blogVo = blogDao.blogTitle(id);
		
		return blogVo;
	}
	
	
	/************************************* 블로그 카테고리 정보 **************************************/
	public List<CateVo> cateList(UserVo authUser){
		System.out.println("AdminService > cateList()");
		
		String id = authUser.getId();
		List<CateVo> cateList = cateDao.cateList(id);
		
		return cateList;
	}
	
	
	/************************************* 블로그 카테고리 추가 **************************************/
	public CateVo cateInsert(UserVo authUser, CateVo cateVo){
		System.out.println("AdminService > cateInsert()");
		
		cateVo.setId(authUser.getId());
		cateDao.cateInsert(cateVo);
		
		int cateNo = cateVo.getCateNo();
		CateVo cateVo1 = cateDao.selectOne(cateNo);
		cateVo1.setCountPost(0);
		
		return cateVo1;
	}
	
	
	/************************************* 블로그 카테고리 삭제 **************************************/
	public String cateDelete(UserVo authUser, int cateNo){
		System.out.println("AdminService > cateDelete()");
		
		CateVo cateVo = new CateVo();
		cateVo.setId(authUser.getId());
		cateVo.setCateNo(cateNo);
		
		String state;
		int count = cateDao.cateDelete(cateVo);
		
		if(count == 1) {
			state = "success";
			
		}else {
			state = "fail";
			
		}
		
		return state;
	}
	
	
	/*************************************** 블로그 글 작성폼 ***************************************/
	public Map<String, Object> writeForm(String id) {
		System.out.println("AdminService > writeForm()");
		
		BlogVo blogVo = blogDao.blogTitle(id);
		List<CateVo> cateIndex = cateDao.cateIndex(id);
		
		Map<String, Object> map = new HashMap<>();
		map.put("blogVo", blogVo);
		map.put("cateIndex", cateIndex);
		
		return map;
	}
	
	
	/**************************************** 블로그 글 작성 ****************************************/
	public void postInsert(PostVo postVo) {
		System.out.println("AdminService > postInsert()");
		
		postDao.postInsert(postVo);
	}

}
