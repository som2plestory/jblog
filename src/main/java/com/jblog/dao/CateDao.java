package com.jblog.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jblog.vo.CateVo;

@Repository
public class CateDao {
	
	@Autowired
	private SqlSession sqlSession;
	
	
	/*********************************** 카테고리 정보 리스트 ************************************/
	public List<CateVo> cateList(String id) {
		System.out.println("CateDao > cateList()");
		
		List<CateVo> cateList =sqlSession.selectList("category.cateList", id);
		
		return cateList;
	}
	
	
	/************************************* 카테고리 추가 **************************************/
	public void cateInsert(CateVo cateVo){
		System.out.println("CateDao > cateInsert()");
		
		sqlSession.insert("category.cateInsert", cateVo);
	}
	
	
	/****************************** 카테고리 정보(1개 - 리스트 추가) *******************************/
	public CateVo selectOne(int cateNo){
		System.out.println("CateDao > selectOne()");
		
		CateVo cateVo1 = sqlSession.selectOne("category.selectOne", cateNo);
		
		return cateVo1;
	}

	
	/************************************* 카테고리 삭제 **************************************/
	public int cateDelete(CateVo cateVo){
		System.out.println("CateDao > cateDelete()");
		
		int count = sqlSession.delete("category.cateDelete", cateVo);
		
		return count;
	}
	
	
	/********************************* 카테고리 리스트(index) **********************************/
	public List<CateVo> cateIndex(String id){
		System.out.println("CateDao > cateIndex()");
		
		List<CateVo> cateIndex = sqlSession.selectList("category.cateIndex", id);
		
		return cateIndex;
	}

}
