package kr.ac.kopo.eda.controller;

import java.util.List;

import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import kr.ac.kopo.account.service.DepositAccountService;
import kr.ac.kopo.account.service.DepositDetailService;
import kr.ac.kopo.account.vo.DepositDetailVO;
import kr.ac.kopo.challenge.service.ChallengeService;
import kr.ac.kopo.eda.service.EdaService;
import kr.ac.kopo.eda.vo.EdaVO;
import kr.ac.kopo.eda.vo.EmailVO;
import kr.ac.kopo.member.vo.MemberVO;

@Controller
public class EdaController {
	
	@Autowired
	private EdaService edaService;
	@Autowired
	private DepositDetailService depositDetailService;
	@Autowired
	private ChallengeService challengeService;
	@Autowired
	private DepositAccountService depositAccountService;
	@Autowired
	private JavaMailSenderImpl mailSender;

	
	/**
	 *   분석 페이지.
	 */
	@RequestMapping("/eda")
	public ModelAndView eda(HttpSession session) {
		
		ModelAndView mav = new ModelAndView("eda/eda");
		
		// id 
		MemberVO loginVO = (MemberVO)session.getAttribute("loginVO");
		String id = loginVO.getId();
		
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
		
		// 이번달 카테고리별 지출액 
		List<EdaVO> amountByTypeList = edaService.amountByType(id);
		mav.addObject("amountByTypeList", amountByTypeList);
		// 이번달 총 지출액
		mav.addObject("totalThisMonth", amountByTypeList.get(0).getTotalThisMonth());
		
		
		// 메인계좌번호 
		String accountNumber = depositAccountService.getMainAccountNumber(id);
				
		// 이번달 가장 지출 많은 카테고리
		String biggestCategory = edaService.biggestCategory(accountNumber);
		mav.addObject("biggestCategory",biggestCategory);
		
		// 가장 지출이 많은 카테고리 그래프 그리기 위한 작업(전 월부터 지금까지)
		DepositDetailVO depositDetailVO = new DepositDetailVO();
		depositDetailVO.setAccountNumber(accountNumber);
		depositDetailVO.setLogTypeKey(biggestCategory);
		List<DepositDetailVO> detailListByCotegory2 = edaService.detailListByCotegory2(depositDetailVO);
		
		int num = 0;
		String str = "[";
		str += "[ '', ''],";

		for(DepositDetailVO vo:detailListByCotegory2) {
			str += "['";
			str += vo.getLogDate();
			str += "', ";
			str += vo.getSumAmount();
			str += "]";
			
			num ++;
			if(num < detailListByCotegory2.size()) {
				str += ",";
			}
		}
		str += "]";
		mav.addObject("str",str);
		
		// 이메일 서비스 구독 여부
		int mailServiceBool = edaService.mailServiceBool(id);
		mav.addObject("mailServiceBool", mailServiceBool);
		
		
		// 최근 3개월 수입, 지출액 
		List<Integer> depositByLast3Month = edaService.depositByLast3Month(accountNumber);
		List<Integer> withdrawByLast3Month = edaService.withdrawByLast3Month(accountNumber);
		mav.addObject("depositByLast3Month", depositByLast3Month);
		mav.addObject("withdrawByLast3Month", withdrawByLast3Month);
		
		
		
		// 이번달 주별 지출액
		List<DepositDetailVO> expenditureByWeekList = depositDetailService.expenditureByWeekList(accountNumber);
		mav.addObject("expenditureByWeekList", expenditureByWeekList);
		
		// 주 평균 지출액
		int avgExpenditureByWeek = 0;
		for(DepositDetailVO vo:expenditureByWeekList) {
			avgExpenditureByWeek += vo.getSumAmount();
		}

		mav.addObject("avgExpenditureByWeek", (int)Math.ceil((double)avgExpenditureByWeek / expenditureByWeekList.size()) ); 
		
		// 주별 그래프 위한 작업
		String str2 = "[";
		int num2 = 0;
		for(DepositDetailVO vo:expenditureByWeekList) {
			str2 += "['";
			str2 += Integer.toString(vo.getWeek() - 35) + "주차";
			str2 += "', ";
			str2 += vo.getSumAmount();
			str2 += "]";
			
			num2 ++;
			if(num2 < expenditureByWeekList.size()) {
				str2 += ",";
			}
		}
		str2 += "]";
		System.out.println(str2);
		mav.addObject("str2",str2);
		
		
		return mav;
	}

	
	/**
	 *  이번달 잦은 지출 목록 ajax
	 *  선택된 이번달 잦은 지출 정보(날짜, 금액) return
	 */

	@ResponseBody
	@GetMapping("/frequentDetail")
	public List<DepositDetailVO> frequentDetail(DepositDetailVO depositDetailVO) {
		
		// 받아온 accountNumber, toName으로, 이 지출에 대한 목록(날짜, toName)가져오기
		List<DepositDetailVO> frequentDetailList = depositDetailService.getFrequentDetail(depositDetailVO);
		
		return frequentDetailList;
	}	
	
