package com.jblog.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.jblog.service.MainService;
import com.jblog.vo.PostVo;

@Controller
public class MainController {
	
	@Autowired
	private MainService mainService;
	
	
	/************************************* 메인 화면 **************************************/
	@RequestMapping(value="/")
	public String main() {
		System.out.println("MainController > main()");
		
		return "main/index";
	}

	
	/************************************* 포스트 검색 **************************************/
	@RequestMapping(value="/search")
	public String searchPost(Model model, @RequestParam(name="keyword", defaultValue="") String keyword,
							 @RequestParam(name="kwdOpt", defaultValue="") String kwdOpt) {
		System.out.println("MainController > searchPost()");
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		List<PostVo> postList = mainService.searchPost(keyword, kwdOpt);
		map.put("postList", postList);
		map.put("keyword", keyword);
		map.put("kwdOpt", kwdOpt);
		
		model.addAttribute("sMap", map);
		
		return "main/index";
	}
	
}
