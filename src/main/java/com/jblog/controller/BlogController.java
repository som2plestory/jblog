package com.jblog.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jblog.service.BlogService;

@Controller
public class BlogController {

	@Autowired
	private BlogService blogService;
	
	
	//하나 or 분리 어떤게 더 편리할지 맞는지 고민이 되고
	/**************************************** 블로그 이동 *****************************************/
	@RequestMapping("/{id}")
	public String blogMain(Model model, @PathVariable("id") String id){
		System.out.println("BlogController > blogMain()");
		
		Map<String, Object> map = blogService.blogMain(id);
		
		Object blogVo = map.get("blogVo");
		
		if(blogVo == null) {
			System.out.println("잘못된 접근: 해당 주소가 존재하지 않음");
			
			//error 404
			return "/error/403";
		}
		
		model.addAllAttributes(map);
		
		return "/blog/blog-main";
	}
	
	
	/************************************** 카테고리/글 조회 ***************************************/
	@RequestMapping("/{id}/{cateNo}/{postNo}")
	public String blogRead(Model model, @PathVariable("id") String id,
							@PathVariable("cateNo")int cateNo, @PathVariable("postNo")int postNo) {
		System.out.println("BlogController > blogRead()");
		
		Map<String, Object> map = blogService.blogRead(id, cateNo, postNo);
		
		Object blogVo = map.get("blogVo");
		
		if(blogVo == null) {
			System.out.println("잘못된 접근: 해당 주소가 존재하지 않음");
			
			//error 404
			return "/error/403";
		}
		
		model.addAllAttributes(map);
		
		return "/blog/blog-main";
	}


}
