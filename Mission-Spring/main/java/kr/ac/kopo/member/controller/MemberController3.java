package kr.ac.kopo.member.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import kr.ac.kopo.member.service.MemberService;
import kr.ac.kopo.member.vo.MemberVO;

//@SessionAttributes("loginVO")	//로그인할때 세션에 올릴 수 있는 어노테이션. 배열로 여러개 들어올 수 있음 ({"loginVO"})
//@Controller
public class MemberController3 {

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
	public ModelAndView login(MemberVO member, HttpSession session) {	 
		

		MemberVO loginVO = memberService.login(member);
		ModelAndView mav = new ModelAndView();
		
		//로그인 실패
		if(loginVO == null) {
			mav.setViewName("redirect:/login");
		} else {
		//로그인 성공
			String dest = (String)session.getAttribute("dest");
			if(dest == null) {	
				mav.setViewName("redirect:/");				
			} else {
				mav.setViewName("redirect:" + dest);	//로그인 안한살태면 다시 로그인 화면으로 가는데, 로그인 하면 다시 원래 왔던 곳으로 돌아가주게 해줌.
				session.removeAttribute("dest");
			}
			
			mav.addObject("loginVO", loginVO);	//위에 @SessionAttributes("loginVO") 했으므로 session에 등록됨
		}
		return mav;
	}
	
	@RequestMapping("/logout")
	public String logout(SessionStatus status) {	//세션에 등록된 객체 있는지 없는지 볼때 쓰는 객체
		
		status.setComplete();	// @SessionAttributes("loginVO") 이걸로 session에 등록했으면 이렇게 지워야함! 
		
		return "redirect:/";
	}
	
}
