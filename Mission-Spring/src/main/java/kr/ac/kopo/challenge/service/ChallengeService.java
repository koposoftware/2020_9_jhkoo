package kr.ac.kopo.challenge.service;

import java.util.List;

import kr.ac.kopo.challenge.vo.ChallengeVO;

public interface ChallengeService {

	
	/**
	 * 내 도전 리스트 가져오기
	 */
	List<ChallengeVO> myChallenge(String id);
	

	/**
	 * 내 도전 리스트명 가져오기
	 */
	List<String> myChallengeName(String id);
	
	/**
	 * 내 도전 테이블에 insert
	 */
	void insertMyChallenge(ChallengeVO challengeVO);
	
	/**
	 * 메인 계좌번호 가져오기. 도전 실패 시 메인 계좌에서 돈 빼기 위해!
	 */
	String getMainAccountNumber(String id);
	
	
	
	/**
	 *  도전 완료 여부 판단 정보 VO에 담음. 도전 실패 시 메인계좌에서 1만원 차감(기부)
	 *  이후 도전 새롭게 갱신
	 */
	void challengeJudge(String id);
	
	/**
	 *  도전 갱신
	 */
	void challengeDelete();
	
	
	/**
	 *  내 나이대가 가장 많이든 입출금 통장명
	 */
	String ageGroupDepositAccount(String myAgeGroup);
	
	/**
	 *  내 직업과 같은 사람들이 가장 많이든 적금 통장명
	 */
	String jobSavingsAccount(String myJob);
	
}
