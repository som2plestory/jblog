package com.jblog.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jblog.vo.BlogVo;

@Repository
public class BlogDao {
	
	@Autowired
	private SqlSession sqlSession;
	
	
	/**************************************** 블로그 생성 *****************************************/
	public void blogCreate(BlogVo blogVo) {
		System.out.println("BlogDao > blogCreate()");
		
		sqlSession.insert("blog.blogCreate", blogVo);
	}
	
	
	/************************************** 블로그 메인 정보 ***************************************/
	public BlogVo blogInfo(String id) {
		System.out.println("BlogDao > blogInfo()");
		
		BlogVo blogVo = sqlSession.selectOne("blog.blogInfo", id);
		
		return blogVo;
	}
	
	
	/************************************** 블로그 기본 정보 ***************************************/
	public BlogVo blogBasic(String id) {
		System.out.println("BlogDao > blogBasic()");
		
		BlogVo blogVo = sqlSession.selectOne("blog.blogBasic", id);
		
		return blogVo;
	}
	
	
	/*************************************** 블로그 타이틀 ****************************************/
	public BlogVo blogTitle(String id) {
		System.out.println("BlogDao > blogTitle()");
		
		BlogVo blogVo = sqlSession.selectOne("blog.blogTitle", id);
		
		return blogVo;
	}
	
	
	/************************************* 블로그 기본설정변경 **************************************/
	public void basicUpdate(BlogVo blogVo) {
		System.out.println("BlogDao > basicUpdate()");
		
		sqlSession.update("blog.basicUpdate", blogVo);
	}

}
