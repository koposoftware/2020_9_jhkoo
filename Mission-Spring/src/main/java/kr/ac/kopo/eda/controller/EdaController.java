package kr.ac.kopo.eda.controller;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.List;

import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.rosuda.REngine.REXP;
import org.rosuda.REngine.REXPMismatchException;
import org.rosuda.REngine.REngineException;
import org.rosuda.REngine.Rserve.RConnection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
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
	public ModelAndView eda(HttpSession session) throws Exception{
		
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
		System.out.println(mailServiceBool);
		
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
		str2 += "[ '', ''],";
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
		content += "총 지출은 " + totalThisMonth + "원입니다. \n";
		
		// 메인계좌번호 
		String accountNumber = depositAccountService.getMainAccountNumber(id);
		
		// 가장 많은 지출한 카테고리
		String biggestCategory = edaService.biggestCategory(accountNumber);
		content += "가장 많이 지출한 카테고리는 '" + biggestCategory + "'입니다. \n";
		
		for(EdaVO vo:amountByTypeList) {
			content += vo.getCategory() +"에 " + vo.getTotalAmountByType() + "원 지출하였습니다. \n";
		}
		
		
		// 최근 3개월 수입, 지출액 
		List<Integer> depositByLast3Month = edaService.depositByLast3Month(accountNumber);
		List<Integer> withdrawByLast3Month = edaService.withdrawByLast3Month(accountNumber);
		// 이번달 주별 지출액
		List<DepositDetailVO> expenditureByWeekList = depositDetailService.expenditureByWeekList(accountNumber);
			
		content += "이번 달 수입은 " + depositByLast3Month.get(0) + "원이고, 지출은 " + withdrawByLast3Month.get(0) + "입니다. \n";
		content += "\n";
		
		content += "주별 지출액은 다음과 같습니다.";
		for(DepositDetailVO vo:expenditureByWeekList) {
			content += vo.getWeek() - 35 + "주차에 " + vo.getSumAmount() + "원 지출하였습니다. \n";
		}
		
		content += "예측된 3개월 지출액이 '위험수준'입니다. 다음 달에는 반드시 지출을 잘 관리해주세요!";
		
		
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
	@Scheduled(cron = "0 31 18 * * *")
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
	
	
	// R
	@RequestMapping("/R")
	public ModelAndView rExcercise(HttpServletRequest request) throws REngineException, REXPMismatchException{
		ModelAndView mav = new ModelAndView("eda/R");

		RConnection r = null;
		System.out.println(1);
		try {
			r = new RConnection();

			r.eval("library('RJDBC')");
			r.eval("library('dplyr')");
			r.eval("library('ggplot2')");
			r.eval("library('reshape')");
			
			System.out.println(2);
			
			r.eval("jdbc_driver <- JDBC('oracle.jdbc.OracleDriver', classPath = 'C:/Users/rn926/eclipse-webWorkspace/webWorkspace/wtpwebapps/Test2-MVC/WEB-INF/lib/ojdbc8.jar')");
//			r.eval("conn <- dbConnect(jdbc_driver, 'jdbc:oracle:thin:@//192.168.11.1:1521/xe', 'jaehee', '1111')");
			r.eval("conn <- dbConnect(jdbc_driver, 'jdbc:oracle:thin:@//localhost/xe', 'jaehee', '1111')");
			r.eval("query <- \"select to_char(log_date, 'yy/mm') as month, sum(amount) as amount from dw_card_log where log_type_key != '1' and account_number = '111-111111-11111' group by to_char(log_date, 'yy/mm') order by month\"");
			r.eval("dw_account_log <- dbGetQuery(conn, query)");
			r.eval("amount <- dw_account_log %>% select(AMOUNT)");
			
			r.eval("ts_object <- ts(amount, frequency = 12, start = c(17,9))");
			r.eval("hw_object<-HoltWinters(ts_object)");
			r.eval("forecast<-predict(hw_object,  n.ahead=3,  prediction.interval=T,  level=0.95)");
			r.eval("for_values<-data.frame(time=round(time(forecast),  3),  value_forecast=as.data.frame(forecast)$fit,  dev=as.data.frame(forecast)$upr-as.data.frame(forecast)$fit)");
			r.eval("fitted_values<-data.frame(time=round(time(hw_object$fitted),  3),  value_fitted=as.data.frame(hw_object$fitted)$xhat)");
			r.eval("actual_values<-data.frame(time=round(time(hw_object$x),  3),  Actual=c(hw_object$x))");
			r.eval("graphset<-merge(actual_values,  fitted_values,  by='time',  all=TRUE)");
			r.eval("graphset<-merge(graphset,  for_values,  all=TRUE,  by='time')");
			r.eval("graphset[is.na(graphset$dev),  ]$dev<-0");
			r.eval("graphset$Fitted<-c(rep(NA,  NROW(graphset)-(NROW(for_values) + NROW(fitted_values))),  fitted_values$value_fitted,  for_values$value_forecast)");
			r.eval("graphset.melt<-melt(graphset[, c('time', 'Actual', 'Fitted')], id='time')");
			
			System.out.println(3);
			
			r.eval("p <- ggplot(graphset.melt,  aes(x=time,  y=value)) + geom_ribbon(data=graphset, aes(x=time, y=Fitted, ymin=Fitted-dev,  ymax=Fitted + dev),  alpha=.2,  fill='green') + geom_line(aes(colour=variable), size=1) + geom_vline(xintercept = max(actual_values$time), lty=2) + xlab('년도') + ylab('지출액') +  scale_colour_hue('') +scale_y_continuous(labels = scales::comma)");
			r.eval("ggsave('C:/Users/rn926/spring-workspace/Mission-Spring2/src/main/webapp/resources/assets/images/R/p.jpg')");
			
			System.out.println(4);
			
			r.close();
			
		} catch(Exception e) {
			System.out.println(e);
		}
		
		return mav;
	}

	 	
	
	
}
