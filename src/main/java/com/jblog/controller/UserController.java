package com.jblog.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jblog.service.UserService;
import com.jblog.vo.UserVo;

@Controller
public class UserController {

	@Autowired
	private UserService userService;
	
	
	/***************************************** 회원가입폼 ******************************************/
	@RequestMapping("/user/joinForm")
	public String joinForm() {
		System.out.println("UserController > joinForm()");
		
		return "user/joinForm";
	}
	
	
	/*************************************** 아이디 중복 확인 ****************************************/
	@ResponseBody
	@PostMapping("/user/idCheck")
	public String idCheck(@RequestBody String id) {
		System.out.println("UserController > idCheck");
		
		String result;
		
		boolean check = userService.idCheck(id); 
		
		//아이디 생성 가능
		if(check == true) {
			result = "success";
			
		//아이디 중복
		}else {
			result = "fail";
		}
		
		return result;
	}
	
	
	/****************************************** 회원가입 *******************************************/
	@PostMapping("/user/join")
	public String join(@ModelAttribute UserVo userVo) {
		System.out.println("UserContoller > join()");
		
		userService.join(userVo);
		
		return "user/joinSuccess";
	}
	
	
	/****************************************** 로그인폼 *******************************************/
	@RequestMapping("/user/loginForm")
	public String loginForm() {
		System.out.println("UserController > loginForm()");
		
		return "user/loginForm";
	}
	
	
	/******************************************* 로그인 ********************************************/
	@PostMapping("/user/login")
	public String login(HttpSession session, UserVo userVo) {
		System.out.println("UserController > login()");
		
		UserVo authUser = userService.login(userVo);
		
		if(authUser != null) {
			System.out.println("로그인 성공");
			session.setAttribute("authUser", authUser);
			System.out.println(authUser);
			return "redirect:/";
			
		}else {
			System.out.println("로그인 실패");
			
			return "redirect:/user/loginForm?result=fail";
		}
	}
	
	
	/****************************************** 로그아웃 *******************************************/
	@RequestMapping("/user/logout")
	public String logout(HttpSession session) {
		System.out.println("UserController > login()");
		
		session.removeAttribute("authUser");
		session.invalidate();
		
		return "redirect:/";
	}
}
