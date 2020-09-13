package kr.ac.kopo.eda.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
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
}
