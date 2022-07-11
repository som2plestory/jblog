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
}
