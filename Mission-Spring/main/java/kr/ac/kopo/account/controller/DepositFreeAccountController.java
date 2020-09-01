package kr.ac.kopo.account.controller;


import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import kr.ac.kopo.account.service.DepositAccountService;
import kr.ac.kopo.account.vo.DepositAccountVO;
import kr.ac.kopo.member.vo.MemberVO;

@Controller
public class DepositFreeAccountController {

	@Autowired
	private DepositAccountService depositAccountService;
	
	
	@GetMapping("/product/depositFreeJoin")
	public ModelAndView depositFreeJoinForm(HttpSession session) {
		
		ModelAndView mav = new ModelAndView("accountProduct/depositFreeJoin");
		
		// 개설할 입출금 자유 계좌의 id를 사용자의 아이디로 설정
		MemberVO loginVO = (MemberVO)session.getAttribute("loginVO");
		String id = loginVO.getId();
		
		DepositAccountVO depositAccountVO = new DepositAccountVO();
		depositAccountVO.setId(id);

		// 공유영역에 올려 이것을 매개로 spring form태그에서 값 받아옴 
		mav.addObject("depositAccountVO",depositAccountVO);
		
		return mav;
	}
	
	
	@PostMapping("/product/depositFreeJoin")
	public String depositFreeJoin(@Valid DepositAccountVO depositAccountVO, BindingResult result) {
		
		if(result.hasErrors()) {
			System.out.println("입출금 자유 예금 가입 오류 발생...");
			return "accountProduct/depositFreeJoin";
		}
		
		// insert
		depositAccountService.insertDepositAccount(depositAccountVO);
		
		return "accountProduct/depositFreeJoinFinish";
	}
	
}
