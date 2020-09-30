package kr.ac.kopo.transfer.controller;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.commons.codec.binary.Base32;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import kr.ac.kopo.account.service.DepositAccountService;
import kr.ac.kopo.account.service.SavingsAccountService;
import kr.ac.kopo.account.vo.DepositAccountVO;
import kr.ac.kopo.account.vo.SavingsAccountVO;
import kr.ac.kopo.eda.service.EdaService;
import kr.ac.kopo.eda.vo.EmailVO;
import kr.ac.kopo.favorite.service.FavoriteService;
import kr.ac.kopo.favorite.vo.FavoriteVO;
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
	@Autowired
	private FavoriteService favoriteService;
	@Autowired
	private EdaService edaService;
	@Autowired
	private JavaMailSenderImpl mailSender;
	
	
	/**
	 *  나의 입출금 계좌 중에서 이체를 시행할 계좌 선택하는 페이지. 
	 *  보유한 입출금 계좌의 정보 리스트를 공유영역에 올립니다.
	 */
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
	
	/**
	 *  이체 FORM 페이지.
	 *  선택된 입출금 계좌 번호를 받아서, 이체에 필요한 내용들을 공유영역에 올려 표시해줍니다.
	 *  Spring Form태그 방식으로, 사용자의 입력값을 담아올 transferVO를 생성하여 공유영역에 올려줍니다. 
	 */
	@GetMapping("/transfer/{accountNumber}")
	public ModelAndView transferForm(@PathVariable String accountNumber, HttpSession session) {
		
		ModelAndView mav = new ModelAndView("transfer/transfer");
		
		TransferVO transferVO = new TransferVO();
		mav.addObject("transferVO", transferVO);
		
		// 해당 계좌의 정보
		DepositAccountVO depositAccount = depositAccountService.getDepositAccountInfo(accountNumber);
		mav.addObject("depositAccount",depositAccount);
		
		// 즐겨찾기 목록
		MemberVO loginVO = (MemberVO)session.getAttribute("loginVO");
		String id = loginVO.getId();
		List<FavoriteVO> favoriteList = favoriteService.getFavoriteList(id);
		mav.addObject("favoriteList", favoriteList);

		
		return mav;
	}
	
	// 이체하기
	/**
	 *   이체하기. (1. 이체    2. 최근이체목록에 등록) 두 가지의 작업이 수행되므로 트랜잭션 처리를 해줘야합니다.   
	 */
	@Transactional
	@PostMapping("/transfer/{accountNumber}")	
	public String transfer(@Valid TransferVO transferVO, BindingResult result, HttpSession session) {
		
		if(result.hasErrors()) {
			System.out.println("이체오류 발생...");
			return "transfer/transferFail";
		}
		
		// 상대방 이름 가져오기. 이체 내역에 상대방 이름 찍혀야 하므로, 해당 계좌의 주인 이름 가져옴
		String toName = transferService.accountOwner(transferVO.getToAccountNumber());
		transferVO.setToName(toName);
		
		// 이체. 내 이름 넣어서 vo 보냄
		MemberVO loginVO = (MemberVO)session.getAttribute("loginVO");
		String myName = loginVO.getName();
		transferVO.setMyName(myName);
		
		transferService.transfer(transferVO);
		
		// 이체 시, 최근 이체 목록 테이블에 들어감
		FavoriteVO favoriteVO = new FavoriteVO();
		favoriteVO.setId(loginVO.getId());
		favoriteVO.setToAccountNumber(transferVO.getToAccountNumber());
		favoriteVO.setToName(toName);
		favoriteService.addFavorite(favoriteVO);
		
		return "transfer/transferSuccess";
	}
	
	

	/**
	 *  예약 이체 설정하기.(적금 계좌에서 예약이체 설정한 경우) (수행은 아래 스케쥴러 어노테이션에서함!)
	 *  여러 작업이 수행되므로 트랜잭션 처리를 해줍니다.
	 *  1. 예약 이체 Flag 값을  N에서 Y로 바꿔줍니다.
	 *  2. 예약 이체 테이블에 이체 정보를 저장합니다. -> 이후에 일정 시간에 이체가 수행됩니다.
	 */
	@Transactional
	@GetMapping("/autoTransferConfirm/{accountNumber}")
	public ModelAndView autoTransferConfirm(@PathVariable String accountNumber, HttpSession session) {
		
		ModelAndView mav = new ModelAndView("transfer/autoTransferFinish");
		
		// 해당 계좌의 정보 업데이트
		SavingsAccountVO savingsAccount = savingsAccountService.getSavingsAccountInfo(accountNumber);
		savingsAccount.setAutoSavingBool("Y");
		savingsAccountService.changeBool(savingsAccount);
		
		
		// autoTransfer DB에 내용 저장하기
		TransferVO transferVO = new TransferVO();
		transferVO.setAccountNumber(savingsAccount.getAutoSaving());	   // 출금 계좌
		transferVO.setToAccountNumber(savingsAccount.getAccountNumber());  // 내 적금 계좌
		transferVO.setToAmount(savingsAccount.getAvgAmount());             // 계산된 평균 금액 송금 됨
		transferVO.setToType("2");	 									   // 이체 타입(이체)
		transferVO.setAutoTransDay(savingsAccount.getSavingDay()); 		   // 예약 이체 날짜
		
		MemberVO loginVO = (MemberVO)session.getAttribute("loginVO");
		String myName = loginVO.getName();
		transferVO.setToName(myName);								       // 받는사람 이름(적금 계좌 )
		transferVO.setMyName(myName);								       // 보내는 사람 이름 

		
		transferService.insertAutoTransfer1(transferVO);
		
		return mav;
	}
	
	
	
	
	/**
	 *  입출금 계좌에서 예약이체하는 Form페이지.(입출금계좌이체에서)
	 * 
	 */
	@GetMapping("/autoTransferToDeposit/{accountNumber}")
	public ModelAndView autoTransferForm(@PathVariable String accountNumber, HttpSession session) {
		
		ModelAndView mav = new ModelAndView("transfer/autoTransfer");
		
		TransferVO transferVO = new TransferVO();
		mav.addObject("transferVO", transferVO);
		
		// 해당 계좌의 정보
		DepositAccountVO depositAccount = depositAccountService.getDepositAccountInfo(accountNumber);
		mav.addObject("depositAccount",depositAccount);
		
		// 즐겨찾기 목록
		MemberVO loginVO = (MemberVO)session.getAttribute("loginVO");
		String id = loginVO.getId();
		List<FavoriteVO> favoriteList = favoriteService.getFavoriteList(id);
		mav.addObject("favoriteList", favoriteList);

		return mav;
	}
	
	/**
	 *   입출금 계좌에서 예약이체
	 */
	@Transactional
	@PostMapping("/autoTransferToDeposit/{accountNumber}")
	public ModelAndView autoTransferToDeposit(TransferVO transferVO, HttpSession session) {
		ModelAndView mav = new ModelAndView("transfer/autoTransferFinish");
		
		// 상대방 이름 가져오기. 이체 내역에 상대방 이름 찍혀야 하므로, 해당 계좌의 주인 이름 가져옴
		String toName = transferService.accountOwner(transferVO.getToAccountNumber());
		transferVO.setToName(toName);

		// 내 이름 넣기
		MemberVO loginVO = (MemberVO)session.getAttribute("loginVO");
		String myName = loginVO.getName();
		transferVO.setMyName(myName);		
		
		transferService.insertAutoTransfer1(transferVO);
		
		return mav;
	}
	
	

	/**
	 *  자동 이체 실행. 
	 *  자동 이체 테이블의 정보로 이체함.
	 *  매일 12시 시행. 오늘 날짜와 예약이체 날이 같으면 이체 실행됨
	 */
	@Scheduled(cron = "0 0 0 * * *")
	public void autoTransfer() {
		
		transferService.autoTransfer1();
	}
	
	

	/**
	 *  예약 이체 해지
	 *  예약 이체 테이블에서 삭제.
	 *  예약 이체 Flag Y에서 N.
	 */
	@GetMapping("/autoTransferDelete/{accountNumber}")
	public ModelAndView autoTransferDelete(@PathVariable String accountNumber) {
		transferService.autoTransferDelete(accountNumber);
		
		ModelAndView mav = new ModelAndView("redirect:/account");
		return mav;
	}
	
	
	
	//card////////////////////////////////////////////////////////////////////////////////////////////
	/**
	 *  카드리더기 form 페이지.
	 */
	@GetMapping("/card")
	public ModelAndView cardForm(HttpSession session) {
		
		ModelAndView mav = new ModelAndView("card/card");
		
		TransferVO transferVO = new TransferVO();
		mav.addObject("transferVO", transferVO);
	
		return mav;
	}
	

	/**
	 *   결제하기 
	 *   1. 카드번호로 계좌번호 가져오기        2. 결제하기(이체)  
	 */

	@PostMapping("/card")	
	public String card(TransferVO transferVO) {
	
		transferService.payment(transferVO);

		return "redirect:/card";
	}
	
	
	
	/**
	 *   인증 키 생성
	 */
	@ResponseBody
	@GetMapping("/otp")
	public ModelAndView otp(HttpSession session) {
		
		ModelAndView mav = new ModelAndView();
		
		byte[] buffer = new byte[5 + 5 * 5];
		new Random().nextBytes(buffer);
		
		Base32 codec = new Base32();
		byte[] secretKey = Arrays.copyOf(buffer, 5);
		byte[] bEncodeKey = codec.encode(secretKey);
		
		//생성된 Key
		String encodedKey = new String(bEncodeKey);
		System.out.println("encodedKey : " + encodedKey);
		
		String url = getQRBarcodeURL("hj", "company.com", encodedKey);
		System.out.println("URL : " + url);
		
		mav.addObject(encodedKey);
		mav.addObject(url);
		
		return mav;
	}
	
	public static String getQRBarcodeURL(String user, String host, String secret) {
		String format = "http://chart.apis.google.com/chart?cht=qr&amp;chs=300x300&amp;chl=otpauth://totp/%s@%s%%3Fsecret%%3D%s&amp;chld=H|0";
        
        return String.format(format, user, host, secret);
	}
}
