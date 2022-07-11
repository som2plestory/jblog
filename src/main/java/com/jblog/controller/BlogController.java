package com.jblog.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jblog.service.BlogService;
import com.jblog.vo.CmtVo;

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
	
	
	/************************************** 코멘트 가져오기 ***************************************/
	@ResponseBody
	@RequestMapping("/{postNo}/cmtList")
	public List<CmtVo> cmtList(@PathVariable("postNo") int postNo) {
		System.out.println("BlogController > cmtList()");
		
		List<CmtVo> cmtList = blogService.cmtList(postNo);
		
		return cmtList;
	}
	
	 


}
