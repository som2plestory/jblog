package com.jblog.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jblog.dao.BlogDao;
import com.jblog.dao.CateDao;
import com.jblog.dao.PostDao;
import com.jblog.vo.BlogVo;
import com.jblog.vo.CateVo;
import com.jblog.vo.PostVo;

@Service
public class BlogService {

	@Autowired
	private BlogDao blogDao;
	@Autowired
	private CateDao cateDao;
	@Autowired
	private PostDao postDao;
	
	
	/************************************** 블로그 메인 정보 ***************************************/
	public Map<String, Object> blogMain(String id) {
		System.out.println("BlogService > blogMain()");
		
		Map<String, Object> map = new HashMap<>();
		
		BlogVo blogVo = blogDao.blogInfo(id);
		map.put("blogVo", blogVo);

		if(blogVo != null) {
			List<CateVo> cateIndex = cateDao.cateIndex(id); 
			map.put("cateIndex", cateIndex);
			
			int cateNo = cateIndex.get(0).getCateNo();
			List<PostVo> postList = postDao.postList(cateNo);
			map.put("postList", postList);
			
			PostVo postVo = postDao.postLast(id);
			map.put("postVo", postVo);
		}
		
		return map;
	}
	
	
	/************************************** 카테고리/글 조회 ***************************************/
	public Map<String, Object> blogRead(String id, int cateNo, int postNo) {
		System.out.println("BlogService > blogRead()");
		
		Map<String, Object> map = new HashMap<>();
		
		BlogVo blogVo = blogDao.blogInfo(id);
		map.put("blogVo", blogVo);
		
		if(blogVo != null) {
			List<CateVo> cateIndex = cateDao.cateIndex(id); 
			map.put("cateIndex", cateIndex);
			
			List<PostVo> postList = postDao.postList(cateNo);
			map.put("postList", postList);
			
			PostVo postVo = postDao.postRead(postNo);
			map.put("postVo", postVo);
		}
		
		return map;
	}
	
	
}
