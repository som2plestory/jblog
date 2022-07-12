package com.jblog.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jblog.vo.CmtVo;

@Repository
public class CmtDao {

	@Autowired
	private SqlSession sqlSession;
	
	
	/************************************** 코멘트 가져오기 ***************************************/
	public List<CmtVo> cmtList(int postNo)	{
		System.out.println("CmtDao > cmtList()");
		
		List<CmtVo> cmtList = sqlSession.selectList("comments.cmtList", postNo);

		return cmtList;
	}

	
	/************************************** 코멘트 등록 ***************************************/
	public void cmtInsert(int cmtNo) {
		System.out.println("CmtDao > cmtInsert()");
		
		sqlSession.insert("comments.cmtDelete", cmtNo);
	}
	
	
	/******************************* 코멘트 정보(1개 - 리스트 추가) ********************************/
	public CmtVo selectOne(int cmtNo) {
		System.out.println("CmtDao > selectOne()");
		
		CmtVo cmtVo = sqlSession.selectOne("comments.selectOne", cmtNo);
		
		return cmtVo;
	}
	
	
	/************************************** 코멘트 삭제 ***************************************/
	public int cmtDelete(int cmtNo) {
		System.out.println("CmtDao > cmtDelete()");
		
		int count = sqlSession.delete("comments.cmtDelete", cmtNo);
		
		return count;
	}
	
	
}
