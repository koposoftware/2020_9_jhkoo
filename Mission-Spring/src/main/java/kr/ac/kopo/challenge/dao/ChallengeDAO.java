package kr.ac.kopo.challenge.dao;

import java.util.List;

import kr.ac.kopo.challenge.vo.ChallengeVO;

public interface ChallengeDAO {

	// 내 도전 정보 가져오기
	public List<ChallengeVO> myChallenge(String id);
	
	// 내 도전 정보 insert
	public void insertMyChallenge(ChallengeVO challengeVO);
	
	// 도전 갱신
	public void challengeDelete();
	
	// 내 나이대 가장 많이 이용하는 입출금 통장 명
	public String ageGroupDepositAccount(String myAgeGroup);
	
	// 내 직업과 같은 사람들 중에서 가장 많이 이용하는 적금 통장 명
	public String jobSavingsAccount(String myJob);
}
