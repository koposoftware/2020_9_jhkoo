package kr.ac.kopo.account.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import kr.ac.kopo.account.service.SavingsAccountService;
import kr.ac.kopo.account.service.SavingsDetailService;
import kr.ac.kopo.account.vo.SavingsAccountVO;
import kr.ac.kopo.account.vo.SavingsDetailVO;


@Controller
public class SavingsDetailController {

	@Autowired
	private SavingsDetailService savingsDetailService;
	@Autowired
	private SavingsAccountService savingsAccountService;
	
	// 적금 내역  
	@RequestMapping("/savingsDetail/{accountNumber}")
	public ModelAndView savingsDetail(@PathVariable("accountNumber") String accountNumber) {
		
		ModelAndView mav = new ModelAndView("account/savingsDetail");
		
		// 내역 리스트
		List<SavingsDetailVO> savingsDetailList = savingsDetailService.savingsDetailList(accountNumber);
		mav.addObject("savingsDetailList", savingsDetailList);
		
		// 해당 적금 계좌 정보.
		SavingsAccountVO savingsAccount = savingsAccountService.getSavingsAccountInfo(accountNumber);
		
		// 현재부터 만기일 까지 남은 개월 수 
	//	int savingMonth = savingsAccountService.getSavingMonth(accountNumber);
	//	savingsAccount.setSavingMonth(savingMonth);	

		mav.addObject("savingsAccount",savingsAccount);
		
		// 차트 그리기 위한 작업. 데이터 JSON형태에 맞춰 보내주기
		String str = "[";
		
		int num = 0;
		for(SavingsDetailVO vo:savingsDetailList) {
			
			str += "['";
			str += vo.getLogDate().substring(2,10);
			str += "', ";
			str += vo.getAmount();
			str += " ]";
			
			num ++;
			if(num<savingsDetailList.size()) {
				str += ",";
			}
		}
		str += "]";
		
		
		mav.addObject("str", str);
		
		return mav;
	}
}
