package kr.ac.kopo.account.controller;


import java.util.Date;
import java.util.List;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import kr.ac.kopo.account.service.DepositAccountService;
import kr.ac.kopo.account.service.DepositDetailService;
import kr.ac.kopo.account.service.SavingsAccountService;
import kr.ac.kopo.account.vo.DepositAccountVO;
import kr.ac.kopo.account.vo.DepositDetailVO;
import kr.ac.kopo.account.vo.SavingsAccountVO;
import kr.ac.kopo.challenge.service.ChallengeService;
import kr.ac.kopo.eda.vo.EmailVO;
import kr.ac.kopo.member.service.MemberService;
import kr.ac.kopo.member.vo.MemberVO;


@Controller
public class AccountController { 

	@Autowired
	private DepositAccountService depositAccountService;
	@Autowired
	private SavingsAccountService savingsAccountService;
	@Autowired
	private MemberService memberService;
	@Autowired
	private DepositDetailService depositDetailService;
	@Autowired
	private ChallengeService challengeService;
	@Autowired
	private JavaMailSenderImpl mailSender;
	
	
	
	@RequestMapping("/account")
	public ModelAndView accountList(HttpSession session) throws Exception {
		
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
		
		
		// 총자산
		int totalBalance = depositTotalBalance + savingsTotalBalance + loginVO.getCash();
		loginVO.setTotalBalance(totalBalance);
		mav.addObject("totalBalance", loginVO.getTotalBalance());
		// (총자산 - 저번달 총 자산) = 이번달 입출금 계좌 입금 내역 + 이번달 적금 계좌 입금 내역 - 이번달 입출금 계좌 출금 내역
		int totalBalanceChange = memberService.totalBalanceChange(id) + loginVO.getCash();
		loginVO.setTotalBalanceChange(totalBalanceChange);
		mav.addObject("totalBalanceChange", totalBalanceChange);
		
		//이번 월
		String month = depositDetailService.month();
		mav.addObject("month", month);
		
		
		//내 나이대가 가장 많이든 입출금 계좌
		String myAgeGroup = loginVO.getAgeGroup();
		String ageGroupDepositAccountBankBook = challengeService.ageGroupDepositAccount(myAgeGroup);
		mav.addObject("ageGroupDepositAccountBankBook", ageGroupDepositAccountBankBook);
		
		//내 직업과 같은 사람들이 가장 많이든 적금 계좌
		String myJob = loginVO.getJobKey();
		String jobSavingsAccountBankBook = challengeService.jobSavingsAccount(myJob);
		mav.addObject("jobSavingsAccountBankBook", jobSavingsAccountBankBook);
		
		
		// 이번달 잦은 지출 
		List<DepositDetailVO> frequentExpenditureList = depositDetailService.frequentExpenditureList(id);
		mav.addObject("frequentExpenditureList", frequentExpenditureList);
		
		// 이번달 최고 지출금 Top3
		List<DepositDetailVO> expenditureTop3List = depositDetailService.expenditureTop3List(id);
		mav.addObject("expenditureTop3List", expenditureTop3List);
		

		
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

	
	// 상품 가입 시 인증번호 메일로 보내기
	@ResponseBody
	@GetMapping("/certificate")
	public String sendCertificateNumber(HttpSession session) throws Exception {
		
		MemberVO loginVO = (MemberVO)session.getAttribute("loginVO");
		String id = loginVO.getId();
		String toMail = loginVO.getEmail();
		
		//인증번호 -> otp
		String code = create();
		
		//제목
		String title = "인증번호입니다.";
		
		//내용, 인증번호 포함
		String content = "";
		content += "하나은행 이체서비스 인증번호입니다. \n";
		content += "인증번호 : " + code + "\n";
		
		EmailVO emailVO = new EmailVO();
		emailVO.setId(id);
		emailVO.setToMail(toMail);
		emailVO.setTitle(title);
		emailVO.setContent(content);
		
		String setFrom = "KOO";
		
		try {
			MimeMessage message = mailSender.createMimeMessage();
			MimeMessageHelper messageHelper = new MimeMessageHelper(message,true, "UTF-8");
			
			messageHelper.setFrom(setFrom);
			messageHelper.setTo(emailVO.getToMail());
			messageHelper.setSubject(emailVO.getTitle());
			messageHelper.setText(emailVO.getContent());
			
			mailSender.send(message);
			
		} catch(Exception e) {
			System.out.println(e);
		}
		
		return code;
	}
	
	
	// otp	
	private static final long DISTANCE = 30000; 
	private static final String ALGORITHM = "HmacSHA1";
	private static final byte[] SECRET_KEY = "define your secret key here".getBytes();

	private static long create(long time) throws Exception {
		byte[] data = new byte[8];
		
		long value = time;
		for (int i = 8; i-- > 0; value >>>= 8) {
			data[i] = (byte) value;
		}
	 
		Mac mac = Mac.getInstance(ALGORITHM);
		mac.init(new SecretKeySpec(SECRET_KEY, ALGORITHM));
	 
		byte[] hash = mac.doFinal(data);
		int offset = hash[20 - 1] & 0xF;
	 
		long truncatedHash = 0;
		for (int i = 0; i < 4; ++i) {
			truncatedHash <<= 8;
			truncatedHash |= hash[offset + i] & 0xFF;
		}
	 
		truncatedHash &= 0x7FFFFFFF;
		truncatedHash %= 1000000;
	 
		return truncatedHash;
	}
	
	public static String create() throws Exception {
		return String.format("%06d", create(new Date().getTime() / DISTANCE));
	}

	public static boolean vertify(String code) throws Exception {
		return create().equals(code);
	}
	
}
