package com.jblog.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jblog.dao.PostDao;
import com.jblog.vo.PostVo;

@Service
public class MainService {

	@Autowired
	private PostDao postDao;
	
	
	/************************************* 포스트 검색 **************************************/
	public List<PostVo> searchPost(String keyword, String kwdOpt) {
		System.out.println("MainService > searchPost()");
		
		Map<String, String> map = new HashMap<>();
		map.put("keyword", keyword);
		map.put("kwdOpt", kwdOpt);
		
		List<PostVo> postList = postDao.searchPost(map);
		System.out.println(postList);
		return postList;
	}
	
}
