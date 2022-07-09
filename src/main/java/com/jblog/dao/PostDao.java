package com.jblog.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jblog.vo.PostVo;

@Repository
public class PostDao {

	@Autowired
	private SqlSession sqlSession;
	
	
	/**************************************** 블로그 글 작성 ****************************************/
	public void postInsert(PostVo postVo) {
		System.out.println("PostDao > postInsert()");
		
		sqlSession.insert("post.postInsert", postVo);
	}
	
	
	/************************************* 카테고리별 포스트 리스트 *************************************/
	public List<PostVo> postList(int cateNo) {
		System.out.println("PostDao > postList()");
		
		List<PostVo> postList = sqlSession.selectList("post.postList", cateNo);
		
		return postList;
	}
	
	
	/***************************************** 최근 글 조회 *****************************************/
	public PostVo postLast(String id) {
		System.out.println("PostDao > postLast()");
		
		PostVo postVo = sqlSession.selectOne("post.postLast", id);
		
		return postVo;
	}
	
	
	/***************************************** 특정 글 조회 *****************************************/
	public PostVo postRead(int postNo) {
		System.out.println("PostDao > postRead()");
		
		PostVo postVo = sqlSession.selectOne("post.postRead", postNo);
		
		return postVo;
	}
	
}
