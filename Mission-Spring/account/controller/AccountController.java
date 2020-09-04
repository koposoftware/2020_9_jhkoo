package kr.ac.kopo.account.controller;


import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import kr.ac.kopo.account.service.DepositAccountService;
import kr.ac.kopo.account.service.SavingsAccountService;
import kr.ac.kopo.account.vo.DepositAccountVO;
import kr.ac.kopo.account.vo.SavingsAccountVO;
import kr.ac.kopo.member.vo.MemberVO;

@Controller
public class AccountController { //오류난거 -> implements Serializable 이거 왜한거?

	@Autowired
	private DepositAccountService depositAccountService;
	@Autowired
	private SavingsAccountService savingsAccountService;

	
	
	@RequestMapping("/account")
	public ModelAndView accountList(HttpSession session) {
		
		ModelAndView mav = new ModelAndView("account/list");
		// session에 등록된 id 
		MemberVO loginVO = (MemberVO)session.getAttribute("loginVO");
		String id = loginVO.getId();
		
		// 입출금지유 계좌 테이블		
		List<DepositAccountVO> depositAccountList = depositAccountService.selectDepositAccountById(id);
		// 보유 입출금 계좌 총 잔액
		int depositTotalBalance = depositAccountService.depositTotalBalanceById(id);
		
		mav.addObject("depositAccountList", depositAccountList);
		mav.addObject("depositTotalBalance", depositTotalBalance);
	
		session.setAttribute("depositAccountList", depositAccountList);
		
		// 적금 계좌 테이블
		List<SavingsAccountVO> savingsAccountList = savingsAccountService.selectSavingsAccountById(id);
		// 보유 적금 계좌 총 잔액
		int savingsTotalBalance = savingsAccountService.savingsTotalBalanceById(id);
		
		mav.addObject("savingsAccountList", savingsAccountList);
		mav.addObject("savingsTotalBalance", savingsTotalBalance);
		
		// 현금
		mav.addObject("cash", loginVO.getCash());
		
		return mav;
	}
	

	
	// 입출금 계좌 manage
	@GetMapping("/depositManage/{accountNumber}")
	public ModelAndView depositManage(@PathVariable("accountNumber") String accountNumber) {
		
		ModelAndView mav = new ModelAndView("account/depositManage");
		
		// 해당 계좌의 정보
		DepositAccountVO depositAccount = depositAccountService.getDepositAccountInfo(accountNumber);
		mav.addObject("depositAccount",depositAccount);
		
		return mav;
	}
	
	// 계좌 정보 수정
	@PostMapping("/depositManageChange")
	public String depositManageUpdate(DepositAccountVO depositAccountVO, HttpSession session) {
		
		// 사용자 id를 vo에 넣어서 보내기. 사용자의 모든 계좌상태를 sub으로 바꿔놓기 위해 필요
		MemberVO loginVO = (MemberVO)session.getAttribute("loginVO");
		String id = loginVO.getId();
		depositAccountVO.setId(id);

		depositAccountService.changeDepositManage(depositAccountVO);
		
		return "redirect:/account";
	}
	
	// 적금 계좌 manage
	@GetMapping("/savingsManage/{accountNumber}")
	public ModelAndView savingsManage(@PathVariable("accountNumber") String accountNumber, HttpSession session) {
		
		ModelAndView mav = new ModelAndView("account/savingsManage");
		
		// 해당 계좌의 정보
		SavingsAccountVO savingsAccount = savingsAccountService.getSavingsAccountInfo(accountNumber);
		mav.addObject("savingsAccount", savingsAccount);
		
		// 출금계좌 선택을 위한 입출금 계좌 리스트
		MemberVO loginVO = (MemberVO)session.getAttribute("loginVO");
		String id = loginVO.getId();
		List<String> depositAccountNumList = depositAccountService.depositAccountNumListById(id);
		mav.addObject("depositAccountNumList",depositAccountNumList);
		
		return mav;
	}
	
	// 적금 계좌 정보 수정
	@PostMapping("/savingsManageChange")
	public String savingsManageUpdate(SavingsAccountVO savingsAccountVO) {
		savingsAccountService.changeSavingsManage(savingsAccountVO);
		return "redirect:/account";
	}
	
}
