package kr.ac.kopo.member.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.ac.kopo.member.service.MemberService;
import kr.ac.kopo.member.vo.MemberVO;

//@Controller
public class MemberController2 {

	@Autowired
	private MemberService memberService;
	
	//get방식 post방식으로 나누기
//	@RequestMapping(value="/login", method = RequestMethod.GET)
	@GetMapping("/login")
	public String loginForm() {
		
		return "login/login";
	}
	
//	@RequestMapping(value="/login", method = RequestMethod.POST)
	@PostMapping("/login")
	public String login(MemberVO member, HttpSession session) {	//session객체 바로 가져올 수 있음    @RequestParam("id") String id, @RequestParam("password") String password 이렇게도 가능
		
	//	System.out.println(id + " : " + password);
	//	System.out.println(member);
		
		MemberVO loginVO = memberService.login(member);
		
		//로그인 실패
		if(loginVO == null) {
			return "redirect:/login";
		}
		//로그인 성공
		session.setAttribute("loginVO", loginVO);
		return "redirect:/";
	}
	
	@RequestMapping("/logout")
	public String logout(HttpSession session) {
		
		session.invalidate();
		
		return "redirect:/";
	}
	
	
	//회원가입 폼으로 보내기
	@RequestMapping("/join")
	public String joinForm() {	
		return "login/join";
	}
	
	
	
}
