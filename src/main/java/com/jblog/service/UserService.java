package com.jblog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jblog.dao.BlogDao;
import com.jblog.dao.CateDao;
import com.jblog.dao.UserDao;
import com.jblog.vo.BlogVo;
import com.jblog.vo.CateVo;
import com.jblog.vo.UserVo;

@Service
public class UserService {
	
	@Autowired
	private UserDao userDao;
	@Autowired
	private BlogDao blogDao;
	@Autowired
	private CateDao cateDao; 
	
	
	/*************************************** 아이디 중복 확인 ****************************************/
	public boolean idCheck(String id) {
		System.out.println("UserService > idCheck");

		if(userDao.idCheck(id) == false) {
			System.out.println("아이디 중복");
			
			return false;
			
		}else {
			System.out.println("아이디 생성 가능");
			
			return true;
		}
	}
	
	
	/****************************************** 회원가입 *******************************************/
	public void join(UserVo userVo) {
		System.out.println("UserService > join()");
		
		//회원가입
		userDao.userInsert(userVo);
		
		String id = userVo.getId();
		
		//블로그 정보 생성
		BlogVo blogVo = new BlogVo();
		blogVo.setId(id);
		blogVo.setBlogTitle(userVo.getUserName()+"의 블로그 입니다");
		blogVo.setLogoFile("basic");
		blogDao.blogCreate(blogVo);
		
		//카테고리 생성(미분류 - 제거 할 수 없는 기본 카테고리)
		CateVo cateVo = new CateVo();
		cateVo.setId(id);
		cateVo.setCateName("미분류");
		cateVo.setdesc("기본 카테고리입니다.");
		cateDao.cateInsert(cateVo);
	}
	
	
	/************************************* 로그인 정보 ***************************************/
	public UserVo login(UserVo userVo) {
		System.out.println("UserService > login()");
		
		UserVo loginUser = userDao.login(userVo);
		
		return loginUser;
	}
	
	
}
