package com.jblog.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jblog.vo.UserVo;

@Repository
public class UserDao {

	@Autowired
	private SqlSession sqlSession;
	
	
	/**************************** 아이디 중복 확인 ****************************/
	public boolean idCheck(String id) {
		System.out.println("UserDao > idCheck()");
		
		if(sqlSession.selectOne("users.idCheck", id) == null) {
			return true;
			
		}else {
			return false;
		}
	}
	
	
	/******************************* 회원가입 ******************************/
	public void userInsert(UserVo userVo) {
		System.out.println("UserDao > userInsert()");
		
		sqlSession.insert("users.userInsert", userVo);
	}
	
	
	/****************************** 로그인 정보 *****************************/
	public UserVo login(UserVo userVo) {
		System.out.println("UserDao > login()");
		
		UserVo loginUser = sqlSession.selectOne("users.login", userVo);
		
		return loginUser;
	}
}
