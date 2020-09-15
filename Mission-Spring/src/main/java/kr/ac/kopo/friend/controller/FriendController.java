package kr.ac.kopo.friend.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import kr.ac.kopo.account.service.DepositDetailService;
import kr.ac.kopo.account.vo.DepositDetailVO;
import kr.ac.kopo.challenge.service.ChallengeService;
import kr.ac.kopo.challenge.vo.ChallengeVO;
import kr.ac.kopo.friend.service.FriendService;
import kr.ac.kopo.friend.vo.FriendVO;
import kr.ac.kopo.member.vo.MemberVO;

@Controller
public class FriendController {

	@Autowired
	private FriendService friendService;
	@Autowired
	private DepositDetailService depositDetailService;
	@Autowired
	private ChallengeService challengeService;	
	
	/**
	 *   친구에게 비교 요청 보내는 FORM 페이지.
	 */
	@GetMapping("/friend/sendRequest")
	public ModelAndView sendRequestForm(HttpSession session) {
		
		ModelAndView mav = new ModelAndView("friend/sendRequest");
		
		// 내 아이디
		MemberVO loginVO = (MemberVO)session.getAttribute("loginVO");
		String id = loginVO.getId();
		
		// form태그에서 정보 받아올 VO 생성해 공유영역에 올리기
		FriendVO friendVO = new FriendVO();
		friendVO.setId(id);
		mav.addObject("friendVO", friendVO);
		
		return mav;
	}
	
	/**
	 *   친구에게 비교 요청 보낸 것 DB에 insert
	 */
	@PostMapping("/friend/sendRequest")
	public ModelAndView insertRequest(FriendVO friendVO) {
		ModelAndView mav = new ModelAndView("friend/friend");
		
		// 1. 상대 아이디 존재 유무 확인
		
		// 2. DB에 insert
		friendService.insertRequest(friendVO);
		
		return mav;
	}
	
	////////////////////////////////////////////////////////////////////////////
	
	/**
	 *   내가 보낸 요청, 받은 요청 관리하는 페이지
	 */
	@RequestMapping("/friend/requestManage")
	public ModelAndView getRequest(HttpSession session) {
		
		ModelAndView mav = new ModelAndView("friend/requestManage");
		
		// 내 아이디
		MemberVO loginVO = (MemberVO)session.getAttribute("loginVO");
		String id = loginVO.getId();
		
		// 내가 보낸 요청
		List<FriendVO> requestList = friendService.getRequestList(id);
		mav.addObject("requestList", requestList);
		for(FriendVO f:requestList) {
			System.out.println(f);
		}
		
		// 내가 받은 요청
		List<FriendVO> requestedList = friendService.getRequestedList(id);
		mav.addObject("requestedList", requestedList);
		for(FriendVO f:requestedList) {
			System.out.println(f);
		}
		
		return mav;
	}
	
	
	/**
	 *   친구가 보낸 요청 승인하기
	 *   agreeFlag 0 에서 1로 바꿉니다.
	 */
	@ResponseBody
	@GetMapping("/friend/agreeRequest")
	public void agreeRequest(FriendVO friendVO) {
		friendVO.setAgreeFlag("1");
		friendService.agreeRequest(friendVO);
	}
	
	/**
	 *   친구가 보낸 요청 거절하기
	 *   테이블에서 삭제합니다.
	 */
	@ResponseBody
	@GetMapping("/friend/disAgreeRequest")
	public void disAgreeRequest(FriendVO friendVO) {
		friendService.disAgreeREquest(friendVO);
	}
	
	
	/**
	 *   매월 1일에 도전 초기화
	 */
	@Scheduled(cron = "0 0 0 1 * *")
	public void resetChallenge() {
		friendService.resetChallenge();
	}
		

	//////////////////////////////////////////////////////////////////////////////
	
	
	/**
	 *   내가 받은 요청을 승인했거나, 보낸 요청이 승인 되어진 경우 그래프로 비교하기.
	 */
	@RequestMapping("/friend/compare")
	public ModelAndView compare(HttpSession session) {
		ModelAndView mav = new ModelAndView("friend/compare");
		
		// id, name
		MemberVO loginVO = (MemberVO)session.getAttribute("loginVO");
		String myId = loginVO.getId();
		String myName = loginVO.getName();

		// 나의 요청이 승인된 친구들의 id, 내가 요청을 승락한 친구들의 id 가져오기
		List<String> friendsIdList = friendService.getFriendsIdList(myId);
		
		// 위 친구들의 이름
		List<String> friendsNameList = friendService.getFriendsNameList(myId);
		
		// 친구들 이름 : key
		// 친구들 아이디로, 개인이 소유한 입출금 계좌들의 이번 달 지출 총액 가져와서 map에 담기 : value
		// + 내 정보도 map에 추가
		Map<String, Integer> friendsMoney = new HashMap<String, Integer>();
		
		for(int i = 0; i < friendsIdList.size(); i++) {
			int friendMoney = depositDetailService.getExpenditureThisMonth(friendsIdList.get(i));
			friendsMoney.put(friendsNameList.get(i), friendMoney);
		}
		friendsMoney.put(loginVO.getName(), depositDetailService.getExpenditureThisMonth(myId));

		
		// bar 차트 그리기 위해 json형태의 string 만들기 sortedFriendsMoney
		String str = "";
		int num = 0;
		for(String key: friendsMoney.keySet()) {
			
			str += "['";
			str += key;
			str += "', ";
			str += friendsMoney.get(key);
			str += " ]";
			
			num ++;
			if(num < friendsMoney.size()) {
				str += ",";
			}
		}
		mav.addObject("str", str);
		
		
		//도전 공유///////////////////////////////////////////////
		//이번 월
		String month = depositDetailService.month();
		mav.addObject("month", month);		

		// 도전 진행 정보. 나 + 내 요청을 승인하거나 내가 요청을 승인한 친구들 //	
		List<ChallengeVO> challengeList = new ArrayList<ChallengeVO>();
		
		// 내가 진행 중인 도전 정보VO 가져오고, 도전 실패 여부 판단 후 VO에 담고, 리스트에 저장
		List<ChallengeVO> myChallenge = challengeService.myChallenge(myId);
//		challengeService.challengeJudge(myId);		
//		challengeList.addAll(myChallenge);
		
		// 친구들 도전 정보 같은 방법으로 담기
		for(String friendId:friendsIdList) {
			List<ChallengeVO> friendChallenge = challengeService.myChallenge(friendId);
			challengeService.challengeJudge(friendId);		
			challengeList.addAll(friendChallenge);
		}
		mav.addObject("challengeList",challengeList);
		
		for(ChallengeVO c:challengeList) {
			System.out.println(c);
		}
		
		
		return mav;
	}
	
	

	
}
















