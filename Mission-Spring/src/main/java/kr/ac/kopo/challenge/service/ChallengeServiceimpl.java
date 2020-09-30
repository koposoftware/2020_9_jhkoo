package kr.ac.kopo.challenge.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.ac.kopo.account.dao.DepositAccountDAO;
import kr.ac.kopo.account.dao.DepositDetailDAO;
import kr.ac.kopo.account.vo.DepositDetailVO;
import kr.ac.kopo.challenge.dao.ChallengeDAO;
import kr.ac.kopo.challenge.vo.ChallengeVO;



@Service
public class ChallengeServiceimpl implements ChallengeService {

	@Autowired
	private ChallengeDAO challengeDAO;
	@Autowired
	private DepositAccountDAO depositAccountDAO;
	@Autowired
	private DepositDetailDAO depositDetailDAO;


	@Override
	public List<ChallengeVO> myChallenge(String id) {
		List<ChallengeVO> myChallenge = challengeDAO.myChallenge(id);
		
		// 오늘 날짜(일) 가져오기
		SimpleDateFormat format = new SimpleDateFormat ( "dd");
		Date time = new Date();
		String stringDay = format.format(time);
		int today = Integer.parseInt(stringDay);
		
		
		// 메인계좌의 목표 카테고리의 현재 달 사용 금액 가져오기
		for(ChallengeVO c:myChallenge) {
			String logTypeKey = c.getChallengeType();
			String myId = c.getId();

			// 메인 계좌 번호
			String accountNumber = depositAccountDAO.getMainAccountNumber(myId);
			
			// depositDetailVO에 id, 카테고리타입, 메인 계좌 번호 담아서 
			// 메인 계좌의 이번달 카테고리 내역 총합 가져오고, challengeVO에 저장
			DepositDetailVO depositDetailVO = new DepositDetailVO();
			depositDetailVO.setLogTypeKey(logTypeKey);
			depositDetailVO.setAccountNumber(accountNumber);
			int nowBalanceByType = depositDetailDAO.nowBalanceByType(depositDetailVO);
			
			// 가져온 타입별 이번달 총사용 금액을 challenge객체에 저장
			c.setNowBalanceByType(nowBalanceByType);
			
			// 도전 성공 가능 금액 계산 후 vo에 저장
			String stringEndDay = c.getChallengeEndDate().substring(10,12);
			int endDay = Integer.parseInt(stringEndDay);
			int expectedAmount = (c.getTargetAmount() - c.getNowBalanceByType()) / (endDay - today + 1);
			c.setExpectedAmount(expectedAmount);
			
			// 도전 실패 여부 판단 후 vo에 저장
			if(c.getNowBalanceByType() > c.getTargetAmount()) {
				// 실패 표시
				c.setChallengeFail(true);
			}
		}
		
		return myChallenge;
	}

	@Override
	public List<String> myChallengeName(String id) {
		//나의 도전 정보
		List<ChallengeVO> myChallenge = challengeDAO.myChallenge(id);
		// 도전 이름만 추출
		List<String> myChallengeName = new ArrayList<>();
		for(ChallengeVO challenge:myChallenge) {
			myChallengeName.add(challenge.getChallengeName());
		}
		
		return myChallengeName;
	}

	@Override
	public void insertMyChallenge(ChallengeVO challengeVO) {
		challengeDAO.insertMyChallenge(challengeVO);
	}

	
	
	@Override
	public String getMainAccountNumber(String id) {
		String mainAccountNumber = depositAccountDAO.getMainAccountNumber(id);
		return mainAccountNumber;
	}

	@Override
	public void challengeJudge(String id) {
		
		// 1. 도전 정보 가져오기
		List<ChallengeVO> challengeInfo = challengeDAO.myChallenge(id);
		
		// 2. 메인 계좌번호 가져오기
		// String mainAccountNumber = depositAccountDAO.getMainAccountNumber(id);
		
		// 3. 도전 완료 여부 판단. 도전 실패 시 벌금 이체
		for(ChallengeVO c:challengeInfo) {
			if(c.getNowBalanceByType() > c.getTargetAmount()) {
				
				// 실패 표시
				c.setChallengeFail(true);
				
				
				/*
				// 3. 목표금액보다 더 많이 지출했다면, 메인계좌에서 도전 벌금 모금함으로 만원 이체
				TransferVO transferVO = new TransferVO();
				transferVO.setAccountNumber(mainAccountNumber);
				transferVO.setToAccountNumber("100-000000-00020"); // 벌금 모음 전용 계좌
				transferVO.setToAmount(10000); //벌금 1만원
				transferVO.setToType("2");
				transferVO.setToName("도전 벌금 모금함");
				transferVO.setMyName("구재희");	// 여기 고치기!!!
				
				//  1. 내 계좌에 이체 내역 남기기. 	
				transferDAO.addMyLog(transferVO);
				
				//  2. 상대 계좌에 입금 내역 남기기. 
				transferDAO.addYourLog(transferVO);
				
				//  3. 내 계좌 잔액 조정하기
				transferDAO.withdrawal(transferVO);
				
				//  4. 상대 계좌 잔액 조정하기          내 이름, 상대 이름
				transferDAO.deposit(transferVO);
				*/
			}
		}
	}

	@Override
	public void challengeDelete() {
		challengeDAO.challengeDelete();
		
	}

	@Override
	public String ageGroupDepositAccount(String myAgeGroup) {
		String ageGroupDepositAccount = challengeDAO.ageGroupDepositAccount(myAgeGroup);
		return ageGroupDepositAccount;
	}

	@Override
	public String jobSavingsAccount(String myJob) {
		String jobSavingsAccount = challengeDAO.jobSavingsAccount(myJob);
		return jobSavingsAccount;
	}
	
	
	
	

}
