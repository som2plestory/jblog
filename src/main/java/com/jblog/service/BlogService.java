package com.jblog.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jblog.dao.BlogDao;
import com.jblog.dao.CateDao;
import com.jblog.dao.CmtDao;
import com.jblog.dao.PostDao;
import com.jblog.vo.BlogVo;
import com.jblog.vo.CateVo;
import com.jblog.vo.CmtVo;
import com.jblog.vo.PostVo;

@Service
public class BlogService {

	@Autowired
	private BlogDao blogDao;
	@Autowired
	private CateDao cateDao;
	@Autowired
	private PostDao postDao;
	@Autowired
	private CmtDao cmtDao;
	
	
	/************************************** 블로그 메인 정보 ***************************************/
	public Map<String, Object> blogMain(String id, int cateNo, int postNo)	{
		System.out.println("BlogService > blogMain()");
		
		Map<String, Object> map = new HashMap<>();
		
		BlogVo blogVo = blogDao.blogInfo(id);
		map.put("blogVo", blogVo);

		if(blogVo != null) {
			List<CateVo> cateIndex = cateDao.cateIndex(id); 
			map.put("cateIndex", cateIndex);
			
			if(cateNo == 0) {
				cateNo = cateIndex.get(0).getCateNo();
			}
			List<PostVo> postList = postDao.postList(cateNo);
			map.put("postList", postList);
			
			PostVo postVo = new PostVo();
			if(postNo == 0) {
				postVo = postDao.postLast(id);
			}else {
				postVo = postDao.postRead(postNo);
			}
			map.put("postVo", postVo);
		}
		
		return map;
	}
	
	
	/************************************** 코멘트 가져오기 ***************************************/
	public List<CmtVo> cmtList(int postNo)	{
		System.out.println("BlogService > cmtList()");
		
		List<CmtVo> cmtList = cmtDao.cmtList(postNo);
		
		return cmtList;
	}
	
	
	/*************************************** 코멘트 삭제 ****************************************/
	public String cmtDelete(int cmtNo) {
		System.out.println("BlogService > cmtDelete()");
		
		String result;
		int count = cmtDao.cmtDelete(cmtNo);
		
		if(count == 1) {
			result = "success";
		
		}else {
			result = "fail";
			
		}
		
		return result;
	}
	
	
	/*************************************** 코멘트 작성 ****************************************/
	public CmtVo cmtInsert(CmtVo cmtVo) {
		System.out.println("BlogService > cmtInsert()");
		
		cmtDao.cmtInsert(cmtVo.getUserNo());
		
		int cmtNo = cmtVo.getCmtNo();
		CmtVo cmtVo1 = cmtDao.selectOne(cmtNo);
		
		return cmtVo1;
	}
}
