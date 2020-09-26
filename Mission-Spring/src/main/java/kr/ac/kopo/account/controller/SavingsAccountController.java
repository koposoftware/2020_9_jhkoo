package kr.ac.kopo.account.controller;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import kr.ac.kopo.account.service.DepositAccountService;
import kr.ac.kopo.account.service.SavingsAccountService;
import kr.ac.kopo.account.vo.SavingsAccountVO;
import kr.ac.kopo.member.vo.MemberVO;
import kr.ac.kopo.transfer.service.TransferService;
import kr.ac.kopo.transfer.vo.TransferVO;

@Controller
public class SavingsAccountController {

	@Autowired
	private DepositAccountService depositAccountService;
	@Autowired
	private SavingsAccountService savingsAccountService;
	@Autowired
	private TransferService transferService;
	
	@GetMapping("/product/savingsJoin/{num}")
	public ModelAndView savingsJoinForm(@PathVariable String num, HttpSession session) {
		
		ModelAndView mav = new ModelAndView("accountProduct/savingsJoin" + num);
		
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
	
	
	@PostMapping("/product/savingsJoin/{num}")
	public String savingsJoin(@Valid SavingsAccountVO savingsAccountVO, BindingResult result, HttpSession session) {
		
		if(result.hasErrors()) {
			System.out.println("적금 가입 오류 발생...");
			return "accountProduct/savingsJoin";
		}
		
		System.out.println("1==================");
		System.out.println(savingsAccountVO);
		
		// 새로운 계좌번호 가져오기 위해 가장 최근 seq 가져옴-DB에서 생성하는 것이 안전
		String accountNumber = savingsAccountService.getNewAccountNumber();
		savingsAccountVO.setAccountNumber(accountNumber);
		
		// 적금 계좌 등록하기
		savingsAccountService.insertSavingsAccount(savingsAccountVO);
		
		// 적금 정보 가져오기 - 문제
		SavingsAccountVO savingsAccount = savingsAccountService.getSavingsAccountInfoForTrans(savingsAccountVO.getAccountNumber());
		System.out.println("문제? : " + savingsAccount);
		
		 // 기본가입금액 자동이체하기. 입출금 -> 적금 TransferVO transferVO = new TransferVO();
		 TransferVO transferVO = new TransferVO();
		 transferVO.setAccountNumber(savingsAccount.getAutoSaving());
		 transferVO.setToAccountNumber(savingsAccount.getAccountNumber());
		 transferVO.setToAmount(savingsAccount.getBalance());
		 transferVO.setToType("2");
		 
		 MemberVO loginVO = (MemberVO)session.getAttribute("loginVO"); 
		 String myName = loginVO.getName(); 
		 transferVO.setToName(myName);
		 transferVO.setMyName(myName);
		 
		 // 이체 입출금 -> 적금 
		 transferService.transferToSavings(transferVO);
		 
		return "accountProduct/savingsJoinFinish";
	}
	
}
