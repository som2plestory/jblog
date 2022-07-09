package com.jblog.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jblog.service.AdminService;
import com.jblog.vo.BlogVo;
import com.jblog.vo.CateVo;
import com.jblog.vo.PostVo;
import com.jblog.vo.UserVo;

@Controller
public class AdminController {
	
	@Autowired
	private AdminService adminService;
	
	
	/**************************************** 블로그 설정 *****************************************/
	@RequestMapping({"/{id}/admin","/{id}/admin/basic"})
	public String adminBasic(Model model, HttpSession session, @PathVariable("id") String id) {
		System.out.println("AdminController > adminBasic()");
		
		UserVo authUser = (UserVo)session.getAttribute("authUser");
		
		//주소
		if(authUser == null) {
			System.out.println("로그인이 되어 있지 않음");
			
			return "/user/loginForm";
		}
		
		if (!authUser.getId().equals(id)) {
			System.out.println("잘못된 접근: 로그인 정보 불일치");
			
			return "/error/403";
		}

		BlogVo blogVo = adminService.adminBasic(id);
		model.addAttribute("blogVo", blogVo);
		
		return "/blog/admin/blog-admin-basic";
	}
	
	
	/************************************* 블로그 기본설정변경 **************************************/
	@RequestMapping("/{id}/admin/basic/update")
	public String basicUpdate(Model model, HttpSession session, BlogVo blogVo) {
		System.out.println("AdminController > basicUpdate()");
		
		UserVo authUser = (UserVo)session.getAttribute("authUser");
		
		/*
		 * 보이는 화면이 아니라 사람들이 주소를 알고 접근할 수가 없었다
		 */ 
		
		if(authUser == null) {
			System.out.println("로그인이 되어 있지 않음");
			
			return "/user/loginForm";
		}
		
		adminService.basicUpdate(authUser, blogVo);
		
		return "redirect:./";
	}
	
	
	/************************************* 블로그 카테고리 설정 **************************************/
	@RequestMapping("/{id}/admin/category")
	public String adminCate(Model model, HttpSession session, @PathVariable("id") String id) {
		System.out.println("AdminController > adminCate()");
		
		UserVo authUser = (UserVo)session.getAttribute("authUser");
		
		if(authUser == null) {
			System.out.println("로그인이 되어 있지 않음");
			
			return "/user/loginForm";
		}
		
		if (!authUser.getId().equals(id)) {
			System.out.println("잘못된 접근: 로그인 정보 불일치");
			
			return "/error/403";
		}
		
		BlogVo blogVo = adminService.adminCate(id);
		model.addAttribute("blogVo", blogVo);
		
		return "/blog/admin/blog-admin-cate";
	}
	
	
	/************************************* 블로그 카테고리 정보 **************************************/
	@ResponseBody
	@RequestMapping("/admin/category/cateList")
	public List<CateVo> cateList(HttpSession session) {
		System.out.println("AdminController > cateList()");
		
		UserVo authUser = (UserVo)session.getAttribute("authUser");
		
		List<CateVo> cateList = adminService.cateList(authUser);
		
		return cateList;
	}
	
	
	/************************************* 블로그 카테고리 추가 **************************************/
	@ResponseBody
	@RequestMapping("/admin/category/insert")
	public CateVo cateInsert(HttpSession session, @RequestBody CateVo cateVo) {
		System.out.println("AdminController > cateInsert()");
		
		UserVo authUser = (UserVo)session.getAttribute("authUser");
		System.out.println(authUser);
		CateVo cateVo1 = adminService.cateInsert(authUser, cateVo);
		
		return cateVo1;
	}
	
	
	/************************************* 블로그 카테고리 삭제 **************************************/
	@ResponseBody
	@RequestMapping("/admin/category/delete")
	public String cateDelete(HttpSession session, @RequestBody int cateNo) {
		System.out.println("AdminController > cateDelete()");
		
		UserVo authUser = (UserVo)session.getAttribute("authUser");
		String result = adminService.cateDelete(authUser, cateNo);
		
		return result;
	}
	
	
	/*************************************** 블로그 글 작성폼 ***************************************/
	@RequestMapping("/{id}/admin/writeForm")
	public String writeForm(Model model, HttpSession session, @PathVariable("id") String id) {
		System.out.println("AdminController > writeForm()");
		
		UserVo authUser = (UserVo)session.getAttribute("authUser");
		
		if(authUser == null) {
			System.out.println("로그인이 되어 있지 않음");
			
			return "/user/loginForm";
		}

		if (!authUser.getId().equals(id)) {
			System.out.println("잘못된 접근: 로그인 정보 불일치");
			
			return "/error/403";
		}
		
		Map<String, Object> map = adminService.writeForm(id);
		model.addAllAttributes(map);
		
		return "/blog/admin/blog-admin-write";
	}
	
	
	/**************************************** 블로그 글 작성 ****************************************/
	@RequestMapping("/{id}/admin/post/insert")
	public String postInsert(HttpSession session, PostVo postVo) {
		System.out.println("AdminController > postInsert()");
		
		UserVo authUser = (UserVo)session.getAttribute("authUser");
		
		if(authUser == null) {
			System.out.println("로그인이 되어 있지 않음");
			
			return "/user/loginForm";
		}
		
		adminService.postInsert(postVo);
		
		return "redirect:/{id}/admin/writeForm";
	}
}

