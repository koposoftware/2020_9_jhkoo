package kr.ac.kopo.account.service;

import java.util.List;


import kr.ac.kopo.account.vo.DepositAccountVO;
import kr.ac.kopo.challenge.vo.ChallengeVO;
import kr.ac.kopo.transfer.vo.TransferVO;

public interface DepositAccountService {

	// 보유 입출금 계좌 리스트
	List<DepositAccountVO> selectDepositAccountById(String id);
	
	// 보유 입출금 계좌 총 합
	int depositTotalBalanceById(String id);
	
	// 보유 입출금 계좌 번호 리스트
	List<String> depositAccountNumListById(String id);
	
	// 입출금 자유 계좌 가입
	void insertDepositAccount(DepositAccountVO depositAccountVO);
	
	// 해당 계좌 정보 가져오기
	DepositAccountVO getDepositAccountInfo(String accountNumber);
	
	// 입출금 자유 계좌 정보 수정
	void changeDepositManage(DepositAccountVO depositAccountVO);
	
	
	// 메인 계좌번호 가져오기(서브계좌 탈퇴 시 자동이체 위한 정보)
	String getMainAccountNumber(String id);
	
	// 입출금 서브 계좌 탈퇴. getMainAccountNumber로 메인 계좌 찾고, 그 계좌로 잔액 이체 후, 계좌 삭제
	void deleteDepositAccount(String accountNumber);
	
	
	// 지난 달 선택 카테고리의 총 지출액
	int lastMonthSumByCategory(ChallengeVO challengeVO);
	
}
