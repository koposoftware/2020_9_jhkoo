package kr.ac.kopo.transfer.controller;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import kr.ac.kopo.account.service.DepositAccountService;
import kr.ac.kopo.account.vo.DepositAccountVO;
import kr.ac.kopo.member.vo.MemberVO;
import kr.ac.kopo.transfer.service.TransferService;
import kr.ac.kopo.transfer.vo.TransferVO;

@Controller
public class TransferController {

	@Autowired
	private TransferService transferService;
	@Autowired
	private DepositAccountService depositAccountService;
	
	
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
		
		// 상대방 이름 가져오기. 이체 내역에 상대방 이름 찍혀야 하므로, 해당 계좌의 주인 이름 가져옴
	//	String toName = transferService.accountOwner(transferVO);
		transferVO.setToName("연습");
		
		// 이체. 내 이름 넣어서 vo 보냄
		MemberVO loginVO = (MemberVO)session.getAttribute("loginVO");
		String myName = loginVO.getName();
		transferVO.setMyName(myName);
		
		transferService.transfer(transferVO);
		
		
		return "transfer/transferSuccess";
	}
	
	
}
