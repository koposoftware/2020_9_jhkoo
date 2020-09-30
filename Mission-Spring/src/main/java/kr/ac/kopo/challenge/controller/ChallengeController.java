package kr.ac.kopo.challenge.controller;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import kr.ac.kopo.account.service.DepositAccountService;
import kr.ac.kopo.account.service.DepositDetailService;
import kr.ac.kopo.challenge.service.ChallengeService;
import kr.ac.kopo.challenge.vo.ChallengeVO;
import kr.ac.kopo.member.vo.MemberVO;

@Controller
public class ChallengeController {

	@Autowired
	private ChallengeService challengeService;
	@Autowired
	private DepositAccountService depositAccountService;
	@Autowired
	private DepositDetailService depositDetailService;
	
	// 도전 선택
	@RequestMapping("/challengeChoose")
	public ModelAndView transferChoose(HttpSession session) {
		ModelAndView mav = new ModelAndView("challenge/challenge");
		
		// session에 등록된 id 
		MemberVO loginVO = (MemberVO)session.getAttribute("loginVO");
		String id = loginVO.getId();
				
		//내가 도전하고 있는 도전 이름 -> 내가 도전 중인 것은 선택 view에 안뜸
		List<String> myChallengeNameList = challengeService.myChallengeName(id);
		mav.addObject("myChallengeNameList", myChallengeNameList);

		//이번 월
		String month = depositDetailService.month();
		mav.addObject("month", month);
		
		
		// 도전 진행 정보 //	
		
		// 내가 진행 중인 도전 정보
		List<ChallengeVO> myChallenge = challengeService.myChallenge(id);
		
		// 도전 실패 여부 판단 VO에 저장
		challengeService.challengeJudge(id);
				
		mav.addObject("myChallenge", myChallenge);

		return mav;
	}
	
	// 도전 form
	@GetMapping("/challenge/{challengeName}")
	public ModelAndView challengeForm(@PathVariable String challengeName, HttpSession session) {
		ModelAndView mav = new ModelAndView("challenge/challengeForm");
		ChallengeVO challengeVO = new ChallengeVO();
		
		// 내역과 연결할 type 설정
		String challengeType= challengeName.substring(0,2);
		if(challengeType.equals("식비")) {
			challengeType = "3";
		} else if(challengeType.equals("카페")) {
			challengeType = "4";
		} else if(challengeType.equals("편의")) {
			challengeType = "5";
		} else if(challengeType.equals("미용")) {
			challengeType = "9";
		} else if(challengeType.equals("쇼핑")) {
			challengeType = "7";
		} else {
			challengeType = "6";
		}
		MemberVO loginVO = (MemberVO)session.getAttribute("loginVO");
		String id = loginVO.getId();
		
		challengeVO.setChallengeType(challengeType);
		challengeVO.setId(id);
		
		int lastMonthSumByCatesgory = depositAccountService.lastMonthSumByCategory(challengeVO);
		
		mav.addObject("lastMonthSumByCatesgory", lastMonthSumByCatesgory);
		mav.addObject("challengeName", challengeName);
		
		
		return mav;
	}
	
	// 도전 시작. 도전 테이블에 insert
	@PostMapping("/challengeProcess")
	public ModelAndView challengeProcess(@Valid ChallengeVO challengeVO, HttpSession session) {
		ModelAndView mav = new ModelAndView("redirect:/challengeChoose");
		
		
		
		// 도전자의 id 담기
		MemberVO loginVO = (MemberVO)session.getAttribute("loginVO");
		String id = loginVO.getId();
		challengeVO.setId(id);
		
		challengeService.insertMyChallenge(challengeVO);
		
		return mav;
	}
	
	
	// 도전 테이블 매월 1일 갱신
	@Scheduled(cron = "0 0 0 1 * *")
	public void challengeDelete() {
		challengeService.challengeDelete();
	}
}
