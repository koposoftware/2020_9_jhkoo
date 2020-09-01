package kr.ac.kopo.account.controller;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import kr.ac.kopo.account.service.DepositAccountService;
import kr.ac.kopo.account.service.SavingsAccountService;
import kr.ac.kopo.account.vo.SavingsAccountVO;
import kr.ac.kopo.member.vo.MemberVO;

@Controller
public class SavingsAccountController {

	@Autowired
	private DepositAccountService depositAccountService;
	@Autowired
	private SavingsAccountService savingsAccountService;
	
	@GetMapping("/product/savingsJoin")
	public ModelAndView savingsJoinForm(HttpSession session) {
		
		ModelAndView mav = new ModelAndView("accountProduct/savingsJoin");
		
		// 개설할 적금 계좌의 id를 사용자의 아이디로 설정
		MemberVO loginVO = (MemberVO)session.getAttribute("loginVO");
		String id = loginVO.getId();
		
		
		SavingsAccountVO savingsAccountVO = new SavingsAccountVO();
		savingsAccountVO.setId(id);
		
		

		// 사용자의 입출금자유예금 계좌 가져와서 공유영역에 올리기 
		// 적금 개설 시, 출금할 계좌를 선택하기 위해.
		List<String> depositAccountNumList = depositAccountService.depositAccountNumListById(id);
		mav.addObject("depositAccountNumList",depositAccountNumList);
		
		// 공유영역에 올려 이것을 매개로 spring form태그에서 값 받아옴 
		mav.addObject("savingsAccountVO",savingsAccountVO);
		
		return mav;
	}
	
	
	@PostMapping("/product/savingsJoin")
	public String savingsJoin(@Valid SavingsAccountVO savingsAccountVO, BindingResult result) {
		
		if(result.hasErrors()) {
			System.out.println("적금 가입 오류 발생...");
			return "accountProduct/savingsJoin";
		}
		
		// insert
		savingsAccountService.insertSavingsAccount(savingsAccountVO);
		
		return "accountProduct/savingsJoinFinish";
	}
	
}
