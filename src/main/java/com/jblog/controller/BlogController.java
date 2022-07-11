package com.jblog.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.jblog.service.BlogService;

@Controller
public class BlogController {

	@Autowired
	private BlogService blogService;
	
	
	/**************************************** 블로그 이동 *****************************************/
	@RequestMapping("/{id}")
	public String blogMain( Model model, @PathVariable("id") String id,
							@RequestParam(name="c", defaultValue="0") int cateNo,
							@RequestParam(name="p", defaultValue="0") int postVo){
		System.out.println("BlogController > blogMain()");
		
		Map<String, Object> map = blogService.blogMain(id, cateNo, postVo);
		
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
	/*///<으로 했다가 다른 주소와 겹치면서 pathvariable을 쓰지 않기로 결정 > 축약하면서 전부 param으로 받고 main하나로 합치기로 결정
	@RequestMapping("/{id}/{postNo}") 
	public String blogRead(Model model, @PathVariable("id") String id, 
							 @PathVariable("cateNo")int cateNo, @PathVariable("postNo")int postNo) {
		System.out.println("BlogController > blogRead()");
	
		Map<String, Object> map = blogService.blogRead(id, cateNo, postNo);
	
		Object blogVo = map.get("blogVo");
	
		if(blogVo == null) { 
			System.out.println("잘못된 접근: 해당 주소가 존재하지 않음");
			
			//error 404 
			return "/error/403"; }
		}
	
		model.addAllAttributes(map);
	
		return "/blog/blog-main"; 
	}
	*/
	 


}
