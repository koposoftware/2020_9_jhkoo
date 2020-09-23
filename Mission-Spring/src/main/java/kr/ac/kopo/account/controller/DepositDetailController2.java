package kr.ac.kopo.account.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import kr.ac.kopo.account.service.DepositAccountService;
import kr.ac.kopo.account.service.DepositDetailService;
import kr.ac.kopo.account.vo.DepositAccountVO;
import kr.ac.kopo.account.vo.DepositDetailVO;
import kr.ac.kopo.util.PagingVO;

@Controller
public class DepositDetailController2 {

	@Autowired
	private DepositDetailService depositDetailService;
	@Autowired
	private DepositAccountService depositAccountService; 
	
	/*
	
	// 이번 달 내역
	@RequestMapping("/depositDetail/{accountNumber}")
	public ModelAndView depositDetail(@PathVariable("accountNumber") String accountNumber,
									PagingVO vo) {
		
		ModelAndView mav = new ModelAndView("account/depositDetail");
		
		// 해당 계좌의 내역 리스트
		List<DepositDetailVO> depositDetailList = depositDetailService.depositDetailList(accountNumber);
		mav.addObject("depositDetailList", depositDetailList);

		// 해당 계좌의 정보
		DepositAccountVO depositAccount = depositAccountService.getDepositAccountInfo(accountNumber);
		mav.addObject("depositAccount",depositAccount);
		
		//이번 달 가져오기
		String month = depositDetailService.month();
		int nowMonth = Integer.parseInt(month);
		mav.addObject("month",nowMonth);
		
		
		// 페이징
		
		return mav;
	}
	
	
	// 이전 내역
	@RequestMapping("/goMonthAgo/{accountNumber}/{month}")
	public ModelAndView depositDetailMonthAgo(@PathVariable("accountNumber") String accountNumber,
											  @PathVariable("month") int month) {
		
		ModelAndView mav = new ModelAndView("account/depositDetailAgo");
		
		// 해당 계좌의 정보
		DepositAccountVO depositAccount = depositAccountService.getDepositAccountInfo(accountNumber);
		mav.addObject("depositAccount",depositAccount);
		
		// month와 계좌번호를 보내서 지난 달 계좌 내역 가져오기
		DepositDetailVO depositDetailVO = new DepositDetailVO();
		String strMonth = Integer.toString(month);
		depositDetailVO.setLogDate(strMonth);
		depositDetailVO.setAccountNumber(accountNumber);
		
		// 지난 달 계좌 내역 가져오기
		List<DepositDetailVO> depositDetailListMonthAgo = depositDetailService.depositDetailListMonthAgo(depositDetailVO);
		mav.addObject("depositDetailListMonthAgo", depositDetailListMonthAgo);
		
		// month 지난 달로 업데이트
		int lastMonth = month - 1;
		mav.addObject("month", lastMonth);
		
		return mav;		
	}
	
	// 이후 내역
	@RequestMapping("/goMonthAhead/{accountNumber}/{month}")
	public ModelAndView depositDetailMonthAhead(@PathVariable("accountNumber") String accountNumber,
											  @PathVariable("month") int month) {
		
		ModelAndView mav = new ModelAndView("account/depositDetailAhead");
		
		// 해당 계좌의 정보
		DepositAccountVO depositAccount = depositAccountService.getDepositAccountInfo(accountNumber);
		mav.addObject("depositAccount",depositAccount);
		
		// month와 계좌번호를 보내서 앞 달 계좌 내역 가져오기
		DepositDetailVO depositDetailVO = new DepositDetailVO();
		// month + 2를 하면 monthAgo 로직 그대로 사용 가능
		int intMonth = month + 2;
		String stringMonth = Integer.toString(intMonth);
		
		depositDetailVO.setLogDate(stringMonth);
		depositDetailVO.setAccountNumber(accountNumber);
		
		// 앞 달 계좌 내역 가져오기
		List<DepositDetailVO> depositDetailListMonthAgo = depositDetailService.depositDetailListMonthAgo(depositDetailVO);
		mav.addObject("depositDetailListMonthAgo", depositDetailListMonthAgo);
		
		// month 앞 달로 업데이트
		int aheadMonth = month + 2;
		mav.addObject("month", aheadMonth);
		
		return mav;		
	}
	
	
	// 카테고리별 내역 
	@RequestMapping("/categoryDetail/{accountNumber}/{month}/{logTypeKey}")
	public ModelAndView depositDetailCategory(@PathVariable("accountNumber") String accountNumber,
											  @PathVariable("month") int month,
											  @PathVariable("logTypeKey") String logTypeKey) {
		
		ModelAndView mav = new ModelAndView("account/depositDetailCategory");
		
		// 해당 계좌의 정보
		DepositAccountVO depositAccount = depositAccountService.getDepositAccountInfo(accountNumber);
		mav.addObject("depositAccount",depositAccount);
		
		// 파라미터로 보낼 vo 생성
		DepositDetailVO depositDetailVO = new DepositDetailVO();
		depositDetailVO.setAccountNumber(accountNumber);
		String nowMonth = Integer.toString(month);
		depositDetailVO.setLogDate(nowMonth);
		depositDetailVO.setLogTypeKey(logTypeKey);
		
		// 해당 계좌의 해당 월, 해당 카테고리의 내역 리스트
		List<DepositDetailVO> depositDetailCategoryList = depositDetailService.depositDetailCategory(depositDetailVO);
		mav.addObject("depositDetailCategoryList", depositDetailCategoryList);
		
		return mav;
	}
	
	
	*/
	
	
	
	
}
