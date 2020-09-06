package kr.ac.kopo.transfer.controller;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
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
import kr.ac.kopo.transfer.service.TransferService;
import kr.ac.kopo.transfer.vo.TransferVO;

@Controller
public class TransferController {

	@Autowired
	private TransferService transferService;
	@Autowired
	private DepositAccountService depositAccountService;
	@Autowired
	private SavingsAccountService savingsAccountService;
	
	// 보유한 입출금 계좌 리스트
	@RequestMapping("/transferChoose")
	public ModelAndView transferList(HttpSession session) {
		
		ModelAndView mav = new ModelAndView("transfer/choose");
		// session에 등록된 id 
		MemberVO loginVO = (MemberVO)session.getAttribute("loginVO");
		String id = loginVO.getId();
		
		// 사용자의 입출금 계좌 정보 가져와 공유영역에 올리기
		List<DepositAccountVO> depositAccountList = depositAccountService.selectDepositAccountById(id);
		mav.addObject("depositAccountList", depositAccountList);
		
		return mav;
	}
	
	// 이체 form
	@GetMapping("/transfer/{accountNumber}")
	public ModelAndView transferForm(@PathVariable String accountNumber) {
		
		ModelAndView mav = new ModelAndView("transfer/transfer");
		
		TransferVO transferVO = new TransferVO();
		mav.addObject("transferVO", transferVO);
		
		// 해당 계좌의 정보
		DepositAccountVO depositAccount = depositAccountService.getDepositAccountInfo(accountNumber);
		
		
		
		mav.addObject("depositAccount",depositAccount);
		
		return mav;
	}
	
	// 이체하기
	@PostMapping("/transfer/{accountNumber}")	// 이게 맞나.. 
	public String transfer(@Valid TransferVO transferVO, BindingResult result, HttpSession session) {
		
		if(result.hasErrors()) {
			System.out.println("이체오류 발생...");
			return "transfer/transferFail";
		}
		
		// 고치기!!!!
		// 상대방 이름 가져오기. 이체 내역에 상대방 이름 찍혀야 하므로, 해당 계좌의 주인 이름 가져옴
		//	String toName = transferService.accountOwner(transferVO);
		transferVO.setToName("구재희");
		
		// 이체. 내 이름 넣어서 vo 보냄
		MemberVO loginVO = (MemberVO)session.getAttribute("loginVO");
		String myName = loginVO.getName();
		transferVO.setMyName(myName);
		
		transferService.transfer(transferVO);
		
		
		return "transfer/transferSuccess";
	}
	
	
	/////// 예약 이체. 전 설정
	
	// 1. 확인창. 계좌 정보 받아서 setAutoSavingBool  Y로 바꿔줌
	// 2. autoTransfer DB에 내용 저장
	@GetMapping("/autoTransferConfirm/{accountNumber}")
	public ModelAndView autoTransfer1confirm(@PathVariable String accountNumber, HttpSession session) {
		
		ModelAndView mav = new ModelAndView("transfer/autoTransferFinish");
		
		// 해당 계좌의 정보 업데이트
		SavingsAccountVO savingsAccount = savingsAccountService.getSavingsAccountInfo(accountNumber);
		savingsAccount.setAutoSavingBool("Y");
		savingsAccountService.changeBool(savingsAccount);
		
		
		// autoTransfer DB에 내용 저장하기
		TransferVO transferVO = new TransferVO();
		transferVO.setAccountNumber(savingsAccount.getAutoSaving());	// 출금 계좌
		transferVO.setToAccountNumber(savingsAccount.getAccountNumber());  // 내 적금 계좌
		transferVO.setToAmount(savingsAccount.getAvgAmount());          // 계산된 평균 금액 송금 됨
		transferVO.setToType("2");	 									// 이체 타입(이체)
		
		MemberVO loginVO = (MemberVO)session.getAttribute("loginVO");
		String myName = loginVO.getName();
		transferVO.setToName(myName);								// 받는사람 이름(적금 계좌 )
		transferVO.setMyName("보내는 사람입니다");						// 보내는 사람 이름 
		
		transferService.insertAutoTransfer1(transferVO);
		
		return mav;
	}
	
	
	// 3. 자동 이체 실행. 매월 1일 12시
	@Scheduled(cron = "0 0 12 1 * *")
	public void autoTransfer1() {
		transferService.autoTransfer1();
	}
	
	
	// 예약이체 해지 - 예약이체 테이블에서 삭제, 상태 Y->N 변경
	@GetMapping("/autoTransferDelete/{accountNumber}")
	public ModelAndView autoTransferDelete(@PathVariable String accountNumber) {
		transferService.autoTransferDelete(accountNumber);
		
		ModelAndView mav = new ModelAndView("redirect:/account");
		return mav;
	}
	
}
