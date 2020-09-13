package kr.ac.kopo.account.controller;


import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import kr.ac.kopo.account.service.DepositAccountService;
import kr.ac.kopo.account.vo.DepositAccountVO;
import kr.ac.kopo.member.vo.MemberVO;
import kr.ac.kopo.transfer.service.TransferService;
import kr.ac.kopo.transfer.vo.TransferVO;

@Controller
public class DepositFreeAccountController {

	@Autowired
	private DepositAccountService depositAccountService;
	@Autowired
	private TransferService transferService;
	
	@GetMapping("/product/depositFreeJoin/{num}")
	public ModelAndView depositFreeJoinForm(@PathVariable String num, HttpSession session) {
		
		ModelAndView mav = new ModelAndView("accountProduct/depositFreeJoin" + num);
		
		// 개설할 입출금 자유 계좌의 id를 사용자의 아이디로 설정
		MemberVO loginVO = (MemberVO)session.getAttribute("loginVO");
		String id = loginVO.getId();
		
		DepositAccountVO depositAccountVO = new DepositAccountVO();
		depositAccountVO.setId(id);

		// 공유영역에 올려 이것을 매개로 spring form태그에서 값 받아옴 
		mav.addObject("depositAccountVO",depositAccountVO);
		
		return mav;
	}
	
	
	@PostMapping("/product/depositFreeJoin/{num}")
	public String depositFreeJoin(@Valid DepositAccountVO depositAccountVO, BindingResult result) {
		
		if(result.hasErrors()) {
			System.out.println("입출금 자유 예금 가입 오류 발생...");
			return "accountProduct/depositFreeJoin";
		}
		
		// insert
		depositAccountService.insertDepositAccount(depositAccountVO);
		
		return "accountProduct/depositFreeJoinFinish";
	}
	
	// 계좌 삭제. 1. 메인 계좌로 이체   2.테이블에서 삭제
	@Transactional
	@GetMapping("/deleteDepositAccount/{accountNumber}/{balance}")
	public ModelAndView deleteDepositAccount(@PathVariable String accountNumber, 
											 @PathVariable int balance,
											 HttpSession session) {
		ModelAndView mav = new ModelAndView("redirect:/account");
		
		
		// id로 메인 계좌 찾고 메인계좌번호 transferVO에 넣기!
		MemberVO loginVO = (MemberVO)session.getAttribute("loginVO");
		String id = loginVO.getId();
		String mainAccountNumber = depositAccountService.getMainAccountNumber(id);
		
		// 그 계좌로 잔액 이체 후
		TransferVO transferVO = new TransferVO();
		transferVO.setAccountNumber(accountNumber);			// 삭제할 계좌에서
		transferVO.setToAccountNumber(mainAccountNumber);	// 메인 계좌로
		transferVO.setToAmount(balance);
		transferVO.setToName(loginVO.getName());			// 내 계좌에서 내 계좌로 보내는 것이므로 이름 같게 설정
		transferVO.setMyName(loginVO.getName());
		transferVO.setToType("2");						
		
		transferService.transfer(transferVO);
		
		// 계좌 삭제
		depositAccountService.deleteDepositAccount(accountNumber);
		
		return mav;
	}
	
}