	/**
	 *  카테고리별 이번 달 일별 지출액. ajax
	 */
	@ResponseBody
	@GetMapping("/categoryDetail")
	public List<DepositDetailVO> categoryDetail(String logTypeKey, HttpSession session) {
		
		// 메인계좌번호 
		MemberVO loginVO = (MemberVO)session.getAttribute("loginVO");
		String id = loginVO.getId();		
		String accountNumber = depositAccountService.getMainAccountNumber(id);
		
		// VO에 카테고리, 계좌번호 담아서 
		// 이번 달 해당 카테고리 내역 가져오기
		DepositDetailVO depositDetailVO = new DepositDetailVO();
		depositDetailVO.setLogTypeKey(logTypeKey);
		depositDetailVO.setAccountNumber(accountNumber);
		List<DepositDetailVO> detailListByCotegory = edaService.detailListByCotegory(depositDetailVO);
		
		
		return detailListByCotegory;
	}
	
	
	/**
	 *  정기 메일 서비스
	 */	
	@RequestMapping("/addMailService")
	public String addMailService(HttpSession session) {
		//사용자 아이디, 이메일 가져오기
		MemberVO loginVO = (MemberVO)session.getAttribute("loginVO");
		String id = loginVO.getId();
		String toMail = loginVO.getEmail();
		
		//제목
		String title = depositDetailService.month() + "월 " +
		               loginVO.getName() +  "님의 계좌 내역 분석 메일링 서비스입니다.";
		
		//내용
		String content = "";
		content += "잦은 지출 내역입니다. 잦은 지출을 했던 곳을 확인하시고 습관적으로 방문하는 것은 아닌지 생각해 보는 것은 어떨까요? \n";
		// 이번달 잦은 지출 
		List<DepositDetailVO> frequentExpenditureList = depositDetailService.frequentExpenditureList(id);		
		for(DepositDetailVO vo:frequentExpenditureList) {
			// 나와의 거래는 제외, 거래 3회 이상인 경우만 알려줌
			if(!vo.getToName().equals(loginVO.getName()) && vo.getCount() >= 3) {
				content += vo.getToName() + "에서 총 " + vo.getCount() + "번 지출했습니다. \n";
			}
		}
		
		content += "\n";
		// 지출 Top3
		content += "지출 Top3 내역입니다. 이번 달 지출 금액이 컸던 내역을 확인하세요. 충동적 소비는 경계하시길 바랍니다! \n";
		List<DepositDetailVO> expenditureTop3List = depositDetailService.expenditureTop3List(id);
		for(DepositDetailVO vo:expenditureTop3List) {
			content += vo.getLogDate() + "에 " + vo.getToName() + "에서 " + vo.getAmount() + "원 지출하였습니다 \n";
		}
		content += "\n";
		
		// 이번달 카테고리별 지출액 
		List<EdaVO> amountByTypeList = edaService.amountByType(id);
		// 이번달 총 지출액
		int totalThisMonth = amountByTypeList.get(0).getTotalThisMonth();	
		content += "총 지출은 " + totalThisMonth + "입니다. \n";
		for(EdaVO vo:amountByTypeList) {
			content += vo.getCategory() +"에 " + vo.getTotalAmountByType() + "원 지출하였습니다. \n";
		}
		
		EmailVO emailVO = new EmailVO();
		emailVO.setId(id);
		emailVO.setToMail(toMail);
		emailVO.setTitle(title);
		emailVO.setContent(content);
		
		edaService.addMailService(emailVO);
		
		return "redirect:/eda";
	}
	
	
	
	/**
	 *  정기 메일 서비스 취소
	 */
	@RequestMapping("/deleteMailService")
	public String deleteMailService(HttpSession session) {
		
		MemberVO loginVO = (MemberVO)session.getAttribute("loginVO");
		String id = loginVO.getId();
		
		edaService.deleteMailService(id);
		
		return "redirect:/eda";
	}
	
	/**
	 *  메일 보내기. 매월 말
	 */
//	@Scheduled(cron = "0 0 12 28 * *")
	@Scheduled(cron = "0 6 16 * * *")
	public void sendMail() {
		
		List<EmailVO> emailList = edaService.getMailList();
		String setFrom = "KOO";
		
		try {
			MimeMessage message = mailSender.createMimeMessage();
			MimeMessageHelper messageHelper = new MimeMessageHelper(message,true, "UTF-8");
			
			for(EmailVO vo:emailList) {
				
				messageHelper.setFrom(setFrom);
				messageHelper.setTo(vo.getToMail());
				messageHelper.setSubject(vo.getTitle());
				messageHelper.setText(vo.getContent());
				
				mailSender.send(message);
			}
		} catch(Exception e) {
			System.out.println(e);
		}
		
	}
	
	
}
